package com.darkmi.proxy;

public class RedWineFactory implements SellInterface {
	public Object sell() {
		System.out.println("��ʵ�����ɫRedWineFactory ��������");
		return new Object();
	}
}
