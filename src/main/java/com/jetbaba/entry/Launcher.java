package com.jetbaba.entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jetbaba.controller.BaseController;
import com.jetbaba.controller.DoubanController;
import com.jetbaba.controller.MilanooController;
import com.jetbaba.fetcher.DoubanCrawler;
import com.jetbaba.utils.FileUtil;
import com.jetbaba.utils.Global;
import com.jetbaba.utils.StringUtils;

/**
 * 
 * @author jet
 *
 * 入口程序
 */
public class Launcher {
	
	public static void main(String[] args) throws Exception {
		System.out.println("请输入task: ");
		System.out.println("1:DoubanMoive");
		System.out.println("2:Milanoo");
		System.out.println("#: 退出程序");
		
		InputStream is = System.in;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String command = reader.readLine();
		if("#".equals(command)) {
			System.out.println("程序退出...");
			return;
			/**
			 * 启动DoubanMovie
			 */
		} else if ("1".equals(command)) {
			System.out.println("开始任务 DoubanMovie...");
			List<String> seeds = new ArrayList<String>();
			seeds.add("https://movie.douban.com");
			new DoubanController("home/jet/crawlerResult", 7, "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0", "ll=\"118318\"; bid=JyJR5WfD3/g;", seeds, DoubanCrawler.class).startCrawler();
			System.out.println("结束任务 DoubanMovie...");
			/**
			 * 启动Milanoo crawler
			 */
		} else if("2".equals(command)) {
			System.out.println("读取配置...");
			String folder = Global.getValueByKey("milanoo.folder");
			String tCount = Global.getValueByKey("milanoo.threadcount");
			String seeds = Global.getValueByKey("milanoo.seeds");
			String ifLoad = Global.getValueByKey("milanoo.ifloadvisited");
			System.out.println("读取配置完毕");
			System.out.println(" 存储位置 : " +  folder);
			System.out.println(" 线程数量 : " +  tCount);
			System.out.println(" 初始链接: " +  seeds);
			List<String> seedsList = new ArrayList<String>();
			for(int i=0 ; i < seeds.split(",").length; i++) {
				seedsList.add(seeds.split(",")[i]);
			}
			BaseController con = new MilanooController(folder, Integer.valueOf(tCount), seedsList);
			System.out.println(" 是否加载Map: " +  ("1".equals(ifLoad) ? "是" : "否"));
			if("1".equals(ifLoad)) {
				con.loadVisitedMap();
			} else {
				con.initMap();
			}
			con.startCrawler();
			System.out.println("将全新的Map序列化到磁盘...");
			if(new FileUtil().deleteFile(MilanooController.VISITED_PATH)) {
				System.out.println("原文件删除成功...");
			} else {
				System.out.println("删除失败...");
			}
			StringUtils.saveMap(MilanooController.visted, MilanooController.VISITED_PATH);
			System.out.println("新文件成功写入...");
			System.out.println("结束任务 Milanoo ...");
		} else {
			System.out.println("程序退出...");
			return;
		}
	}
}
