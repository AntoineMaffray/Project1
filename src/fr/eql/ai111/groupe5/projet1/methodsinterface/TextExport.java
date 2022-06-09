








//package fr.eql.ai111.groupe5.projet1;
//
//import java.io.File;
//import java.io.IOException;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
//
//public class TextExport {
//    public static void main(String[] args) throws IOException{
//        File file = new File("C:/FolderProjet/test.pdf");
//        PDDocument document = new PDDocument();
//        document.save(file);
//        PDPage page = document.getPage(0);
//        PDType1Font pdType1Font = new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD);
//        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND,true,true);
//        contentStream.beginText();
//
//        //Setting the font
//        contentStream.setFont(pdType1Font, 12);
//
//        //Setting the text position
//        contentStream.newLineAtOffset(25, 500);
//
//        String text = "This message is writtern to the pdf file.";
//        contentStream.showText(text);
//        contentStream.endText();
//        contentStream.close();
//        // Saving file after writing
//        document.save(new File("test.pdf"));
//        document.close();
//    }
//}
//

//    ArrayList<String> testeuse = new ArrayList<>();
//
//        testeuse.add("Bonjour" + "\r\n");
//                testeuse.add("Aurevoir");
//                System.out.println(testeuse);
//