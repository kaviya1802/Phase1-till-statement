package com.ecommerce.customers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class DeleteCustomer
 */
@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("deletecustomer.html");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Delete customer
		
		try {
			
			String name = request.getParameter("name");
			PrintWriter out = response.getWriter();
			
			//Load data from config
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//get connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			//Write query
			
			String query = "Delete from customer where name = ?";
			
			//create statement
			
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			pstm.setString(1, name);
			
			//Execute query
			int noOfRows = pstm.executeUpdate();
			System.out.println(noOfRows);
			
			//Print Response
			
			out.println("<html><body>");
			if(noOfRows>0) {
				out.println("<h1>" + noOfRows+ "  Rows affected !! </h1>");
			}else {
				out.println("<h1> Customer cannot be deleted!!!</h1>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
