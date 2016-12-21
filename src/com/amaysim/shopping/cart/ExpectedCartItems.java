package com.amaysim.shopping.cart;

public class ExpectedCartItems {

	private Item item;
	private Integer count;

	
	public ExpectedCartItems() {
		super();
	}

	public ExpectedCartItems(Item item, Integer count) {
		super();
		this.item = item;
		this.count = count;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(
	    Item item) {
		this.item = item;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(
	    Integer count) {
		this.count = count;
	}
}
