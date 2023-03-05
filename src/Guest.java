import java.sql.*;

public class Guest {
	public static void readFromTable(Statement st) {
		String message = "How many Guests Do You Want To Print: ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Select Top("+input+") * from Guests";
			
			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	public static void getById(Statement st) {
		String message = "Which Guest Do You Want To Print (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Select * from Guests Where id = "+input+";";
			
			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void updateById(Statement st) {
		String message = "Which Guest Do You Want To Update (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "UPDATE Guests\r\n"
					+ "SET updated_date = GETDATE()\r\n"
					+ "WHERE id = "+input+";";
			st.executeUpdate(sql);

			sql = "Select * from Guests Where id = "+input+";";
			
			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void deleteById(Statement st) {
		String message = "Which Guest Do You Want To Delete (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Delete From Guests Where id = "+input+";";
			st.executeUpdate(sql);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void makeIsActiveFalseById(Statement st) {
		String message = "Which Guest Do You Want To Set Inactive (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "UPDATE Guests\r\n"
					+ "SET is_Active = 0\r\n"
					+ "WHERE id = "+input+";";
			st.executeUpdate(sql);
			
			sql = "Select * from Guests Where id = "+input+";";
			
			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void insertIntoTable(Statement st) {
		String message = "How Many Guests Do You want to Insert: ";
		System.out.print(message);
		int input = Main.getInput(message);
		for (int i = 0; i < input; i++) {
			int randInt = (int) Math.floor((Math.random() * 999999) + 1);
			try {
				
				String sql = "Insert into Guests \r\n"
						+ "values('SAIDR"+randInt+"','SAIDR"+randInt+"',"+randInt+","+randInt+","+randInt+","+randInt+", GETDATE(),GETDATE(),1);";
				st.executeUpdate(sql);
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
		}
	}
	
	public static void printTable(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id")); 
				System.out.println(resultSet.getString("guest_name"));
				System.out.println(resultSet.getString("guest_phone"));
				System.out.println(resultSet.getString("guest_accompanying_members"));
				System.out.println(resultSet.getString("guest_payment_amount"));
				System.out.println(resultSet.getString("room_id"));
				System.out.println(resultSet.getString("hotel_id"));
				System.out.println(resultSet.getString("created_date"));
				System.out.println(resultSet.getString("updated_date"));
				System.out.println(resultSet.getString("is_Active"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
