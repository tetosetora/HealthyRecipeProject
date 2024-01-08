package model;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	// daoファイルの実行テスト用クラスです。

	public static void main(String[] args) {
		String category = "1"; // １～３の数字か、nullが入る
		String sort = "time"; // created_at,time,cost,kcal,nullが入る
		int page = 1;
		String searchParam = "ケーキ";
		int recipeId = 22;

		RecipeDao dao = null;
		try {
			dao = new RecipeDao();

			System.out.println("ランキングを取得ｰ--------------------------------------------------------");
			ArrayList<RankingDto> rankingData = dao.getRankingData(category);
			for(RankingDto i:rankingData) {
				System.out.println(i.getRecipeId());
				System.out.println(i.getRecipeName());
				System.out.println(i.getRecipeImage());
				System.out.println(i.getCategory());
			}


			System.out.println("アクセス数を取得---------------------------------------------------------");
			int count = dao.getRecipeCounts(category,searchParam);
			System.out.println(count);


			System.out.println("レシピ一覧を取得--------------------------------------------------------------");
			ArrayList<RecipeListDto> recipeList = dao.getRecipeList(category,searchParam,sort,page);
			for(RecipeListDto i:recipeList) {
				System.out.println(i.getRecipeId());
				System.out.println(i.getRecipeName());
				System.out.println(i.getRecipeImage());
				System.out.println(i.getCost());
				System.out.println(i.getCreatedAt());
				System.out.println(i.getTime());
				System.out.println(i.getKcal());
				List<String> cate = i.getCategory();
				for(String c:cate) {
					System.out.println(c);
				}
			}


			System.out.println("タグを取得----------------------------------------------------------");
			ArrayList<String> tagList = dao.getAllTagData(category);
			for(String i:tagList) {
				System.out.println(i);
			}


			System.out.println("レシピ詳細を取得ｰ--------------------------------------------------------------");
			RecipeDetailDto recipeDetail = dao.getRecipeDetail(recipeId);
				System.out.println(recipeDetail.getRecipeName());
				System.out.println(recipeDetail.getComment());
				System.out.println(recipeDetail.getCost());
				System.out.println(recipeDetail.getCreatedAt());
				System.out.println(recipeDetail.getKcal());
				System.out.println(recipeDetail.getNutrients());
				System.out.println(recipeDetail.getTime());
				System.out.println(recipeDetail.getPortion());
				System.out.println(recipeDetail.getRecipeImage());

				List<String> point = recipeDetail.getPoint();
				for(String i:point) {
					System.out.println(i);
				}

				List<String> steps = recipeDetail.getSteps();
				for(String i:steps) {
					System.out.println(i);
				}

				List<String> ingred = recipeDetail.getIngredients();
				for(String i:ingred) {
					System.out.println(i);
				}

				List<String> cate = recipeDetail.getCategory();
				for(String i:cate) {
					System.out.println(i);
				}

				List<String> tags = recipeDetail.getTags();
				for(String i:tags) {
					System.out.println(i);
				}


				dao.setAccessCount(recipeId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}