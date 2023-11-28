package Pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdatePage extends JFrame {



    private JTextField cardField , cashField , checkOutField , totAmountField , BalanceDueField , changeField  , itemNameField , itemPriceField , itemCostPriceField , itemQuantityField , totalCostPriceField , totalPriceField  ;

    private JComboBox searchDbTable;
    private JLabel cardLabel , cashLabel , totAmountLabel , BalaceDueLabel , changeLabel , itemName , itemPrice , itemCostPriceLabel , itemBarcodeLabel , itemQuantityLabel ,itemTypeLable , updateLabel , deleteLabel , createButtonLabel , totalCostPriceLabel , totalPriceLabel , insertButtonLabel;


    private JPanel panel1 , panel2 , panel3;

    private String sName , sPrice , sBarcode  , stringQty , getItemName , searched , typeOfItem ,getTypeOfItem , sCostPrice  , getItemType , quantity ;


    int dueAmount = 0 ,  getQuantity = 0;
    int change , qty;
    JButton searchbutton , payButton , removeButton , removeAllButton , backButton , payCardButton , updateButton , deleteButton , createButton , insertButton;

    public static   JTable table;

    Object getTable;

    DefaultTableModel tableModel;

    UpdatePage(){


        panel2 = new JPanel(null);
        panel2.setBounds(0 , 0 , 1000 ,70 );
        panel2.setBackground(Color.lightGray);

        String[] combobox = {"Toiletries" , "Medicine"};
        searchDbTable = new JComboBox(combobox);
        searchDbTable.setBounds(80 , 25 , 100 , 20);
        searchDbTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxListener(e);
            }
        });
        panel2.add(searchDbTable);

        insertButton = new JButton("INSERT");
        insertButton.setBounds(0 , 25 , 75 , 20);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertInfo(e);
            }
        });
        panel2.add(insertButton);


        itemName = new JLabel();
        itemName.setBounds(190 , 25 , 100 , 20 );
        itemName.setText("Item : ");
        itemName.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        panel2.add(itemName);


        itemNameField = new JTextField();
        itemNameField.setBounds(245 , 25 , 100 , 20);
        panel2.add(itemNameField);

        itemPrice = new JLabel();
        itemPrice.setBounds(570 , 25 , 100 , 20 );
        itemPrice.setText("Price: R");
        itemPrice.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        panel2.add(itemPrice);

        itemCostPriceLabel = new JLabel();
        itemCostPriceLabel.setBounds(350 , 25 , 170 , 20 );
        itemCostPriceLabel.setText("Cost Price R");
        itemCostPriceLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        panel2.add(itemCostPriceLabel);

        itemQuantityLabel = new JLabel();
        itemQuantityLabel.setBounds(750 , 25 , 170 , 20 );
        itemQuantityLabel.setText("Quantity");
        itemQuantityLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        panel2.add(itemQuantityLabel);



        itemPriceField = new JTextField();
        itemPriceField.setBounds(650 , 25 , 100 , 20);
        panel2.add(itemPriceField);

        itemCostPriceField = new JTextField();
        itemCostPriceField.setBounds(470,25, 100 , 20);
        panel2.add(itemCostPriceField);

        itemQuantityField = new JTextField();
        itemQuantityField.setBounds(830,25, 100 , 20);
        panel2.add(itemQuantityField);






        updateLabel = new JLabel();
        updateLabel.setBounds(0 , 255 , 100 , 70 );
        updateLabel.setText("UPDATE");
        updateLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));

        updateButton = new JButton();
        updateButton.setBounds(85, 270 , 100 , 40 );
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    updateTable(e);
            }
        });


        deleteLabel = new JLabel();
        deleteLabel.setBounds(250 , 255 , 100 , 70 );
        deleteLabel.setText("DELETE");
        deleteLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));

        deleteButton = new JButton();
        deleteButton.setBounds(340, 270 , 100 , 40 );
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem(e);
            }
        });

        JPanel panel1 = new JPanel();
        panel1.setBounds( 400 , 270 , 350 , 190);
        panel1.setBackground(Color.lightGray);
        panel1.setLayout(null);

        totalCostPriceLabel = new JLabel();
        totalCostPriceLabel.setBounds(0 , 0 , 100 , 70 );
        totalCostPriceLabel.setText("COST R:");
        totalCostPriceLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        panel1.add(totalCostPriceLabel);

        totalCostPriceField = new JTextField();
        totalCostPriceField.setBounds(90, 15 , 130 , 35 );
        panel1.add(totalCostPriceField);



        totalPriceLabel = new JLabel();
        totalPriceLabel.setBounds(0 , 70 , 100 , 70 );
        totalPriceLabel.setText("PRICE R:");
        totalPriceLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        panel1.add(totalPriceLabel);

        totalPriceField = new JTextField();
        totalPriceField.setBounds(90, 85 , 130 , 35 );
        panel1.add(totalPriceField);









        String[] columns = new String[] {"Item Name","Cost Price" , "Item Price" , "Item Barcode" , "Quantity" , "Type"};

        tableModel = new DefaultTableModel(null , columns);
        table = new JTable(tableModel);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0 , 73 , 800 , 195);

        backButton = new JButton("back");
        backButton.setBounds(850 , 220 , 75 , 20);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonListener(e);
            }
        });



        insertButtonLabel = new JLabel("INSERT");
        insertButtonLabel.setBounds(550 , 270 , 90 , 40);
        insertButtonLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));



        insertButton = new JButton("INSERT");
        insertButton.setBounds(650 , 270 , 90 , 40);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertButtonLogic(e);
            }
        });


        removeAllButton = new JButton("Remove All");
        removeAllButton.setBounds(850 , 160 , 100 , 20);
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllBUtton(e);
            }
        });


        removeButton = new JButton("Remove");
        removeButton.setBounds(850 , 110 , 85 , 20);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeButtonAction(e);
            }
        });



        this.setSize(1000 , 400);
        this.setResizable(false);
        this.setLocation(300 , 100 );
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(updateButton);
        this.add(updateLabel);
        this.add(deleteButton);
        this.add(deleteLabel);
        this.add(panel2);
        this.add(pane);
        this.add(insertButton);
        this.add(removeAllButton);
        this.add(removeButton);
        this.add(backButton);
        this.add(insertButtonLabel);

    }
    private void updateButtonAction(ActionEvent e){

        searched = String.valueOf(getTable).toString().toLowerCase();
        getItemName = itemNameField.getText().toLowerCase();

        if(getTable == null || getItemName.isEmpty())
        {
            JOptionPane.showMessageDialog(this , "Input item type and item name " , "Error" , JOptionPane.ERROR_MESSAGE);
        }
        else if(e.getSource() == searchbutton){


            Connection con = ImplementedDbConnections.getConnection();
            try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from "+ searched +" where item_name = '"+getItemName+"'");

                if(rs.next())
                {
                    sName = rs.getString(1);
                    sCostPrice = rs.getString(2);
                    sPrice = rs.getString(3);
                    sBarcode = rs.getString(4);
                    stringQty = rs.getString(5);
                    getTypeOfItem = rs.getString(6);


                    String[] info = {sName ,"R" + sCostPrice + "","R"+sPrice+"" , sBarcode , stringQty , getTypeOfItem };
                    tableModel.addRow(info);
                    itemNameField.setText( String.valueOf(sName));
                    itemPriceField.setText(String.valueOf(sPrice));
                    int getAmount = Integer.parseInt(sPrice);
                    dueAmount = dueAmount + getAmount;
                    BalanceDueField.setText(Integer.toString(dueAmount));
                }
                else
                    JOptionPane.showMessageDialog(this ,"Item not found" , "Error" , JOptionPane.ERROR_MESSAGE);




            }catch(Exception sql){

                JOptionPane.showMessageDialog(this, "Couldnt populate table" , "Error" , JOptionPane.ERROR_MESSAGE);
            }

        }



    }


    private void payCardButtonListener(ActionEvent e )
    {
        String cashReceived = cashField.getText();
        String cardReceived = cardField.getText();
        Connection con2 = ImplementedDbConnections.getConnection();
        int converteCashReceived = 0;
        int i = 0;




        if(cashReceived.isEmpty() && cardReceived.isEmpty())
            JOptionPane.showMessageDialog(this, "Select Items you want to sell", "Error", JOptionPane.ERROR_MESSAGE);

        else if (e.getSource() == payCardButton &&  cashReceived.isEmpty()) {
            converteCashReceived = Integer.parseInt(cardReceived);
            if(converteCashReceived < dueAmount)
                JOptionPane.showMessageDialog(this, "Pay full amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource() == payCardButton && converteCashReceived >= dueAmount) {
            change = converteCashReceived - dueAmount;

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            int rowCount = tableModel.getRowCount();
            int rowCountCopied =rowCount;

            while(rowCount != 0){

                String getStringValue = tableModel.getValueAt(i , 4).toString();
                String getName = tableModel.getValueAt( i, 0).toString();
                String getCost = tableModel.getValueAt(i ,1).toString();
                System.out.println(getName);
                String getBarcode = tableModel.getValueAt(i , 3).toString();
                System.out.println(getBarcode);
                String getPrice = tableModel.getValueAt(i , 2).toString();
                System.out.println(getPrice);
                typeOfItem = tableModel.getValueAt(i , 5).toString();
                System.out.println(typeOfItem);
                System.out.println(date);


                qty = Integer.parseInt(getStringValue);
                qty -= 1;
                String getStringValue2 = Integer.toString(qty);



                try {
                    PreparedStatement st1 = con2.prepareStatement
                            ("insert into sales(item_name, item_cost_price , item_barcode,item_price,item_sold_date,items_type) values('"+ getName + "','"+getCost+"' ,'"+getBarcode+"' , '"+getPrice +"' , '"+sqlDate+"' , '"+typeOfItem +"' )") ;

                    int count = st1.executeUpdate();

                    st1 = con2.prepareStatement("Update "+ typeOfItem +" set item_quantity = '"+getStringValue2+"' where item_quantity = '"+getStringValue +"'");

                    count  = st1.executeUpdate();


                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this , "Couldnt populate sales table" , "Error" , JOptionPane.ERROR_MESSAGE);
                }
                i++;
                rowCount--;



            }


            if(converteCashReceived > 0)
            {
                totAmountField.setText(cardReceived);
                changeField.setText(Integer.toString(change));
                JOptionPane.showMessageDialog(this , "Transaction complete" , "Completion" , JOptionPane.INFORMATION_MESSAGE);
            }


            this.dispose();
            new SellWindow();


        }

    }


    private void comboBoxListener(ActionEvent e){

        getTable = searchDbTable.getSelectedItem();



    }
    private void payButtonListener(ActionEvent e )
    {
        String cashReceived = cashField.getText();
        String cardReceived = cardField.getText();
        Connection con2 = ImplementedDbConnections.getConnection();
        int converteCashReceived = 0;
        int i = 0;


        if(cashReceived.isEmpty() && cardReceived.isEmpty())
            JOptionPane.showMessageDialog(this, "Select Items you want to sell", "Error", JOptionPane.ERROR_MESSAGE);

        else if (e.getSource() == payButton &&  cardReceived.isEmpty()) {
            converteCashReceived = Integer.parseInt(cashReceived);
            if(converteCashReceived < dueAmount)
                JOptionPane.showMessageDialog(this, "Pay full amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource() == payButton && converteCashReceived >= dueAmount) {
            change = converteCashReceived - dueAmount;

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            int rowCount = tableModel.getRowCount();
            int rowCountCopied =rowCount;

            while(rowCount != 0){

                String getStringValue = tableModel.getValueAt(i , 4).toString();
                String getName = tableModel.getValueAt( i, 0).toString();
                String getCost = tableModel.getValueAt(i ,1).toString();
                System.out.println(getName);
                String getBarcode = tableModel.getValueAt(i , 3).toString();
                System.out.println(getBarcode);
                String getPrice = tableModel.getValueAt(i , 2).toString();
                System.out.println(getPrice);
                typeOfItem = tableModel.getValueAt(i , 5).toString();
                System.out.println(typeOfItem);
                System.out.println(date);


                qty = Integer.parseInt(getStringValue);
                qty -= 1;
                String getStringValue2 = Integer.toString(qty);



                try {
                    PreparedStatement st1 = con2.prepareStatement
                            ("insert into sales(item_name, item_price_cost, item_price , item_barcode,item_type,item_date) values('"+ getName + "','"+getCost+"' ,'"+getPrice+"' , '"+getBarcode +"' , '"+typeOfItem+"' , '"+sqlDate +"' )") ;

                    int count = st1.executeUpdate();

                    st1 = con2.prepareStatement("Update "+ typeOfItem +" set item_quantity = '"+getStringValue2+"' where item_quantity = '"+getStringValue +"'");

                    count  = st1.executeUpdate();


                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this , "Couldnt populate sales table" , "Error" , JOptionPane.ERROR_MESSAGE);
                }
                i++;
                rowCount--;



            }
            if(converteCashReceived > 0)
            {
                totAmountField.setText(cashReceived);
                changeField.setText(Integer.toString(change));
                JOptionPane.showMessageDialog(this , "Transaction complete  \n change: R"+change +"" , "Completion" , JOptionPane.INFORMATION_MESSAGE);
            }




            i = 0;
            this.dispose();
            new SellWindow();


        }

    }

    private void backButtonListener(ActionEvent e){

        if(e.getSource()== backButton){

            this.dispose();
            new TransactionsPage();
        }
    }

    private  void insertInfo(ActionEvent e){
        getItemName = itemNameField.getText();
        String getCostPrice = itemCostPriceField.getText();
        String getPrice = itemPriceField.getText();
        String getQuantity = itemQuantityField.getText();
        getItemType = String.valueOf(getTable).toLowerCase();
        Connection con = ImplementedDbConnections.getConnection();

        if(!getItemName.isEmpty() && !getItemType.isEmpty()){

            try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from "+ getItemType +" where item_name = '"+getItemName+"'");


                if(rs.next())
                {
                    sName = rs.getString(1);
                    sCostPrice = rs.getString(2);
                    sPrice = rs.getString(3);
                    sBarcode = rs.getString(4);
                    stringQty = rs.getString(5);
                    getTypeOfItem = rs.getString(6);


                    String[] info = {sName ,"R" + sCostPrice + "","R"+sPrice+"" , sBarcode , stringQty , getTypeOfItem };
                    tableModel.addRow(info);

                }
                else
                    JOptionPane.showMessageDialog(this ,"Item not found" , "Error" , JOptionPane.ERROR_MESSAGE);




            }catch(Exception sql){

                JOptionPane.showMessageDialog(this, "Couldnt populate table" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } else if(getItemName.isEmpty() || getPrice.isEmpty() || getCostPrice.isEmpty() || getQuantity.isEmpty()){

            JOptionPane.showMessageDialog(this , "Insert all Fields" , "Error Message" , JOptionPane.ERROR_MESSAGE);
        }


        else{


            String[] itemInfo ={getItemName , getCostPrice , getPrice, null ,getQuantity , getItemType };
            tableModel.addRow(itemInfo);
        }




    }

    private void updateTable(ActionEvent e) {
        String getQuantityUpdated = itemQuantityField.getText();
        int rowCount = tableModel.getRowCount();
        int i = 0;
        Connection con2 = ImplementedDbConnections.getConnection();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        while (rowCount != 0) {

            String getStringValue = tableModel.getValueAt(i, 4).toString();
            String getName = tableModel.getValueAt(i, 0).toString();
            String getCost = tableModel.getValueAt(i, 1).toString();
            String getPrice = tableModel.getValueAt(i, 2).toString();
            String getBarcode = tableModel.getValueAt(i , 3).toString();


            try {


                PreparedStatement st1 = con2.prepareStatement("Update "+ getItemType.toLowerCase() +" set  item_quantity = '"+getQuantityUpdated +"' where item_name = '"+getName+"'");

                int count  = st1.executeUpdate();
                 st1 = con2.prepareStatement
                        ("insert into sales(item_name, item_price_cost, item_price , item_barcode,item_type,item_date) values('"+ getName + "','"+getCost+"' ,'"+getPrice+"' , '"+getBarcode+"' , '"+getItemType+"' , '"+sqlDate +"' )") ;

                 count = st1.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this , "Couldnt populate sales table" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
            i++;
            rowCount--;






        }
        JOptionPane.showMessageDialog(this , "UPDATE COMPLETE" , "UPDATE" , JOptionPane.INFORMATION_MESSAGE);
    }

   private void deleteItem(ActionEvent e){

       int rowCount = tableModel.getRowCount();
       int i = 0;
       Connection con2 = ImplementedDbConnections.getConnection();
       java.util.Date date = new java.util.Date();
       java.sql.Date sqlDate = new java.sql.Date(date.getTime());


       while (rowCount != 0) {

           String getStringValue = tableModel.getValueAt(i, 4).toString();
           String getName = tableModel.getValueAt(i, 0).toString();
           String getCost = tableModel.getValueAt(i, 1).toString();
           String getPrice = tableModel.getValueAt(i, 2).toString();
           try{
               Statement st = con2.createStatement();
               ResultSet rs = st.executeQuery("select * from "+ getItemType.toLowerCase() +" where item_name = '"+getName+"'");

               if(rs.next())
               {

                   stringQty = rs.getString(5);

                   System.out.println(stringQty);
                   getQuantity = Integer.parseInt(stringQty);
                   System.out.println(getQuantity);
                   getQuantity = getQuantity - Integer.parseInt(getStringValue);
                   System.out.println(getQuantity);
                   quantity = Integer.toString(getQuantity);





               }
               else
                   JOptionPane.showMessageDialog(this ,"Item not found" , "Error" , JOptionPane.ERROR_MESSAGE);




           }catch(Exception sql){

               JOptionPane.showMessageDialog(this, "Couldnt populate table" , "Error" , JOptionPane.ERROR_MESSAGE);
           }





           try {


               PreparedStatement st1 = con2.prepareStatement("Update "+ getItemType.toLowerCase() +" set item_quantity = '"+quantity +"' where item_name = '"+getName +"'");

               int count  = st1.executeUpdate();
               st1 = con2.prepareStatement
                       ("insert into sales(item_name, item_price_cost, item_price , item_barcode,item_type,item_date) values('"+ getName + "','"+getCost+"' ,'"+getPrice+"' , '"+null+"' , '"+getItemType+"' , '"+sqlDate +"' )") ;

               count = st1.executeUpdate();

           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this , "Couldnt populate sales table" , "Error" , JOptionPane.ERROR_MESSAGE);
           }
           i++;
           rowCount--;






       }

       JOptionPane.showMessageDialog(this , "DELETE COMPLETE" , "DELETE" , JOptionPane.INFORMATION_MESSAGE);

   }

   private void insertButtonLogic(ActionEvent e){
       int rowCount = tableModel.getRowCount();
       int i = 0;
       Connection con2 = ImplementedDbConnections.getConnection();



       while (rowCount != 0) {

           String getStringValue = tableModel.getValueAt(i, 4).toString();
           String getName = tableModel.getValueAt(i, 0).toString();
           String getCost = tableModel.getValueAt(i, 1).toString();
           String getPrice = tableModel.getValueAt(i, 2).toString();


           try {


              PreparedStatement st1 = con2.prepareStatement
                       ("insert into "+getItemType.toLowerCase()+"(item_name, item_price_cost, item_price,item_barode,item_quantity ,item_type) values('"+ getName + "','"+getCost+"' ,'"+getPrice+"' , '"+null+"' ,'"+getStringValue+"' ,'"+getItemType+"' )") ;

               int count = st1.executeUpdate();

           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this , "Couldnt populate sales table" , "Error" , JOptionPane.ERROR_MESSAGE);
           }
           i++;
           rowCount--;






       }

       JOptionPane.showMessageDialog(this , "INSERT COMPLETE" , "INSERT" , JOptionPane.INFORMATION_MESSAGE);

   }








    private void removeButtonAction(ActionEvent e){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if(table.getSelectedRowCount() == 1){
            model.removeRow(table.getSelectedRow());

            int row2 = table.getRowCount() ;
            String getAmount;
            int amount = 0;

            for(int i = 0  ; i < row2  ; i++)
            {


                getAmount = table.getValueAt(i , 2).toString();
                StringBuilder sb = new StringBuilder(getAmount);
                String newAmount = sb.deleteCharAt(0).toString();
                System.out.println(newAmount);
                amount = amount + Integer.parseInt(newAmount);
                dueAmount = amount;
                System.out.println(amount);


            }

            BalanceDueField.setText(Integer.toString(amount));
        }
        else{
            if(table.getRowCount() == 0){
                JOptionPane.showMessageDialog(this , "Table is empty" , "Info" , JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }

    private void removeAllBUtton(ActionEvent e){
        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        int row = dt.getRowCount();
        while(row != 0)
        {   int i = 0;
            dt.removeRow(i);
            i++;
            row--;
            dueAmount = 0;
            BalanceDueField.setText(Integer.toString(dueAmount));
        }
    }



    public static void main(String[] args){new UpdatePage();}
}

