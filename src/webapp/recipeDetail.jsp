<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.RecipeDetailDto"%>
<%@ page import="model.RankingDto"%>
<%@ page import="model.Esc" %>
<%@ page import="java.util.ArrayList"%>
<%
	// このファイルでは,Esc.html()でエスケープ処理を行う。

	RecipeDetailDto detail = (RecipeDetailDto) request.getAttribute("recipeDetail");
	String category = (String) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/destyle.css@1.0.15/destyle.css" />
	<link href="./css/style.css" rel="stylesheet" />
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link
		href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&display=swap"
		rel="stylesheet">
	<title>recipedetail_page</title>
</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="/template/header.html" />

	<div class="main">

		<div class="detail-main">
		
			<p class="caution">
				このレシピは人工知能が作成したものです。<br>
				レシピに嘘が含まれる可能性がありますのでご注意ください。画像等もイメージとなります。
			</p>

			<div class="detail-top">

				<div class="detail-top-categoryCreatedAt">

					<div class="detail-top-categoryCreatedAt-category">
						<!-- カテゴリー -->
						<% for (String i : detail.getCategory()) { %>
							<p><%= Esc.html(i) %></p>
						<% } %>
					</div>

					<p>掲載：<%= Esc.html(detail.getCreatedAt().substring(0,11)) %></p>
				</div>

				<div class="detail-top-nameTag">
					<!-- レシピ名 -->
					<h1 class="detail-top-nameTag-name"><%= Esc.html(detail.getRecipeName()) %></h1>

					<div class="detail-top-nameTag-tag">
						<!-- タグ -->
						<% for (String i : detail.getTags()) { %>
							<a href="/HealthyRecipeProject/category?<% if(category!=null){ %>category=<%= Esc.html(category) %>&<% } %>
									tag=<%= Esc.html(i) %>"><span>#<%= Esc.html(i) %></span></a>
						<% } %>
					</div>
				</div>

				<div class="detail-top-img">
					<!-- 画像 -->
					<img src="./img/recipe/<%= Esc.html(detail.getRecipeImage()) %>">
				</div>

				<div class="detail-top-comment">
					<!-- コメント -->
					<p><%= Esc.html(detail.getComment()) %></p>
				</div>

			</div>

			<div class="detail-middle">

				<h2 class="detail-middle-nabeIcon">概要</h2>
				<ul class="detail-middle-ul">
					<li>カロリー　　<%= detail.getKcal() %>kcal</li>
					<li>調理時間　　<%= detail.getCost() %>分</li>
					<li>費用目安　　<%= detail.getTime() %>円</li>
					<li class="detail-middle-nabeIcon-nutrientsTitle">栄養素</li>
				</ul>
				<p class="detail-middle-nabeIcon-nutrients"><%= Esc.html(detail.getNutrients()) %></p>

				<h2 class="detail-middle-nabeIcon">
					材料（<%= detail.getPortion() %>人分）
				</h2>
				<ul class="detail-middle-ul">
					<% for (String i : detail.getIngredients()) { %>
						<li><%= Esc.html(i) %></li>
					<% } %>
				</ul>

				<h2 class="detail-middle-nabeIcon">作り方</h2>
				<ol class="detail-middle-ol">
					<% for (String i : detail.getSteps()) { %>
						<li><%= Esc.html(i) %></li>
					<% } %>
				</ol>

				<h2 class="detail-middle-bikkuriIcon">ポイント</h2>
				<ul>
					<% for (String i : detail.getPoint()) { %>
						<li><%= Esc.html(i) %></li>
					<% } %>
				</ul>

				<h2>  ◎ レシピ引用元</h2>
				<div class="detail-middle-link">
					<p>
						このレシピはopenAI社が開発したchatGPTを使用して作成されています。<br>
						作成したいレシピがある場合は、レシピ作成GPTs
						「<a href="https://chat.openai.com/g/g-IxkPVq7zx-resipikurieita" class="detail-middle-URL">レシピクリエイター</a>」をお試しください。
					</p>
				</div>
			</div>

		</div>

		<div class="sideNav">
			<!-- サイドバー -->
			<jsp:include page="/template/sidebar.jsp" />
		</div>

	</div>


	<!-- フッター -->
	<jsp:include page="/template/footer.html" />

</body>
</html>