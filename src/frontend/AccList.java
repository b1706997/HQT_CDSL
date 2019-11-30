package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import backend.MyConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

public class AccList extends JFrame {

	private JPanel contentPane;
	private JLabel lblAccountListOf;
	private JLabel ownername;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AccList(String perid,JButton invi) throws SQLException {
		System.out.print(perid);
		PreparedStatement a = MyConnection.getConnection().prepareStatement("select * from account where personid=?");
		a.setString(1, perid);
		ResultSet rs = a.executeQuery();
		getTable().setModel(DbUtils.resultSetToTableModel(rs));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1120, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAccountListOf());
		contentPane.add(getOwnername());
		contentPane.add(getScrollPane());
		
		JPopupMenu popupMenuacc = new JPopupMenu();
	    JMenuItem editacc = new JMenuItem("Edit");
	    JMenuItem delacc = new JMenuItem("Remove");
	    popupMenuacc.add(editacc);
	    popupMenuacc.add(delacc);
	    table.setComponentPopupMenu(popupMenuacc);
	    editacc.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = table.getSelectedRow();
		    	   String accid =(String) table.getModel().getValueAt(row, 0);
		    	   try {
					AccEdit editframe = new AccEdit(accid,invi);
					editframe.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	   
		       }
		    });
	    delacc.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   int row = table.getSelectedRow();
		    	   String accid =(String) table.getModel().getValueAt(row, 0);
		    	   try {
					DelAcc editframe = new DelAcc(accid,invi);
					editframe.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	   
		       }
		    });
	    
	}

	private JLabel getLblAccountListOf() {
		if (lblAccountListOf == null) {
			lblAccountListOf = new JLabel("Account list of user");
			lblAccountListOf.setBounds(297, 13, 202, 16);
		}
		return lblAccountListOf;
	}
	private JLabel getOwnername() {
		if (ownername == null) {
			ownername = new JLabel("New label");
			ownername.setBounds(437, 13, 56, 16);
		}
		return ownername;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setForeground(SystemColor.textHighlight);
			table.setFont(new Font("Tahoma", Font.PLAIN, 18));
			table.setBackground(SystemColor.textHighlightText);
		}
		return table;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 42, 1102, 427);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
}
