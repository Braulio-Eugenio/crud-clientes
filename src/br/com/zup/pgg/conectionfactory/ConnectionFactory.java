package br.com.zup.pgg.conectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection()  {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pgg?user=root" + "&password=root&useTimezone=true&serverTimezone=UTC");

		} catch (SQLException e) {
			
			throw new RuntimeException(e);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
