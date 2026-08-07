package zx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import zx.bean.Users;
import zx.util.DBUtil;

public class UserDao {
	public void selectForLogin(Users user) {
		Connection conn = DBUtil.getConn();
		String sql = "select * from users where name=? and pwd=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());   // 1 = wei zhi
			ps.setString(2, user.getPwd());   // 1 = wei zhi

			rs = ps.executeQuery();    // 查询操作结果在rs对象
			while (rs.next()) {
				int id = rs.getInt(1);   // 如果id的值大于 0 说明登录信息正确
				String tel = rs.getString("tel");
				String username = rs.getString(3);
				String pwd = rs.getString(4);
				String email = rs.getString(5);
				String sex = rs.getString(6);

				LocalDateTime createTimeValue = rs.getTimestamp("create_time").toLocalDateTime();				//取出时间
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");				//设置日期显示格式
//				String createTime = createTimeValue.format(formatter);				//把取出的时间转换成需要的时间格式字符串

				//封装数据
				user.setId(id);
				user.setTel(tel);
//	    	    user.setName(userName);
				user.setEmail(email);
				user.setSex(sex);
				user.setCreatetime(createTimeValue);

			}
		} catch (SQLException e) {
			e.printStackTrace();   // 不要删，出了问题需要在控制台找出原因
			user.setId(-1);         // 出现异常（不一定是数据库）
		} finally {
			DBUtil.closeDBResource(rs, ps, conn);
		}
		System.out.println("UserDao "+ user);
	}

	public static boolean selectForRegister(Users user) {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT 1 FROM users WHERE name=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println(user);
		try {
			System.out.print(sql + "\n");
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			rs = ps.executeQuery();    // 查询操作结果在rs对象
			return rs.next();  // 如果结果集中有数据，返回 true，否则返回 false
		} catch (SQLException e) {
			e.printStackTrace();   // 不要删，出了问题需要在控制台找出原因
			return false;  // 出现异常时，返回 false
		} finally {
			DBUtil.closeDBResource(rs, ps, conn);
		}

	}

	public static boolean InsertUser(Users user) {
		Connection conn = DBUtil.getConn();  // 获取数据库连接
//		String sql = "INSERT INTO users (name, pwd, tel, email, sex, create_time) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";  // SQL 插入语句
		String sql = "insert into users(name,pwd,tel,email,sex,create_time) values(?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";  // SQL 插入语句
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);  // 预编译 SQL 语句
			ps.setString(1, user.getName());  // 设置 SQL 插入参数
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getTel());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getSex());
			int rowsInserted = ps.executeUpdate();  // 执行插入操作
			return rowsInserted > 0;  // 如果插入行数大于 0，返回 true，否则返回 false
		} catch (SQLException e) {
			e.printStackTrace();
			return false;  // 出现异常时，返回 false
		} finally {
			DBUtil.closeDBResource(null, ps, conn);  // 关闭数据库资源
		}
	}

	public static boolean ChangeUser(Users user) {
		Connection conn = DBUtil.getConn();  // 获取数据库连接
		String sql = "UPDATE users SET pwd=? WHERE name=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);  // 预编译 SQL 语句
			ps.setString(1, user.getPwd());
			ps.setString(2, user.getName());
			int rowsChange = ps.executeUpdate();  // 执行插入操作
			return rowsChange > 0;  // 如果插入行数大于 0，返回 true，否则返回 false
		} catch (SQLException e) {
			e.printStackTrace();
			return false;  // 出现异常时，返回 false
		} finally {
			DBUtil.closeDBResource(null, ps, conn);  // 关闭数据库资源
		}
	}

	public static String getJiuPassword(String username) {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT pwd FROM users WHERE name=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("pwd"));
				return rs.getString("pwd");
			} else {
				return null; // 用户不存在
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // 出现异常时，返回null
		} finally {
			DBUtil.closeDBResource(rs, ps, conn);
		}
	}


}



