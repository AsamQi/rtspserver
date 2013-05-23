package com.darkmi.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPEchoClient {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		if (args.length < 2 || args.length > 3) {
			throw new IllegalArgumentException("Parameter(s):<Server> <Word> [<Port>]");
		}

		String server = args[0];

		byte[] data = args[1].getBytes();

		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		//1.����һ��Socketʵ�������캯����ָ����Զ�������Ͷ˿ڽ���һ��TCP����
		Socket socket = new Socket(server, servPort);
		System.out.println("Connected to server... sending echo string");

		/**
		 *2. ͨ���׽��ֵ��������������ͨ�ţ�һ��Socket����ʵ������һ��InputStream��һ��OutputStream�����ǵ��÷�ͬ������Java�����������
		 */
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		out.write(data);

		int totalBytesRcvd = 0;
		int bytesRcvd;

		while (totalBytesRcvd < data.length) {
			if ((bytesRcvd = in.read(data, totalBytesRcvd, data.length - totalBytesRcvd)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
		}
		System.out.println("Receved: " + new String(data));

		//3.ʹ��Socet���close���������ر�����
		socket.close();
	}
}
