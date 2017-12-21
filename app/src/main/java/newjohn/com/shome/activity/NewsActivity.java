package newjohn.com.shome.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import java.util.ArrayList;

import newjohn.com.shome.R;
import newjohn.com.shome.fragment.MyAdapter;
import newjohn.com.shome.fragment.NewsFrag1;
import newjohn.com.shome.fragment.NewsFrag2;
import newjohn.com.shome.fragment.NewsFrag3;

public class NewsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tl;
    ViewPager vp;
    MyAdapter adapter;
    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("家居装饰");
        add("美食天地");
        add("生活小妙招");
    }};

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new NewsFrag1());
        add(new NewsFrag2());
        add(new NewsFrag3());
    }};






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        adapter = new MyAdapter(getSupportFragmentManager(), titleList, fragmentList);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp, true);
        tl.setTabsFromPagerAdapter(adapter);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void initView() {
        tl = (TabLayout) findViewById(R.id.tablayout);
        vp = (ViewPager) findViewById(R.id.news_viewpager);
        toolbar= (Toolbar) findViewById(R.id.new_toolbar);
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
}
