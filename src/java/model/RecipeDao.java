package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDao {
	private Connection connection;

	public RecipeDao() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	connection = DriverManager
			// DBに接続します。
}

	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ランキングを取得するメソッド
	public ArrayList<RankingDto> getRankingData(String category) throws SQLException {
		ArrayList<RankingDto> rankingList = new ArrayList<RankingDto>();
		PreparedStatement pstatement = null;
		try  {
			String query = "SELECT * FROM recipe_tbl";
			if (category == null) {
				query += " ORDER BY access_count DESC LIMIT 10";
				pstatement = connection.prepareStatement(query);
			} else {
				query += " LEFT JOIN category_tbl ON recipe_tbl.recipe_id = category_tbl.recipe_id WHERE category_id = ? ORDER BY access_count DESC LIMIT 10";
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, Integer.parseInt(category));
			}
			ResultSet rs = pstatement.executeQuery();
			while (rs.next()) {
				RankingDto dto = new RankingDto();
				dto.setRecipeId(rs.getInt("recipe_id"));
				dto.setRecipeName(rs.getString("recipe_name"));
				dto.setRecipeImage(rs.getString("recipe_image"));
				dto.setCategory(getListData(rs.getInt("recipe_id"), "category_name"));
				rankingList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rankingList;
	}

	// レシピ一覧を取得するメソッド
	public ArrayList<RecipeListDto> getRecipeList(String category, String searchParam, String sort,int page) throws SQLException {		
		ArrayList<RecipeListDto> recipeList = new ArrayList<RecipeListDto>();
		PreparedStatement pstatement = null;

		String query = "SELECT DISTINCT  recipe_tbl.recipe_id, recipe_name, portion, kcal, cost, time, nutrients, access_count, comment, recipe_image, created_at"
				+ " FROM recipe_tbl"
				+ " LEFT JOIN ingredient_tbl ON recipe_tbl.recipe_id = ingredient_tbl.recipe_id"
				+ " LEFT JOIN tag_tbl ON recipe_tbl.recipe_id = tag_tbl.recipe_id";

		if(category != null && searchParam != null){
			query += " LEFT JOIN category_tbl ON recipe_tbl.recipe_id = category_tbl.recipe_id"
					+ " WHERE category_id = ?"
					+ " AND (recipe_name LIKE ?"
					+ " OR comment LIKE ?"
					+ " OR ing_name LIKE ?"
					+ " OR tag_name LIKE ?)";
		}else if(category != null) {
			query += " LEFT JOIN category_tbl ON recipe_tbl.recipe_id = category_tbl.recipe_id"
					+ " WHERE category_id = ?";
		}else if(searchParam != null) {
			query += " WHERE recipe_name LIKE ?"
					+ " OR comment LIKE ?"
					+ " OR ing_name LIKE ?"
					+ " OR tag_name LIKE ?";
		}

		if(sort==null || sort.equals("created_at")){
			query += " ORDER BY created_at DESC LIMIT 12 OFFSET ?";
		}else if(sort.equals("time")){
			query += " ORDER BY time LIMIT 12 OFFSET ?";
		}else if(sort.equals("cost")){
			query += " ORDER BY cost LIMIT 12 OFFSET ?";
		}else if(sort.equals("kcal")){
			query += " ORDER BY kcal LIMIT 12 OFFSET ?";
		}else {
			query += " ORDER BY created_at DESC LIMIT 12 OFFSET ?";
		}

		try {
			if(category != null && searchParam != null){
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, Integer.parseInt(category));
				pstatement.setString(2, "%" + searchParam + "%");
				pstatement.setString(3, "%" + searchParam + "%");
				pstatement.setString(4, "%" + searchParam + "%");
				pstatement.setString(5, "%" + searchParam + "%");
				pstatement.setInt(6, (12 * ((page) - 1)));
			}else if(category != null){
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, Integer.parseInt(category));
				pstatement.setInt(2, (12 * ((page) - 1)));
			}else if(searchParam != null){
				pstatement = connection.prepareStatement(query);
				pstatement.setString(1, "%" + searchParam + "%");
				pstatement.setString(2, "%" + searchParam + "%");
				pstatement.setString(3, "%" + searchParam + "%");
				pstatement.setString(4, "%" + searchParam + "%");
				pstatement.setInt(5, (12 * ((page) - 1)));
			}else{
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, (12 * ((page) - 1)));
			}
			ResultSet rs = pstatement.executeQuery();
			while (rs.next()) {
				RecipeListDto dto = new RecipeListDto();
				dto.setRecipeId(rs.getInt("recipe_id"));
				dto.setRecipeName(rs.getString("recipe_name"));
				dto.setKcal(rs.getInt("kcal"));
				dto.setCost(rs.getInt("cost"));
				dto.setTime(rs.getInt("time"));
				dto.setRecipeImage(rs.getString("recipe_image"));
				dto.setCategory(getListData(rs.getInt("recipe_id"), "category_name"));
				dto.setCreatedAt(rs.getString("created_at"));
				recipeList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}

	// レシピ数を取得するメソッド
	public int getRecipeCounts(String category, String searchParam) throws SQLException {
		int recipeCounts = 0;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try{
			String query = "SELECT COUNT(DISTINCT recipe_tbl.recipe_id)"
					+ " FROM recipe_tbl"
					+ " LEFT JOIN ingredient_tbl ON recipe_tbl.recipe_id = ingredient_tbl.recipe_id"
					+ " LEFT JOIN category_tbl ON recipe_tbl.recipe_id = category_tbl.recipe_id"
					+ " LEFT JOIN tag_tbl ON recipe_tbl.recipe_id = tag_tbl.recipe_id";
			if(category != null && searchParam != null){
				query += " WHERE category_id = ?"
						+ " AND (recipe_name LIKE ?"
						+ " OR comment LIKE ?"
						+ " OR ing_name LIKE ?"
						+ " OR tag_name LIKE ?)";
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, Integer.parseInt(category));
				pstatement.setString(2, "%" + searchParam + "%");
				pstatement.setString(3, "%" + searchParam + "%");
				pstatement.setString(4, "%" + searchParam + "%");
				pstatement.setString(5, "%" + searchParam + "%");
			}else if(category != null){
				query += " WHERE category_id = ?";
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, Integer.parseInt(category));
			}else if(searchParam != null){
				query += " WHERE recipe_name LIKE ?"
						+ " OR comment LIKE ?"
						+ " OR ing_name LIKE ?"
						+ " OR tag_name LIKE ?";
				pstatement = connection.prepareStatement(query);
				pstatement.setString(1, "%" + searchParam + "%");
				pstatement.setString(2, "%" + searchParam + "%");
				pstatement.setString(3, "%" + searchParam + "%");
				pstatement.setString(4, "%" + searchParam + "%");
			}else{
				pstatement = connection.prepareStatement(query);
			}
			rs = pstatement.executeQuery();
			while(rs.next()){
				recipeCounts = rs.getInt(1);
			}
			rs.close();
		} finally {
			pstatement.close();
		}
		return recipeCounts;
	}

	// タグ一覧を取得するメソッド
	public ArrayList<String> getAllTagData(String category) throws SQLException {
		ArrayList<String> tagList = new ArrayList<String>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT tag_name FROM tag_tbl";
		try{
			if(category==null) {
				pstatement = connection.prepareStatement(query);
			}else{
				query += " LEFT JOIN category_tbl ON tag_tbl.recipe_id = category_tbl.recipe_id WHERE category_id = ?";
				pstatement = connection.prepareStatement(query);
				pstatement.setInt(1, Integer.parseInt(category));
			}
			rs = pstatement.executeQuery();
			while(rs.next()){
				tagList.add(rs.getString("tag_name"));
			}
			rs.close();
		} finally {
			pstatement.close();
		}
		return tagList;
	}

	// レシピ詳細を取得するメソッド
	public RecipeDetailDto getRecipeDetail(int recipeId) throws SQLException {
		RecipeDetailDto dto = new RecipeDetailDto();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try{
			pstatement = connection.prepareStatement("SELECT * FROM recipe_tbl WHERE recipe_id=?");
			pstatement.setInt(1, recipeId);
			rs = pstatement.executeQuery();
			if(rs.next()){
				dto.setRecipeName(rs.getString("recipe_name"));
				dto.setKcal(rs.getInt("kcal"));
				dto.setCost(rs.getInt("cost"));
				dto.setTime(rs.getInt("time"));
				dto.setNutrients(rs.getString("nutrients"));
				dto.setComment(rs.getString("comment"));
				dto.setPortion(rs.getInt("portion"));
				dto.setRecipeImage(rs.getString("recipe_image"));
				dto.setCreatedAt(rs.getString("created_at"));
			}
			dto.setPoint(getListData(recipeId, "point_content"));
			dto.setIngredients(getListData(recipeId, "ing_name"));
			dto.setSteps(getListData(recipeId, "step_content"));
			dto.setCategory(getListData(recipeId, "category_name"));
			dto.setTags(getListData(recipeId, "tag_name"));
			rs.close();
		} finally {
			pstatement.close();
		}
		return dto;
	}


	// レシピ詳細におけるリスト形式の情報を取得するメソッド
	public ArrayList<String> getListData(int recipeId, String genre) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try{
			if(genre.equals("point_content")) {
				pstatement = connection.prepareStatement("SELECT point_content FROM recipe_tbl LEFT JOIN point_tbl ON recipe_tbl.recipe_id = point_tbl.recipe_id WHERE recipe_tbl.recipe_id=?");
			}else if(genre.equals("ing_name")) {
				pstatement = connection.prepareStatement("SELECT ing_name FROM recipe_tbl LEFT JOIN ingredient_tbl ON recipe_tbl.recipe_id = ingredient_tbl.recipe_id WHERE recipe_tbl.recipe_id=?");
			}else if(genre.equals("step_content")) {
				pstatement = connection.prepareStatement("SELECT step_content FROM recipe_tbl LEFT JOIN step_tbl ON recipe_tbl.recipe_id = step_tbl.recipe_id WHERE recipe_tbl.recipe_id=?");
			}else if(genre.equals("category_name")) {
				pstatement = connection.prepareStatement("SELECT category_name FROM recipe_tbl LEFT JOIN category_tbl ON recipe_tbl.recipe_id = category_tbl.recipe_id WHERE recipe_tbl.recipe_id=?");
			}else if(genre.equals("tag_name")) {
				pstatement = connection.prepareStatement("SELECT tag_name FROM recipe_tbl LEFT JOIN tag_tbl ON recipe_tbl.recipe_id = tag_tbl.recipe_id WHERE recipe_tbl.recipe_id=?");
			}
			pstatement.setInt(1, recipeId);
			rs = pstatement.executeQuery();
			while(rs.next()){
				list.add(rs.getString(genre));
			}
			rs.close();
		} finally {
			pstatement.close();
		}
		return list;
	}

	// アクセス数を取得するメソッド
	public void setAccessCount(int recipeId) throws SQLException {
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try{
			connection.setAutoCommit(false);
			pstatement = connection.prepareStatement("SELECT access_count FROM recipe_tbl WHERE recipe_id=? FOR UPDATE");
			pstatement.setInt(1, recipeId);
			rs = pstatement.executeQuery();

			int accessCount = 0;
			if (rs.next()) {
				accessCount = rs.getInt("access_count");
			}
			rs.close();
			pstatement = connection.prepareStatement("UPDATE recipe_tbl SET access_count = ? WHERE recipe_id = ?");
			pstatement.setInt(1, accessCount + 1);
			pstatement.setInt(2, recipeId);

			pstatement.executeUpdate();
			connection.commit();
		} finally {
			pstatement.close();
		}
	}

}

