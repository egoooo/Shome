package newjohn.com.shome.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import newjohn.com.shome.R;
import newjohn.com.shome.swipe.MainAdapter;

public class SceneActivity extends AppCompatActivity {


    @BindView(R.id.swipeMenuRecyclerView)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.s_toolbar)
    Toolbar toolbar;
    private List<Map<String, Object>> data_list= new ArrayList<>();

    // 图片封装为一个数组
    private int[] icon = { R.drawable.suijiao, R.drawable.qichuang,
            R.drawable.huike, R.drawable.huijia, R.drawable.lijia,
            R.drawable.guanying, R.drawable.yuedu, R.drawable.jiucan,
            R.drawable.zidingyi };
    private String[] iconName = { "睡眠", "起床","会客", "回家", "离家", "观影", "阅读", "就餐",
            "自定义" };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。




            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(SceneActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(SceneActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };


    SwipeMenuCreator swipeMenuCreator=new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {


            //int width = getResources().getDimensionPixelSize();

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext())
                        .setBackgroundColor(0xCFCFCF)
                        .setImage(R.drawable.icon_ok)
                        .setText("选择")
                        .setTextColor(Color.BLACK)
                        .setWidth(200)
                        .setHeight(height);



                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(SceneActivity.this)
                        .setBackgroundColor(0x87CEFF)
                        .setText("设置")
                        .setImage(R.drawable.settinga)
                        .setTextColor(Color.BLACK)
                        .setWidth(200)
                        .setHeight(height);

                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        ButterKnife.bind(this);
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(Color.BLACK));

        MainAdapter mAdapter=new MainAdapter(SceneActivity.this);

       getData();
        mAdapter.notifyDataSetChanged(data_list);
        mRecyclerView.setAdapter(mAdapter);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

}
