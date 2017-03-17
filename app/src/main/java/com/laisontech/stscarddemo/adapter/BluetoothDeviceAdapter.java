package com.laisontech.stscarddemo.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.adapter.base_adapter.CommonAdapter;
import com.laisontech.stscarddemo.adapter.base_adapter.CommonViewHolder;

import java.util.List;

/**
 * Created by SDP on 2017/3/17.
 */
public class BluetoothDeviceAdapter extends CommonAdapter<BluetoothDevice>{
    public BluetoothDeviceAdapter(Context mContext, List<BluetoothDevice> mData) {
        super(mContext, mData);
    }

    @Override
    public int layoutId() {
        return R.layout.bluetooth_device_item_layout;
    }

    @Override
    public void convert(CommonViewHolder holder, final BluetoothDevice device, final int position) {
        ((TextView)holder.getView(R.id.tv_device_name)).setText(device.getName());
        ((TextView)holder.getView(R.id.tv_device_address)).setText(device.getAddress());
        holder.getView(R.id.ll_click_bluetooth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (onItemClickListener!=null){
                   onItemClickListener.onItemClick(position);
               }
            }
        });
    }
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
