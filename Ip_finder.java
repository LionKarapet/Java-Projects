package apps;
import java.net.*;
import java.awt.event.*;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Ip_finder extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		
			Ip_finder frame = new Ip_finder();
			frame.setVisible(true);
		}


	public Ip_finder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l = new JLabel("Type your address /URL");
		l.setBounds(81, 83, 150, 14);
		contentPane.add(l);
		
		JTextField tf = new JTextField();
		tf.setBounds(81, 104, 169, 20);
		contentPane.add(tf);
		tf.setColumns(10);
		
		JButton b = new JButton("Find");
		b.setBounds(115, 149, 89, 23);
		contentPane.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = tf.getText();
				try {
					InetAddress ia =  InetAddress.getByName(url);
					String ip = ia.getHostAddress();
					JOptionPane.showMessageDialog(contentPane, ip);
				}
				catch(UnknownHostException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.toString());
				}
			}
		});
		
	}
}
