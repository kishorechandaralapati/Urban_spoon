package com.talentsprint.us.model;

import java.util.Arrays;
import java.util.List;

public class Serve {

	private double price;
	private List<Integer>branchID;
	private int userId;
	private int recipeId;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Integer> getBranchID() {
		return branchID;
	}

	public void setBranchID(List<Integer> branchID) {
		this.branchID = branchID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	@Override
	public String toString() {
		return "Serve [price=" + price + ", branchID=" + branchID + ", userId="
				+ userId + ", recipeId=" + recipeId + "]";
	}
}
