/**
 * 
 */
package zx.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import zx.bean.Commodity;
import zx.bean.HomeData;
import zx.bean.Users;
import zx.util.DBUtil;

/**
 * @author TaoTao
 *
 */
public class CommodityDao {

   // 查询所有 分类别 select ForHomeBySort (homeData)
   public static void selectForHomeBySort(HomeData homeData){
      Connection conn = DBUtil.getConn();
      String sql = null;
      if (homeData.getSort() == 0)
         sql = "select  * from (select ROW_NUMBER() over(order by create_time desc)as line,* from commodity) commodity";
      else
         sql = "select  * from (select ROW_NUMBER() over(order by create_time desc)as line,* from commodity where sort=?) commodity";
      selectForHomeTotalElement(homeData, sql);

      String sqlCurPage = sql + " where line>=? and line<=?";

      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         ps = conn.prepareStatement(sqlCurPage);         // 产生数据库语句执行的载体
         if(homeData.getSort() == 0){
            ps.setInt( 1, (homeData.getCurPage()-1) *homeData.getNumPerPage()+1 );
            ps.setInt( 2, homeData.getCurPage() *homeData.getNumPerPage() );
         }else {
            ps.setInt( 1, homeData.getSort() );
            ps.setInt( 2, (homeData.getCurPage()-1) *homeData.getNumPerPage()+1 );
            ps.setInt( 3, homeData.getCurPage() *homeData.getNumPerPage() );
         }
         rs = ps.executeQuery();	 // 真正执行查询操作
         ArrayList<Commodity> commodityList = new ArrayList<Commodity>();
         while(rs.next()){
            int id = rs.getInt("id");	// 字段名和数字的区别？
            String description = rs.getString("description");
            String imgpath = rs.getString("imgpath");
            int sort = rs.getInt("sort");
            BigDecimal price = rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            LocalDateTime createTime = rs.getTimestamp("create_time").toLocalDateTime();
            String creator = rs.getString("creator");

            Commodity commodity = new Commodity(id, description, imgpath, sort, price, quantity, createTime, creator);
            commodityList.add(commodity);
         }
         homeData.setCommodityList(commodityList);
         System.out.println(homeData);
      } catch (SQLException e) {
         e.printStackTrace();	// 不要删，出了问题需要在控制台找出原因
      }finally {
         DBUtil.closeDBResource(rs, ps, conn);
      }
   }


   // 关键字查询 select For Home ByKeyword (homeData,  keyword)
   public static void selectForHomeByKeyword(HomeData homeData, String keyword) {
      Connection conn = DBUtil.getConn();
      PreparedStatement ps = null;
      ResultSet rs = null;

      String  sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY create_time DESC) AS line, * FROM commodity WHERE description LIKE ?) AS commodity";
      String  sqlCurPage = sql + " WHERE line >= ? AND line <= ?";
      selectForHomeTotalElement(homeData, sql, keyword);

      try {
         ps = conn.prepareStatement(sqlCurPage);       // 产生数据库语句执行的载体
                                                                     //  为什么是 sort==0？？？？

            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, (homeData.getCurPage() - 1) * homeData.getNumPerPage() + 1);
            ps.setInt(3, homeData.getCurPage() * homeData.getNumPerPage());

         rs = ps.executeQuery();    // 真正执行查询操作
         ArrayList<Commodity> commodityList = new ArrayList<>();
         while (rs.next()) {
            Integer id = rs.getInt(1);
            String description = rs.getString("description");
            String imgpath = rs.getString("imgpath");
            Integer sort = rs.getInt("sort");
            BigDecimal price = rs.getBigDecimal("price");
            Integer quantity = rs.getInt("quantity");
            LocalDateTime createTime = rs.getTimestamp("create_time").toLocalDateTime();
            String creator = rs.getString("creator");

            Commodity commodity = new Commodity(id, description, imgpath, sort, price, quantity, createTime, creator);
            commodityList.add(commodity);
         }

         homeData.setCommodityList(commodityList);
         System.out.println(homeData);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBUtil.closeDBResource(rs, ps, conn);
      }
   }


   // 俩参数select For Home TotalElement
   public static void selectForHomeTotalElement(HomeData homeData, String sql){
      Connection conn = DBUtil.getConn();
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         ps = conn.prepareStatement(sql);         // 产生数据库语句执行的载体
         if (homeData.getSort() != 0) {           // sort 不为0 分类查询
            ps.setInt(1, homeData.getSort());
         }
         rs=ps.executeQuery();	                  // 真正执行查询操作
         int total = 0;
         while (rs.next()) {
            total++;
         }
         homeData.setTotalElement(total);
      }catch (SQLException e){
         e.printStackTrace();
      }finally {
         DBUtil.closeDBResource(rs, ps, conn);
      }
   }

   // 仨参数select For Home TotalElement
   public static void selectForHomeTotalElement(HomeData homeData, String sql, String keyword) {
      Connection conn = DBUtil.getConn();
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         ps = conn.prepareStatement(sql);         // 产生数据库语句执行的载体
         if (homeData.getSort() != 0) {           // sort 为0 不进行分类查询
            ps.setString(1, "%" + keyword + "%");
         }
         rs = ps.executeQuery();                 // 真正执行查询操作
         int total = 0;
         while (rs.next()) {
            total++;
         }
         homeData.setTotalElement(total);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBUtil.closeDBResource(rs, ps, conn);
      }

   }

   // select Commodity ById     // Commodity类型的方法啊!?
   public static Commodity selectCommodityById(int CommodityId){
      // 产生数据库的连接
      Connection conn = DBUtil.getConn();
      // 准备sql语句
      String sql = "select * from commodity where id=?";

      PreparedStatement ps = null;
      ResultSet rs = null;
      Commodity commodity = null;
      try {
         ps = conn.prepareStatement(sql);          // 产生数据库语句执行的载体
         ps.setInt(1, CommodityId);
         rs = ps.executeQuery();	                  // 真正执行查询操作

         ArrayList<Commodity> commodityList = new ArrayList<Commodity>();
         while(rs.next()){
            int id = rs.getInt("id");	// 字段名和数字的区别？
            String description = rs.getString("description");
            String imgpath = rs.getString("imgpath");
            int sort = rs.getInt("sort");
            BigDecimal price = rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            LocalDateTime createTime = rs.getTimestamp("create_time").toLocalDateTime();
            String creator = rs.getString("creator");

            commodity = new Commodity(id, description, imgpath, sort, price, quantity, createTime, creator);
         }
      } catch (SQLException e) {
         e.printStackTrace();	// 不要删，出了问题需要在控制台找出原因
      }finally {
         DBUtil.closeDBResource(rs, ps, conn);
      }

      return commodity;
   }
}

