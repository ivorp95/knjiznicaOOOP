package knjiznicaOOOP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
		frame.setBounds(100, 100, 520, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 27, 456, 205);
		frame.getContentPane().add(scrollPane);
		
		tablica = new JTable();
		tablica.setBounds(17, 19, 414, 184);
		frame.getContentPane().add(tablica);
		
		tablica = new JTable();
		scrollPane.setViewportView(tablica);
		tablica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID knjige", "Naziv", "Autor", "Godina izdavanja"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		
		JButton btnNewButton = new JButton("Prikaži sve knjige");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/ipangos?serverTimezone=UTC","ipangos","11");
					String upit="SELECT * FROM knjigaOOOP";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) tablica.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int knjiga_id=rs.getInt(1);
						String naslov=rs.getString(2);
						String autor=rs.getString(3);
						String godIzd=rs.getString(4);
						model.addRow(new Object[] {knjiga_id, naslov, autor, godIzd});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
				}
			}
		});
		btnNewButton.setBounds(173, 270, 149, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
