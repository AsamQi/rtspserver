package com.darkmi.study.mina;


public class Demo1Server {

	//	private static int PORT = 3005;
	//
	//	public static void main(String[] args) {
	//		IoAcceptor acceptor = null;   // ��������
	//		try {
	//			// ����һ����������server�˵�Socket
	//			acceptor = new NioSocketAcceptor();
	//			// ���ù�������ʹ��Mina�ṩ���ı����з����������
	//			acceptor.getFilterChain().addLast( //�����Ϣ������
	//					"codec",
	//					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
	//							.forName("UTF-8"),
	//							LineDelimiter.WINDOWS.getValue(),
	//							LineDelimiter.WINDOWS.getValue())));
	//			// ���ö�ȡ���ݵĻ�������С
	//			acceptor.getSessionConfig().setReadBufferSize(2048);
	//			// ��дͨ��10�����޲����������״̬
	//			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	//			// ���߼�������
	//			acceptor.setHandler(new Demo1ServerHandler()); // ���ҵ����
	//			// �󶨶˿�
	//			acceptor.bind(new InetSocketAddress(PORT));
	//			logger.info("����������ɹ�...     �˿ں�Ϊ��" + PORT);
	//		} catch (Exception e) {
	//			logger.error("����������쳣....", e);
	//			e.printStackTrace();
	//		}
	//	}
}
