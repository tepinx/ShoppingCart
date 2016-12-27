package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.model.Cart;
import com.amaysim.sc.model.ExpectedCartItems;
import com.amaysim.sc.model.Item;
import com.amaysim.sc.model.ItemsAdded;

public class BundleInRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		// item code value should be get from the database, but for now hard coded :)
		String itemCode = "ult_medium";
		int freeItemCount = 0;

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (entry.getValue().getItem().getCode().equals(itemCode)) {
				//number of item(s) is equivalent to number of free item(s)
				freeItemCount = entry.getValue().getCount();
			} 
		}

		//invoke create free item
		createFreeItems(cart.getExpectedCartItemsList(), freeItemCount);

		return cart;
	}

	/**
	 * Create free items then add to Expected Cart Items
	 * 
	 * @param expectedCartItemsList
	 * @param freeItemCount
	 */
	private void createFreeItems(
	    List<ExpectedCartItems> expectedCartItemsList,
	    int freeItemCount) {

		if (freeItemCount > 0) {
			//item should be get from the database but for now hard coded :)
			expectedCartItemsList.add(new ExpectedCartItems(new Item("1gb", "1 GB Data-pack", new BigDecimal(9.90).setScale(2, BigDecimal.ROUND_HALF_UP)), freeItemCount));
		}
	}
}
