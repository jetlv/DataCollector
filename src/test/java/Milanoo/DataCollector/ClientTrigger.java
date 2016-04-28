package Milanoo.DataCollector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jetbaba.utils.HttpRequest;

public class ClientTrigger {
	public static void main(String[] args) {
//		String body = HttpRequest.post("https://movie.douban.com").headers().toString();
		 CookieManager cookieManager = CookieManager.getInstance(); 
		System.out.println(cookieManager.getCookies("movie.douban.com"));
		
		
	}
	 /** 
     * 根据域名获取对应的Cookie 
     * @param domain 
     * @return 
     */  
    public static String getCookies(String domain){  
    	Map<String,Map<String,String>> cookies = new ConcurrentHashMap<String, Map<String,String>>();  
        Map<String, String> domainCookies = cookies.get(getTopLevelDomain(domain));  
        if(domainCookies != null){  
            StringBuilder sb = new StringBuilder();  
            boolean isFirst = true;  
            for(Map.Entry<String, String> cookieEntry : domainCookies.entrySet()){  
                if(!isFirst){  
                    sb.append("; ");  
                }else{  
                    isFirst = false;  
                }  
                sb.append(cookieEntry.getKey())  
                  .append("=")  
                  .append(cookieEntry.getValue());  
            }  
            return sb.toString();  
        }  
        return "";  
    }  
    
    /** 
     * 获取域名的顶级域名 
     * @param domain 
     * @return 
     */  
    public static String getTopLevelDomain(String domain){  
        Map<String,String> domainToTopLevelDomainMap = new ConcurrentHashMap<String, String>();  
        if(domain == null){  
            return null;  
        }  
        if(!domainToTopLevelDomainMap.containsKey(domain)){  
            String[] splits = domain.split("\\.");  
            domainToTopLevelDomainMap.put(domain, (splits[splits.length-2] + "." + splits[splits.length -1]));  
        }  
        return domainToTopLevelDomainMap.get(domain);  
    }  
}
