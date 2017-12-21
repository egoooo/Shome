package newjohn.com.shome.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import newjohn.com.shome.R;
import newjohn.com.shome.activity.WebActivity;
import newjohn.com.shome.bean.Data;
import newjohn.com.shome.bean.NewsBean;
import newjohn.com.shome.ui.RefreshAdapter;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/10/26.
 */

public class NewsFrag1 extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<String> mDatas = new ArrayList<>();
    private RefreshAdapter mRefreshAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    Unbinder unbinder;
    Gson gson;
    NewsBean newsBean;
    List<Data> datas = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    datas= (List<Data>) msg.obj;
                    mRefreshAdapter.AddHeaderItem(datas);
                    //刷新完成
                    mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "更新了 " + datas.size() + " 条目数据", Toast.LENGTH_SHORT).show();

            }

        }
    };




        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        private static String cacheDirectory = Environment.getExternalStorageDirectory() + "/okttpcaches";
        File file = new File(cacheDirectory);
        Cache cache;


        OkHttpClient okHttpClient;
        Request request;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.news1, container, false);
            unbinder = ButterKnife.bind(this, view);
            initNet();
            initView();
            initData();
            initListener();
            return view;
        }


        private void initView() {

            mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

        }

        private void initData() {

            getNewsData();

            initRecylerView();
        }

        private void initRecylerView() {

            mRefreshAdapter = new RefreshAdapter(getContext(), datas);
            mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


            //添加动画
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());


            mRefreshAdapter.setOnItemClickListener(new RefreshAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent=new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url",datas.get(position).getUrl());
                    startActivity(intent);
                }
            });
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setAdapter(mRefreshAdapter);



        }

        private void initListener() {

            initPullRefresh();

            initLoadMoreListener();

        }


        private void initPullRefresh() {
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<String> headDatas = new ArrayList<String>();
//                        for (int i = 20; i <30 ; i++) {
//
//                            headDatas.add("Heard Item "+i);
//                        }
//                        mRefreshAdapter.AddHeaderItem(headDatas);
//
//                        //刷新完成
//                        mSwipeRefreshLayout.setRefreshing(false);
//                        Toast.makeText(getActivity(), "更新了 "+headDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
//                    }
//
//                }, 3000);


                    getNewsData();
                    mSwipeRefreshLayout.setRefreshing(false);

                }
            });
        }

        private void initLoadMoreListener() {

            mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                int lastVisibleItem;

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRefreshAdapter.getItemCount()) {

                        //设置正在加载更多
                        mRefreshAdapter.changeMoreStatus(mRefreshAdapter.LOADING_MORE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshAdapter.changeMoreStatus(mRefreshAdapter.NO_LOAD_MORE);

                            }
                        },1000);

//                        改为网络请求
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
////                            //
////                            List<String> footerDatas = new ArrayList<String>();
////                            int i = 0;
////                            Random random = new Random();
////                            int page = random.nextInt(12) + 1;
////                            for (; i<page ; i++) {
////
////                                footerDatas.add("footer  item" + i);
////                            }
////                            mRefreshAdapter.AddFooterItem(footerDatas);
//                            if (page<10){
//                                //没有加载更多了
//                                mRefreshAdapter.changeMoreStatus(mRefreshAdapter.NO_LOAD_MORE);
//                            }
//                            else {
//                                //设置回到上拉加载更多
//                                mRefreshAdapter.changeMoreStatus(mRefreshAdapter.PULLUP_LOAD_MORE);
//                            }
//
//                            Toast.makeText(getActivity(), "更新了 "+footerDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
//                        }
//                    }, 3000);


                    }

                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //最后一个可见的ITEM
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                }
            });

        }


        public void initNet() {

            cache = new Cache(file, cacheSize);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cache(cache);
            okHttpClient = builder.build();
            gson = new Gson();


        }


        @Override
        public void onDestroy() {
            super.onDestroy();
            unbinder.unbind();
        }


        public void getNewsData() {


            request = new Request.Builder().url("http://v.juhe.cn/toutiao/index?type=top&key=3b8657a75ae1abe94f84c50a0617e404")
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    Log.d("okHttp", "sibai");

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String json = response.body().string();
                    Log.d("okHttp", json);
                    System.out.println("Response 1 response:          " + response);
                    System.out.println("Response 1 cache response:    " + response.cacheResponse());
                    System.out.println("Response 1 network response:  " + response.networkResponse());
                    NewsBean newsBean = gson.fromJson(json, NewsBean.class);
                    List<Data> datas = newsBean.getResult().getData();
//                Toast.makeText(getContext(),json,Toast.LENGTH_LONG).show();
                    System.out.println("datas  " + datas);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = datas;
                    handler.sendMessage(message);


                }
            });


        }

}

