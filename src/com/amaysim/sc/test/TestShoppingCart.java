package com.amaysim.sc.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.controller.ShoppingCart;
import com.amaysim.sc.model.ExpectedCartItems;
import com.amaysim.sc.model.Item;
import com.amaysim.sc.model.ItemsAdded;
import com.amaysim.sc.rule.AddPromoCodeRuleImpl;
import com.amaysim.sc.rule.BulkDiscountRuleImpl;
import com.amaysim.sc.rule.BundleInRuleImpl;
import com.amaysim.sc.rule.DealPromoRuleImpl;
import com.amaysim.sc.rule.PricingRule;

public class TestShoppingCart {

	public static void main(String[] args) {

		// list of all items
		List<Item> items = new ArrayList<Item>();
		Item ult_small = new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90));
		Item ult_medium = new Item("ult_medium", "Unlimited 2GB", new BigDecimal(29.90));
		Item ult_large = new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90));
		Item onegb = new Item("1gb", "1 GB Data-pack", new BigDecimal(9.90));
		items.add(ult_small);
		items.add(ult_medium);
		items.add(ult_large);
		items.add(onegb);

		System.out.println("####################Scenario 1####################");
		// display all items
		displayAllItems(items);

		// set pricing rule to constructor
		ShoppingCart sccScen1 = new ShoppingCart(getPricingRuleList());
		// ShoppingCart sccScen1 = new ShoppingCart();
		// add new items
		sccScen1.add(ult_small);
		sccScen1.add(ult_small);
		sccScen1.add(ult_small);
		sccScen1.add(ult_large);

		System.out.println("\nItems Added:\n");
		// list all items added to the cart
		for (Map.Entry<String, ItemsAdded> entry : sccScen1.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		// get cart total price
		System.out.println("\nExpected Cart Total = " + "$" + sccScen1.total() + "\n");

		System.out.println("Expected Cart Items:");
		// list all expected cart items
		for (ExpectedCartItems eci : sccScen1.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario 2####################");
		// display all items
		displayAllItems(items);

		// set pricing rule to constructor
		ShoppingCart sccScen2 = new ShoppingCart(getPricingRuleList());
		// ShoppingCart sccScen2 = new ShoppingCart();
		// add new items
		sccScen2.add(ult_small);
		sccScen2.add(ult_small);
		sccScen2.add(ult_large);
		sccScen2.add(ult_large);
		sccScen2.add(ult_large);
		sccScen2.add(ult_large);

		System.out.println("Items Added:");
		// list all items added to the cart
		for (Map.Entry<String, ItemsAdded> entry : sccScen2.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		// get cart total price
		System.out.println("\nExpected Cart Total = " + "$" + sccScen2.total() + "\n");

		System.out.println("Expected Cart Items:");
		// list all expected cart items
		for (ExpectedCartItems eci : sccScen2.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario 3####################");
		// display all items
		displayAllItems(items);

		// set pricing rule to constructor
		ShoppingCart sccScen3 = new ShoppingCart(getPricingRuleList());
		// ShoppingCart sccScen3 = new ShoppingCart();
		// add new items
		sccScen3.add(ult_small);
		sccScen3.add(ult_medium);
		sccScen3.add(ult_medium);

		System.out.println("Items Added:");
		// list all items added to the cart
		for (Map.Entry<String, ItemsAdded> entry : sccScen3.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		// get cart total price
		System.out.println("\nExpected Cart Total = " + "$" + sccScen3.total() + "\n");

		System.out.println("Expected Cart Items:");
		// list all expected cart items
		for (ExpectedCartItems eci : sccScen3.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario 4####################");
		// display all items
		displayAllItems(items);

		// set pricing rule to constructor
		ShoppingCart sccScen4 = new ShoppingCart(getPricingRuleList());
		// ShoppingCart sccScen4 = new ShoppingCart();
		// add new items and promo code
		sccScen4.add(ult_small, "I<3AMAYSIM");
		sccScen4.add(onegb, "I<3AMAYSIM");

		System.out.println("Items Added:");
		// list all items added to the cart
		for (Map.Entry<String, ItemsAdded> entry : sccScen4.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		// get cart total price
		System.out.println("\nExpected Cart Total = " + "$" + sccScen4.total() + "\n");

		System.out.println("Expected Cart Items:");
		// list all expected cart items
		for (ExpectedCartItems eci : sccScen4.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}
	}

	private static List<PricingRule> getPricingRuleList() {
		// List of pricing rule
		List<PricingRule> pricingRules = new ArrayList<PricingRule>();
		AddPromoCodeRuleImpl addPromoCodeRuleImpl = new AddPromoCodeRuleImpl();
		BulkDiscountRuleImpl bulkDiscountRuleImpl = new BulkDiscountRuleImpl();
		BundleInRuleImpl bundleInRuleImpl = new BundleInRuleImpl();
		DealPromoRuleImpl dealPromoRuleImpl = new DealPromoRuleImpl();
		pricingRules.add(addPromoCodeRuleImpl);
		pricingRules.add(bulkDiscountRuleImpl);
		pricingRules.add(dealPromoRuleImpl);
		pricingRules.add(bundleInRuleImpl);

		return pricingRules;
	}

	private static void displayAllItems(
	    List<Item> items) {

		System.out.println("List of all Items:");

		System.out.println("-----------------------------------------");
		System.out.println("|Product Code	|Product Name	|Price	|");
		for (Item item : items) {
			System.out.println("-----------------------------------------");
			if (item.getCode().equals("1gb")) {
				System.out.println("|" + item.getCode() + "		|" + item.getName() + "	|" + item.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + "	|");
			} else {
				System.out.println("|" + item.getCode() + "	|" + item.getName() + "	|" + item.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + "	|");
			}
		}
		System.out.println("-----------------------------------------");
	}
}
