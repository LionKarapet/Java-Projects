package apps;

import java.sql.*;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IUD_DATABASE_APPLICATION extends JFrame {

	private JPanel contentPane;
	private JTextField tf_ID;
	private JTextField tf_FirstName;
	private JTextField tf_LastName;
	private JTextField tf_Age;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUD_DATABASE_APPLICATION frame = new IUD_DATABASE_APPLICATION();
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
	
	public IUD_DATABASE_APPLICATION() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ID = new JLabel("ID:");
		lbl_ID.setBounds(10, 35, 46, 14);
		contentPane.add(lbl_ID);
		
		tf_ID = new JTextField();
		tf_ID.setBounds(135, 32, 86, 20);
		contentPane.add(tf_ID);
		tf_ID.setColumns(10);
		
		JLabel lbl_FirstName = new JLabel("First Name:");
		lbl_FirstName.setBounds(10, 75, 79, 14);
		contentPane.add(lbl_FirstName);
		
		tf_FirstName = new JTextField();
		tf_FirstName.setBounds(135, 72, 86, 20);
		contentPane.add(tf_FirstName);
		tf_FirstName.setColumns(10);
		
		JLabel lbl_LastName = new JLabel("Last Name:");
		lbl_LastName.setBounds(10, 117, 67, 14);
		contentPane.add(lbl_LastName);
		
		tf_LastName = new JTextField();
		tf_LastName.setBounds(135, 114, 86, 20);
		contentPane.add(tf_LastName);
		tf_LastName.setColumns(10);
		
		JLabel lbl_Age = new JLabel("Age:");
		lbl_Age.setBounds(10, 158, 46, 14);
		contentPane.add(lbl_Age);
		
		tf_Age = new JTextField();
		tf_Age.setBounds(135, 155, 86, 20);
		contentPane.add(tf_Age);
		tf_Age.setColumns(10);
		
		JButton btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					theQuery("INSERT INTO PERSON(ID,First_Name,Last_Name,Age) values('"+tf_ID.getText()+"','"+tf_FirstName.getText()+"','"+tf_LastName.getText()+"','"+tf_Age.getText()+"')");
				}catch(Exception ex) {
					
				}
			}
		});
		btnInsert.setBounds(282, 42, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					theQuery("UPDATE PERSON SET First_Name = '"+tf_FirstName.getText()+"',Last_Name = '"+tf_LastName.getText()+"',Age = '"+tf_Age.getText()+"' WHERE ID =  '"+tf_ID.getText()+"' ");
				}catch(Exception ex) {
					
				}
				
			}
		});
		btnUpdate.setBounds(282, 85, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					theQuery("DELETE FROM PERSON WHERE ID = "+tf_ID.getText() );
				}catch(Exception ex) {
					
				}
				
			}
		});
		btnDelete.setBounds(282, 126, 89, 23);
		contentPane.add(btnDelete);
	}
	
	public void theQuery(String query) {
		
		Connection con = null;
		Statement st = null;
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
			st = con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Query Executed");
			st.close();
			con.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	
	}
	
}
