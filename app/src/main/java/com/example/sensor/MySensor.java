package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import java.util.List;


public class MySensor extends ArrayAdapter<Sensor> {
    private int textViewResourceId;

    private static class ViewHolder {
        private TextView itemView;
    }

    public MySensor(Context context, int textViewResourceId, List<Sensor> items){
        super(context, textViewResourceId, items);
        this.textViewResourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(textViewResourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        Sensor item = getItem(position);
        if(item != null){
            viewHolder.itemView.setText("Name: " + item.getName()
            +"\nInt Type: " + item.getType() + "\nVendor: " + item.getVendor() +"\nVersion: " + item.getVersion() +
                    "\nPower: " + item.getPower() +"mAh" + "\nResolution: " + item.getResolution()  + "\nMax.range: " + item.getMaximumRange() +"\n");
        }
        return convertView;
    }

}
