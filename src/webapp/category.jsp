<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.RecipeListDto"%>
<%@ page import="model.Esc" %>
<%@ page import="java.util.ArrayList"%>
<%
	// このファイルでは,Esc.html()でエスケープ処理を行う。
	
	ArrayList<RecipeListDto> recipeList = (ArrayList<RecipeListDto>) request.getAttribute("recipeList");
	ArrayList<String> tagList = (ArrayList<String>) request.getAttribute("tagList");
	String unCategory = (String) request.getAttribute("category");
	String category = Esc.html(unCategory);
	String sort = (String) request.getAttribute("sort");
	String searchParam = null;
	if(request.getAttribute("searchParam")!=null){
		searchParam = (String) request.getAttribute("searchParam");
	}else{
		searchParam = "すべて";
	}
	if(searchParam.equals("")){
		searchParam = "すべて";
	}
	int count = (Integer) request.getAttribute("count");
	String pages = (String) request.getAttribute("pages");

	// カテゴリー文字、画像、コメントの設定
	String categoryImg = null;
	String categoryComment = null;
	String categoryName = null;
	if(category==null){
		categoryImg = "subete";
		categoryComment = "バランスの取れた食事で健康を手に入れましょう";
		categoryName = "すべての";
	}else if(category.equals("1")){
		categoryImg = "training";
		categoryComment = "生活習慣を見直す一環として、トレーニング前後の食事から取り入れてみよう";
		categoryName = "筋トレ";
	}else if(category.equals("2")){
		categoryImg = "diet";
		categoryComment = "ダイエットは健康的な食事からはじめましょう";
		categoryName = "ダイエット";
	}else if(category.equals("3")){
		categoryImg = "healthy";
		categoryComment = "食事は自然の薬です。質の高い食生活を目指しましょう";
		categoryName = "健康";
	}

	// 現在のソート表示
	String newSort = null;
	if(sort==null || sort.equals("created_at")){
		newSort = "新着";
	}else if(sort.equals("time")){
		newSort = "調理時間";
	}else if(sort.equals("cost")){
		newSort = "費用";
	}else if(sort.equals("kcal")){
		newSort = "カロリー";
	}else{
		newSort = "新着";
	}
	
	// ソートプルダウン表示用
	String[] sortList = {"created_at\">新着", "time\">調理時間", "cost\">費用", "kcal\">カロリー"};

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/destyle.css@1.0.15/destyle.css" />
	<link href="./css/style.css" rel="stylesheet" />
	<link href="./css/header.css" rel="stylesheet" type="text/css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&display=swap" rel="stylesheet">
	<title>Category_page</title>
</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="/template/header.html"/>

	<div class="main">
	
		<div class="category">
			<!-- カテゴリータイトルエリア -->
			<div class="category-title">
				<img src="./img/banner/<%= categoryImg %>.png" />
				<p class="cat-feature">category</p>
				<p class="cat-name"><%= categoryName %>レシピ</p>
				<div class="category-comment">
					<p><%= categoryComment %></p>
				</div>
			</div>
			
			<p class="caution">
				このレシピは人工知能が作成したものです。<br>
				レシピに嘘が含まれる可能性がありますのでご注意ください。画像等もイメージとなります。
			</p>

			<!-- 検索フォーム&タグエリア -->
			<div class="search-content">
				<!-- 検索フォーム -->
				<form action="category" method="get">
					<div class="input-keyword">
						<label for="keyword">キーワード検索</label>
						<div><!-- レイアウト用div -->
							<input class="categoryInputBox" type="text" name="keyword" placeholder="キーワードを入力">
							<input type="hidden" name="category" value="<%= category %>">
							<button class="my-parts" type="submit"><span></span></button>
						</div>
					</div>
				</form>
				<!-- タグ -->
				<div class="tag-list">
					<section class="tag">
						<ul>
							<% for (String t : tagList) {%>
								<li><a href="/HealthyRecipeProject/category?<% if(category!=null){ %>category=<%= category %>&<% } %>
										tag=<%= Esc.html(t) %>"><span>#<%= Esc.html(t) %></span></a></li>
							<%}%>
						</ul>
					</section>
				</div>
				<div>
					<a href="#" class="tag-accordion-button"> タグ一覧</a>
				</div>
			</div>

			<!-- 検索結果&ソートエリア -->
			<div class="result-container">
				<div class="sort-area">
					<p>「<%= Esc.html(searchParam) %>」の検索結果 <%=count%>件</p>
					<!-- ソートメニュー -->
					<div class="dropdown">
						<button class="dropbtn">▼<%= newSort %>順</button>
						<div class="dropdown-content">
							<% for(int i=0;i<4;i++){ %>
								<a href="/HealthyRecipeProject/category?
									<% if(category!=null){ %>category=<%= category %>&<% } %>
									<% if(!searchParam.equals("すべて")){ %>searchParam=<%= Esc.html(searchParam) %>&<% } %>sort=
									<%= sortList[i] %>
								</a>
							<% } %>
						</div>
					</div>
				</div>
			</div>

			<!-- レシピ一覧エリア -->
			<div class="recipe-card-container">
				<% for(int i = 0; i < recipeList.size(); i++) {%>
					<div class="recipe-card">
						<!-- ここにレシピページへのリンク -->
						<a class="recipe-link" href="/HealthyRecipeProject/recipe?<% if(category!=null){ %>category=<%= category %>&<% } %>recipeId=<%= recipeList.get(i).getRecipeId() %>">
							<div class="recipe-img-box">
								<img class="recipe-img" src="./img/recipe/<%= Esc.html(recipeList.get(i).getRecipeImage()) %>" />
							</div>
							<div class="label-area">
								<div class="main-category-label">
								<% for(String c : recipeList.get(i).getCategory()){ %>
									<p><%= Esc.html(c) %></p>
								<% } %>
								</div>
							</div>
							<div class="recipe-info">
								<p class="name-and-cal"><%= Esc.html(recipeList.get(i).getRecipeName()) %>｜<%= recipeList.get(i).getKcal() %>kcal</p>
								<p class="time-and-cost">time:<%= recipeList.get(i).getTime() %>分　cost:<%= recipeList.get(i).getCost() %>円</p>
								<p class="post-time"><%= Esc.html(recipeList.get(i).getCreatedAt().substring(0,11)) %></p>
							</div>
						</a>
					</div>
				<%}%>
			</div>

			<!-- ページネーションエリア -->
			<%= pages %>

		</div>
		
		<div class="sideNav">
			<!-- サイドバー -->
			<jsp:include page="/template/sidebar.jsp"/>
		</div>
		
	</div>
	
	<!-- フッター -->
	<jsp:include page="/template/footer.html"/>
	
	<script>
		// タグ一覧のアコーディオン
		document.querySelector('.tag-accordion-button').addEventListener('click',function(event) {
			event.preventDefault(); // リンクが動かないようにする
		    var tagTarget = document.querySelector('.tag-list');
		    tagTarget.classList.toggle('openClause');
		    var targetButton = document.querySelector('.tag-accordion-button');
		    targetButton.classList.toggle('openButton');
		});
	</script>
</body>
</html>