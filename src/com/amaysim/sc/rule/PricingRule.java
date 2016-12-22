package com.amaysim.sc.rule;

import com.amaysim.sc.model.Cart;

public interface PricingRule {

	Cart applyRule(
	    Cart cart);

}
