package newjohn.com.shome.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import newjohn.com.shome.R;
import newjohn.com.shome.fragment.ControlFrag;
import newjohn.com.shome.fragment.DetectFrag;
import newjohn.com.shome.fragment.EnergyFrag;
import newjohn.com.shome.fragment.SafeFrag;
import newjohn.com.shome.udp.UDPConnection;
import newjohn.com.shome.ui.NoScrollViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

   // @BindView(R.id.head_layout)
    LinearLayout drawerHeadlinearLayout;
    @BindView(R.id.drawer_layout)
     DrawerLayout drawerLayout;
    @BindView(R.id.control_tv)
     TextView mControl;
    @BindView(R.id.energy_tv)
    TextView mEnergy;
    @BindView(R.id.monitor_tv)
     TextView mMonitor;
    @BindView(R.id.safe_tv)
    TextView mSafe;
    @BindView(R.id.id_tab_control)
     LinearLayout mTabControl;
    @BindView(R.id.id_tab_energy)
     LinearLayout mTabEnergy;
    @BindView(R.id.id_tab_monitor)
     LinearLayout mTabMonitor;
    @BindView(R.id.id_tab_safe)
     LinearLayout mTabSafe;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.id_viewpager)
    NoScrollViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        initEvent();
        resetTextViewColor();
        setSupportActionBar(toolbar);



        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();
                switch (id){
                    case R.id.news:
                        Intent intent=new Intent(MainActivity.this,NewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.logOut:
                        new AlertDialog.Builder(MainActivity.this).setTitle("退出").setMessage("您真的要退出吗？").setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).create().show();
                        break;
                    case R.id.email:
                        Intent intent1=new Intent(MainActivity.this,SceneActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(Gravity.START);
                        break;

                }

                return false;
            }
        });


        mFragments=new ArrayList<>();
        ControlFrag controlFrag=new ControlFrag();
        DetectFrag detectFrag=new DetectFrag();
        EnergyFrag energyFrag=new EnergyFrag();
        SafeFrag safeFrag= new SafeFrag();
        mFragments.add(controlFrag);
        mFragments.add(detectFrag);
        mFragments.add(energyFrag);
        mFragments.add(safeFrag);
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return  mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    private void resetTextViewColor()
    {
        this.mControl.setTextColor(getResources().getColor(R.color.cardview_dark_background));
        this.mEnergy.setTextColor(getResources().getColor(R.color.cardview_dark_background));
        this.mSafe.setTextColor(getResources().getColor(R.color.cardview_dark_background));
        this.mMonitor.setTextColor(getResources().getColor(R.color.cardview_dark_background));
    }

    private void setSelect(int paramInt)
    {
        setTab(paramInt);
        this.mViewPager.setCurrentItem(paramInt);
        if(paramInt==0){
            toolbar.setVisibility(View.VISIBLE);
        }
        else {
            toolbar.setVisibility(View.GONE);
        }

    }

    private void setTab(int paramInt)
    {
        resetTextViewColor();
        switch (paramInt)
        {


            case 0:
                this.mControl.setTextColor(getResources().getColor(R.color.cardview_shadow_start_color));
                break;
            case 1:
                this.mMonitor.setTextColor(getResources().getColor(R.color.cardview_shadow_start_color));
                break;
            case 2:
            this.mEnergy.setTextColor(getResources().getColor(R.color.cardview_shadow_start_color));
                break;
            case 3:
            this.mSafe.setTextColor(getResources().getColor(R.color.cardview_shadow_start_color));
                break;
        }
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.id_tab_control:
            setSelect(0);
            break;
            case R.id.id_tab_monitor:
            setSelect(1);
                break;
            case R.id.id_tab_energy:

            setSelect(2);
                break;
            case R.id.id_tab_safe:

            setSelect(3);
                break;
        }
    }





    private void initEvent()
    {
        mTabSafe.setOnClickListener(this);
        mTabMonitor.setOnClickListener(this);
        mTabControl.setOnClickListener(this);
        mTabEnergy.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this).setTitle("退出").setMessage("您真的要退出吗？").setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create().show();
    }
}
