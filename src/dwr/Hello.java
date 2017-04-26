package dwr;  
  
import java.util.*;  

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.*;  
import org.directwebremoting.proxy.dwr.*;  
  
public class Hello {  

    private static Map<String, ScriptSession> scrSessionMap = new HashMap<String, ScriptSession>();
    
    private static Integer userCnt = 0;

    @SuppressWarnings("deprecation")
	public void getwebindex(String webindex) {

    	webindex = webindex + userCnt;
    	userCnt ++;
        WebContext wctx = WebContextFactory.get();
        HttpServletRequest request = wctx.getHttpServletRequest();
        
        String url = request.getRemoteAddr();
        
        ScriptSession sessions = wctx.getScriptSession();
        
        Util util = new Util(sessions);
        
        util.addFunctionCall("setUrl", url);
        System.out.println(url);
        scrSessionMap.put(webindex, sessions);  
    }  
  
    // ���˷��ͷ�����ǰ�����������ߵ�ҳ���ʾ������Ϣ  
    @SuppressWarnings("deprecation")
	public void send(String to, String msg) {  

        // ������session������Ϣ
        for (String key: scrSessionMap.keySet()) {
        	ScriptSession s = scrSessionMap.get(key);
        	Util u = new Util(s);
            u.addFunctionCall("rec", msg); 
            System.out.println(msg);
        }  
    }  
  
}  