package fr.eql.ai111.groupe5.projet1;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;


public class TextExportItext {
    public static void main(String[] args) {


        String path = "C:/JavaPdf/addingList.pdf";
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
        PdfWriter pdfwriter = PdfWriter.getInstance(path, outputStream);

        PdfDocument pdfdocument = new PdfDocument(pdfwriter);

        Document document = new Document(pdfdocument);

        // Creating a list
        List list = new ArrayList();

        // Adding contents to the list
        list.add("geekforgeeks");
        list.add("helps");
        list.add("to");
        list.add("master");
        list.add("DSA");

        // Adding list to the document
        document.add(list);

        // Closing the document
        document.close();

        // Java program to add a list in a PDF

    }
}



public class AddList {
    public static void main(String args[])
    {
        try {
            // path where the pdf is to be created.
            String path = "C:/JavaPdf/addingList.pdf";
            PdfWriter pdfwriter = new PdfWriter(path);

            // Creating a PdfDocument object.
            // passing PdfWriter object constructor of
            // pdfDocument.
            PdfDocument pdfdocument
                    = new PdfDocument(pdfwriter);

            // Creating a Document and passing pdfDocument
            // object
            Document document = new Document(pdfdocument);

            // Creating a list
            List<String> list = new ArrayList();

            // Adding contents to the list
            list.add("geekforgeeks");
            list.add("helps");
            list.add("to");
            list.add("master");
            list.add("DSA");

            // Adding list to the document
            document.add(list);

            // Closing the document
            document.close();
            System.out.println(
                    "List has been successfully added to the file :"
                            + path);
        }
        catch (Exception e) {
            System.out.println(
                    "failed to add the list to file due to "
                            + e);
        }
    }
}
