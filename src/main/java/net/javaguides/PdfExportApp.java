package net.javaguides;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

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

}
