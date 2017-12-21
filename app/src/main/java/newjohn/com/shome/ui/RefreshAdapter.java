package newjohn.com.shome.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import newjohn.com.shome.R;
import newjohn.com.shome.bean.Data;
import newjohn.com.shome.glide.GlideApp;

/**
 * Created by 刘楠 on 2016/9/10 0010.18:06
 */
public class RefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private OnItemClickListener mOnItemClickListener = null;
    Context        mContext;
    LayoutInflater mInflater;
    List<Data>   mDatas;
    private static final int TYPE_ITEM   = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE     = 2;
    //网络错误！
    public static final int NET_ERROR     = 3;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;


    public RefreshAdapter(Context context, List<Data> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_refresh_recylerview, parent, false);

            itemView.setOnClickListener(this);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.load_more_footview_layout, parent, false);

            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Data         data            = mDatas.get(position);
            itemViewHolder.title.setText(data.getTitle());
            itemViewHolder.date.setText(data.getDate());
            itemViewHolder.category.setText(data.getCategory());
            itemViewHolder.from.setText(data.getAuthor_name());

            itemViewHolder.itemView.setTag(position);
            GlideApp
                    .with(mContext)
                    .load(data.getThumbnail_pic_s())
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .into(itemViewHolder.imageView1);

            GlideApp
                    .with(mContext)
                    .load(data.getThumbnail_pic_s02())
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .into(itemViewHolder.imageView2);


        } else if (holder instanceof FooterViewHolder) {


            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;


            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.mPbLoad.setVisibility(View.VISIBLE);
                    footerViewHolder.mTvLoadText.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.mPbLoad.setVisibility(View.VISIBLE);
                    footerViewHolder.mTvLoadText.setText("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.mTvLoadText.setText("没有更多了！");
                    footerViewHolder.mPbLoad.setVisibility(View.GONE);
                    break;
                case NET_ERROR:
                    footerViewHolder.mTvLoadText.setText("请检查网络！");
                    footerViewHolder.mPbLoad.setVisibility(View.GONE);


            }
        }

    }

    @Override
    public int getItemCount() {
        //RecyclerView的count设置为数据总条数+ 1（footerView）
        return mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_title)
        TextView title;
        @BindView(R.id.news_pic1)
        ImageView imageView1;
        @BindView(R.id.news_pic2)
        ImageView imageView2;
        @BindView(R.id.news_date)
        TextView  date;
        @BindView(R.id.news_category)
        TextView  category;
        @BindView(R.id.news_from)
        TextView  from;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //initListener(itemView);
        }

//        private void initListener(View itemView) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "poistion " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pbLoad)
        ProgressBar  mPbLoad;
        @BindView(R.id.tvLoadText)
        TextView     mTvLoadText;
        @BindView(R.id.loadLayout)
        LinearLayout mLoadLayout;
        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public void AddHeaderItem(List<Data> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<Data> items) {
        mDatas.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     * @param status
     */
    public void changeMoreStatus(int status){
        mLoadMoreStatus=status;
        notifyDataSetChanged();
    }


    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}
