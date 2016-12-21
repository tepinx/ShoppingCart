package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.pojo.Cart;
import com.amaysim.sc.pojo.ExpectedCartItems;
import com.amaysim.sc.pojo.ItemsAdded;

public class BulkDiscountRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		String itemCode = "ult_large";
		BigDecimal dropPrice = new BigDecimal(39.90);
		MathContext mc = new MathContext(4);
		boolean isExpectedCartItemsListEmpty = cart.getExpectedCartItemsList().isEmpty();

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (entry.getValue().getItem().getCode().equals(itemCode) && entry.getValue().getCount() > 3) {
				entry.getValue().setDiscPriceTotal(new BigDecimal(entry.getValue().getCount()).multiply(dropPrice, mc));
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
