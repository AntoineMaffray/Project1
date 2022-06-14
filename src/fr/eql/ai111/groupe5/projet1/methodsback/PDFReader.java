package fr.eql.ai111.groupe5.projet1.methodsback;
import com.qoppa.pdfViewer.PDFViewerBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class PDFReader{

    public void openPdfUser() throws Exception {

        PDFViewerBean bean = new PDFViewerBean();
        Frame frame = new Frame();
        bean.loadPDF("C://FolderProjet/DocumentationAideUser.pdf");
        frame.add(bean, BorderLayout.CENTER);
        bean.getToolbar().setVisible (false);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,1000));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
        }

    public void openPdfAdmin() throws Exception {

        PDFViewerBean bean = new PDFViewerBean();
        Frame frame = new Frame();
        bean.loadPDF("C://FolderProjet/DocumentationAideAdmin.pdf");
        frame.add(bean, BorderLayout.CENTER);
        bean.getToolbar().setVisible (false);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,1000));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
    }
    public void openPdfSuperAdmin() throws Exception {

        PDFViewerBean bean = new PDFViewerBean();
        Frame frame = new Frame();
        bean.loadPDF("C://FolderProjet/DocumentationAideSuperAdmin.pdf");
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
