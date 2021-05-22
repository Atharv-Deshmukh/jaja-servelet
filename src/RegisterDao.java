import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegisterDao {
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
	
	public String insert(User user) throws SQLException {
		String result = "Data Entered Successfully!";
		loadDriver(dbdriver);
		Connection con = getConnection();
		String sql = "insert into users (email, fname, lname, pass) values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, user.getEmail());
		ps.setString(2, user.getFname());
		ps.setString(3, user.getLname());
		ps.setString(4, user.getPass());
		ps.executeUpdate();
		return result;
	}
}
