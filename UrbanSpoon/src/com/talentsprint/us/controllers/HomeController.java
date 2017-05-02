package com.talentsprint.us.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.talentsprint.us.dao.BranchDAO;
import com.talentsprint.us.dao.RecipeDAO;
import com.talentsprint.us.dao.RestaurantDAO;
import com.talentsprint.us.dao.UserDAO;
import com.talentsprint.us.model.Branch;
import com.talentsprint.us.model.Recipe;
import com.talentsprint.us.model.Restaurant;
import com.talentsprint.us.model.User;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (null == action) {
			List<Recipe> recipesList = new RecipeDAO().getRecipes();
			request.setAttribute("recipesList", recipesList);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();

			if (!request.isRequestedSessionIdValid()) {

				response.sendRedirect("index.jsp");
			}

		}

		else if (action.equals("viewBranch")) {
			Set<Branch> branchList = new BranchDAO().getBranchperRecipe(
					Integer.parseInt(request.getParameter("id")), 0);
			request.setAttribute("branchList", branchList);
			request.getRequestDispatcher("viewbranch.jsp").forward(request,
					response);
		} else if (action.equals("addfeedback")) {
			List<Restaurant> restaurants = new RestaurantDAO().getRestaurants();
			List<Branch> branchs = new BranchDAO().getBranches(1);

		}

		else if (action.equals("viewRestaurantviseBranches")) {

			Set<Restaurant> restaurants = new RestaurantDAO()
					.getRestaurantsServe(Integer.parseInt(request
							.getParameter("id")));
			System.out.println(restaurants.size());
			request.setAttribute("restuarntList", restaurants);
			request.getRequestDispatcher("viewbranch.jsp").forward(request,
					response);

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			DiskFileItemFactory dis = new DiskFileItemFactory();
			ServletFileUpload sfd = new ServletFileUpload(dis);

			try {
				List<FileItem> list = sfd.parseRequest(request);
				Restaurant restaurant = new Restaurant();
				Branch branch = new Branch();
				int restaurantId = 0;
				for (FileItem fileItem : list) {
					System.out.println(fileItem.getFieldName());
					if (!fileItem.isFormField()) {
						String imagePath = "C:/raji/"
								+ getServletContext().getContextPath()
								+ "/WebContent/images/branches/"
								+ fileItem.getName();
						fileItem.write(new File(imagePath));
						System.out.println(imagePath);
						branch.setImagePath(fileItem.getName());

					} else {
						if (fileItem.getFieldName().equals("name")) {
							restaurant
									.setRegistrationName(fileItem.getString());
						} else if (fileItem.getFieldName().equals("password")) {
							restaurant.setPassword(fileItem.getString());
						} else if (fileItem.getFieldName().equals(
								"registrationId")) {
							restaurant
									.setRegistartionId((fileItem.getString()));

						} else if (fileItem.getFieldName().equals("location")) {
							branch.setLocation((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("city")) {
							branch.setCity((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("state")) {
							branch.setState((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("postalCode")) {
							branch.setPostalCode((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("country")) {
							branch.setCountry((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("emailId")) {
							branch.setEmailId((fileItem.getString()));
						} else if (fileItem.getFieldName()
								.equals("phoneNumber")) {
							branch.setPhoneNumber((Long.parseLong(fileItem
									.getString())));
						}
					}

				}
				RestaurantDAO restaurantDAO = new RestaurantDAO();
				restaurantId = restaurantDAO.insert(restaurant);
				branch.setRestaurntId(restaurantId);
				int status = new BranchDAO().insert(branch);
				if (status > 0) {
					request.setAttribute("userName",
							restaurant.getRegistrationName());
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
				} else {
					response.sendRedirect("registration.jsp");

				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			String action = request.getParameter("action");
			if (action.equals("user_registration")) {
				String firstName = request.getParameter("FirstName");
				String lastName = request.getParameter("LastName");
				String email = request.getParameter("EmailId");
				String password = request.getParameter("password");
				long phoneNumber = Long.parseLong(request
						.getParameter("phoneNumber"));

				User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmailId(email);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				UserDAO userDAO = new UserDAO();
				if (userDAO.insert(user) > 0) {
					request.setAttribute("userName", email);
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
				} else {

				}

			} else if (action.equals("login")) {

				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String loginAs = request.getParameter("loginAs");
				if (loginAs.equals("userLogin")) {
					User user = new UserDAO().getuser(userName);
					if (user != null && user.getPassword().equals(password)) {
						HttpSession httpSession = request.getSession();
						httpSession.setAttribute("loggedUser",
								user.getUser_id());
						response.sendRedirect("home.jsp");
					} else {
						response.sendRedirect("login.jsp");
					}
				} else if (loginAs.equals("restaurantLogin")) {
					System.out.println(userName);
					Restaurant restaurant = new RestaurantDAO()
							.getRestaurant(userName);
					if (restaurant != null
							&& restaurant.getPassword().equals(password)) {

						HttpSession httpSession = request.getSession();
						httpSession.setAttribute("loggedUser",
								restaurant.getRestaurantId());
						response.sendRedirect("restaurantHome.jsp");
					} else {
						response.sendRedirect("login.jsp");
					}
				}
			}

		}

	}

}
