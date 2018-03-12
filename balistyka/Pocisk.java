
import java.util.ArrayList;



public class Pocisk{

	static double masa;	//masa podana w gramach
	static double predkosc;
	double Energia; //= (masa*predkosc*predkosc)/2000;
	//double CzasLotu;
	private static double Vy; 	//predkosci katowe	
	private static double Vx;
	
	private static double g =  9.81;
	
	static double WspTarcia;
	
	double KatWystrzalu;
	
	public ArrayList<Double> WspX = new ArrayList<Double>();		//Wspolrzedne w ktorych znajduje sie pocisk
	public ArrayList<Double> WspY = new ArrayList<Double>();	
	
	public Pocisk(){};
	
	public Pocisk(double m, double v ){
		masa = m;
		predkosc = v;
		Energia = (masa*predkosc*predkosc)/2000;
		}
	
	
	
	/*
	 * Konstruktor do strzalu po katem
	 */
	
	public Pocisk(double masa, double predkosc, double kat, double wspolczynnik){
		Pocisk.masa = masa*0.001;
		Pocisk.predkosc = predkosc;
		KatWystrzalu = kat;
		WspTarcia = wspolczynnik;
		Energia = (masa*predkosc*predkosc)/2000;
		Vy = predkosc * Math.sin(Math.toRadians(KatWystrzalu));
		Vx = predkosc * Math.cos(Math.toRadians(KatWystrzalu));
		Wypelnij();
	}
	

	public double CzasLotu(){
		double CzasWznoszenia = Vy/g;
		double CzasOpadania = Math.sqrt(2*Wysokosc/g);
		
		return CzasWznoszenia + CzasOpadania;
	}
	
	public double Wysokosc = Math.sqrt((Vy*Vy)/(2*g));


	public double Odleglosc = CzasLotu() * Vx;
	
	public void Wypelnij(){
		WspX.add(0.0);
		WspY.add(0.0);
		for(int i =1; i<=CzasLotu(); i++){
			 double x = (Vx/WspTarcia)*(1-Math.exp(-WspTarcia*i));
			WspX.add(x);
			
			double y = (Vy/WspTarcia + g/WspTarcia)*(1 - Math.exp(-WspTarcia*i)) - g*i/WspTarcia;
			if(y<0)
				i = (int) CzasLotu();
				WspY.add(y);
		
		}
	}
	
}
		
	

