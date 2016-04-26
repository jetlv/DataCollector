package Milanoo.DataCollector;

import com.jetbaba.utils.HttpRequest;

public class ClientTrigger {
	public static void main(String[] args) {
		String body = HttpRequest.post("http://www.baidu.com").body();
		
		System.out.println(body);
		
		
	}
}
