package com.ashokIt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokIt.model.Task;
import com.ashokIt.repository.TaskRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExportDatatoPDFService {
	@Autowired
	TaskRepository taskRepo;
	public void generatedData(HttpServletResponse response) throws DocumentException, IOException {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			PdfPTable table = new PdfPTable(3);
			String[] headers = {"Activity Title","Activity Message","Activity status"}; 
			writeHeaders(headers,table);
			writeData(taskRepo.findAll(),table);
			document.add(table);
			document.close();
			
	}
	public void writeHeaders(String[] headers, PdfPTable table) {
		for(String header:headers) {
			table.addCell(header);
		}
	}
	public void writeData(List<Task> tasks,PdfPTable table) {
		for(Task task:tasks) {
			table.addCell(task.getActivityTitle());
			table.addCell(task.getActivityMessage());
			table.addCell(task.getStatus().toString());
		}
	}
	
}
