package zx.service;

import zx.bean.Commodity;
import zx.bean.HomeData;
import zx.dao.CommodityDao;

import java.util.List;


public class CommodityService {

   public static void prepareForHomeData(HomeData homeData){

      if(homeData.getKeyword() == null){
         CommodityDao.selectForHomeBySort(homeData);
      }else {
         // 关键字查询
         String keyword = homeData.getKeyword();
         CommodityDao.selectForHomeByKeyword(homeData,keyword);
      }
      // 计算导航分页范围
      homeData.setFirstPageForNavigation();
      homeData.setLastPageForNavigation();


   }

}
