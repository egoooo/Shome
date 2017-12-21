package newjohn.com.shome.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import newjohn.com.shome.R;
import newjohn.com.shome.bean.Electricity;

/**
 * Created by Administrator on 2017/9/29.
 */

public class TableAdapter extends BaseAdapter {

    private List<Electricity> list;
    private LayoutInflater inflater;

    public TableAdapter(Context context,List list){
        this.list=list;

        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Electricity electricity= (Electricity) getItem(position);
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView=inflater.inflate(R.layout.item_layout,null);
            viewHolder.device=convertView.findViewById(R.id.device);
            viewHolder.current=convertView.findViewById(R.id.current);
            viewHolder.voltage=convertView.findViewById(R.id.voltage);
            viewHolder.comsuption=convertView.findViewById(R.id.comsuption);
            viewHolder.state=convertView.findViewById(R.id.state);
            viewHolder.power=convertView.findViewById(R.id.power);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.device.setText(electricity.getDevice());
        viewHolder.current.setText(String.valueOf(electricity.getCurrent()));
        viewHolder.voltage.setText(String.valueOf(electricity.getVoltage()));
        viewHolder.comsuption.setText(String.valueOf(electricity.getPowerConsumption()));
        viewHolder.state.setText(electricity.getState());
        viewHolder.power.setText(String.valueOf(electricity.getPower()));


        return convertView;
    }


    public  static  class ViewHolder{
        public TextView device;
        public TextView current;
        public TextView voltage;
        public TextView power;
        public TextView comsuption;
        public TextView state;
    }
}
