package com.jetbaba.fetcher;

import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 
 * @author jet
 * 豆瓣电影抓取器。继承自crawler4j，实现父类的两个基本方法
 */
public class DoubanCrawler extends WebCrawler {
	
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");
	
	/**
	 * 遍历的条件
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		System.out.println("href :   " + href);
		/**
		 * 判断是否为movie详情页
		 */
		return !FILTERS.matcher(href).matches() && href.matches("https://movie.douban.com/subject/\\d+/\\?.*");
	}

	/**
	 * 访问时所做到操作
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		String html = htmlParseData.getHtml();
		Document doc = Jsoup.parse(html);
		String rateString = doc.getElementById("interest_sectl").getElementsByAttributeValue("property", "v:average").get(0).text();
		
		if(Double.valueOf(rateString) > 7.5) {
			System.out.println(url + " : " + Double.valueOf(rateString));
		}
	}
}
