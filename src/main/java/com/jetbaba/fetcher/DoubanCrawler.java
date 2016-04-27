package com.jetbaba.fetcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jetbaba.handler.Handler;
import com.jetbaba.handler.HandlerFactory;
import com.jetbaba.utils.CustomizedUtils;
import com.jetbaba.utils.Global;
import com.jetbaba.utils.HttpRequest;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 
 * @author jet
 * 豆瓣电影抓取器。继承自crawler4j，实现父类的两个基本方法
 * 
 * 抓取可选指标：
 * 1. 分数
 * 2. 国家
 * 3. 上映年份
 */
public class DoubanCrawler extends WebCrawler {
	public static Integer c = 0;
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");
	/**
	 * 3个筛选器
	 */
	private static String MOVIE_YEAR = Global.getValueByKey("doubanmovie.year");
	private static String MOVIE_SCORE = Global.getValueByKey("doubanmovie.score");
	private static String MOVIE_COUNTRY = Global.getValueByKey("doubanmovie.country");
	
	/**
	 * 从工厂获取处理器
	 */
	Handler handler = HandlerFactory.getSpecificHandler(1);
	
	
	/**
	 * 遍历的条件 - 判断是否为详情页
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		/**
		 * 是否为详情页
		 */
		boolean isDetailPage = href.matches("https://movie.douban.com/subject/\\d+/\\?.*");
		
		
		/**
		 * 判断是否为movie详情页
		 */
		return !FILTERS.matcher(href).matches() && isDetailPage;
	}

	/**
	 * 访问时所做到操作 -  根据score, year 和 country 判断是否搜集
	 */
	@Override
	public void visit(Page page) {
		c++;
//		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
//		String html = htmlParseData.getHtml();
//		Document doc = Jsoup.parse(html);
//		String rateString = doc.getElementById("interest_sectl").getElementsByAttributeValue("property", "v:average").get(0).text();
//		if(rateString != null && !rateString.equals("")) {
//			
//		}
		String url = page.getWebURL().getURL();
		/**
		 * 是否符合配置文件里面到条件
		 */
		boolean isMeetCondition = false;
			/**
			 * 正则将id数据匹配出来
			 */
			String idStr = CustomizedUtils.getIdStringFromUrl(url);
			/**
			 * 豆瓣查询json的api地址
			 */
			String apiBase = "https://api.douban.com/v2/movie/subject/";
			/**
			 * 发送http请求获取Json
			 */
			String jsonResultBody = HttpRequest.get(apiBase + idStr).body();
			/**
			 * 使用jsonPath解析并对比
			 */
			String jpForScore = "$.rating.average";
			Double mScore = JsonPath.read(jsonResultBody, jpForScore);
			String jpForYear = "$.year";
			String mYear = JsonPath.read(jsonResultBody, jpForYear);
			String jpForCountry = "$.countries";
			String mCountries = JsonPath.read(jsonResultBody, jpForCountry).toString();
			
			if(mScore == Double.valueOf(DoubanCrawler.MOVIE_SCORE)  && mYear.equals(DoubanCrawler.MOVIE_YEAR) && mCountries.contains(DoubanCrawler.MOVIE_COUNTRY)) {
				isMeetCondition = true;
			}
			if(isMeetCondition) {
				handler.handleCrawlerResult(page);
			}
	}
}
