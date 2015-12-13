# Camel Credit Card

This is a simple Camel integration that creads a [BMO-formatted MasterCard](https://www.bmo.com/mastercard) .csv file, selects relevant transactions, and loads them into QuickBooks.

For now this just picks up a CSV in src/test/resources, splits it, and plunks the split files down in target/data/quickbooks/in.
