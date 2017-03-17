package com.laisontech.stscarddemo.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.adapter.BluetoothDeviceAdapter;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.bluetooth.BluetoothManager;
import com.laisontech.stscarddemo.callback.OnScanPrinterCallback;
import com.laisontech.stscarddemo.constant.Constants;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class ConnectPrinterActivity extends BaseActivity implements View.OnTouchListener, OnScanPrinterCallback, BluetoothDeviceAdapter.OnItemClickListener {

    private static final int MSG_BLUETOOTH_FOUND = 0x110;
    private static final int MSG_BLUETOOTH_SEARCH_FINISHED = 0x111;
    @InjectView(R.id.ll_remove_title)
    LinearLayout mLlRemoveTitle;
    @InjectView(R.id.btn_connect_printer)
    Button mBtnConnectPrinter;
    @InjectView(R.id.lv_bluetooth)
    ListView mLvBluetooth;
    @InjectView(R.id.tv_printer_status)
    TextView mTvPrinterStatus;
    @InjectView(R.id.tv_printer_name)
    TextView mTvPrinterName;
    private SystemSettingUtils.ActionBarView mActionBarView;
    //TODO 模拟数据实现滑动上面的布局跟着消失
    private float mFirstTouchY;
    private float mCurrentTouchY;
    private float mTouchSlop;
    private Animator mAnimatorTitle;
    private Animator mAnimatorContent;
    private Animator mAnimatorButton;
    /**
     * 蓝牙设备管理类
     */
    private BluetoothManager mBluetoothManager;
    /**
     * 搜索完成的蓝牙集合
     */
    private List<BluetoothDevice> mDevices = new ArrayList<>();
    ;
    /**
     * 主线程的Handler，用于处理蓝牙的发现和搜索完毕
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_BLUETOOTH_FOUND:
                    if (msg.obj == null) return;
                    BluetoothDevice device = (BluetoothDevice) msg.obj;
                    addDeviceToListView(device);
                    break;
                case MSG_BLUETOOTH_SEARCH_FINISHED:
                    //TODO 怎么写好搜索，是在背后搜索，还是在界面显示？
                    showToast("搜索完成");
                    mBtnConnectPrinter.setText(Constants.SEARCHER_PRINTER);
                    break;
            }
        }
    };
    private BluetoothDeviceAdapter mAdapter;

    private void addDeviceToListView(BluetoothDevice device) {
        mDevices.add(device);
        if (mDevices.size() > 0) {
            mBtnConnectPrinter.setText(Constants.SEARCHER_PAUSE);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBluetoothManager != null) {
            if (!mBluetoothManager.isOpenBluetooth()) {
                mBluetoothManager.openBluetooth(this);
            }
            mBluetoothManager.registerBlueReceiver(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothManager != null) {
            mBluetoothManager.unRegisterBlueReceiver(this);
        }
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_connect_printer;
    }

    @Override
    protected void initData() {
        mBluetoothManager = BluetoothManager.getManager();
        mActionBarView = SystemSettingUtils.setActionBar(this, true);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        showHideLl(true, true);
    }

    @Override
    protected void initEvent() {
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActionBarView.tv.setText("连接打印机");
        mLvBluetooth.setOnTouchListener(this);
        mAdapter = new BluetoothDeviceAdapter(this, mDevices);
        mLvBluetooth.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.btn_connect_printer)
    public void onClick() {
        if (mBluetoothManager == null) {
            return;
        }
        String btnText = mBtnConnectPrinter.getText().toString().trim();
        //判断当前的字段是什么
        //1、搜索打印机
        if (Constants.SEARCHER_PRINTER.equals(btnText)){
                searchPrinter();
            // 2、搜索中
        }else if (Constants.SEARCHERING.equals(btnText)){
            searching();
            // 3、暂停
        }else if (Constants.SEARCHER_PAUSE.equals(btnText)){
            searchPause();
            // 4、连接打印机
        }else if (Constants.CONNECT_PRINTER.equals(btnText)){
            connectPrinter();
            //5、连接中
        }else if (Constants.CONNECTING.equals(btnText)){
            connecting();
        }
    }

    private void searchPrinter() {
        boolean discovering = mBluetoothManager.isDiscovering();
        boolean isDiscovery;
        if (discovering) {
            isDiscovery = mBluetoothManager.stopDiscovery();
            if (!isDiscovery) return;
            return;
        }
        //清除元数据
        if (mDevices.size() > 0) {
            mDevices.clear();
        }
        mAdapter.notifyDataSetChanged();
        mBtnConnectPrinter.setText(Constants.SEARCHERING);
        mBluetoothManager.startDiscovery();
    }

    private void searching() {
        setBoolean(false);
    }
    private void setBoolean(boolean b){
        mLlRemoveTitle.setClickable(b);
        mLvBluetooth.setClickable(b);
        mLlRemoveTitle.setEnabled(b);
        mLvBluetooth.setEnabled(b);
    }
    private void searchPause() {
        setBoolean(true);
        mBluetoothManager.stopDiscovery();
        mBtnConnectPrinter.setText(Constants.SEARCHER_PRINTER);
    }

    private void connectPrinter() {
        //执行连接任务
        mBtnConnectPrinter.setText(Constants.CONNECTING);
        mTvPrinterStatus.setText("连接中...");
    }

    private void connecting() {
        //连接打印机，并且此阶段不能操作其他任务，如果连接成功，就跳转到成功界面，进行打印小票的任务
        mTvPrinterStatus.setText("连接完成");
        //连接不成功，设置为 mTvPrinterStatus.setText("搜索打印机");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentTouchY = event.getY();
                if (mCurrentTouchY - mFirstTouchY > mTouchSlop) {
                    //下滑动
                    showHideLl(true, false);
                } else if (mFirstTouchY - mCurrentTouchY > mTouchSlop) {
                    //上滑动
                    showHideLl(false, false);
                }
                break;
        }
        return false;
    }

    private void showHideLl(boolean b, boolean needDuration) {
        if (mAnimatorTitle != null && mAnimatorTitle.isRunning()) {
            mAnimatorTitle.cancel();
        }
        if (mAnimatorButton != null && mAnimatorButton.isRunning()) {
            mAnimatorButton.cancel();
        }
        if (mAnimatorContent != null && mAnimatorContent.isRunning()) {
            mAnimatorTitle.cancel();
        }
        if (b) {
            mAnimatorTitle = ObjectAnimator.ofFloat(mLlRemoveTitle, "translationY"
                    , mLlRemoveTitle.getTranslationY(), 0);
            mAnimatorButton = ObjectAnimator.ofFloat(mBtnConnectPrinter, "translationY"
                    , mBtnConnectPrinter.getTranslationY(), getResources().getDimension(R.dimen.ll_height));
            mAnimatorContent = ObjectAnimator.ofFloat(mLvBluetooth, "translationY"
                    , mLvBluetooth.getTranslationY(),
                    getResources().getDimension(R.dimen.ll_height));
            if (needDuration) {
                mAnimatorTitle.setDuration(10);
                mAnimatorButton.setDuration(10);
                mAnimatorContent.setDuration(10);
            }
        } else {
            mAnimatorTitle = ObjectAnimator.ofFloat(mLlRemoveTitle, "translationY",
                    mLlRemoveTitle.getTranslationY(), -mLlRemoveTitle.getHeight());
            mAnimatorButton = ObjectAnimator.ofFloat(mBtnConnectPrinter, "translationY"
                    , mBtnConnectPrinter.getTranslationY(), 0);
            mAnimatorContent = ObjectAnimator.ofFloat(mLvBluetooth, "translationY",
                    mLvBluetooth.getTranslationY(), 0);
        }
        mAnimatorTitle.start();
        mAnimatorButton.start();
        mAnimatorContent.start();
    }

    //通过接口回调获取的设备等信息
    @Override
    public void onScanDevice(BluetoothDevice device) {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_BLUETOOTH_FOUND, device));
    }

    @Override
    public void onScanFinished() {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_BLUETOOTH_SEARCH_FINISHED));
    }

    @Override
    public void onItemClick(int position) {
        if (mDevices.size() > 0) {
            mTvPrinterName.setText(mDevices.get(position).getName());
            mBtnConnectPrinter.setText(Constants.CONNECT_PRINTER);
            showHideLl(true,false);
        }
    }
}
