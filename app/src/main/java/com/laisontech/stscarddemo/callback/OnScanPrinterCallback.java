package com.laisontech.stscarddemo.callback;

import android.bluetooth.BluetoothDevice;

/**
 * Created by SDP on 2017/3/17.
 */
public interface OnScanPrinterCallback {
    void onScanDevice(BluetoothDevice device);
    void onScanFinished();
}
