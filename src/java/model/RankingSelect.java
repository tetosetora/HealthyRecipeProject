package model;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

public class RankingSelect {

	public void execute(HttpServletRequest request) throws Exception {
		String category = request.getParameter("category");
		if(category!=null && category.equals("null")){
			category = null;
		}
		RecipeDao dao = null;
		try {
			dao = new RecipeDao();
			ArrayList<RankingDto> rankingData = dao.getRankingData(category);
			request.setAttribute("rankingData", rankingData);
		} catch (SQLException e) {
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
