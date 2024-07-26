package com.ashokIt.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokIt.model.Task;
import com.ashokIt.repository.TaskRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExportDatatoXLService {
	@Autowired
	TaskRepository taskRepo;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public ExportDatatoXLService() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Task data");
	}

	public void generateHeader() {
		XSSFRow headerRow = sheet.createRow(0);
		int colindex = 0;
		String headers[] = { "Activity Title", "Activity message", "status" };
		for (String header : headers) {
			headerRow.createCell(colindex++).setCellValue(header);
		}
	}

	public void writetoCell(XSSFRow row, int column, Object data) {
		if (data instanceof Boolean) {
			row.createCell(column).setCellValue((Boolean) data);
		} else if (data instanceof Integer) {
			row.createCell(column).setCellValue((Integer) data);
		} else if (data instanceof String) {
			row.createCell(column).setCellValue((String) data);
		} else if (data instanceof LocalDateTime) {
			row.createCell(column).setCellValue((LocalDateTime) data);
		} else if (data instanceof Float) {
			row.createCell(column).setCellValue((Float) data);
		} else if (data instanceof Character) {
			row.createCell(column).setCellValue((Character) data);
		}
		else {
			row.createCell(column).setCellValue(data.toString());
		}

	}

	public void writeData(List<Task> tasks) {
		int rowIndex = 1, colIndex = 0;
		for (Task task : tasks) {
			colIndex = 0;
			XSSFRow row = sheet.createRow(rowIndex++);
			writetoCell(row, colIndex++, task.getActivityTitle());
			writetoCell(row, colIndex++, task.getActivityMessage());
			writetoCell(row, colIndex++, task.getStatus());
		}
	}

	public void generatedData(HttpServletResponse response) throws IOException {
		generateHeader();
		writeData(taskRepo.findAll());
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}
}
