package org.codapli.utn;

import com.fazecast.jSerialComm.SerialPort;

public class Serial {

	private static Serial instance = null;
	private String port = "/dev/ttyACM0";
	private SerialPort comPort;

	protected Serial() {
	}

	public static Serial getInstance() {
		if (instance == null) {
			instance = new Serial();
		}
		return instance;
	}

	public Boolean connect() {
		comPort = SerialPort.getCommPort(port);
		comPort.setBaudRate(9600);
		comPort.setNumDataBits(8);
		comPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
		comPort.setParity(SerialPort.NO_PARITY);

		if (!comPort.openPort()) {
			System.err.println("El puerto " + port + " no pudo ser abierto");
			return false;
		} else {
			System.out.println("Puerto abierto " + port);
			return true;
		}
	}

	public String readPort() {

		String mgs = null;
		if (comPort.bytesAvailable() > 0) {
			byte[] readBuffer = new byte[comPort.bytesAvailable()];
			int numRead = comPort.readBytes(readBuffer, readBuffer.length);
			mgs = new String(readBuffer);

		}
		return mgs;
	}

	public Boolean write(String mgs) {
		byte[] readBuffer = mgs.getBytes();
		int numRead = comPort.writeBytes(readBuffer, readBuffer.length);
		if (numRead > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void disconnect() {
		comPort.closePort();
	}

}
