package p1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class NewUser1 extends JFrame {
	Connection con;
	PreparedStatement pst;
	Statement stmt;
	
	private JPanel contentPane;
	private JPasswordField txtPass;
	private JPasswordField cnfPass;
	private JTextField txtUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser1 frame = new NewUser1();
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
	public NewUser1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 290);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 62, 73, 19);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 92, 73, 19);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmpassword = new JLabel("ConfirmPassword");
		lblConfirmpassword.setBounds(10, 122, 90, 19);
		contentPane.add(lblConfirmpassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(130, 61, 135, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(130, 91, 135, 19);
		contentPane.add(txtPass);
		
		cnfPass = new JPasswordField();
		cnfPass.setBounds(130, 122, 135, 19);
		contentPane.add(cnfPass);
		
		//JComboBox UsrTyp = new JComboBox();
		//UsrTyp.setModel(new DefaultComboBoxModel(new String[] {"User", "Admin"}));
		//UsrTyp.setBounds(93, 165, 57, 22);
		//contentPane.add(UsrTyp);
		
		//JLabel lblUsertype = new JLabel("UserType");
		//lblUsertype.setBounds(10, 169, 73, 18);
		//contentPane.add(lblUsertype);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUser.getText().length()==0)
				{
					JOptionPane.showMessageDialog(txtUser,"Please insert user Name");
				}
				else if(txtPass.getText().length()==0)
				{
					JOptionPane.showMessageDialog(txtPass,"Please insert Password");
				}
				else if(txtPass.getText().equals(cnfPass.getText())==false)
				{
					JOptionPane.showMessageDialog(getParent(), "Password Does Not Match");
				}
				else 
				{ 
					try {
						String usernm=txtUser.getText();
						String passwrd=txtPass.getText();
						//String cnfpasswrd=CnfPass.getText();
						//String utype=UsrTyp.getSelectedItem().toString();

						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentmanagement" ,"root" ,"mysql");
						pst=con.prepareStatement("insert into user (UserName,Password) values(?,?)");
						pst.setString(1,usernm);
						pst.setString(2,passwrd);
						//pst.setString(3,utype);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null,"User Created.....");
						txtUser.setText("");
						txtPass.setText("");
						cnfPass.setText("");
						//UsrTyp.setSelectedIndex(-1);
						txtUser.requestFocus();
						
					} catch (Exception e2) {
						e2.printStackTrace();
						}
				}
			}
			
		});
		btnCreate.setBounds(55, 185, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnCancel.setBounds(193, 185, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewuserCreation = new JLabel("NewUser Creation");
		lblNewuserCreation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewuserCreation.setBackground(Color.BLACK);
		lblNewuserCreation.setBounds(98, 0, 343, 44);
		contentPane.add(lblNewuserCreation);
	}
}
