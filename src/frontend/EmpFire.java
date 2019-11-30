package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmpFire extends JFrame {

	private JPanel contentPane;
	private JLabel lblPersonid;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
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
	private JLabel lblWorkPlace;
	private JLabel lblPayrate;
	private JLabel payrateshow;
	private JButton btnFire;
	private JButton btnCancel;
	private JLabel lblEmployid;
	private JLabel empidshow;
	private String empid;
	private String perid;
	private JLabel workplaceshow;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EmpFire(String empid,JButton invi) throws SQLException {
		this.empid = empid;
		PreparedStatement getperid = MyConnection.getConnection().prepareStatement("select * from employee where employid=?");
		getperid.setString(1,empid);
		ResultSet emp=getperid.executeQuery();
		emp.next();
		String perid = emp.getString("personid");
		this.perid=perid;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPersonid());
		contentPane.add(getLabel_1());
		contentPane.add(getLabel_2());
		contentPane.add(getLabel_3());
		contentPane.add(getLabel_4());
		contentPane.add(getLabel_5());
		contentPane.add(getLabel_6());
		contentPane.add(getLabel_7());
		contentPane.add(getLabel_8());
		contentPane.add(getLabel_9());
		contentPane.add(getLabel_10());
		contentPane.add(getPeridshow());
		contentPane.add(getFnameshow());
		contentPane.add(getCmndshow());
		contentPane.add(getEmailshow());
		contentPane.add(getBirthdayshow());
		contentPane.add(getAgeshow());
		contentPane.add(getRoleshow());
		contentPane.add(getJoindayshow());
		contentPane.add(getPhone1show());
		contentPane.add(getPhone2show());
		contentPane.add(getAddr1show());
		contentPane.add(getAddr2show());
		contentPane.add(getZipshow());
		contentPane.add(getLblWorkPlace());
		contentPane.add(getLblPayrate());
		contentPane.add(getPayrateshow());
		contentPane.add(getBtnFire());
		contentPane.add(getBtnCancel());
		contentPane.add(getLblEmployid());
		contentPane.add(getLabel_11());
		
		refreshShow();
		getLabel_11().setText(emp.getString("employid"));
		Float prate=emp.getFloat("payrate");
		getPayrateshow().setText(prate.toString());
		contentPane.add(getWorkplaceshow());
		
		// CLOSE WINDOWN LISTENER
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		    	//System.out.print("YES");
		        //invi.setEnabled(true);
		        invi.doClick();
		    }
		});
		
	}
	
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
	private JLabel getLblPersonid() {
		if (lblPersonid == null) {
			lblPersonid = new JLabel("PersonID");
			lblPersonid.setBounds(104, 34, 56, 16);
		}
		return lblPersonid;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Full name");
			label_1.setBounds(104, 79, 56, 16);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Identity");
			label_2.setBounds(104, 110, 56, 16);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Birthday");
			label_3.setBounds(104, 179, 56, 16);
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Age");
			label_4.setBounds(104, 233, 56, 16);
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Role");
			label_5.setBounds(104, 274, 56, 16);
		}
		return label_5;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Join day");
			label_6.setBounds(104, 315, 56, 16);
		}
		return label_6;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("Phone numbers");
			label_7.setBounds(104, 357, 102, 16);
		}
		return label_7;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("Addresses");
			label_8.setBounds(104, 421, 80, 16);
		}
		return label_8;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("ZIP ");
			label_9.setBounds(104, 472, 56, 16);
		}
		return label_9;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("Email");
			label_10.setBounds(104, 150, 56, 16);
		}
		return label_10;
	}
	private JLabel getPeridshow() {
		if (peridshow == null) {
			peridshow = new JLabel((String) null);
			peridshow.setBounds(184, 34, 56, 16);
		}
		return peridshow;
	}
	private JLabel getFnameshow() {
		if (fnameshow == null) {
			fnameshow = new JLabel((String) null);
			fnameshow.setBounds(209, 79, 126, 16);
		}
		return fnameshow;
	}
	private JLabel getCmndshow() {
		if (cmndshow == null) {
			cmndshow = new JLabel((String) null);
			cmndshow.setBounds(198, 110, 117, 16);
		}
		return cmndshow;
	}
	private JLabel getEmailshow() {
		if (emailshow == null) {
			emailshow = new JLabel((String) null);
			emailshow.setBounds(184, 150, 166, 16);
		}
		return emailshow;
	}
	private JLabel getBirthdayshow() {
		if (birthdayshow == null) {
			birthdayshow = new JLabel((String) null);
			birthdayshow.setBounds(184, 179, 131, 16);
		}
		return birthdayshow;
	}
	private JLabel getAgeshow() {
		if (ageshow == null) {
			ageshow = new JLabel("0");
			ageshow.setBounds(172, 233, 56, 16);
		}
		return ageshow;
	}
	private JLabel getRoleshow() {
		if (roleshow == null) {
			roleshow = new JLabel((String) null);
			roleshow.setBounds(184, 274, 131, 16);
		}
		return roleshow;
	}
	private JLabel getJoindayshow() {
		if (joindayshow == null) {
			joindayshow = new JLabel((String) null);
			joindayshow.setBounds(209, 315, 106, 16);
		}
		return joindayshow;
	}
	private JLabel getPhone1show() {
		if (phone1show == null) {
			phone1show = new JLabel((String) null);
			phone1show.setBounds(220, 357, 56, 16);
		}
		return phone1show;
	}
	private JLabel getPhone2show() {
		if (phone2show == null) {
			phone2show = new JLabel((String) null);
			phone2show.setBounds(220, 386, 56, 16);
		}
		return phone2show;
	}
	private JLabel getAddr1show() {
		if (addr1show == null) {
			addr1show = new JLabel((String) null);
			addr1show.setBounds(209, 421, 56, 16);
		}
		return addr1show;
	}
	private JLabel getAddr2show() {
		if (addr2show == null) {
			addr2show = new JLabel((String) null);
			addr2show.setBounds(209, 462, 56, 16);
		}
		return addr2show;
	}
	private JLabel getZipshow() {
		if (zipshow == null) {
			zipshow = new JLabel((String) null);
			zipshow.setBounds(209, 472, 56, 16);
		}
		return zipshow;
	}
	private JLabel getLblWorkPlace() {
		if (lblWorkPlace == null) {
			lblWorkPlace = new JLabel("Work place");
			lblWorkPlace.setBounds(104, 563, 56, 16);
		}
		return lblWorkPlace;
	}
	private JLabel getLblPayrate() {
		if (lblPayrate == null) {
			lblPayrate = new JLabel("Payrate");
			lblPayrate.setBounds(104, 603, 56, 16);
		}
		return lblPayrate;
	}
	private JLabel getPayrateshow() {
		if (payrateshow == null) {
			payrateshow = new JLabel("New label");
			payrateshow.setBounds(220, 603, 56, 16);
		}
		return payrateshow;
	}
	private JButton getBtnFire() {
		if (btnFire == null) {
			btnFire = new JButton("Fire");
			btnFire.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String firequery = "call fire_emp(?)";
					try {
						CallableStatement fire_emp = MyConnection.getConnection().prepareCall(firequery);
						fire_emp.setString(1, empid);
						fire_emp.execute();
						JOptionPane.showMessageDialog(null,"Employee fired");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnFire.setBounds(482, 633, 97, 25);
		}
		return btnFire;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancel.setBounds(12, 633, 97, 25);
		}
		return btnCancel;
	}
	private JLabel getLblEmployid() {
		if (lblEmployid == null) {
			lblEmployid = new JLabel("EmployID");
			lblEmployid.setBounds(104, 522, 56, 16);
		}
		return lblEmployid;
	}
	private JLabel getLabel_11() {
		if (empidshow == null) {
			empidshow = new JLabel("New label");
			empidshow.setBounds(220, 522, 56, 16);
		}
		return empidshow;
	}
	private JLabel getWorkplaceshow() {
		if (workplaceshow == null) {
			workplaceshow = new JLabel("New label");
			workplaceshow.setBounds(220, 563, 56, 16);
		}
		return workplaceshow;
	}
}
