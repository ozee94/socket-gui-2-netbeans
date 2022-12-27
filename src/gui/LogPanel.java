/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author oooaaa
 */
public class LogPanel {

	public static JTextArea ta = null;
	JScrollPane sp;

	public LogPanel() {
		sp = new JScrollPane();
		ta = new JTextArea();

		ta.setColumns(20);
		ta.setLineWrap(true);
		ta.setRows(5);
		ta.setToolTipText("");
		ta.setWrapStyleWord(true);
		ta.setEditable(false);

		sp.setViewportView(ta);

		DefaultCaret caret = (DefaultCaret) ta.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

	}

	public JScrollPane getLogPanel() {
		return sp;
	}

	public static void setLog(String txt) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String temp = "[" + sdFormat.format(now) + "] " + txt;

		if (ta.getText().length() > 0) {
			ta.setText(ta.getText() + "\n" + temp);
		} else {
			ta.setText(temp);
		}
	}
}
