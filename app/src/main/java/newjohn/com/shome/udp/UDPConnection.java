package newjohn.com.shome.udp;

/**
 * Created by Administrator on 2017/9/28.
 */

import java.io.IOException;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import newjohn.com.shome.bean.DataBean;

public class UDPConnection {


    public int recvNum = 0;
    private static UDPConnection instance;
    private static String ipStr="192.168.1.105";
    public static String ipCamera="";
    private static int port=6530;
    private Thread recvUDPThread;
    public byte[] recvData = new byte[40];
    private static DatagramSocket UDPSocket;
    public static int recvFlag = 0;
    public static DataBean dataBean=new DataBean();

    public void startConnection(){
        try {
            Log.i("kjkk","jjjjj");
            UDPSocket=new DatagramSocket();
            sendUDPdata(new byte[0],0);
            receiveUDPdata();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static String washroomQuality = "0.0";
    public static String parlorTemperature = "0.0";
    public static String parlorHumidity = "0.0";
    public static String parlorInfrared = "0.0";
    public static String kitchenMethane = "0.0";
    public static String kitchenSmoke = "0.0";
    public static String kitchenFlame = "0.0";
    public static String bedroomTemperature = "0.0";
    public static String bedroomHumidity = "0.0";
    public static String parlorMagnetic = "0.0";


    //获取UDP实例对象
    public static UDPConnection getInstance() {
        if (instance == null) {

                instance = new UDPConnection();

        }
        return instance;
    }

    //绑定Socket端口
    public void bindUDPSocket() {
        try {
            // 锟襟定端匡拷
            if (UDPSocket == null) {
                UDPSocket = new DatagramSocket(8080);//本地端口号可设置成未被手机使用的端口号，不用必须是6530
//				UDPSocket = new DatagramSocket(null);
//				UDPSocket.setReuseAddress(true);
//				UDPSocket.bind(new InetSocketAddress(8080));//版本高低导致UDP Socket的创建存在区别，机顶盒上Android系统版本较高，其app则使用这几行代码来建立UDP Socket本地端口


            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    //设置地址和端口
    public static void setAddress(String ipStr1,String ipStr2, int port1) {
        ipStr = ipStr1;
        ipCamera = ipStr2;
        port = port1;

    }




    public void recvData(){
        Log.i("111111111", "开始解析数据");
        Log.i("data：  ",recvData.length+"*"+Arrays.toString(recvData));
        String wq,pt,ph,bt,bh,km,ks ;

        switch (recvData[2]) {
            case 0x07://空气质量
                wq = String.valueOf(recvData[10]+recvData[11]*0.01);
                washroomQuality=dealData(wq);
                dataBean.setWashroomQuality(washroomQuality);
                System.out.println("washroomQuality:"+washroomQuality);
                break;

            case 0x01:
                switch (recvData[3]) {
                    case 0x01://客厅温湿度
                        pt = String.valueOf(recvData[8]+recvData[9]*0.01);
                        ph = String.valueOf(recvData[10]+recvData[11]*0.01);
                        parlorTemperature=dealData(pt);
                        parlorHumidity=dealData(ph);
                        dataBean.setParlorTemperature(parlorTemperature);
                        dataBean.setParlorHumidity(parlorHumidity);
                        System.out.println("parlorTemperature:"+parlorTemperature);
                        System.out.println("parlorHumidity:"+parlorHumidity);
                        break;
                    case 0x02://主卧温湿度
                        bt = String.valueOf(recvData[8]+(recvData[9])*0.01);
                        bh = String.valueOf(recvData[10]+(recvData[11])*0.01);
                        bedroomTemperature=dealData(bt);
                        bedroomHumidity=dealData(bh);
                        dataBean.setBedroomTemperature(bedroomTemperature);
                        dataBean.setBedroomHumidity(bedroomHumidity);
                        System.out.println("bedroomTemperature:"+bedroomTemperature);
                        System.out.println("bedroomHumidity:"+  bedroomHumidity);

                        break;
                }break;

            case 0x02://厨房天然气
                km = String.valueOf(recvData[10]+(recvData[11])*0.01);
                kitchenMethane=dealData(km);
                dataBean.setKitchenMethane(kitchenMethane);
                System.out.println("kitchenMethane:"+kitchenMethane);
                break;
            case 0x03://厨房烟雾
                ks = String.valueOf(Math.abs(recvData[10]+(recvData[11])*0.01));
                kitchenSmoke=dealData(ks);
                dataBean.setKitchenSmoke(kitchenSmoke);
                System.out.println("kitchenSmoke:"+kitchenSmoke);
                break;

            case 0x06://客厅红外
                parlorInfrared=String.valueOf(recvData[10]+(recvData[11])*1);
                System.out.println("parlorInfrared:"+parlorInfrared);
                dataBean.setParlorInfrared(parlorInfrared);
			/* if(parlorInfrared.equals("1")){
					ParlorInfrared.infrared_flag=1;
					System.out.println("infrared_flag"+ParlorInfrared.infrared_flag);
				}*/

                break;
            case 0x09://厨房火焰
                kitchenFlame=String.valueOf(recvData[10]+recvData[11]*1);
                dataBean.setKitchenFlame(kitchenFlame);
                System.out.println("kitchenFlame:"+kitchenFlame);
		   /* if(kitchenFlame.equals("1")){
				Kitchen.kitcen_flame_flag=1;
				System.out.println("kitcen_flame_flag"+Kitchen.kitcen_flame_flag);

			}*/

                break;

            case 0x0b: //客厅门禁
                parlorMagnetic=String.valueOf(recvData[10]+recvData[11]*1);
                dataBean.setParlorMagnetic(parlorMagnetic);
                System.out.println("parlorMagnetic"+parlorMagnetic);
			/* if(parlorMagnetic.equals("1")){
					ParlorMagnetic.magnetic_flag=1;

					System.out.println("magnetic_flag"+ParlorMagnetic.magnetic_flag);

				}*/
        }
        Log.i("111111111", "数据解析完毕"+"\n卧室温度"+bedroomTemperature+"\n"+
                "卧室湿度"+bedroomHumidity+"\n厨房flame"+kitchenFlame+
                "\n厨房一氧化碳"+kitchenMethane+"\n厨房烟雾"+kitchenSmoke+
                "\n客厅湿度"+parlorHumidity+"\n客厅红外"+parlorInfrared+
                "\n客厅温度"+parlorTemperature+"\n客厅门禁"+parlorMagnetic+
                "\n浴室空气质量"+washroomQuality);

    }
    public static synchronized void sendUDPdata(final byte[] msg, final int seq) {
        // 开启发送数据线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress serverAddr = InetAddress.getByName(ipStr);
                    DatagramPacket sendDatagramPacket = new DatagramPacket(msg,
                            seq, serverAddr, port);
                    UDPSocket.send(sendDatagramPacket);
                    Log.i("1111111111", "数据发送成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public String dealData(String str) {
        if(str.length()>4)
            str = str.substring(0, 4);
        return str;
    }



    public synchronized void receiveUDPdata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] data = new byte[20];
                DatagramPacket dataPacket = new DatagramPacket(data, data.length);
                while (UDPSocket != null) {
                    try {
                        System.out.println("UDPSocket != null");
                        Log.i("线程:",Thread.currentThread().getName());
                        UDPSocket.receive(dataPacket);

                        recvFlag = 1;
                        recvData = dataPacket.getData();
                        Log.i("recvData:",recvData.toString());
                        recvNum = dataPacket.getLength();
                        recvData();
                    } catch (IOException e) {
                        recvFlag = 0;
                    }
                }

            }
        }).start();



            }



    public synchronized void receiveUDPdata(final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] data = new byte[20];
                DatagramPacket dataPacket = new DatagramPacket(data, data.length);
                while (UDPSocket != null) {
                    try {
                        System.out.println("UDPSocket != null");
                        Log.i("线程:",Thread.currentThread().getName());
                        UDPSocket.receive(dataPacket);

                        recvFlag = 1;
                        recvData = dataPacket.getData();
                        Log.i("recvData",recvData.toString());
                        recvNum = dataPacket.getLength();
                        recvData(handler);
                    } catch (IOException e) {
                        recvFlag = 0;
                    }
                }

            }
        }).start();



    }




    public void recvData(Handler handler){
        Log.i("111111111", "开始解析数据");
        Log.i("data：  ",recvData.length+"*"+Arrays.toString(recvData));
        String wq,pt,ph,bt,bh,km,ks ;

        switch (recvData[2]) {
            case 0x07://空气质量
                wq = String.valueOf(recvData[10]+recvData[11]*0.01);
                washroomQuality=dealData(wq);
                dataBean.setWashroomQuality(washroomQuality);
                System.out.println("washroomQuality:"+washroomQuality);
                break;

            case 0x01:
                switch (recvData[3]) {
                    case 0x01://客厅温湿度
                        pt = String.valueOf(recvData[8]+recvData[9]*0.01);
                        ph = String.valueOf(recvData[10]+recvData[11]*0.01);
                        parlorTemperature=dealData(pt);
                        parlorHumidity=dealData(ph);
                        dataBean.setParlorTemperature(parlorTemperature);
                        dataBean.setParlorHumidity(parlorHumidity);
                        System.out.println("parlorTemperature:"+parlorTemperature);
                        System.out.println("parlorHumidity:"+parlorHumidity);
                        break;
                    case 0x02://主卧温湿度
                        bt = String.valueOf(recvData[8]+(recvData[9])*0.01);
                        bh = String.valueOf(recvData[10]+(recvData[11])*0.01);
                        bedroomTemperature=dealData(bt);
                        bedroomHumidity=dealData(bh);
                        dataBean.setBedroomTemperature(bedroomTemperature);
                        dataBean.setBedroomHumidity(bedroomHumidity);
                        System.out.println("bedroomTemperature:"+bedroomTemperature);
                        System.out.println("bedroomHumidity:"+  bedroomHumidity);

                        break;
                }break;

            case 0x02://厨房天然气
                km = String.valueOf(recvData[10]+(recvData[11])*0.01);
                kitchenMethane=dealData(km);
                dataBean.setKitchenMethane(kitchenMethane);
                System.out.println("kitchenMethane:"+kitchenMethane);
                break;
            case 0x03://厨房烟雾
                ks = String.valueOf(Math.abs(recvData[10]+(recvData[11])*0.01));
                kitchenSmoke=dealData(ks);
                dataBean.setKitchenSmoke(kitchenSmoke);
                System.out.println("kitchenSmoke:"+kitchenSmoke);
                break;

            case 0x06://客厅红外
                parlorInfrared=String.valueOf(recvData[10]+(recvData[11])*1);
                System.out.println("parlorInfrared:"+parlorInfrared);
                dataBean.setParlorInfrared(parlorInfrared);
			/* if(parlorInfrared.equals("1")){
					ParlorInfrared.infrared_flag=1;
					System.out.println("infrared_flag"+ParlorInfrared.infrared_flag);
				}*/

                break;
            case 0x09://厨房火焰
                kitchenFlame=String.valueOf(recvData[10]+recvData[11]*1);
                dataBean.setKitchenFlame(kitchenFlame);
                System.out.println("kitchenFlame:"+kitchenFlame);
		   /* if(kitchenFlame.equals("1")){
				Kitchen.kitcen_flame_flag=1;
				System.out.println("kitcen_flame_flag"+Kitchen.kitcen_flame_flag);

			}*/

                break;

            case 0x0b: //客厅门禁
                parlorMagnetic=String.valueOf(recvData[10]+recvData[11]*1);
                dataBean.setParlorMagnetic(parlorMagnetic);
                System.out.println("parlorMagnetic"+parlorMagnetic);
			/* if(parlorMagnetic.equals("1")){
					ParlorMagnetic.magnetic_flag=1;

					System.out.println("magnetic_flag"+ParlorMagnetic.magnetic_flag);

				}*/
        }
        Log.i("111111111", "数据解析完毕"+"\n卧室温度"+bedroomTemperature+"\n"+
                "卧室湿度"+bedroomHumidity+"\n厨房flame"+kitchenFlame+
                "\n厨房一氧化碳"+kitchenMethane+"\n厨房烟雾"+kitchenSmoke+
                "\n客厅湿度"+parlorHumidity+"\n客厅红外"+parlorInfrared+
                "\n客厅温度"+parlorTemperature+"\n客厅门禁"+parlorMagnetic+
                "\n浴室空气质量"+washroomQuality);


        Message message =Message.obtain();
        message.obj=dataBean;
        message.what=1;
        handler.sendMessage(message);

    }




}
