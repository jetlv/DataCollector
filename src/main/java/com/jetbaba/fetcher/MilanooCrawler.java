package com.jetbaba.fetcher;

import com.jetbaba.controller.MilanooController;
import com.jetbaba.utils.FileUtil;
import com.jetbaba.utils.StringUtils;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 
 * @author jet 米兰网爬虫
 */
public class MilanooCrawler extends WebCrawler {

	private FileUtil fUtil = new FileUtil();

	/**
	 * 判断条件 - 是否为milanoo.com到链接，或者米兰图片服务器的链接
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL();

		/**
		 * 米兰域名下的地址，以及mlo.me图片下的地址
		 */
		boolean isMeetCondition = href.contains("milanoo") || href.contains("mlo.me");
		boolean isVisited = false;
		if (isMeetCondition) {
			/**
			 * 判断是否在visited中
			 */

			String hrefMd5 = StringUtils.md5(href);
			if (MilanooController.visted.containsKey(hrefMd5)) {
				if (MilanooController.visted.get(hrefMd5) == true) {
					isVisited = true;
				}
			}

		}

		return isMeetCondition && !isVisited;
	}

	/**
	 * 查出大于400的code，记录到文件
	 */
	@Override
	public void visit(Page page) {
		if (MilanooController.visted.size() % 100 == 0) {
			System.out.println(MilanooController.visted.size() + "个已执行");
		}
		/**
		 * 将url的md5存入map
		 */
		MilanooController.visted.put(StringUtils.md5(page.getWebURL().getURL()), true);
		
		Integer code = Integer.valueOf(page.getStatusCode());
		String url = page.getWebURL().getURL();

		if (code >= 400) {
			String toRecord = url + " : " + code;
			fUtil.WriteFile(toRecord + "\r\n", "output/", "milanoo_result.txt", "utf-8");
			System.out.println(toRecord);
		}
	}
}
