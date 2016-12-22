package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.util.Map;

import com.amaysim.sc.model.Cart;
import com.amaysim.sc.model.ItemsAdded;

public class DealPromoRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		// item code value should be get from the database, but for now hard coded :)
		String itemCode = "ult_small";
		int itemsDealCount = 0;

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (entry.getValue().getItem().getCode().equals(itemCode)) {
				// check if item count is equal or more than 3
				if (entry.getValue().getCount() >= 3) {
					//get the items deal count for the pricing rule (if you buy 3 items, you will pay the price of two only)
					itemsDealCount = ((entry.getValue().getCount() / 3) * 2) + (entry.getValue().getCount() % 3);
				} else {
					itemsDealCount = entry.getValue().getCount();
				}
				//get item price multiply by items deal count
				entry.getValue().setDiscPriceTotal(entry.getValue().getItem().getPrice().multiply(new BigDecimal(itemsDealCount)));
			}
		}
		return cart;
	}
}
