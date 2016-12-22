package com.amaysim.sc.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.model.Cart;
import com.amaysim.sc.model.ExpectedCartItems;
import com.amaysim.sc.model.Item;
import com.amaysim.sc.model.ItemsAdded;
import com.amaysim.sc.rule.AddPromoCodeRuleImpl;
import com.amaysim.sc.rule.BulkDiscountRuleImpl;
import com.amaysim.sc.rule.BundleInRuleImpl;
import com.amaysim.sc.rule.DealPromoRuleImpl;
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

	/**
	 * Add Item to Cart
	 * 
	 * @param item
	 * @return Boolean
	 */
	public Boolean add(
	    Item item) {

		// find item in the Items Added Map
		ItemsAdded itemsAdded = cart.getItemsAddedMap().get(item.getCode());

		if (itemsAdded != null) {
			// increment the count of an item
			itemsAdded.setCount(itemsAdded.getCount() + 1);
			// add to original price total
			itemsAdded.setOrigPriceTotal(itemsAdded.getOrigPriceTotal().add(itemsAdded.getItem().getPrice()));
		} else {
			// put new items added in the map
			cart.getItemsAddedMap().put(item.getCode(), new ItemsAdded(item, 1, item.getPrice()));
		}

		return true;
	}

	/**
	 * Add Item to Cart
	 * 
	 * @param item
	 * @param promoCode
	 * @return Boolean
	 */
	public Boolean add(
	    Item item,
	    String promoCode) {

		// find item in the Items Added Map
		ItemsAdded itemsAdded = cart.getItemsAddedMap().get(item.getCode());

		if (itemsAdded != null) {
			// increment the count of an item
			itemsAdded.setCount(itemsAdded.getCount() + 1);
			// add the item price to original price total
			itemsAdded.setOrigPriceTotal(itemsAdded.getOrigPriceTotal().add(itemsAdded.getItem().getPrice()));
		} else {
			// put the new items added in the map
			cart.getItemsAddedMap().put(item.getCode(), new ItemsAdded(item, 1, item.getPrice()));
			// set promo code
			cart.setPromoCode(promoCode);
		}

		return true;
	}

	/**
	 * Get the cart total price
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal total() {

		// apply pricing rule
		applyPricingRule(cart);

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			// check the discounted price total if not zero
			if (entry.getValue().getDiscPriceTotal() != null && entry.getValue().getDiscPriceTotal().intValue() > 0) {
				// get the discounted price then add to cart total price
				cart.setTotal(cart.getTotal().add(entry.getValue().getDiscPriceTotal()));
			} else {
				// get the original price then add to cart total price
				cart.setTotal(cart.getTotal().add(entry.getValue().getOrigPriceTotal()));
			}
		}

		// round off the total price to two decimal places
		return cart.getTotal().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Get the expected Cart items
	 * 
	 * @return List<ExpectedCartItems>
	 */
	public List<ExpectedCartItems> items() {
		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			addToExpectedCartItems(cart.getExpectedCartItemsList(), entry.getValue());
		}
		return cart.getExpectedCartItemsList();
	}

	/**
	 * Get the items added in the cart
	 * 
	 * @return Map<String, ItemsAdded>
	 */
	public Map<String, ItemsAdded> addedItems() {
		return cart.getItemsAddedMap();
	}

	/**
	 * Apply the pricing rule
	 * 
	 * @param cart
	 */
	private void applyPricingRule(
	    Cart cart) {

		PricingRule addPromoCodeRuleImpl = null;

		if (cart.getPricingRules() != null) {
			for (PricingRule pr : cart.getPricingRules()) {
				if (pr instanceof AddPromoCodeRuleImpl) {
					addPromoCodeRuleImpl = pr;
				} else if (pr instanceof BulkDiscountRuleImpl) {
					pr.applyRule(cart);
				} else if (pr instanceof BundleInRuleImpl) {
					pr.applyRule(cart);
				} else if (pr instanceof DealPromoRuleImpl) {
					pr.applyRule(cart);
				}
			}

			// adding a promo code will discount the cart total price so it needs to apply in the last
			if (addPromoCodeRuleImpl != null) {
				addPromoCodeRuleImpl.applyRule(cart);
			}
		}
	}

	/**
	 * Add the items added to Expected Cart Items
	 * 
	 * @param expectedCartItemsList
	 * @param itemsAdded
	 */
	private void addToExpectedCartItems(
	    List<ExpectedCartItems> expectedCartItemsList,
	    ItemsAdded itemsAdded) {

		expectedCartItemsList.add(new ExpectedCartItems(itemsAdded.getItem(), itemsAdded.getCount()));
	}

}
