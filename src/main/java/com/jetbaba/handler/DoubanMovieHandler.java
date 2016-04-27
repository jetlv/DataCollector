package com.jetbaba.handler;

import com.jayway.jsonpath.JsonPath;
import com.jetbaba.entity.DoubanMovie;
import com.jetbaba.entity.StoredEntity;
import com.jetbaba.utils.CustomizedUtils;
import com.jetbaba.utils.HttpRequest;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;

/**
 * 
 * @author jet
 * 
 * 豆瓣处理器
 *
 */
public class DoubanMovieHandler implements Handler{

	/**
	 * 暂时顺序执行看效率
	 */
	public StoredEntity handleCrawlerResult(Page page) {
		String url = page.getWebURL().getURL();
		String idStr = CustomizedUtils.getIdStringFromUrl(url);
		String apiBase = "https://api.douban.com/v2/movie/subject/";
		/**
		 * 发送http请求获取Json
		 */
		String jsonResultBody = HttpRequest.get(apiBase + idStr).body();
		/**
		 * 使用jsonPath解析 并构造实体
		 */
		String jpForId = "$.id";
		Integer mId = JsonPath.read(jsonResultBody, jpForId);
		String jpForTitle = "$.title";
		String mTitle = JsonPath.read(jsonResultBody, jpForTitle);
		String jpForScore = "$.rating.average";
		Double mScore = JsonPath.read(jsonResultBody, jpForScore);
		String jpForCountry = "$.countries";
		String mCountries = JsonPath.read(jsonResultBody, jpForCountry);
		String jpForYear = "$.year";
		String mYear = JsonPath.read(jsonResultBody, jpForYear);
		String jpForViewd = "$.collect_count";
		Integer mViewed = JsonPath.read(jsonResultBody, jpForViewd);
		String jpForWished = "$.wish_count";
		Integer mWished = JsonPath.read(jsonResultBody, jpForWished);
		String jpForPicUrl = "$.images.small";
		String mPicUrl = JsonPath.read(jsonResultBody, jpForPicUrl);
		String jpForUrl = "$.alt";
		String mUrl = JsonPath.read(jsonResultBody, jpForUrl);
		
		DoubanMovie dm = new DoubanMovie(mId, mTitle, mScore, mCountries, mYear, mViewed, mWished, mPicUrl, mUrl);
		System.out.println(dm.toString());
		return dm;
	}
	
	
}
