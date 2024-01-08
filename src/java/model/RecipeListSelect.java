package model;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

public class RecipeListSelect {

	public void execute(HttpServletRequest request) throws Exception {
		int page = 1;
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		String category = request.getParameter("category");
		if(category!=null && category.equals("null")) {
			category = null;
		}
		String sort = request.getParameter("sort");
		/* タグとキーワード、ある方をsearchParamに代入 */
		String searchParam = request.getParameter("searchParam");
		if(searchParam==null && request.getParameter("keyword")!=null){
			searchParam = request.getParameter("keyword");
		}else if(searchParam==null && request.getParameter("tag")!=null){
			searchParam = request.getParameter("tag");
		}
		RecipeDao dao = null;
		try {
			dao = new RecipeDao();
			request.setAttribute("category", category);
			request.setAttribute("searchParam", searchParam);
			request.setAttribute("sort", sort);
			/* レシピ件数取得 */
			int count = dao.getRecipeCounts(category,searchParam);
			request.setAttribute("count", count);
			/* ページネーション作成 */
			String pages = Utilities.editPages(category,searchParam,count,sort);
			request.setAttribute("pages", pages);
			/* ランキング取得 */
			ArrayList<RankingDto> rankingData = dao.getRankingData(category);
			request.setAttribute("rankingData", rankingData);
			/* レシピリスト取得 */
			ArrayList<RecipeListDto> recipeList = dao.getRecipeList(category,searchParam,sort,page);
			request.setAttribute("recipeList", recipeList);
			/* タグ一覧取得 */
			ArrayList<String> tagList = dao.getAllTagData(category);
			request.setAttribute("tagList", tagList);
		} catch (SQLException e) {
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

}
