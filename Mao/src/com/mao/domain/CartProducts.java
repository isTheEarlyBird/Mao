package com.mao.domain;

import java.util.Map;

public class CartProducts {
	private Map<Integer, CartItem> cartItem;
	private double total;
	public CartProducts() {
		super();
	}
	public Map<Integer, CartItem> getCartItem() {
		return cartItem;
	}
	public void setCartItem(Map<Integer, CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
