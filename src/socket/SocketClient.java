package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import gui.LogPanel;

public class SocketClient {

	public static SocketClient instance = null;

	Socket socket = null;

	String ip = "";
	int port = 0;

	public static SocketClient getInstance() {
		if (instance == null) {
			instance = new SocketClient();
		}
		return instance;
	}

	public void setSocketInfo(String ip, int port) {
		if (!(socket != null && socket.isConnected())) {
			this.ip = ip;
			this.port = port;
		}
	}

	public boolean sendData(String data, String serviceType) throws IOException {
		if (isConnected()) {
			byte[] bytes = null;
			String message = data;

			OutputStream os = socket.getOutputStream();
			bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			LogPanel.setLog("SEND DATA 👉👉👉 " + serviceType + "\n===============================================\n"
					+ data + "\n===============================================");

			// 데이터 받을
			InputStream is = socket.getInputStream();
			bytes = new byte[200];
			int readByteCount = is.read(bytes);
			message = new String(bytes, 0, readByteCount, "UTF-8");
			LogPanel.setLog("RECEIVED DATA 👇👇👇\n" + "byte : " + readByteCount + "\nmessage : " + message
					+ "\n===============================================");
			is.close();
			os.close();
			return true;
		} else {
			return false;
		}
	}

	public void connect() {
		try {
			if (socket == null) {
				socket = new Socket();
			}

			if (socket.isConnected()) {
				LogPanel.setLog("❗️이미 " + ip + ":" + port + "에 연결되어 습니다");
			} else {
				LogPanel.setLog(ip + ":" + port + " 연결 요청");
				socket.connect(new InetSocketAddress(ip, port));
				LogPanel.setLog(ip + ":" + port + " 연결 성공");
			}
		} catch (IOException e) {
			LogPanel.setLog("[❌]소켓 연결 중 실패" + e);
			socket = new Socket();
		}
	}

	public boolean isConnected() {
		return socket != null && socket.isConnected();
	}

	public void disconnect() {
		try {
			if (socket != null && socket.isConnected()) {
				socket.close();
				socket = null;
				LogPanel.setLog(ip + ":" + port + " 연결 해제 ⭕️\n");
			} else {
				LogPanel.setLog("[❌] 현재 연결되어 있는 소켓이 없습니다");
			}
		} catch (IOException e) {
			LogPanel.setLog("[❌]소켓 연결해제 실패");
		}
	}
}
