package com.jetbaba.entity;

/**
 * 
 * @author jet
 * 
 * 豆瓣电影实体 包含电影相关的所有信息
 *
 */
public class DoubanMovie {
	/**
	 * 影片id
	 */
	private Integer id;
	/**
	 * 影片名称
	 */
	private String title;
	/**
	 * 得分
	 */
	private Double rating;
	
	/**
	 * 影片国家
	 */
	private String contries;
	
	/**
	 * 上映年份
	 */
	private String year;
	/**
	 * 多少人看过
	 */
	private Integer viewdNumber;
	/**
	 * 多少人想看
	 */
	private Integer wishedNumber;
	/**
	 * 影片小图地址
	 */
	private String smallPic;
	
	/**
	 * 影片地址
	 */
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getContries() {
		return contries;
	}

	public void setContries(String contries) {
		this.contries = contries;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getViewdNumber() {
		return viewdNumber;
	}

	public void setViewdNumber(Integer viewdNumber) {
		this.viewdNumber = viewdNumber;
	}

	public Integer getWishedNumber() {
		return wishedNumber;
	}

	public void setWishedNumber(Integer wishedNumber) {
		this.wishedNumber = wishedNumber;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @param id
	 * @param title
	 * @param rating
	 * @param contries
	 * @param year
	 * @param viewdNumber
	 * @param wishedNumber
	 * @param smallPic
	 * @param url
	 * 
	 * 全参数构造
	 */
	public DoubanMovie(Integer id, String title, Double rating, String contries, String year, Integer viewdNumber,
			Integer wishedNumber, String smallPic, String url) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.contries = contries;
		this.year = year;
		this.viewdNumber = viewdNumber;
		this.wishedNumber = wishedNumber;
		this.smallPic = smallPic;
		this.url = url;
	}
	
	
}
