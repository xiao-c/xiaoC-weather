package util;

import android.text.TextUtils;
import model.City;
import model.County;
import model.Province;
import model.Xiaoc_weatherDB;

public class Utility {

	/**
	 * �����ʹ�����������ص�ʡ������	
	 */
	public synchronized static boolean handleProvincesResponse(Xiaoc_weatherDB
			xiaoc_weatherDB,String response){
		if(!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces !=null &&allProvinces.length> 0) {
				for (String p :allProvinces) {
					String  [] array =p.split("\\|");
					Province province =new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݴ洢��Province��
					xiaoc_weatherDB.saveProvince(province);
				}
				return true	;
			}
		}
		return false;
	}
	
	/**
	 * �����ʹ�����������ص��м�����
	 */
	public static boolean handleCitiesResponse (Xiaoc_weatherDB xiaoc_weatherDB,String response,int provinceId){
		
		if(!TextUtils.isEmpty(response)){
			String[] allCities =response.split(",");
			if (allCities != null &&allCities.length>0){
				for (String c : allCities) {
					String[]array = c.split("\\|");
					City city =new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//���������������ݴ洢��City��
					xiaoc_weatherDB.saveCity(city);
							
				}
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * �����ʹ�����������ص��м�����
	 */
	public static boolean handleCountiesResponse (Xiaoc_weatherDB
			xiaoc_weatherDB,String response,int cityId){
		
		if(!TextUtils.isEmpty(response)){
			String[] allCounties =response.split(",");
			if (allCounties != null &&allCounties.length>0){
				for (String c : allCounties) {
					String[]array = c.split("\\|");
					County county =new County();
					
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//���������������ݴ洢��City��
					xiaoc_weatherDB.saveCounty(county);
							
				}
				return true;
			}
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
