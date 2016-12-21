package com.amaysim.shopping.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

	private List<ExpectedCartItems> expectedCartItemsList = new ArrayList<ExpectedCartItems>();
	private Map<String, ItemsAdded> itemsAddedMap = new HashMap<String, ItemsAdded>();
	private BigDecimal total = new BigDecimal(0);
	private List<PricingRule> pricingRules = new ArrayList<PricingRule>();
	private String promoCode;

	public List<ExpectedCartItems> getExpectedCartItemsList() {
		return expectedCartItemsList;
	}

	public void setExpectedCartItemsList(
	    List<ExpectedCartItems> expectedCartItemsList) {
		this.expectedCartItemsList = expectedCartItemsList;
	}

	public Map<String, ItemsAdded> getItemsAddedMap() {
		return itemsAddedMap;
	}

	public void setItemsAddedMap(
	    Map<String, ItemsAdded> itemsAddedMap) {
		this.itemsAddedMap = itemsAddedMap;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(
	    BigDecimal total) {
		this.total = total;
	}

	public List<PricingRule> getPricingRules() {
		return pricingRules;
	}

	public void setPricingRules(
	    List<PricingRule> pricingRules) {
		this.pricingRules = pricingRules;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(
	    String promoCode) {
		this.promoCode = promoCode;
	}
}
