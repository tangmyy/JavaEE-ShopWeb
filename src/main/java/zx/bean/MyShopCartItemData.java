package zx.bean;

import java.math.BigDecimal;

public class MyShopCartItemData {
   private boolean checked;   // 表示当前商品在购物车中的选中状态 决定是否要加入统计
   private Commodity commodity;
   private int num;  // 当前购物车内该商品的数量
   private BigDecimal subtotal;   // 当前购物车内商品项的小计

   public MyShopCartItemData(boolean checked, Commodity commodity, int num) {
      this.checked = checked;
      this.commodity = commodity;
      this.num = num;
      setSubtotal();
   }

   public boolean getChecked() {
      return checked;
   }

   public void setChecked(boolean checked) {
      this.checked = checked;
   }

   public Commodity getCommodity() {
      return commodity;
   }

   public void setCommodity(Commodity commodity) {
      this.commodity = commodity;
   }

   public int getNum() {
      return num;
   }

   public void setNum(int num) {
      this.num = num;
   }

   public BigDecimal getSubtotal() {
      return subtotal;
   }

   public void setSubtotal() {
      this.subtotal = commodity.getPrice().multiply(new BigDecimal(num));
   }

   @Override
   public String toString() {
      return "\n MyShopCartItemData{" +
            "checked=" + checked +
            ", commodity=" + commodity +
            ", num=" + num +
            ", subtotal=" + subtotal +
            '}';
   }



}
