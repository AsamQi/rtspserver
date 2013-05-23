package com.darkmi.study;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MySqlTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData rsMetaData;

	//GUI��������
	private JTable table;
	private JTextArea inputQuery;
	private JButton submitQuery;

	public MySqlTest() {
		//Form�ı��� 
		super("����SQL��䣬����ѯ��ť�鿴�����");

		String url = "jdbc:mysql://67.20.120.174:3306/onezesm5_huoxingren";
		String username = "onezesm5_mixh";
		String password = "sweet2dead";
		//���������������������ݿ�
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(url, username, password);
		}
		//����������������쳣
		catch (ClassNotFoundException cnfex) {
			System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�");
			cnfex.printStackTrace();
			System.exit(1); // terminate program 
		}
		//�����������ݿ��쳣
		catch (SQLException sqlex) {
			System.err.println("�޷��������ݿ�");
			sqlex.printStackTrace();
			System.exit(1); // terminate program 
		}
		//������ݿ����ӳɹ�������GUI
		//SQL���
		String test = "SELECT * FROM bbs_forum_activityapply";
		inputQuery = new JTextArea(test, 4, 30);
		submitQuery = new JButton("��ѯ");
		//Button�¼�
		submitQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTable();
			}
		});

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		//��"�����ѯ"�༭���õ� "CENTER"
		topPanel.add(new JScrollPane(inputQuery), BorderLayout.CENTER);
		//��"�ύ��ѯ"��ť���õ� "SOUTH"
		topPanel.add(submitQuery, BorderLayout.SOUTH);
		table = new JTable();
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		//��"topPanel"�༭���õ� "NORTH"
		c.add(topPanel, BorderLayout.NORTH);
		//��"table"�༭���õ� "CENTER"
		c.add(table, BorderLayout.CENTER);
		getTable();
		setSize(500, 300);
		//��ʾForm
		show();
	}

	private void getTable() {
		try {
			//ִ��SQL���
			String query = inputQuery.getText();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			//�ڱ������ʾ��ѯ���
			displayResultSet(resultSet);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	private void displayResultSet(ResultSet rs) throws SQLException {
		//��λ�����һ����¼
		boolean moreRecords = rs.next();
		//���û�м�¼������ʾһ����Ϣ
		if (!moreRecords) {
			JOptionPane.showMessageDialog(this, "��������޼�¼");
			setTitle("�޼�¼��ʾ");
			return;
		}
		Vector columnHeads = new Vector();
		Vector rows = new Vector();
		try {
			//��ȡ�ֶε�����
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); ++i)
				columnHeads.addElement(rsmd.getColumnName(i));
			//��ȡ��¼��
			do {
				rows.addElement(getNextRow(rs, rsmd));
			} while (rs.next());
			//�ڱ������ʾ��ѯ���
			table = new JTable(rows, columnHeads);
			JScrollPane scroller = new JScrollPane(table);
			Container c = getContentPane();
			c.remove(1);
			c.add(scroller, BorderLayout.CENTER);
			//ˢ��Table
			c.validate();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		Vector currentRow = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			currentRow.addElement(rs.getString(i));
		//����һ����¼ 
		return currentRow;
	}

	public void shutDown() {
		try {
			//�Ͽ����ݿ�����
			connection.close();
		} catch (SQLException sqlex) {
			System.err.println("Unable to disconnect");
			sqlex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		final MySqlTest app = new MySqlTest();
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.shutDown();
				System.exit(0);
			}
		});
	}
}
