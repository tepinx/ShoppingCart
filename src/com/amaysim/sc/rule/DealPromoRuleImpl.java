package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.pojo.Cart;
import com.amaysim.sc.pojo.ExpectedCartItems;
import com.amaysim.sc.pojo.ItemsAdded;

public class DealPromoRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		String itemCode = "ult_small";
		boolean isExpectedCartItemsListEmpty = cart.getExpectedCartItemsList().isEmpty();
		int count = 0;
		MathContext mc = new MathContext(4);

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (entry.getValue().getItem().getCode().equals(itemCode)) {

				if (entry.getValue().getCount() >= 3) {
					count = ((entry.getValue().getCount() / 3) * 2) + (entry.getValue().getCount() % 3);
				} else {
					count = entry.getValue().getCount();
				}

				entry.getValue().setDiscPriceTotal(entry.getValue().getItem().getPrice().multiply(new BigDecimal(count), mc));

				if (isExpectedCartItemsListEmpty) {
					addToExpectedCartItemsList(cart.getExpectedCartItemsList(), entry.getValue());
				}
			} else {
				if (isExpectedCartItemsListEmpty) {
					addToExpectedCartItemsList(cart.getExpectedCartItemsList(), entry.getValue());
				}
			}
		}

		return cart;
	}

	private void addToExpectedCartItemsList(
	    List<ExpectedCartItems> expectedCartItemsList,
	    ItemsAdded itemsAdded) {
		ExpectedCartItems expectedCartItems = new ExpectedCartItems();
		expectedCartItems.setItem(itemsAdded.getItem());
		expectedCartItems.setCount(itemsAdded.getCount());
		expectedCartItemsList.add(expectedCartItems);
	}

}
