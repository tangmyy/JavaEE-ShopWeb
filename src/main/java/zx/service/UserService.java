package zx.service;

import zx.bean.Users;
import zx.dao.UserDao;
import zx.util.MD5Util;

public class UserService {

	public void Login(Users user) {
		String pwdmd5 = MD5Util.getMD5Str(user.getPwd());
		user.setPwd(pwdmd5);
		new UserDao().selectForLogin(user);
	}
	public static boolean Register(Users user) {
		// 首先，检查用户是否已注册
		if (UserDao.selectForRegister(user)) {
			System.out.println("用户已注册");
			return false;
		} else {
			System.out.println("未注册，尝试插入新用户");
			return UserDao.InsertUser(user);
		}
	}
	public static boolean Retrieve(Users user) {
		// 首先，检查用户是否存在
		if (UserDao.selectForRegister(user)) {
			// 已注册，修改
			System.out.println("用户存在，可以修改");
			return UserDao.ChangeUser(user);		// 调用修改函数
		} else {
			// 未注册
			System.out.println("用户不存在，无法修改");
			return false;
		}
	}
}
