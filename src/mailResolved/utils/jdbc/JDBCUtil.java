package mailResolved.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mailResolved.entity.Resume;


public class JDBCUtil {

	public Connection getConn() {

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/lptong?useUnicode=true&characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		Connection conn = null;
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public int insert(Resume resume) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into resumes "
				+ "(resumeId,resumeName,username,sex,age,nation,education,major"
				+ ",college,address,phone,email,stuExperience,jobExperience,assess,dataSource) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resume.getId());
			pstmt.setString(2, resume.getName());
			pstmt.setString(3, resume.getUsername());
			pstmt.setInt(4, resume.getSex());
			pstmt.setInt(5, resume.getAge());
			pstmt.setString(6, resume.getNation());
			pstmt.setString(7, resume.getEducation());
			pstmt.setString(8, resume.getMajor());
			pstmt.setString(9, resume.getCollege());
			pstmt.setString(10, resume.getAddress());
			pstmt.setString(11, resume.getPhone());
			pstmt.setString(12, resume.getEmail());
			pstmt.setString(13, resume.getStuExperience());
			pstmt.setString(14, resume.getJobExperience());
			pstmt.setString(15, resume.getAssess());
			pstmt.setString(16, resume.getDataSource());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	public void insert2(List<Resume> resumes) {
		Connection conn = getConn();

		String sql = "insert into resumes "
				+ "(resumeName,username,sex,age,nation,education,major"
				+ ",college,address,phone,email,stuExperience,jobExperience,assess,dataSource) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(Resume resume:resumes){  
				pstmt.setString(1, resume.getName());
				pstmt.setString(2, resume.getUsername());
				pstmt.setInt(3, resume.getSex());
				pstmt.setInt(4, resume.getAge());
				pstmt.setString(5, resume.getNation());
				pstmt.setString(6, resume.getEducation());
				pstmt.setString(7, resume.getMajor());
				pstmt.setString(8, resume.getCollege());
				pstmt.setString(9, resume.getAddress());
				pstmt.setString(10, resume.getPhone());
				pstmt.setString(11, resume.getEmail());
				pstmt.setString(12, resume.getStuExperience());
				pstmt.setString(13, resume.getJobExperience());
				pstmt.setString(14, resume.getAssess());
				pstmt.setString(15, resume.getDataSource());
				pstmt.addBatch();  
			}
			
			pstmt.executeBatch();
			conn.commit();  
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
