#!/usr/bin/env python3
"""CLI scraper produk -> CSV. Disciplined refactor dari playground/mastering-claude/7-mini-project/scraper.py.

Spec: playground/mastering-claude/8-mini-project.md
"""
import argparse
import csv
import sys
import time
from urllib.parse import urlparse

import requests
from bs4 import BeautifulSoup

HEADERS = {
    "User-Agent": "Mozilla/5.0 (compatible; mastering-claude-scraper/1.0)"
}
TIMEOUT_SECONDS = 10
MAX_RETRIES = 3
RETRY_BACKOFF_SECONDS = 1

# Selector divalidasi terhadap struktur HTML nyata (books.toscrape.com),
# bukan tebakan seperti versi vibes-only.
PRODUCT_CONTAINER_SELECTOR = "article.product_pod"
TITLE_SELECTOR = "h3 a"
PRICE_SELECTOR = "p.price_color"


class ScrapeError(Exception):
    """Kegagalan yang harus menghasilkan exit non-zero + pesan jelas ke user."""


def validate_url(url):
    parsed = urlparse(url)
    if parsed.scheme not in ("http", "https"):
        raise ScrapeError(f"Invalid URL: '{url}' must use http:// or https://")
    if not parsed.netloc:
        raise ScrapeError(f"Invalid URL: '{url}' is missing a host")
    return url


def fetch_page(url):
    """HTTP GET dengan timeout eksplisit + retry terbatas untuk error transient."""
    last_error = None
    for attempt in range(1, MAX_RETRIES + 1):
        try:
            response = requests.get(url, headers=HEADERS, timeout=TIMEOUT_SECONDS)
            response.raise_for_status()
            # response.content (bytes), bukan .text: server ini tidak mengirim charset
            # di Content-Type, requests fallback .encoding ke ISO-8859-1 dan salah decode
            # UTF-8 (mis. "£" jadi "Â£"). BeautifulSoup auto-detect encoding dari bytes.
            return response.content
        except (requests.ConnectionError, requests.Timeout) as exc:
            last_error = exc
            if attempt < MAX_RETRIES:
                time.sleep(RETRY_BACKOFF_SECONDS * attempt)
        except requests.HTTPError as exc:
            raise ScrapeError(f"HTTP request failed: {exc}") from exc

    raise ScrapeError(
        f"Network request failed after {MAX_RETRIES} attempts: {last_error}"
    )


def extract_products(html):
    """Return (products, skipped_count). Tidak pernah menganggap 0 hasil sebagai sukses di sini —
    itu keputusan caller (main), fungsi ini murni ekstraksi + skip counting."""
    soup = BeautifulSoup(html, "html.parser")
    containers = soup.select(PRODUCT_CONTAINER_SELECTOR)

    products = []
    skipped = 0

    for container in containers:
        title_el = container.select_one(TITLE_SELECTOR)
        price_el = container.select_one(PRICE_SELECTOR)

        # title penuh ada di attribute `title`, teks <a> sering dipotong ("...") oleh CSS
        title = title_el.get("title", "").strip() if title_el else ""
        if not title and title_el:
            title = title_el.get_text(strip=True)
        price = price_el.get_text(strip=True) if price_el else ""

        if not title or not price:
            skipped += 1
            continue

        products.append({"title": title, "price": price})

    return products, skipped


def write_csv(products, output_path):
    with open(output_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=["title", "price"])
        writer.writeheader()
        writer.writerows(products)


def run(url, output_path):
    validate_url(url)
    html = fetch_page(url)
    products, skipped = extract_products(html)

    if not products:
        raise ScrapeError(
            "Page loaded successfully but no products were found "
            "(selector may not match this page's structure)."
        )

    write_csv(products, output_path)
    return products, skipped


def main():
    parser = argparse.ArgumentParser(
        description="Scrape product title & price from an e-commerce catalogue page"
    )
    parser.add_argument("url", help="URL of the e-commerce catalogue page")
    parser.add_argument("-o", "--output", default="products.csv", help="Output CSV file")
    args = parser.parse_args()

    try:
        products, skipped = run(args.url, args.output)
    except ScrapeError as exc:
        print(f"Error: {exc}", file=sys.stderr)
        sys.exit(1)

    print(f"Scraped {len(products)} products.")
    if skipped:
        print(f"Skipped {skipped} incomplete products.")
    print(f"Saved to {args.output}")


if __name__ == "__main__":
    main()
