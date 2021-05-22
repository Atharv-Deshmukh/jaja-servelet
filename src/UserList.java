import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserList {
	private String dburl = "jdbc:mysql://localhost/userdb";
	private String uname = "root";
	private String pass = "mysql123";
	private String dbdriver = "com.mysql.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			getClass().forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection(dburl, uname, pass);
		return con;
	}
	
	public List<UserDetails>getAllUsers(){
		loadDriver(dbdriver);
		Connection con;
		Statement st;
		List<UserDetails>users = new ArrayList<UserDetails>();
		try {
			con = getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users;");
			while(rs.next()) {
				UserDetails usr = new UserDetails();
				usr.setId(rs.getInt("id"));
				usr.setEmail(rs.getString("email"));
				usr.setFname(rs.getString("fname"));
				usr.setLname(rs.getString("lname"));
				usr.setPass(rs.getString("pass"));
				users.add(usr);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
}
