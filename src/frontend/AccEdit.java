package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccEdit extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField accid;
	private JTextField psw;
	private JRadioButton lock;
	private JRadioButton unlock;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel acctype;
	private JTextField balance;
	private JLabel ownername;
	private JButton btnApply;
	private JButton btnCancel;
	private JLabel uname;
	private String acccid;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AccEdit(String accid,JButton invi) throws SQLException {
		acccid = accid;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 588);
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
		contentPane.add(getAccid());
		contentPane.add(getPsw());
		contentPane.add(getLock());
		contentPane.add(getUnlock());
		contentPane.add(getAcctype());
		contentPane.add(getBalance());
		contentPane.add(getOwnername());
		contentPane.add(getBtnApply());
		contentPane.add(getBtnCancel());
		
		// HERE
		String query="select accountid,username,acckey,password,status,personid,balance from account where accountid=?";
		PreparedStatement getacc = MyConnection.getConnection().prepareStatement(query);
		getacc.setString(1, accid);
		ResultSet acc=getacc.executeQuery();
		acc.next();
		getAccid().setText(acc.getString("accountid"));
		getPsw().setText(acc.getString("password"));
		Long balance = acc.getLong("balance");
		getBalance().setText(balance.toString());
		boolean status = acc.getBoolean("status");
		if(status)
		{
			unlock.setSelected(true);
		}
		else
		{
			lock.setSelected(true);
		}
		
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
		contentPane.add(getUname());
		
		// CLOSED EVENT LISTENER
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
			lblNewLabel = new JLabel("Account info");
			lblNewLabel.setBounds(192, 13, 178, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Account ID");
			lblNewLabel_1.setBounds(12, 52, 108, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Username");
			lblNewLabel_2.setBounds(12, 95, 56, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Password");
			lblNewLabel_3.setBounds(12, 155, 56, 16);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Status");
			lblNewLabel_4.setBounds(12, 211, 56, 16);
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Account type");
			lblNewLabel_5.setBounds(12, 277, 88, 16);
		}
		return lblNewLabel_5;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("Balance");
			lblNewLabel_6.setBounds(12, 332, 56, 16);
		}
		return lblNewLabel_6;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("Owner");
			lblNewLabel_7.setBounds(12, 390, 56, 16);
		}
		return lblNewLabel_7;
	}
	private JTextField getAccid() {
		if (accid == null) {
			accid = new JTextField();
			accid.setBounds(106, 49, 116, 22);
			accid.setColumns(10);
		}
		return accid;
	}
	private JTextField getPsw() {
		if (psw == null) {
			psw = new JTextField();
			psw.setBounds(106, 152, 116, 22);
			psw.setColumns(10);
		}
		return psw;
	}
	private JRadioButton getLock() {
		if (lock == null) {
			lock = new JRadioButton("LOCKED");
			lock.setForeground(Color.RED);
			buttonGroup.add(lock);
			lock.setBounds(95, 207, 127, 25);
		}
		return lock;
	}
	private JRadioButton getUnlock() {
		if (unlock == null) {
			unlock = new JRadioButton("UNLOCKED");
			unlock.setForeground(new Color(0, 128, 0));
			buttonGroup.add(unlock);
			unlock.setBounds(262, 207, 127, 25);
		}
		return unlock;
	}
	private JLabel getAcctype() {
		if (acctype == null) {
			acctype = new JLabel("New label");
			acctype.setBounds(151, 277, 56, 16);
		}
		return acctype;
	}
	private JTextField getBalance() {
		if (balance == null) {
			balance = new JTextField();
			balance.setBounds(106, 329, 116, 22);
			balance.setColumns(10);
		}
		return balance;
	}
	private JLabel getOwnername() {
		if (ownername == null) {
			ownername = new JLabel("New label");
			ownername.setBounds(106, 390, 56, 16);
		}
		return ownername;
	}
	private JButton getBtnApply() {
		if (btnApply == null) {
			btnApply = new JButton("Apply");
			btnApply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int status;
					if(getLock().isSelected())
					{
						status=0;
					}
					else
					{
						status=1;
					}
					String psw = getPsw().getText();
					String accid = getAccid().getText();
					Long balance = Long.parseLong(getBalance().getText());
					try {
						PreparedStatement update = MyConnection.getConnection().prepareStatement("update account set accountid=?,password=?,balance=?,status=? where accountid=?");
						update.setString(1, accid);
						update.setString(2, psw);
						update.setLong(3, balance);
						update.setInt(4, status);
						update.setString(5, acccid);
						update.execute();
						JOptionPane.showMessageDialog(null, "Account updated");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnApply.setBounds(456, 503, 97, 25);
		}
		return btnApply;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancel.setBounds(12, 503, 97, 25);
		}
		return btnCancel;
	}
	private JLabel getUname() {
		if (uname == null) {
			uname = new JLabel("New label");
			uname.setBounds(116, 95, 56, 16);
		}
		return uname;
	}
}
