package model;

import java.util.List;

public class RankingDto {
	private int recipeId;
	private String recipeName;
	private String recipeImage;
	private List<String> category;

	// コンストラクタ
	public RankingDto() {
		// デフォルトコンストラクタ
	}

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
}



