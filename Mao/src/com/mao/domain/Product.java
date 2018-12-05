package com.mao.domain;

public class Product {
	private String id;
	private String image;
	private String title;
	private double money;
	private String productImg;
	private String[] imgs;
	private CategoryBean category = new CategoryBean();
	public Product() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String[] getImgs() {
		return productImg.split(";");
	}
	
	public CategoryBean getCategory() {
		return category;
	}
	public void setCategory(CategoryBean category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", image=" + image + ", title=" + title + ", money=" + money + ", productImg="
				+ productImg + "]";
	}
	
}
