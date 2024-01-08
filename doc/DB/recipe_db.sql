#古いデータベース領域の削除
DROP DATABASE IF EXISTS recipe_db;

#データベース領域の作成
CREATE DATABASE recipe_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

#データベース領域の指定
USE recipe_db;

#テーブルの作成

-- レシピテーブルの作成
CREATE TABLE recipe_tbl(
	recipe_id INT AUTO_INCREMENT PRIMARY KEY,
	recipe_name VARCHAR(20),
	portion INT,
	kcal INT,
	cost INT,
	time INT,
	nutrients VARCHAR(250),
	access_count INT DEFAULT 0,
	comment VARCHAR(300),
	recipe_image VARCHAR(100),
	created_at TIMESTAMP DEFAULT '2023-01-01 00:00:00'
) DEFAULT CHARSET=utf8;

-- 材料テーブルの作成
CREATE TABLE ingredient_tbl(
	recipe_id INT,
	FOREIGN KEY (recipe_id) REFERENCES recipe_tbl(recipe_id),
	ing_name VARCHAR(100)
) DEFAULT CHARSET=utf8;

-- 作り方テーブルの作成
CREATE TABLE step_tbl(
	recipe_id INT,
	FOREIGN KEY (recipe_id) REFERENCES recipe_tbl(recipe_id),
	step_content VARCHAR(300)
) DEFAULT CHARSET=utf8;

-- ポイントテーブルの作成
CREATE TABLE point_tbl(
	recipe_id INT,
	FOREIGN KEY (recipe_id) REFERENCES recipe_tbl(recipe_id),
	point_content VARCHAR(200)
) DEFAULT CHARSET=utf8;

-- カテゴリーテーブルの作成
CREATE TABLE category_tbl(
	category_id INT,
	category_name VARCHAR(10),
	recipe_id INT,
	FOREIGN KEY (recipe_id) REFERENCES recipe_tbl(recipe_id)
) DEFAULT CHARSET=utf8;

-- タグテーブルの作成
CREATE TABLE tag_tbl(
	recipe_id INT,
	FOREIGN KEY (recipe_id) REFERENCES recipe_tbl(recipe_id),
	tag_name VARCHAR(50)
) DEFAULT CHARSET=utf8;

#テーブルへのデータインポート recipe_tblレコードの追加
LOAD DATA INFILE 'C:\\recipe_db\\recipe_tbl.csv'
	INTO TABLE recipe_tbl
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
;

#テーブルへのデータインポート ingredient_tblレコードの追加
LOAD DATA INFILE 'C:\\recipe_db\\ingredient_tbl.csv'
	INTO TABLE ingredient_tbl
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
;


#テーブルへのデータインポート step_tblレコードの追加
LOAD DATA INFILE 'C:\\recipe_db\\step_tbl.csv'
	INTO TABLE step_tbl
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
;

#テーブルへのデータインポート point_tblレコードの追加
LOAD DATA INFILE 'C:\\recipe_db\\point_tbl.csv'
	INTO TABLE point_tbl
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
;

#テーブルへのデータインポート category_tblレコードの追加
LOAD DATA INFILE 'C:\\recipe_db\\category_tbl.csv'
	INTO TABLE category_tbl
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
;

#テーブルへのデータインポート tag_tblレコードの追加
LOAD DATA INFILE 'C:\\recipe_db\\tag_tbl.csv'
	INTO TABLE tag_tbl
	FIELDS TERMINATED BY ','
	LINES TERMINATED BY '\r\n'
;
