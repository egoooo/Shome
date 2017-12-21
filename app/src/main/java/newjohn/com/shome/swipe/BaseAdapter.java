package newjohn.com.shome.swipe;

/**
 * Created by Administrator on 2017/11/6.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;
import java.util.Map;

/**
 * Created by YanZhenjie on 2017/10/3.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private LayoutInflater mInflater;

    public Context getContext() {
        return context;
    }

    private  Context context;

    public BaseAdapter(Context context) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }



    public abstract void notifyDataSetChanged(List<Map<String, Object>> dataList);

}