package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.CallableStatement;

import backend.MyConnection;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTable;

public class EmployeeForm extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JLabel lblEbanking;
	private JButton btnDepositwithdraw;
	private JButton btnCreateNewAccount;
	private JButton btnWorkLogs;
	private JButton btnNewButton;
	private JPanel mainpanel;
	private JPanel transpanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblDeposit;
	private JLabel lblUsername;
	private JLabel lblAmount;
	private JTextField amde;
	private JTextField unamede;
	private JLabel lblWithdraw;
	private JLabel lblNewLabel;
	private JTextField unamewit;
	private JLabel lblAmount_1;
	private JTextField amwit;
	private JLabel lblTransfer;
	private JLabel lblFromUsername;
	private JTextField unametrans1;
	private JLabel lblToUsername;
	private JTextField unametrans2;
	private JLabel lblAmount_2;
	private JTextField amtrans;
	private JTable table;
	private JButton btnBack;
	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JButton btnTransfer;
	private String empid;
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm frame = new EmployeeForm();
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
	
	public EmployeeForm(String accid) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLayeredPane_1());
		contentPane.add(getLblEbanking());
		// GET EMPID
		PreparedStatement getemp = MyConnection.getConnection().prepareStatement("select personid from account where accountid=?");
		getemp.setString(1, accid);
		ResultSet per=getemp.executeQuery();
		per.next();
		String perid=per.getString("personid");
		PreparedStatement empp = MyConnection.getConnection().prepareStatement("select * from employee where personid=?");
		empp.setString(1,perid);
		ResultSet emp = empp.executeQuery();
		emp.next();
		String empid = emp.getString("employid");
		this.empid = empid;
	}
	public void switchPanels(JLayeredPane kenn,JPanel abc)
	{
		kenn.removeAll();
		kenn.add(abc);
		kenn.repaint();
		kenn.revalidate();
	}
	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setBounds(0, 89, 1053, 543);
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getTranspanel(), "name_259910089602500");
			layeredPane.add(getMainpanel(), "name_259910117734900");
		}
		return layeredPane;
	}
	private JLabel getLblEbanking() {
		if (lblEbanking == null) {
			lblEbanking = new JLabel("E-Banking");
			lblEbanking.setBounds(480, 13, 56, 16);
		}
		return lblEbanking;
	}
	private JButton getBtnDepositwithdraw() {
		if (btnDepositwithdraw == null) {
			btnDepositwithdraw = new JButton("Create transaction");
			btnDepositwithdraw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switchPanels(layeredPane,transpanel);
				}
			});
		}
		return btnDepositwithdraw;
	}
	private JButton getBtnCreateNewAccount() {
		if (btnCreateNewAccount == null) {
			btnCreateNewAccount = new JButton("Create new account");
		}
		return btnCreateNewAccount;
	}
	private JButton getBtnWorkLogs() {
		if (btnWorkLogs == null) {
			btnWorkLogs = new JButton("Work logs");
		}
		return btnWorkLogs;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Personal");
		}
		return btnNewButton;
	}
	private JPanel getMainpanel() {
		if (mainpanel == null) {
			mainpanel = new JPanel();
			mainpanel.add(getBtnDepositwithdraw());
			mainpanel.add(getBtnCreateNewAccount());
			mainpanel.add(getBtnWorkLogs());
			mainpanel.add(getBtnNewButton());
		}
		return mainpanel;
	}
	private JPanel getTranspanel() {
		if (transpanel == null) {
			transpanel = new JPanel();
			transpanel.setLayout(null);
			transpanel.add(getLblDeposit());
			transpanel.add(getLblUsername());
			transpanel.add(getLblAmount());
			transpanel.add(getAmde());
			transpanel.add(getUnamede());
			transpanel.add(getLblWithdraw());
			transpanel.add(getLblNewLabel());
			transpanel.add(getUnamewit());
			transpanel.add(getLblAmount_1());
			transpanel.add(getAmwit());
			transpanel.add(getLblTransfer());
			transpanel.add(getLblFromUsername());
			transpanel.add(getUnametrans1());
			transpanel.add(getLblToUsername());
			transpanel.add(getUnametrans2());
			transpanel.add(getLblAmount_2());
			transpanel.add(getAmtrans());
			transpanel.add(getTable());
			transpanel.add(getBtnBack());
			transpanel.add(getBtnDeposit());
			transpanel.add(getBtnWithdraw());
			transpanel.add(getBtnTransfer());
		}
		return transpanel;
	}
	private JLabel getLblDeposit() {
		if (lblDeposit == null) {
			lblDeposit = new JLabel("Deposit");
			lblDeposit.setBounds(39, 13, 56, 16);
		}
		return lblDeposit;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username");
			lblUsername.setBounds(0, 59, 65, 16);
		}
		return lblUsername;
	}
	private JLabel getLblAmount() {
		if (lblAmount == null) {
			lblAmount = new JLabel("Amount");
			lblAmount.setBounds(0, 98, 56, 16);
		}
		return lblAmount;
	}
	private JTextField getAmde() {
		if (amde == null) {
			amde = new JTextField();
			amde.setBounds(81, 95, 116, 22);
			amde.setColumns(10);
		}
		return amde;
	}
	private JTextField getUnamede() {
		if (unamede == null) {
			unamede = new JTextField();
			unamede.setBounds(81, 56, 116, 22);
			unamede.setColumns(10);
		}
		return unamede;
	}
	private JLabel getLblWithdraw() {
		if (lblWithdraw == null) {
			lblWithdraw = new JLabel("Withdraw");
			lblWithdraw.setBounds(388, 13, 56, 16);
		}
		return lblWithdraw;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Username");
			lblNewLabel.setBounds(346, 59, 56, 16);
		}
		return lblNewLabel;
	}
	private JTextField getUnamewit() {
		if (unamewit == null) {
			unamewit = new JTextField();
			unamewit.setBounds(430, 56, 116, 22);
			unamewit.setColumns(10);
		}
		return unamewit;
	}
	private JLabel getLblAmount_1() {
		if (lblAmount_1 == null) {
			lblAmount_1 = new JLabel("Amount");
			lblAmount_1.setBounds(356, 98, 56, 16);
		}
		return lblAmount_1;
	}
	private JTextField getAmwit() {
		if (amwit == null) {
			amwit = new JTextField();
			amwit.setBounds(430, 95, 116, 22);
			amwit.setColumns(10);
		}
		return amwit;
	}
	private JLabel getLblTransfer() {
		if (lblTransfer == null) {
			lblTransfer = new JLabel("Transfer");
			lblTransfer.setBounds(866, 13, 56, 16);
		}
		return lblTransfer;
	}
	private JLabel getLblFromUsername() {
		if (lblFromUsername == null) {
			lblFromUsername = new JLabel("From username");
			lblFromUsername.setBounds(747, 59, 71, 16);
		}
		return lblFromUsername;
	}
	private JTextField getUnametrans1() {
		if (unametrans1 == null) {
			unametrans1 = new JTextField();
			unametrans1.setBounds(849, 56, 116, 22);
			unametrans1.setColumns(10);
		}
		return unametrans1;
	}
	private JLabel getLblToUsername() {
		if (lblToUsername == null) {
			lblToUsername = new JLabel("To username");
			lblToUsername.setBounds(747, 98, 56, 16);
		}
		return lblToUsername;
	}
	private JTextField getUnametrans2() {
		if (unametrans2 == null) {
			unametrans2 = new JTextField();
			unametrans2.setBounds(849, 95, 116, 22);
			unametrans2.setColumns(10);
		}
		return unametrans2;
	}
	private JLabel getLblAmount_2() {
		if (lblAmount_2 == null) {
			lblAmount_2 = new JLabel("Amount");
			lblAmount_2.setBounds(747, 137, 56, 16);
		}
		return lblAmount_2;
	}
	private JTextField getAmtrans() {
		if (amtrans == null) {
			amtrans = new JTextField();
			amtrans.setBounds(849, 134, 116, 22);
			amtrans.setColumns(10);
		}
		return amtrans;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBounds(12, 240, 1029, 245);
		}
		return table;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.setBounds(-2, 518, 97, 25);
		}
		return btnBack;
	}
	private JButton getBtnDeposit() {
		if (btnDeposit == null) {
			btnDeposit = new JButton("Deposit");
			btnDeposit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String uname = getUnamede().getText();
					String am = getAmde().getText();
					if(uname.trim().length()==0||am.trim().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Please fill in all fields");
						return;
					}
					else
					{
						Long amlong = Long.parseLong(am);
						// CHECK EXISTS FIRST
						try {
							if(check_exist(uname))
							{
								// DEPOSIT
								//-get accid to confirm psw first
								PreparedStatement pSt = MyConnection.getConnection().prepareStatement("select accountid from account where username=?");
								pSt.setString(1, uname);
								ResultSet getrs = pSt.executeQuery();
								getrs.next();
								String accid = getrs.getString("accountid");
								PswConfirm a = new PswConfirm(accid);
								a.setVisible(true);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			});
			btnDeposit.setBounds(39, 170, 97, 25);
		}
		return btnDeposit;
	}
	private boolean check_exist(String uname) throws SQLException
	{
		boolean rs = false;
		String checkquery = "select accountid from account where username=?";
		PreparedStatement check = MyConnection.getConnection().prepareStatement(checkquery);
		check.setString(1, uname);
		ResultSet getaccid=check.executeQuery();
		if(getaccid.next())
		{
			rs = true;
		}
		return rs;
	}
	private JButton getBtnWithdraw() {
		if (btnWithdraw == null) {
			btnWithdraw = new JButton("Withdraw");
			btnWithdraw.setBounds(422, 170, 97, 25);
		}
		return btnWithdraw;
	}
	private JButton getBtnTransfer() {
		if (btnTransfer == null) {
			btnTransfer = new JButton("Transfer");
			btnTransfer.setBounds(866, 184, 97, 25);
		}
		return btnTransfer;
	}
}
