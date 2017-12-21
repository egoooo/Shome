package newjohn.com.shome.udp;

import java.net.SocketException;

/**
 * Created by Administrator on 2017/10/9.
 */

public class Control {
    private  UDPConnection  udpConnection;
    private byte[] message = new byte[15];

    public Control() throws SocketException {
        udpConnection=new UDPConnection();
        udpConnection.startConnection();
    }

    public void ControlCurtain()
    {
        udpConnection.sendUDPdata(message,message.length);
    }

    public void turnOnTv()
    {
        message[0]=(byte)0x68;
        message[1]=(byte)0xcc;
        message[2]=(byte)0x6f;
        message[3]=(byte)0x01;
        message[4]=(byte)0x01;
        message[5]=(byte)0xf1;
        message[6]=(byte)0x78;
        message[7]=(byte)0x68;
        message[8]=(byte)0xaa;
        message[9]=(byte)0x16;
        udpConnection.sendUDPdata(message,message.length);


    }
    public void turnOffTv()
    {
        message[0]=(byte)0x68;
        message[1]=(byte)0xcc;
        message[2]=(byte)0x6f;
        message[3]=(byte)0x00;
        message[4]=(byte)0x01;
        message[5]=(byte)0xf1;
        message[6]=(byte)0x78;
        message[7]=(byte)0x68;
        message[8]=(byte)0xaa;
        message[9]=(byte)0x16;
        udpConnection.sendUDPdata(message,message.length);
    }

    public void playMusic()
    {
        message[0]=(byte)0x68;
        message[1]=(byte)0xcc;
        message[2]=(byte)0x62;
        message[3]=(byte)0x00;
        message[4]=(byte)0x01;
        message[5]=(byte)0xf1;
        message[6]=(byte)0x78;
        message[7]=(byte)0x68;
        message[8]=(byte)0xaa;
        message[9]=(byte)0x16;
        udpConnection.sendUDPdata(message,message.length);
    }

    public void playvideo()
    {
        message[0]=(byte)0x68;
        message[1]=(byte)0xcc;
        message[2]=(byte)0x61;
        message[3]=(byte)0x00;
        message[4]=(byte)0x01;
        message[5]=(byte)0xf1;
        message[6]=(byte)0x78;
        message[7]=(byte)0x68;
        message[8]=(byte)0xaa;
        message[9]=(byte)0x16;
        udpConnection.sendUDPdata(message,message.length);

    }

    public void turnOnAirConditioner()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x12;// 客厅空调判断位
        message[3] = (byte) 0x01;//
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }

    public void turnOffAirConditioner()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x12;
        message[3] = (byte) 0x00;
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOnFanInBathRoom()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x11;// 卫生间风扇判断位
        message[3] = (byte) 0x01;
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }

    public void turnOffFanInBathRoom()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x11;
        message[3] = (byte) 0x00;
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }

    public void turnOnLightBelt()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x13;//
        message[3] = (byte) 0x01;// 开灯带
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }


    public void turnOffLightBelt()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x13;
        message[3] = (byte) 0x00;
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOnLightInBathRoom()
    {
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOffLightInBathRoom()
    {
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOnLightInBedRoom()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x52;
        message[3] = (byte) 0x01;
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOffLightInBedRoom()
    {
        message[0] = (byte) 0x68;
        message[1] = (byte) 0xcc;
        message[2] = (byte) 0x52;
        message[3] = (byte) 0x00;
        message[4] = (byte) 0x01;
        message[5] = (byte) 0xf1;
        message[6] = (byte) 0x78;
        message[7] = (byte) 0x68;
        message[8] = (byte) 0xaa;
        message[9] = (byte) 0x16;
        udpConnection.sendUDPdata(message,message.length);
    }

    public void turnOnLightInGuestBedroom()
    {
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOffLightInGuestBedroom()
    {
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOnLightInLivingRoom(int paramInt)
    {
        udpConnection.sendUDPdata(message,message.length);
    }


    public void turnOffLightInLivingRoom()
    {
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOnSmokeExhausterInKitchen()
    {
        udpConnection.sendUDPdata(message,message.length);
    }
    public void turnOffSmokeExhausterInKitchen()
    {
        udpConnection.sendUDPdata(message,message.length);
    }

    public void turnOnWashingMachine()
    {
        udpConnection.sendUDPdata(message,message.length);

    }

    public void turnOffWashingMachine()
    {
        udpConnection.sendUDPdata(message,message.length);
    }












}
