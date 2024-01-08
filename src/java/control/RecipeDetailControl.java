package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RankingSelect;
import model.RecipeAccessCountInsert;
import model.RecipeDetailSelect;

public class RecipeDetailControl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try{
			/* レシピのアクセス数をDBにカウント */
			RecipeAccessCountInsert recipeAccessCountInsert  = new RecipeAccessCountInsert();
			recipeAccessCountInsert.execute(request);
			/* ランキング取得 */
			RankingSelect rankingSelect = new RankingSelect();
			rankingSelect.execute(request);
			/* レシピの詳細情報取得 */
			RecipeDetailSelect recipeDetailSelect = new RecipeDetailSelect();
			recipeDetailSelect.execute(request);
		}catch(Exception e) {
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/recipeDetail.jsp");
		rd.forward(request, response);
	}
}
