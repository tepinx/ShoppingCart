package com.amaysim.sc.controller;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amaysim.sc.controller.ShoppingCart;
import com.amaysim.sc.model.Item;
import com.amaysim.sc.rule.AddPromoCodeRuleImpl;
import com.amaysim.sc.rule.BulkDiscountRuleImpl;
import com.amaysim.sc.rule.BundleInRuleImpl;
import com.amaysim.sc.rule.DealPromoRuleImpl;
import com.amaysim.sc.rule.PricingRule;

public class ShoppingCartTest {

	private static ShoppingCart sccScen1;
	private static ShoppingCart sccScen2;
	private static ShoppingCart sccScen3;
	private static ShoppingCart sccScen4;
	private static ShoppingCart sccNoRuleScen1;
	private static ShoppingCart sccNoRuleScen2;
	private static ShoppingCart sccNoRuleScen3;
	private static ShoppingCart sccNoRuleScen4;
	private static List<Item> items;
	private static Item ult_small;
	private static Item ult_medium;
	private static Item ult_large;
	private static Item onegb;
	
	@BeforeClass
	public static void initShoppingCart() {
		
		sccScen1 = new ShoppingCart(getPricingRuleList());
		sccScen2 = new ShoppingCart(getPricingRuleList());
		sccScen3 = new ShoppingCart(getPricingRuleList());
		sccScen4 = new ShoppingCart(getPricingRuleList());
		
		sccNoRuleScen1 = new ShoppingCart();
		sccNoRuleScen2 = new ShoppingCart();
		sccNoRuleScen3 = new ShoppingCart();
		sccNoRuleScen4 = new ShoppingCart();
		
		items = new ArrayList<Item>();
	}

	@Before
	public void beforeEachTest() {
		
		ult_small = new Item("ult_small", "Unlimited 1GB", new BigDecimal(24.90).setScale(2, BigDecimal.ROUND_HALF_UP));
		ult_medium = new Item("ult_medium", "Unlimited 2GB", new BigDecimal(29.90).setScale(2, BigDecimal.ROUND_HALF_UP));
		ult_large = new Item("ult_large", "Unlimited 5GB", new BigDecimal(44.90).setScale(2, BigDecimal.ROUND_HALF_UP));
		onegb = new Item("1gb", "1 GB Data-pack", new BigDecimal(9.90).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		items.add(ult_small);
		items.add(ult_medium);
		items.add(ult_large);
		items.add(onegb);
		
		sccScen1.add(ult_small);
		sccScen1.add(ult_small);
		sccScen1.add(ult_small);
		sccScen1.add(ult_large);
		
		sccScen2.add(ult_small);
		sccScen2.add(ult_small);
		sccScen2.add(ult_large);
		sccScen2.add(ult_large);
		sccScen2.add(ult_large);
		sccScen2.add(ult_large);
		
		sccScen3.add(ult_small);
		sccScen3.add(ult_medium);
		sccScen3.add(ult_medium);
		
		sccScen4.add(ult_small, "I<3AMAYSIM");
		sccScen4.add(onegb, "I<3AMAYSIM");
		
		sccNoRuleScen1.add(ult_small);
		sccNoRuleScen1.add(ult_small);
		sccNoRuleScen1.add(ult_small);
		sccNoRuleScen1.add(ult_large);
		
		sccNoRuleScen2.add(ult_small);
		sccNoRuleScen2.add(ult_small);
		sccNoRuleScen2.add(ult_large);
		sccNoRuleScen2.add(ult_large);
		sccNoRuleScen2.add(ult_large);
		sccNoRuleScen2.add(ult_large);
		
		sccNoRuleScen3.add(ult_small);
		sccNoRuleScen3.add(ult_medium);
		sccNoRuleScen3.add(ult_medium);
		
		sccNoRuleScen4.add(ult_small, "I<3AMAYSIM");
		sccNoRuleScen4.add(onegb, "I<3AMAYSIM");
	}

	@After
	public void afterEachTest() {
		
		items.clear();
		
		sccScen1 = new ShoppingCart(getPricingRuleList());
		sccScen2 = new ShoppingCart(getPricingRuleList());
		sccScen3 = new ShoppingCart(getPricingRuleList());
		sccScen4 = new ShoppingCart(getPricingRuleList());
		
		sccNoRuleScen1 = new ShoppingCart();
		sccNoRuleScen2 = new ShoppingCart();
		sccNoRuleScen3 = new ShoppingCart();
		sccNoRuleScen4 = new ShoppingCart();
		
	}
	
	@Test
	public void testAddByItem() {
		assertEquals(true, sccScen1.add(ult_small));
		assertEquals(true, sccNoRuleScen1.add(ult_small));
	}
	
	@Test
	public void testAddByItemAndPromoCode() {
		assertEquals(true, sccScen4.add(ult_small, "I<3AMAYSIM"));
		assertEquals(true, sccNoRuleScen4.add(ult_small, "I<3AMAYSIM"));
	}
	
	@Test
	public void testTotalWithPricingRule() {
		assertEquals(new BigDecimal(94.70).setScale(2, BigDecimal.ROUND_HALF_UP), sccScen1.total());
		
		assertEquals(new BigDecimal(209.40).setScale(2, BigDecimal.ROUND_HALF_UP), sccScen2.total());
		
		assertEquals(new BigDecimal(84.70).setScale(2, BigDecimal.ROUND_HALF_UP), sccScen3.total());
		
		assertEquals(new BigDecimal(31.32).setScale(2, BigDecimal.ROUND_HALF_UP), sccScen4.total());
	}
	
	@Test
	public void testTotalWithOutPricingRule() {
		assertEquals(new BigDecimal(119.60).setScale(2, BigDecimal.ROUND_HALF_UP), sccNoRuleScen1.total());
		
		assertEquals(new BigDecimal(229.40).setScale(2, BigDecimal.ROUND_HALF_UP), sccNoRuleScen2.total());
		
		assertEquals(new BigDecimal(84.70).setScale(2, BigDecimal.ROUND_HALF_UP), sccNoRuleScen3.total());
		
		assertEquals(new BigDecimal(34.80).setScale(2, BigDecimal.ROUND_HALF_UP), sccNoRuleScen4.total());
	}
	
	@Test
	public void testItemsWithPricingRule() {
		assertThat(sccScen1.items().size(), is(2));
		assertEquals(sccScen1.items().get(0).getItem().getCode(), ult_small.getCode());
		assertEquals(sccScen1.items().get(0).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccScen1.items().get(0).getItem().getName(), ult_small.getName());
		assertEquals(sccScen1.items().get(0).getCount(), 3);
		assertEquals(sccScen1.items().get(1).getItem().getCode(), ult_large.getCode());
		assertEquals(sccScen1.items().get(1).getItem().getPrice(), ult_large.getPrice());
		assertEquals(sccScen1.items().get(1).getItem().getName(), ult_large.getName());
		assertEquals(sccScen1.items().get(1).getCount(), 1);
		
		assertThat(sccScen2.items().size(), is(2));
		assertEquals(sccScen2.items().get(0).getItem().getCode(), ult_small.getCode());
		assertEquals(sccScen2.items().get(0).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccScen2.items().get(0).getItem().getName(), ult_small.getName());
		assertEquals(sccScen2.items().get(0).getCount(), 2);
		assertEquals(sccScen2.items().get(1).getItem().getCode(), ult_large.getCode());
		assertEquals(sccScen2.items().get(1).getItem().getPrice(), ult_large.getPrice());
		assertEquals(sccScen2.items().get(1).getItem().getName(), ult_large.getName());
		assertEquals(sccScen2.items().get(1).getCount(), 4);
		
		assertThat(sccScen3.items().size(), is(3));
		assertEquals(sccScen3.items().get(0).getItem().getCode(), onegb.getCode());
		assertEquals(sccScen3.items().get(0).getItem().getPrice(), onegb.getPrice());
		assertEquals(sccScen3.items().get(0).getItem().getName(), onegb.getName());
		assertEquals(sccScen3.items().get(0).getCount(), 2);
		assertEquals(sccScen3.items().get(1).getItem().getCode(), ult_medium.getCode());
		assertEquals(sccScen3.items().get(1).getItem().getPrice(), ult_medium.getPrice());
		assertEquals(sccScen3.items().get(1).getItem().getName(), ult_medium.getName());
		assertEquals(sccScen3.items().get(1).getCount(), 2);
		assertEquals(sccScen3.items().get(2).getItem().getCode(), ult_small.getCode());
		assertEquals(sccScen3.items().get(2).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccScen3.items().get(2).getItem().getName(), ult_small.getName());
		assertEquals(sccScen3.items().get(2).getCount(), 1);
		
		assertThat(sccScen4.items().size(), is(2));
		assertEquals(sccScen4.items().get(0).getItem().getCode(), ult_small.getCode());
		assertEquals(sccScen4.items().get(0).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccScen4.items().get(0).getItem().getName(), ult_small.getName());
		assertEquals(sccScen4.items().get(0).getCount(), 1);
		assertEquals(sccScen4.items().get(1).getItem().getCode(), onegb.getCode());
		assertEquals(sccScen4.items().get(1).getItem().getPrice(), onegb.getPrice());
		assertEquals(sccScen4.items().get(1).getItem().getName(), onegb.getName());
		assertEquals(sccScen4.items().get(1).getCount(), 1);
	}
	
	@Test
	public void testItemsWithOutPricingRule() {
		assertThat(sccNoRuleScen1.items().size(), is(2));
		assertEquals(sccNoRuleScen1.items().get(0).getItem().getCode(), ult_small.getCode());
		assertEquals(sccNoRuleScen1.items().get(0).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccNoRuleScen1.items().get(0).getItem().getName(), ult_small.getName());
		assertEquals(sccNoRuleScen1.items().get(0).getCount(), 3);
		assertEquals(sccNoRuleScen1.items().get(1).getItem().getCode(), ult_large.getCode());
		assertEquals(sccNoRuleScen1.items().get(1).getItem().getPrice(), ult_large.getPrice());
		assertEquals(sccNoRuleScen1.items().get(1).getItem().getName(), ult_large.getName());
		assertEquals(sccNoRuleScen1.items().get(1).getCount(), 1);
		
		assertThat(sccNoRuleScen2.items().size(), is(2));
		assertEquals(sccNoRuleScen2.items().get(0).getItem().getCode(), ult_small.getCode());
		assertEquals(sccNoRuleScen2.items().get(0).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccNoRuleScen2.items().get(0).getItem().getName(), ult_small.getName());
		assertEquals(sccNoRuleScen2.items().get(0).getCount(), 2);
		assertEquals(sccNoRuleScen2.items().get(1).getItem().getCode(), ult_large.getCode());
		assertEquals(sccNoRuleScen2.items().get(1).getItem().getPrice(), ult_large.getPrice());
		assertEquals(sccNoRuleScen2.items().get(1).getItem().getName(), ult_large.getName());
		assertEquals(sccNoRuleScen2.items().get(1).getCount(), 4);
		
		assertThat(sccNoRuleScen3.items().size(), is(2));
		assertEquals(sccNoRuleScen3.items().get(0).getItem().getCode(), ult_medium.getCode());
		assertEquals(sccNoRuleScen3.items().get(0).getItem().getPrice(), ult_medium.getPrice());
		assertEquals(sccNoRuleScen3.items().get(0).getItem().getName(), ult_medium.getName());
		assertEquals(sccNoRuleScen3.items().get(0).getCount(), 2);
		assertEquals(sccNoRuleScen3.items().get(1).getItem().getCode(), ult_small.getCode());
		assertEquals(sccNoRuleScen3.items().get(1).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccNoRuleScen3.items().get(1).getItem().getName(), ult_small.getName());
		assertEquals(sccNoRuleScen3.items().get(1).getCount(), 1);
		
		assertThat(sccNoRuleScen4.items().size(), is(2));
		assertEquals(sccNoRuleScen4.items().get(0).getItem().getCode(), ult_small.getCode());
		assertEquals(sccNoRuleScen4.items().get(0).getItem().getPrice(), ult_small.getPrice());
		assertEquals(sccNoRuleScen4.items().get(0).getItem().getName(), ult_small.getName());
		assertEquals(sccNoRuleScen4.items().get(0).getCount(), 1);
		assertEquals(sccNoRuleScen4.items().get(1).getItem().getCode(), onegb.getCode());
		assertEquals(sccNoRuleScen4.items().get(1).getItem().getPrice(), onegb.getPrice());
		assertEquals(sccNoRuleScen4.items().get(1).getItem().getName(), onegb.getName());
		assertEquals(sccNoRuleScen4.items().get(1).getCount(), 1);
	}
	
	private static List<PricingRule> getPricingRuleList() {
		
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

}
