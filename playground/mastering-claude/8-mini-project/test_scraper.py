import sys
from unittest.mock import patch, Mock

import pytest
import requests

from scraper import validate_url, extract_products, fetch_page, run, main, ScrapeError

PRODUCT_HTML = b"""
<html><body>
<article class="product_pod">
  <h3><a href="x" title="Full Title One">Full Titl...</a></h3>
  <p class="price_color">\xc2\xa351.77</p>
</article>
<article class="product_pod">
  <h3><a href="y" title="No Price Book">No Price Book</a></h3>
</article>
</body></html>
"""

NO_PRODUCT_HTML = b"<html><body><p>nothing here</p></body></html>"


# --- Task 1: URL validation (security boundary — must reject before any network call) ---

def test_validate_url_rejects_non_http_scheme():
    with pytest.raises(ScrapeError):
        validate_url("ftp://example.com")


def test_validate_url_rejects_empty():
    with pytest.raises(ScrapeError):
        validate_url("")


def test_validate_url_rejects_scheme_without_host():
    with pytest.raises(ScrapeError, match="missing a host"):
        validate_url("http://")


@patch("scraper.requests.get")
def test_invalid_url_never_hits_network(mock_get):
    with pytest.raises(ScrapeError):
        run("javascript:alert(1)", "out.csv")
    mock_get.assert_not_called()


# --- Task 3: extraction, skip incomplete ---

def test_extract_products_skips_incomplete_and_uses_full_title():
    products, skipped = extract_products(PRODUCT_HTML)
    assert products == [{"title": "Full Title One", "price": "£51.77"}]
    assert skipped == 1


def test_extract_products_returns_empty_when_no_containers():
    products, skipped = extract_products(NO_PRODUCT_HTML)
    assert products == []
    assert skipped == 0


def test_extract_products_falls_back_to_link_text_when_title_attr_missing():
    html = b"""
    <article class="product_pod">
      <h3><a href="z">Plain Text Title</a></h3>
      <p class="price_color">\xc2\xa31.00</p>
    </article>
    """
    products, skipped = extract_products(html)
    assert products == [{"title": "Plain Text Title", "price": "£1.00"}]
    assert skipped == 0


# --- Task 4: 0 products must be a failure, never a silent success ---

@patch("scraper.fetch_page", return_value=NO_PRODUCT_HTML)
def test_zero_products_raises_and_does_not_write_csv(mock_fetch, tmp_path):
    out = tmp_path / "out.csv"
    with pytest.raises(ScrapeError, match="no products were found"):
        run("https://example.com", str(out))
    assert not out.exists()


@patch("scraper.fetch_page", return_value=PRODUCT_HTML)
def test_success_writes_csv_with_only_complete_products(mock_fetch, tmp_path):
    out = tmp_path / "out.csv"
    products, skipped = run("https://example.com", str(out))
    assert len(products) == 1
    assert skipped == 1
    assert out.exists()
    content = out.read_text(encoding="utf-8")
    assert "Full Title One" in content
    assert "No Price Book" not in content


# --- Task 2: retry is bounded, not infinite ---

@patch("scraper.requests.get", side_effect=requests.ConnectionError("boom"))
@patch("scraper.time.sleep", return_value=None)
def test_fetch_page_retries_then_raises_bounded(mock_sleep, mock_get):
    with pytest.raises(ScrapeError, match="after 3 attempts"):
        fetch_page("https://example.com")
    assert mock_get.call_count == 3


@patch("scraper.requests.get")
def test_fetch_page_http_error_does_not_retry(mock_get):
    response = Mock()
    response.raise_for_status.side_effect = requests.HTTPError("404")
    mock_get.return_value = response
    with pytest.raises(ScrapeError):
        fetch_page("https://example.com")
    assert mock_get.call_count == 1


@patch("scraper.requests.get")
def test_fetch_page_success_returns_bytes(mock_get):
    response = Mock()
    response.raise_for_status.return_value = None
    response.content = PRODUCT_HTML
    mock_get.return_value = response
    assert fetch_page("https://example.com") == PRODUCT_HTML


# --- Task 6: CLI reporting (main()) ---

@patch("scraper.run", return_value=([{"title": "A", "price": "£1"}], 2))
def test_main_success_reports_scraped_and_skipped(mock_run, capsys, monkeypatch):
    monkeypatch.setattr(sys, "argv", ["scraper.py", "https://example.com", "-o", "out.csv"])
    main()
    out = capsys.readouterr().out
    assert "Scraped 1 products." in out
    assert "Skipped 2 incomplete products." in out
    assert "Saved to out.csv" in out


@patch("scraper.run", side_effect=ScrapeError("no products were found"))
def test_main_failure_exits_non_zero_with_stderr_message(mock_run, capsys, monkeypatch):
    monkeypatch.setattr(sys, "argv", ["scraper.py", "https://example.com"])
    with pytest.raises(SystemExit) as exc_info:
        main()
    assert exc_info.value.code == 1
    assert "Error: no products were found" in capsys.readouterr().err
