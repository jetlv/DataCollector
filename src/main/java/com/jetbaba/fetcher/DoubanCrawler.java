package com.jetbaba.fetcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jetbaba.handler.Handler;
import com.jetbaba.handler.HandlerFactory;
import com.jetbaba.utils.CustomizedUtils;
import com.jetbaba.utils.Global;
import com.jetbaba.utils.HttpRequest;
import com.jetbaba.utils.StringUtils;

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
//		System.out.println("开始判断 " + href);
		/**
		 * 首次跳转
		 */
//		if(href.equals("https://movie.douban.com/")) {
//			return true;
//		}
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
		if(c % 10 == 0) {
			System.out.println(c);
			System.out.println("开始调整cookie...");
			Header header_userAgent = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
	        Header header_cookie = new BasicHeader("cookie", "ll=\""  + StringUtils.getRandomString(6) + "\"; bid=" + StringUtils.getRandomString(11) + ";");
	        Collection<Header> defaultHeaders = new ArrayList<Header>();
	        defaultHeaders.add(header_userAgent);
	        defaultHeaders.add(header_cookie);
	        this.getMyController().getConfig().setDefaultHeaders(defaultHeaders);
	        System.out.println("Misson Completed");
		}
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
			
			/**
			 * 判断条件中如果有为空的，则不考虑这个筛选，直接设置为true
			 */
			boolean conditionOne = DoubanCrawler.MOVIE_SCORE == ""? true : mScore >= Double.valueOf(DoubanCrawler.MOVIE_SCORE);
			boolean conditionTwo = DoubanCrawler.MOVIE_YEAR == ""? true: Integer.valueOf(mYear) >= Integer.valueOf(DoubanCrawler.MOVIE_YEAR); 
			boolean conditionThree = DoubanCrawler.MOVIE_COUNTRY == "" ? true : mCountries.contains(DoubanCrawler.MOVIE_COUNTRY);
			if(conditionOne&&conditionTwo&&conditionThree) {
				isMeetCondition = true;
			}
			if(isMeetCondition) {
				handler.handleCrawlerResult(page);
			}
	}
	
	@Override
	protected void onUnexpectedStatusCode(String urlStr, int statusCode, String contentType, String description) {
		System.out.println(urlStr + " 返回code " + statusCode + " 描述为： "  + description);
	}
}
