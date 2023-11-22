package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class GlavniIzbornik {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniIzbornik window = new GlavniIzbornik();
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
	public GlavniIzbornik() {
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
		
		JButton btnNewButton = new JButton("Posudba");
		btnNewButton.setBounds(57, 25, 117, 62);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Vracanje");
		btnNewButton_1.setBounds(57, 105, 117, 62);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Novi korisnik");
		btnNewButton_2.setBounds(57, 190, 117, 62);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ispis ");
		btnNewButton_3.setBounds(257, 25, 117, 62);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Pretraga");
		btnNewButton_3_1.setBounds(257, 105, 117, 62);
		frame.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Brisanje korisnika");
		btnNewButton_3_2.setBounds(257, 190, 117, 62);
		frame.getContentPane().add(btnNewButton_3_2);
	}
	
		public void showWindow(){			//metoda showWindow() definicija nove metode za otvaranje novog prozora -----definicija metoda ide na pocetak ili kraj 
		frame.setVisible(true);
		}
}
