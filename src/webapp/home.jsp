<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>Home_page</title>
</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="/template/header.html" />
	
	<div class="home-img">
		<img alt="ヘルシーをシンプルに" src="./img/home/home.png">
	</div>

	<div class="home">

		<div class="content">

			<div class="home-block">
				<a href="/HealthyRecipeProject/category?category=1">
					<img alt="筋トレ" src="./img/home/homeTraining.png">
				</a>
			</div>

			<div class="home-block">
				<a href="/HealthyRecipeProject/category?category=2">
					<img alt="ダイエット" src="./img/home/homeDiet.png">
				</a>
			</div>

			<div class="home-block">
				<a href="/HealthyRecipeProject/category?category=3">
				    <img alt="健康" src="./img/home/homeHealthy.png">
				</a>
			</div>

			<div class="home-block">
				<a href="/HealthyRecipeProject/category">
					<img alt="すべてのレシピ" src="./img/home/subete.jpg">
				</a>
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