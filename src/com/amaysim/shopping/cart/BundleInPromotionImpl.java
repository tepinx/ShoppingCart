package com.amaysim.shopping.cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BundleInPromotionImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		String itemCode = "ult_medium";
		int freeItemCount = 0;

		boolean isExpectedCartItemsListEmpty = cart.getExpectedCartItemsList().isEmpty();

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (entry.getValue().getItem().getCode().equals(itemCode)) {
				freeItemCount = entry.getValue().getCount();
				if (isExpectedCartItemsListEmpty) {
					addToExpectedCartItemsList(cart.getExpectedCartItemsList(), entry.getValue());
				}
			} else {
				if (isExpectedCartItemsListEmpty) {
					addToExpectedCartItemsList(cart.getExpectedCartItemsList(), entry.getValue());
				}
			}
		}

		createFreeItem(cart.getExpectedCartItemsList(), freeItemCount);

		return cart;
	}

	private void createFreeItem(
	    List<ExpectedCartItems> expectedCartItemsList,
	    int freeItemCount) {

		if (freeItemCount > 0) {
			ItemsAdded freeItemsAdded = new ItemsAdded();
			freeItemsAdded.setCount(freeItemCount);
			Item freeItem = new Item();
			freeItem.setCode("1gb");
			freeItem.setName("1 GB Data-pack");
			freeItem.setPrice(new BigDecimal(9.90));
			freeItemsAdded.setItem(freeItem);
			addToExpectedCartItemsList(expectedCartItemsList, freeItemsAdded);
		}
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