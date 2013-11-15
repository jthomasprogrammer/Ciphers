package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import ciphers.CaesarCipher;
import ciphers.VernamCipher;
import ciphers.VigenereCipher;

public class EncryptionWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3284426052799538340L;
	final private JTextArea textBox;
	final private JTextField detailsField;
	private String plainText;
	private int plainTextLength;

	JPanel panel = new JPanel();
	public EncryptionWindow(JTextArea textBox, JTextField detailsField) {
		this.textBox = textBox;
		this.detailsField = detailsField;
		String text = textBox.getText().trim();
		text = text.replaceAll("\\W", "");
		this.plainText = text;
		this.plainTextLength = text.length();
		initUI();
	}

	private void initUI() {

		panel = new JPanel();

		//Everything is added to the panel centered on the Y Axis.
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		/*
		 * Creates the encryption protocol panel where a user can select what protocol they want to use.
		 */
		final JPanel protocolPanel = new JPanel();
		protocolPanel.setLayout(new BoxLayout(protocolPanel, BoxLayout.X_AXIS));
		final JLabel protocolLabel = new JLabel("Encrytpion protocol:");
		final String options[] = {"Caesar Cipher", "Vernam Cipher", "Vigenere Cipher"};
		final JComboBox<String> protocolBox = new JComboBox<String>(options);
		protocolPanel.add(protocolLabel);
		protocolPanel.add(protocolBox);
		panel.add(protocolPanel);
		
		/*
		 * Creates the key panel where a user can generate a key to use in the encryption protocol.
		 */
		final JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.X_AXIS));
		final JButton generateButton = new JButton("Generate Key");
		final JTextField keyField = new JTextField(30);
		keyField.setBackground(Color.WHITE);
		keyField.setEditable(false);
		keyPanel.add(generateButton);
		keyPanel.add(keyField);
		panel.add(keyPanel);
		
		/*
		 * The listener for the Generate Button.
		 */
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Gets the currently selected cipher.
				int chosenCipher = protocolBox.getSelectedIndex();
				if(chosenCipher != -1){
					if(chosenCipher == 0){
						CaesarCipher cipher = new CaesarCipher();
						keyField.setText(cipher.generateKey()+"");
					}else if(chosenCipher == 1){
						VernamCipher cipher = new  VernamCipher();
						keyField.setText(cipher.generateKey(plainTextLength)+"");
					}else{
						VigenereCipher cipher = new VigenereCipher();
						keyField.setText(cipher.generateKey(plainTextLength)+"");
					}
				}
				
			}
		});
		

		//Creates the submit button.
		final JButton submitButton = new JButton("Submit");
		panel.add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Gets the currently selected cipher.
				int chosenCipher = protocolBox.getSelectedIndex();
				if(chosenCipher != -1){
					if(chosenCipher == 0){
						CaesarCipher cipher = new CaesarCipher();
						int key = Integer.parseInt(keyField.getText());
						String cipherText = cipher.encrypt(plainText, key);
						textBox.setText(cipherText);
						detailsField.setText("Encrypted text using the "+cipher.getName()+ " with key "+key);
						dispose();
					}else if(chosenCipher == 1){
						VernamCipher cipher = new VernamCipher();
						String key = keyField.getText();
						String cipherText = cipher.encrypt(plainText, key);
						textBox.setText(cipherText);
						detailsField.setText("Encrypted text using the "+cipher.getName()+ " with key "+key);
						dispose();
					}else{
						VigenereCipher cipher = new VigenereCipher();
						String key = keyField.getText();
						String cipherText = cipher.encrypt(plainText, key);
						textBox.setText(cipherText);
						detailsField.setText("Encrypted text using the "+cipher.getName()+ " with key "+key);
						dispose();
					}
				}
				
			}
		});
		
		add(panel);
		pack();
		setTitle("Encryption");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
