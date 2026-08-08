package zx.bean;

import java.util.ArrayList;

public class HomeData {
	// 封装首页数据的关键信息，仅提供这些信息
	private String keyword;					// keyword != null时不参考sort
	private int sort;							// keyword == null : sort==0 全部查询，sort!=0 分类查询
	private int curPage; 					// 由服务器根据用户传递和当前页面信息决定要取的记录数
	private int numPerPage = 8;			// 一页显示的商品数据条数

	private int totalElement;				// 查询返回的数据条数，取决于 keyword sort numPerPage，由dao层实现赋值
	private int totalPages; 				// 查询返回的总页数，取决于 keyword sort


	private int navigationPages = 5;						// 页码导航条数信息
	private int firstPageForNavigation;					// 页码导航条的第一个页码数值
	private int lastPageForNavigation;					// 页码导航条最后一个页码数值，取决于头尾相关计算
	private ArrayList<Commodity> commodityList;		// 查询返回的数据，取决于分页相关计算


	public HomeData() {
		super();
	}

	public HomeData(String keyword, int sort, int curPage) {
		super();
		this.keyword = keyword;
		this.sort = sort;
		this.curPage = curPage;
	}


	public String getKeyword() {							// 关键字		Keyword
		return keyword;
	}
	public int getSort() {										// 分类			Sort
		return sort;
	}
	public int getCurPage() {									// 当前页面		CurPage
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
	public int getNavigationPages() {						// 导航页数	NavigationPages
		return navigationPages;
	}
	public int getFirstPageForNavigation() {				// 导航首页	FirstPageForNavigation
		return firstPageForNavigation;
	}
	public int getLastPageForNavigation() {				// 导航末页	LastPageForNavigation
		return lastPageForNavigation;
	}
	public ArrayList<Commodity> getCommodityList() {	// 商品列表		CommodityList
		return commodityList;
	}


	public void setKeyword(String keyword) {																	// 关键字		Keyword
		this.keyword = keyword;
	}
	public void setSort(int sort) {																				// 分类			Sort
		this.sort = sort;
	}
	public void setCurPage(int curPage) {																		// 当前页面		CurPage
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
	public void setNavigationPages(int navigationPages) {													// 导航页数	NavigationPages
		this.navigationPages = navigationPages;
	}

	public void setFirstPageForNavigation() {																	// 导航首页	FirstPageForNavigation
		if (totalElement == 0)
			this.firstPageForNavigation = 0;
		else if (curPage <= navigationPages/2+1)    // 1 2 3 4 5    23456
			firstPageForNavigation = 1;
		else
			firstPageForNavigation = curPage-navigationPages/2;
	}

	public void setLastPageForNavigation() {																	// 导航末页	LastPageForNavigation
		this.lastPageForNavigation = Math.min(firstPageForNavigation +navigationPages-1,totalPages);
	}

	public void setCommodityList(ArrayList<Commodity> commodityList) {								// 商品列表		CommodityList
		this.commodityList = commodityList;
	}


	@Override // 该方法重写父类的哈希函数，用于将对象存储在哈希集合中，提高查找效率。
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curPage;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + sort;
		return result;
	}

	@Override // 该方法比较两个对象是否相等，默认情况下比较的是对象内存地址，根据需要重写以便比较对象的数据内容。
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

	@Override // 该方法重写对象的字符串表示形式，通过重写该方法可以更方便的方式显示对象数据。
	public String toString() {
		return "HomeData [keyword=" + keyword + ", sort=" + sort + ", curPage=" + curPage + ", totalElement="
				+ totalElement + ", numPerPage=" + numPerPage + ", totalPages=" + totalPages + ", navigationPages="
				+ navigationPages + ", firstPageForNavigation=" + firstPageForNavigation + ", lastPageForNavigation="
				+ lastPageForNavigation + ", commodityList=" + commodityList + "]";
	}

}
