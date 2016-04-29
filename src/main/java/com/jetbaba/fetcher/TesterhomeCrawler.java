package com.jetbaba.fetcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jetbaba.controller.TesterHomeController;
import com.jetbaba.utils.FileUtil;
import com.jetbaba.utils.StringUtils;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 
 * @author jet
 * testerhome 爬取器
 */
public class TesterhomeCrawler extends WebCrawler{
	
	private FileUtil fUtil = new FileUtil();
	Map<String, Integer> keywordMap = new HashMap<String, Integer>();
	
	/**
	 * 开始到时候，初始化keywordList
	 */
	@Override
	public void onStart() {
		keywordMap.put("移动", 0);
		keywordMap.put("楼主", 0);
		keywordMap.put("招聘", 0);
		keywordMap.put("Android", 0);
		keywordMap.put("iOS", 0);
		keywordMap.put("自动化", 0);
		keywordMap.put("性能", 0);
		keywordMap.put("安全", 0);
		keywordMap.put("功能", 0);
	}
	/**
	 * 爬取所有页面
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL();
		/**
		 * 包含domain的url
		 */
		boolean containsDomain = href.contains("testerhome");
		boolean isVisited = false;
		
		if (containsDomain) {
			/**
			 * 判断是否在visited中
			 */

			String hrefMd5 = StringUtils.md5(href);
			if (TesterHomeController.visted.containsKey(hrefMd5)) {
				if (TesterHomeController.visted.get(hrefMd5) == true) {
					isVisited = true;
				}
			}

		}
				
		return containsDomain && !isVisited;
	}
	
	/**
	 * 记录关键词
	 */
	@Override
	public void visit(Page page) {
		Integer vSize = TesterHomeController.visted.size();
		if(vSize % 100 == 0) {
			System.out.println("已完成 " + vSize);
			System.out.println("当前keyword信息： " + keywordMap.toString());
		}
		/**
		 * 将url的md5存入map
		 */
		TesterHomeController.visted.put(StringUtils.md5(page.getWebURL().getURL()), true);
		
		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		String fetchedContent = htmlParseData.getHtml();
		
		Set<String> keySet = keywordMap.keySet();
		for(String key : keySet)  {
			if(fetchedContent.contains(key)) {
				keywordMap.put(key, keywordMap.get(key) + 1);
			}
		}
	}
	
	/**
	 * 写keywordMap
	 */
	@Override
	public void onBeforeExit() {
		System.out.println("流程结束 结果为 " + keywordMap.toString());
	}
}
