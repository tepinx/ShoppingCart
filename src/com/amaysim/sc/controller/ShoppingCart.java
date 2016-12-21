package com.amaysim.sc.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.pojo.Cart;
import com.amaysim.sc.pojo.ExpectedCartItems;
import com.amaysim.sc.pojo.Item;
import com.amaysim.sc.pojo.ItemsAdded;
import com.amaysim.sc.rule.PricingRule;

public class ShoppingCart {

	private Cart cart;

	public ShoppingCart() {
		super();
		this.cart = new Cart();
	}

	public ShoppingCart(List<PricingRule> pricingRules) {
		super();
		this.cart = new Cart();
		this.cart.setPricingRules(pricingRules);
	}

	public Boolean add(
	    Item item) {

		ItemsAdded itemsAdded = cart.getItemsAddedMap().get(item.getCode());
		MathContext mc = new MathContext(4);
		
		if (itemsAdded != null) {
			itemsAdded.setCount(itemsAdded.getCount() + 1);
			itemsAdded.setOrigPriceTotal(itemsAdded.getOrigPriceTotal().add(itemsAdded.getItem().getPrice(), mc));
		} else {
			ItemsAdded newItemsAdded = new ItemsAdded();
			newItemsAdded.setItem(item);
			newItemsAdded.setCount(newItemsAdded.getCount() + 1);
			newItemsAdded.setOrigPriceTotal(newItemsAdded.getItem().getPrice());
			cart.getItemsAddedMap().put(item.getCode(), newItemsAdded);
		}

		return true;
	}

	public Boolean add(
	    Item item,
	    String promoCode) {

		ItemsAdded itemsAdded = cart.getItemsAddedMap().get(item.getCode());
		MathContext mc = new MathContext(4);
		
		if (itemsAdded != null) {
			itemsAdded.setCount(itemsAdded.getCount() + 1);
			itemsAdded.setOrigPriceTotal(itemsAdded.getOrigPriceTotal().add(itemsAdded.getItem().getPrice(), mc));
		} else {
			ItemsAdded newItemsAdded = new ItemsAdded();
			newItemsAdded.setItem(item);
			newItemsAdded.setCount(newItemsAdded.getCount() + 1);
			newItemsAdded.setOrigPriceTotal(newItemsAdded.getItem().getPrice());
			cart.getItemsAddedMap().put(item.getCode(), newItemsAdded);
			cart.setPromoCode(promoCode);
		}

		return true;
	}

	public BigDecimal total() {

		BigDecimal total = new BigDecimal(0);
		MathContext mc = new MathContext(4);

		applyPricingRule(cart);
		
		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (entry.getValue().getDiscPriceTotal() != null && entry.getValue().getDiscPriceTotal().intValue() > 0) {
				total = total.add(entry.getValue().getDiscPriceTotal(), mc);
			} else {
				total = total.add(entry.getValue().getOrigPriceTotal(), mc);
			}
		}

		return total;
	}

	public List<ExpectedCartItems> items() {
		return cart.getExpectedCartItemsList();
	}
	
	public Map<String, ItemsAdded> addedItems() {
		return cart.getItemsAddedMap();
	}

	private void applyPricingRule(
	    Cart cart) {
		for (PricingRule pr : cart.getPricingRules()) {
			pr.applyRule(cart);
		}
	}
	
}
