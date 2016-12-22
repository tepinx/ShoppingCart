package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import com.amaysim.sc.model.Cart;
import com.amaysim.sc.model.ExpectedCartItems;
import com.amaysim.sc.model.ItemsAdded;

public class AddPromoCodeRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		String promoCode = "I<3AMAYSIM";
		float discount = 0.1f;
		MathContext mc = new MathContext(4);
		boolean isExpectedCartItemsListEmpty = cart.getExpectedCartItemsList().isEmpty();

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (cart.getPromoCode() != null && cart.getPromoCode().equals(promoCode)) {
				if (entry.getValue().getDiscPriceTotal() != null && entry.getValue().getDiscPriceTotal().intValue() > 0) {
					BigDecimal discPrice = entry.getValue().getDiscPriceTotal().multiply(new BigDecimal(discount), mc);
					entry.getValue().setDiscPriceTotal(entry.getValue().getDiscPriceTotal().subtract(discPrice, mc));
				} else {
					BigDecimal discPrice = entry.getValue().getOrigPriceTotal().multiply(new BigDecimal(discount), mc);
					entry.getValue().setDiscPriceTotal(entry.getValue().getOrigPriceTotal().subtract(discPrice, mc));
				}
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
