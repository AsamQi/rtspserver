package com.darkmi.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
*������һ��Ҫʵ����InvocationHandler�ӿ�
*/
public class ProxyObject implements InvocationHandler {
	private Object proxy_obj;

	ProxyObject(Object obj) {
		this.proxy_obj = obj;
	}

	public static Object factory(Object obj) {
		Class cls = obj.getClass();
		//ͨ��Proxy���newProxyInstance���������ش������
		return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new ProxyObject(obj));
	}

	/**
	*ʵ��InvocationHandler�ӿڵ�invoke
	*/
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("��������ǰ��������:   " + method);
		if (args != null) {
			//��ӡ�����б�
			System.out.println("������  " + args.length + "    ������");
			for (int i = 0; i < args.length; i++) {
				System.out.println(args[i]);
			}
		}
		//���÷�����ƶ�̬����ԭ����ķ���
		Object mo = method.invoke(proxy_obj, args);
		System.out.println("�������ú���д��� :   " + method);
		return mo;
	}

	//���Դ���    
	public static void main(String agr[]) {
		SellInterface si = (SellInterface) factory(new RedWineFactory());
		si.sell();
	}
}
