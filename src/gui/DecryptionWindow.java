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

public class DecryptionWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2907863773016690320L;
	final private JTextArea textBox;
	final private JTextField detailsField;
	private String cipherText;

	JPanel panel = new JPanel();
	public DecryptionWindow(JTextArea textBox, JTextField detailsField) {
		this.textBox = textBox;
		this.detailsField = detailsField;
		String text = textBox.getText();
		this.cipherText = text;
		initUI();
	}

	private void initUI() {

		panel = new JPanel();

		//Everything is added to the panel centered on the Y Axis.
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		/*
		 * Creates the decryption protocol panel where a user can select what protocol they want to use.
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
		 * Creates the key panel where a user can enter their key to use in the decryption protocol.
		 */
		final JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.X_AXIS));
		final JLabel keyLabel = new JLabel("Enter the key used to encrypt the ciphertext: ");
		final JTextField keyField = new JTextField(30);
		keyField.setBackground(Color.WHITE);
		keyPanel.add(keyLabel);
		keyPanel.add(keyField);
		panel.add(keyPanel);

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
						String plainText = cipher.decrypt(cipherText, key);
						textBox.setText(plainText);
						detailsField.setText("Decrypted text using the "+cipher.getName()+ " with key "+key);
						dispose();
					}else if(chosenCipher == 1){
						VernamCipher cipher = new VernamCipher();
						String key = keyField.getText();
						String plainText = cipher.decrypt(cipherText, key);
						textBox.setText(plainText);
						detailsField.setText("Decrypted text using the "+cipher.getName()+ " with key "+key);
						dispose();
					}else{
						VigenereCipher cipher = new VigenereCipher();
						String key = keyField.getText();
						String plainText = cipher.decrypt(cipherText, key);
						textBox.setText(plainText);
						detailsField.setText("Decrypted text using the "+cipher.getName()+ " with key "+key);
						dispose();
					}
				}
				
			}
		});
		
		add(panel);
		pack();
		setTitle("Decryption");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
