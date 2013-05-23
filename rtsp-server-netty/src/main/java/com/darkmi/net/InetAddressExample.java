package com.darkmi.net;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InetAddressExample {

	public static void main(String[] args) {

		try {
			//1.��ȡ����������ӿ��б�
			//��̬����getNetworkInterface()����һ���б�,���а����ո�����ÿһ���ӿ�����Ӧ��NetworkInterface��ʵ����
			Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
			if (interfaceList == null) {
				System.out.println("--No interface found--");
			} else {
				while (interfaceList.hasMoreElements()) {
					NetworkInterface iface = interfaceList.nextElement();
					System.out.println("interface " + iface.getName() + ":");
					Enumeration<InetAddress> addrList = iface.getInetAddresses();

					if (!addrList.hasMoreElements()) {
						System.out.println("\t(No address for this interface)");
					}
					while (addrList.hasMoreElements()) {
						InetAddress address = addrList.nextElement();
						System.out.print("\tAddress"
								+ ((address instanceof Inet4Address ? "(v4)"
										: (address instanceof Inet6Address ? "(v6)" : "(?)"))));
						System.out.println(": " + address.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

		for (String host : args) {
			try {
				System.out.println(host + ":");
				InetAddress[] addressList = InetAddress.getAllByName(host);
				for (InetAddress address : addressList) {
					System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
				}

			} catch (UnknownHostException e) {
				System.out.println("\tUnable tofind addrss for" + host);
			}
		}
	}
}
