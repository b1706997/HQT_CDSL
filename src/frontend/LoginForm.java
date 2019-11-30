package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import backend.MyConnection;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 50, 432, 9);
		contentPane.add(separator);
		
		JLabel lblEb = new JLabel("E-Banking System");
		lblEb.setBounds(168, 21, 123, 16);
		contentPane.add(lblEb);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(53, 106, 77, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(53, 152, 56, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(142, 103, 170, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag = 0;
				CallableStatement cSt = null;
				PreparedStatement pSt = null;
				ResultSet rs = null;
				
				String uname = textField.getText();
				String psw = String.valueOf(passwordField.getPassword());
				final JPanel panel = new JPanel();
				// HANDLE INPUT 
				while(flag==0)
				{
					if(uname.equals(null)|| uname.equals("") || uname.length()>50)
					{
					    JOptionPane.showMessageDialog(panel, "Username format is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(psw.equals(null) || psw.equals("") || psw.length()>50)
					{
					    JOptionPane.showMessageDialog(panel, "Password format is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					flag = 1;
				}
				try {
					cSt =  MyConnection.getConnection().prepareCall("{call login(?,?,?,?,?)}");
					cSt.setString(1, uname);
					cSt.setString(2, psw);
					cSt.registerOutParameter(3,Types.BIT);
					cSt.registerOutParameter(4,Types.CHAR);
					cSt.registerOutParameter(5, Types.VARCHAR);
					cSt.executeQuery();
					
					if(cSt.getInt(3)==0)
					{ // NO USERNAME OR PASSWORD FOUND IN DB 
					    JOptionPane.showMessageDialog(panel, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
					    return;
					}
					else
					{ // PASS accountid TO A SUITABLE JFRAME
						if(cSt.getString(4).equals("admin"))
						{
							AdminForm admin = new AdminForm(cSt.getString(5));
							admin.setVisible(true);
							dispose();
						}
						if(cSt.getString(4).equals("employee"))
						{
							EmployeeForm employ = new EmployeeForm(cSt.getString(5));
							employ.setVisible(true);
							dispose();
						}
						if(cSt.getString(4).equals("user"))
						{
							UserForm user = new UserForm(cSt.getString(5));
							user.setVisible(true);
							dispose();
						}
					}
					//System.out.print(cSt.getInt(3)+"\n"+cSt.getString(4));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(142, 204, 97, 25);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 149, 170, 22);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(269, 204, 97, 25);
		contentPane.add(btnNewButton);
	}
}
