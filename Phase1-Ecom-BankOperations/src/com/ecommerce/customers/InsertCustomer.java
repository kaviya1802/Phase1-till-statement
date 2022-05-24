package com.ecommerce.customers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/InsertCustomer")
public class InsertCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Insert customer info
		
		try {
			PrintWriter out = response.getWriter();
			
			//load data from config
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//get connection
			
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			//create statement
			
			Statement stm = conn.getConnection().createStatement();
			//Execute query
			
			String query = "INSERT INTO customer(name, email, account_type) VALUES('Prakash', 'prakashs@gmail.com', 'Savings ')";
			int result = stm.executeUpdate(query);
			
			//Print Response
			
			out.println("<html><body>");
			if(result>0) {
				out.println("<h1> "+result+" Rows affected!!</h1>");
			}else {
				out.println("<h1> Customer cannot be added!!!</h1>");
			}
			out.println("</body></html>");
			
			//close connection
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
