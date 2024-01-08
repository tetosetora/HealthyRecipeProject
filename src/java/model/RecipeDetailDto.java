package model;

import java.util.List;

public class RecipeDetailDto {
	private String recipeName;
	private int kcal;
	private int cost;
	int time;
	private String nutrients;
	private String comment;
	private int portion;
	private String recipeImage;
	private String createdAt;
	private List<String> point;
	private List<String> ingredients;
	private List<String> steps;
	private List<String> category;
	private List<String> tags;

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

	// 栄養素のsetterとgetter
	public void setNutrients(String nutrients) {
		this.nutrients = nutrients;
	}

	public String getNutrients() {
		return nutrients;
	}

	// コメントのsetterとgetter
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	// ポイントのsetterとgetter
	public void setPortion(int portion) {
		this.portion = portion;
	}

	public int getPortion() {
		return portion;
	}

	// レシピ画像のsetterとgetter
	public void setRecipeImage(String recipeImage) {
		this.recipeImage = recipeImage;
	}

	public String getRecipeImage() {
		return recipeImage;
	}

	// 投稿日時のsetterとgetter
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	// Listのsetterとgetter
	public List<String> getPoint() {
		return point;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public List<String> getSteps() {
		return steps;
	}

	public List<String> getCategory() {
		return category;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setPoint(List<String> point) {
		this.point = point;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}



