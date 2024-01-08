package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RankingSelect;

public class HomeControl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try{
			/* ランキング取得 */
			RankingSelect rankingSelect = new RankingSelect();
			rankingSelect.execute(request);
		}catch(Exception e) {
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/home.jsp");
		rd.forward(request, response);
	}

}
