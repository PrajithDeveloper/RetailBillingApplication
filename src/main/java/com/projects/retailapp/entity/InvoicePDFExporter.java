package com.projects.retailapp.entity;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class InvoicePDFExporter {
	
	private List<SaleDetailsTbl> saleDetailsTbl;
	private Integer totalQty;

	public InvoicePDFExporter(List<SaleDetailsTbl> saleDetailsTbl) {
		super();
		this.saleDetailsTbl = saleDetailsTbl;
	}
	
	/*
	 *adding table column headings , number of columns
	 * 
	 * */
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Item Name", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Qty", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("MRP", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Rate", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Amount", font));
		table.addCell(cell);
	}
	
	/*
	 * adding each item details of saleDetailsTbl as rows into the table
	 * */
	private void writeTableData(PdfPTable table) {
		totalQty = 0;
		for (SaleDetailsTbl entity : saleDetailsTbl) {
			table.addCell(String.valueOf(entity.getItemTbl().getName()));
			table.addCell(String.valueOf(entity.getQty()));
			table.addCell(String.valueOf(entity.getItemTbl().getMrp()));
			table.addCell(String.valueOf(entity.getItemTbl().getRate()));
			table.addCell(String.valueOf(entity.getTotal()));
			totalQty += entity.getQty();
		}
	}
	
	/*
	 * opens a document 
	 * writes table with item details
	 * writes sale details
	 * */
	public void export(HttpServletResponse response, SalesTbl entity) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		Paragraph header = new Paragraph("MEN'S CORNER", font);
		header.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(header);

		font.setSize(11);
		font.setColor(Color.BLACK);
		Paragraph shopDetails = new Paragraph(
				"Edavanna, Malappuram\nGSTIN : 32BQZPA6789G1Z9\nPh : 0483-123456, 9876543210, ", font);
		shopDetails.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(shopDetails);

		font.setColor(Color.BLUE);
		font.setSize(14);
		Paragraph p = new Paragraph("Invoice", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		String invoiceNo = String.valueOf(entity.getSaleID());
		String customerName = entity.getCustomerName();
		LocalDateTime dateTime = entity.getWefDate();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = dateTime.format(format);
		String parts[] = formatDateTime.split(" ");
		font.setSize(10);
		font.setColor(Color.BLACK);
		Paragraph saleDetails = new Paragraph("\nInvoice No : " + invoiceNo + "\nDate : " + parts[0] + "\nTime : "
				+ parts[1] + "\nTo : " + customerName, font);
		saleDetails.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(saleDetails);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3.5f, 1.5f, 1.5f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);
		document.add(table);

		String netTotal = String.valueOf(entity.getTotalAmount());
		Integer grandTotal = Math.round(entity.getTotalAmount());
		Paragraph amountDetails = new Paragraph("Total Qty : " + String.valueOf(totalQty) + "\nNetTotal : " + netTotal
				+ "\nGRAND TOTAL : " + String.valueOf(grandTotal), font);
		amountDetails.setAlignment(Paragraph.ALIGN_RIGHT);
		amountDetails.setSpacingAfter(5);
		document.add(amountDetails);

		font.setSize(8);
		Paragraph footer = new Paragraph("For : MEN'S CORNER", font);
		footer.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(footer);

		document.close();
	}
}
