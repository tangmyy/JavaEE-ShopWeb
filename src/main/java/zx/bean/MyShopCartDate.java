package zx.bean;

import java.math.BigDecimal;
import java.util.HashMap;


public class MyShopCartDate {
   private int userID;
   private boolean allChecked;
   private HashMap <String, MyShopCartItemData> cartList; // 是购物车中商品项的集合 key-value commodityID-MyShopCartItemData
   private BigDecimal total;     // 购物车总价

   public MyShopCartDate(int userID) {
      this.userID = userID;
      this.allChecked = true;
      this.cartList = new HashMap<String, MyShopCartItemData>();   //List初始化， 目前元素个数为0 但cartList不是Null
      this.total = new BigDecimal(0);
   }

   // 增加
   public void add(Commodity commodity, int num){
      MyShopCartItemData myShopCartItemData = new MyShopCartItemData(true, commodity, num);     // 把 1 改为 num
      cartList.put(commodity.getId()+"", myShopCartItemData);   // Sting.valueOf(commodity.getID()) 啥意思？？？
      setTotal(total.add(commodity.getPrice() ));
   }

   // 删除
   public void remove(String commodityID) {
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);      //得到要移除的项目
      // 如果商品项被选中 就改变总计
      if(myShopCartItemData.getChecked()) {
         setTotal(getTotal().subtract(myShopCartItemData.getSubtotal()));
      }
      // 注意：不在if范围内
      cartList.remove(commodityID);
   }
   // 改变
   public void updateByNum(String commodityID, int changeNum) {            // changNum的含义是变化的数量，不是变化后的数量
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
      int newNum = myShopCartItemData.getNum() + changeNum;
      // 如果新的数量小于等于0，则不进行更新
      if (newNum <= 0) {
         return;
      } else{
         myShopCartItemData.setNum(newNum);   // 更新数量
         myShopCartItemData.setSubtotal();    // 更新总计
      }
      // 如果商品项被选中 才改变总计
      if (myShopCartItemData.getChecked()) {
         setTotal(getTotal().add(myShopCartItemData.getCommodity().getPrice().multiply(new BigDecimal(changeNum))));
      }
   }

   // 单选盒子
   public void updateByCheckedBox(String commodityID) {
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
      myShopCartItemData.setChecked(!myShopCartItemData.getChecked());
      if(myShopCartItemData.getChecked()) {
         setTotal(total.add(myShopCartItemData.getSubtotal()) );
      }else {
         setTotal(total.subtract(myShopCartItemData.getSubtotal()) );
      }
   }
   // 全选盒子
   public void updateAllCheckedBox(boolean isChecked) {
      allChecked = isChecked;
      BigDecimal newTotal = BigDecimal.ZERO;
      for (MyShopCartItemData myShopCartItemData : cartList.values()) {
         myShopCartItemData.setChecked(allChecked);
         if (allChecked) {
            newTotal = newTotal.add(myShopCartItemData.getSubtotal());
         }
      }
      if (!allChecked) {
         newTotal = BigDecimal.ZERO;
      }
      setTotal(newTotal);
   }



   public void setUserID(int userID) {
      this.userID = userID;
   }

   public boolean isAllChecked() {
      return allChecked;
   }

   public void setAllChecked(boolean allChecked) {
      this.allChecked = allChecked;
   }

   public HashMap<String, MyShopCartItemData> getCartList() {
      return cartList;
   }

   public void setCartList(HashMap<String, MyShopCartItemData> cartList) {
      this.cartList = cartList;
   }

   public BigDecimal getTotal() {
      return total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   @Override
   public String toString() {
      return "MyShopCartDate{" +
            "userID=" + userID +
            ", allChecked=" + allChecked +
            ", cartList=" + cartList +
            ", total=" + total +
            '}';
   }
}


//// 删除
//public void remove(String commodityID) {
//   MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
//   if(myShopCartItemData != null && myShopCartItemData.getChecked()) {
//      setTotal(getTotal().subtract(myShopCartItemData.getSubtotal()));
//   }
//   cartList.remove(commodityID);
//   if (total.compareTo(BigDecimal.ZERO) < 0) {
//      setTotal(BigDecimal.ZERO);
//   }
//}
//// 改变
//public void updateByNum(String commodityID, int changeNum) {
//   MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
//   int newNum = myShopCartItemData.getNum() + changeNum;
//   // 如果新的数量小于等于0，则不进行更新
//   if (newNum <= 0) {
//      return;
//   } else {
//      myShopCartItemData.setNum(newNum);
//      myShopCartItemData.setSubtotal();
//   }
//   // 如果商品项被选中 才改变总计
//   if (myShopCartItemData.getChecked()) {
//      setTotal(getTotal().add(myShopCartItemData.getCommodity().getPrice().multiply(new BigDecimal(changeNum))));
//      if (total.compareTo(BigDecimal.ZERO) < 0) {
//         setTotal(BigDecimal.ZERO);
//      }
//   }
//}
//public void updateAllCheckedBox(boolean isChecked) {
//   BigDecimal newTotal = BigDecimal.ZERO;
//   for (MyShopCartItemData myShopCartItemData : cartList.values()) {
//      myShopCartItemData.setChecked(isChecked);
//      if (isChecked) {
//         newTotal = newTotal.add(myShopCartItemData.getSubtotal());
//      }
//   }
//   setTotal(newTotal);
//}
//public void updateByCheckedBox(String commodityId) {
//   MyShopCartItemData myShopCartItemData = cartList.get(commodityId);
//   if (myShopCartItemData != null) {
//      boolean wasChecked = myShopCartItemData.getChecked();
//      myShopCartItemData.setChecked(!wasChecked);
//      if (myShopCartItemData.getChecked()) {
//         setTotal(total.add(myShopCartItemData.getSubtotal()));
//      } else {
//         setTotal(total.subtract(myShopCartItemData.getSubtotal()));
//         if (total.compareTo(BigDecimal.ZERO) < 0) {
//            setTotal(BigDecimal.ZERO);
//         }
//      }
//   }
//}

// 同步全选
//   public void updateAllCheckedBox() {
//      allChecked = !allChecked; // 切换全选状态
//      BigDecimal newTotal = BigDecimal.ZERO; // 用于计算新的总价
//      for (MyShopCartItemData myShopCartItemData : cartList.values()) {
//         // 更新每个商品项的选中状态
//         myShopCartItemData.setChecked(allChecked);
//         // 如果选择了所有商品，则累加其小计到总价
//         if (allChecked) {
//            newTotal = newTotal.add(myShopCartItemData.getSubtotal());
//         }
//      }
//      setTotal(newTotal);    // 设置新的总价
//   }
//            case "updateAllCheckedBox":
//   updateAllCheckedBox(myShopCartDate);
//         break;
//   public static void updateAllCheckedBox(MyShopCartDate myShopCartDate) {
//      myShopCartDate.updateAllCheckedBox();
//   }
//function updateAllCheckedBox(commodityId){
//   location.href = "ShopCartController?commodityId=" +commodityId +"&serviceType=updateAllCheckedBox";
//}
//
//function updateByCheckedBox(commodityId){
//   location.href = "ShopCartController?commodityId=" +commodityId +"&serviceType=updateByCheckedBox"
//}

