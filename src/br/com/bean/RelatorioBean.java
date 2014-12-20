package br.com.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import br.com.modelo.Exame;
import br.com.modelo.Receita;

@ManagedBean
@ViewScoped
public class RelatorioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dtInicio1;
	private Date dtFim1;
	private Date dtInicio2;
	private Date dtFim2;
	private Date dtInicio3;
	private Date dtFim3;
	private Receita receitaSelecionada;

	public RelatorioBean() {
		dtInicio1 = new Date();
		dtFim1 = new Date();
		dtInicio2 = new Date();
		dtFim2 = new Date();
		dtInicio3 = new Date();
		dtFim3 = new Date();
		setReceitaSelecionada(new Receita());
	}

	// colocar o mesmo ds do tomcat

	public void medicamentosRInternaRetiradosPorPeriodo() {
		String relatorioPath = "/WEB-INF/reports/medicamentosRInternaRetiradosPorPeriodo.jrxml";

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("dtInicio", dtInicio1);
		jasperParameter.put("dtFim", dtFim1);

		exportToPdf(relatorioPath, jasperParameter);
	}

	public void medicamentosRExternaRetiradosPorPeriodo() {
		String relatorioPath = "/WEB-INF/reports/medicamentosRExternaRetiradosPorPeriodo.jrxml";

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("dtInicio", dtInicio2);
		jasperParameter.put("dtFim", dtFim2);

		exportToPdf(relatorioPath, jasperParameter);
	}

	public void consultas() {
		String relatorioPath = "/WEB-INF/reports/consultas.jrxml";

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("dtInicio", dtInicio3);
		jasperParameter.put("dtFim", dtFim3);

		exportToPdf(relatorioPath, jasperParameter);
	}
	
	public void receita() {
		String relatorioPath = "/WEB-INF/reports/receita.jrxml";

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("idReceita", receitaSelecionada.getId());

		exportToPdf(relatorioPath, jasperParameter);
	}
	
	public void receitaExterna(Receita receita) {
		String relatorioPath = "/WEB-INF/reports/receitaExterna.jrxml";

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("idReceita", receita.getId());

		exportToPdf(relatorioPath, jasperParameter);
	}
	
	public void estoque() {
		String relatorioPath = "/WEB-INF/reports/estoque.jrxml";

		exportToPdf(relatorioPath, null);
	}

	public void exame(Exame exame) {
		String relatorioPath = "/WEB-INF/reports/exame.jrxml";

		HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("idExame", exame.getId());

		exportToPdf(relatorioPath, jasperParameter);
	}
	
	public void exportToPdf(String relatorioPath,
			HashMap<String, Object> jasperParameter) {
		// Logando informações do usuário
		Connection connection = null;
		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			InputStream inputStream = servletContext
					.getResourceAsStream(relatorioPath);


			// JDBC driver name and database URL
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			String DB_URL = "jdbc:mysql://mysql.ubs.kinghost.net/ubs06?autoReconnect=true";

			// Database credentials
			String USER = "ubs06";
			String PASS = "a4w7f6q2";

			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(inputStream);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, jasperParameter, connection);

			byte[] file = JasperExportManager.exportReportToPdf(jasperPrint);

			int bufferSize = 2048;
			int fileLength = file.length;
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

			response.setContentType("application/pdf");

			response.setHeader("Content-disposition",
					"attachment;filename=Relatorio.pdf");

			response.setBufferSize(bufferSize);
			response.setContentLength(fileLength);
			ServletOutputStream out = response.getOutputStream();
			if (fileLength > 0) {
				out.write(file, 0, fileLength);
			}
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getLocalizedMessage()));
		} finally {
			// finally block used to close resources

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public Receita getReceitaSelecionada() {
		return receitaSelecionada;
	}

	public void setReceitaSelecionada(Receita receitaSelecionada) {
		this.receitaSelecionada = receitaSelecionada;
		System.out.println(receitaSelecionada.getId());
	}

	public Date getDtInicio1() {
		return dtInicio1;
	}

	public void setDtInicio1(Date dtInicio1) {
		this.dtInicio1 = dtInicio1;
	}

	public Date getDtFim1() {
		return dtFim1;
	}

	public void setDtFim1(Date dtFim1) {
		this.dtFim1 = dtFim1;
	}

	public Date getDtInicio2() {
		return dtInicio2;
	}

	public void setDtInicio2(Date dtInicio2) {
		this.dtInicio2 = dtInicio2;
	}

	public Date getDtFim2() {
		return dtFim2;
	}

	public void setDtFim2(Date dtFim2) {
		this.dtFim2 = dtFim2;
	}

	public Date getDtInicio3() {
		return dtInicio3;
	}

	public void setDtInicio3(Date dtInicio3) {
		this.dtInicio3 = dtInicio3;
	}

	public Date getDtFim3() {
		return dtFim3;
	}

	public void setDtFim3(Date dtFim3) {
		this.dtFim3 = dtFim3;
	}
}
