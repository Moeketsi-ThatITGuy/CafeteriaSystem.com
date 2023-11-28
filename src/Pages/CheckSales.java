package Pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckSales extends JFrame {


    private final JTextField  searchField;
    private final JTextField fromDateField;
    private final JTextField toDateField;
    private final JTextField totItemsCostField;
    private final JTextField totItemsField;
    private final JTextField totItemsProfitField;
    private final JTextField itemsCategory;

    JTextField comboBox;


    int i , costPrice , totalProfit;
    JButton searchbutton,  removeAllButton, backButton ;

    public static JTable table;

    DefaultTableModel tableModel;


    CheckSales() {

        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 270, 1000, 120);
        panel1.setBackground(Color.lightGray);
        panel1.setLayout(null);


        JPanel panel2 = new JPanel(null);
        panel2.setBounds(0, 0, 1000, 70);
        panel2.setBackground(Color.lightGray);


        searchField = new JTextField();
        searchField.setBounds(70, 25, 100, 20);
        panel2.add(searchField);

        searchbutton = new JButton("Search");
        searchbutton.setBounds(110, 25, 75, 20);
        searchbutton.addActionListener(this::searchButtonAction);
        panel2.add(searchbutton);


        JLabel itemCategory = new JLabel();
        itemCategory.setBounds(180, 25, 120, 20);
        itemCategory.setText("Category : ");
        itemCategory.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel2.add(itemCategory);


        itemsCategory = new JTextField();
        itemsCategory.setBounds(290, 25, 100, 20);
        panel2.add(itemsCategory);


        JLabel fromDate = new JLabel("From :");
        fromDate.setBounds(410, 25, 100, 20);
        //fromDate.setText("Item : ");
        fromDate.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel2.add(fromDate);


        fromDateField = new JTextField();
        fromDateField.setBounds(480, 25, 100, 20);
        panel2.add(fromDateField);

        JLabel toDate = new JLabel();
        toDate.setBounds(600, 25, 100, 20);
        toDate.setText("To : ");
        toDate.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel2.add(toDate);


        toDateField = new JTextField();
        toDateField.setBounds(630, 25, 100, 20);
        panel2.add(toDateField);

        JLabel itemName = new JLabel();
        itemName.setBounds(7, 25, 100, 20);
        itemName.setText("Item :");
        itemName.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel2.add(itemName);


        searchbutton.setBounds(750, 25, 100, 20);


        removeAllButton = new JButton("Remove All");
        removeAllButton.setBounds(760, 100, 100, 20);
        removeAllButton.addActionListener(this::removeAllBUtton);

        backButton = new JButton("back");
        backButton.setBounds(760, 160, 75, 20);
        backButton.addActionListener(this::backButtonListener);


        String[] columns = {"Item name", "Item Cost Price ", "Item Price", "Item barcode", "Item Type", "Item Date"};
        tableModel = new DefaultTableModel(null, columns);
         table = new JTable(tableModel);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 73, 750, 195);

        JLabel totItemsLabel = new JLabel("Total items: ");
        totItemsLabel.setBounds(10, 50, 120, 40);
        totItemsLabel.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel1.add(totItemsLabel);

        totItemsField = new JTextField();
        totItemsField.setBounds(120, 50, 120, 40);
        totItemsField.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel1.add(totItemsField);


        JLabel totItemsCostLabel = new JLabel("Total Cost: ");
        totItemsCostLabel.setBounds(250, 50, 120, 40);
        totItemsCostLabel.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel1.add(totItemsCostLabel);

        totItemsCostField = new JTextField();
        totItemsCostField.setBounds(360, 50, 120, 40);
        totItemsCostField.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel1.add(totItemsCostField);


        JLabel totItemsProfit = new JLabel("Total Profit: ");
        totItemsProfit.setBounds(490, 50, 120, 40);
        totItemsProfit.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel1.add(totItemsProfit);

        totItemsProfitField = new JTextField();
        totItemsProfitField.setBounds(610, 50, 120, 40);
        totItemsProfitField.setFont(new Font("Ariel Black", Font.BOLD, 20));
        panel1.add(totItemsProfitField);


        this.setSize(870, 420);
        this.setResizable(false);
        this.setLocation(300, 100);
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(panel1);
        this.add(panel2);
        this.add(pane);
        this.add(removeAllButton);
        this.add(backButton);


    }

    private void backButtonListener(ActionEvent e) {

        if (e.getSource() == backButton) {

            this.dispose();
            new TransactionsPage();
        }
    }

    private void searchButtonAction(ActionEvent e) {

        String searchItemName = searchField.getText();
        String searchFromDate = fromDateField.getText();
        String searchtoDate = toDateField.getText();
        String category = itemsCategory.getText();
        String sName;
        String sPrice;
        String sBarcode;
        String sCostPrice;
        String getTypeOfItem;

         if (!searchItemName.isEmpty() && !searchFromDate.isEmpty() && !searchtoDate.isEmpty()) {
            java.sql.Date sqlDate;

            Connection con = ImplementedDbConnections.getConnection();
            try {
                assert con != null;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from sales where item_name = '" + searchItemName + "' and item_date between '" + searchFromDate + "' and '" + searchtoDate + "'");

                while (rs.next()) {
                    sName = rs.getString("item_name");
                    sCostPrice = rs.getString("item_price_cost");
                    sPrice = rs.getString("item_price");
                    sBarcode = rs.getString("item_barcode");
                    getTypeOfItem = rs.getString("item_type");
                    sqlDate = rs.getDate("item_date");

                    String[] info = {sName, sCostPrice, sPrice, sBarcode, getTypeOfItem, sqlDate.toString()};
                    tableModel.addRow(info);
                    i++;
                    StringBuilder sb = new StringBuilder(sCostPrice);
                    String newString = sb.deleteCharAt(0).toString();
                    sb = new StringBuilder(sPrice);
                    costPrice = Integer.parseInt(newString) + costPrice;
                    newString = sb.deleteCharAt(0).toString();
                    totItemsCostField.setText("R" + costPrice);
                    totalProfit = Integer.parseInt(newString) + totalProfit;
                    totItemsProfitField.setText("R " + totalProfit);


                }
            } catch (Exception sql) {
                JOptionPane.showMessageDialog(this, "Couldnt populate table", "Error", JOptionPane.ERROR_MESSAGE);
            }
            totItemsField.setText(Integer.toString(i));
        }
        else if (!searchItemName.isEmpty()) {
            java.sql.Date sqlDate;
            searchItemName = searchField.getText();
            Connection con = ImplementedDbConnections.getConnection();
            try {
                assert con != null;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from sales where item_name = '" + searchItemName + "' ");

                while (rs.next()) {
                    sName = rs.getString("item_name");
                    sCostPrice = rs.getString("item_price_cost");
                    sPrice = rs.getString("item_price");
                    sBarcode = rs.getString("item_barcode");
                    getTypeOfItem = rs.getString("item_type");
                    sqlDate = rs.getDate("item_date");

                    String[] info = {sName, sCostPrice, sPrice, sBarcode, getTypeOfItem, sqlDate.toString()};
                    tableModel.addRow(info);
                    i++;
                    StringBuilder sb = new StringBuilder(sCostPrice);
                    String newString = sb.deleteCharAt(0).toString();
                    sb = new StringBuilder(sPrice);
                    costPrice = Integer.parseInt(newString) + costPrice;
                    newString = sb.deleteCharAt(0).toString();
                    totItemsCostField.setText("R" + costPrice);
                    totalProfit = Integer.parseInt(newString) + totalProfit;
                    totItemsProfitField.setText("R " + totalProfit);

                }
            } catch (Exception sql) {

                JOptionPane.showMessageDialog(this, "Couldnt populate table", "Error", JOptionPane.ERROR_MESSAGE);
            }
            totItemsField.setText(Integer.toString(i));
        }
        else if (!searchFromDate.isEmpty() && !searchtoDate.isEmpty() ) {
            java.sql.Date sqlDate;
            Connection con = ImplementedDbConnections.getConnection();
            try {
                assert con != null;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from sales where item_date between  '" + searchFromDate + "' and  '"+ searchtoDate + "'");

                while (rs.next()) {
                    sName = rs.getString("item_name");
                    sCostPrice = rs.getString("item_price_cost");
                    sPrice = rs.getString("item_price");
                    sBarcode = rs.getString("item_barcode");
                    getTypeOfItem = rs.getString("item_type");
                    sqlDate = rs.getDate("item_date");

                    String[] info = {sName, sCostPrice, sPrice, sBarcode, getTypeOfItem, sqlDate.toString()};
                    tableModel.addRow(info);
                    i++;
                    StringBuilder sb = new StringBuilder(sCostPrice);
                    String newString = sb.deleteCharAt(0).toString();
                    sb = new StringBuilder(sPrice);
                    costPrice = Integer.parseInt(newString) + costPrice;
                    newString = sb.deleteCharAt(0).toString();
                    totItemsCostField.setText("R" + costPrice);
                    totalProfit = Integer.parseInt(newString) + totalProfit;
                    totItemsProfitField.setText("R " + totalProfit);








                }

            } catch (Exception sql) {
                JOptionPane.showMessageDialog(this, "Couldnt populate table", "Error", JOptionPane.ERROR_MESSAGE);

            }
            totItemsField.setText(Integer.toString(i));

        } else if (!category.isEmpty()) {
             java.sql.Date sqlDate;
             Connection con = ImplementedDbConnections.getConnection();
             try {
                 assert con != null;
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("select * from sales where item_type = '"+category+"'");

                 while (rs.next()) {
                     sName = rs.getString("item_name");
                     sCostPrice = rs.getString("item_price_cost");
                     sPrice = rs.getString("item_price");
                     sBarcode = rs.getString("item_barcode");
                     getTypeOfItem = rs.getString("item_type");
                     sqlDate = rs.getDate("item_date");

                     String[] info = {sName, sCostPrice, sPrice, sBarcode, getTypeOfItem, sqlDate.toString()};
                     tableModel.addRow(info);
                     i++;
                     StringBuilder sb = new StringBuilder(sCostPrice);
                     String newString = sb.deleteCharAt(0).toString();
                     sb = new StringBuilder(sPrice);
                     costPrice = Integer.parseInt(newString) + costPrice;
                     newString = sb.deleteCharAt(0).toString();
                     totItemsCostField.setText("R" + costPrice);
                     totalProfit = Integer.parseInt(newString) + totalProfit;
                     totItemsProfitField.setText("R " + totalProfit);








                 }

             } catch (Exception sql) {
                 JOptionPane.showMessageDialog(this, "Couldnt populate table", "Error", JOptionPane.ERROR_MESSAGE);

             }
             totItemsField.setText(Integer.toString(i));

         }
        else if (!searchFromDate.isEmpty()) {
            java.sql.Date sqlDate;
            Connection con = ImplementedDbConnections.getConnection();
            try {
                assert con != null;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from sales where item_date = '" + searchFromDate + "'");

                while (rs.next()) {
                    sName = rs.getString("item_name");
                    sCostPrice = rs.getString("item_price_cost");
                    sPrice = rs.getString("item_price");
                    sBarcode = rs.getString("item_barcode");
                    getTypeOfItem = rs.getString("item_type");
                    sqlDate = rs.getDate("item_date");

                    String[] info = {sName, sCostPrice, sPrice, sBarcode, getTypeOfItem, sqlDate.toString()};
                    tableModel.addRow(info);
                    i++;
                    StringBuilder sb = new StringBuilder(sCostPrice);
                    String newString = sb.deleteCharAt(0).toString();
                    sb = new StringBuilder(sPrice);
                    costPrice = Integer.parseInt(newString) + costPrice;
                    newString = sb.deleteCharAt(0).toString();
                    totItemsCostField.setText("R" + costPrice);
                    totalProfit = Integer.parseInt(newString) + totalProfit;
                    totItemsProfitField.setText("R " + totalProfit);


                }
            } catch (Exception sql) {
                JOptionPane.showMessageDialog(this, "Couldnt populate table", "Error", JOptionPane.ERROR_MESSAGE);

            }
            totItemsField.setText(Integer.toString(i));

        }
        else
            JOptionPane.showMessageDialog(this, "Item not found", "Error", JOptionPane.ERROR_MESSAGE);

    }










    private void removeAllBUtton(ActionEvent e){
        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        int row = dt.getRowCount();
        System.out.println(row);
        while(row != 0)
        {
            i = 0;
            dt.removeRow(i);

            row--;

        }
        totItemsCostField.setText(null);
        totItemsField.setText(null);
        totItemsProfitField.setText(null);
        costPrice = 0;
        totalProfit = 0;
    }



    public static void main(String[] args ){new CheckSales();}
}
