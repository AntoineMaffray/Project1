package fr.eql.ai111.groupe5.projet1;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Methods;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class TextExportPdfbox {
    public static void main(String[] args) throws IOException{

        List<Stagiaire> testeuse = new ArrayList<>();
        Arbre arbre = new Arbre();
        testeuse = arbre.arbreParcours();
        int compteur = 10;
        PDDocument doc = null;
        try
        {
            doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            PDImageXObject image = PDImageXObject.createFromFile("C:/FolderProjet/eql.png", new PDDocument());
            PDImageXObject image2 = PDImageXObject.createFromFile("C:/FolderProjet/eqlfil.png", new PDDocument());
            contentStream.drawImage(image, 30, 580);
            PDFont pdfFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            // taille des caractères
            float fontSize = 12;
            // interligne
            float leading = 1.5f * fontSize;
            PDRectangle mediabox = page.getMediaBox();
            // marge gauche
            float margin = 20;
            String text = "";
            StringBuffer stringBuffer = new StringBuffer();
            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(30, 550);
            for (Stagiaire s : testeuse) {
                int nspace1 = 20 - (s.getSurname().trim().length());
                String space1 = "";
                text = "";
                stringBuffer.append(s.getSurname().trim()+s.getName().trim()+ s.getDept().trim()+ s.getPromo().trim()+ s.getYear().trim());
                text = "Nom: " + s.getSurname().trim()+ "  " +
                        "Prénom: " + s.getName().trim()+ "  " +
                        "Département: " + s.getDept().trim()+ "  " +
                        "Promotion: " + s.getPromo().trim()+ "  " +
                        "Année: " + s.getYear().trim();
                compteur = compteur+1;
                if (compteur > 40) {
                    page = new PDPage();
                    doc.addPage(page);
                    contentStream.endText();
                    contentStream.close();
                    contentStream = new PDPageContentStream(doc, page);
                    contentStream.drawImage(image2, 150, 150);
                    contentStream.beginText();
                    contentStream.setFont(pdfFont, fontSize);
                    contentStream.newLineAtOffset(20, 750);
                    compteur = 0;
                }
                contentStream.showText(text);
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.endText();
            contentStream.close();
            doc.save(new File("C:/FolderProjet/test.pdf"));
        }
        finally
        {
            if (doc != null)
            {
                doc.close();
            }
        }

    }
}

//                for (int i = 0; i < nspace1; i++) {
//                    space1 += " ";
//                }
//                int nspace2 = 15 - (s.getName().trim().length());
//                String space2 = "";
//                for (int i = 0; i < nspace2; i++) {
//                    space2 += " ";
//                }
//                int nspace3 = 5 - (s.getDept().trim().length());
//                String space3 = "";
//                for (int i = 0; i < nspace1; i++) {
//                    space3 += " ";
//                }
//                int nspace4 = 10 - (s.getPromo().trim().length());
//                String space4 = "";
//                for (int i = 0; i < nspace1; i++) {
//                    space4 += " ";
//                }
//                int spaceIndex = text.indexOf(' ', lastSpace + 1);
//                if (spaceIndex < 0)
//                    spaceIndex = text.length();
//                String subString = text.substring(0, spaceIndex);
//                float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
//                System.out.printf("'%s' - %f of %f\n", subString, size, width);
//                if (size > width)
//                {
//                    if (lastSpace < 0)
//                        lastSpace = spaceIndex;
//                    subString = text.substring(0, lastSpace);
//                    lines.add(subString);
//                    text = text.substring(lastSpace).trim();
//                    System.out.printf("'%s' is line\n", subString);
//                    lastSpace = -1;
//                }
//                else if (spaceIndex == text.length())
//                {
//                    lines.add(text);
//                    System.out.printf("'%s' is line\n", text);
//                    text = "";
//                }
//                else
//                {
//                    lastSpace = spaceIndex;
//                }

//            contentStream.beginText();
//            contentStream.setFont(pdfFont, fontSize);
//            contentStream.newLineAtOffset(startX, startY);
//            for (String line: lines)
//            {
//                contentStream.showText(line);
//                contentStream.newLineAtOffset(0, -leading);
//            }



//    File file = new File("C:/FolderProjet/test.pdf");
//    PDDocument document = new PDDocument();
//    PDPage page = new PDPage();
//    PDType1Font pdType1Font = new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD);
//    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND,true,true);
//        contentStream.beginText();
//
//                page.getActions();
//
//                //Setting the font
//                contentStream.setFont(pdType1Font, 12);
//
//                //Setting the text position
//                contentStream.newLineAtOffset(25, 500);
//
//                String text = "This message is writtern to the pdf file.";
//                contentStream.showText(text);
//                contentStream.endText();
//                contentStream.close();
//                // Saving file after writing
//                document.save("C:/FolderProjet/test.pdf");
//                document.close();