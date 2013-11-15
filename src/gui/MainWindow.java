package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;





public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8972845754063080764L;
	JPanel panel = new JPanel();
	public MainWindow() {
		initUI();
	}

	private void initUI() {

		panel = new JPanel();

		//Everything is added to the panel centered on the Y Axis.
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//Declares the textbox where plaintext or ciphertext is put to have it decrypted or encrypted.
		final JTextArea textBox = new JTextArea(30, 40);
		textBox.setText("Enter the plaintext or ciphertext here.");
		textBox.setBackground(Color.WHITE);
		JScrollPane scroll = new JScrollPane (textBox);
		scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel.add(scroll);
		
		//Declares the text field where details about the encryption and decryption are stored.
		final JTextField detailsField = new JTextField(40);
		detailsField.setBackground(Color.WHITE);
		detailsField.setText("Details about the encryption or decryption operation performed are outputted here.");
		detailsField.setEditable(false);
		panel.add(detailsField);
		
		/*
		 * The panel where the three buttons, encrypt, decrypt and clear and stored.
		 */
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		final JButton encryptButton = new JButton("Encrypt");
		final JButton decryptButton = new JButton("Decrypt");
		final JButton clearButton = new JButton("Clear");
		buttonPanel.add(encryptButton);
		buttonPanel.add(decryptButton);
		buttonPanel.add(clearButton);
		panel.add(buttonPanel);
		
		/*
		 * The listener for the Encryption Button.
		 */
		encryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				EncryptionWindow window = new EncryptionWindow(textBox, detailsField);
				window.setVisible(true);
			}
		});
		
		/*
		 * The listener for the Decryption Button.
		 */
		decryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				DecryptionWindow window = new DecryptionWindow(textBox, detailsField);
				window.setVisible(true);
			}
		});
		
		/*
		 * The listener for the Clear Button.
		 */
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Clears the textBox of text.
				textBox.setText("");
			}
		});
		
		add(panel);
		pack();
		setTitle("Ciphers");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}