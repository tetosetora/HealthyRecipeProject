<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.RankingDto"%>
<%@ page import="model.Esc" %>
<%@ page import="java.util.ArrayList"%>
<%
	// このファイルでは,Esc.html()でエスケープ処理を行う。

	ArrayList<RankingDto> rankingData = (ArrayList<RankingDto>) request.getAttribute("rankingData");
	String category = (String) request.getAttribute("category");

	String categoryName = null;
	if(category==null){
		categoryName = "総合アクセス";
	}else if(category.equals("1")){
		categoryName = "筋トレレシピ";
	}else if(category.equals("2")){
		categoryName = "ダイエットレシピ";
	}else if(category.equals("3")){
		categoryName = "健康レシピ";
	}
%>
		<div class="sidebar">
			<!-- ▼アフィリエイトリンク -->
			<!-- <div class="back-number-box"> -->

			<!-- </div> -->
			<!-- ▲アフィリエイトリンク -->

			<!-- 区切り線 -->
			<div class="Line"></div>

			<!-- ▼総合ランキング -->
			<div class="ranking-section">
				<p>
					<%= categoryName %>TOP10
				</p>
				<% for (int i = 0; i < 10; i++) {%>
					<div class="rank-content">
						<!-- レシピ画像 -->
						<div class="rank-recipe-box">
							<img class="rank-recipe-img" src="./img/recipe/<%= Esc.html(rankingData.get(i).getRecipeImage()) %>"/>
						</div>
						<!-- ランキング順位画像 -->
						<img class="rank-img" src="./img/rank/ranking_<%= i %>.png" />
						<!-- カテゴリーラベル -->
						<div class="main-category-label">
							<% for(int c = 0; c < rankingData.get(i).getCategory().size(); c++){ %>
								<p><%= Esc.html(rankingData.get(i).getCategory().get(c)) %></p>
							<% } %>
						</div>
						<!-- レシピ名とリンク -->
						<a class="recipe-nameLink" href="/HealthyRecipeProject/recipe?
								<% if(category!=null){ %>category=<%= Esc.html(category) %>&<% } %>recipeId=<%= rankingData.get(i).getRecipeId() %>" target="_blank">
							<div class="recipe-name-area">
									<p><%= Esc.html(rankingData.get(i).getRecipeName()) %></p>
							</div>
						</a>
					</div>
				<%}%>
			</div>
			<!-- ▲総合ランキング -->
		</div>
