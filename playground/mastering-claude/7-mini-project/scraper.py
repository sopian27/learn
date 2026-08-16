#!/usr/bin/env python3
import argparse
import csv
import requests
from bs4 import BeautifulSoup


HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0 Safari/537.36"
}


def scrape_products(url):
    resp = requests.get(url, headers=HEADERS, timeout=10)
    soup = BeautifulSoup(resp.text, "html.parser")

    products = []
    containers = soup.select(".product, .product-item, .product-card, li.product")

    for c in containers:
        title_el = c.select_one(".product-title, h2, h3, .title")
        price_el = c.select_one(".price, .product-price, .amount")

        title = title_el.get_text(strip=True) if title_el else None
        price = price_el.get_text(strip=True) if price_el else None

        if title and price:
            products.append({"title": title, "price": price})

    return products


def main():
    parser = argparse.ArgumentParser(description="Scrape product title & price from an e-commerce page")
    parser.add_argument("url", help="URL of the e-commerce page")
    parser.add_argument("-o", "--output", default="products.csv", help="Output CSV file")
    args = parser.parse_args()

    products = scrape_products(args.url)

    with open(args.output, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=["title", "price"])
        writer.writeheader()
        writer.writerows(products)

    print(f"Saved {len(products)} products to {args.output}")


if __name__ == "__main__":
    main()
