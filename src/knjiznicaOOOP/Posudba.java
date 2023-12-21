package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

public class Posudba {

	private JFrame frame;
	private JTable tablica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Posudba window = new Posudba();
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
	public Posudba() {
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
		
		tablica = new JTable();
		tablica.setBounds(6, 6, 438, 208);
		frame.getContentPane().add(tablica);
	}
}
