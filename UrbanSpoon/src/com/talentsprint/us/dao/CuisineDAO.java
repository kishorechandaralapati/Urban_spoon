package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Cuisine;

public class CuisineDAO {

	public int insert(Cuisine cuisine) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection
					.prepareStatement("insert into cuisine (name,country) values (?,?) ");
			preparedStatement.setString(1, cuisine.getName());
			preparedStatement.setString(2, cuisine.getCountry());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
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

	public ArrayList<Cuisine> getCuisines() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Cuisine> cuisinesList = new ArrayList<>();
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection
					.prepareStatement("select * from cuisine");

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())

			{
				Cuisine cuisine = new Cuisine();
				cuisine.setCuisineId(resultSet.getString(1));
				cuisine.setName(resultSet.getString(2));
				cuisine.setCountry(resultSet.getString(3));
				cuisinesList.add(cuisine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cuisinesList;
	}

}
