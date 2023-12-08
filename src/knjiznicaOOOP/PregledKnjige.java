package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class PregledKnjige {

	private JFrame frame;
	private JTable tablica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledKnjige window = new PregledKnjige();
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
	public PregledKnjige() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 519, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tablica = new JTable();
		tablica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"godina izdavanja", "autor", "naziv", "ID knjige"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablica.setBounds(10, 11, 483, 248);
		frame.getContentPane().add(tablica);
		
		JButton btnNewButton = new JButton("Prilkaži sve knjige");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(173, 270, 149, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
