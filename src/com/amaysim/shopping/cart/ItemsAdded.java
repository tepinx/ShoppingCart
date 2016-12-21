package com.amaysim.shopping.cart;

import java.math.BigDecimal;

public class ItemsAdded {

	private Item item;
	private int count;
	private BigDecimal origPriceTotal = new BigDecimal(0);
	private BigDecimal discPriceTotal = new BigDecimal(0);

	public ItemsAdded() {
		super();
	}

	public ItemsAdded(Item item, int count, BigDecimal origPriceTotal, BigDecimal discPriceTotal) {
		super();
		this.item = item;
		this.count = count;
		this.origPriceTotal = origPriceTotal;
		this.discPriceTotal = discPriceTotal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(
	    Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(
	    int count) {
		this.count = count;
	}

	public BigDecimal getOrigPriceTotal() {
		return origPriceTotal;
	}

	public void setOrigPriceTotal(
	    BigDecimal origPriceTotal) {
		this.origPriceTotal = origPriceTotal;
	}

	public BigDecimal getDiscPriceTotal() {
		return discPriceTotal;
	}

	public void setDiscPriceTotal(
	    BigDecimal discPriceTotal) {
		this.discPriceTotal = discPriceTotal;
	}
}
