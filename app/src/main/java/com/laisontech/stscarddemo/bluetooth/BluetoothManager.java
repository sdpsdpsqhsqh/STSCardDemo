package com.laisontech.stscarddemo.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.laisontech.stscarddemo.callback.OnScanPrinterCallback;
import com.laisontech.stscarddemo.constant.Constants;

import java.lang.reflect.Method;

/**
 * Created by SDP on 2017/3/17.
 */
public class BluetoothManager {
    private static final String TAG = BluetoothManager.class.getSimpleName();
    private BluetoothManager(){}
    private static BluetoothManager mInstance;

    private BluetoothAdapter mBluetoothAdapter;
    public static BluetoothManager getManager(){
        if (mInstance==null){
            synchronized (BluetoothManager.class) {
                if (mInstance == null) {
                    mInstance = new BluetoothManager();
                }
            }
        }
        return mInstance;
    }

    //打开蓝牙
    public boolean openBluetooth(Activity activity){

        if (mBluetoothAdapter==null){
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (mBluetoothAdapter ==null){
            return false;
        }
        if (mBluetoothAdapter.isEnabled()){
            return true;
        }
        try {
            if (!mBluetoothAdapter.isEnabled()){
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
                activity.startActivityForResult(intent, Constants.OPEN_BLUETOOTH_CODE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //蓝牙是否打开
    public boolean isOpenBluetooth(){
        if (mBluetoothAdapter == null)
        {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (mBluetoothAdapter == null)
        {
            return false;
        }
        boolean isopen = mBluetoothAdapter.enable();
        return  isopen;
    }

    /**
     * 扫描BLE设备
     */
    private OnScanPrinterCallback mCallback;
    //注册蓝牙广播
    public boolean registerBlueReceiver(Activity activity,OnScanPrinterCallback callback){
        this.mCallback = callback;
        if (mBluetoothAdapter==null){
            return false;
        }
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(BluetoothDevice.ACTION_FOUND);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            activity.registerReceiver(mReceiver,filter);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //监听广播
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)){
               BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mCallback!=null){
                    mCallback.onScanDevice(device);
                }
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                if (mCallback!=null){
                    mCallback.onScanFinished();
                }
            }
        }
    };

    //关闭搜索，结束广播
    public void unRegisterBlueReceiver(Activity activity){
        if (mBluetoothAdapter==null||activity==null){
            return;
        }
        stopDiscovery();
        activity.unregisterReceiver(mReceiver);
    }

    public boolean startDiscovery(){
        if (mBluetoothAdapter==null){
            return false;
        }
        if (mBluetoothAdapter.isDiscovering()){
            return true;
        }
        return mBluetoothAdapter.startDiscovery();
    }

    public boolean stopDiscovery() {
        if (mBluetoothAdapter==null||!mBluetoothAdapter.isDiscovering()){
            return true;
        }
        return mBluetoothAdapter.cancelDiscovery();
    }
    public boolean isDiscovering(){
        return mBluetoothAdapter!=null&&mBluetoothAdapter.isDiscovering();
    }

    //获取设备从地址中
    public BluetoothDevice getBlueDeviceByAddress(String address){
        if (address.isEmpty()){
            return null;
        }
        if (mBluetoothAdapter==null){
            return null;
        }
        BluetoothDevice device =null;
        try {
            device = mBluetoothAdapter.getRemoteDevice(address);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return device;
    }

    //绑定设备
    public boolean bondBluetoothDevice(String address){
        if (mBluetoothAdapter==null){
            return false;
        }
        BluetoothDevice remoteDevice = mBluetoothAdapter.getRemoteDevice(address);
        if (remoteDevice==null){
            return false;
        }
        boolean isBond = false;
        try {
            Method method = BluetoothDevice.class.getMethod("createBond");
            method.invoke(remoteDevice);
            int bondStatus = remoteDevice.getBondState();
            isBond = (bondStatus == BluetoothDevice.BOND_BONDED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isBond;
    }
}
