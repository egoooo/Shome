package newjohn.com.shome.swipe;

/**
 * Created by Administrator on 2017/11/6.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;
import java.util.Map;

import newjohn.com.shome.R;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MainAdapter extends BaseAdapter<MainAdapter.ViewHolder> {

    private List<Map<String, Object>> mDataList;


    public MainAdapter(Context context) {


        super(context);
    }

    public void notifyDataSetChanged(List<Map<String, Object>> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getInflater().inflate(R.layout.swip_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mDataList.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.s_title);
            imageView=itemView.findViewById(R.id.s_item_img);
        }

        public void setData(Map<String, Object> map) {
            this.tvTitle.setText((String)map.get("text"));
            this.imageView.setImageResource((int)map.get("image"));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

}
