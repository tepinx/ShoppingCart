package com.amaysim.shopping.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StartApplication {
	public static void main(
	    String[] args) {

		List<PricingRule> pricingRules = new ArrayList<PricingRule>();
		AddPromoCodePromoImpl addPromoCodePromoImpl = new AddPromoCodePromoImpl();
		BulkDiscountPromoImpl bulkDiscountPromoImpl = new BulkDiscountPromoImpl();
		BundleInPromotionImpl bundleInPromotionImpl = new BundleInPromotionImpl();
		DealPromotionImpl dealPromotionImpl = new DealPromotionImpl();
		pricingRules.add(bulkDiscountPromoImpl);
		pricingRules.add(bundleInPromotionImpl);
		pricingRules.add(dealPromotionImpl);
		pricingRules.add(addPromoCodePromoImpl);

		System.out.println("####################Scenario1####################");
		ShoppingCart sccScen1 = new ShoppingCart(pricingRules);
		sccScen1.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen1.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen1.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen1.add(new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90)));

		System.out.println("Expected Cart Total = " + "$" + sccScen1.total());
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

		System.out.println("Expected Cart Total = " + "$" + sccScen2.total());
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen2.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario3####################");
		ShoppingCart sccScen3 = new ShoppingCart(pricingRules);
		sccScen3.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)));
		sccScen3.add(new Item("ult_medium", "Unlimited 2GB", new BigDecimal(29.90)));
		sccScen3.add(new Item("ult_medium", "Unlimited 2GB", new BigDecimal(29.90)));

		System.out.println("Expected Cart Total = " + "$" + sccScen3.total());
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen3.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}

		System.out.println("####################Scenario4####################");
		ShoppingCart sccScen4 = new ShoppingCart(pricingRules);
		sccScen4.add(new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90)), "I<3AMAYSIM");
		sccScen4.add(new Item("1gb", "1 GB Data-pack", new BigDecimal(9.90)), "I<3AMAYSIM");

		System.out.println("Expected Cart Total = " + "$" + sccScen4.total());
		System.out.println("Expected Cart Items:");
		for (ExpectedCartItems eci : sccScen4.items()) {
			System.out.println(eci.getCount() + " X " + eci.getItem().getName());
		}
	}
}
