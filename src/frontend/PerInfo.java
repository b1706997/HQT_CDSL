package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class PerInfo extends JFrame {

	private JPanel contentPane;
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
	private JLabel label_11;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerInfo frame = new PerInfo();
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
	public PerInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		contentPane.add(getLabel_11());
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
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("personid");
			label_1.setBounds(65, 79, 56, 16);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Full name");
			label_2.setBounds(65, 124, 56, 16);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Identity");
			label_3.setBounds(65, 155, 56, 16);
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Birthday");
			label_4.setBounds(65, 224, 56, 16);
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Age");
			label_5.setBounds(65, 278, 56, 16);
		}
		return label_5;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Role");
			label_6.setBounds(65, 319, 56, 16);
		}
		return label_6;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("Join day");
			label_7.setBounds(65, 360, 56, 16);
		}
		return label_7;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("Phone numbers");
			label_8.setBounds(65, 402, 102, 16);
		}
		return label_8;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("Addresses");
			label_9.setBounds(65, 466, 80, 16);
		}
		return label_9;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("ZIP ");
			label_10.setBounds(65, 550, 56, 16);
		}
		return label_10;
	}
	private JLabel getLabel_11() {
		if (label_11 == null) {
			label_11 = new JLabel("Email");
			label_11.setBounds(65, 195, 56, 16);
		}
		return label_11;
	}
	private JLabel getPeridshow() {
		if (peridshow == null) {
			peridshow = new JLabel("<dynamic>");
			peridshow.setBounds(145, 79, 56, 16);
		}
		return peridshow;
	}
	private JLabel getFnameshow() {
		if (fnameshow == null) {
			fnameshow = new JLabel((String) null);
			fnameshow.setBounds(170, 124, 126, 16);
		}
		return fnameshow;
	}
	private JLabel getCmndshow() {
		if (cmndshow == null) {
			cmndshow = new JLabel((String) null);
			cmndshow.setBounds(159, 155, 117, 16);
		}
		return cmndshow;
	}
	private JLabel getEmailshow() {
		if (emailshow == null) {
			emailshow = new JLabel((String) null);
			emailshow.setBounds(145, 195, 166, 16);
		}
		return emailshow;
	}
	private JLabel getBirthdayshow() {
		if (birthdayshow == null) {
			birthdayshow = new JLabel((String) null);
			birthdayshow.setBounds(145, 224, 131, 16);
		}
		return birthdayshow;
	}
	private JLabel getAgeshow() {
		if (ageshow == null) {
			ageshow = new JLabel("0");
			ageshow.setBounds(133, 278, 56, 16);
		}
		return ageshow;
	}
	private JLabel getRoleshow() {
		if (roleshow == null) {
			roleshow = new JLabel((String) null);
			roleshow.setBounds(145, 319, 131, 16);
		}
		return roleshow;
	}
	private JLabel getJoindayshow() {
		if (joindayshow == null) {
			joindayshow = new JLabel((String) null);
			joindayshow.setBounds(170, 360, 106, 16);
		}
		return joindayshow;
	}
	private JLabel getPhone1show() {
		if (phone1show == null) {
			phone1show = new JLabel((String) null);
			phone1show.setBounds(181, 402, 56, 16);
		}
		return phone1show;
	}
	private JLabel getPhone2show() {
		if (phone2show == null) {
			phone2show = new JLabel((String) null);
			phone2show.setBounds(181, 431, 56, 16);
		}
		return phone2show;
	}
	private JLabel getAddr1show() {
		if (addr1show == null) {
			addr1show = new JLabel((String) null);
			addr1show.setBounds(170, 466, 56, 16);
		}
		return addr1show;
	}
	private JLabel getAddr2show() {
		if (addr2show == null) {
			addr2show = new JLabel((String) null);
			addr2show.setBounds(170, 507, 56, 16);
		}
		return addr2show;
	}
	private JLabel getZipshow() {
		if (zipshow == null) {
			zipshow = new JLabel((String) null);
			zipshow.setBounds(159, 550, 56, 16);
		}
		return zipshow;
	}
}
