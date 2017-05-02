package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Feedback;

public class FeedbackDAO {
	public static int saveRatings() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();

		try {
			preparedStatement = connection.prepareStatement(
					"insert into recipe_rating(branch_id,customer_id,comments,feedback_date) values (?,?,?,?,?)");
			Feedback feedback = new Feedback();
			preparedStatement.setInt(1, feedback.getBranchId());
			preparedStatement.setInt(2, feedback.getCustomerId());
			preparedStatement.setString(3, feedback.getComments());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = null;
			date = (feedback.getFeedbackDate());
			java.sql.Date ratedDate = new java.sql.Date(date.getTime());
			preparedStatement.setDate(4, ratedDate);

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
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static int delete(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection.prepareStatement("delete from  rating  where id=? ");

			preparedStatement.setInt(1, id);

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
				e.printStackTrace();
			}
		}
		return 0;

	}

	public List<Feedback> getFeedbacksByItem(int recipeId) {

		List<Feedback> feedbackList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DAOUtility.getConncetion();

		try {
			preparedStatement = connection.prepareStatement("SELECT * from feedback_recipe where recipe_id=?");
			preparedStatement.setInt(1, recipeId);
			resultSet = preparedStatement.executeQuery();
			feedbackList = new ArrayList<Feedback>();
			while (resultSet.next()) {
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(resultSet.getInt(1));
				feedback.setComments(resultSet.getString(2));
				feedback.setFeedbackDate(resultSet.getDate(3));
				feedback.setBranchId(resultSet.getInt(4));
				feedback.setCustomerId(resultSet.getInt(5));
				feedbackList.add(feedback);
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
				e.printStackTrace();
			}
		}
		return feedbackList;

	}

	public List<Feedback> getFeedbacksByUser(int userId) {

		List<Feedback> feedbackList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DAOUtility.getConncetion();

		try {
			preparedStatement = connection.prepareStatement("SELECT * from feedback_recipe where recipe_id=?;");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			feedbackList = new ArrayList<Feedback>();
			while (resultSet.next()) {
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(resultSet.getInt(1));
				feedback.setComments(resultSet.getString(2));
				feedback.setFeedbackDate(resultSet.getDate(3));
				feedback.setBranchId(resultSet.getInt(4));
				feedback.setCustomerId(resultSet.getInt(5));
				feedbackList.add(feedback);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedbackList;

	}

}
