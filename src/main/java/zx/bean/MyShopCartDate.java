package zx.bean;

import java.math.BigDecimal;
import java.util.HashMap;


public class MyShopCartDate {
   private int userID;
   private boolean allChecked;
   private HashMap <String, MyShopCartItemData> cartList; // 购物车商品集合，key-value 为 commodityID-MyShopCartItemData
   private BigDecimal total;     // 购物车总价

   public MyShopCartDate(int userID) {
      this.userID = userID;
      this.allChecked = true;
      this.cartList = new HashMap<String, MyShopCartItemData>();   // 初始化集合，当前元素个数为0，cartList不为Null
      this.total = new BigDecimal(0);
   }

   // 添加商品
   public void add(Commodity commodity, int num){
      MyShopCartItemData myShopCartItemData = new MyShopCartItemData(allChecked, commodity, num);
      cartList.put(commodity.getId()+"", myShopCartItemData);   // String.valueOf(commodity.getID()) 效果一样，拼接更直接
      if (myShopCartItemData.getChecked()) {
         setTotal(total.add(commodity.getPrice()));
      }
      refreshAllChecked();   // 遍历cartList中所有商品的checked状态，重新计算并设置allChecked
   }

   // 删除商品
   public void remove(String commodityID) {
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);      // 获取要移除的商品项
      // 如果商品已被选中，就更新总价
      if(myShopCartItemData.getChecked()) {
         setTotal(getTotal().subtract(myShopCartItemData.getSubtotal()));
      }
      // 注意：删除不区分if范围
      cartList.remove(commodityID);
      refreshAllChecked();   // 遍历cartList中所有商品的checked状态，重新计算并设置allChecked
   }
   // 修改数量
   public void updateByNum(String commodityID, int changeNum) {            // changeNum的含义是变化数量，可以是正数也可以是负数
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
      int newNum = myShopCartItemData.getNum() + changeNum;
      // 如果新数量小于等于0，则不进行更新
      if (newNum <= 0) {
         return;
      } else{
         myShopCartItemData.setNum(newNum);   // 更新数量
         myShopCartItemData.setSubtotal();    // 更新小计
      }
      // 如果商品已被选中，才更新总价
      if (myShopCartItemData.getChecked()) {
         setTotal(getTotal().add(myShopCartItemData.getCommodity().getPrice().multiply(new BigDecimal(changeNum))));
      }
   }

   // 单选框更新
   public void updateByCheckedBox(String commodityID) {
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
      myShopCartItemData.setChecked(!myShopCartItemData.getChecked());
      if(myShopCartItemData.getChecked()) {
         setTotal(total.add(myShopCartItemData.getSubtotal()) );
      }else {
         setTotal(total.subtract(myShopCartItemData.getSubtotal()) );
      }
      refreshAllChecked();   // 遍历cartList中所有商品的checked状态，重新计算并设置allChecked
   }
   // 全选框更新
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

   // 根据 cartList 中所有商品的 checked 状态，重新计算并设置 allChecked
   private void refreshAllChecked() {
      // 如果购物车为空，allChecked 保持 true 或 false 都无所谓，这里默认 true（和构造器一致）
      if (cartList.isEmpty()) {
         allChecked = true;
         return;
      }
      // 遍历所有商品，只要有一个没选中，allChecked 就是 false
      boolean all = true;
      for (MyShopCartItemData item : cartList.values()) {
         if (!item.getChecked()) {
            all = false;
            break;
         }
      }
      allChecked = all;
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

