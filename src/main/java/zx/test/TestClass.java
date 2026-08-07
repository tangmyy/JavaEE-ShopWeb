/**
 * 
 */
package zx.test;

import zx.bean.HomeData;
import zx.bean.MyShopCartDate;
import zx.dao.CommodityDao;
import zx.service.ShopCartService;

/**
 * @author TAOER
 *
 */
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyShopCartDate myShopCartDate = new MyShopCartDate(1);
		ShopCartService.dispathServiceType("addToCart", "2", myShopCartDate);
		ShopCartService.dispathServiceType("addToCart", "3", myShopCartDate);
		ShopCartService.dispathServiceType("updateByCheckedBox", "2", myShopCartDate);
		ShopCartService.dispathServiceType("updateAllCheckedBox", "2", myShopCartDate);
//		ShopCartService.dispathServiceType("updateAllCheckedBox", myShopCartDate);
		System.out.println(myShopCartDate);

	}

}
