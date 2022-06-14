package fr.eql.ai111.groupe5.projet1.methodsback;


import com.adobe.acrobat.Viewer;
import com.aspose.pdf.IDocument;
import com.aspose.pdf.facades.PdfViewer;
import com.qoppa.pdfViewer.PDFViewerBean;
import javafx.scene.layout.VBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;

public class PDFReader{

    public void openPdfUser() throws Exception {
//        if (Desktop.isDesktopSupported()) {
            File myFile = new File("C://FolderProjet/DocumentationAideUser.pdf");
//            try {
//                Desktop.getDesktop().open(myFile);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

//        Viewer viewer = new Viewer();
//        Frame frame = new Frame();
//        frame.add(viewer, BorderLayout.CENTER);
//        frame.setSize(new Dimension(1000,1000));
//        FileInputStream fis = new FileInputStream("C:/Users/Formation/Desktop/Fiche_de_communication_CDA_2018.pdf");
//        viewer.setDocumentInputStream(fis);
//        viewer.activate();
//        frame.setVisible(true);
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                frame.setVisible(false);
//            }
//        });

        PDFViewerBean bean = new PDFViewerBean();
        Frame frame = new Frame();
        bean.loadPDF("C://FolderProjet/DocumentationAideUser.pdf");
        frame.add(bean, BorderLayout.CENTER);
        bean.getToolbar().setVisible (false);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,1000));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
        }

    public void openPdfAdmin() throws Exception {
//        if (Desktop.isDesktopSupported()) {
        File myFile = new File("C://FolderProjet/DocumentationAideAdmin.pdf");
//            try {
//                Desktop.getDesktop().open(myFile);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

//        Viewer viewer = new Viewer();
//        Frame frame = new Frame();
//        frame.add(viewer, BorderLayout.CENTER);
//        frame.setSize(new Dimension(1000,1000));
//        FileInputStream fis = new FileInputStream("C:/Users/Formation/Desktop/Fiche_de_communication_CDA_2018.pdf");
//        viewer.setDocumentInputStream(fis);
//        viewer.activate();
//        frame.setVisible(true);
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                frame.setVisible(false);
//            }
//        });

        PDFViewerBean bean = new PDFViewerBean();
        Frame frame = new Frame();
        bean.loadPDF("C://FolderProjet/DocumentationAideAdmin.pdf");
        frame.add(bean, BorderLayout.CENTER);
        bean.getToolbar().setVisible (false);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,1000));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
    }
    public void openPdfSuperAdmin() throws Exception {
//        if (Desktop.isDesktopSupported()) {
        File myFile = new File("C://FolderProjet/DocumentationAideUser.pdf");
//            try {
//                Desktop.getDesktop().open(myFile);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

//        Viewer viewer = new Viewer();
//        Frame frame = new Frame();
//        frame.add(viewer, BorderLayout.CENTER);
//        frame.setSize(new Dimension(1000,1000));
//        FileInputStream fis = new FileInputStream("C:/Users/Formation/Desktop/Fiche_de_communication_CDA_2018.pdf");
//        viewer.setDocumentInputStream(fis);
//        viewer.activate();
//        frame.setVisible(true);
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                frame.setVisible(false);
//            }
//        });

        PDFViewerBean bean = new PDFViewerBean();
        Frame frame = new Frame();
        bean.loadPDF("C://FolderProjet/Fiche_de_communication_CDA_2018.pdf");
        frame.add(bean, BorderLayout.CENTER);
        bean.getToolbar().setVisible (false);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,1000));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
    }
    }
