package co.edu.unbosque.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import co.edu.unbosque.service.CPLUSService;
import co.edu.unbosque.service.JavaTopicService;
import co.edu.unbosque.service.PythonTopicService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase que permite la gestion de graficos de barra y de pastel de los temas en
 * los diferentes lenguaes de programacion
 * 
 * @author Saniago Rueda
 * @version 1.0
 * @since 01/04/2024
 */
@Named("chartsUserBean")
@SessionScoped
public class ChartsUserBean implements Serializable {

	/**
	 * Identificador unico de la version de la clase para la serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que llama a la clase CPLUSService
	 */
	@Inject
	private CPLUSService cPlusService;

	/**
	 * Atributo que llama a la clase JavaTopicService
	 */
	@Inject
	private JavaTopicService javaService;

	/**
	 * Atributo que llama a la clase PythonTopicService
	 */
	@Inject
	private PythonTopicService pythonService;

	/**
	 * Atributo que llama a la clase BarChartModel
	 */
	private BarChartModel barModel;

	/**
	 * Atributo que llama a la clase PieChartModel
	 */
	private PieChartModel pieModel;

	/**
	 * Constructor que inicializa la grafica de barras y la de pastel
	 */
	@PostConstruct
	public void init() {
		createBarModel();
		createPieModel();
	}

	/**
	 * Funcion que permite crear la tabla respecto a la grafica de barrras de los
	 * usuarios que estan registrados en la biblioteca
	 */
	public void createBarModel() {
		barModel = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Temas por lenguaje");

		List<Number> numTopics = new ArrayList<>();

		// Check if services are not null before accessing their methods
		if (cPlusService != null) {
			numTopics.add(cPlusService.getTopics().size());
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		if (javaService != null) {
			numTopics.add(javaService.getTopics().size());
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		if (pythonService != null) {
			numTopics.add(pythonService.getTopics().size());
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		barDataSet.setData(numTopics);

		List<String> bgColor = new ArrayList<>();
		bgColor.add("rgba(4, 78, 134, 0.5)");
		bgColor.add("rgba(245, 131, 18, 0.5)");
		bgColor.add("rgba(254, 216, 101,0.5)");
		barDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();
		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(69, 97, 61)");
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);

		List<String> labels = new ArrayList<>();
		labels.add("C ++");
		labels.add("Java");
		labels.add("Python");
		data.setLabels(labels);
		barModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		options.setMaintainAspectRatio(false);
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		linearAxes.setOffset(true);
		linearAxes.setBeginAtZero(true);
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Temas");
		options.setTitle(title);

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("italic");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		// disable animation
		Animation animation = new Animation();
		animation.setDuration(0);
		options.setAnimation(animation);

		barModel.setOptions(options);
	}

	/**
	 * Funcion que permite crear la tabla respecto a la grafica de pastel de los
	 * usuarios que estan registrados en la biblioteca
	 */
	public void createPieModel() {
		pieModel = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> numTopics = new ArrayList<>();
		int difCPlus = 0;
		int difJava = 0;
		int difPython = 0;
		// Check if services are not null before accessing their methods
		if (cPlusService != null) {

			for (int i = 0; i < cPlusService.getTopics().size(); i++) {
				difCPlus += cPlusService.getTopics().get(i).getDifficulty();
			}
			numTopics.add(difCPlus);
			System.out.println(difCPlus);
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		if (javaService != null) {
			for (int i = 0; i < javaService.getTopics().size(); i++) {
				difJava += javaService.getTopics().get(i).getDifficulty();
			}
			numTopics.add(difJava);
			System.out.println(difJava);
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		if (pythonService != null) {
			for (int i = 0; i < pythonService.getTopics().size(); i++) {
				difPython += pythonService.getTopics().get(i).getDifficulty();
			}
			numTopics.add(difPython);
			System.out.println(difPython);
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		dataSet.setData(numTopics);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(4, 78, 134)");
		bgColors.add("rgb(245, 131, 18)");
		bgColors.add("rgb(254, 216, 101)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add("Diff C++");
		labels.add("Diff Java");
		labels.add("Diff Python");
		data.setLabels(labels);

		pieModel.setData(data);
	}

	/**
	 * Get del atributo barModel
	 * 
	 * @return barModel
	 */
	public BarChartModel getBarModel() {
		return barModel;
	}

	/**
	 * Get del atributo cPlusService
	 * 
	 * @return cPlusService
	 */
	public CPLUSService getcPlusService() {
		return cPlusService;
	}

	/**
	 * Set del atributo cPlusService
	 * 
	 * @param cPlusService
	 */
	public void setcPlusService(CPLUSService cPlusService) {
		this.cPlusService = cPlusService;
	}

	/**
	 * Get del atributo javaService
	 * 
	 * @return javaService
	 */
	public JavaTopicService getJavaService() {
		return javaService;
	}

	/**
	 * Set del atributo javaService
	 * 
	 * @param javaService
	 */
	public void setJavaService(JavaTopicService javaService) {
		this.javaService = javaService;
	}

	/**
	 * Get del atributo pythonService
	 * 
	 * @return pythonService
	 */
	public PythonTopicService getPythonService() {
		return pythonService;
	}

	/**
	 * Set del atributo pythonService
	 * 
	 * @param pythonService
	 */
	public void setPythonService(PythonTopicService pythonService) {
		this.pythonService = pythonService;
	}

	/**
	 * Obtiene el valor del serialVersionUID.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Set del atributo barModel
	 * 
	 * @param barModel
	 */
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	/**
	 * Get del atributo pieModel
	 * 
	 * @return pieModel
	 */
	public PieChartModel getPieModel() {
		return pieModel;
	}

	/**
	 * Set del atributo pieModel
	 * 
	 * @param pieModel
	 */
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

}
