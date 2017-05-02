package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Serve;

public class ServeDao {

	public int save(Serve serve) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();
       int a=0;
		try {
			for (int i : serve.getBranchID()) {
				
					preparedStatement = connection
							.prepareStatement("insert into serve values (?,?,?)");
					preparedStatement.setDouble(1, serve.getPrice());
					preparedStatement.setInt(2, i);
					preparedStatement.setInt(3, serve.getRecipeId());
				a=preparedStatement.executeUpdate();
				
			}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return a;
	}
	public List<Integer> getbranchId(int recipeId) {
		List<Integer> branchIds = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection
					.prepareStatement("select branch_id from serve where recipe_id=?");
			preparedStatement.setInt(1, recipeId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
			branchIds.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branchIds;
	}
}
