package com.amaysim.sc.rule;

import java.math.BigDecimal;
import java.util.Map;

import com.amaysim.sc.model.Cart;
import com.amaysim.sc.model.ItemsAdded;

public class AddPromoCodeRuleImpl implements PricingRule {

	@Override
	public Cart applyRule(
	    Cart cart) {

		// promo code and discount value should be get from the database, but for now hard coded :)
		String promoCode = "I<3AMAYSIM";
		// equivalent to 10% discount
		float discount = 0.1f;

		for (Map.Entry<String, ItemsAdded> entry : cart.getItemsAddedMap().entrySet()) {
			if (cart.getPromoCode() != null && cart.getPromoCode().equals(promoCode)) {
				// check if discounted price total is not zero
				if (entry.getValue().getDiscPriceTotal() != null && entry.getValue().getDiscPriceTotal().intValue() > 0) {
					// get the 10% the of discounted price total
					BigDecimal computedDiscountPrice = entry.getValue().getDiscPriceTotal().multiply(new BigDecimal(discount));
					// subtract the computed discounted price to discounted Price Total
					entry.getValue().setDiscPriceTotal(entry.getValue().getDiscPriceTotal().subtract(computedDiscountPrice));
				} else {
					// get the 10% the of original price total
					BigDecimal computedDiscountPrice = entry.getValue().getOrigPriceTotal().multiply(new BigDecimal(discount));
					// subtract the computed discounted price to original Price Total
					entry.getValue().setDiscPriceTotal(entry.getValue().getOrigPriceTotal().subtract(computedDiscountPrice));
				}
			} 
		}
		return cart;
	}
}
