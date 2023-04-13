package com.example.baitaplon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    Context context;
    private int layout;
    private List<CongViec> lists;

    public CongViecAdapter(Context context, int layout, List<CongViec> lists) {
        this.context = context;
        this.layout = layout;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTen = (TextView)  view.findViewById(R.id.tencv);
            holder.imgDone = (ImageView) view.findViewById(R.id.done);
            holder.imageEdit = (ImageView) view.findViewById(R.id.edit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.delete);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CongViec congviec = lists.get(i);
        holder.txtTen.setText(congviec.getTenCV());

        return null;
    }

    private class ViewHolder{
        TextView txtTen;
        ImageView imgDone,imageEdit,imgDelete;
    }
}
