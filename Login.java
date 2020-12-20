package p1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	Connection con;
	PreparedStatement pst;
	Statement stmt;
	ResultSet rs;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 434, 53);
		contentPane.add(panel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panel.add(lblLogin);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(10, 76, 71, 21);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 105, 71, 21);
		contentPane.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(98, 77, 204, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(98, 106, 204, 21);
		contentPane.add(txtPass);
		
		JButton btnNewuser = new JButton("NewUser?");
		btnNewuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUser1 n=new NewUser1();
				n.setVisible(true);
				
			}
		});
		btnNewuser.setBounds(28, 177, 108, 32);
		contentPane.add(btnNewuser);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(txtUser.getText().isEmpty() || txtPass.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"UserName Or Password not found...");
					}
					else
					{
						String usrname=txtUser.getText();
						String passwrd=txtPass.getText();
						
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentmanagement" ,"root" ,"mysql");
								//+ "jdbc:mysql://localhost/StudentManagement","root","");
						
						pst=con.prepareStatement("Select * from user where UserName=? and Password=?");
						pst.setString(1,usrname);
						pst.setString(2,passwrd);
						rs=pst.executeQuery();
						if(rs.next())
						{
							Main m=new Main();
							hide();
							m.setVisible(true);
							
						}
						else
						{
						
							JOptionPane.showMessageDialog(null,"UserName Or Password do not match...");
							txtUser.setText("");
							txtPass.setText("");
							txtUser.requestFocus();
						}
						
					}
					
				} 
				catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(146, 177, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(269, 177, 89, 23);
		contentPane.add(btnCancel);
	}
}
