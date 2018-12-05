package com.mao.domain;

public class CategoryBean {
	private String cid;
	private String cateName;
	public CategoryBean() {
		super();
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	@Override
	public String toString() {
		return "CategoryBean [cid=" + cid + ", cateName=" + cateName + "]";
	}
	
}
