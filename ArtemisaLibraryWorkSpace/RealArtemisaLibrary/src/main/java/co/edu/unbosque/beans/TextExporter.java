package co.edu.unbosque.beans;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.export.DataTableExporter;
import org.primefaces.component.export.ExporterOptions;
import org.primefaces.component.export.ExporterUtils;
import org.primefaces.util.EscapeUtils;

import jakarta.faces.FacesException;
import jakarta.faces.context.FacesContext;

public class TextExporter extends DataTableExporter<PrintWriter, ExporterOptions> {

	public TextExporter() {
		super(null, Collections.emptySet(), false);
	}

	@Override
	protected PrintWriter createDocument(FacesContext context) throws IOException {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(os(), exportConfiguration.getEncodingType());
			return new PrintWriter(osw);
		} catch (UnsupportedEncodingException e) {
			throw new FacesException(e);
		}
	}

	@Override
	protected void exportTable(FacesContext context, DataTable table, int index) throws IOException {
		document.append("").append(table.getId()).append("\n");

		super.exportTable(context, table, index);

		document.append("").append(table.getId());
	}

	@Override
	protected void preRowExport(FacesContext context, DataTable table) {
		(document).append("\t").append(table.getVar()).append("\n");
	}

	@Override
	protected void exportCellValue(FacesContext context, DataTable table, UIColumn col, String text, int index) {
		String columnTag = ExporterUtils.getColumnExportTag(context, col);
		document.append("\t\t").append(columnTag).append(": ").append(EscapeUtils.forXml(text)).append("\n");
	}

	@Override
	public String getContentType() {
		return "text/plain";
	}

	@Override
	public String getFileExtension() {
		return ".txt";
	}
}