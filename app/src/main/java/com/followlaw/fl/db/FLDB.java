package com.followlaw.fl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.followlaw.fl.model.City;
import com.followlaw.fl.model.County;
import com.followlaw.fl.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 12/26/15.
 */
public class FLDB {

    public static final String DB_NAME = "fl_db" ;
    public static final int VERSION = 1;
    private static FLDB fldb;
    private SQLiteDatabase db;

    /**
     * 私有化构造方法
     */
    private FLDB(Context context) {
        FLOpenHelper dbHelper = new FLOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static FLDB getInstance(Context context) {
        if (fldb == null) {
            fldb = new FLDB(context);
        }
        return fldb;
    }

    /**
     * 存储实例到数据库
     */
    public void saveProvince(Province province){
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    public void saveCity(City city){
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    public void saveCounty(County county){
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }
    /**
     * 从数据库中读取
     */
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("provice_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[] { String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(cursor.getShort(cursor.getColumnIndex("city_code")));
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", "null", "cityId = ?", new String[] { String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cursor.getShort(cursor.getColumnIndex("county_code")));
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }


}
