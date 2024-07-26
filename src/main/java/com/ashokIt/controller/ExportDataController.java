package com.ashokIt.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class ExportDataController {
	@Autowired
	ExportDatatoXLService exportXlcomponent;
	@Autowired
	ExportDatatoPDFService exportPdfcomponent;
	@GetMapping("/downloads/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		exportXlcomponent.generatedData(response);
	}
	@GetMapping("/downloads/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=PDF_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		exportPdfcomponent.generatedData(response);
	}
}
