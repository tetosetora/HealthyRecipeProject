package model;

import jakarta.servlet.http.HttpServletRequest;

public class RecipeAccessCountInsert {

	public void execute(HttpServletRequest request) throws Exception {
		RecipeDao dao = null;
		try {
			int recipeId = Integer.parseInt(request.getParameter("recipeId"));
			dao = new RecipeDao();
			dao.setAccessCount(recipeId);
		} catch (Exception e) {
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
