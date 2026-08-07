package zx.bean;

import java.util.ArrayList;

public class HomeData {
	// 购物首页数据的关键信息 必须提供这些信息
	private String keyword;					// keyword != null，则不考虑sort
	private int sort;							// keyword == null : sort==0 不分类查询，sort!=0分类查询
	private int curPage; 					// 从符合条件的所有数据中根据当前页的信息确定取哪几条数据
	private int numPerPage = 8;			// 一页显示的商品数量（记录数）

	private int totalElement;				// 符合条件的所有数据（取决于keyword sort numPerPage，在dao层实现赋值）
	private int totalPages; 				// 符合条件的总页数（取决于keyword sort）
	

	private int navigationPages = 5;						// 页码导航栏信息
	private int firstPageForNavigation;					// 页码导航栏里的第一个页码数值
	private int lastPageForNavigation;					// 页码导航栏里的最后一个页码数值 取决于开头三条参数
	private ArrayList<Commodity> commodityList;		// 符合条件的数据（取决于 开头三条参数）
																	// 代码补齐工具：ctrl+/


	public HomeData() {
		super();
	}

	public HomeData(String keyword, int sort, int curPage) {		// String sort 改 int sort
		super();
		this.keyword = keyword;
		this.sort = sort;
		this.curPage = curPage;
	}


	public String getKeyword() {							// 关键字		Keyword
		return keyword;
	}
	public int getSort() {										// 排序			Sort
		return sort;
	}
	public int getCurPage() {									// 当前页码		CurPage
		return curPage;
	}
	public int getTotalElement() {							// 元素总数		TotalElement
		return totalElement;
	}
	public int getNumPerPage() {								// 每页元素数	NumPerPage
		return numPerPage;
				}
	public int getTotalPages() {								// 总页数		TotalPages
		return totalPages;
	}
	public int getNavigationPages() {						// 导航栏页数	NavigationPages
		return navigationPages;
	}
	public int getFirstPageForNavigation() {				// 导航栏首页	FirstPageForNavigation
		return firstPageForNavigation;
	}
	public int getLastPageForNavigation() {				// 导航栏末页	LastPageForNavigation
		return lastPageForNavigation;
	}
	public ArrayList<Commodity> getCommodityList() {	// 商品列表		CommodityList
		return commodityList;
	}


	public void setKeyword(String keyword) {																	// 关键字		Keyword
		this.keyword = keyword;
	}
	public void setSort(int sort) {																				// 排序			Sort
		this.sort = sort;
	}
	public void setCurPage(int curPage) {																		// 当前页码		CurPage
		this.curPage = curPage;
	}
	public void setTotalElement(int totalElement) {															// 元素总数		TotalElement
		this.totalElement = totalElement;
		setTotalPages();
		setFirstPageForNavigation();
		setLastPageForNavigation();
	}
	public void setNumPerPage(int numPerPage) {																// 每页元素数	NumPerPage
		this.numPerPage = numPerPage;
	}
	public void setTotalPages() {																					// 总页数		TotalPages
		this.totalPages = (totalElement-1)/numPerPage +1;
	}
	public void setNavigationPages(int navigationPages) {													// 导航栏页数	NavigationPages
		this.navigationPages = navigationPages;
	}

	public void setFirstPageForNavigation() {																	// 导航栏首页	FirstPageForNavigation
		if (totalElement == 0)
			this.firstPageForNavigation = 0;
		else if (curPage <= navigationPages/2+1)    // 1 2 3 4 5    23456
			firstPageForNavigation = 1;
		else 
			firstPageForNavigation = curPage-navigationPages/2;
	}

	public void setLastPageForNavigation() {																	// 导航栏末页	LastPageForNavigation
		this.lastPageForNavigation = Math.min(firstPageForNavigation +navigationPages-1,totalPages);
	}

	public void setCommodityList(ArrayList<Commodity> commodityList) {								// 商品列表		CommodityList
		this.commodityList = commodityList;
	}


	@Override //用于返回对象的哈希码值。可以用于将对象存储在哈希表中，以提高查找效率。
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curPage;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + sort;
		return result;
	}

	@Override //用于比较两个对象是否相等。默认情况下，它比较的是对象的内存地址，但可以根据需要重写以比较对象的内容。
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomeData other = (HomeData) obj;
		if (curPage != other.curPage)
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (sort != other.sort)
			return false;
		return true;
	}

	@Override //于返回对象的字符串表示形式。通常重写这个方法，以便以更有意义的方式显示对象的内容。
	public String toString() {
		return "HomeData [keyword=" + keyword + ", sort=" + sort + ", curPage=" + curPage + ", totalElement="
				+ totalElement + ", numPerPage=" + numPerPage + ", totalPages=" + totalPages + ", navigationPages="
				+ navigationPages + ", firstPageForNavigation=" + firstPageForNavigation + ", lastPageForNavigation="
				+ lastPageForNavigation + ", commodityList=" + commodityList + "]";
	}

}
