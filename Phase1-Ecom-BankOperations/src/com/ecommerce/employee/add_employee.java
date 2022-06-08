package com.ecommerce.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;
import com.mysql.cj.jdbc.CallableStatement;

/**
 * Servlet implementation class add_employee
 */
@WebServlet("/add_employee")
public class add_employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Add Employee
		
		try {
			
			PrintWriter out = response.getWriter();
			
			//fetch data from config.properties
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//get connectiion
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			//create statement
			
			java.sql.CallableStatement cstm = conn.getConnection().prepareCall("{call add_employees(?,?,?,?)}");
			cstm.setString(1, "Kaviya");
			cstm.setString(2, "nallakavi@gmail.com");
			cstm.setString(3, "Production");
			cstm.setDouble(4, 66000.23);
			//execute query
			
			int no = cstm.executeUpdate();
			
			//Print response
			out.println("<html><body>");
			if(no>0) {
				out.println("<h1> "+no+ " Rows affected!!</h1>");
			}else {
				out.println("</h1>"+no+ " Rows Affected!!</h1>");
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
