package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerDelConfirm extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
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
	private JLabel accshow;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private String perid;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PerDelConfirm frame = new PerDelConfirm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PerDelConfirm(String perid) throws SQLException {
		this.perid = perid;
		String perquery = "select fullname,identity,email,birthday,age,rolekey,joinday,phone1,phone2,addr1,addr2,zipcode from person where personid=?";
		PreparedStatement getper = MyConnection.getConnection().prepareStatement(perquery);
		getper.setString(1, perid);
		ResultSet person = getper.executeQuery();
		person.next();
		getPeridshow().setText(perid);
		getFnameshow().setText(person.getNString("fullname"));
		getCmndshow().setText(person.getString("identity"));
		getEmailshow().setText(person.getString("email"));
		Integer ageee = person.getInt("age");
		getAgeshow().setText(ageee.toString());
		// get role 
		PreparedStatement getrole = MyConnection.getConnection().prepareStatement("select roletype from role where rolekey=?");
		getrole.setString(1, person.getString("rolekey"));
		ResultSet role=getrole.executeQuery();
		role.next();
		getRoleshow().setText(role.getString("roletype"));
		
		Date datee1 = person.getDate("birthday");
		Date datee = person.getDate("joinday");
		getBirthdayshow().setText(datee1.toString());
		getJoindayshow().setText(datee.toString());
		getPhone1show().setText(person.getString("phone1"));
		getPhone2show().setText(person.getString("phone2"));
		getAddr1show().setText(person.getString("addr1"));
		getAddr2show().setText(person.getString("addr2"));
		getZipshow().setText(person.getString("zipcode"));
		
		String accquery = "select username,balance from account where personid=?";
		PreparedStatement accs = MyConnection.getConnection().prepareStatement(accquery);
		accs.setString(1, perid);
		ResultSet account = accs.executeQuery();
		StringBuilder accstring = new StringBuilder("<html>");
		while(account.next())
		{
			NumberFormat myFormat = NumberFormat.getInstance();
			myFormat.setGroupingUsed(true);
			Long bal=account.getLong("balance");
			String format=myFormat.format(bal);
			accstring.append("Username: "+account.getString("username")+" Balance: "+format+"<br>");
		}
		accstring.append("</html>");
		getAccshow().setText(accstring.toString());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getLblNewLabel_4());
		contentPane.add(getLblNewLabel_5());
		contentPane.add(getLblNewLabel_6());
		contentPane.add(getLblNewLabel_7());
		contentPane.add(getLblNewLabel_8());
		contentPane.add(getLblNewLabel_9());
		contentPane.add(getLblNewLabel_10());
		contentPane.add(getLblNewLabel_11());
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
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Please confirm your removal of this person");
			lblNewLabel.setBounds(30, 13, 466, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("personid");
			lblNewLabel_1.setBounds(12, 61, 56, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Full name");
			lblNewLabel_2.setBounds(12, 106, 56, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Identity");
			lblNewLabel_3.setBounds(12, 137, 56, 16);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Birthday");
			lblNewLabel_4.setBounds(12, 206, 56, 16);
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Age");
			lblNewLabel_5.setBounds(12, 260, 56, 16);
		}
		return lblNewLabel_5;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("Role");
			lblNewLabel_6.setBounds(12, 301, 56, 16);
		}
		return lblNewLabel_6;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("Join day");
			lblNewLabel_7.setBounds(12, 342, 56, 16);
		}
		return lblNewLabel_7;
	}
	private JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("Phone numbers");
			lblNewLabel_8.setBounds(12, 384, 102, 16);
		}
		return lblNewLabel_8;
	}
	private JLabel getLblNewLabel_9() {
		if (lblNewLabel_9 == null) {
			lblNewLabel_9 = new JLabel("Addresses");
			lblNewLabel_9.setBounds(12, 448, 80, 16);
		}
		return lblNewLabel_9;
	}
	private JLabel getLblNewLabel_10() {
		if (lblNewLabel_10 == null) {
			lblNewLabel_10 = new JLabel("ZIP ");
			lblNewLabel_10.setBounds(12, 532, 56, 16);
		}
		return lblNewLabel_10;
	}
	private JLabel getLblNewLabel_11() {
		if (lblNewLabel_11 == null) {
			lblNewLabel_11 = new JLabel("Email");
			lblNewLabel_11.setBounds(12, 177, 56, 16);
		}
		return lblNewLabel_11;
	}
	private JLabel getPeridshow() {
		if (peridshow == null) {
			peridshow = new JLabel("New label");
			peridshow.setBounds(92, 61, 56, 16);
		}
		return peridshow;
	}
	private JLabel getFnameshow() {
		if (fnameshow == null) {
			fnameshow = new JLabel("New label");
			fnameshow.setBounds(117, 106, 126, 16);
		}
		return fnameshow;
	}
	private JLabel getCmndshow() {
		if (cmndshow == null) {
			cmndshow = new JLabel("New label");
			cmndshow.setBounds(106, 137, 117, 16);
		}
		return cmndshow;
	}
	private JLabel getEmailshow() {
		if (emailshow == null) {
			emailshow = new JLabel("New label");
			emailshow.setBounds(92, 177, 166, 16);
		}
		return emailshow;
	}
	private JLabel getBirthdayshow() {
		if (birthdayshow == null) {
			birthdayshow = new JLabel("New label");
			birthdayshow.setBounds(92, 206, 131, 16);
		}
		return birthdayshow;
	}
	private JLabel getAgeshow() {
		if (ageshow == null) {
			ageshow = new JLabel("New label");
			ageshow.setBounds(80, 260, 56, 16);
		}
		return ageshow;
	}
	private JLabel getRoleshow() {
		if (roleshow == null) {
			roleshow = new JLabel("New label");
			roleshow.setBounds(92, 301, 131, 16);
		}
		return roleshow;
	}
	private JLabel getJoindayshow() {
		if (joindayshow == null) {
			joindayshow = new JLabel("New label");
			joindayshow.setBounds(117, 342, 106, 16);
		}
		return joindayshow;
	}
	private JLabel getPhone1show() {
		if (phone1show == null) {
			phone1show = new JLabel("New label");
			phone1show.setBounds(128, 384, 56, 16);
		}
		return phone1show;
	}
	private JLabel getPhone2show() {
		if (phone2show == null) {
			phone2show = new JLabel("New label");
			phone2show.setBounds(128, 413, 56, 16);
		}
		return phone2show;
	}
	private JLabel getAddr1show() {
		if (addr1show == null) {
			addr1show = new JLabel("New label");
			addr1show.setBounds(117, 448, 56, 16);
		}
		return addr1show;
	}
	private JLabel getAddr2show() {
		if (addr2show == null) {
			addr2show = new JLabel("New label");
			addr2show.setBounds(117, 489, 56, 16);
		}
		return addr2show;
	}
	private JLabel getZipshow() {
		if (zipshow == null) {
			zipshow = new JLabel("New label");
			zipshow.setBounds(106, 532, 56, 16);
		}
		return zipshow;
	}
	private JLabel getAccshow() {
		if (accshow == null) {
			accshow = new JLabel("New label");
		}
		return accshow;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Confirm");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// CONFIRM => ALL TRANSACTION,ACCOUNT INCLUDED THIS PERSON WILL BE REMOVE
					try {
						CallableStatement del_person = MyConnection.getConnection().prepareCall("{call del_person(?,?)}");
						del_person.setString(1, perid);
						del_person.registerOutParameter(2, Types.BOOLEAN);
						del_person.execute();
						if(del_person.getBoolean(2))
						{
							JOptionPane.showMessageDialog(null, "Person and accounts removed");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Cannot remove employee or admin");

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(495, 615, 97, 25);
		}
		return btnNewButton;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(355, 87, 257, 245);
			scrollPane.setViewportView(getAccshow());
		}
		return scrollPane;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Cancel");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_1.setBounds(30, 615, 97, 25);
		}
		return btnNewButton_1;
	}
}
