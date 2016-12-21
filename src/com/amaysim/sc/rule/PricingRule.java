package com.amaysim.sc.rule;

import com.amaysim.sc.pojo.Cart;

public interface PricingRule {

	Cart applyRule(
	    Cart cart);

}
