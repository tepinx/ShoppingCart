package com.amaysim.sc.model;

public class ExpectedCartItems {

	private Item item;
	private int count;

	
	public ExpectedCartItems() {
		super();
	}

	public ExpectedCartItems(Item item, int count) {
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

	public int getCount() {
		return count;
	}

	public void setCount(
	    int count) {
		this.count = count;
	}
}
