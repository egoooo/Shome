package newjohn.com.shome.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.net.SocketException;

import newjohn.com.shome.R;
import newjohn.com.shome.udp.Control;
import newjohn.com.shome.udp.UDPConnection;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ControlFrag extends Fragment {
    CardView cardView_kt;
    CardView cardView_ws;
    CardView cardView_cf;
    CardView cardView_wsj;
    CardView cardView_yt;
    CardView cardView_cw;
    CardView cardView_xyf;
    Control control;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view1 = inflater.inflate(R.layout.control_layout, container, false);
        cardView_kt = view1.findViewById(R.id.kt);
        cardView_ws=view1.findViewById(R.id.ws);
        cardView_cf=view1.findViewById(R.id.cf);
        cardView_wsj=view1.findViewById(R.id.wsj);
        cardView_yt=view1.findViewById(R.id.yt);
        cardView_cw=view1.findViewById(R.id.cw);
        cardView_xyf=view1.findViewById(R.id.xyf);


        try {
            control=new Control();
        } catch (SocketException e) {
            e.printStackTrace();
        }


        cardView_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view1);

            }
        });

        cardView_ws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view1);
            }
        });

        cardView_cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKitchenWindow(view1);
            }
        });

        cardView_ws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBedroomWindow(view1);
            }
        });

        cardView_wsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBathroomWindow(view1);
            }
        });
        cardView_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cardView_cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGuestroomWindow(view1);

            }
        });
        cardView_xyf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view1;
    }


    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_layout, null);
        TextView tvOn=contentView.findViewById(R.id.tv_on);
        TextView tvOff=contentView.findViewById(R.id.tv_off);
        TextView music=contentView.findViewById(R.id.music);
        TextView video=contentView.findViewById(R.id.video);
        TextView airOn=contentView.findViewById(R.id.air_on);
        TextView airOff=contentView.findViewById(R.id.air_off);
        TextView beltOn=contentView.findViewById(R.id.belt_on);
        TextView beltOff=contentView.findViewById(R.id.belt_off);

        tvOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk",Toast.LENGTH_LONG).show();
                control.turnOnTv();
            }
        });
        tvOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk1",Toast.LENGTH_LONG).show();
                control.turnOffTv();
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.playMusic();
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.playvideo();
            }
        });
        airOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.turnOnAirConditioner();
            }
        });
        airOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.turnOffAirConditioner();
            }
        });

        beltOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.turnOnLightBelt();
            }
        });

        beltOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.turnOffLightBelt();
            }
        });



        final PopupWindow popupWindow = new PopupWindow(contentView,1000
                , LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }




    private void showKitchenWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_kitchen, null);
        TextView hookOn=contentView.findViewById(R.id.hood_on);
        TextView hookOff=contentView.findViewById(R.id.hood_off);


        hookOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk",Toast.LENGTH_LONG).show();
                control.turnOnFanInBathRoom();
            }
        });
       hookOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk1",Toast.LENGTH_LONG).show();
                control.turnOffTv();
            }
        });



        final PopupWindow popupWindow = new PopupWindow(contentView,1000
                , LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }


    private void showBedroomWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_bedroom, null);
        TextView bedOn=contentView.findViewById(R.id.bedroom_on);
        TextView bedOff=contentView.findViewById(R.id.bedroom_off);


        bedOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk",Toast.LENGTH_LONG).show();
                control.turnOnFanInBathRoom();
            }
        });
        bedOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk1",Toast.LENGTH_LONG).show();
                control.turnOffTv();
            }
        });



        final PopupWindow popupWindow = new PopupWindow(contentView,1000
                , LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }




    private void showBathroomWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_bathroom, null);
        TextView bathOn=contentView.findViewById(R.id.bath_fan_on);
        TextView bathOff=contentView.findViewById(R.id.bath_fan_off);


       bathOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk",Toast.LENGTH_LONG).show();
                control.turnOnFanInBathRoom();
            }
        });
        bathOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk1",Toast.LENGTH_LONG).show();
                control.turnOffFanInBathRoom();
            }
        });



        final PopupWindow popupWindow = new PopupWindow(contentView,1000
                , LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }


    private void showGuestroomWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_guestroom, null);
        TextView guestOn=contentView.findViewById(R.id.guest_on);
        TextView guestOff=contentView.findViewById(R.id.guest_off);


        guestOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk",Toast.LENGTH_LONG).show();
                control.turnOnFanInBathRoom();
            }
        });
        guestOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"kkkk1",Toast.LENGTH_LONG).show();
                control.turnOffFanInBathRoom();
            }
        });



        final PopupWindow popupWindow = new PopupWindow(contentView,1000
                , LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
}




