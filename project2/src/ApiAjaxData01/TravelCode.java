package ApiAjaxData01;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Util.JDBCUtil;




public class TravelCode {
  	
	public void areaBasedListDataGet(String serviceKey, String aCode, String contentTypeId,
			String mobileOs, String mobileApp, String ourl, String id, String pw) {
	 // public static void main(String args[]){	
    	String  xml;
    	JDBCUtil db = null;
    	Connection con = null;
    	PreparedStatement pstmt = null;
		try {

			StringBuilder urlBuilder = 
					new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"); /*URL*/
			urlBuilder.append("?"+"ServiceKey="+serviceKey);
			urlBuilder.append("&"+"numOfRows=999");
			urlBuilder.append("&"+"pageNo=1");
			urlBuilder.append("&"+"MobileOS="+mobileOs);
			urlBuilder.append("&"+"MobileApp="+mobileApp);
			urlBuilder.append("&"+"arrange=A");
			urlBuilder.append("&"+"listYn=Y");
			urlBuilder.append("&"+"contentTypeId="+contentTypeId);
			urlBuilder.append("&"+"areaCode="+aCode);
								
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
	        // xml -> json �쑝濡� 蹂��솚�븯硫� item�쑝濡� �븳踰덉뿉 臾띠씤�떎.
	 
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map = objectMapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});
	        Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
	        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
	        Map<String, Object> items = (Map<String, Object>) body.get("items");
	        List<Map<String, Object>> itemList =  (List<Map<String, Object>>) items.get("item");
	        
	        int length = itemList.size();
	        ArrayList<String> keyList = new ArrayList<String>();
	        String keyString[] = {"addr1", "areacode", "cat1", "cat2", "cat3", "contentid",
	        		"contenttypeid", "createdtime", "firstimage", "firstimage2", "mapx", "mapy",
	        		"modifiedtime", "readcount", "sigungucode", "tel", "title", "zipcode"};
	       
	        for(int i=0; i<keyString.length;i++){
	        	keyList.add(keyString[i]);
	        	//System.out.println(keyList.get(i));
	        }
	        
	        //
	        db = new JDBCUtil();
	        con = db.getCON(ourl, id, pw);
	        String sql = "insert into travelTbl values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	        		+ "?, ?, ?, ?, ?, ?)";
			pstmt = db.getPSTMT(con, sql);
	        
	        
	        Map<String,Object> itemMap = null;
	        	        
	        for(int i=0; i<length; i++){
	        	 itemMap = (Map<String,Object>) itemList.get(i);
	        	
	        	 
	        	for(int j=0; j<keyList.size(); j++){
	        		boolean keyCheck = itemMap.containsKey(keyList.get(j));
	        		if(keyCheck== true){
	        			itemMap.get(keyList.get(j));
	        			
	        		}else{
	        			
	        			itemMap.put(keyList.get(j),"empty");
	        			
	        		}// �꼸 泥섎━ �셿
	        	}//	�궡遺� for臾� j, k end
	        	
	        	String areaCode =  itemMap.get("areacode").toString();
	        	String sigunCode = itemMap.get("sigungucode").toString(); 
	        	String addr1 = itemMap.get("addr1").toString(); 
	        	String cat1 = itemMap.get("cat1").toString(); 
	        	String cat2 = itemMap.get("cat2").toString(); 
	        	String cat3 = itemMap.get("cat3").toString(); 
	        	String contentid = itemMap.get("contentid").toString();  
	        	String contenttypeid = itemMap.get("contenttypeid").toString(); 
	        	String createdtime = itemMap.get("createdtime").toString(); 
	        	String firstimage = itemMap.get("firstimage").toString(); 
	        	String firstimage2 = itemMap.get("firstimage2").toString(); 
	        	String mapx = itemMap.get("mapx").toString(); 
	        	String mapy = itemMap.get("mapy").toString(); 
	        	String modifiedtime = itemMap.get("modifiedtime").toString(); 
	        	String readcount = itemMap.get("readcount").toString(); 
	        	String tel = itemMap.get("tel").toString(); 
	        	String title = itemMap.get("title").toString(); 
	        	String zipcode = itemMap.get("zipcode").toString();
	        	
	        	pstmt.setString(1, areaCode);
	        	pstmt.setString(2, sigunCode);
	        	pstmt.setString(3, addr1);
	        	pstmt.setString(4, cat1);
	        	pstmt.setString(5, cat2);
	        	pstmt.setString(6, cat3);
	        	pstmt.setString(7, contentid);
	        	pstmt.setString(8, contenttypeid);
	        	pstmt.setString(9, createdtime);
	        	pstmt.setString(10, firstimage);
	        	pstmt.setString(11, firstimage2);
	        	pstmt.setString(12, mapx);
	        	pstmt.setString(13, mapy);
	        	pstmt.setString(14, modifiedtime);
	        	pstmt.setString(15, readcount);
	        	pstmt.setString(16, tel);
	        	pstmt.setString(17, title);
	        	pstmt.setString(18, zipcode);
	        	pstmt.executeQuery();
				
	        	
	        }//	�뜲�씠�꽣 �젙�젣 �셿
	        
	        
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
	
		System.out.println("end	TravelCode" + aCode);
       
	}

}

