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
         // ¹Ø¼ü×Ö²éÑ¯
         String keyword = homeData.getKeyword();
         CommodityDao.selectForHomeByKeyword(homeData,keyword);
      }
      // ÉèÖÃµ¼º½À¸Ò³Âë
      homeData.setFirstPageForNavigation();
      homeData.setLastPageForNavigation();


   }

}
