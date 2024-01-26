package videoteka_1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PregledFilm {

	private JFrame frame;
	private JTable tablica;
	private JTextField nazivFilma;
	private JTextField godIzdanja;
	private JTextField redatelj;
	private int id_film;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField pretraga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledFilm window = new PregledFilm();
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
	public PregledFilm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 863, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 53, 444, 235);
		frame.getContentPane().add(scrollPane);
		
		tablica = new JTable();
		scrollPane.setViewportView(tablica);
		tablica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Film id", "Naziv filma", "Godina izdanja", "Redatelj filma"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnNewButton = new JButton("Prika\u017Ei filmove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/isimac?serverTimezone=UTC", "isimac", "is123");
					String upit="SELECT * FROM filmOOT2";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);
					
					DefaultTableModel model=(DefaultTableModel)tablica.getModel();
					
					model.setRowCount(0);
					
					while(rs.next())
					{
						int id_film=rs.getInt(1);
						String naziv_film=rs.getString(2);
						String godina_izdavanja=rs.getString(3);
						String redatelj=rs.getString(4);
						
						model.addRow(new Object[] {id_film, naziv_film, godina_izdavanja, redatelj});
					}
					
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnNewButton.setBounds(246, 345, 137, 23);
		frame.getContentPane().add(btnNewButton);
		
		nazivFilma = new JTextField();
		nazivFilma.setBounds(661, 76, 86, 20);
		frame.getContentPane().add(nazivFilma);
		nazivFilma.setColumns(10);
		
		godIzdanja = new JTextField();
		godIzdanja.setBounds(661, 144, 86, 20);
		frame.getContentPane().add(godIzdanja);
		godIzdanja.setColumns(10);
		
		redatelj = new JTextField();
		redatelj.setBounds(661, 214, 86, 20);
		frame.getContentPane().add(redatelj);
		redatelj.setColumns(10);
		
		tablica.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						int odabraniRedak=tablica.getSelectedRow();
						nazivFilma.setText(tablica.getValueAt(odabraniRedak,1).toString());
						godIzdanja.setText(tablica.getValueAt(odabraniRedak, 2).toString());
						redatelj.setText(tablica.getValueAt(odabraniRedak, 3).toString());
						id_film=Integer.parseInt(tablica.getValueAt(odabraniRedak, 0).toString());
						
						
					}
				});
		
		JButton btnNewButton_1 = new JButton("A\u017Euriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JOptionPane.showMessageDialog(null, id_film);
				
				String nazivFilmas=nazivFilma.getText();
				String godIzdanjas=godIzdanja.getText();
				String redateljs=redatelj.getText();
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/isimac?serverTimezone=UTC", "isimac", "is123");
					
					String upit="UPDATE filmOOT2 SET naziv_film=?, godina_izdanja=?, redatelj=? WHERE film_id=?";
					PreparedStatement ps=con.prepareStatement(upit);
					
					ps.setString(1, nazivFilmas);
					ps.setString(2, godIzdanjas);
					ps.setString(3, redateljs);
					ps.setInt(4, id_film);
					
					int updateRedak=ps.executeUpdate();
					
					
					if (updateRedak > 0) {
		                JOptionPane.showMessageDialog(null, "Ažuriranje je uspješno izvršeno.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Ažuriranje nije uspješno izvršeno.");
		            }
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
				
			}
		});
		btnNewButton_1.setBounds(658, 296, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Obri\u0161i");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model=(DefaultTableModel)tablica.getModel();
				int odabraniRedak=tablica.getSelectedRow();
				
				if (odabraniRedak>=0)
				{
					try
					{
						int id_film=Integer.parseInt(tablica.getValueAt(odabraniRedak,0).toString());
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/isimac?serverTimezone=UTC", "isimac", "is123");
						
						
						String upit="DELETE FROM filmOOT2 WHERE film_id=?";
						//String upit="DELETE FROM filmOOT2 WHERE naziv_film=? AND godina_izdanja=? AND redatelj=?";
						PreparedStatement ps=con.prepareStatement(upit);
						ps.setInt(1, id_film);
						
						int rezultat=ps.executeUpdate();
						
						if (rezultat==1)
						{
							DefaultTableModel model1=(DefaultTableModel)tablica.getModel();
							model1.removeRow(odabraniRedak);
							JOptionPane.showMessageDialog(null, "Film uspješno izbrisan");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Film nije moguæe obrisati");
						}
						
						
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Redak nije odabran");
				}
			}
		});
		btnNewButton_2.setBounds(92, 345, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Pretraga");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pretragas=pretraga.getText();
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/isimac?serverTimezone=UTC", "isimac", "is123");
					
					String upit="SELECT * FROM filmOOT2 WHERE naziv_film LIKE ? OR godina_izdanja LIKE ? OR redatelj LIKE ?";
					
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setString(1, "%"+pretragas+"%");
					ps.setString(2, "%"+pretragas+"%");
					ps.setString(3, "%"+pretragas+"%");
					
					ResultSet rs=ps.executeQuery();
					//i dalje ide defaultTableModel i nakon toga while (rs.next)
					// i onda sve isto kao kod punjenja podataka iz baze
					
DefaultTableModel model=(DefaultTableModel)tablica.getModel();
					
					model.setRowCount(0);
					
					while(rs.next())
					{
						int id_film=rs.getInt(1);
						String naziv_film=rs.getString(2);
						String godina_izdavanja=rs.getString(3);
						String redatelj=rs.getString(4);
						
						model.addRow(new Object[] {id_film, naziv_film, godina_izdavanja, redatelj});
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_3.setBounds(400, 19, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		pretraga = new JTextField();
		pretraga.setBounds(180, 20, 184, 20);
		frame.getContentPane().add(pretraga);
		pretraga.setColumns(10);
	}
}
