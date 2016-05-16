package com.sf.kycmanager.portlet.controller.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.sf.biocapture.entity.Demographic;
import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Cyprian on 5/11/2016.
 */
@Component
public class PdfBuilder extends AbstractITextPdfView {

//    @Column(name = "FIRSTNAME")
//    private String firstName;
//    @Column(name = "SURNAME")
//    private String surname;
//    @Column(name = "OTHERNAME")
//    private String otherName;
//    @Column(name = "BIRTHDAY")
//    private Timestamp birthday;
//    @Column(name = "GENDER")
//    private String gender;
//    @Column(name = "NATIONALITY")
//    private String nationality;
//    @Column(name = "STATEOFORIGIN")
//    private String stateOfOrigin;
//    @Column(name = "OCCUPATION")
//    private String occupation;
//    @Column(name = "SUBSCRIBERTYPE")
//    private String subscriberType;
//    @Column(name = "REGISTRATIONLGA")
//    private String registrationLGA;
//    @Column(name = "RESIDENTIALADDRESS")
//    private String residentialAddress;
//    @Column(name = "RESIDENTIALADDRESSLGA")
//    private String residentialAddressLGA;
//    @Column(name = "RESIDENTIALADDRESSSTATE")
//    private String residentialAddressState;
//    @Column(name = "EMAIL")
//    private String email;
//    @Column(name = "DDA2")
//    private String dda2;
//    @Column(name = "DDA3")
//    private String dda3;
//    @Column(name = "DDA1")
//    private String dda1;
//    @Column(name = "REGISTRATIONTYPE")
//    private String registrationType;
//    @Column(name = "COMPANYID")
//    private String companyId;
//    @Column(name = "COMPANYNAME")
//    private String companyName;
//    @Column(name = "RESIDENT")
//    private String resident;
//    @Column(name = "POSTALCODE")
//    private String postalCode;
//    @Column(name = "COMPANYADDRESS")
//    private String companyAddress;
//    @Column(name = "COMPANYADDRESSLGA")
//    private String companyAddressLGA;
//    @Column(name = "COMPANYADDRESSSTATE")
//    private String companyAddressState;
//    @Column(name = "COMPANYADDRESSPOSTALCODE")
//    private String companyAddressPostalCode;
//    @Column(name = "BASICDATAID")
//    private Long basicData;
//    @Column(name = "PHONE_NUMBER")
//    private String phoneNumber;
//    @Column(name = "UNIQUE_ID")
//    private String uniqueId;
//    @Column(name = "MOTHERSMAIDENNAME")
//    private String mothersMaidenName;
//    @Column(name = "CLIENTID")
//    private String clientId;
//    @Column(name = "MAC_ADDRESS")
//    private String macAddress;
//    @Column(name = "RECEIPT_TIMESTAMP")
//    private Timestamp receiptTimeStamp;
//    @Column(name = "EXPIRY_DATE")
//    private Timestamp expiryDate;
//    @Column(name = "COUNTRYISSUEDCODE")
//    private String countryIssued;
//    @Column(name = "PASSPORT_NUMBER")
//    private String passportNumber;
//    @Column(name = "CONFIRMATION_STATUS")
//    private String confirmationStatus;
//    @Column(name = "CONFIRMATION_TIMESTAMP")
//    private String confirmationTimeStamp;
//    @Column(name = "DDA20")
//    private String dda20;
//    @Column(name = "SIM_SERIAL")
//    private String msisdnSerial;


    private DemographicsService demographicsService;
    private Long id;

    public PdfBuilder(Demographic demographic) {
        super(demographic);
    }

    public void init(Long id) {
        this.id = id;
    }

    private PdfPTable tableRows(PdfPTable table)
            throws IOException, BadElementException {

        String s = demographicsService.getDemographicImage(id).get();
        java.util.List<Map<String, String>> wsqImages = demographicsService.getDemographicPrints(id);
        table.addCell(Image.getInstance(Base64.decode(s)));
//        Finger Images
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.CYAN);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // write table header
        cell.setPhrase(new Phrase("LEFT THUMB", font));
        table.addCell(cell);
// write table header
        cell.setPhrase(new Phrase("LEFT INDEX", font));
        table.addCell(cell);
// write table header
        cell.setPhrase(new Phrase("RIGHT THUMB", font));
        table.addCell(cell);
// write table header
        cell.setPhrase(new Phrase("RIGHT INDEX", font));
        table.addCell(cell);

        for (Map<String, String> wsq : wsqImages) {
            table.addCell(Image.getInstance(Base64.decode(wsq.get("data"))));
        }

        return table;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container

        doc.add(new Paragraph("Demographic Information"));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);

        // define font for table header row
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(BaseColor.WHITE);
//
//        // define table header cell
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(BaseColor.BLUE);
//        cell.setPadding(5);
//
//        // write table header
//        cell.setPhrase(new Phrase("Book Title", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Author", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("ISBN", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Published Date", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Price", font));
//        table.addCell(cell);
//
//
//        if(demographic !=null){
//
//        }
//        // write table row data
//        for (Book aBook : demographic) {
//            table.addCell(aBook.getTitle());
//            table.addCell(aBook.getAuthor());
//            table.addCell(aBook.getIsbn());
//            table.addCell(aBook.getPublishedDate());
//            table.addCell(String.valueOf(aBook.getPrice()));
//        }

        doc.add(table);

    }
}
