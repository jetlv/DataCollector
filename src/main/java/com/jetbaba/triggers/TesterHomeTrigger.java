package com.jetbaba.triggers;

import java.util.ArrayList;
import java.util.List;

import com.jetbaba.anntations.LaunchId;
import com.jetbaba.controller.BaseController;
import com.jetbaba.controller.TesterHomeController;
import com.jetbaba.utils.FileUtil;
import com.jetbaba.utils.Global;
import com.jetbaba.utils.LogUtils;
import com.jetbaba.utils.StringUtils;


@LaunchId(id = "3")
public class TesterHomeTrigger implements Trigger {

	public void configure() {
		System.out.println("读取配置...");
		String folder = Global.getValueByKey("testerhome.folder");
		String tCount = Global.getValueByKey("testerhome.threadcount");
		String seeds = Global.getValueByKey("testerhome.seeds");
		String ifLoad = Global.getValueByKey("testerhome.ifloadvisited");
		System.out.println("读取配置完毕");
		System.out.println(" 存储位置 : " +  folder);
		System.out.println(" 线程数量 : " +  tCount);
		System.out.println(" 初始链接: " +  seeds);
		List<String> seedsList = new ArrayList<String>();
		for(int i=0 ; i < seeds.split(",").length; i++) {
			seedsList.add(seeds.split(",")[i]);
		}
		BaseController con = new TesterHomeController(folder, Integer.valueOf(tCount), seedsList);
		System.out.println(" 是否加载Map: " +  ("1".equals(ifLoad) ? "是" : "否"));
		if("1".equals(ifLoad)) {
			con.loadVisitedMap();
		} else {
			con.initMap();
		}
		try { 
		con.startCrawler();
		System.out.println("将全新的Map序列化到磁盘...");
		if(new FileUtil().deleteFile(TesterHomeController.VISITED_PATH)) {
			System.out.println("原文件删除成功...");
		} else {
			System.out.println("删除失败...");
		}
		StringUtils.saveMap(TesterHomeController.visted, TesterHomeController.VISITED_PATH);
		System.out.println("新文件成功写入...");
		System.out.println("结束任务 Testerhome ...");
		} catch(Exception e) {
			LogUtils.log(e.getMessage());
		}
	}

	public void start() {
		
	}
	
}
