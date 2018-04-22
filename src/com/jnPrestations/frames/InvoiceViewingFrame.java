package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jnPrestations.manages.ManageInvoice;
import com.jnPrestations.manages.ManageMyBusiness;
import com.jnPrestations.miscellaneous.MyTableModel;
import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.MyBusiness;
import com.jnPrestations.beans.Property;




public class InvoiceViewingFrame extends TemplateFrame {


	private JTextArea myBusinessTextAreaHaut = new JTextArea();	

	private JTextArea estateAgTextArea = new JTextArea();
	private JLabel dateLabel = new JLabel();
	private JLabel billNumberLabel = new JLabel();
	private JLabel vatLabel = new JLabel();
	private MyTableModel model;
	private JTable invoiceTable;
	private JScrollPane scrollPane ;
	private JLabel aggregateLabel = new JLabel("TOTAL"); 
	private JTextField aggregateField = new JTextField();
	private JButton saveButton  = new JButton("Enregister", new ImageIcon("images\\saveImg.png"));
	private JFrame frame;
	private Boolean newInvoice = true;
	
	private MyBusiness myBusiness;
	private Property property;
	private EstateAgency estateAg;
	
	private String filePath;

	private int monthInvoice = 0;
	private String monthInvoiceToString = "";
	private int yearInvoice = 0;
	private String monthStart = "01";
	private int monthEnd = 0;
	private String period;
	private String dateOfIssueToString;
	private String dayOut;

	public InvoiceViewingFrame(MyBusiness myBusiness, EstateAgency estateAg, Property property){
		frame    = this;
		//setTitle("Visualisation de la facture");
		setLayout(null);
		this.myBusiness = myBusiness;
		this.estateAg = estateAg;
		this.property = property;
		
		myBusinessTextAreaHaut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		myBusinessTextAreaHaut.setForeground(Color.blue);
		myBusinessTextAreaHaut.setText(myBusiness.getLegalName()+"\n\n"+ myBusiness.getAddress()+"\n" +
				myBusiness.getZipCode()+ " " + myBusiness.getTown()+ "\nTél: "+
				myBusiness.getPhoneNumber() + "\nEmail: "+ myBusiness.getEmail() + "\n"+
				myBusiness.getSiren()
				);
		myBusinessTextAreaHaut.setEditable(false);
		myBusinessTextAreaHaut.setBounds(20, 20, 220, 120);
		pan.add(myBusinessTextAreaHaut);

		estateAgTextArea.setText("Client:      " + estateAg.getLegalName()+"\n\n"+ "Adresse:   "+ estateAg.getAddress()+"\n"+
				"                 " + estateAg.getZipCode() + " " + estateAg.getTown());
		estateAgTextArea.setBounds(450, 40, 220, 100 );
		Font police = new Font("Arial", Font.BOLD, 13);
		estateAgTextArea.setFont(police);
		pan.add(estateAgTextArea);

		Locale currentLocale = new Locale("fr", "FR");
		Date today;
		DateFormat dateFormatter;
		dateFormatter  = DateFormat.getDateInstance(DateFormat.SHORT, currentLocale);
		today = new Date();
		dayOut = dateFormatter.format(today);

		dateLabel.setText("Date:             " + dayOut );
		dateLabel.setBounds(450, 140, 150, 30);
		pan.add(dateLabel);
		
		dateOfIssueToString = dayOut;
		
		billNumberLabel.setText("Facture n°  "+ myBusiness.getBillNumber()+ 
				"                                                   " + 
				"Résidence:  "+ property.getAddress());
		billNumberLabel.setBounds(30, 250, 2000, 30);
		pan.add(billNumberLabel);


		Object [][] data = new Object[1][5];
		String [] title = {"Période", "Désignation", "Quantité", "Tarif unitaire", "Total"}; 
		LocalDate today_s_date = LocalDate.now();
		monthInvoice = today_s_date.getMonthValue() - 1;
		yearInvoice = today_s_date.getYear();

		if (monthInvoice == 0){
			monthInvoice = 12;
			yearInvoice--;
			monthEnd = 31;
		}
		else if (monthInvoice == 2){
			if(today_s_date.isLeapYear()){
				monthEnd = 29;
			}
			else{
				monthEnd = 28;
			}
		}
		else if(monthInvoice % 2 == 0){
			if(monthInvoice >= 8){
				monthEnd = 31;
			}
			else{
				monthEnd = 30;
			}	
		}
		else {
			if(monthInvoice > 8){
				monthEnd = 30;
			}
			else{
				monthEnd = 31;
			}
		}

		monthInvoiceToString = ""+monthInvoice;

		if(monthInvoice < 10){
			monthInvoiceToString ="0"+monthInvoice;
		}

		data[0][0] =  monthStart + "-" + monthEnd + "/" + monthInvoiceToString + "/" + yearInvoice;
		data[0][1] = property.getService();
		data[0][2] = 1;
		data[0][3] = property.getBasicFee() + " €";
		data[0][4] = property.getBasicFee() + " €";
		

		model = new MyTableModel(data, title);
		invoiceTable = new JTable(model);
		
		period = (String) invoiceTable.getValueAt(0, 0);
		
		TableColumn column = null;
		column = invoiceTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(170);

		
		invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(40);


		scrollPane = new JScrollPane(invoiceTable);
		scrollPane.setBounds(30, 300, 600, 55);
		pan.add(scrollPane);

		aggregateLabel.setBounds(410, 350,110, 30);
		aggregateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		pan.add(aggregateLabel);

		aggregateField.setText(property.getBasicFee() + " €");
		aggregateField.setBounds(520, 352, 110, 28);
		aggregateField.setBorder(BorderFactory.createLineBorder(Color.black));
		pan.add(aggregateField);

		DefaultTableCellRenderer dtcr_center = new DefaultTableCellRenderer();
		dtcr_center.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 5; i++){
			invoiceTable.getColumn(title[i]).setCellRenderer(dtcr_center);
		}
		aggregateLabel.setHorizontalAlignment(JLabel.CENTER);
		aggregateField.setHorizontalAlignment(JTextField.CENTER);
		
		vatLabel.setText("tva non applicable article 293b du code général des impôts");
		vatLabel.setBounds(300, 400, 400, 20);
		pan.add(vatLabel);
		
		saveButton.setHorizontalTextPosition(JButton.LEFT);
		saveButton.setBounds(530, 450, 125, 30);
		saveButton.addActionListener(new listenSaveButton());
		pan.add(saveButton);
		
		filePath = "C:\\Users\\lahad\\Desktop\\Données\\Docs\\JN PRESTATIONS\\factures\\fac n° " + myBusiness.getBillNumber() + ".pdf";
		pan.setBackground(Color.white);
		setContentPane(pan);
		setVisible(true);
	}

	class listenSaveButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JOptionPane jop = new JOptionPane();
			if (newInvoice){
				ManageMyBusiness mmb =   new ManageMyBusiness();
				mmb.incrementBillNumber();;
				newInvoice = false;
				
				// save the bill in database
				ManageInvoice mi = new ManageInvoice();
				mi.register(myBusiness.getBillNumber(), dateOfIssueToString, period, "unsettled", property);
				
				// create the pdf document
				builtDpfDocument();
				
				jop.showMessageDialog(frame, "Facture enregistrée dans le dossier \"mesFactures\"!", "Information !" , JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				jop.showMessageDialog(frame, "Facture déjà enregistrée !", "Information !" , JOptionPane.ERROR_MESSAGE);

			}
			
		}

	}
	public void builtDpfDocument(){
		Document document = new Document();
		try{
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			
			document.add(new Paragraph
					(myBusiness.getLegalName() + "                                                                         " + "Client:  "+ estateAg.getLegalName() + "\n"+
				"\n"+ myBusiness.getAddress()   + "                                                   	 " + "Adresse:  "+ estateAg.getAddress() + "\n" +
				 myBusiness.getZipCode() + "  "+ myBusiness.getTown() + "                                                                                      " + estateAg.getZipCode() + "   " + estateAg.getTown() + "\n" +
				 "Tél: " + myBusiness.getPhoneNumber() + "\n" +
				 "Email: " + myBusiness.getEmail() +"\n" +
				 myBusiness.getSiren() + "                                                            " + "Date: " +  dayOut + "\n\n\n\n\n\n\n" + 
				 "Facture n° " + myBusiness.getBillNumber() + "                                        " + "Résidence: " + property.getAddress() +"\n\n\n\n\n"));
			
			
			document.add((createPdfTable()));
	
	
			document.add(new Paragraph());
			document.add(new Paragraph("\n\n\n                                                   tva non applicable article 293b du code général des impôts\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
			
			com.itextpdf.text.Font blueFont ;
			blueFont = new com.itextpdf.text.Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.BLUE);
			Chunk blueText = new Chunk("                                                                                          " + myBusiness.getLegalName() + "\n"+
			                           "                                                               " + myBusiness.getAddress() + " / " + myBusiness.getZipCode() + " - " + myBusiness.getTown() + "\n" +
					                   "                                                                     " + "Tel: " + myBusiness.getPhoneNumber() + " / Email: " + myBusiness.getEmail() + "\n"+
			                           "                                                                                      " + myBusiness.getSiren(), 
			                           blueFont);
			Paragraph parag = new Paragraph(blueText);
			document.add(parag);
			
			
		
		}
		catch(DocumentException de){
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		document.close();
	}
	public PdfPCell getCell (String text, int alignment){
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}
	public  PdfPTable createPdfTable(){
		
		float [] columnWidths = {4,8,2,4,3};
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);
		PdfPCell cell;
		
		cell = new PdfPCell(new Phrase("Période"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Désignation"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("Quantité"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Tarif unitaire"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	
		cell = new PdfPCell(new Phrase("Total"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(monthStart + "-" + monthEnd + "/" + monthInvoiceToString + "/" + yearInvoice));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(property.getService()));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("1"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(property.getBasicFee() + " €"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(property.getBasicFee() + " €"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.setBackgroundColor(BaseColor.GRAY);
		cell.setColspan(5);
		table.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Total"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(property.getBasicFee() + " €"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
				
		return table;	
	}
}
