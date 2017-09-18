package org.codapli.utn;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MainBrazoRobot {

	private JFrame frmCodapliBrazo;
	private JTextField tfPort;
	private static Serial serial;

	private JButton bHombroUp;
	private JButton bCodoUp;
	private JButton bHombroDown;
	private JButton bCodoDown;
	private JButton bPinzaOpen;
	private JButton bPinzaClose;
	private JButton bRotarLeft;
	private JButton bRotarRight;
	private JButton bConnect;
	Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainBrazoRobot window = new MainBrazoRobot();
					window.frmCodapliBrazo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainBrazoRobot() {
		initialize();
		disableEnableControlRobot(false);
		setMouseListener();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmCodapliBrazo = new JFrame();
		frmCodapliBrazo.setTitle("CODAPLI - Brazo Robotico");
		frmCodapliBrazo.setBounds(100, 100, 450, 300);
		frmCodapliBrazo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCodapliBrazo.setResizable(false);

		JPanel panel = new JPanel();
		frmCodapliBrazo.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		ImagePanel jpanelImage = new ImagePanel("/resources/customLogo.jpg");
		GridBagConstraints gbc_jpanelImage = new GridBagConstraints();
		gbc_jpanelImage.gridwidth = 2;
		gbc_jpanelImage.insets = new Insets(0, 0, 5, 5);
		gbc_jpanelImage.fill = GridBagConstraints.BOTH;
		gbc_jpanelImage.gridx = 3;
		gbc_jpanelImage.gridy = 0;
		panel.add(jpanelImage, gbc_jpanelImage);

		JLabel lblNewLabel_3 = new JLabel("Hombro");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Codo");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 1;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		bHombroUp = new JButton("Up");

		GridBagConstraints gbc_bHombroUp = new GridBagConstraints();
		gbc_bHombroUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_bHombroUp.insets = new Insets(0, 0, 5, 5);
		gbc_bHombroUp.gridx = 3;
		gbc_bHombroUp.gridy = 2;
		panel.add(bHombroUp, gbc_bHombroUp);

		bCodoUp = new JButton("Up");

		GridBagConstraints gbc_bCodoUp = new GridBagConstraints();
		gbc_bCodoUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_bCodoUp.insets = new Insets(0, 0, 5, 5);
		gbc_bCodoUp.gridx = 4;
		gbc_bCodoUp.gridy = 2;
		panel.add(bCodoUp, gbc_bCodoUp);

		bHombroDown = new JButton("Down");

		GridBagConstraints gbc_bHombroDown = new GridBagConstraints();
		gbc_bHombroDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_bHombroDown.insets = new Insets(0, 0, 5, 5);
		gbc_bHombroDown.gridx = 3;
		gbc_bHombroDown.gridy = 3;
		panel.add(bHombroDown, gbc_bHombroDown);

		bCodoDown = new JButton("Down");

		GridBagConstraints gbc_bCodoDown = new GridBagConstraints();
		gbc_bCodoDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_bCodoDown.insets = new Insets(0, 0, 5, 5);
		gbc_bCodoDown.gridx = 4;
		gbc_bCodoDown.gridy = 3;
		panel.add(bCodoDown, gbc_bCodoDown);

		JLabel lblNewLabel = new JLabel("Pinza");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		bPinzaOpen = new JButton("Open");

		GridBagConstraints gbc_bPinzaOpen = new GridBagConstraints();
		gbc_bPinzaOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPinzaOpen.insets = new Insets(0, 0, 5, 5);
		gbc_bPinzaOpen.gridx = 3;
		gbc_bPinzaOpen.gridy = 5;
		panel.add(bPinzaOpen, gbc_bPinzaOpen);

		bPinzaClose = new JButton("Close");

		GridBagConstraints gbc_bPinzaClose = new GridBagConstraints();
		gbc_bPinzaClose.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPinzaClose.insets = new Insets(0, 0, 5, 5);
		gbc_bPinzaClose.gridx = 4;
		gbc_bPinzaClose.gridy = 5;
		panel.add(bPinzaClose, gbc_bPinzaClose);

		JLabel lblNewLabel_1 = new JLabel("Rotar");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 6;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		bRotarLeft = new JButton("Left");

		GridBagConstraints gbc_bRotarLeft = new GridBagConstraints();
		gbc_bRotarLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_bRotarLeft.insets = new Insets(0, 0, 5, 5);
		gbc_bRotarLeft.gridx = 3;
		gbc_bRotarLeft.gridy = 7;
		panel.add(bRotarLeft, gbc_bRotarLeft);

		bRotarRight = new JButton("Right");

		GridBagConstraints gbc_bRotarRight = new GridBagConstraints();
		gbc_bRotarRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_bRotarRight.insets = new Insets(0, 0, 5, 5);
		gbc_bRotarRight.gridx = 4;
		gbc_bRotarRight.gridy = 7;
		panel.add(bRotarRight, gbc_bRotarRight);

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 8;
		panel.add(horizontalGlue, gbc_horizontalGlue);

		JLabel lblNewLabel_2 = new JLabel("Port :");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 8;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		tfPort = new JTextField();
		tfPort.setText("/dev/tty");
		GridBagConstraints gbc_tfPort = new GridBagConstraints();
		gbc_tfPort.gridwidth = 2;
		gbc_tfPort.insets = new Insets(0, 0, 5, 5);
		gbc_tfPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPort.gridx = 3;
		gbc_tfPort.gridy = 8;
		panel.add(tfPort, gbc_tfPort);
		tfPort.setColumns(10);

		bConnect = new JButton("Connect");
		bConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (bConnect.getText().equals("Connect")) {
					serial = Serial.getInstance();
					serial.setPort(tfPort.getText());
					if (serial.connect()) {
						bConnect.setText("Disconnect");
						disableEnableControlRobot(true);
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"El puerto " + tfPort.getText() + " no pudo ser abierto", "Dialog",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					bConnect.setText("Connect");
					serial.disconnect();
					disableEnableControlRobot(false);
				}
			}
		});
		GridBagConstraints gbc_bConnect = new GridBagConstraints();
		gbc_bConnect.insets = new Insets(0, 0, 5, 5);
		gbc_bConnect.gridx = 6;
		gbc_bConnect.gridy = 8;
		panel.add(bConnect, gbc_bConnect);
	}

	public void disableEnableControlRobot(Boolean estado) {
		bHombroUp.setEnabled(estado);
		bCodoUp.setEnabled(estado);
		bHombroDown.setEnabled(estado);
		bCodoDown.setEnabled(estado);
		bPinzaOpen.setEnabled(estado);
		bPinzaClose.setEnabled(estado);
		bRotarLeft.setEnabled(estado);
		bRotarRight.setEnabled(estado);
		tfPort.setEditable(!estado);

	}

	public void setMouseListener() {
		bCodoUp.addMouseListener(new MouseAdapterButton(new ActionListenerButton("q")));
		bHombroUp.addMouseListener(new MouseAdapterButton(new ActionListenerButton("w")));
		bHombroDown.addMouseListener(new MouseAdapterButton(new ActionListenerButton("s")));
		bCodoDown.addMouseListener(new MouseAdapterButton(new ActionListenerButton("e")));
		bPinzaOpen.addMouseListener(new MouseAdapterButton(new ActionListenerButton("o")));
		bPinzaClose.addMouseListener(new MouseAdapterButton(new ActionListenerButton("p")));
		bRotarLeft.addMouseListener(new MouseAdapterButton(new ActionListenerButton("a")));
		bRotarRight.addMouseListener(new MouseAdapterButton(new ActionListenerButton("d")));
	}
}
