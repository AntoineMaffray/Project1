package fr.eql.ai111.groupe5.projet1;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import java.io.IOException;
import java.util.ArrayList;

public class TextExport {
    public static void main(String[] args) throws IOException {

        ArrayList<String> testeuse = new ArrayList<>();

        testeuse.add("Bonjour" + "\r\n");
        testeuse.add("Aurevoir");
        System.out.println(testeuse);

        try{
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);


        contentStream.beginText();
        contentStream.showText("hello");
        contentStream.endText();
        contentStream.close();

        document.save("C:/FolderProjet/pdftest.pdf");
        document.close();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
