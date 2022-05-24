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
 * Servlet implementation class UpdateWithParam
 */
@WebServlet("/UpdateWithParam")
public class UpdateWithParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWithParam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Update with Parameter
		
		try {
			
			PrintWriter out = response.getWriter();
			
			//load data from config
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//Get Connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			//get query
			String query = "Update customer SET name = ? where id = ?";
			
			//Create statement
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			pstm.setString(1, "Priya");
			pstm.setInt(2, 4);
			
			//execute query
			
			int noOfRows = pstm.executeUpdate();
			
			//Print response
			
			out.println("<html><body>");
			if(noOfRows>0) {
				out.println("<h1> "+noOfRows +" Rows Affected !!! </h1>");
			}else {
				out.println("<h1> Data cannot be added</h1>");
			}
			out.println("</body></html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
