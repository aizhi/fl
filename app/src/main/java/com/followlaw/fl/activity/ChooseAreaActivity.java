package com.followlaw.fl.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.followlaw.fl.db.FLDB;
import com.followlaw.fl.model.City;
import com.followlaw.fl.model.County;
import com.followlaw.fl.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 12/26/15.
 */
public class ChooseAreaActivity extends Activity {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private FLDB fldb;
    private List<String> dataList = new ArrayList<String>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private Province selectedProvince;
    private City selectedCity;
    private County selectedCounty;

    private int currentLevel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
