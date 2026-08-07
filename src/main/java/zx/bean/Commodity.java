package zx.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Commodity {
	private Integer id;		// Integer可以是 null int 0 不区分用整形
	private String description;
	private String imgpath;
	private Integer sort;
	private BigDecimal price;
	private Integer quantity;
	private LocalDateTime createTime;
	private String creator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "\n Commodity{" +
				"id=" + id +
				", description='" + description + '\'' +
				", imgpath='" + imgpath + '\'' +
				", sort=" + sort +
				", Price=" + price +
				", quantity=" + quantity +
				", createTime=" + createTime +
				", creator='" + creator + '\'' +
				'}';
	}

	/**
	 * @param id
	 * @param description
	 * @param imgpath
	 * @param sort
	 * @param price
	 * @param quantity
	 * @param createTime
	 * @param creator
	 */
	public Commodity(Integer id, String description, String imgpath, Integer sort, BigDecimal price, Integer quantity,
						  LocalDateTime createTime, String creator) {
		super();
		this.id = id;
		this.description = description;
		this.imgpath = imgpath;
		this.sort = sort;
		this.price = price;
		this.quantity = quantity;
		this.createTime = createTime;
		this.creator = creator;
	}
	/**
	 *
	 */
	public Commodity() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commodity other = (Commodity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
