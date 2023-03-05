import java.sql.*;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=HotelDBMS;" +
                "encrypt=true;" +
                "trustServerCertificate=true";
		
		String user = "sa";
        String pass = "root";
        
        Connection con = null;

        try {
        	Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);
            con = DriverManager.getConnection(url, user, pass);
            
            Statement st = con.createStatement();
            //create all tables
            initializeAllTables(st);
            
            //insert 10,000 hotels. IN THE CONSOLE TYPE 10,000 
            Hotel.insertIntoTable(st);
            
            //insert 1 hotels. IN THE CONSOLE TYPE 1 
            Hotel.insertIntoTable(st);
            
            //print 10 hotels, IN THE CONSOLE TYPE 10
            Hotel.readFromTable(st);
            
            //make the first 10 hotels not active, IN THE CONSOLE TYPE 1, THEN 2, THEN 3... THEN 10.
            for (int i = 0; i<10; i++) {
            	Hotel.makeIsActiveFalseById(st);
            }
            
            //select 1 hotel by the ID
            Hotel.getById(st);
            
            
            con.close();
        }
        catch(Exception ex) {
        	System.err.println(ex);
        }
		
	}
	
	static void initializeAllTables(Statement st) {
		try {
            String sql = "IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'Hotels')\r\n"
            		+ "BEGIN\r\n"
            		+ "CREATE TABLE Hotels (\r\n"
            		+ "  id INTEGER PRIMARY KEY IDENTITY(1,1),\r\n"
            		+ "  hotel_name Text NOT NULL,\r\n"
            		+ "  hotel_location Text,\r\n"
            		+ "  created_date DATE NOT NULL,\r\n"
            		+ "  updated_date DATE,\r\n"
            		+ "  is_Active BIT NOT NULL\r\n"
            		+ ");"
            		+ "END";
            int m = st.executeUpdate(sql);
            if (m==0) {
            	System.out.println("\n hotels table is created");            	
            }
            else {
            	System.out.println("\n hotels table already exists");
            }
            
            sql = "IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'Room_Type')\r\n"
            		+ "BEGIN\r\n"
            		+ "CREATE TABLE Room_Type (\r\n"
            		+ "  id INTEGER PRIMARY KEY IDENTITY(1,1),\r\n"
            		+ "  room_type_name Text NOT NULL,\r\n"
            		+ "  created_date DATE,\r\n"
            		+ "  updated_date DATE,\r\n"
            		+ "  is_Active BIT NOT NULL\r\n"
            		+ ");"
            		+ "END";
            m = st.executeUpdate(sql);
            if (m==0) {
            	System.out.println("\n room_type table is created");            	
            }
            else {
            	System.out.println("\n room_type table already exists");
            }
            
            sql = "IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'Rooms')\r\n"
            		+ "BEGIN\r\n"
            		+"CREATE TABLE Rooms (\r\n"
            		+ "  id INTEGER PRIMARY KEY IDENTITY(1,1),\r\n"
            		+ "  room_type_id INTEGER,\r\n"
            		+ "  hotel_id INTEGER,\r\n"
            		+ "  created_date DATE NOT NULL,\r\n"
            		+ "  updated_date DATE,\r\n"
            		+ "  is_Active BIT NOT NULL,\r\n"
            		+ "  FOREIGN KEY (room_type_id) REFERENCES Room_Type(id),\r\n"
            		+ "  FOREIGN KEY (hotel_id) REFERENCES Hotels(id)\r\n"
            		+ ");"
            		+ "END";
            m = st.executeUpdate(sql);
            if (m==0) {
            	System.out.println("\n rooms table is created");            	
            }
            else {
            	System.out.println("\n rooms table already exists");
            }
            
            sql = "IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'Guests')\r\n"
            		+ "BEGIN\r\n"
            		+ "CREATE TABLE Guests (\r\n"
            		+ "  id INTEGER PRIMARY KEY IDENTITY(1,1),\r\n"
            		+ "  guest_name Text NOT NULL,\r\n"
            		+ "  guest_phone Text NOT NULL,\r\n"
            		+ "  guest_accompanying_members INTEGER NOT NULL,\r\n"
            		+ "  guest_payment_amount INTEGER NOT NULL,\r\n"
            		+ "  room_id INTEGER,\r\n"
            		+ "  hotel_id INTEGER,\r\n"
            		+ "  created_date DATE NOT NULL,\r\n"
            		+ "  updated_date DATE,\r\n"
            		+ "  is_Active BIT NOT NULL,\r\n"
            		+ "  FOREIGN KEY (room_id) REFERENCES Rooms(id),\r\n"
            		+ "  FOREIGN KEY (hotel_id) REFERENCES Hotels(id)\r\n"
            		+ ");"
            		+ "END";
            m = st.executeUpdate(sql);
            if (m==0) {
            	System.out.println("\n guests table is created");            	
            }
            else {
            	System.out.println("\n guests table already exists");
            }
            
            sql = "IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'Employee_Type')\r\n"
            		+ "BEGIN\r\n"
            		+ "CREATE TABLE Employee_Type (\r\n"
            		+ "  id INTEGER PRIMARY KEY IDENTITY(1,1),\r\n"
            		+ "  employee_type_name Text NOT NULL,\r\n"
            		+ "  created_date DATE NOT NULL,\r\n"
            		+ "  updated_date DATE,\r\n"
            		+ "  is_Active BIT NOT NULL\r\n"
            		+ ");"
            		+ "END";
            m = st.executeUpdate(sql);
            if (m==0) {
            	System.out.println("\n employee_type table is created");            	
            }
            else {
            	System.out.println("\n employee_type table already exists");
            }
            
            sql = "IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'Employees')\r\n"
            		+ "BEGIN\r\n"
            		+ "CREATE TABLE Employees (\r\n"
            		+ "  id INTEGER PRIMARY KEY IDENTITY(1,1),\r\n"
            		+ "  employee_type_id INTEGER,\r\n"
            		+ "  room_id INTEGER,\r\n"
            		+ "  created_date DATE NOT NULL,\r\n"
            		+ "  updated_date DATE,\r\n"
            		+ "  is_Active BIT NOT NULL,\r\n"
            		+ "  FOREIGN KEY (employee_type_id) REFERENCES Employee_Type(id),\r\n"
            		+ "  FOREIGN KEY (room_id) REFERENCES Rooms(id)\r\n"
            		+ ");"
            		+ "END";
            m = st.executeUpdate(sql);
            if (m==0) {
            	System.out.println("\n employees table is created");            	
            }
            else {
            	System.out.println("\n employees table already exists");
            }
        }
        catch(Exception ex) {
            System.err.println(ex);
        }
	}
	
	public static int getInput(String message) {
		while(true) {
			String input = Main.sc.next();
			try {
				Integer inputInt = Integer.parseInt(input);
				return inputInt;
			}
			catch(Exception ex) {
				System.out.println("\nInvalid Input");
			}
			System.out.print(message);
		}
	}
	
	

}
