package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveSpeechFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private JFrame thisFrame = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveSpeechFrame frame = new SaveSpeechFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaveSpeechFrame() {
		setTitle("Save Speech");
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 102, 576, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 173, 472, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCurrentSpeech = new JLabel("Current Speech:");
		lblCurrentSpeech.setBounds(12, 65, 206, 25);
		contentPane.add(lblCurrentSpeech);
		
		JLabel lblNewLabel = new JLabel("Name of file:");
		lblNewLabel.setBounds(12, 175, 104, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveSpeech ss = new SaveSpeech(textField.getText(), textField_1.getText());
				ss.execute();
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(46, 269, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		btnNewButton_1.setBounds(428, 269, 117, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Save Speech");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1.setBounds(12, 12, 313, 33);
		contentPane.add(lblNewLabel_1);
	}
	public void setSpeech(String message){
		textField.setText(message);
	}
}
