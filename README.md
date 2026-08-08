# 嗨购购物网站（HiGo Shop）

> 上海建桥学院 · JavaEE 课程大作业 · 大学生入门级 Java Web 项目

一个使用纯 JavaEE 技术栈搭建的 B2C 电商购物网站，覆盖用户账号、商品浏览、购物车三大核心模块，代码结构清晰，适合作为 Java Web 入门的参考项目。

---

## 技术栈

| 技术 | 版本 | 说明 |
|---|---|---|
| **JSP (Java Server Pages)** | 2.3 | 服务器端动态视图层技术，在 HTML 中嵌入 Java 代码 + JSTL 标签库渲染页面；传统 JavaWeb 入门必会内容 |
| **Servlet** | 4.0 | 后端控制层，作为浏览器请求与 Java 业务逻辑之间的入口，分发到对应 Service |
| **fetch API** | ES6+ 原生 | 浏览器内置的异步 HTTP 请求工具，替代传统 `<form>` 整页提交的方式，实现**无刷新**的用户交互（如购物车勾选、加购、改数量、删除后自动更新总价与全选状态，页面不跳转），本质是轻量级 AJAX 风格 |
| **JSTL / EL** | 1.2 | JSP 标准标签库 + 表达式语言，用于在视图层做循环、条件判断、数据回显 |
| **MVC 分层** | — | Controller（Servlet）→ Service → Dao（JDBC）→ Bean（实体），职责清晰 |
| **SQL Server** | 2019+ | 关系型数据库，JDBC 驱动使用 `mssql-jdbc 9.4.1` |
| **MD5** | — | 用户密码入库前做 MD5 散列，不存储明文 |
| **JDK** | 1.8 | 源码/字节码编译级别 1.8 |
| **Tomcat** | 9.x | Servlet 容器，JDK 1.8 配对使用 |
| **Maven** | 3.x | 依赖管理与 WAR 包打包 |
| **IDEA** | Ultimate | 推荐开发工具（内置 Tomcat 部署支持） |

### 关于 JSP + fetch 的组合说明

传统的 JSP 入门项目普遍采用「`<form>` 提交 → Servlet 处理 → `response.sendRedirect` / `forward` 跳转页面」的模式，每次操作都会整页刷新。
本项目在购物车模块使用了 **JSP 负责首次渲染 + fetch 负责后续异步操作** 的方式：
- 页面初次加载时由 JSP + JSTL 把购物车数据一次性渲染好
- 后续的勾选、改数量、删除等操作，由 JS 调用 `fetch()` 向后台 Servlet 发 GET 请求
- Servlet 返回 JSON（`{loggedIn, success, total, cartEmpty, allChecked}` 等字段）
- 前端拿到 JSON 后**只修改需要更新的 DOM 节点**（总价、全选框状态、移除行），不刷新整页

相比纯 form 提交的方式：用户操作更流畅，页面闪屏更少，也更贴近现代前端交互方式，同时又保留了 JSP 渲染首屏的便利性，是入门 JavaEE 向「前后端分离」过渡的自然一步。

---

## 主要功能

| 模块 | 功能点 |
|---|---|
| **用户账号** | 登录、注册、找回密码；密码经 MD5 散列存储；未登录操作购物车自动跳转到登录页 |
| **首页商品** | 全部分类浏览 / 按分类筛选 / 关键字搜索；分页显示（每页 8 条），带页码导航条 |
| **商品分类** | 图书、服装、数码、设备、食品 五大类（测试数据） |
| **购物车** | 加入购物车；删除商品；改数量（含边界校验）；**单项勾选 ↔ 全选框双向联动**；勾选状态实时决定总价；购物车自动保存在 Session 中 |

---

## 项目结构

```
JavaEE-ShopWeb/
├── src/main/java/xx/
│   ├── bean/          # 实体类：Users / Commodity / MyShopCartDate / MyShopCartItemData / HomeData
│   ├── controller/    # Servlet 控制层：Login/Register/Retrieve/Home/ShopCart 五大入口
│   ├── service/       # 业务层：UserService / CommodityService / ShopCartService
│   ├── dao/           # 数据访问层：UserDao / CommodityDao（原生 JDBC + DBUtil）
│   ├── util/          # 工具类：DBUtil（SQL Server 连接池）/ MD5Util
│   └── test/          # 本地测试类
├── src/main/webapp/
│   ├── *.jsp          # JSP 视图：index / login / register / retrieve / home / myshopcart
│   ├── css/           # 页面样式
│   ├── js/            # 前端脚本：home.js / mycart.js / register.js / retrieve.js
│   ├── image/         # 商品图、Logo、背景图等静态资源
│   ├── Test/          # 页面原型备份（改前版本）
│   └── WEB-INF/web.xml
├── pom.xml            # Maven 依赖 & WAR 打包配置
└── README.md
```

---

## 快速运行

> 要求：JDK 1.8、SQL Server、Tomcat 9、IDEA Ultimate

1. **建库建表**：SQL Server 中创建数据库 `shop`，建 `users`（账号）和 `commodity`（商品）两张表，插入若干测试数据（详见 `DBUtil.java` 的表结构约定）。
2. **导入项目**：IDEA 中 `Open` 项目根目录，Maven 会自动下载依赖。
3. **配置 Tomcat**：Run → Edit Configurations → 添加 Tomcat Server → Local，Deployment 选项卡添加 `ZhangXingShop:war exploded`，Application context 填 `/`。
4. **启动**：运行 Tomcat，浏览器访问 `http://localhost:8080/` 进入首页。
5. **测试账号**：`admin / 123456`（或自行注册）。

---

## 说明

本项目为课程练习性质，代码与功能都做了适当简化，适合 Java Web 初学者学习 MVC 分层、Servlet 生命周期、JSP 动态渲染、AJAX 异步交互等核心知识点。请勿直接用于生产环境。
