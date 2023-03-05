import java.sql.*;

public class Hotel {
	public static void readFromTable(Statement st) {
		String message = "How many Hotels Do You Want To Print: ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Select Top(" + input + ") * from Hotels";

			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void getById(Statement st) {
		String message = "Which Hotel Do You Want To Print (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Select * from Hotels Where id = " + input + ";";

			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void updateById(Statement st) {
		String message = "Which Hotel Do You Want To Update (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "UPDATE Hotels\r\n" + "SET updated_date = GETDATE()\r\n" + "WHERE id = " + input + ";";
			st.executeUpdate(sql);

			sql = "Select * from Hotels Where id = " + input + ";";

			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void deleteById(Statement st) {
		String message = "Which Hotel Do You Want To Delete (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "Delete From Hotels Where id = " + input + ";";
			st.executeUpdate(sql);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void makeIsActiveFalseById(Statement st) {
		String message = "Which Hotel Do You Want To Set Inactive (id): ";
		System.out.print(message);
		int input = Main.getInput(message);
		try {
			String sql = "UPDATE Hotels\r\n" + "SET is_Active = 0\r\n" + "WHERE id = " + input + ";";
			st.executeUpdate(sql);

			sql = "Select * from Hotels Where id = " + input + ";";

			ResultSet resultSet = st.executeQuery(sql);
			printTable(resultSet);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void insertIntoTable(Statement st) {
		String message = "How Many Hotels Do You want to Insert: ";
		System.out.print(message);
		int input = Main.getInput(message);
		for (int i = 0; i < input; i++) {
			int randInt = (int) Math.floor((Math.random() * 999999) + 1);
			try {

				String sql = "Insert into Hotels \r\n" + "values('SAIDR" + randInt
						+ "','SAIDR" + randInt + "', GETDATE(),GETDATE(),1);";
				st.executeUpdate(sql);
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	public static void printTable(ResultSet resultSet) {
		try {
			System.out.println();
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", "id", "hotel_name", "hotel_location", "created_date", "updated_date", "is_Active");
			while (resultSet.next()) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", resultSet.getString("id"),
						resultSet.getString("hotel_name"),
						resultSet.getString("hotel_location"),
						resultSet.getString("created_date"),
						resultSet.getString("updated_date"),
						resultSet.getString("is_Active")
						);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}
