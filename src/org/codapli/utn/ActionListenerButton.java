package org.codapli.utn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerButton implements ActionListener {

	String caracter;
	Serial serial;

	public ActionListenerButton(String caracter) {
		this.caracter = caracter;
		this.serial = Serial.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(caracter);
		serial.write(caracter);

	}

}
