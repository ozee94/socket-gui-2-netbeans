/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import socket.SocketClient;

/**
 *
 * @author oooaaa
 */
public class SendEventPanel {
	SocketClient socket = SocketClient.getInstance();

	JTabbedPane tabPane;

	JButton jbSend;
	JCheckBox isRepeat;
	JPanel jPanel2;

	JScrollPane jsp_119;
	JScrollPane jsp_112;
	JScrollPane jsp_weak;

	JTextArea jta_119data;
	JTextArea jta_112data;
	JTextArea jta_weakdata;

	JTextField jtfMs;

	Thread thread = null;
	Runnable runnable = null;

	public SendEventPanel() {
		jPanel2 = new JPanel();
		isRepeat = new JCheckBox();
		jbSend = new JButton();
		jtfMs = new JTextField();
		tabPane = new JTabbedPane();
		jsp_119 = new JScrollPane();
		jta_119data = new JTextArea();
		jsp_112 = new JScrollPane();
		jta_112data = new JTextArea();
		jsp_weak = new JScrollPane();
		jta_weakdata = new JTextArea();

		isRepeat.setText("반복보내기(ms)");

		jbSend.setText("전문보내기");
		jbSend.addActionListener(e -> sendData());

		jtfMs.setText("3000");

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(isRepeat)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jtfMs, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
						.addComponent(jbSend, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(isRepeat).addComponent(jbSend).addComponent(jtfMs,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jta_119data.setColumns(20);
		jta_119data.setLineWrap(true);
		jta_119data.setRows(5);
		jta_119data.setText(
				"101020180913165134119UCP201809131651340000000188T20180913165134\u001e화재\u001e교통사고\u001e127.0992389\u001e37.4140687\u001e경기도 성남시 수정구 대왕판교로 815 판교창조경제밸리 기업지원허브\u001e4113111500\u001e20180913165134\u001euser119\u001e;");
		jsp_119.setViewportView(jta_119data);

		tabPane.addTab("119", jsp_119);

		jta_112data.setColumns(20);
		jta_112data.setLineWrap(true);
		jta_112data.setRows(5);
		jta_112data.setToolTipText("");
		jta_112data.setText(
				"101020180917151944112ucp201809171519440000000124YU3024003890\u001eCODE1\u001e강력범죄\u001e127.0992389\u001e37.4140687\u001e경기도 성남시 대왕판교로 815\u001e1111\u001e20180917151944\u001euser112\u001e;");
		jsp_112.setViewportView(jta_112data);

		tabPane.addTab("112", jsp_112);

		jta_weakdata.setColumns(20);
		jta_weakdata.setLineWrap(true);
		jta_weakdata.setRows(5);
		jta_weakdata.setText(
				"101020180917110728WP1UCP201809171107280000000370WP20180917110728\u001e기어S2\u001e127.1012453\u001e37.4089425\u001e경기도 성남시 분당구 대왕판교로 764\u001e4113510900\u001e-\u001e홍길동\u001e010-1111-1111\u001e20081022\u001e남\u001e경기도 성남시 수정구 달래내로 28번길 20-3\u001e홍을동\u001e010-2222-2222\u001e20180917110728\u001ehttp://dev.copynpaste.co.kr:8090/scp/T001.jpg\u001e흰색 상의를 입고 있음\u001e눈이 크고 서양계의 이목구비\u001eSKT001\u001e;\n");
		jsp_weak.setViewportView(jta_weakdata);

		tabPane.addTab("사회적약자", jsp_weak);

		tabPane.addChangeListener(e -> tabHandler());
	}

	public void tabHandler() {
		if (tabPane.getSelectedIndex() == 0) {
			SocketConnectPanel.tfPort.setText("5331");
		} else if (tabPane.getSelectedIndex() == 1) {
			SocketConnectPanel.tfPort.setText("5335");
		} else if (tabPane.getSelectedIndex() == 2) {
			SocketConnectPanel.tfPort.setText("5333");
		}
	}

	public String getData(boolean tabOrData) {
		String data = "";
		if (tabPane.getSelectedIndex() == 0) {
			data = tabOrData ? jta_119data.getText() : "119긴급출동";
		} else if (tabPane.getSelectedIndex() == 1) {
			data = tabOrData ? jta_112data.getText() : "112영상지원";
		} else if (tabPane.getSelectedIndex() == 2) {
			data = tabOrData ? jta_weakdata.getText() : "사회적약자";
		}
		return data;
	}

	public void sendData() {
		if (jbSend.getText().equals("전문보내기")) {
			if (socket.isConnected()) {
				if (isRepeat.isSelected()) {
					sendRepeat(getData(true));
				} else {
					sendOnce(getData(true));
				}
			} else {
				LogPanel.setLog("[❌] 현재 연결되어 있는 소켓이 없습니다");
			}
		} else if (jbSend.getText().equals("해제")) {
			setEnabled(true);
			if (thread != null && runnable != null) {
				thread.interrupt();
				thread = null;
				runnable = null;
			}
		}
	}

	public void sendOnce(String data) {
		if (data.length() > 0) {
			try {
				boolean result = socket.sendData(data);
				if (result) {
					LogPanel.setLog(
							"SEND DATA 👉👉👉 " + getData(false) + "\n===============================================\n"
									+ data + "\n===============================================");
				}
				socket.disconnect();
			} catch (IOException err) {
				LogPanel.setLog("[❌] 전문을 보내는 실패했습니다.\n" + err.getMessage());
			}
		} else {
			LogPanel.setLog("[❌] 전문내용을 입력해주세요");
		}

	}

	public void sendRepeat(String data) {
		final long timeInterval = Integer.parseInt(jtfMs.getText());
		setEnabled(false);
		runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (!Thread.currentThread().isInterrupted()) {
						if (!socket.isConnected())
							socket.connect();
						sendOnce(data);
						Thread.sleep(timeInterval);
					}
				} catch (InterruptedException e) {
					LogPanel.setLog("연속 전문보내기 해제");
					e.printStackTrace();
				}
			}
		};

		thread = new Thread(runnable);
		thread.start();
	}

	public void setEnabled(boolean bol) {
		jbSend.setText(bol ? "전문보내기" : "해제");
		jtfMs.setEditable(bol);
		isRepeat.setEnabled(bol);
	}

	public JPanel getPanel2() {
		return jPanel2;
	}

	public JTabbedPane getTabPane() {
		return tabPane;
	}
}
