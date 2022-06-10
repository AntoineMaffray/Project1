package fr.eql.ai111.groupe5.projet1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eql.ai111.groupe5.projet1.methodsback.Arbre;
import fr.eql.ai111.groupe5.projet1.methodsback.Stagiaire;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class TextExportPdfbox {
    public static void main(String[] args) throws IOException{

        List<Stagiaire> stagiaires = new ArrayList<>();
        Arbre arbre = new Arbre();
        stagiaires = arbre.arbreParcours();
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
            for (Stagiaire s : stagiaires) {
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
            File file = new File("C:/FolderProjet/test.pdf");
            if (!file.exists());
            doc.save(file);
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