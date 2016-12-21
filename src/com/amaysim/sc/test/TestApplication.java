package com.amaysim.sc.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.controller.ShoppingCart;
import com.amaysim.sc.pojo.ExpectedCartItems;
import com.amaysim.sc.pojo.Item;
import com.amaysim.sc.pojo.ItemsAdded;
import com.amaysim.sc.rule.AddPromoCodeRuleImpl;
import com.amaysim.sc.rule.BulkDiscountRuleImpl;
import com.amaysim.sc.rule.BundleInRuleImpl;
import com.amaysim.sc.rule.DealPromoRuleImpl;
import com.amaysim.sc.rule.PricingRule;

public class TestApplication {

	public static void main(
	    String[] args) {

		List<PricingRule> pricingRules = new ArrayList<PricingRule>();
		AddPromoCodeRuleImpl addPromoCodeRuleImpl = new AddPromoCodeRuleImpl();
		BulkDiscountRuleImpl bulkDiscountRuleImpl = new BulkDiscountRuleImpl();
		BundleInRuleImpl bundleInRuleImpl = new BundleInRuleImpl();
		DealPromoRuleImpl dealPromoRuleImpl = new DealPromoRuleImpl();
		pricingRules.add(bulkDiscountRuleImpl);
		pricingRules.add(bundleInRuleImpl);
		pricingRules.add(dealPromoRuleImpl);
		pricingRules.add(addPromoCodeRuleImpl);

		System.out.println("####################Scenario1####################");
		ShoppingCart sccScen1 = new ShoppingCart(pricingRules);
		sccScen1.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen1.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen1.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen1.add(new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90)));

		System.out.println("Items Added:");
		for (Map.Entry<String, ItemsAdded> entry : sccScen1.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		System.out.println("\nExpected Cart Total = " + "$" + sccScen1.total()+"\n");
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen1.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario2####################");
		ShoppingCart sccScen2 = new ShoppingCart(pricingRules);
		sccScen2.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen2.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen2.add(new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90)));
		sccScen2.add(new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90)));
		sccScen2.add(new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90)));
		sccScen2.add(new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90)));

		System.out.println("Items Added:");
		for (Map.Entry<String, ItemsAdded> entry : sccScen2.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		System.out.println("\nExpected Cart Total = " + "$" + sccScen2.total()+"\n");
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen2.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario3####################");
		ShoppingCart sccScen3 = new ShoppingCart(pricingRules);
		sccScen3.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen3.add(new Item("ult_medium", "Unlimited 2GB", new BigDecimal(29.90)));
		sccScen3.add(new Item("ult_medium", "Unlimited 2GB", new BigDecimal(29.90)));

		System.out.println("Items Added:");
		for (Map.Entry<String, ItemsAdded> entry : sccScen3.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		System.out.println("\nExpected Cart Total = " + "$" + sccScen3.total()+"\n");
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen3.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario4####################");
		ShoppingCart sccScen4 = new ShoppingCart(pricingRules);
		sccScen4.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)), "I<3AMAYSIM");
		sccScen4.add(new Item("1gb", "1 GB Data-pack", new BigDecimal(9.90)), "I<3AMAYSIM");

		System.out.println("Items Added:");
		for (Map.Entry<String, ItemsAdded> entry : sccScen4.addedItems().entrySet()) {
			System.out.println(entry.getValue().getCount() + " X " + entry.getValue().getItem().getName());
		}
		System.out.println("\nExpected Cart Total = " + "$" + sccScen4.total()+"\n");
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen4.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}
	}
}
