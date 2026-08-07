package zx.service;

import com.sun.media.sound.RIFFInvalidFormatException;
import zx.bean.Commodity;
import zx.bean.HomeData;
import zx.bean.MyShopCartDate;
import zx.bean.MyShopCartItemData;
import zx.dao.CommodityDao;

public class ShopCartService {
   public static void dispathServiceType(String serviceType, String commodityId, MyShopCartDate myShopCartDate){  // what is serviceType?
      switch (serviceType){
         case "addToCart":
            addToCart(commodityId, myShopCartDate);
            break;
         case "updateByCheckedBox":
            updateByCheckedBox(commodityId, myShopCartDate);
            break;
         case "updateAllCheckedBox":
            boolean isChecked = Boolean.parseBoolean(commodityId); // 将 commodityId 解释为全选状态
            updateAllCheckedBox(myShopCartDate, isChecked);
         break;
         case "remove":
            remove(commodityId, myShopCartDate);
            break;
         default:             // 如果没有匹配的case则执行：
            break;
      }
   }

   public static void dispathServiceType(String serviceType, String commodityId, MyShopCartDate myShopCartDate, int changeNum) {
      if(serviceType.equals("updateByNum")){
         updateByNum(commodityId, myShopCartDate, changeNum);
      }
   }

   private static void updateByCheckedBox(String commodityId, MyShopCartDate myShopCartDate) {
      myShopCartDate.updateByCheckedBox(commodityId);
   }

   public static void updateAllCheckedBox(MyShopCartDate myShopCartDate, boolean isChecked) {
      myShopCartDate.updateAllCheckedBox(isChecked);
   }



   private static void remove(String commodityId, MyShopCartDate myShopCartDate) {
      myShopCartDate.remove(commodityId);
   }
   private static void updateByNum(String commodityId, MyShopCartDate myShopCartDate, int changeNum) {
         myShopCartDate.updateByNum(commodityId, changeNum);
   }


   public static void addToCart(String commodityId, MyShopCartDate myShopCartDate){
      if(myShopCartDate.getCartList().containsKey(commodityId)){     // 当前购物车有此商品 更新数量并选中
         // 先把当前项保存，需要使用数据
         MyShopCartItemData myShopCartItemData = myShopCartDate.getCartList().get(commodityId);
         myShopCartDate.remove(commodityId);       // remove内已经实现更新总计
         myShopCartDate.add(myShopCartItemData.getCommodity(),myShopCartItemData.getNum()+1);
      }else{
         // 先准备 Commodity 对象，通过commodityDao层查询数据来得到数据
         Commodity commodity = CommodityDao.selectCommodityById(Integer.parseInt(commodityId));
         myShopCartDate.add(commodity, 1);
      }
   }


}
