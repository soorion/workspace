package ApiAjaxData01;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;



import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Util.JDBCUtil;


public class DetailIntroDataTravel {
  	
	 public void detailDataGet(String serviceKey, String contentId, String contentTypeId,
			 String ourl, String id, String pw) {
//	 public static void main(String args[]){	
    	String  xml;
    	JDBCUtil db = null;
    	Connection con = null;
    	PreparedStatement pstmt = null;
		try {

			StringBuilder urlBuilder = 
					new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro"); /*URL*/
			urlBuilder.append("?"+"serviceKey="+serviceKey);
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageSize=10");
			urlBuilder.append("&pageNo=1");
			urlBuilder.append("&startPage=1");
			urlBuilder.append("&MobileOS="+"ETC");
			urlBuilder.append("&MobileApp="+"AppTest");
			urlBuilder.append("&introYN=Y");
			urlBuilder.append("&contentTypeId="+contentTypeId);// stay=32, food=39 , travel = 12/15
			urlBuilder.append("&contentId="+contentId);	//	change check 
			
		
			URL url = new URL(urlBuilder.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			//	ajax = xml	/ json = application/json
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while (true) {
			    String line = br.readLine();
			    if (line == null)
			        break;
			    sb.append(line);
			}
			xml = sb.toString();
			br.close();
			conn.disconnect();
			
			org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
	        String xmlJSONObjString = xmlJSONObj.toString();
	        //	xml -> json �쑝濡� 蹂��솚�븯硫� item�쑝濡� �븳踰덉뿉 臾띠씤�떎.
	        //	System.out.println(xml);
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map = objectMapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});	       
	        Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
	        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
	        Map<String, Object> items = (Map<String, Object>) body.get("items");
	        Map<String, Object> itemList =  (Map<String, Object>) items.get("item");
	        
	        
 
	        System.out.println(itemList);
	        // 	15
	        String keyString[] = {"chkbabycarriage", "chkcreditcard", "chkpet", "contentid",
	        		"contenttypeid", "expguide", "heritage1", "heritage2", "heritage3", "infocenter",
	        		"opendate", "parking", "restdate", "expagerange", "usetime"};
	     	
	        
	        
	       for(int i=0; i<keyString.length; i++){
	    	   boolean keycheck = itemList.containsKey(keyString[i]);
	       		if(keycheck==false){
	       			itemList.put(keyString[i], "empty");
	       		}
	       		
	       		String temp = itemList.get(keyString[i]).toString();
	       		
	       		if(temp==null || temp.length()==0 || temp == ""){
	       			itemList.put(keyString[i], "empty");
	       		}
	        }
	      
	        
	        db = new JDBCUtil();
	        con = db.getCON(ourl, id, pw);
	        String sql = "insert into TravelIntroTbl values ( ?, ?, ?, ?, ?,"
	        		+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	        pstmt = db.getPSTMT(con, sql);
	      
	       
	        for(int i=1; i<keyString.length+1; i++){
	        	pstmt.setString(i, itemList.get(keyString[i-1]).toString());
	        }
	        
	        pstmt.executeQuery();
	        
	        db.close(pstmt);
	        db.close(con);
	       
	        System.out.println("	DetailIntroDataTravel	success");
	        
		} catch(Exception e){
		 System.out.println(e); 
		}
	 }
}
	        