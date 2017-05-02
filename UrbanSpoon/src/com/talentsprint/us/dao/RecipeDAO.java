package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Recipe;

public class RecipeDAO {
	public int save(Recipe recipe) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection
					.prepareStatement("insert into recipe(name,description,image_path,cuisine_id,is_veg) values (?,?,?,?,?)");

			preparedStatement.setString(1, recipe.getName());
			preparedStatement.setString(2, recipe.getDescription());
			preparedStatement.setString(3, recipe.getImagePath());
			preparedStatement.setInt(4, recipe.getCuisineId());
			if (recipe.isVeg()) {
				preparedStatement.setString(5, "1");
			}
			else
			{
				preparedStatement.setString(5, "2");
			}
			preparedStatement.setBoolean(5, recipe.isVeg());
			
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<Recipe> getRecipes() {
		List<Recipe> arrayList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection
					.prepareStatement("select * from recipe");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeId(resultSet.getInt(1));
				recipe.setName(resultSet.getString(2));
				recipe.setCuisineId(resultSet.getInt(3));
				recipe.setDescription(resultSet.getString(4));
				recipe.setImagePath(resultSet.getString(5));
				recipe.setVeg((resultSet.getInt(6) > 0));
				recipe.setFeedbackList(new FeedbackDAO()
			    .getFeedbacksByItem(resultSet.getInt(1)));
				arrayList.add(recipe);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(arrayList.size());
		return arrayList;

	}

	public int delete(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection
					.prepareStatement("delete from recipe where id=? ");

			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {

				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int getRecipeID(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection
					.prepareStatement("select recipe_id from recipe where name=?");

			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {

				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
