package com.jetbaba.entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jetbaba.anntations.LaunchId;
import com.jetbaba.controller.BaseController;
import com.jetbaba.controller.DoubanController;
import com.jetbaba.controller.MilanooController;
import com.jetbaba.controller.TesterHomeController;
import com.jetbaba.fetcher.DoubanCrawler;
import com.jetbaba.utils.FileUtil;
import com.jetbaba.utils.Global;
import com.jetbaba.utils.StringUtils;
import com.milanoo.triggers.Trigger;

/**
 * 
 * @author jet
 *
 * 入口程序
 */
public class Launcher {
	
	public static void main(String[] args) throws Exception {
		
		String entries = Global.getValueByKey("linelist");
		String[] entriesArray = entries.split(",");
		for(String e : entriesArray) {
			System.out.println(e);
		}
		
		InputStream is = System.in;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String command = reader.readLine();
		
		if("#".equals(command)) {
			System.out.println("程序退出...");
			return;
			/**
			 * 启动DoubanMovie
			 */
		} else {
			boolean match = false;
			String triggers = Global.getValueByKey("triggers");
			String[] triggersArray = triggers.split(",");
			for(String str : triggersArray) {
				Class clazz = Class.forName(Trigger.class.getPackage().getName() + "." + str);
				LaunchId l = (LaunchId) clazz.getAnnotation(LaunchId.class);
				if(command.equals(l.id())) {
					match = true;
					Trigger inst = (Trigger) clazz.getConstructor().newInstance();
					inst.configure();
				}
			}
			if(!match) {
				System.out.println("Dosen't match any task, program will exit");
				return;
			}
		}
	}
}
