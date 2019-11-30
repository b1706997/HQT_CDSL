package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class UserForm extends JFrame {
	Long cacba = (long) 0;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForm frame = new UserForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	JLayeredPane layeredPane = new JLayeredPane();
	private JTextField amountbox;
	private JTable depositlogtable;
	
	Float getrateint ;
	Long oldball;
	String acctype;
	private JTable transferlogtable;
	private JTextField unamein;
	private JTextField amountin;
	
	ResultSet translogre;
	String receiverid;
	private JButton btnNewButton_1;
	private JTable table;
	private JScrollPane scrollPane;
	JPanel main;
	
	
	
	public void switchPanels(JPanel abc)
		{
			layeredPane.removeAll();
			layeredPane.add(abc);
			layeredPane.repaint();
			layeredPane.revalidate();
		}
	
	
	/**
	 * Create the frame.
	 */
	public UserForm(String accid) throws SQLException {
		getContentPane().setBackground(SystemColor.activeCaption);
		CallableStatement cSt = null;
		PreparedStatement pSt = null;
		ResultSet rs = null;
		Long newball = (long) 0;
		
		// GET RATE
		CallableStatement getrate = MyConnection.getConnection().prepareCall("{?=call get_accrate(?)}");
		getrate.registerOutParameter(1, Types.FLOAT);
		getrate.setString(2, accid);
		getrate.executeUpdate();
		getrateint = (getrate.getFloat(1) * 100);
		// GET OLD BALANCE NEW BALANCE 
		CallableStatement getbal = MyConnection.getConnection().prepareCall("{?=call get_balance(?)}");
		getbal.registerOutParameter(1, Types.FLOAT);
		getbal.setString(2,accid);
		getbal.executeUpdate();
		oldball = getbal.getLong(1);
		// get person name for display
		cSt = MyConnection.getConnection().prepareCall("{?=call get_name(?)}");
		cSt.registerOutParameter(1, Types.VARCHAR);
		cSt.setString(2, accid);
		cSt.executeUpdate();
		String name = cSt.getString(1);
		// get acctype for display
		CallableStatement getacctype = MyConnection.getConnection().prepareCall("{?=call get_acctype(?)}");
		getacctype.registerOutParameter(1, Types.VARCHAR);
		getacctype.setString(2, accid);
		getacctype.executeUpdate();
		acctype = getacctype.getString(1);
		
		// GET TRANSFER USER LOG
		String translogquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Type',username as 'From/To',amount as 'Amount',newbalance as 'New Balance' from usertransfer where senderid=?";
		PreparedStatement gettranslog=MyConnection.getConnection().prepareStatement(translogquery);
		gettranslog.setString(1,accid);
		translogre = gettranslog.executeQuery();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1221, 690);
		getContentPane().setLayout(null);
		
		JLabel lblEbankingSystem = new JLabel("E-Banking System");
		lblEbankingSystem.setBounds(355, 0, 475, 82);
		getContentPane().add(lblEbankingSystem);
		lblEbankingSystem.setForeground(SystemColor.control);
		lblEbankingSystem.setBackground(SystemColor.controlShadow);
		lblEbankingSystem.setFont(new Font("Sitka Banner", Font.BOLD, 38));
		lblEbankingSystem.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel welcome = new JLabel("Welcome back, ");
		welcome.setForeground(SystemColor.textHighlight);
		welcome.setFont(new Font("Verdana", Font.PLAIN, 16));
		welcome.setBounds(867, 40, 130, 27);
		getContentPane().add(welcome);
		
		JButton unamebutton = new JButton(name);
		unamebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AccountInfo a = new AccountInfo(accid);
					a.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		unamebutton.setForeground(SystemColor.textHighlight);
		unamebutton.setBackground(SystemColor.controlHighlight);
		unamebutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		unamebutton.setBounds(1004, 40, 187, 27);
		getContentPane().add(unamebutton);
		layeredPane.setBackground(SystemColor.text);
		
		//JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 73, 1203, 569);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		main = new JPanel();
		main.setBackground(SystemColor.text);
		layeredPane.add(main);
		main.setLayout(null);
		
		JPanel leftpanelmain = new JPanel();
		leftpanelmain.setBackground(SystemColor.textHighlightText);
		leftpanelmain.setBounds(0, 0, 602, 566);
		main.add(leftpanelmain);
		leftpanelmain.setLayout(null);
		
		JLabel label_30 = new JLabel("Balance: ");
		label_30.setForeground(SystemColor.textHighlight);
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_30.setBounds(12, 69, 148, 40);
		leftpanelmain.add(label_30);
		
		JLabel curballmain = new JLabel("0");
		curballmain.setHorizontalAlignment(SwingConstants.CENTER);
		curballmain.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		curballmain.setBounds(157, 139, 292, 58);
		leftpanelmain.add(curballmain);
		
		JLabel label_32 = new JLabel("VND");
		label_32.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		label_32.setBounds(415, 154, 56, 34);
		leftpanelmain.add(label_32);
		
		JLabel label_33 = new JLabel("Account type: ");
		label_33.setForeground(SystemColor.textHighlight);
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_33.setBounds(12, 237, 196, 34);
		leftpanelmain.add(label_33);
		
		JLabel typemain = new JLabel("<dynamic>");
		typemain.setHorizontalAlignment(SwingConstants.CENTER);
		typemain.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		typemain.setBounds(191, 289, 226, 40);
		leftpanelmain.add(typemain);
		
		JLabel label_35 = new JLabel("Interest Rate:");
		label_35.setForeground(SystemColor.textHighlight);
		label_35.setHorizontalAlignment(SwingConstants.CENTER);
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_35.setBounds(12, 355, 196, 34);
		leftpanelmain.add(label_35);
		
		JLabel ratemain = new JLabel("0.0");
		ratemain.setHorizontalAlignment(SwingConstants.CENTER);
		ratemain.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		ratemain.setBounds(191, 427, 226, 40);
		leftpanelmain.add(ratemain);
		//switchPanels(main);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(600, 0, 2, 566);
		main.add(separator_1);
		
		JButton withdrawbutton = new JButton("DEPOSIT/WITHDRAW");
		withdrawbutton.setForeground(SystemColor.textHighlight);
		withdrawbutton.setBackground(SystemColor.controlHighlight);
		withdrawbutton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		withdrawbutton.setBounds(731, 128, 345, 45);
		main.add(withdrawbutton);
		
		JButton transferbutton = new JButton("TRANSFER");
		transferbutton.setForeground(SystemColor.textHighlight);
		transferbutton.setBackground(SystemColor.controlHighlight);
		
		transferbutton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		transferbutton.setBounds(731, 215, 345, 45);
		main.add(transferbutton);
		
		
		
		JButton lockbutton = new JButton("LOGOUT");
		lockbutton.setForeground(SystemColor.textHighlight);
		lockbutton.setBackground(SystemColor.controlHighlight);
		lockbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginForm login = new LoginForm();
				login.setVisible(true);
				dispose();
			}
		});
		lockbutton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lockbutton.setBounds(731, 398, 345, 45);
		main.add(lockbutton);
		
		
		JPanel deposit = new JPanel();
		deposit.setBackground(SystemColor.text);
		layeredPane.add(deposit, "name_260304101059000");
		deposit.setLayout(null);
		
	
		
		JScrollPane depologs = new JScrollPane();
		depologs.setBounds(490, 314, 713, 255);
		deposit.add(depologs);
		depologs.getViewport().setBackground(Color.WHITE);
		
		// POPULATE MAIN INFO
				NumberFormat myform = NumberFormat.getInstance();
				myform.setGroupingUsed(true);
				curballmain.setText(myform.format(oldball));
				typemain.setText(acctype);
				ratemain.setText(getrateint.toString());
				
				JSeparator separator_7 = new JSeparator();
				separator_7.setOrientation(SwingConstants.VERTICAL);
				separator_7.setBounds(590, 0, 12, 566);
				leftpanelmain.add(separator_7);
		
		depositlogtable = new JTable();
		depositlogtable.setBackground(SystemColor.textHighlightText);
		depositlogtable.setForeground(SystemColor.textHighlight);
		depositlogtable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		depologs.setViewportView(depositlogtable);
		depositlogtable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JTableHeader depositlogheader = depositlogtable.getTableHeader();
		depositlogheader.setFont(new Font("Dialog", Font.BOLD, 25));
		
		
		JPanel rightpaneldepo = new JPanel();
		rightpaneldepo.setBackground(SystemColor.textHighlightText);
		rightpaneldepo.setBounds(490, 0, 713, 569);
		deposit.add(rightpaneldepo);
		rightpaneldepo.setLayout(null);
		
		JLabel lblAmount_1 = new JLabel("Amount:");
		lblAmount_1.setForeground(SystemColor.textHighlight);
		lblAmount_1.setBounds(0, 64, 177, 41);
		rightpaneldepo.add(lblAmount_1);
		lblAmount_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAmount_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		amountbox = new JTextField();
		amountbox.setFont(new Font("Arial Unicode MS", Font.PLAIN, 28));
		amountbox.setBounds(120, 118, 481, 82);
		rightpaneldepo.add(amountbox);
		amountbox.setColumns(10);
		
		JButton btnNewButton = new JButton("WITHDRAW");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setForeground(SystemColor.textHighlight);
		
		btnNewButton.setBounds(382, 213, 190, 56);
		rightpaneldepo.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JButton btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setBackground(SystemColor.controlHighlight);
		btnDeposit.setForeground(SystemColor.textHighlight);
		btnDeposit.setBounds(142, 213, 177, 56);
		rightpaneldepo.add(btnDeposit);
		
		btnDeposit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblDepositwithdraw = new JLabel("DEPOSIT / WITHDRAW");
		lblDepositwithdraw.setForeground(SystemColor.textHighlight);
		lblDepositwithdraw.setBounds(28, 0, 685, 85);
		rightpaneldepo.add(lblDepositwithdraw);
		lblDepositwithdraw.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepositwithdraw.setFont(new Font("Times New Roman", Font.BOLD, 38));
		
		JLabel lblLogs = new JLabel("Logs");
		lblLogs.setForeground(SystemColor.textHighlight);
		lblLogs.setBounds(13, 266, 105, 56);
		rightpaneldepo.add(lblLogs);
		lblLogs.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 37, 149, 2);
		rightpaneldepo.add(separator_4);
		
		JLabel amconfirm = new JLabel(amountbox.getText());
		
		
		
		
		
		JPanel leftpaneldepo = new JPanel();
		leftpaneldepo.setBackground(SystemColor.textHighlightText);
		leftpaneldepo.setLayout(null);
		leftpaneldepo.setBounds(0, 0, 491, 569);
		deposit.add(leftpaneldepo);
		
		JLabel label = new JLabel("Balance: ");
		label.setForeground(SystemColor.textHighlight);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label.setBounds(-12, 65, 148, 40);
		leftpaneldepo.add(label);
		
		JLabel label_1 = new JLabel(myform.format(oldball));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		label_1.setBounds(76, 126, 292, 58);
		leftpaneldepo.add(label_1);
		
		JLabel label_2 = new JLabel("VND");
		label_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		label_2.setBounds(358, 141, 56, 34);
		leftpaneldepo.add(label_2);
		
		JLabel label_13 = new JLabel("Account type: ");
		label_13.setForeground(SystemColor.textHighlight);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_13.setBounds(-12, 210, 196, 34);
		leftpaneldepo.add(label_13);
		
		JLabel label_14 = new JLabel(acctype.toString());
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		label_14.setBounds(128, 303, 226, 40);
		leftpaneldepo.add(label_14);
		
		JLabel label_15 = new JLabel("Interest Rate:");
		label_15.setForeground(SystemColor.textHighlight);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_15.setBounds(-12, 385, 196, 34);
		leftpaneldepo.add(label_15);
		
		JLabel label_16 = new JLabel(getrateint.toString());
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		label_16.setBounds(128, 446, 226, 40);
		leftpaneldepo.add(label_16);
		
		JButton depositback = new JButton("BACK");
		depositback.setBackground(SystemColor.controlHighlight);
		depositback.setForeground(SystemColor.textHighlight);
		depositback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getacctype.executeUpdate();
					getbal.executeUpdate();
					getrate.executeUpdate();
					getrateint = (getrate.getFloat(1) * 100);
					oldball = getbal.getLong(1);
					acctype = getacctype.getString(1);
					curballmain.setText(myform.format(oldball));
					typemain.setText(acctype);
					ratemain.setText(getrateint.toString());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switchPanels(main);
			}
		});
		depositback.setFont(new Font("Times New Roman", Font.BOLD, 20));
		depositback.setBounds(12, 516, 95, 40);
		leftpaneldepo.add(depositback);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(-12, 37, 654, 2);
		leftpaneldepo.add(separator_3);
		
		
		
		JPanel confirmdep = new JPanel();
		confirmdep.setBackground(SystemColor.textHighlightText);
		confirmdep.setBounds(0, 0, 1203, 569);
		deposit.add(confirmdep);
		confirmdep.setLayout(null);
		confirmdep.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Please confirm your deposit of");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(12, 79, 382, 76);
		confirmdep.add(lblNewLabel);
		
		JLabel lblVnd_1 = new JLabel("VND");
		lblVnd_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblVnd_1.setBounds(760, 103, 90, 29);
		confirmdep.add(lblVnd_1);
		
		JLabel lblNewLabel_1 = new JLabel("New balance:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(126, 258, 251, 62);
		confirmdep.add(lblNewLabel_1);
		
		JLabel lblOldBalance = new JLabel("Old balance");
		lblOldBalance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblOldBalance.setBounds(126, 168, 251, 62);
		confirmdep.add(lblOldBalance);
		
		withdrawbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(deposit);
				// QUERY STRING
				label_1.setText(myform.format(oldball));
				label_14.setText(acctype);
				label_16.setText(getrateint.toString());
				String logquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Transaction Type',amount as 'Amount' from usertranslogview where senderid=? and receiverid=?";
				try {
					PreparedStatement pSt = MyConnection.getConnection().prepareStatement(logquery);
					pSt.setString(1,accid); // BOTH SENDER AND RECEIVERID == accid
					pSt.setString(2,accid);
					ResultSet rs = pSt.executeQuery();
					depositlogtable.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		JLabel oldconfirm = new JLabel(oldball.toString());
		oldconfirm.setForeground(Color.RED);
		oldconfirm.setFont(new Font("Times New Roman", Font.BOLD, 34));
		oldconfirm.setBounds(487, 168, 251, 62);
		confirmdep.add(oldconfirm);
		
		JLabel newconfirm = new JLabel(cacba.toString());
		newconfirm.setForeground(Color.RED);
		newconfirm.setFont(new Font("Times New Roman", Font.BOLD, 34));
		newconfirm.setBounds(487, 243, 251, 62);
		confirmdep.add(newconfirm);
		
		JLabel label_6 = new JLabel("VND");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_6.setBounds(760, 182, 90, 29);
		confirmdep.add(label_6);
		
		JLabel label_17 = new JLabel("VND");
		label_17.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_17.setBounds(760, 258, 90, 29);
		confirmdep.add(label_17);
		amconfirm.setForeground(Color.RED);
		amconfirm.setFont(new Font("Times New Roman", Font.BOLD, 34));
		amconfirm.setBounds(487, 93, 251, 62);
		confirmdep.add(amconfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setForeground(SystemColor.textHighlight);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getacctype.executeUpdate();
					getbal.executeUpdate();
					getrate.executeUpdate();
					getrateint = (getrate.getFloat(1) * 100);
					oldball = getbal.getLong(1);
					acctype = getacctype.getString(1);
					label_1.setText(myform.format(oldball));
					label_14.setText(acctype);
					label_16.setText(getrateint.toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				leftpaneldepo.setVisible(true);
				rightpaneldepo.setVisible(true);
				confirmdep.setVisible(false);
				depologs.setVisible(true);
				String logquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Transaction Type',amount as 'Amount' from usertranslogview where senderid=? and receiverid=?";
				try {
					PreparedStatement pSt = MyConnection.getConnection().prepareStatement(logquery);
					pSt.setString(1,accid); // BOTH SENDER AND RECEIVERID == accid
					pSt.setString(2,accid);
					ResultSet rs = pSt.executeQuery();
					depositlogtable.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel m = depositlogtable.getColumnModel();
					m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
					m.getColumn(0).setCellRenderer(FormatRenderer.getDateTimeRenderer());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnCancel.setBounds(423, 350, 141, 49);
		
		confirmdep.add(btnCancel);
		
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(SystemColor.controlHighlight);
		btnConfirm.setForeground(SystemColor.textHighlight);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Long am = Long.valueOf(amountbox.getText());
				am = Long.parseLong(amountbox.getText());
				if(am<50000)
				{
					JPanel panel12 = new JPanel();
					JOptionPane.showMessageDialog(panel12, "Smallest deposit amount is 50000 VND", "Error", JOptionPane.ERROR_MESSAGE);
					amountbox.setText(null);
					return;
				}
				try {
					//am = Long.parseLong(amountbox.getText());
					CallableStatement cSt = MyConnection.getConnection().prepareCall("{call deposit(?,?,?)}");
					cSt.setString(1, accid);
					cSt.setFloat(2, am);
					cSt.registerOutParameter(3, Types.INTEGER);
					cSt.executeUpdate();
					getacctype.executeUpdate();
					getbal.executeUpdate();
					getrate.executeUpdate();
					getrateint = (getrate.getFloat(1) * 100);
					oldball = getbal.getLong(1);
					acctype = getacctype.getString(1);
					label_1.setText(myform.format(oldball));
					label_14.setText(acctype);
					label_16.setText(getrateint.toString());
					
					if(cSt.getInt(3)==1)
					{
						JOptionPane.showMessageDialog(null, "Deposit successfully");
						confirmdep.setVisible(false);
						leftpaneldepo.setVisible(true);
						rightpaneldepo.setVisible(true);
						depologs.setVisible(true);
						amountbox.setText("");
						String logquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Transaction Type',amount as 'Amount' from usertranslogview where senderid=? and receiverid=?";
						try {
							PreparedStatement pSt = MyConnection.getConnection().prepareStatement(logquery);
							pSt.setString(1,accid); // BOTH SENDER AND RECEIVERID == accid
							pSt.setString(2,accid);
							ResultSet rs = pSt.executeQuery();
							depositlogtable.setModel(DbUtils.resultSetToTableModel(rs));
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Deposit failed");
						confirmdep.setVisible(false);
						leftpaneldepo.setVisible(true);
						rightpaneldepo.setVisible(true);
						depologs.setVisible(true);
						amountbox.setText("");
						String logquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Transaction Type',amount as 'Amount' from usertranslogview where senderid=? and receiverid=?";
						try {
							PreparedStatement pSt = MyConnection.getConnection().prepareStatement(logquery);
							pSt.setString(1,accid); // BOTH SENDER AND RECEIVERID == accid
							pSt.setString(2,accid);
							ResultSet rs = pSt.executeQuery();
							depositlogtable.setModel(DbUtils.resultSetToTableModel(rs));
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnConfirm.setBounds(687, 350, 141, 49);
		confirmdep.add(btnConfirm);
		
		JPanel confirmwit = new JPanel();
		confirmwit.setBackground(SystemColor.textHighlightText);
		confirmwit.setBounds(0, 0, 1203, 569);
		deposit.add(confirmwit);
		confirmwit.setLayout(null);
		confirmwit.setVisible(false);
		
		JLabel lblPleaseConfirmYour = new JLabel("Please confirm your withdraw of");
		lblPleaseConfirmYour.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblPleaseConfirmYour.setBounds(27, 63, 398, 76);
		confirmwit.add(lblPleaseConfirmYour);
		
		JLabel label_5 = new JLabel("VND");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_5.setBounds(775, 87, 90, 29);
		confirmwit.add(label_5);
		
		JLabel label_18 = new JLabel("New balance:");
		label_18.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_18.setBounds(141, 242, 251, 62);
		confirmwit.add(label_18);
		
		JLabel lblCurrentBalance = new JLabel("Current balance");
		lblCurrentBalance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblCurrentBalance.setBounds(141, 152, 251, 62);
		confirmwit.add(lblCurrentBalance);
		
		JLabel label_20 = new JLabel("0");
		label_20.setForeground(Color.RED);
		label_20.setFont(new Font("Times New Roman", Font.BOLD, 34));
		label_20.setBounds(502, 152, 251, 62);
		confirmwit.add(label_20);
		
		JLabel label_21 = new JLabel("0");
		label_21.setForeground(Color.RED);
		label_21.setFont(new Font("Times New Roman", Font.BOLD, 34));
		label_21.setBounds(502, 227, 251, 62);
		confirmwit.add(label_21);
		
		JLabel label_22 = new JLabel("VND");
		label_22.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_22.setBounds(775, 166, 90, 29);
		confirmwit.add(label_22);
		
		JLabel label_23 = new JLabel("VND");
		label_23.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_23.setBounds(775, 242, 90, 29);
		confirmwit.add(label_23);
		
		JLabel label_24 = new JLabel("");
		label_24.setForeground(Color.RED);
		label_24.setFont(new Font("Times New Roman", Font.BOLD, 34));
		label_24.setBounds(502, 77, 251, 62);
		confirmwit.add(label_24);
		
		JButton button = new JButton("Cancel");
		button.setForeground(SystemColor.textHighlight);
		button.setBackground(SystemColor.controlHighlight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getacctype.executeUpdate();
					getbal.executeUpdate();
					getrate.executeUpdate();
					getrateint = (getrate.getFloat(1) * 100);
					oldball = getbal.getLong(1);
					acctype = getacctype.getString(1);
					label_1.setText(myform.format(oldball));
					label_14.setText(acctype);
					label_16.setText(getrateint.toString());
					confirmwit.setVisible(false);
					leftpaneldepo.setVisible(true);
					rightpaneldepo.setVisible(true);
					depologs.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		button.setBounds(438, 334, 141, 49);
		confirmwit.add(button);
		
		JButton button_1 = new JButton("Confirm");
		button_1.setForeground(SystemColor.textHighlight);
		button_1.setBackground(SystemColor.controlHighlight);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long am = Long.parseLong(amountbox.getText());
				try {
					//am = Long.parseLong(amountbox.getText());
					CallableStatement cSt = MyConnection.getConnection().prepareCall("{call withdraw(?,?,?)}");
					cSt.setString(1, accid);
					cSt.setFloat(2, am);
					cSt.registerOutParameter(3, Types.INTEGER);
					cSt.executeUpdate();
					getacctype.executeUpdate();
					getbal.executeUpdate();
					getrate.executeUpdate();
					getrateint = (getrate.getFloat(1) * 100);
					oldball = getbal.getLong(1);
					acctype = getacctype.getString(1);
					label_1.setText(myform.format(oldball));
					label_14.setText(acctype);
					label_16.setText(getrateint.toString());
					
					if(cSt.getInt(3)==1)
					{
						JOptionPane.showMessageDialog(null, "Withdraw successfully");
						confirmwit.setVisible(false);
						leftpaneldepo.setVisible(true);
						rightpaneldepo.setVisible(true);
						depologs.setVisible(true);
						amountbox.setText("");
						String logquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Transaction Type',amount as 'Amount' from usertranslogview where senderid=? and receiverid=?";
						try {
							PreparedStatement pSt = MyConnection.getConnection().prepareStatement(logquery);
							pSt.setString(1,accid); // BOTH SENDER AND RECEIVERID == accid
							pSt.setString(2,accid);
							ResultSet rs = pSt.executeQuery();
							depositlogtable.setModel(DbUtils.resultSetToTableModel(rs));
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Withdraw failed");
						confirmwit.setVisible(false);
						leftpaneldepo.setVisible(true);
						rightpaneldepo.setVisible(true);
						depologs.setVisible(true);
						amountbox.setText("");
						String logquery = "select DATE_FORMAT(time, '%H:%i %d/%m/%Y') as 'Time',transactiontype as 'Transaction Type',amount as 'Amount' from usertranslogview where senderid=? and receiverid=?";
						try {
							PreparedStatement pSt = MyConnection.getConnection().prepareStatement(logquery);
							pSt.setString(1,accid); // BOTH SENDER AND RECEIVERID == accid
							pSt.setString(2,accid);
							ResultSet rs = pSt.executeQuery();
							depositlogtable.setModel(DbUtils.resultSetToTableModel(rs));
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		button_1.setBounds(702, 334, 141, 49);
		confirmwit.add(button_1);
		final JPanel panel12 = new JPanel();
		
		
		JPanel transfer = new JPanel();
		transfer.setForeground(SystemColor.textHighlight);
		transfer.setBackground(SystemColor.text);
		layeredPane.add(transfer, "name_271777719592500");
		transfer.setLayout(null);
		
		
		
		// DEPOSIT BUTTON
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmdep.setVisible(true);
				leftpaneldepo.setVisible(false);
				rightpaneldepo.setVisible(false);
				depologs.setVisible(false);
				cacba = oldball+Long.parseLong(amountbox.getText());		
				newconfirm.setText(myform.format(cacba));
				Long a = Long.parseLong(amountbox.getText());
				amconfirm.setText(myform.format(a));
				oldconfirm.setText(myform.format(oldball));
			}
		});
		
		// WITHDRAW BUTTON
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long am = Long.parseLong(amountbox.getText());
				Long curbalance = Long.parseLong(label_1.getText().replace(",", ""));
				if(am>=curbalance)
				{
					JPanel panel12 = new JPanel();
					JOptionPane.showMessageDialog(panel12, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
					amountbox.setText("");
					return;
				}
				else
				{
					confirmwit.setVisible(true);
					leftpaneldepo.setVisible(false);
					rightpaneldepo.setVisible(false);
					depologs.setVisible(false);
					cacba = oldball-Long.parseLong(amountbox.getText());		
					label_21.setText(myform.format(cacba));
					Long a = Long.parseLong(amountbox.getText());
					label_24.setText(myform.format(a));
					label_20.setText(myform.format(oldball));
				}
			}
		});
		
		
		
		JScrollPane translogs = new JScrollPane();
		translogs.setBounds(491, 362, 712, 207);
		transfer.add(translogs);
		translogs.getViewport().setBackground(Color.WHITE);

		
		transferlogtable = new JTable();
		transferlogtable.setForeground(SystemColor.textHighlight);
		translogs.setViewportView(transferlogtable);
		transferlogtable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JTableHeader transferlogheader = transferlogtable.getTableHeader();
		transferlogheader.setFont(new Font("Dialog", Font.BOLD, 18));
		transferlogtable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		

	
		
		JPanel leftpaneltrans = new JPanel();
		leftpaneltrans.setBackground(SystemColor.textHighlightText);
		leftpaneltrans.setBounds(0, 0, 491, 569);
		transfer.add(leftpaneltrans);
		leftpaneltrans.setLayout(null);
		
		JLabel label_4 = new JLabel("Balance: ");
		label_4.setForeground(SystemColor.textHighlight);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		label_4.setBounds(28, 90, 148, 40);
		leftpaneltrans.add(label_4);
		
		JLabel label_7 = new JLabel(oldball.toString());
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		label_7.setBounds(82, 143, 273, 70);
		leftpaneltrans.add(label_7);
		
		JLabel label_8 = new JLabel("VND");
		label_8.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		label_8.setBounds(367, 164, 56, 34);
		leftpaneltrans.add(label_8);
		
		JLabel label_9 = new JLabel("Account type: ");
		label_9.setForeground(SystemColor.textHighlight);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		label_9.setBounds(24, 226, 196, 34);
		leftpaneltrans.add(label_9);
		
		JLabel label_10 = new JLabel(acctype);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		label_10.setBounds(130, 298, 226, 40);
		leftpaneltrans.add(label_10);
		
		JLabel label_11 = new JLabel("Interest Rate:");
		label_11.setForeground(SystemColor.textHighlight);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		label_11.setBounds(24, 383, 196, 34);
		leftpaneltrans.add(label_11);
		
		JLabel label_12 = new JLabel(getrateint.toString());
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
		label_12.setBounds(130, 452, 226, 40);
		leftpaneltrans.add(label_12);
		
		
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.setBackground(SystemColor.controlHighlight);
		btnBack_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBack_1.setForeground(SystemColor.textHighlight);
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(main);
				// POPULATE MAIN INFO
				curballmain.setText(myform.format(oldball));
				typemain.setText(acctype);
				ratemain.setText(getrateint.toString());
			}
		});
		btnBack_1.setBounds(24, 516, 93, 40);
		leftpaneltrans.add(btnBack_1);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 39, 491, 2);
		leftpaneltrans.add(separator_6);
		
		JPanel rightpaneltrans = new JPanel();
		rightpaneltrans.setBackground(SystemColor.textHighlightText);
		rightpaneltrans.setBounds(491, 0, 712, 569);
		transfer.add(rightpaneltrans);
		rightpaneltrans.setLayout(null);
		
		JLabel label_3 = new JLabel("Receiver username:");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_3.setBounds(12, 58, 234, 40);
		rightpaneltrans.add(label_3);
		
		unamein = new JTextField();
		unamein.setFont(new Font("Arial Unicode MS", Font.PLAIN, 28));
		unamein.setColumns(10);
		unamein.setBounds(135, 111, 468, 50);
		rightpaneltrans.add(unamein);
		
		JLabel label_19 = new JLabel("Transfer amount: ");
		label_19.setForeground(SystemColor.textHighlight);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_19.setBounds(14, 174, 215, 40);
		rightpaneltrans.add(label_19);
		
		amountin = new JTextField();
		amountin.setFont(new Font("Arial Unicode MS", Font.PLAIN, 28));
		amountin.setColumns(10);
		amountin.setBounds(135, 227, 468, 50);
		rightpaneltrans.add(amountin);
		
		
		
		JLabel label_25 = new JLabel("TRANSFER");
		label_25.setForeground(SystemColor.textHighlight);
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setFont(new Font("Times New Roman", Font.BOLD, 38));
		label_25.setBounds(245, 13, 227, 50);
		rightpaneltrans.add(label_25);
		//confirmdep.setVisible(false);
		
		// TRANSFER MAIN SWITCH
		transferbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					label_7.setText(myform.format(oldball));
					label_10.setText(acctype);
					label_12.setText(getrateint.toString());
					translogre = gettranslog.executeQuery();
					transferlogtable.setModel(DbUtils.resultSetToTableModel(translogre));
					translogs.setVisible(true);
					TableColumn column = transferlogtable.getColumnModel().getColumn(0);
					column.setPreferredWidth(120);
					column = transferlogtable.getColumnModel().getColumn(1);
					column.setPreferredWidth(20);
					switchPanels(transfer);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
			
			JPanel confirmtrans = new JPanel();
			confirmtrans.setBounds(491, 0, 712, 569);
			transfer.add(confirmtrans);
			confirmtrans.setBackground(SystemColor.text);
			confirmtrans.setLayout(null);
			// SET VISIBLE FALSE FOR HIDING
			confirmtrans.setVisible(false);
			
			JLabel label_26 = new JLabel("Confirm your transfer");
			label_26.setBackground(SystemColor.control);
			label_26.setForeground(SystemColor.textText);
			label_26.setHorizontalAlignment(SwingConstants.CENTER);
			label_26.setFont(new Font("Century Gothic", Font.BOLD, 30));
			label_26.setBounds(0, 13, 713, 95);
			confirmtrans.add(label_26);
			
			JLabel label_27 = new JLabel("To username: ");
			label_27.setForeground(SystemColor.textText);
			label_27.setFont(new Font("Calisto MT", Font.PLAIN, 25));
			label_27.setBounds(82, 143, 189, 55);
			confirmtrans.add(label_27);
			
			JLabel label_28 = new JLabel("Amount:");
			label_28.setForeground(SystemColor.textText);
			label_28.setFont(new Font("Calisto MT", Font.PLAIN, 25));
			label_28.setBounds(129, 220, 132, 55);
			confirmtrans.add(label_28);
			
			JLabel amounttrans = new JLabel("New label");
			amounttrans.setHorizontalAlignment(SwingConstants.CENTER);
			amounttrans.setForeground(Color.RED);
			amounttrans.setFont(new Font("Tahoma", Font.PLAIN, 22));
			amounttrans.setBounds(291, 228, 295, 39);
			confirmtrans.add(amounttrans);
			
			JButton unametrans = new JButton("");
			unametrans.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			unametrans.setForeground(SystemColor.textHighlight);
			unametrans.setFont(new Font("Tahoma", Font.PLAIN, 22));
			unametrans.setBackground(SystemColor.menu);
			unametrans.setBounds(291, 143, 295, 45);
			confirmtrans.add(unametrans);
			
			
			
			
			
			JLabel lblNewBalance = new JLabel("New balance:");
			lblNewBalance.setForeground(Color.BLACK);
			lblNewBalance.setFont(new Font("Calisto MT", Font.PLAIN, 25));
			lblNewBalance.setBounds(92, 309, 169, 55);
			confirmtrans.add(lblNewBalance);
			
			JLabel newballtrans = new JLabel("New label");
			newballtrans.setHorizontalAlignment(SwingConstants.CENTER);
			newballtrans.setForeground(Color.RED);
			newballtrans.setFont(new Font("Tahoma", Font.PLAIN, 22));
			newballtrans.setBounds(291, 317, 295, 39);
			confirmtrans.add(newballtrans);
			
			JSeparator separator = new JSeparator();
			separator.setBackground(SystemColor.controlText);
			separator.setForeground(new Color(160, 160, 160));
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(0, 0, 11, 569);
			confirmtrans.add(separator);
			
			JButton canceltrans = new JButton("Cancel");
			canceltrans.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmtrans.setVisible(false);
					rightpaneltrans.setVisible(true);
					translogs.setVisible(true);
				}
			});
			canceltrans.setForeground(SystemColor.textHighlight);
			canceltrans.setFont(new Font("Times New Roman", Font.BOLD, 20));
			canceltrans.setBackground(SystemColor.controlHighlight);
			canceltrans.setBounds(291, 416, 116, 39);
			confirmtrans.add(canceltrans);
			JButton transbutton = new JButton("Confirm");
			
			transbutton.setForeground(SystemColor.textHighlight);
			transbutton.setFont(new Font("Times New Roman", Font.BOLD, 20));
			transbutton.setBackground(SystemColor.controlHighlight);
			transbutton.setBounds(470, 416, 116, 39);
			confirmtrans.add(transbutton);
			
			
			JButton button_2 = new JButton("Transfer");
			button_2.setForeground(SystemColor.textHighlight);
			button_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
			button_2.setBackground(SystemColor.controlHighlight);
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CallableStatement getreceiverid;
					try {
						getreceiverid = MyConnection.getConnection().prepareCall("{?=call get_accid(?)}");
						getreceiverid.registerOutParameter(1, Types.VARCHAR);
						getreceiverid.setString(2, unamein.getText());
						getreceiverid.execute();
						receiverid = getreceiverid.getString(1);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					// CHECK USER LEGIT FIRST
					String unamein1 = unamein.getText();
					Long am;
					try {
						
						am = Long.parseLong(amountin.getText());
					}
					catch(NumberFormatException e1)
					{
						JPanel panel12 = new JPanel();
						JOptionPane.showMessageDialog(panel12, "Wrong amount format", "Error", JOptionPane.ERROR_MESSAGE);
						amountin.setText("");
						return;
					}
					if(unamein1.length()>=50 || unamein1.equals("") || unamein1.equals(null))
					{
						JPanel panel12 = new JPanel();
						JOptionPane.showMessageDialog(panel12, "Wrong username format", "Error", JOptionPane.ERROR_MESSAGE);
						unamein.setText("");
						return;
					}
					if(am<0 || am>=oldball)
					{
						JPanel panel12 = new JPanel();
						JOptionPane.showMessageDialog(panel12, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
						amountin.setText("");
						return;
					}
					// CHECK IF ACC EXISTS IN DB
					PreparedStatement checkacc;
					try {
						checkacc = MyConnection.getConnection().prepareStatement("select accountid from account where username=?");
						checkacc.setString(1, unamein1);
						ResultSet checkrs=checkacc.executeQuery();
						// ==0 MEAN DOESNT EXIST
						if(checkrs.next()==false)
						{
							JPanel panel12 = new JPanel();
							JOptionPane.showMessageDialog(panel12, "Invalid account", "Error", JOptionPane.ERROR_MESSAGE);
							unamein.setText("");
							return;
						}
						else
						{
							{
								unametrans.setText(unamein1);
								amounttrans.setText(myform.format(am));
								confirmtrans.setVisible(true);
								rightpaneltrans.setVisible(false);
								translogs.setVisible(false);
								Long newwtrans = oldball-am;
								newballtrans.setText(myform.format(newwtrans));
							}
						}
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			button_2.setBounds(262, 290, 198, 59);
			rightpaneltrans.add(button_2);
			
			JSeparator separator_5 = new JSeparator();
			separator_5.setBounds(0, 39, 257, 2);
			rightpaneltrans.add(separator_5);
			
			JPanel logs = new JPanel();
			logs.setBackground(SystemColor.textHighlightText);
			layeredPane.add(logs, "name_223599480361500");
			logs.setLayout(null);
			logs.add(getBtnNewButton_1());
			logs.add(getScrollPane());
			
			JButton logbutton = new JButton("HISTORY");
			logbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanels(logs);
					String logsquery = "select time as 'Time',transactiontype as 'Type',receiverid as 'From/To',amount as 'Amount',oldbalance as 'Current Balance',newbalance as 'New Balance' from userlogs where senderid=?";
					PreparedStatement get_logs;
					ResultSet logrs=null;
					try {
						get_logs = MyConnection.getConnection().prepareStatement(logsquery);
						get_logs.setString(1, accid);
						logrs=get_logs.executeQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table.setModel(DbUtils.resultSetToTableModel(logrs));
					JTableHeader depositlogheader = table.getTableHeader();
					depositlogheader.setFont(new Font("Dialog", Font.BOLD, 25));
				}
			});
			logbutton.setForeground(SystemColor.textHighlight);
			logbutton.setBackground(SystemColor.controlHighlight);
			logbutton.setFont(new Font("Times New Roman", Font.BOLD, 25));
			logbutton.setBounds(731, 303, 345, 45);
			main.add(logbutton);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(601, 0, 2, 569);
			main.add(separator_2);
			
			transbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String transstring = "call transfer(?,?,?,?)";
					// CONFIRM TRANSFER BUTTON
					try {
						Integer result = 0;
						Long amount = Long.parseLong(amounttrans.getText().replace(",", ""));
						CallableStatement trans = MyConnection.getConnection().prepareCall(transstring);
						trans.setString(1, accid);
						trans.setString(2, receiverid);
						trans.setLong(3, amount);
						trans.setInt(4, result);
						trans.execute();
						if(trans.getInt(4)==1)
						{
							getbal.executeUpdate();
							oldball=getbal.getLong(1);
							getacctype.executeUpdate();
							acctype=getacctype.getString(1);
							getrate.executeUpdate();
							getrateint = (getrate.getFloat(1) * 100);
							JOptionPane.showMessageDialog(null, "Transfer successfully");
							// 7 10 12 label
							label_7.setText(myform.format(oldball));
							label_10.setText(acctype);
							label_12.setText(getrateint.toString());
							translogre = gettranslog.executeQuery();
							transferlogtable.setModel(DbUtils.resultSetToTableModel(translogre));
							// turn back to trans main menu
							rightpaneltrans.setVisible(true);
							confirmtrans.setVisible(false);
							translogs.setVisible(true);
							//PreparedStatement = MyConnection.getConnection().prepareStatement(sql)
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		
		
		
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("BACK");
			btnNewButton_1.setBackground(SystemColor.controlHighlight);
			btnNewButton_1.setForeground(SystemColor.textHighlight);
			btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switchPanels(main);
				}
			});
			btnNewButton_1.setBounds(22, 508, 93, 40);
		}
		return btnNewButton_1;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setForeground(SystemColor.textHighlight);
			table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return table;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 13, 1179, 482);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
}

