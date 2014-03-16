package mailResolved.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import mailResolved.entity.MailAccount;
import mailResolved.utils.jdbc.JDBCUtil;

/**
 * Servlet implementation class MailAccountServlet
 */
@WebServlet("/MailAccountServlet")
public class MailAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JDBCUtil jdbc = new JDBCUtil();
		String sql="select * from mail_account";
		Connection conn = jdbc.getConn();
		List<MailAccount> accounts = new ArrayList<MailAccount>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				MailAccount account = new MailAccount();
				account.setId(rs.getInt("id"));
				account.setHost(rs.getString("host"));
				account.setFile(rs.getString("file"));
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/WEB-INF/pages/mailResolved/list.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
