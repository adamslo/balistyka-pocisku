import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Wykres extends Pocisk{
	

	public Wykres(double w1, double w2, double w3, double w4){
		super(w1,w2,w3,w4);
		
	}
	
		JFreeChart wykres = ChartFactory.createLineChart(
		         "Wykres",
		         "X","Y",
		         CreateDataset(),
		         PlotOrientation.VERTICAL,
		         true,true,false);
		
		ChartPanel chartPanel = new ChartPanel(wykres);		
	
	private DefaultCategoryDataset CreateDataset(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < WspY.size(); i++)
			dataset.addValue(WspY.get(i), "",WspX.get(i));
		
	
	return dataset;
	}
	
}
