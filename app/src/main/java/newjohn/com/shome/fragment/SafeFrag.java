package newjohn.com.shome.fragment;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

import newjohn.com.shome.R;
import newjohn.com.shome.bean.DataBean;
import newjohn.com.shome.udp.UDPConnection;


/**
 * Created by Administrator on 2017/9/29.
 */

public class SafeFrag extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioButtonIn;
    RadioButton radioButtonOut;
    ImageView doorAlert;
    ImageView bodyAlert;
    WebView mWebView;
    int checkedID;
    DataBean dataBean=new DataBean();
    int count=0;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                dataBean= (DataBean) msg.obj;
                Log.i("dataBean:",dataBean.toString());
                alert();
            }
        }
    };
    Vibrator vibrator;
    MediaPlayer m;
    MediaPlayer  mpMediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.safe_layout,container,false);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        radioGroup =view.findViewById(R.id.group);
        radioButtonIn=view.findViewById(R.id.in);
        radioButtonOut=view.findViewById(R.id.out);
        doorAlert=view.findViewById(R.id.dooralert);
        bodyAlert=view.findViewById(R.id.bodyalert);
        mWebView=view.findViewById(R.id.webView);
        vibrator= (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);




        mWebView.loadUrl("http://192.168.1.105:8888/javascript.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.requestFocus();
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("http://192.168.1.105:8888/javascript.html");
                return true;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                checkedID=i;

            }
        });

        UDPConnection udpConnection=new UDPConnection();
        udpConnection.startConnection();
        udpConnection.receiveUDPdata(handler);







        return view;
    }


    public void alert()  {
        Log.i("门径", dataBean.getParlorMagnetic()+"count:"+count+"ID"+checkedID);
        if (checkedID==radioButtonOut.getId()){
            if (dataBean.getParlorMagnetic().equals("1")&&count<1){

                vibrator.vibrate(new long[]{100,100,100,1000},-1);
                doorAlert.setImageResource(R.drawable.off);
                count++;
                play();
               new AlertDialog.Builder(getContext()).setTitle("警告").setMessage("检测到门被打开，请核对是否为本人操作").setNeutralButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       count=0;
                       vibrator.cancel();

                       mpMediaPlayer.release();

                   }
               }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                   @Override
                   public void onDismiss(DialogInterface dialogInterface) {
                       count=0;
                       vibrator.cancel();

                       mpMediaPlayer.release();
                   }
               }).create().show();








            }

        }
    }



    protected void play() {
        System.out.println("play");
        mpMediaPlayer = MediaPlayer.create(getContext(), R.raw.noisy);
        try {
      //    mpMediaPlayer.prepare();

            if(mpMediaPlayer.isPlaying()){
                mpMediaPlayer.stop();
                mpMediaPlayer.release();
                mpMediaPlayer = MediaPlayer.create(getContext(), R.raw.noisy);
            }

            mpMediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        mpMediaPlayer.start();
        mpMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//设置重复播放
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mpMediaPlayer.start();
                mpMediaPlayer.setLooping(true);
            }
        });
    }
}
