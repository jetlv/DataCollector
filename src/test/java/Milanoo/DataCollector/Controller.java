package Milanoo.DataCollector;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.jetbaba.fetcher.DoubanCrawler;
import com.jetbaba.fetcher.MilanooCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void main(String[] args) throws Exception {
    	
//    	System.out.println("https://movie.douban.com/subject/25881781/?tag=".matches("https://movie.douban.com/subject/\\d+/\\?.*"));
    	
        String crawlStorageFolder = "/home/jet/crawlerResult";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
//        Header header_userAgent = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
//        Header header_cookie = new BasicHeader("cookie", "ll=\"118318\"; bid=JyJR5WfD3/g;");
//        Collection<Header> defaultHeaders = new ArrayList<Header>();
//        defaultHeaders.add(header_userAgent);
//        defaultHeaders.add(header_cookie);
//        config.setDefaultHeaders(defaultHeaders);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
//        controller.addSeed("https://movie.douban.com");
          controller.addSeed("http://www.milanoo.com");
//        controller.addSeed("http://www.ics.uci.edu/~welling/");
//        controller.addSeed("http://www.ics.uci.edu/");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MilanooCrawler.class, numberOfCrawlers);
//        System.out.println(DoubanCrawler.c);
    }
}