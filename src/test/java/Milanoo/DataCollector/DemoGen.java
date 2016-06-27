package Milanoo.DataCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jetbaba.utils.HttpRequest;

public class DemoGen {
	public static void dd(String[] args) {
		String userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7";
		// String url =
		// HttpRequest.get("https://www.facebook.com").trustAllHosts().userAgent(userAgent).followRedirects(true).url().toString();
		// String headers =
		// HttpRequest.get("https://www.facebook.com").trustAllHosts().userAgent(userAgent).followRedirects(true).headers().toString();
		// System.out.println(url);
		// System.out.println(headers);

		List<String> urls = new ArrayList<String>();
		urls.add("https://twitter.com/");
		urls.add("https://www.youtube.com/");
		urls.add("https://www.linkedin.com/");
		urls.add("http://www.milanoo.com/");
		urls.add("https://www.facebook.com/");

		for (String u : urls) {

			Map<String, List<String>> h = HttpRequest.get(u).trustAllHosts()
					.userAgent(userAgent).followRedirects(false).headers();
			List<String> acao = h.get("Access-Control-Allow-Origin");
			List<String> location = h.get("location") == null? h.get("Location") : h.get("location");
			if (location == null || location.size() == 0) {
				if (acao == null || acao.size() == 0) {
					System.out.println("No way");
					 System.out.println("Header is " +
					 HttpRequest.get(u).trustAllHosts().userAgent(userAgent).followRedirects(false).headers().toString());
					// System.out.println("CurrentUrl is " +
					// HttpRequest.get(u).trustAllHosts().userAgent(userAgent).followRedirects(false).url());
				} else {
					System.out.println(acao.get(0));
				}
			} else {
				System.out.println(location.get(0));
			}
		}
	}
	
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("productId", 92546);
		map.put("tts", 1484749939);
		map.put("id", 20751);
		String body = HttpRequest.post("http://www.renault-trucks.net/J47MAP/web/getEtab.do?").form(map).body();
		System.out.println(body);
	}
}
