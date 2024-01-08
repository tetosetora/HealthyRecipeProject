package model;

import java.util.List;

public class RecipeListDto {
	private int recipeId;
	private String recipeName;
	private int kcal;
	private int cost;
	private int time;
	private String recipeImage;
	private String createdAt;
	private List<String> category;

	// レシピIDのsetterとgetter
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	// レシピ名のsetterとgetter
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeName() {
		return recipeName;
	}

	// カロリーのsetterとgetter
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getKcal() {
		return kcal;
	}

	// 費用のsetterとgetter
	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	// 調理時間のsetterとgetter
	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	// レシピ画像のsetterとgetter
	public void setRecipeImage(String recipeImage) {
		this.recipeImage = recipeImage;
	}

	public String getRecipeImage() {
		return recipeImage;
	}

	// カテゴリーのsetterとgetter
	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}
	
	// 投稿日時のsetterとgetter
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

}

