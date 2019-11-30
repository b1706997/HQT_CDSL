package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class PerEdit extends JFrame {

	private JPanel contentPane;
	private JTextField age;
	private JLayeredPane layeredPane;
	private JPanel edit;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField fnamein;
	private JTextField cmndin;
	private JTextField emailin;
	private JTextField birthdayin;
	private JTextField agein;
	private JTextField phone1in;
	private JTextField phone2in;
	private JTextField addr1in;
	private JTextField addr2in;
	private JTextField zipin;
	private JButton button;
	private JButton button_1;
	private JPanel show;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_18;
	private JLabel peridshow;
	private JLabel fnameshow;
	private JLabel cmndshow;
	private JLabel emailshow;
	private JLabel birthdayshow;
	private JLabel ageshow;
	private JLabel roleshow;
	private JLabel joindayshow;
	private JLabel phone1show;
	private JLabel phone2show;
	private JLabel addr1show;
	private JLabel addr2show;
	private JLabel zipshow;
	private String perid;
	private JButton btnNewButton;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @param perid 
	 * @throws SQLException 
	 */
	public PerEdit(String perid,JButton invi) throws SQLException {
		this.perid=perid;
		PreparedStatement getper = MyConnection.getConnection().prepareStatement("select fullname,identity,birthday,age,phone1,phone2,addr1,addr2,zipcode,email from person where personid=?");
		getper.setString(1, perid);
		ResultSet person=getper.executeQuery();
		person.next();
		Date date = new Date();
		date = person.getDate("birthday");
		refreshShow();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 694, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLayeredPane_1());
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		    	System.out.print("YES");
		        //invi.setEnabled(true);
		        invi.doClick();
		    }
		});
	}
	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setBounds(0, 0, 676, 697);
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getShow(), "name_11661316391400");
			layeredPane.add(getEdit(), "name_11570781812800");
		}
		return layeredPane;
	}
	private JPanel getEdit() {
		if (edit == null) {
			edit = new JPanel();
			edit.setLayout(null);
			edit.add(getLabel());
			edit.add(getLabel_1());
			edit.add(getLabel_2());
			edit.add(getLabel_3());
			edit.add(getLabel_4());
			edit.add(getLabel_5());
			edit.add(getLabel_6());
			edit.add(getLabel_7());
			edit.add(getFnamein());
			edit.add(getCmndin());
			edit.add(getEmailin());
			edit.add(getBirthdayin());
			edit.add(getAgein());
			edit.add(getPhone1in());
			edit.add(getPhone2in());
			edit.add(getAddr1in());
			edit.add(getAddr2in());
			edit.add(getZipin());
			edit.add(getButton());
			edit.add(getButton_1());
		}
		return edit;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Fullname");
			label.setBounds(49, 47, 56, 16);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Identity");
			label_1.setBounds(49, 91, 56, 16);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Email");
			label_2.setBounds(49, 143, 56, 16);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Birthday");
			label_3.setBounds(49, 188, 56, 16);
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Age");
			label_4.setBounds(49, 241, 56, 16);
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Phone numbers");
			label_5.setBounds(31, 293, 92, 16);
		}
		return label_5;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Addresses");
			label_6.setBounds(49, 409, 74, 16);
		}
		return label_6;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("ZIP code");
			label_7.setBounds(49, 523, 56, 16);
		}
		return label_7;
	}
	private JTextField getFnamein() {
		if (fnamein == null) {
			fnamein = new JTextField();
			fnamein.setText((String) null);
			fnamein.setColumns(10);
			fnamein.setBounds(135, 44, 116, 22);
		}
		return fnamein;
	}
	private JTextField getCmndin() {
		if (cmndin == null) {
			cmndin = new JTextField();
			cmndin.setText((String) null);
			cmndin.setColumns(10);
			cmndin.setBounds(145, 91, 116, 22);
		}
		return cmndin;
	}
	private JTextField getEmailin() {
		if (emailin == null) {
			emailin = new JTextField();
			emailin.setText((String) null);
			emailin.setColumns(10);
			emailin.setBounds(145, 140, 116, 22);
		}
		return emailin;
	}
	private JTextField getBirthdayin() {
		if (birthdayin == null) {
			birthdayin = new JTextField();
			birthdayin.setText((String) null);
			birthdayin.setColumns(10);
			birthdayin.setBounds(135, 196, 116, 22);
		}
		return birthdayin;
	}
	private JTextField getAgein() {
		if (agein == null) {
			agein = new JTextField();
			agein.setColumns(10);
			agein.setBounds(145, 238, 116, 22);
		}
		return agein;
	}
	private JTextField getPhone1in() {
		if (phone1in == null) {
			phone1in = new JTextField();
			phone1in.setText((String) null);
			phone1in.setColumns(10);
			phone1in.setBounds(135, 290, 116, 22);
		}
		return phone1in;
	}
	private JTextField getPhone2in() {
		if (phone2in == null) {
			phone2in = new JTextField();
			phone2in.setText((String) null);
			phone2in.setColumns(10);
			phone2in.setBounds(145, 340, 116, 22);
		}
		return phone2in;
	}
	private JTextField getAddr1in() {
		if (addr1in == null) {
			addr1in = new JTextField();
			addr1in.setText((String) null);
			addr1in.setColumns(10);
			addr1in.setBounds(135, 409, 116, 22);
		}
		return addr1in;
	}
	private JTextField getAddr2in() {
		if (addr2in == null) {
			addr2in = new JTextField();
			addr2in.setText((String) null);
			addr2in.setColumns(10);
			addr2in.setBounds(159, 458, 116, 22);
		}
		return addr2in;
	}
	private JTextField getZipin() {
		if (zipin == null) {
			zipin = new JTextField();
			zipin.setText((String) null);
			zipin.setColumns(10);
			zipin.setBounds(135, 520, 116, 22);
		}
		return zipin;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Apply");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String fnamein=getFnamein().getText();
					String cmndin = getCmndin().getText();
					String emailin = getEmailin().getText();
					String birthdayin = getBirthdayin().getText();
					Integer agein = Integer.parseInt(getAgein().getText());
					String phone1in = getPhone1in().getText();
					String phone2in = getPhone2in().getText();
					String addr1in = getAddr1in().getText();
					String addr2in = getAddr1in().getText();
					String zipin = getZipin().getText();
					if(fnamein.equals("")||cmndin.equals("")||birthdayin.equals("")||phone1in.equals("")||addr1in.equals("")||zipin.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill in all fields");
						return;
					}
					String updatequery = "update person set fullname=?,identity=?,email=?,birthday=?,age=?,phone1=?,phone2=?,addr1=?,addr2=?,zipcode=? where personid=?";
					try {
						PreparedStatement update = MyConnection.getConnection().prepareStatement(updatequery);
						update.setString(1, fnamein);
						update.setString(2, cmndin);
						update.setString(3, emailin);
						update.setString(4, birthdayin);
						update.setInt(5, agein);
						update.setString(6, phone1in);
						update.setString(7, phone2in);
						update.setString(8, addr1in);
						update.setString(9, addr2in);
						update.setString(10, zipin);
						update.setString(11, perid);
						update.execute();
						JOptionPane.showMessageDialog(null, "User infomations updated");
						refreshShow();
						switchPanels(layeredPane,show);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			button.setBounds(529, 632, 97, 25);
		}
		return button;
	}
	// POPULATE SHOW
	protected void refreshShow() throws SQLException {
		PreparedStatement getper = MyConnection.getConnection().prepareStatement("select * from person where personid=?");
		getper.setString(1, perid);
		ResultSet person=getper.executeQuery();
		person.next();
		getPeridshow().setText(person.getString("personid"));
		getFnameshow().setText(person.getString("fullname"));
		getEmailshow().setText(person.getString("email"));
		getCmndshow().setText(person.getString("identity"));
		getPhone1show().setText(person.getString("phone1"));
		getPhone2show().setText(person.getString("phone2"));
		getAddr1show().setText(person.getString("Addr1"));
		getAddr2show().setText(person.getString("Addr2"));
		getBirthdayshow().setText(person.getDate("birthday").toString());
		getJoindayshow().setText(person.getDate("joinday").toString());
		getZipshow().setText(person.getString("zipcode"));
		Integer age = person.getInt("age");
		getAgeshow().setText(age.toString());
		String role = person.getString("rolekey");
		PreparedStatement getrole = MyConnection.getConnection().prepareStatement("select roletype from role where rolekey=?");
		getrole.setString(1, role);
		ResultSet roletype=getrole.executeQuery();
		roletype.next();
		getRoleshow().setText(roletype.getString("roletype"));
		}
	protected void switchPanels(JLayeredPane kenn, JPanel abc) {
		kenn.removeAll();
		kenn.add(abc);
		kenn.repaint();
		kenn.revalidate();
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("Cancel");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						refreshShow();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					switchPanels(layeredPane,show);
				}
			});
			button_1.setBounds(81, 632, 97, 25);
		}
		return button_1;
	}
	private JPanel getShow() {
		if (show == null) {
			show = new JPanel();
			show.setLayout(null);
			show.add(getLabel_8());
			show.add(getLabel_9());
			show.add(getLabel_10());
			show.add(getLabel_11());
			show.add(getLabel_12());
			show.add(getLabel_13());
			show.add(getLabel_14());
			show.add(getLabel_15());
			show.add(getLabel_16());
			show.add(getLabel_17());
			show.add(getLabel_18());
			show.add(getPeridshow());
			show.add(getFnameshow());
			show.add(getCmndshow());
			show.add(getEmailshow());
			show.add(getBirthdayshow());
			show.add(getAgeshow());
			show.add(getRoleshow());
			show.add(getJoindayshow());
			show.add(getPhone1show());
			show.add(getPhone2show());
			show.add(getAddr1show());
			show.add(getAddr2show());
			show.add(getZipshow());
			show.add(getBtnNewButton());
			show.add(getBtnCancel());
		}
		return show;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("personid");
			label_8.setBounds(89, 44, 56, 16);
		}
		return label_8;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("Full name");
			label_9.setBounds(89, 89, 56, 16);
		}
		return label_9;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("Identity");
			label_10.setBounds(89, 120, 56, 16);
		}
		return label_10;
	}
	private JLabel getLabel_11() {
		if (label_11 == null) {
			label_11 = new JLabel("Birthday");
			label_11.setBounds(89, 189, 56, 16);
		}
		return label_11;
	}
	private JLabel getLabel_12() {
		if (label_12 == null) {
			label_12 = new JLabel("Age");
			label_12.setBounds(89, 243, 56, 16);
		}
		return label_12;
	}
	private JLabel getLabel_13() {
		if (label_13 == null) {
			label_13 = new JLabel("Role");
			label_13.setBounds(89, 284, 56, 16);
		}
		return label_13;
	}
	private JLabel getLabel_14() {
		if (label_14 == null) {
			label_14 = new JLabel("Join day");
			label_14.setBounds(89, 325, 56, 16);
		}
		return label_14;
	}
	private JLabel getLabel_15() {
		if (label_15 == null) {
			label_15 = new JLabel("Phone numbers");
			label_15.setBounds(89, 367, 102, 16);
		}
		return label_15;
	}
	private JLabel getLabel_16() {
		if (label_16 == null) {
			label_16 = new JLabel("Addresses");
			label_16.setBounds(89, 431, 80, 16);
		}
		return label_16;
	}
	private JLabel getLabel_17() {
		if (label_17 == null) {
			label_17 = new JLabel("ZIP ");
			label_17.setBounds(89, 515, 56, 16);
		}
		return label_17;
	}
	private JLabel getLabel_18() {
		if (label_18 == null) {
			label_18 = new JLabel("Email");
			label_18.setBounds(89, 160, 56, 16);
		}
		return label_18;
	}
	private JLabel getPeridshow() {
		if (peridshow == null) {
			peridshow = new JLabel("<dynamic>");
			peridshow.setBounds(169, 44, 56, 16);
		}
		return peridshow;
	}
	private JLabel getFnameshow() {
		if (fnameshow == null) {
			fnameshow = new JLabel((String) null);
			fnameshow.setBounds(194, 89, 126, 16);
		}
		return fnameshow;
	}
	private JLabel getCmndshow() {
		if (cmndshow == null) {
			cmndshow = new JLabel((String) null);
			cmndshow.setBounds(183, 120, 117, 16);
		}
		return cmndshow;
	}
	private JLabel getEmailshow() {
		if (emailshow == null) {
			emailshow = new JLabel((String) null);
			emailshow.setBounds(169, 160, 166, 16);
		}
		return emailshow;
	}
	private JLabel getBirthdayshow() {
		if (birthdayshow == null) {
			birthdayshow = new JLabel((String) null);
			birthdayshow.setBounds(169, 189, 131, 16);
		}
		return birthdayshow;
	}
	private JLabel getAgeshow() {
		if (ageshow == null) {
			ageshow = new JLabel("0");
			ageshow.setBounds(157, 243, 56, 16);
		}
		return ageshow;
	}
	private JLabel getRoleshow() {
		if (roleshow == null) {
			roleshow = new JLabel((String) null);
			roleshow.setBounds(169, 284, 131, 16);
		}
		return roleshow;
	}
	private JLabel getJoindayshow() {
		if (joindayshow == null) {
			joindayshow = new JLabel((String) null);
			joindayshow.setBounds(194, 325, 106, 16);
		}
		return joindayshow;
	}
	private JLabel getPhone1show() {
		if (phone1show == null) {
			phone1show = new JLabel((String) null);
			phone1show.setBounds(205, 367, 56, 16);
		}
		return phone1show;
	}
	private JLabel getPhone2show() {
		if (phone2show == null) {
			phone2show = new JLabel((String) null);
			phone2show.setBounds(205, 396, 56, 16);
		}
		return phone2show;
	}
	private JLabel getAddr1show() {
		if (addr1show == null) {
			addr1show = new JLabel((String) null);
			addr1show.setBounds(194, 431, 56, 16);
		}
		return addr1show;
	}
	private JLabel getAddr2show() {
		if (addr2show == null) {
			addr2show = new JLabel((String) null);
			addr2show.setBounds(194, 472, 56, 16);
		}
		return addr2show;
	}
	private JLabel getZipshow() {
		if (zipshow == null) {
			zipshow = new JLabel((String) null);
			zipshow.setBounds(183, 515, 56, 16);
		}
		return zipshow;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Edit");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setIn();
					switchPanels(layeredPane,edit);
				}
			});
			btnNewButton.setBounds(516, 648, 97, 25);
		}
		return btnNewButton;
	}
	protected void setIn() {
		getFnamein().setText(getFnameshow().getText());
		getCmndin().setText(getCmndshow().getText());
		getPhone1in().setText(getPhone1show().getText());
		getPhone2in().setText(getPhone2show().getText());
		getAddr1in().setText(getAddr1show().getText());
		getAddr2in().setText(getAddr2show().getText());
		getBirthdayin().setText(getBirthdayshow().getText());
		getEmailin().setText(getEmailshow().getText());
		getZipin().setText(getZipshow().getText());
		getAgein().setText(getAgeshow().getText());
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancel.setBounds(26, 648, 97, 25);
		}
		return btnCancel;
	}
}
