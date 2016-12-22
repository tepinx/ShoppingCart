package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.util.Map;

import com.amaysim.sc.model.Cart;
import com.amaysim.sc.model.ItemsAdded;

public class BulkDiscountRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		// item code and drop price value should be get from the database, but for now hard coded :)
		String itemCode = "ult_large";
		BigDecimal dropPrice = new BigDecimal(39.90);

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			// check item code if correct and item count if greater than 3
			if (entry.getValue().getItem().getCode().equals(itemCode) && entry.getValue().getCount() > 3) {
				//multiply the item count to drop price and set to discount price total
				entry.getValue().setDiscPriceTotal(new BigDecimal(entry.getValue().getCount()).multiply(dropPrice));
			} 
		}
		return cart;
	}
}
