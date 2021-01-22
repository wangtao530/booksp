package book.test;



import java.awt.image.BufferedImage;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import book.util.VerifyCode;

class BookTest {

	void testJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://192.168.0.220:3306/book";
			String user = "userbook";
			String password = "us1rP@ssw0rd";
			Connection c = DriverManager.getConnection(url, user, password);
			System.out.println(c);
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
	}

	// 在其他类中调用
	public void fun() {
		VerifyCode vc = new VerifyCode();
		BufferedImage bi = vc.getImage();
		System.out.println(vc.getText());
		System.out.println(bi);
		
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		//BookTest b = new BookTest();
		//b.encryptData();
	}
	
}