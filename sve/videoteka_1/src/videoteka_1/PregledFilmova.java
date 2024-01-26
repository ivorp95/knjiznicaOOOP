package videoteka_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PregledFilmova extends JFrame {

	private JPanel contentPane;
	private JTable table_filmovi;
	private JScrollPane scrollPane_1;
	private JTable table_rezervirani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledFilmova frame = new PregledFilmova();
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
	public PregledFilmova() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 1010, 195);
		contentPane.add(scrollPane);
		
		table_filmovi = new JTable();
		table_filmovi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selected = table_filmovi.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)table_filmovi.getModel();
				int id_filma = (int)model.getValueAt(selected, 0);
				System.out.println("Film: "+id_filma);			
				//SELECT r.id, r.datum_sat, k.korisnicko_ime, k.br_tel FROM rezervacija as r, korisnik as k WHERE r.korisnik=k.id AND r.film=3 ORDER BY `datum_sat` DESC 
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/oot2_izv?serverTimezone=UTC", "oot2", "11");
					String upit="SELECT r.id, r.datum_sat, k.korisnicko_ime, k.br_tel FROM rezervacija as r, "+
							"korisnik as k WHERE r.korisnik=k.id AND r.film=? ORDER BY `datum_sat` DESC";
					PreparedStatement ps=con.prepareStatement(upit); 
					ps.setInt(1, id_filma);
					
					ResultSet rs=ps.executeQuery();
					
					DefaultTableModel model_r = (DefaultTableModel) table_rezervirani.getModel();
					model_r.setRowCount(0);
					while (rs.next()){
						long ms_now = (new Date()).getTime();
						System.out.println("Datum: "+rs.getDate(2));
						System.out.println("Vrijeme: "+rs.getTime(2));
						long ms = rs.getDate(2).getTime()+rs.getTime(2).getTime();
						long ukupnoVrijemeMS=ms_now-ms;
						long sek = ukupnoVrijemeMS/1000;
						long sati = sek/3600;
						long dani = sati/24;
						sati = sati % 24;
						String ukupnoVrijeme = dani+" dana i "+sati+" sata";
						model_r.addRow(new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), 
								rs.getString(4), ukupnoVrijeme});
						
					}	
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Greška prilikom spajanja na bazu "+e1.getMessage());
					
				}			
				
			}
		});
		table_filmovi.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "\u017Danr", "Naslov", "Godina", "Glumci", "Radnja", "Rezervirano", "Iznajmljeno", "Slobodno"
			}
		));
		table_filmovi.getColumnModel().getColumn(0).setPreferredWidth(35);
		table_filmovi.getColumnModel().getColumn(2).setPreferredWidth(224);
		table_filmovi.getColumnModel().getColumn(4).setPreferredWidth(189);
		table_filmovi.getColumnModel().getColumn(5).setPreferredWidth(155);
		table_filmovi.getColumnModel().getColumn(6).setPreferredWidth(76);
		table_filmovi.getColumnModel().getColumn(7).setPreferredWidth(70);
		scrollPane.setViewportView(table_filmovi);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 239, 1010, 289);
		contentPane.add(scrollPane_1);
		
		table_rezervirani = new JTable();
		table_rezervirani.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Id rezervacije", "Vrijeme rezervacije", "Korisniik", "Broj telefona", "Proteklo vrijeme"
			}
		));
		table_rezervirani.getColumnModel().getColumn(0).setPreferredWidth(82);
		table_rezervirani.getColumnModel().getColumn(1).setPreferredWidth(133);
		table_rezervirani.getColumnModel().getColumn(2).setPreferredWidth(136);
		table_rezervirani.getColumnModel().getColumn(3).setPreferredWidth(120);
		table_rezervirani.getColumnModel().getColumn(4).setPreferredWidth(126);
		scrollPane_1.setViewportView(table_rezervirani);
		getData();
	}

	private void getData() {
		// TODO Auto-generated method stub
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/oot2_izv?serverTimezone=UTC", "oot2", "11");
			String upit="SELECT * FROM film";
			PreparedStatement ps=con.prepareStatement(upit); 
			/*
			 * ps.setString(1, brojMobs);
			 * ps.setString(2, lozinkas);
			 */
			
			ResultSet rs=ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table_filmovi.getModel();
			while (rs.next()){
				model.addRow(new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(10), rs.getInt(11), 
						rs.getInt(9)-rs.getInt(10)-rs.getInt(11)});
				
			}	
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Greška prilikom spajanja na bazu "+e1.getMessage());
			
		}
		
	}

}
