package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import backend.MyConnection;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;

public class AccountInfo extends JFrame {

	private JPanel contentPane;
	private JTextField unamein;
	private JTextField passwordin;
	private JTextField emailin;
	private JTextField fullnamein;
	private JTextField agein;
	private JTextField birthdayin;
	private JTextField cmndin;
	private JTextField phonenoin;
	private JTextField addressin;
	private JTextField zipin;
	JLayeredPane layeredPane ;
	JPanel show ;
	JPanel edit;
	private JRadioButton lock;
	private JRadioButton unlock;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JButton editbutton;
	//private JButton confbutton;

	/**
	 * Launch the application.
	 */
	public void switchPanels(JPanel abc)
	{
		layeredPane.removeAll();
		layeredPane.add(abc);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AccountInfo(String accid) throws SQLException {
		// GET ALL THE INFO
		// get personid
	
		
		PreparedStatement peridget = MyConnection.getConnection().prepareStatement("select username,password,personid,status from account where accountid=?");
		peridget.setString(1, accid);
		peridget.executeQuery();
		ResultSet rs = peridget.executeQuery();
		rs.next();
		String perid= rs.getString("personid");
		
		PreparedStatement cSt = MyConnection.getConnection().prepareStatement("select fullname,birthday,age,joinday,phone1,addr1,email,identity,zipcode from person where personid=?");
		cSt.setString(1, perid);
		ResultSet rs1=cSt.executeQuery();
		rs1.next();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 772);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel infolabel = new JLabel("ACCOUNT INFO");
		infolabel.setForeground(SystemColor.control);
		infolabel.setFont(new Font("Sitka Banner", Font.PLAIN, 40));
		infolabel.setHorizontalAlignment(SwingConstants.CENTER);
		infolabel.setBounds(174, 13, 266, 33);
		contentPane.add(infolabel);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 59, 639, 666);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		show = new JPanel();
		show.setBackground(SystemColor.textHighlightText);
		layeredPane.add(show, "name_14415624054000");
		show.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(0, 0, 109, 26);
		show.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Personal");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(0, 223, 109, 26);
		show.add(lblNewLabel_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblUsername.setForeground(SystemColor.windowText);
		lblUsername.setBounds(73, 29, 120, 26);
		show.add(lblUsername);
		
		JLabel unameshow = new JLabel("New label");
		unameshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		unameshow.setForeground(SystemColor.windowText);
		unameshow.setBounds(278, 29, 325, 26);
		show.add(unameshow);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_2.setForeground(SystemColor.windowText);
		lblNewLabel_2.setBounds(73, 73, 120, 32);
		show.add(lblNewLabel_2);
		
		JLabel passwordshow = new JLabel("New label");
		passwordshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		passwordshow.setForeground(SystemColor.windowText);
		passwordshow.setBounds(255, 73, 348, 32);
		show.add(passwordshow);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_3.setForeground(SystemColor.windowText);
		lblNewLabel_3.setBounds(73, 118, 120, 34);
		show.add(lblNewLabel_3);
		
		JLabel emailshow = new JLabel("New label");
		emailshow.setHorizontalAlignment(SwingConstants.CENTER);
		emailshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		emailshow.setForeground(SystemColor.windowText);
		emailshow.setBounds(205, 118, 398, 32);
		show.add(emailshow);
		
		JLabel lblNewLabel_5 = new JLabel("Account status");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_5.setForeground(SystemColor.windowText);
		lblNewLabel_5.setBounds(71, 165, 174, 32);
		show.add(lblNewLabel_5);
		
		JLabel lblFullname = new JLabel("Fullname");
		lblFullname.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblFullname.setForeground(SystemColor.windowText);
		lblFullname.setBounds(107, 252, 132, 32);
		show.add(lblFullname);
		
		JLabel fullnameshow = new JLabel("New label");
		fullnameshow.setHorizontalAlignment(SwingConstants.CENTER);
		fullnameshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		fullnameshow.setForeground(SystemColor.windowText);
		fullnameshow.setBounds(251, 247, 302, 42);
		show.add(fullnameshow);
		
		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setForeground(SystemColor.windowText);
		lblNewLabel_4.setBounds(107, 297, 120, 36);
		show.add(lblNewLabel_4);
		
		JLabel ageshow = new JLabel("New label");
		ageshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		ageshow.setForeground(SystemColor.windowText);
		ageshow.setBounds(303, 297, 250, 36);
		show.add(ageshow);
		
		JLabel lblNewLabel_6 = new JLabel("Birthday");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_6.setForeground(SystemColor.windowText);
		lblNewLabel_6.setBounds(109, 346, 120, 48);
		show.add(lblNewLabel_6);
		
		JLabel birthdayshow = new JLabel("New label");
		birthdayshow.setHorizontalAlignment(SwingConstants.CENTER);
		birthdayshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		birthdayshow.setForeground(SystemColor.windowText);
		birthdayshow.setBounds(278, 355, 227, 30);
		show.add(birthdayshow);
		
		JLabel lblNewLabel_7 = new JLabel("Identity");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_7.setForeground(SystemColor.windowText);
		lblNewLabel_7.setBounds(107, 407, 96, 32);
		show.add(lblNewLabel_7);
		
		JLabel cmndshow = new JLabel("New label");
		cmndshow.setHorizontalAlignment(SwingConstants.CENTER);
		cmndshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		cmndshow.setForeground(SystemColor.windowText);
		cmndshow.setBounds(303, 407, 227, 32);
		show.add(cmndshow);
		
		JLabel lblNewLabel_8 = new JLabel("Phone number");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_8.setForeground(SystemColor.windowText);
		lblNewLabel_8.setBounds(107, 454, 181, 40);
		show.add(lblNewLabel_8);
		
		JLabel phonenoshow = new JLabel("New label");
		phonenoshow.setHorizontalAlignment(SwingConstants.CENTER);
		phonenoshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		phonenoshow.setForeground(SystemColor.windowText);
		phonenoshow.setBounds(303, 452, 183, 44);
		show.add(phonenoshow);
		
		JLabel lblNewLabel_10 = new JLabel("ZIP");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_10.setForeground(SystemColor.windowText);
		lblNewLabel_10.setBounds(112, 562, 67, 26);
		show.add(lblNewLabel_10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(278, 507, 227, 42);
		show.add(scrollPane);
		
		JLabel adrshow = new JLabel("New label");
		adrshow.setBackground(SystemColor.textHighlightText);
		adrshow.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(adrshow);
		adrshow.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		adrshow.setForeground(SystemColor.windowText);
		adrshow.setText(rs1.getString("addr1"));
		
		JLabel lblNewLabel_12 = new JLabel("Join day");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_12.setForeground(SystemColor.windowText);
		lblNewLabel_12.setBounds(112, 602, 144, 34);
		show.add(lblNewLabel_12);
		
		JLabel zipshow = new JLabel("New label");
		zipshow.setHorizontalAlignment(SwingConstants.CENTER);
		zipshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		zipshow.setForeground(SystemColor.windowText);
		zipshow.setBounds(278, 562, 208, 27);
		show.add(zipshow);
		
		Date bd = rs1.getDate("birthday");
		Integer agee = rs1.getInt("age");
		Integer status = rs.getInt("status");
		
		Date date = rs1.getDate("joinday");
		JLabel lblNewLabel_14 = new JLabel("Address");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_14.setBounds(107, 514, 99, 23);
		show.add(lblNewLabel_14);
		JLabel joindayshow = new JLabel("New label");
		joindayshow.setHorizontalAlignment(SwingConstants.CENTER);
		joindayshow.setForeground(Color.BLACK);
		joindayshow.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		joindayshow.setBounds(334, 602, 219, 35);
		show.add(joindayshow);
		birthdayshow.setText(bd.toString());
		ageshow.setText(agee.toString());
		emailshow.setText(rs1.getString("email"));
		unameshow.setText(rs.getString("username"));
		passwordshow.setText(rs.getString("password"));
		fullnameshow.setText(rs1.getString("fullname"));
		cmndshow.setText(rs1.getString("identity"));
		phonenoshow.setText(rs1.getString("phone1"));
		zipshow.setText(rs1.getString("zipcode"));
		joindayshow.setText(date.toString());
		
		editbutton = new JButton("Edit");
		editbutton.setBackground(SystemColor.controlHighlight);
		editbutton.setForeground(SystemColor.textHighlight);
		editbutton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		editbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				unamein.setText(unameshow.getText());
				passwordin.setText(passwordshow.getText());
				emailin.setText(emailshow.getText());
				if(status==1)
				{
					unlock.setSelected(true);
				}
				else
				{
					lock.setSelected(true);
				}
				fullnamein.setText(fullnameshow.getText());
				agein.setText(ageshow.getText());
				birthdayin.setText(birthdayshow.getText());
				addressin.setText(adrshow.getText());
				cmndin.setText(cmndshow.getText());
				phonenoin.setText(phonenoshow.getText());
				zipin.setText(zipshow.getText());
				switchPanels(edit);
			}
		});
		editbutton.setBounds(530, 634, 109, 32);
		show.add(editbutton);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(0, 634, 97, 32);
		show.add(btnNewButton);
		
		JLabel readyshow = new JLabel("READY");
		readyshow.setForeground(new Color(60, 179, 113));
		readyshow.setFont(new Font("Tahoma", Font.BOLD, 18));
		readyshow.setBounds(300, 178, 75, 16);
		show.add(readyshow);
		readyshow.setVisible(false);
		
	
		
		JLabel lockshow = new JLabel("LOCKED");
		lockshow.setForeground(new Color(255, 0, 0));
		lockshow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lockshow.setBounds(303, 176, 75, 16);
		show.add(lockshow);
		show.add(getSeparator());
		show.add(getSeparator_1());
		lockshow.setVisible(false);

		if(status==1)
		{
			readyshow.setVisible(true);
		}
		else
		{
			lockshow.setVisible(true);
		}
		
		edit = new JPanel();
		edit.setBackground(SystemColor.textHighlightText);
		layeredPane.add(edit, "name_14415646033400");
		edit.setLayout(null);
		
		JLabel label = new JLabel("Username");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label.setBounds(14, 46, 120, 26);
		edit.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_1.setBounds(14, 90, 120, 32);
		edit.add(label_1);
		
		JLabel label_2 = new JLabel("Email");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_2.setBounds(14, 135, 120, 34);
		edit.add(label_2);
		
		JLabel label_3 = new JLabel("Account status");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_3.setBounds(12, 182, 174, 32);
		edit.add(label_3);
		
		JLabel label_4 = new JLabel("Fullname");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_4.setBounds(14, 269, 132, 32);
		edit.add(label_4);
		
		JLabel label_5 = new JLabel("Age");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_5.setBounds(14, 314, 120, 36);
		edit.add(label_5);
		
		JLabel label_6 = new JLabel("Birthday");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_6.setBounds(14, 363, 120, 48);
		edit.add(label_6);
		
		JLabel label_7 = new JLabel("Identity");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_7.setBounds(14, 424, 96, 32);
		edit.add(label_7);
		
		JLabel label_8 = new JLabel("Phone number");
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_8.setBounds(14, 473, 181, 40);
		edit.add(label_8);
		
		JLabel label_9 = new JLabel("Address");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_9.setBounds(12, 532, 99, 23);
		edit.add(label_9);
		
		JLabel label_10 = new JLabel("ZIP");
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_10.setBounds(14, 580, 67, 26);
		edit.add(label_10);
		
		unamein = new JTextField();
		unamein.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		unamein.setBounds(180, 46, 293, 29);
		edit.add(unamein);
		unamein.setColumns(10);
		
		passwordin = new JTextField();
		passwordin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		passwordin.setColumns(10);
		passwordin.setBounds(190, 90, 213, 32);
		edit.add(passwordin);
		
		emailin = new JTextField();
		emailin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		emailin.setColumns(10);
		emailin.setBounds(166, 135, 265, 33);
		edit.add(emailin);
		
		fullnamein = new JTextField();
		fullnamein.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		fullnamein.setColumns(10);
		fullnamein.setBounds(158, 269, 326, 32);
		edit.add(fullnamein);
		
		agein = new JTextField();
		agein.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		agein.setColumns(10);
		agein.setBounds(113, 314, 51, 34);
		edit.add(agein);
		
		birthdayin = new JTextField();
		birthdayin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		birthdayin.setColumns(10);
		birthdayin.setBounds(158, 377, 189, 26);
		edit.add(birthdayin);
		
		cmndin = new JTextField();
		cmndin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cmndin.setColumns(10);
		cmndin.setBounds(158, 424, 229, 32);
		edit.add(cmndin);
		
		phonenoin = new JTextField();
		phonenoin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		phonenoin.setColumns(10);
		phonenoin.setBounds(231, 483, 156, 26);
		edit.add(phonenoin);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(143, 526, 353, 33);
		edit.add(scrollPane_1);
		
		addressin = new JTextField();
		scrollPane_1.setViewportView(addressin);
		addressin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addressin.setColumns(10);
		
		zipin = new JTextField();
		zipin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		zipin.setColumns(10);
		zipin.setBounds(102, 580, 120, 29);
		edit.add(zipin);
		
		JButton apply = new JButton("Apply");
		apply.setBackground(SystemColor.controlHighlight);
		apply.setForeground(SystemColor.textHighlight);
		apply.setFont(new Font("Tahoma", Font.BOLD, 18));
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// account
				String uname1 = unamein.getText();
				String psw = passwordin.getText();
				Integer stt ;
				if(lock.isSelected())
				{
					stt = 0;
				}
				else
				{
					stt = 1;
				}
				//Integer stt = Integer.parseInt(statusin.getText());
				// person
				String mail = emailin.getText();
				String name = fullnamein.getText();
				Integer age = Integer.parseInt(agein.getText());
				String birthday = birthdayin.getText().toString();
				String cmnd = cmndin.getText();
				String phoneno = phonenoin.getText();
				String zip = zipin.getText();
				String addr = addressin.getText();
				// CHECK INPUT
				if(uname1==""||psw==""||stt==null||mail==""||name==""||age==null||birthday==""||cmnd==""||phoneno==""||zip==""||addr=="")
				{
					JOptionPane.showMessageDialog(null, "Please fill all the values");
					return;
				}
				
					try {
						String check = "select accountid from account where username=?";
						PreparedStatement check1 = MyConnection.getConnection().prepareStatement(check);
						check1.setString(1,uname1);
						ResultSet checkrs = check1.executeQuery();
						checkrs.next();
						if(checkrs.next()==true)
						{
							JOptionPane.showMessageDialog(null, "Username is already exists");
							unamein.setText("");
							return;
						}
						else
						{
							String update1 = "update account set username=?,password=?,status=? where accountid=?";
							String update2 = "update person set fullname=?,email=?,birthday=?,age=?,phone1=?,zipcode=?,identity=?,addr1=? where personid=?";
							CallableStatement cSt;
							cSt = MyConnection.getConnection().prepareCall(update1);
							cSt.setString(1, uname1);
							cSt.setString(2, psw);
							cSt.setInt(3, stt);
							cSt.setString(4,accid);
							cSt.execute();
							
							cSt = MyConnection.getConnection().prepareCall(update2);
							cSt.setString(1, name);
							cSt.setString(2, mail);
							cSt.setString(3, birthday);
							cSt.setInt(4, age);
							cSt.setString(5, phoneno);
							cSt.setString(6, zip);
							cSt.setString(7, cmnd);
							cSt.setString(8, addr);
							cSt.setString(9, perid);
							cSt.execute();
							
							JOptionPane.showMessageDialog(null, "Your information has been updated");
							AccountInfo abcc = new AccountInfo(accid);
							dispose();
							abcc.setVisible(true);
						}	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		});
		apply.setBounds(542, 634, 97, 32);
		edit.add(apply);
		
		JButton canceledit = new JButton("Cancel");
		canceledit.setBackground(SystemColor.controlHighlight);
		canceledit.setForeground(SystemColor.textHighlight);
		canceledit.setFont(new Font("Tahoma", Font.BOLD, 18));
		canceledit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(show);
			}
		});
		canceledit.setBounds(0, 634, 97, 32);
		edit.add(canceledit);
		
		JLabel label_11 = new JLabel("Account");
		label_11.setForeground(SystemColor.textHighlight);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 22));
		label_11.setBounds(0, 7, 109, 26);
		edit.add(label_11);
		
		JLabel label_12 = new JLabel("Personal");
		label_12.setForeground(SystemColor.textHighlight);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 22));
		label_12.setBounds(0, 230, 109, 26);
		edit.add(label_12);
		edit.add(getLock());
		edit.add(getUnlock());
		edit.add(getSeparator_2());
		edit.add(getSeparator_3());
		
		
		
	
	
		
	}
	private JRadioButton getLock() {
		if (lock == null) {
			lock = new JRadioButton("LOCKED");
			buttonGroup.add(lock);
			lock.setForeground(new Color(178, 34, 34));
			lock.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lock.setBackground(SystemColor.textHighlightText);
			lock.setBounds(231, 191, 127, 25);
		}
		return lock;
	}
	private JRadioButton getUnlock() {
		if (unlock == null) {
			unlock = new JRadioButton("UNLOCKED");
			buttonGroup.add(unlock);
			unlock.setForeground(new Color(50, 205, 50));
			unlock.setFont(new Font("Tahoma", Font.PLAIN, 18));
			unlock.setBackground(SystemColor.textHighlightText);
			unlock.setBounds(395, 191, 127, 25);
		}
		return unlock;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(102, 237, 537, 2);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(102, 13, 537, 2);
		}
		return separator_1;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setBounds(113, 245, 526, 2);
		}
		return separator_2;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setBounds(101, 23, 526, 2);
		}
		return separator_3;
	}
}
