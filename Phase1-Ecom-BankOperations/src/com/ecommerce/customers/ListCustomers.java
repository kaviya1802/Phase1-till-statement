package com.ecommerce.customers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class ListCustomers
 */
@WebServlet("/ListCustomers")
public class ListCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCustomers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ListCustomers
		
		try {
			PrintWriter out = response.getWriter();
			
			//load data from config
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//Get connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			//create statement
			Statement stm = conn.getConnection().createStatement();
			
			//execute query
			String query = "Select * from customer";
			ResultSet result = stm.executeQuery(query);
			
			//Print Response
			out.println("<html><body>");
			while(result.next()) {
				out.println("<p>"+result.getInt("id")+"  |  "+result.getString("name")+"  |  "+result.getString("email")+"  |  "+
						result.getString("account_type")+"  |  "+result	.getTimestamp("date_added"));
			}
			out.println("</body></html>");
			
			//Close connection
			conn.closeConnection();
			
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
