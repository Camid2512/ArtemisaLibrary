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
import co.edu.unbosque.service.AdminService;
import co.edu.unbosque.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase que permite gestionar las graficas del administrador
 * 
 * @author Santiago Rueda
 * @version 1.0
 * @since 01/04/2024
 */
@Named("chartsAdminBean")
@SessionScoped
public class ChartsAdminBean implements Serializable {

	/**
	 * Identificador unico de la version de la clase para la serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que llama a la clase UserService
	 */
	@Inject
	private UserService uService;

	/**
	 * Atributo que llama a la clase AdminService
	 */
	@Inject
	private AdminService aService;

	/**
	 * Atributo que llama a la clase BarChartModel
	 */
	private BarChartModel barModel2;

	/**
	 * Constructor que inicializa la grafica
	 */
	@PostConstruct
	public void init() {
		createBarModel2();
	}

	/**
	 * Funcion que permite crear la tabla respecto a la grafica de barrras de los
	 * usuarios que estan registrados en la biblioteca
	 */
	public void createBarModel2() {
		barModel2 = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Usuarios Registrados");

		List<Number> numTopics = new ArrayList<>();
		int numUsers = 0;
		// Check if services are not null before accessing their methods
		if (uService != null) {

			numUsers += uService.getUserList().size();

		} else {
			numTopics.add(0); // Add default value if service is null
		}

		if (aService != null) {
			numUsers += aService.getAdminList().size();
		} else {
			numTopics.add(0); // Add default value if service is null
		}

		numTopics.add(numUsers);

		barDataSet.setData(numTopics);

		List<String> bgColor = new ArrayList<>();
		bgColor.add("rgba(96, 45, 128, 0.5)");
		barDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();
		borderColor.add("rgb(255, 99, 132)");
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);

		List<String> labels = new ArrayList<>();
		labels.add("USERS");
		data.setLabels(labels);
		barModel2.setData(data);

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
		title.setText("NUMERO USUARIOS");
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

		barModel2.setOptions(options);
	}

	/**
	 * Get del atributo uService
	 * 
	 * @return uService
	 */
	public UserService getuService() {
		return uService;
	}

	/**
	 * Set del atributo uService
	 * 
	 * @param uService
	 */
	public void setuService(UserService uService) {
		this.uService = uService;
	}

	/**
	 * Get del atributo aService
	 * 
	 * @return uService
	 */
	public AdminService getaService() {
		return aService;
	}

	/**
	 * Set del atributo aService
	 * 
	 * @param aService
	 */
	public void setaService(AdminService aService) {
		this.aService = aService;
	}

	/**
	 * Get del atributo barModel2
	 * 
	 * @return barModel2
	 */
	public BarChartModel getBarModel2() {
		return barModel2;
	}

	/**
	 * Set del atributo barModel2
	 * 
	 * @param uService
	 */
	public void setBarModel2(BarChartModel barModel2) {
		this.barModel2 = barModel2;
	}

	/**
	 * Obtiene el valor del serialVersionUID.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
