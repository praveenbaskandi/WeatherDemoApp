package com.praveen.weatherappdemo.appUI

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.praveen.weatherappdemo.R
import com.praveen.weatherappdemo.appUtil.NetworkUtils
import com.praveen.weatherappdemo.applicationUtil.BaseActivity
import com.praveen.weatherappdemo.databinding.ActivityMainBinding
import java.util.*


class MainActivity : BaseActivity() {
    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainViewModel: MainViewModel? = null
    private var mDataList: MutableLiveData<List<DataModelClass.Weather>>? = null
    private val mContext: Context = this@MainActivity
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    companion object {
        var REQUEST_CODE_LOCATION = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mMainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )
        mActivityMainBinding?.lifecycleOwner = this
        mActivityMainBinding?.viewModel = mMainViewModel
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mDataList = mMainViewModel?.getDataListData()
        mActivityMainBinding!!.rvListHorizontal.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        settingObserver()
    }

    /**
     * check if network is there or not and call API accordingly
     */
    fun checkNetworkConnection() {
        if (NetworkUtils.isConnected(mContext)) {
            mMainViewModel?.errorShow?.value = false
            callSampleApi()
        } else {
            mMainViewModel?.errorShow?.value = true
        }
    }

    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLatLocation();
        } else {
            askLocationPermission();
        }
    }

    /**
     * Asking for permission in case no permission is given
     */
    private fun askLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLatLocation()
            } else {
                Log.e("PERMISSION: ", "Permission Not Granted");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * getting city name from Geo-locator
     */
    @SuppressLint("MissingPermission")
    private fun getLatLocation() {
        mFusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                val mGeocoder = Geocoder(this, Locale.getDefault())
                val addresses: List<Address> =
                    mGeocoder.getFromLocation(location!!.latitude, location.longitude, 1)
                mMainViewModel!!.showCity.value = addresses[0].adminArea
                checkNetworkConnection()
            }
    }

    /**
     * setting observer to observe data changes
     */
    private fun settingObserver() {
        mMainViewModel!!.dataListSuccess.observe(this) { apiObserverDataModel ->
            hideProgressDialog()
            val modelData: DataModelClass =
                apiObserverDataModel.responseDataModel as DataModelClass
            mMainViewModel!!.setDataListData(modelData)
        }
        mMainViewModel!!.getResponseError().observe(this) {
            hideProgressDialog()
            mMainViewModel?.errorShow?.value = true
            Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show()
        }

        mDataList!!.observe(this) { dataListModel ->
            val mHorizontalAdapter = DataHorizontalAdapter(dataListModel)
            mActivityMainBinding!!.rvListHorizontal.adapter = mHorizontalAdapter
        }
    }


    /**
     * calling api to get latest weather report
     */
    private fun callSampleApi() {
        showProgressDialog("")
        mMainViewModel?.getDataListApi()
    }
}