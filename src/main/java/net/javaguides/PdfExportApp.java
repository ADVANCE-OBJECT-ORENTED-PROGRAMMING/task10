package net.javaguides;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PdfExportApp extends JFrame {
    JTable table;
    JButton loadBtn, exportBtn;

    public PdfExportApp() {
        setTitle("Database Table Exporter");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane sp = new JScrollPane(table);

        loadBtn = new JButton("Load Data");
        exportBtn = new JButton("Export to PDF");

        JPanel topPanel = new JPanel();
        topPanel.add(loadBtn);
        topPanel.add(exportBtn);

        add(topPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);

        loadBtn.addActionListener(this::loadDataFromDB);
        exportBtn.addActionListener(this::exportPDF);
        setVisible(true);
    }

    private void loadDataFromDB(ActionEvent e) {
        try {
            String url = "jdbc:mysql://localhost:3306/task10";
            String user = "root";
            String pass = "witty";

            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, name, email FROM employees");

            table.setModel(buildTableModel(rs));
            conn.close();
            JOptionPane.showMessageDialog(this, "Data Loaded Successfully!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    public static javax.swing.table.TableModel buildTableModel(ResultSet rs) throws Exception {

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        java.util.Vector<String> columnNames = new java.util.Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(meta.getColumnName(i));
        }

        java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<>();
        while (rs.next()) {
            java.util.Vector<Object> row = new java.util.Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        return new javax.swing.table.DefaultTableModel(data, columnNames);
    }
    private void exportPDF(ActionEvent e) {
        try {
            // Check empty table
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty! Nothing to export.");
                return;
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save PDF");
            chooser.setSelectedFile(new File("report.pdf"));

            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
                return;

            File file = chooser.getSelectedFile();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Employee Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Timestamp
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Paragraph timestamp = new Paragraph("Generated on: " + date);
            timestamp.setAlignment(Element.ALIGN_CENTER);
            timestamp.setSpacingAfter(20);
            document.add(timestamp);

            // Table
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Column Headers
            TableModel model = table.getModel();
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            for (int i = 0; i < model.getColumnCount(); i++) {
                PdfPCell header = new PdfPCell(new Phrase(model.getColumnName(i), headerFont));
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(header);
            }

            // Table Data
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    pdfTable.addCell(new Phrase(model.getValueAt(r, c).toString()));
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "PDF Exported Successfully!");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "Cannot write to file! Check permissions.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "An error occurred: " + ex.getMessage(),
                    "Unknown Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
