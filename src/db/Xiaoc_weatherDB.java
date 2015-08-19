package db;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.County;
import model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Xiaoc_weatherDB {

	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "xiaoc_weather"; 
	
	/**  
	 *
	 *   ���ݿ�汾
	 */
	public static final int VERSION = 1; 
	
	private static Xiaoc_weatherDB xiaoc_weatherDB;

	
	
	private SQLiteDatabase db;
	
	/**
	 * �����췽��˽�л�		
	 */
	private Xiaoc_weatherDB(Context context){
		Xiaoc_weatherOpenHelper dbHelper =new Xiaoc_weatherOpenHelper(context,
				DB_NAME,null,VERSION);
		db =dbHelper.getWritableDatabase();
		
	}
	/**
	 * ��ȡXiaoc_weatherDBʵ����
	 */
	public synchronized static Xiaoc_weatherDB getInstance(Context context){
		
		if (xiaoc_weatherDB ==null){
			xiaoc_weatherDB= new Xiaoc_weatherDB(context);
		}
		return xiaoc_weatherDB;	
	}
	/**
	 * ��Provinceʵ�����浽���ݿ�
	 */
	
	public void saveProvince(Province province){
		if (province !=null){
			ContentValues values =new ContentValues();
			values.put("provinc_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("province", null, values);
			}
		}
	/**
	 * �����ݿ��ȡȫ������ʡ����Ϣ��
	 */
	public List<Province> loadProvinces(){
		List<Province> list =new ArrayList<Province>();
		Cursor cursor =db 
				.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province =new Province ();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
					list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
	}
	/**
	 * ��Cityʵ���洢�����ݿ⡣
	 */
				
	public void saveCity(City city) {
		if(city !=null){
			ContentValues values =new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id",city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	/**
	 * �����ݿ��ȡĳʡ�������г�����Ϣ			
	 */
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City",null,"province_id=?",
				new String[]{String.valueOf(provinceId)},null,null,null);
		if(cursor.moveToFirst()){
			do{
				City city = new City(); 
				city.setId(cursor.getInt(cursor.getColumnIndex("id"))); 
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor
						.getColumnIndex("city_code"))); 
				city.setProvinceId(provinceId); 
				list.add(city);   
				} while (cursor.moveToNext()); 
		}
		return list;
	}
	 
	
	/**
	 * ��Countyʵ���洢�����ݿ⡣
	 */
	
	public void saveCounty (County county ) {
		if (county !=null){
			ContentValues values =new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyName());
			values.put("county_id", county.getCountyName());
			db.insert("County", null, values);
			
		}
	
	}
	/**
	 * �����ݿ��ж�ȡĳ���������е�����Ϣ��
	 */
	public List<County> loadCounties(int cityId ) {
		List<County> list =new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?", 
				new String []{String.valueOf(cityId) }, null,null,null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setCityId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex("county_name"))); 
				county.setCountyCode(cursor.getString(cursor   
						.getColumnIndex("county_code"))); 
				county.setCityId(cityId); 
				list.add(county);
				} while (cursor.moveToNext()); 	
		}  
		return list; 
				
	}

}
