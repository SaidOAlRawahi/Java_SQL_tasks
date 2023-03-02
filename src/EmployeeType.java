import java.sql.*;

public class EmployeeType {
	public static void readFromTable(Statement st) {
		String message = "How many Employee_Type Do You Want To Print: ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Select Top("+input+") * from Employee_Type";
			
			ResultSet resultSet = st.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id")); 
				System.out.println(resultSet.getString("employee_type_name "));
				System.out.println(resultSet.getString("created_date"));
				System.out.println(resultSet.getString("updated_date"));
				System.out.println(resultSet.getString("is_Active"));
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	public static void getById(Statement st) {
		String message = "Which Employee_type Do You Want To Print (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Select * from Employee_type Where id = "+input+";";
			
			ResultSet resultSet = st.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id")); 
				System.out.println(resultSet.getString("employee_type_name "));
				System.out.println(resultSet.getString("created_date"));
				System.out.println(resultSet.getString("updated_date"));
				System.out.println(resultSet.getString("is_Active"));
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void updateById(Statement st) {
		String message = "Which Employee_type Do You Want To Update (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "UPDATE Employee_type\r\n"
					+ "SET updated_date = GETDATE()\r\n"
					+ "WHERE id = "+input+";";
			st.executeUpdate(sql);

			sql = "Select * from Employee_type Where id = "+input+";";
			
			ResultSet resultSet = st.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id")); 
				System.out.println(resultSet.getString("employee_type_name "));
				System.out.println(resultSet.getString("created_date"));
				System.out.println(resultSet.getString("updated_date"));
				System.out.println(resultSet.getString("is_Active"));
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void deleteById(Statement st) {
		String message = "Which Employee_type Do You Want To Delete (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Delete From Employee_type Where id = "+input+";";
			st.executeUpdate(sql);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void makeIsActiveFalseById(Statement st) {
		String message = "Which Employee_type Do You Want To Set Inactive (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "UPDATE Employee_type\r\n"
					+ "SET is_Active = 0\r\n"
					+ "WHERE id = "+input+";";
			st.executeUpdate(sql);
			
			sql = "Select * from Employee_type Where id = "+input+";";
			
			ResultSet resultSet = st.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id")); 
				System.out.println(resultSet.getString("employee_type_name "));
				System.out.println(resultSet.getString("created_date"));
				System.out.println(resultSet.getString("updated_date"));
				System.out.println(resultSet.getString("is_Active"));
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void insertIntoTable(Statement st) {
		String message = "How Many Room_typed Do You want to Insert: ";
		System.out.print(message);
		int input = Main.getInput(message);
		for (int i = 0; i < input; i++) {
			int randInt = (int) Math.floor((Math.random() * 200000000) + 1);
			try {
				
				String sql = "Insert into Room_type \r\n"
						+ "values("+randInt+",'SAID AL RAWAHI"+randInt+"', GETDATE(),GETDATE(),1);";
				st.executeUpdate(sql);
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
		}
	
	}
}
