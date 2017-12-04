package ApiAjaxData01;


import java.util.ArrayList;
import Util.ListSizeUtil;

/**
 * 
 *	@author 박주영
 *	@see	
 */
public class ApiDataInsert {
	
	
	public static void main(String args[]){
		//	oracle
		String ourl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id =  "trip";
		String pw = "trip";
		
			
		//String serviceKey = "6EJNTamkI39r7f8Igxs0usZiNoa0qpP16oujfSU2XVfPKMWWJEuoYeviOKJa4fSTcsriiJi52gvECu4BgoWPNQ%3D%3D";
		//String serviceKey = "QSKQdAbQ3BNyHrBqyPwaBU71LU3d%2Bfw2njH%2FdK5MC9n1x%2BFaen%2BUXFyohfy%2BLdNHhhbK4mzXvE6nIujaex1HuQ%3D%3D";
		//String serviceKey = "b%2BGh9vpt2A18z9NSh6gx56%2Fbqzm2Ei5bvH8yBMckKO8rc9bgi%2FMntZ3r0GKF%2BuGCU%2FAwq%2BIymeECX2iKahmU8w%3D%3D";
		String serviceKey = "GTG%2FtBxaNKxQQOsb7WQryNV%2B080o4vAB7lNZEXrI%2FMLmYnr4AaZ%2BOUmzEVpx2ELkoCq%2Fd0oD%2B49gHwOwFH5%2B1w%3D%3D";
		//	3-소현누나
		String mobileOs = "ETC";
		String mobileApp ="AppTest";
		
		ArrayList<String> areaList = new ArrayList<String>();
		
		/*String[] areaArray = {"1", "2", "3", "4", "5", "6", "7" ,"8",
				"31", "32", "33", "34","35", "36", "37", "38", "39"};*/
		String[] areaArray = {"1"};
		for(int i=0; i<areaArray.length;i++){
			areaList.add(areaArray[i]);
		}
		
		///////////////////////////////////////////////////////////////////////////
		//	1, 2 -> 3,4,5 -> 3-2, 3-3 / 4-2 4-3/ 5-2, 5-3						 //
		//			stay=315	travel = 958(511)	food = 990					 //
		//	ignore stayCode8 exception(세종자치시 데이터가 없어서 에러					 //
		//	travelTbl 12	-> 511		<-----use data							 //
		//	travelTbl 15	-> 447		<-check?(데이터가 여러개 겹쳐서 확인해봐야함.. ) 	 //
		//								필요하면  4-1)매개변수 넣은후 다시 실행		     //
		//	travelinfoTbl	-> 511												 //
		//	TravelIntroTbl	-> 509?												 //
		///////////////////////////////////////////////////////////////////////////
		
		//	1. AreaCode	- korea
		//new AreaCode().areaDataGet(serviceKey, mobileOs, mobileApp, ourl, id, pw);
		System.out.println("			areaDataGet	success" );
		
		
		///////////////////////////////////////////////////////////////////////////
		//	2. sigunguCode - korea
				
		/*for(int i=0; i<areaList.size(); i++){
			new AreaSigunCode().sigunDataGet(serviceKey, areaList.get(i), mobileOs, mobileApp, ourl, id, pw);
		}*/
		
		System.out.println("			sigunguDataGet	success" );
		
		
		///////////////////////////////////////////////////////////////////////////
		//	3.	stayDataGet	- korea
		for(int i=0; i<areaList.size(); i++){
			new StayCode().stayDataGet(serviceKey, areaList.get(i), mobileOs, mobileApp, ourl, id, pw);
			System.out.println("	stayDataGet	" + areaList.get(i));
		}
		
		// 3-1	stay(table name, area, contenttypeid) 315 success
		//		11	stayInfoTbl(tableName, area, id)  
		ArrayList<String> stayIdList = ListSizeUtil.getContentid("stayTbl", "1" , "32", ourl, id, pw);
					
		for(int i=0; i<stayIdList.size();i++){
			new DetailCommonData().detailDataGet(serviceKey, mobileOs, mobileApp, stayIdList.get(i), 
					"stayInfoTbl", ourl, id, pw);
		}
		
		
		//	3-2	8	
		//		stayIntroTbl 315 success
		for(int i=0; i<stayIdList.size();i++){
			new DetailIntroDataStay().detailDataGet(serviceKey, stayIdList.get(i), ourl, id, pw);
		}	
				
		System.out.println("			new DetailIntroDataStay().detailDataGet	success");
		
		///////////////////////////////////////////////////////////////////////////
		//	4.	Travel	- korea
		/*String[] contenttypeid = {"12", "15"};
		
		for(int i=0; i<areaList.size(); i++){
			for(int j=0; j<contenttypeid.length;j++){
				new TravelCode().areaBasedListDataGet(serviceKey, areaList.get(i), contenttypeid[j],
						mobileOs, mobileApp, ourl, id, pw);
				System.out.println("	areaBasedListDataGet	" + areaList.get(i) + " " + contenttypeid[j]);
			}
		}
		
		System.out.println("			new TravelCode().areaBasedListDataGet	success");
		
		//	4-1.	11	travelInfoTbl		
		ArrayList<String> travelIdList = ListSizeUtil.getContentid("travelTbl", "1", "12", ourl, id, pw);
			for(int i=0; i<travelIdList.size(); i++){
				new DetailCommonData().detailDataGet(serviceKey, mobileOs, mobileApp, travelIdList.get(i), 
						"travelInfoTbl", ourl, id, pw);
			}
		
		
		ArrayList<String> travelIdList15 = ListSizeUtil.getContentid("travelTbl", "1", "15", ourl, id, pw);
		for(int i=0; i<travelIdList15.size(); i++){
			new DetailCommonData().detailDataGet(serviceKey, mobileOs, mobileApp, travelIdList15.get(i), 
					"travelInfoTbl", ourl, id, pw);
		}
		
		System.out.println("			new DetailCommonData().detailDataGet	success");
		
		//	4-2.	8		
		for(int i=0; i<travelIdList.size(); i++){
			new DetailIntroDataTravel().detailDataGet(serviceKey, travelIdList.get(i),"12", ourl, id, pw);
		}
		
		for(int i=0; i<travelIdList15.size(); i++){
			new DetailIntroDataTravel().detailDataGet(serviceKey, travelIdList15.get(i),"15", ourl, id, pw);
		} */
		
		///////////////////////////////////////////////////////////////////////////
		//	5. food	- korea
	/*	for(int i=0; i<areaList.size(); i++){
			new FoodCode().foodDataGet(serviceKey, areaList.get(i), mobileOs, mobileApp, ourl, id, pw );
			System.out.println("	foodDataGet	" + areaList.get(i));
		}*/
		
		System.out.println("			new FoodCode().foodDataGet	success");
		
				
		//	5-1	foodInfoTbl	11	990 success
		//		
		ArrayList<String> foodIdList = ListSizeUtil.getContentid("foodTbl", "1", "39", ourl, id, pw);
		/*for(int i=0; i<foodIdList.size();i++){
			new DetailCommonData().detailDataGet(serviceKey, mobileOs, mobileApp, foodIdList.get(i), 
					"foodInfoTbl", ourl, id, pw);
		}
		*/
		System.out.println("			DetailCommonData().detailDataGet	success");
		
		//	5-2	8	990	success
		/*
		for(int i=0; i<foodIdList.size();i++){
			new DetailIntroDataFood().detailDataGet(serviceKey, foodIdList.get(i), ourl, id, pw);
		}*/
		System.out.println("			DetailIntroDataFood().detailDataGet	success");
				
		
		
		System.out.println("the	end");
	}

}
