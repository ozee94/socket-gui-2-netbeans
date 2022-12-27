/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import socket.SocketClient;

/**
 * @author oooaaa
 */
public class SocketConnectPanel {

	SocketClient socket = SocketClient.getInstance();
	String ip = "10.0.100.138";
	int port = 5331;

	JPanel panel;

	JTextField tfIp;
	public static JTextField tfPort;

	public SocketConnectPanel() {
		panel = new JPanel();

		JLabel lbIp = new JLabel();
		JLabel lbPort = new JLabel();

		tfIp = new JTextField();
		tfPort = new JTextField();
		JButton jbConnect = new JButton();
		JButton jbDisconnect = new JButton();
		Label lbLogo = new Label();
		JLabel lbClose = new JLabel();

		panel.setBackground(new java.awt.Color(44, 67, 144));

		lbIp.setForeground(new java.awt.Color(255, 255, 255));
		lbIp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbIp.setText("IP");

		tfIp.setText("10.0.100.138");

		lbPort.setForeground(new java.awt.Color(255, 255, 255));
		lbPort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbPort.setText("PORT");

		tfPort.setText("5331");

		jbConnect.setText("연결");
		jbConnect.addActionListener(e -> connectServerSocket());

		jbDisconnect.setText("연결해제");
		jbDisconnect.addActionListener(e -> disconnectServerSocket());

		lbLogo.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
		lbLogo.setForeground(new java.awt.Color(255, 255, 255));
		lbLogo.setText("TTA 상호연동테스트기 (socket)");

		lbClose.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 36)); // NOI18N
		lbClose.setForeground(new java.awt.Color(255, 255, 255));
		lbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbClose.setText("");
		lbClose.setToolTipText("");

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
		panel.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelLayout.createSequentialGroup()
										.addComponent(lbIp, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(tfIp, javax.swing.GroupLayout.PREFERRED_SIZE, 198,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 198,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jbConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jbDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										panelLayout.createSequentialGroup().addGap(11, 11, 11)
												.addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 748,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbClose, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(tfPort)
								.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jbConnect).addComponent(lbIp)
										.addComponent(tfIp, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lbPort).addComponent(jbDisconnect)))
						.addContainerGap()));
	}

	public JPanel getSocketConnectPanel() {
		return panel;
	}

	public void connectServerSocket() {
		if (tfIp != null && tfPort != null && tfIp.getText().length() > 0 && tfPort.getText().length() > 0) {
			socket.setSocketInfo(tfIp.getText(), Integer.parseInt(tfPort.getText()));
			socket.connect();
		} else {
			LogPanel.setLog("ip와 port 정보를 입력해주세요");
		}
	}

	public void disconnectServerSocket() {
		if (socket != null) {
			socket.disconnect();
		}
	}
}
