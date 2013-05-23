package com.darkmi.proxy;

/**
*���������ɫ������ָ��ƴ����̡�������ҲҪʵ����sellInterface�ӿ��⣬�����к��
*����RedWineFactory ��������ã��Ӷ�ʹ�����ڵ�����ʵ����ǰ����һЩ��Ҫ����
*/
public class RedWineProxy implements SellInterface {
	//����һ��RedWineFactory���������
	private RedWineFactory redWineFactory;

	//��������
	private static int sell_count = 0;

	public Object sell() {
		if (checkUser()) {//��ͨ�����������ɫ�����ǿ�������ʵ�����ɫ������ǰ��һЩ����Ȩ���жϵ�����
			Object obj = redWineFactory.sell();
			sell_count++;//ͬ�����ڵ��ú�����Ҳ����ִ��һЩ����Ķ�����
			return obj;
		} else {
			throw new RuntimeException();
		}
	}

	protected boolean checkUser() {
		//do something
		return true;
	}

	public static void main(String agr[]) {
		SellInterface sell = new RedWineProxy();
		sell.sell();
	}
}
