package com.talentsprint.us.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.talentsprint.us.dao.BranchDAO;
import com.talentsprint.us.dao.CuisineDAO;
import com.talentsprint.us.dao.RecipeDAO;
import com.talentsprint.us.dao.ServeDao;
import com.talentsprint.us.model.Branch;
import com.talentsprint.us.model.Cuisine;
import com.talentsprint.us.model.Recipe;
import com.talentsprint.us.model.Serve;

@WebServlet("/RestaurantController")
public class RestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);

		if (action.equals("addBranch")) {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loggedUser")) {

				response.sendRedirect("login.jsp");

			} else {
				response.sendRedirect("addBranch.jsp");
			}

		} else if (action.equals("addRecipe")) {

			List<Cuisine> cuisineList = new CuisineDAO().getCuisines();
			request.setAttribute("cuisineList", cuisineList);
			HttpSession session = request.getSession();
			int a = (int) session.getAttribute("loggedUser");
			List<Branch> branchList = new BranchDAO().getBranches(a);
			request.setAttribute("branchList", branchList);
			request.getRequestDispatcher("addRecipe.jsp").forward(request,
					response);
		} else if (action.equals("viewRecipe")) {

			List<Recipe> recipesList = new RecipeDAO().getRecipes();
			request.setAttribute("recipesList", recipesList);
			request.getRequestDispatcher("viewrecipe.jsp").forward(request,
					response);
		} else if (action.equals("viewBranch")) {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loggedUser")) {

				response.sendRedirect("login.jsp");

			} else {

				List<Branch> branchList = new BranchDAO()
						.getBranches((int) session.getAttribute("loggedUser"));
				request.setAttribute("branchList", branchList);
				request.getRequestDispatcher("viewbranch.jsp").forward(request,
						response);
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		String str = "";
		if (isMultipart) {

			DiskFileItemFactory dis = new DiskFileItemFactory();
			ServletFileUpload sfd = new ServletFileUpload(dis);
			try {
				List<FileItem> list = sfd.parseRequest(request);
				Branch branch = new Branch();
				Recipe recipe = new Recipe();
				Serve serve = new Serve();
				int count = 0;
				for (FileItem fileItem : list) {
					if (fileItem.getFieldName().equals("action")
							&& fileItem.getString().equals("addRecipe")) {
						count = 1;
					}
					if (count == 0) {
						if (!fileItem.isFormField()) {
							String imagePath = "C:/raji/"
									+ getServletContext().getContextPath()
									+ "/WebContent/images/branches/"
									+ fileItem.getName();
							fileItem.write(new File(imagePath));
							branch.setImagePath(fileItem.getName());
						}
						if (fileItem.getFieldName().equals("location")) {
							System.out.println(fileItem.getString());
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
					} else if (count == 1) {
						if (!fileItem.isFormField()) {
							String imagePath = "C:/raji/"
									+ getServletContext().getContextPath()
									+ "/WebContent/images/recipes/"
									+ fileItem.getName();
							fileItem.write(new File(imagePath));
							recipe.setImagePath(fileItem.getName());
						}
						if (fileItem.getFieldName().equals("name")) {
							recipe.setName((fileItem.getString()));
						} else if (fileItem.getFieldName()
								.equals("description")) {
							recipe.setDescription(fileItem.getString());
						} else if (fileItem.getFieldName().equals("isVeg")) {
							recipe.setVeg((fileItem.getString()).equals("1"));
						} else if (fileItem.getFieldName().equals("cuisine")) {
							recipe.setCuisineId((Integer.parseInt(fileItem
									.getString())));
						} else if (fileItem.getFieldName().equals("branch")) {

							str = str + fileItem.getString() + ",";
						} else if (fileItem.getFieldName().equals("price")) {
							serve.setPrice(Double.parseDouble(fileItem
									.getString()));
						}
					}
				}

				if (count == 0) {
					HttpSession session = request.getSession();
					branch.setRestaurntId((int) session
							.getAttribute("loggedUser"));
					if (new BranchDAO().insert(branch) > 0) {
						response.sendRedirect("RestaurantController?action=viewBranch");
					}
				}
				String branchId[] = str.split(",");
				List<Integer> branchIds = new ArrayList<Integer>();

				for (int i = 0; i < branchId.length; i++) {
					branchIds.add(Integer.parseInt(branchId[i]));
				}
				serve.setBranchID(branchIds);
				if (count == 1) {
					System.out.println(recipe);
					int a = new RecipeDAO().save(recipe);
					if (a > 0) {
						serve.setRecipeId(new RecipeDAO().getRecipeID(recipe
								.getName()));
						System.out.println(serve);
						new ServeDao().save(serve);
						response.sendRedirect("RestaurantController?action=viewRecipes");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
