package zx.bean;

import java.math.BigDecimal;
import java.util.HashMap;


public class MyShopCartDate {
   private int userID;
   private boolean allChecked;
   private HashMap <String, MyShopCartItemData> cartList; // �ǹ��ﳵ����Ʒ��ļ��� key-value commodityID-MyShopCartItemData
   private BigDecimal total;     // ���ﳵ�ܼ�

   public MyShopCartDate(int userID) {
      this.userID = userID;
      this.allChecked = true;
      this.cartList = new HashMap<String, MyShopCartItemData>();   //List��ʼ���� ĿǰԪ�ظ���Ϊ0 ��cartList����Null
      this.total = new BigDecimal(0);
   }

   // ����
   public void add(Commodity commodity, int num){
      MyShopCartItemData myShopCartItemData = new MyShopCartItemData(true, commodity, num);     // �� 1 ��Ϊ num
      cartList.put(commodity.getId()+"", myShopCartItemData);   // Sting.valueOf(commodity.getID()) ɶ��˼������
      setTotal(total.add(commodity.getPrice() ));
   }

   // ɾ��
   public void remove(String commodityID) {
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);      //�õ�Ҫ�Ƴ�����Ŀ
      // �����Ʒ�ѡ�� �͸ı��ܼ�
      if(myShopCartItemData.getChecked()) {
         setTotal(getTotal().subtract(myShopCartItemData.getSubtotal()));
      }
      // ע�⣺����if��Χ��
      cartList.remove(commodityID);
   }
   // �ı�
   public void updateByNum(String commodityID, int changeNum) {            // changNum�ĺ����Ǳ仯�����������Ǳ仯�������
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
      int newNum = myShopCartItemData.getNum() + changeNum;
      // ����µ�����С�ڵ���0���򲻽��и���
      if (newNum <= 0) {
         return;
      } else{
         myShopCartItemData.setNum(newNum);   // ��������
         myShopCartItemData.setSubtotal();    // �����ܼ�
      }
      // �����Ʒ�ѡ�� �Ÿı��ܼ�
      if (myShopCartItemData.getChecked()) {
         setTotal(getTotal().add(myShopCartItemData.getCommodity().getPrice().multiply(new BigDecimal(changeNum))));
      }
   }

   // ��ѡ����
   public void updateByCheckedBox(String commodityID) {
      MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
      myShopCartItemData.setChecked(!myShopCartItemData.getChecked());
      if(myShopCartItemData.getChecked()) {
         setTotal(total.add(myShopCartItemData.getSubtotal()) );
      }else {
         setTotal(total.subtract(myShopCartItemData.getSubtotal()) );
      }
   }
   // ȫѡ����
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


//// ɾ��
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
//// �ı�
//public void updateByNum(String commodityID, int changeNum) {
//   MyShopCartItemData myShopCartItemData = cartList.get(commodityID);
//   int newNum = myShopCartItemData.getNum() + changeNum;
//   // ����µ�����С�ڵ���0���򲻽��и���
//   if (newNum <= 0) {
//      return;
//   } else {
//      myShopCartItemData.setNum(newNum);
//      myShopCartItemData.setSubtotal();
//   }
//   // �����Ʒ�ѡ�� �Ÿı��ܼ�
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

// ͬ��ȫѡ
//   public void updateAllCheckedBox() {
//      allChecked = !allChecked; // �л�ȫѡ״̬
//      BigDecimal newTotal = BigDecimal.ZERO; // ���ڼ����µ��ܼ�
//      for (MyShopCartItemData myShopCartItemData : cartList.values()) {
//         // ����ÿ����Ʒ���ѡ��״̬
//         myShopCartItemData.setChecked(allChecked);
//         // ���ѡ����������Ʒ�����ۼ���С�Ƶ��ܼ�
//         if (allChecked) {
//            newTotal = newTotal.add(myShopCartItemData.getSubtotal());
//         }
//      }
//      setTotal(newTotal);    // �����µ��ܼ�
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

