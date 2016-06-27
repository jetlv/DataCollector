package com.milanoo.triggers;

import java.util.ArrayList;
import java.util.List;

import com.jetbaba.anntations.LaunchId;
import com.jetbaba.controller.DoubanController;
import com.jetbaba.fetcher.DoubanCrawler;
import com.jetbaba.utils.LogUtils;

@LaunchId(id = "1")
public class DoubanMovieTrigger implements Trigger {

	public void configure() {
		try {
			System.out.println("开始任务 DoubanMovie...");
			List<String> seeds = new ArrayList<String>();
			seeds.add("https://movie.douban.com");
			new DoubanController(
					"home/jet/crawlerResult",
					7,
					"Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0",
					"ll=\"118318\"; bid=JyJR5WfD3/g;", seeds,
					DoubanCrawler.class).startCrawler();
			System.out.println("结束任务 DoubanMovie...");
		} catch (Exception e) {
			LogUtils.log(e.getMessage());
		}

	}

	public void start() {

	}

}
