package com.followlaw.fl.util;

import android.text.TextUtils;

import com.followlaw.fl.db.FLDB;
import com.followlaw.fl.model.City;
import com.followlaw.fl.model.County;
import com.followlaw.fl.model.Province;

/**
 * Created by ubuntu on 12/26/15.
 */
public class Utility {

    /**
     * parse data
     */

    public synchronized static boolean handleProvinceResponse(FLDB fldb, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    fldb.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(FLDB fldb, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array =c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    fldb.loadCities(city);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCountiesResponse(FLDB fldb, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if(allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    fldb.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
