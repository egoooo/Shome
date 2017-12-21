package newjohn.com.shome.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import newjohn.com.shome.R;
import newjohn.com.shome.ui.TableAdapter;
import newjohn.com.shome.bean.Electricity;

/**
 * Created by Administrator on 2017/9/29.
 */

public class EnergyFrag extends Fragment {

    Button begin;
    Button end;
    Button tongji;
    private Calendar cal;
    private int year,month,day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.energy_layout,container,false);
//设置表格标题的背景颜色
        ViewGroup tableTitle = (ViewGroup)view.findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));
        List<Electricity> list= new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new Electricity("电脑",2.0,220.0,440.0,345.0,"正常"));
        }
        ListView tableListView = (ListView) view.findViewById(R.id.list);

        TableAdapter adapter = new TableAdapter(getContext(), list);

        tableListView.setAdapter(adapter);


        begin=view.findViewById(R.id.begin);
        end=view.findViewById(R.id.end);
        tongji=view.findViewById(R.id.tongji);

        getDate();
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {


                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        begin.setText("开始日期："+year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(getContext(),listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {


                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        end.setText("截止日期："+year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(getContext(),listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();

            }
        });


        tongji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChartWindow(view);
            }
        });


        return  view;
    }


    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒  
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }



    private void showChartWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.chart_layout, null);
        /**
         * 1.在布局文件中定义
         */
        LineChart lineChart = contentView.findViewById(R.id.chart);


        /**
         *  2.绑定view 设置LineChart显示属性
         */

        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.parseColor("#66CDAA"));
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
        lineChart.setBackgroundColor(Color.WHITE);

        /**
         * 3.绑定数据
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();

        values1.add(new Entry(4, 10));
        values1.add(new Entry(6, 15));
        values1.add(new Entry(9, 20));
        values1.add(new Entry(12, 5));
        values1.add(new Entry(15, 30));

        values2.add(new Entry(3, 110));
        values2.add(new Entry(6, 115));
        values2.add(new Entry(9, 130));
        values2.add(new Entry(12, 85));
        values2.add(new Entry(15, 90));

        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;
        LineDataSet set2;

        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(1);
            set2.setValues(values2);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "测试数据1");
            set1.setColor(Color.parseColor("#66CDAA"));
            set1.setCircleColor(Color.parseColor("#66CDAA"));
            set1.setLineWidth(3f);//设置线宽
            set1.setCircleRadius(7f);//设置焦点圆心的大小
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(5f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(Color.parseColor("#66CDAA"));//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }


            });
//            if (Utils.getSDKInt() >= 18) {
//                // fill drawable only supported on api level 18 and above
//                //Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
//                //set1.setFillDrawable(drawable);//设置范围背景填充
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }

            //设置数据2
            set2 = new LineDataSet(values2, "测试数据2");
            set2.setColor(Color.parseColor("#B0E2FF"));
            set2.setCircleColor(Color.parseColor("#B0E2FF"));
            set2.setLineWidth(3f);
            set2.setCircleRadius(7f);
            set2.setValueTextSize(10f);

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2);
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();


            /**
             * 4.设置X轴的显示效果
             */
            //获取此图表的x轴
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
            xAxis.setDrawAxisLine(true);//是否绘制轴线
            xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
            xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
            //xAxis.setTextSize(20f);//设置字体
            //xAxis.setTextColor(Color.BLACK);//设置字体颜色
            //设置竖线的显示样式为虚线
            //lineLength控制虚线段的长度
            //spaceLength控制线之间的空间
            xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
            xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
            xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        xAxis.setLabelCount(10);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符
          xAxis.setAxisLineColor(Color.parseColor("#66CDAA"));
          xAxis.setAxisLineWidth(5f);





            /**
             * 5.设置Y轴的显示效果
             */
            /**
             * Y轴默认显示左右两个轴线
             */
            //获取右边的轴线
            YAxis rightAxis = lineChart.getAxisRight();
            //设置图表右边的y轴禁用
            rightAxis.setEnabled(false);
            //获取左边的轴线
            YAxis leftAxis = lineChart.getAxisLeft();
            //设置网格线为虚线效果
            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            //是否绘制0所在的网格线
            leftAxis.setDrawZeroLine(false);
            leftAxis.setAxisLineColor(Color.parseColor("#EE8262"));
            leftAxis.setAxisLineWidth(5f);

            /**
             * 6.设置与图表交互
             */


            lineChart.setTouchEnabled(true); // 设置是否可以触摸
            lineChart.setDragEnabled(true);// 是否可以拖拽
            lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
            lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
            lineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
            lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
            lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
            lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
            lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
            lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。


            /**
             * 7.设置图例
             */

            Legend l = lineChart.getLegend();//图例
            l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);//设置图例的位置
            l.setTextSize(10f);//设置文字大小
            l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
            l.setFormSize(10f); // 设置Form的大小
            l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
            l.setFormLineWidth(10f);//设置Form的宽度






            final PopupWindow popupWindow = new PopupWindow(contentView, 1500
                    , 900, true);

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
}
