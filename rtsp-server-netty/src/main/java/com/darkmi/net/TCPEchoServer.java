package com.darkmi.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPEchoServer {
	private static final int BUFSIZE = 32;

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("RTSP/1.0 200 OK\r\n");
		sb.append("CSeq: 1\r\n");
		sb.append("Date: 12 Dec 2011 06:28:54 GMT\r\n");
		sb.append("Session: 49462271-1196354595;timeout=60\r\n");
		sb.append("Transport: MP2T/AVP;unicast;destination=192.168.14.102;port=49156\r\n");
		sb.append("Range: npt=-233.800\r\n");
		sb.append("Location: rtsp://192.168.7.134:8070/movie---2142---movie_387\r\n");
		
		
		//if (args.length != 1) {
		//	throw new IllegalArgumentException("Parameter(s):<Port>");
		//}

		int servPort = 444;

		//1.����һ��ServerSocketʵ����ָ�����ض˿ڡ����׽��ֵĹ������������ض˿��յ������ӡ�
		ServerSocket servSock = new ServerSocket(servPort);

		//int recvMsgSize;

		byte[] receiveBuf = new byte[BUFSIZE];

		//2.�ظ�ִ��
		while (true) {
			//a.����ServerSocket��accept()�����Ի�ȡ��һ���ͻ������ӡ�
			//�����½����Ŀͻ������ӣ�����һ��Socketʵ��������accept()��������
			Socket clntSock = servSock.accept();
			SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
			System.out.println("Handling client at " + clientAddress);

			//b,ʹ�������ص�Socketʵ����InputStream��OutputStream��ͻ��˽���ͨ��
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			while ((in.read(receiveBuf)) != -1) {
				//out.write(receiveBuf, 0, recvMsgSize);
				out.write(sb.toString().getBytes());
			}

			//c��ͨ����ɺ�ʹ��Socket��close()�����رոÿͻ����׽�������
			clntSock.close();
		}
	}
}