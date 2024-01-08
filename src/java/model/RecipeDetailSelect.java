package model;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

public class RecipeDetailSelect {

	public void execute(HttpServletRequest request) throws Exception {
		int recipeId = Integer.parseInt(request.getParameter("recipeId"));
		String category = request.getParameter("category");
		RecipeDao dao = null;
		try {
			dao = new RecipeDao();
			request.setAttribute("category", category);
			RecipeDetailDto recipeDetail = dao.getRecipeDetail(recipeId);
			request.setAttribute("recipeDetail", recipeDetail);
		} catch (SQLException e) {
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

}
