package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

public class MediaPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MediaPlayer frame = new MediaPlayer();
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
	public MediaPlayer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MediaPlayer.class.getResource("/javagui/resources/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon p= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48.png"));
		ImageIcon f= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2).png"));
		ImageIcon b= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2) - Copy.png"));
		ImageIcon v= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (3).png"));
		ImageIcon s= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (1).png"));
		
		JPanel screen = new JPanel();
		screen.setBackground(SystemColor.menu);
		screen.setBounds(0, 0, 782, 481);
		contentPane.add(screen);
		screen.setLayout(new BorderLayout(0, 0));
		
		JPanel controls = new JPanel();
		controls.setBackground(SystemColor.inactiveCaptionBorder);
		controls.setBounds(0, 482, 782, 67);
		contentPane.add(controls);
		controls.setLayout(null);
		
		JButton play = new JButton("");
		play.setBounds(350, 5, 100, 55);
		play.setIcon(new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48.png")));
		controls.add(play);
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(play.getIcon().equals(p)){
					play.setIcon(s);
				}else{
					play.setIcon(p);
				}
			}
		
		});
		
		JButton forward = new JButton("");
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		forward.setIcon(new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2).png")));
		forward.setBounds(460, 5, 100, 55);
		controls.add(forward);
		
		JButton backward = new JButton("");
		backward.setIcon(new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2) - Copy.png")));
		backward.setBounds(240, 5, 100, 55);
		controls.add(backward);
		
		JButton volume = new JButton("");
		volume.setIcon(new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (3).png")));
		volume.setBounds(591, 5, 100, 55);
		controls.add(volume);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(0, 551, 782, 102);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		textField.setBounds(165, 15, 450, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Speak");
		btnNewButton.setBounds(291, 48, 97, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(389, 48, 97, 41);
		panel.add(btnNewButton_1);
	}
}
