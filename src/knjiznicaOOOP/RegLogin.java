package knjiznicaOOOP;

// moramo importat ostale klase koje se pozivaju ovom klasom u obliku --  import packageName.className
import knjiznicaOOOP.Login;
import knjiznicaOOOP.Registracija;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("unused")
public class RegLogin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegLogin window = new RegLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("LogIn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login=new Login();
				login.showWindow();
			}
		});
		btnNewButton.setBounds(126, 6, 189, 103);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRegistracija = new JButton("Registracija");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registracija registracija = new Registracija(); 	 //instanciranje novog objekta klase Registracija
				registracija.showWindow();		//pozivanje metode showWindow
				
			}
		});
		btnRegistracija.setBounds(126, 163, 189, 103);
		frame.getContentPane().add(btnRegistracija);
	}
}
