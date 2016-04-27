package com.jetbaba.handler;

import com.jetbaba.entity.StoredEntity;

import edu.uci.ics.crawler4j.crawler.Page;

/**
 * 
 * @author jet
 *
 * 处理器接口
 */
public interface Handler {
	/**
	 * 
	 * @return
	 * 
	 * 处理结果，封装为StoredEntity
	 */
	public StoredEntity handleCrawlerResult(Page page);
	
}
