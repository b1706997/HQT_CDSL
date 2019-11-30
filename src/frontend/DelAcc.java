package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DelAcc extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel accid;
	private JLabel uname;
	private JLabel psw;
	private JLabel status;
	private JLabel acctype;
	private JLabel balance;
	private JButton btnRemove;
	private JButton btnNewButton;
	private JLabel lblOwner;
	private JLabel ownername;
	private String accc;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DelAcc(String accid,JButton invi) throws SQLException {
		accc = accid;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 593);
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
		contentPane.add(getAccid());
		contentPane.add(getUname());
		contentPane.add(getPsw());
		contentPane.add(getStatus());
		contentPane.add(getAcctype());
		contentPane.add(getBalance());
		contentPane.add(getBtnRemove());
		contentPane.add(getBtnNewButton());
		contentPane.add(getLblOwner());
		contentPane.add(getOwnername());
		NumberFormat myformat = NumberFormat.getInstance();
		myformat.setGroupingUsed(true);
		
		// HERE
				String query="select accountid,username,acckey,password,status,personid,balance from account where accountid=?";
				PreparedStatement getacc = MyConnection.getConnection().prepareStatement(query);
				getacc.setString(1, accid);
				ResultSet acc=getacc.executeQuery();
				acc.next();
				getUname().setText(acc.getString("username"));
				getAccid().setText(acc.getString("accountid"));
				getPsw().setText(acc.getString("password"));
				Long balance = acc.getLong("balance");
				getBalance().setText(myformat.format(balance));
				String status = acc.getString("status");
				getStatus().setText(status);
				
				String perid = acc.getString("personid");
				PreparedStatement getper = MyConnection.getConnection().prepareStatement("select fullname from person where personid=?");
				getper.setString(1, perid);
				ResultSet per = getper.executeQuery();
				if(per.next())
				{
					getOwnername().setText(per.getString("fullname"));
				}
				else
				{
					getOwnername().setText("NULL");
				}
				
				
				String acckey = acc.getString("acckey");
				PreparedStatement gettype = MyConnection.getConnection().prepareStatement("select acctype from acctype where acckey=?");
				gettype.setString(1, acckey);
				ResultSet type = gettype.executeQuery();
				type.next();
				getAcctype().setText(type.getString("acctype"));
				
			// WINDOWN CLOSE LISTENER
				this.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				    	
				        //invi.setEnabled(true);
				        invi.doClick();
				    }
				});

	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Account ID");
			lblNewLabel.setBounds(26, 57, 96, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Username");
			lblNewLabel_1.setBounds(26, 110, 76, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Password");
			lblNewLabel_2.setBounds(26, 166, 56, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Status");
			lblNewLabel_3.setBounds(26, 220, 56, 16);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Account type");
			lblNewLabel_4.setBounds(26, 288, 76, 16);
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Balance");
			lblNewLabel_5.setBounds(26, 349, 56, 16);
		}
		return lblNewLabel_5;
	}
	private JLabel getAccid() {
		if (accid == null) {
			accid = new JLabel("New label");
			accid.setBounds(134, 57, 56, 16);
		}
		return accid;
	}
	private JLabel getUname() {
		if (uname == null) {
			uname = new JLabel("New label");
			uname.setBounds(134, 110, 56, 16);
		}
		return uname;
	}
	private JLabel getPsw() {
		if (psw == null) {
			psw = new JLabel("New label");
			psw.setBounds(134, 166, 56, 16);
		}
		return psw;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("New label");
			status.setBounds(122, 220, 56, 16);
		}
		return status;
	}
	private JLabel getAcctype() {
		if (acctype == null) {
			acctype = new JLabel("New label");
			acctype.setBounds(122, 288, 56, 16);
		}
		return acctype;
	}
	private JLabel getBalance() {
		if (balance == null) {
			balance = new JLabel("New label");
			balance.setBounds(122, 349, 56, 16);
		}
		return balance;
	}
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String query = "delete from account where accountid=?";
					PreparedStatement remove;
					try {
						remove = MyConnection.getConnection().prepareStatement(query);
						remove.setString(1, accc);
						remove.execute();
						JOptionPane.showMessageDialog(null, "Account removed");
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			btnRemove.setBounds(435, 508, 97, 25);
		}
		return btnRemove;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Cancel");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton.setBounds(12, 508, 97, 25);
		}
		return btnNewButton;
	}
	private JLabel getLblOwner() {
		if (lblOwner == null) {
			lblOwner = new JLabel("Owner");
			lblOwner.setBounds(26, 392, 56, 16);
		}
		return lblOwner;
	}
	private JLabel getOwnername() {
		if (ownername == null) {
			ownername = new JLabel("New label");
			ownername.setBounds(134, 392, 56, 16);
		}
		return ownername;
	}
}
