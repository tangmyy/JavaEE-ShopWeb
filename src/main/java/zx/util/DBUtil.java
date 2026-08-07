package zx.util;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBUtil {

	public static Connection getConn(){

		String DBDirverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String DBUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=shop";
		String DBUser = "sa";
		String DBPwd = "2004";
		Connection connection = null;
		try {
			Class.forName(DBDirverName);
			connection = DriverManager.getConnection(DBUrl, DBUser, DBPwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("SQLServerDriver没有找到，驱动包加载了吗");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			System.out.println("数据库连接出现了问题");
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeDBResource(ResultSet rs, Statement stm, Connection con) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stm != null) {
				stm.close();
				stm = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("关闭数据库出现了问题");
			e.printStackTrace();
		}

	}
	public  void printTableDataForTest(){
		Connection conn=getConn();
		String sql="select * from users";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();;
			while(rs.next()){
				int id=rs.getInt(1);
				String tel=rs.getString("tel");
				String username=rs.getString(3);
				String pwd=rs.getString(4);
				String email=rs.getString(5);
				String sex=rs.getString(6);

				//取出时间
				LocalDateTime createTimeValue = rs.getTimestamp("create_time").toLocalDateTime();
				//设置日期显示格式
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
				//把取出的时间转换成需要的时间格式字符串
				String createTime = createTimeValue.format(formatter);

				System.out.print(id+"\t");
				System.out.print(tel+"\t");
				System.out.print(username+"\t");
				System.out.print(pwd+"\t");
				System.out.print(createTime+"\t");
				System.out.print(sex+"\t\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();//不要删，出了问题需要在控制台找出原因
		}finally {
			closeDBResource(rs, ps, conn);
		}

	}
}
