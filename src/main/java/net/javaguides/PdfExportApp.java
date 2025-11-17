package net.javaguides;

import javax.swing.*;
import java.awt.*;

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

        setVisible(true);
    }

}
