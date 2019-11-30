package frontend;

import java.awt.BorderLayout;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import backend.MyConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

public class AdminForm extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel search;
	private JPanel setting;
	private JPanel create;
	private JLayeredPane layeredPane_1;
	private JTable pertable;
	private JScrollPane scrollPane;
	private JPanel persearch;
	private JPanel accsearch;
	private JPanel transsearch;
	private JButton invisiblebutton;
	private JTextField pertextfield;
	private JTextField acctextfield;
	private JTextField transtextfield;
	private JToggleButton tglbtnNewToggleButton;
	private JToggleButton tglbtnNewToggleButton_1;
	private JToggleButton tglbtnNewToggleButton_2;
	private JTable acctable;
	private JScrollPane scrollPane_1;
	private JTable transtable;
	private JScrollPane scrollPane_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JToggleButton tglbtnNewToggleButton_3;
	private JToggleButton tglbtnCreate;
	private JToggleButton tglbtnSetting;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblNewLabel;
	private JTextField fnamein;
	private JLabel lblNewLabel_1;
	private JTextField cmndin;
	private JLabel lblNewLabel_2;
	private JTextField emailin;
	private JLabel lblNewLabel_3;
	private JTextField birthdayin;
	private JLabel lblNewLabel_5;
	private JTextField phone1in;
	private JLabel lblNewLabel_6;
	private JTextField phone2in;
	private JTextField addr1in;
	private JTextField addr2in;
	private JTextField zipin;
	private JLabel lblZipCode;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JTextArea workplacein;
	private JLabel lblNewLabel_9;
	private JTextField payratein;
	private JButton btnNewButton;
	private JButton btnCancel;
	private JTextField unamein;
	private JTextField pswin;
	private JTextField balancein;
	private JPanel empsearch;
	private JTable emptable;
	private JScrollPane scrollPane_3;
	private JToggleButton tglbtnEmployee;
	private JTextField emptextfield;
	
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForm frame = new AdminForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public void switchPanels(JLayeredPane kenn,JPanel abc)
	{
		kenn.removeAll();
		kenn.add(abc);
		kenn.repaint();
		kenn.revalidate();
	}
	


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminForm(String accid) throws SQLException {
		CallableStatement cSt = null;
		PreparedStatement pSt = null;
		ResultSet rs = null;
		
		// get person name for display
		cSt = MyConnection.getConnection().prepareCall("{?=call get_name(?)}");
		cSt.registerOutParameter(1, Types.VARCHAR);
		cSt.setString(2, accid);
		cSt.executeUpdate();
		String name = cSt.getString(1);
		cSt.close();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEbankingSystem = new JLabel("E-Banking System");
		lblEbankingSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblEbankingSystem.setBounds(537, 13, 137, 23);
		contentPane.add(lblEbankingSystem);
		
		
		JLabel lblSysdba = new JLabel("SYSDBA");
		lblSysdba.setBounds(1027, 41, 59, 23);
		contentPane.add(lblSysdba);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		JButton profilebutton = new JButton(name);
		profilebutton.setBorder(emptyBorder);
		profilebutton.setBackground(SystemColor.menu);
		profilebutton.setBounds(975, 62, 157, 25);
		contentPane.add(profilebutton);
		contentPane.add(getLayeredPane_1());
		// HERE
		JPopupMenu popupMenu = new JPopupMenu();
	    JMenuItem editItem = new JMenuItem("Edit");
	    JMenuItem editItem1 = new JMenuItem("Remove");
	    JMenuItem seeaccper = new JMenuItem("List account");
	    popupMenu.add(editItem);
	    popupMenu.add(editItem1);
	    popupMenu.add(seeaccper);
	    pertable.setComponentPopupMenu(popupMenu);
	    seeaccper.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = pertable.getSelectedRow();
		    	   String perid =(String) pertable.getModel().getValueAt(row, 0);
		    	   try {
					AccList acclist = new AccList(perid,invisiblebutton);
					acclist.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	   
		       }
		    });
	    editItem.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   int row = pertable.getSelectedRow();
	    	   String perid =(String) pertable.getModel().getValueAt( row,0);
	    	   try {
				PerEdit editframe = new PerEdit(perid,invisiblebutton);
				editframe.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	   
	       }
	    });
	    editItem1.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = pertable.getSelectedRow();
		    	   String perid =(String) pertable.getModel().getValueAt(row, 0);
		    	   PerDelConfirm confirmframe;
				try {
					confirmframe = new PerDelConfirm(perid);
					confirmframe.setVisible(true);
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       }
		 });
	    
	    JPopupMenu popupMenuacc = new JPopupMenu();
	    JMenuItem editacc = new JMenuItem("Edit");
	    JMenuItem delacc = new JMenuItem("Remove");
	    JMenuItem seeowner = new JMenuItem("Owner info");
	    popupMenuacc.add(seeowner);
	    popupMenuacc.add(editacc);
	    popupMenuacc.add(delacc);
	    acctable.setComponentPopupMenu(popupMenuacc);
	    seeowner.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = acctable.getSelectedRow();
		    	   String perid =(String) acctable.getModel().getValueAt(row, 5);
		    	   try {
					PerEdit editframe = new PerEdit(perid,invisiblebutton);
					editframe.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	   
		       }
		    });
	    editacc.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = acctable.getSelectedRow();
		    	   System.out.print(row);
		    	   String accid =(String) acctable.getModel().getValueAt(row, 0);
		    	   try {
					AccEdit editframe = new AccEdit(accid,invisiblebutton);
					editframe.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	   
		       }
		    });
	    delacc.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = acctable.getSelectedRow();
		    	   String accid =(String) acctable.getModel().getValueAt(row, 0);
		    	   try {
		    		   DelAcc editframe = new DelAcc(accid,invisiblebutton);
		    		   editframe.setVisible(true);
		    	   } 	
		    	   catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       }
		    });
	    // EMPLOYEE POPUP
	    JPopupMenu emppop = new JPopupMenu();
	    JMenuItem empfire = new JMenuItem("Fire");
	    JMenuItem empcheckinfo = new JMenuItem("Personal information");
	    emppop.add(empfire);
	    emppop.add(empcheckinfo);
	    emptable.setComponentPopupMenu(emppop);
	    empcheckinfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = emptable.getSelectedRow();
				String perid = (String) emptable.getModel().getValueAt(row, 1);
				try {
					PerEdit peredit = new PerEdit(perid,invisiblebutton);
					peredit.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });
	    empfire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = emptable.getSelectedRow();
				String empid = (String) emptable.getModel().getValueAt(row, 0);
				EmpFire fireframe;
				try {
					fireframe = new EmpFire(empid,invisiblebutton);
					fireframe.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });
	    
	    
	   
	    contentPane.add(getTglbtnNewToggleButton_3());
	    contentPane.add(getTglbtnCreate());
	    contentPane.add(getTglbtnSetting());
	   
	    
	}
	   
	
	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setBounds(0, 136, 1179, 567);
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getSearch(), "name_135157941738900");
			layeredPane.add(getSetting(), "name_135166738695800");
			layeredPane.add(getCreate(), "name_135173080763400");
		}
		return layeredPane;
	}
	private JPanel getSearch() {
		if (search == null) {
			search = new JPanel();
			search.setLayout(null);
			search.add(getLayeredPane_1_1());
			search.add(getTglbtnNewToggleButton());
			search.add(getTglbtnNewToggleButton_1());
			search.add(getTglbtnNewToggleButton_2());
			search.add(getTglbtnEmployee());
		}
		return search;
	}
	private JPanel getSetting() {
		if (setting == null) {
			setting = new JPanel();
		}
		return setting;
	}
	private JPanel getCreate() {
		if (create == null) {
			create = new JPanel();
			create.setLayout(null);
			create.add(getLblNewLabel());
			create.add(getFnamein());
			create.add(getLblNewLabel_1());
			create.add(getCmndin());
			create.add(getLblNewLabel_2());
			create.add(getEmailin());
			create.add(getLblNewLabel_3());
			create.add(getBirthdayin());
			create.add(getLblNewLabel_5());
			create.add(getPhone1in());
			create.add(getLblNewLabel_6());
			create.add(getPhone2in());
			create.add(getAddr1in());
			create.add(getAddr2in());
			create.add(getZipin());
			create.add(getLblZipCode());
			create.add(getLblUsername());
			create.add(getLblPassword());
			create.add(getLblNewLabel_7());
			create.add(getLblNewLabel_8());
			create.add(getWorkplacein());
			create.add(getLblNewLabel_9());
			create.add(getPayratein());
			create.add(getBtnNewButton());
			create.add(getBtnCancel());
			create.add(getTextField_3_1());
			create.add(getTextField_4_1());
			create.add(getTextField_5_1());
		}
		return create;
	}
	private JLayeredPane getLayeredPane_1_1() {
		if (layeredPane_1 == null) {
			layeredPane_1 = new JLayeredPane();
			layeredPane_1.setBounds(0, 30, 1179, 537);
			layeredPane_1.setLayout(new CardLayout(0, 0));
			layeredPane_1.add(getPersearch(), "name_137424923353700");
			layeredPane_1.add(getAccsearch(), "name_137426725368000");
			layeredPane_1.add(getTranssearch(), "name_137428356001300");
			layeredPane_1.add(getEmpsearch(), "name_9313398107600");
		}
		return layeredPane_1;
	}
	private ResultSet get_person() throws SQLException
	{
		PreparedStatement a = MyConnection.getConnection().prepareStatement("select * from person");
		ResultSet rs = a.executeQuery();
		return rs;
	}
	private ResultSet get_account() throws SQLException
	{
		PreparedStatement a = MyConnection.getConnection().prepareStatement("select * from account");
		ResultSet rs = a.executeQuery();
		return rs;
	}
	private ResultSet get_transaction() throws SQLException
	{
		PreparedStatement a = MyConnection.getConnection().prepareStatement("select * from transactionlog");
		ResultSet rs = a.executeQuery();
		return rs;
	}
	private ResultSet get_employee() throws SQLException
	{
		PreparedStatement a = MyConnection.getConnection().prepareStatement("select * from employee");
		ResultSet rs = a.executeQuery();
		return rs;
		
	}
	private JTable getPertable() {
		if (pertable == null) {
			pertable = new JTable();
		}
		return pertable;
	}
	
	
	
	

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 1179, 450);
			scrollPane.setViewportView(getPertable());
		}
		return scrollPane;
	}
	private JPanel getPersearch() {
		if (persearch == null) {
			persearch = new JPanel();
			persearch.setLayout(null);
			persearch.add(getScrollPane());
			persearch.add(getInvisiblebutton());
			persearch.add(getPertextfield());
		}
		return persearch;
	}
	private JPanel getAccsearch() {
		if (accsearch == null) {
			accsearch = new JPanel();
			accsearch.setLayout(null);
			accsearch.add(getAcctextfield());
			accsearch.add(getScrollPane_1());
		}
		return accsearch;
	}
	private JPanel getTranssearch() {
		if (transsearch == null) {
			transsearch = new JPanel();
			transsearch.setLayout(null);
			transsearch.add(getTranstextfield());
			transsearch.add(getScrollPane_2());
		}
		return transsearch;
	}
	private JButton getInvisiblebutton() {
		if (invisiblebutton == null) {
			invisiblebutton = new JButton("New button");
			invisiblebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						refreshtables();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			invisiblebutton.setBounds(33, 499, 121, 25);
			invisiblebutton.setVisible(false);
		}
		return invisiblebutton;
	}
	private JTextField getPertextfield() {
		if (pertextfield == null) {
			pertextfield = new JTextField();
			pertextfield.setBounds(185, 490, 161, 34);
			pertextfield.setColumns(10);
			RowFilterUtil.createRowFilter(pertextfield,getPertable());
		}
		return pertextfield;
	}
	private JTextField getAcctextfield() {
		if (acctextfield == null) {
			acctextfield = new JTextField();
			acctextfield.setBounds(39, 488, 165, 36);
			acctextfield.setColumns(10);
		}
		return acctextfield;
	}
	private JTextField getTranstextfield() {
		if (transtextfield == null) {
			transtextfield = new JTextField();
			transtextfield.setBounds(35, 502, 116, 22);
			transtextfield.setColumns(10);
		}
		return transtextfield;
	}
	// SEARCH BUTTONS ELEMENT
	private JToggleButton getTglbtnNewToggleButton() {
		if (tglbtnNewToggleButton == null) {
			tglbtnNewToggleButton = new JToggleButton("Person");
			buttonGroup.add(tglbtnNewToggleButton);
			tglbtnNewToggleButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switchPanels(layeredPane_1,persearch);
					try {
						pertable.setModel(DbUtils.resultSetToTableModel(get_person()));
					    RowFilterUtil.createRowFilter(pertextfield,pertable);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			tglbtnNewToggleButton.setBounds(0, 0, 137, 25);
		}
		return tglbtnNewToggleButton;
	}
	private JToggleButton getTglbtnNewToggleButton_1() {
		if (tglbtnNewToggleButton_1 == null) {
			tglbtnNewToggleButton_1 = new JToggleButton("Account");
			buttonGroup.add(tglbtnNewToggleButton_1);
			tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanels(layeredPane_1,accsearch);
					try {
						acctable.setModel(DbUtils.resultSetToTableModel(get_account()));
					    RowFilterUtil.createRowFilter(acctextfield,acctable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			tglbtnNewToggleButton_1.setBounds(137, 0, 137, 25);
		}
		return tglbtnNewToggleButton_1;
	}
	private JToggleButton getTglbtnNewToggleButton_2() {
		if (tglbtnNewToggleButton_2 == null) {
			tglbtnNewToggleButton_2 = new JToggleButton("Transaction");
			buttonGroup.add(tglbtnNewToggleButton_2);
			tglbtnNewToggleButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanels(layeredPane_1,transsearch);
					try {
						transtable.setModel(DbUtils.resultSetToTableModel(get_transaction()));
					    RowFilterUtil.createRowFilter(transtextfield,transtable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			tglbtnNewToggleButton_2.setBounds(274, 0, 137, 25);
		}
		return tglbtnNewToggleButton_2;
	}
	private void refreshtables() throws SQLException
	{
		System.out.print("yes");
		transtable.setModel(DbUtils.resultSetToTableModel(get_transaction()));
	    RowFilterUtil.createRowFilter(transtextfield,transtable);
	    acctable.setModel(DbUtils.resultSetToTableModel(get_account()));
	    RowFilterUtil.createRowFilter(acctextfield,acctable);
	    pertable.setModel(DbUtils.resultSetToTableModel(get_person()));
	    RowFilterUtil.createRowFilter(pertextfield,pertable);
	    pertable.setModel(DbUtils.resultSetToTableModel(get_person()));
	    RowFilterUtil.createRowFilter(pertextfield,pertable);
	    transtable.repaint();
	    emptable.repaint();
	    pertable.repaint();
	    acctable.repaint();

	}
	private JTable getAcctable() {
		if (acctable == null) {
			acctable = new JTable();
		}
		return acctable;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(0, 0, 1179, 450);
			scrollPane_1.setViewportView(getAcctable());
		}
		return scrollPane_1;
	}
	private JTable getTranstable() {
		if (transtable == null) {
			transtable = new JTable();
		}
		return transtable;
	}
	private JScrollPane getScrollPane_2() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(0, 0, 1179, 450);
			scrollPane_2.setViewportView(getTranstable());
		}
		return scrollPane_2;
	}
	private JToggleButton getTglbtnNewToggleButton_3() {
		if (tglbtnNewToggleButton_3 == null) {
			tglbtnNewToggleButton_3 = new JToggleButton("Search");
			tglbtnNewToggleButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanels(layeredPane,search);
				}
			});
			buttonGroup_1.add(tglbtnNewToggleButton_3);
			tglbtnNewToggleButton_3.setBounds(12, 110, 137, 25);
		}
		return tglbtnNewToggleButton_3;
	}
	private JToggleButton getTglbtnCreate() {
		if (tglbtnCreate == null) {
			tglbtnCreate = new JToggleButton("Create");
			tglbtnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanels(layeredPane,create);
				}
			});
			buttonGroup_1.add(tglbtnCreate);
			tglbtnCreate.setBounds(154, 110, 137, 25);
		}
		return tglbtnCreate;
	}
	private JToggleButton getTglbtnSetting() {
		if (tglbtnSetting == null) {
			tglbtnSetting = new JToggleButton("Setting");
			tglbtnSetting.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanels(layeredPane,setting);
				}
			});
			buttonGroup_1.add(tglbtnSetting);
			tglbtnSetting.setBounds(303, 110, 137, 25);
		}
		return tglbtnSetting;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Full name");
			lblNewLabel.setBounds(12, 13, 56, 16);
		}
		return lblNewLabel;
	}
	private JTextField getFnamein() {
		if (fnamein == null) {
			fnamein = new JTextField();
			fnamein.setBounds(94, 10, 116, 22);
			fnamein.setColumns(10);
		}
		return fnamein;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Identity");
			lblNewLabel_1.setBounds(12, 66, 56, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getCmndin() {
		if (cmndin == null) {
			cmndin = new JTextField();
			cmndin.setBounds(94, 63, 116, 22);
			cmndin.setColumns(10);
		}
		return cmndin;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Email");
			lblNewLabel_2.setBounds(12, 127, 56, 16);
		}
		return lblNewLabel_2;
	}
	private JTextField getEmailin() {
		if (emailin == null) {
			emailin = new JTextField();
			emailin.setBounds(94, 124, 116, 22);
			emailin.setColumns(10);
		}
		return emailin;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Birthday");
			lblNewLabel_3.setBounds(12, 177, 56, 16);
		}
		return lblNewLabel_3;
	}
	private JTextField getBirthdayin() {
		if (birthdayin == null) {
			birthdayin = new JTextField();
			birthdayin.setBounds(94, 174, 116, 22);
			birthdayin.setColumns(10);
		}
		return birthdayin;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Phone numbers");
			lblNewLabel_5.setBounds(23, 273, 56, 16);
		}
		return lblNewLabel_5;
	}
	private JTextField getPhone1in() {
		if (phone1in == null) {
			phone1in = new JTextField();
			phone1in.setBounds(112, 270, 116, 22);
			phone1in.setColumns(10);
		}
		return phone1in;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("Addresses");
			lblNewLabel_6.setBounds(12, 361, 56, 16);
		}
		return lblNewLabel_6;
	}
	private JTextField getPhone2in() {
		if (phone2in == null) {
			phone2in = new JTextField();
			phone2in.setBounds(112, 318, 116, 22);
			phone2in.setColumns(10);
		}
		return phone2in;
	}
	private JTextField getAddr1in() {
		if (addr1in == null) {
			addr1in = new JTextField();
			addr1in.setBounds(112, 358, 116, 22);
			addr1in.setColumns(10);
		}
		return addr1in;
	}
	private JTextField getAddr2in() {
		if (addr2in == null) {
			addr2in = new JTextField();
			addr2in.setBounds(94, 411, 116, 22);
			addr2in.setColumns(10);
		}
		return addr2in;
	}
	private JTextField getZipin() {
		if (zipin == null) {
			zipin = new JTextField();
			zipin.setBounds(94, 465, 116, 22);
			zipin.setColumns(10);
		}
		return zipin;
	}
	private JLabel getLblZipCode() {
		if (lblZipCode == null) {
			lblZipCode = new JLabel("ZIP code");
			lblZipCode.setBounds(12, 468, 56, 16);
		}
		return lblZipCode;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username");
			lblUsername.setBounds(380, 13, 56, 16);
		}
		return lblUsername;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setBounds(380, 66, 56, 16);
		}
		return lblPassword;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("Starting balance");
			lblNewLabel_7.setBounds(380, 127, 56, 16);
		}
		return lblNewLabel_7;
	}
	private JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("Workplace");
			lblNewLabel_8.setBounds(665, 13, 101, 16);
		}
		return lblNewLabel_8;
	}
	private JTextArea getWorkplacein() {
		if (workplacein == null) {
			workplacein = new JTextArea();
			workplacein.setBounds(755, 42, 137, 22);
		}
		return workplacein;
	}
	private JLabel getLblNewLabel_9() {
		if (lblNewLabel_9 == null) {
			lblNewLabel_9 = new JLabel("Payrate");
			lblNewLabel_9.setBounds(665, 86, 56, 16);
		}
		return lblNewLabel_9;
	}
	private JTextField getPayratein() {
		if (payratein == null) {
			payratein = new JTextField();
			payratein.setBounds(753, 124, 116, 22);
			payratein.setColumns(10);
		}
		return payratein;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Create");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String fname10=getFnamein().getText();
					String cmnd10 =getCmndin().getText();
					String email10 =getEmailin().getText();
					String phone1in = getPhone1in().getText();
					String phone2in = getPhone2in().getText();
					String addr1in = getAddr1in().getText();
					String addr2in = getAddr2in().getText();
					String zipin = getZipin().getText();
					String bd10 = getBirthdayin().getText();
					if(bd10.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill in all fields");
						return;
					}
					String unamein10 = getTextField_3_1().getText();
					String pswin10 = getTextField_4_1().getText();
					
					
					String workplacein1 = getWorkplacein().getText();
					// INPUT CHECK
					if(fname10.equals("")||cmnd10.equals("")||email10.equals("")||phone1in.equals("")||addr1in.equals("")||zipin.equals("")||bd10.equals("")||unamein10.equals("")||pswin10.equals("")||getTextField_5_1().getText().equals("")||getPayratein().getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill in all fields");
						return;
					}
					Long balancein = Long.parseLong(getTextField_5_1().getText());
					Float payratein1 = Float.parseFloat(getPayratein().getText());
					
					
					try {
						
						// CREATE PERSON
						String addperquery = "call add_person(?,?,?,?,?,?,?,?)";
						CallableStatement add_per = MyConnection.getConnection().prepareCall(addperquery);
						add_per.setString(1, fname10);
						add_per.setString(2, cmnd10);
						add_per.setString(3, bd10 );
						// creating employee => 'E'
						add_per.setString(4, "E");
						add_per.setString(5, phone1in);
						add_per.setString(6, addr1in);
						add_per.setString(7, email10);
						add_per.setString(8, zipin);
						add_per.execute();
						// GET THE PERSONID
						String getperidquery = "select personid from person where identity=?";
						PreparedStatement getperid = MyConnection.getConnection().prepareStatement(getperidquery);
						getperid.setString(1,cmnd10);
						ResultSet perid = getperid.executeQuery();
						perid.next();
						String peridadded = perid.getString("personid");
						if(addr2in!="" || addr2in!=null)
						{
							PreparedStatement bosung = MyConnection.getConnection().prepareStatement("update person set addr2=? where personid=?");
							bosung.setString(1, addr2in);
							bosung.setString(2, peridadded);
							bosung.execute();
						}
						if(phone2in!=""||phone2in!=null)
						{
							PreparedStatement bosung = MyConnection.getConnection().prepareStatement("update person set phone2=? where personid=?");
							bosung.setString(1, phone2in);
							bosung.setString(2, peridadded);
							bosung.execute();
						}
						// CHECK UNAME EXIST
						// CHECK IF ACC EXISTS IN DB
						PreparedStatement checkacc;
						
							checkacc = MyConnection.getConnection().prepareStatement("select accountid from account where username=?");
							checkacc.setString(1, unamein10);
							ResultSet checkrs=checkacc.executeQuery();
							// ==0 MEAN DOESNT EXIST
							if(checkrs.next()==true)
							{
								JPanel panel12 = new JPanel();
								JOptionPane.showMessageDialog(panel12, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
								getTextField_3_1().setText("");						 		
								return;
							}
						// CREATE ACCOUNT
						String addaccquery = "call add_account_balance(?,?,?,?)";
						PreparedStatement addacc = MyConnection.getConnection().prepareCall(addaccquery);
						addacc.setString(1, unamein10);
						addacc.setString(2, pswin10);
						addacc.setString(3, peridadded);
						addacc.setLong(4, balancein);
						addacc.execute();
						// GET THE ACCOUNTID
						PreparedStatement getaccid = MyConnection.getConnection().prepareStatement("select accountid from account where username=?");
						getaccid.setString(1, unamein10);
						ResultSet accid = getaccid.executeQuery();
						accid.next();
						String accidadded = accid.getString("accountid");
						// CREATE EMPLOY
						String empquery = "call add_employ(?,?,?)";
						CallableStatement add_emp = MyConnection.getConnection().prepareCall(empquery);
						add_emp.setString(1,peridadded);
						add_emp.setString(2, workplacein1);
						add_emp.setFloat(3, payratein1);
						add_emp.execute();
						JOptionPane.showMessageDialog(null, "Employee created");
						getFnamein().setText("");
						getCmndin().setText("");
						getEmailin().setText("");
						getPhone1in().setText("");
						getPhone2in().setText("");
						getAddr1in().setText("");
						getAddr2in().setText("");
						getZipin().setText("");
						getBirthdayin().setText("");
						getTextField_3_1().setText("");
						getTextField_4_1().setText("");
						getTextField_5_1().setText("");
						getWorkplacein().setText("");
						getPayratein().setText("");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
			btnNewButton.setBounds(1025, 529, 97, 25);
		}
		return btnNewButton;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(12, 529, 97, 25);
		}
		return btnCancel;
	}
	private JTextField getTextField_3_1() {
		if (unamein == null) {
			unamein = new JTextField();
			unamein.setBounds(468, 10, 116, 22);
			unamein.setColumns(10);
		}
		return unamein;
	}
	private JTextField getTextField_4_1() {
		if (pswin == null) {
			pswin = new JTextField();
			pswin.setBounds(468, 63, 116, 22);
			pswin.setColumns(10);
		}
		return pswin;
	}
	private JTextField getTextField_5_1() {
		if (balancein == null) {
			balancein = new JTextField();
			balancein.setBounds(468, 124, 116, 22);
			balancein.setColumns(10);
		}
		return balancein;
	}
	private JPanel getEmpsearch() {
		if (empsearch == null) {
			empsearch = new JPanel();
			empsearch.setLayout(null);
			empsearch.add(getScrollPane_3());
			empsearch.add(getEmptextfield());
		}
		return empsearch;
	}
	private JTable getEmptable() {
		if (emptable == null) {
			emptable = new JTable();
		}
		return emptable;
	}
	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(0, 0, 1179, 450);
			scrollPane_3.setViewportView(getEmptable());
		}
		return scrollPane_3;
	}
	private JToggleButton getTglbtnEmployee() {
		if (tglbtnEmployee == null) {
			tglbtnEmployee = new JToggleButton("Employee");
			tglbtnEmployee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					switchPanels(layeredPane_1,empsearch);
					try {
						emptable.setModel(DbUtils.resultSetToTableModel(get_employee()));
					    RowFilterUtil.createRowFilter(emptextfield,emptable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonGroup.add(tglbtnEmployee);
			tglbtnEmployee.setBounds(413, 0, 137, 25);
		}
		return tglbtnEmployee;
	}
	private JTextField getEmptextfield() {
		if (emptextfield == null) {
			emptextfield = new JTextField();
			emptextfield.setBounds(47, 485, 116, 22);
			emptextfield.setColumns(10);
		}
		return emptextfield;
	}
}
