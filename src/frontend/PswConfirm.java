package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PswConfirm extends JFrame {

	private JPanel contentPane;
	private JLabel lblDepositConfirm;
	private JLabel lblUsername;
	private JButton unameshow;
	private JLabel Balance;
	private JLabel lblNewLabel;
	private JLabel lblVn;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public PswConfirm(String accid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblDepositConfirm());
		contentPane.add(getLblUsername());
		contentPane.add(getUnameshow());
		contentPane.add(getBalance());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblVn());
	}
	private JLabel getLblDepositConfirm() {
		if (lblDepositConfirm == null) {
			lblDepositConfirm = new JLabel("Deposit Confirm");
			lblDepositConfirm.setBounds(252, 13, 56, 16);
		}
		return lblDepositConfirm;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username");
			lblUsername.setBounds(62, 74, 77, 16);
		}
		return lblUsername;
	}
	private JButton getUnameshow() {
		if (unameshow == null) {
			unameshow = new JButton("New button");
			unameshow.setBounds(200, 70, 97, 25);
		}
		return unameshow;
	}
	private JLabel getBalance() {
		if (Balance == null) {
			Balance = new JLabel("Current balance");
			Balance.setBounds(44, 130, 84, 16);
		}
		return Balance;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(208, 130, 56, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblVn() {
		if (lblVn == null) {
			lblVn = new JLabel("VN\u0110");
			lblVn.setBounds(332, 130, 56, 16);
		}
		return lblVn;
	}
}
