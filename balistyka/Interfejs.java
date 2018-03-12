import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class Interfejs {

	private JFrame frame;
	private JTextField Masa;
	private JTextField Predkosc;
	private JTextField Kat;
	private JTextField Tarcie;
	private JTextField txtWysokosc;
	private JTextField textOdleglosc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfejs window = new Interfejs();
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
	public Interfejs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 22, 300, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Masa [g]:");
		label.setBounds(12, 12, 121, 23);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Prędkość [m/s]:");
		label_1.setBounds(12, 47, 125, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Kąt[wzgledem ziemi]:");
		label_2.setBounds(12, 84, 156, 31);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Współczynnik \ntarcia:");
		label_3.setBounds(12, 116, 156, 31);
		panel.add(label_3);
		
		Masa = new JTextField();
		Masa.setColumns(10);
		Masa.setBounds(163, 11, 125, 25);
		panel.add(Masa);
		
		Predkosc = new JTextField();
		Predkosc.setColumns(10);
		Predkosc.setBounds(163, 47, 125, 25);
		panel.add(Predkosc);
		
		Kat = new JTextField();
		Kat.setColumns(10);
		Kat.setBounds(173, 84, 125, 25);
		panel.add(Kat);
		
		txtWysokosc = new JTextField();
		txtWysokosc.setText("Wysokosc");
		txtWysokosc.setColumns(10);
		txtWysokosc.setBounds(63, 195, 167, 31);
		panel.add(txtWysokosc);
		
		textOdleglosc = new JTextField();
		textOdleglosc.setText("Odleglosc");
		textOdleglosc.setColumns(10);
		textOdleglosc.setBounds(63, 238, 167, 34);
		panel.add(textOdleglosc);
		
		JLabel label_4 = new JLabel("Obliczone Wartości:");
		label_4.setBounds(33, 159, 168, 34);
		panel.add(label_4);
		
		Tarcie = new JTextField();
		Tarcie.setBounds(173, 119, 125, 25);
		panel.add(Tarcie);
		Tarcie.setColumns(10);
		
		JPanel panelWykresu = new JPanel();
		panelWykresu.setBounds(324, 22, 364, 300);
		frame.getContentPane().add(panelWykresu);
		panelWykresu.setLayout(new BorderLayout(0, 0));
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(12, 334, 676, 76);
		frame.getContentPane().add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		
		
		JButton button = new JButton("Oblicz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double m = Double.parseDouble(Masa.getText());
				double v = Double.parseDouble(Predkosc.getText());
				double alfa = Double.parseDouble(Kat.getText());
				double wsp = Double.parseDouble(Tarcie.getText());
				Pocisk bullet = new Pocisk(m,v,alfa,wsp);
				txtWysokosc.setText(""+bullet.Wysokosc);
				textOdleglosc.setText(""+bullet.Odleglosc);
			}
		});
		button.setBounds(12, 12, 134, 43);
		ButtonPanel.add(button);
		
		JButton button_1 = new JButton("Zapisz konfiguracje");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double m = Double.parseDouble(Masa.getText());
				double v = Double.parseDouble(Predkosc.getText());
				double alfa = Double.parseDouble(Kat.getText());
				double wsp = Double.parseDouble(Tarcie.getText());
				
				try {
					ZapiszKonfiguracje(m,v,alfa,wsp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(158, 12, 201, 25);
		ButtonPanel.add(button_1);
		
		JButton button_2 = new JButton("Wykres");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double m = Double.parseDouble(Masa.getText());
				double v = Double.parseDouble(Predkosc.getText());
				double alfa = Double.parseDouble(Kat.getText());
				double wsp = Double.parseDouble(Tarcie.getText());
				Wykres wykres = new Wykres(m,v,alfa,wsp);
				
				panelWykresu.removeAll();
			panelWykresu.add(wykres.chartPanel, BorderLayout.CENTER);
			panelWykresu.revalidate();
			panelWykresu.repaint();
			
			}
		});
		button_2.setBounds(416, 12, 117, 43);
		ButtonPanel.add(button_2);
		
		JButton btnNewButton = new JButton("Wczytaj konfiguracje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ArrayList lista = new ArrayList();
			try {
				lista = WczytajDane();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Masa.setText(""+lista.get(0));
			Predkosc.setText(""+lista.get(1));
			Kat.setText(""+lista.get(2));
			Tarcie.setText(""+lista.get(3));
			
			}
		});
		btnNewButton.setBounds(158, 45, 200, 25);
		ButtonPanel.add(btnNewButton);
	}



public void ZapiszKonfiguracje(double w1, double w2, double w3, double w4) throws IOException{
	
	FileWriter plikWy = new FileWriter("konfiguracja.txt");           
            
        plikWy.write(""+w1+ "," +w2+ "," +w3+ "," +w4);
  
        plikWy.close();
}

@SuppressWarnings({ "rawtypes", "unchecked" })
static ArrayList WczytajDane() throws FileNotFoundException{
	File plik = new File("konfiguracja.txt");
	Scanner odczyt = new Scanner(plik);
	ArrayList lista = new ArrayList();
	
	StringTokenizer token = null;
	while(odczyt.hasNext())
	token=new StringTokenizer(odczyt.next(), ",");
	while (token.hasMoreElements())
		lista.add(token.nextToken());
		
		
	
	odczyt.close();
	return lista;
}



}