package cn.larry.search.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author gzfu
 */
public class ParamMapUtil {

	@SuppressWarnings("rawtypes")
	private  static Map<String,String> getParameterMap(Map properties) {
	    Map<String,String> returnMap = new HashMap<>();
	    Iterator<?> entries = properties.entrySet().iterator();
		Map.Entry entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Map.Entry) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i = 0 ; i < values.length ; i ++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
	    }
	    return returnMap;
	}
	
	public static Map<String,String> getParameterMap(HttpServletRequest request) {
		return getParameterMap(request.getParameterMap());
	}

}
