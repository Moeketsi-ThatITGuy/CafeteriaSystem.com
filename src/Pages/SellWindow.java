package Pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellWindow extends JFrame  {



    private JTextField cardField , cashField , checkOutField , totAmountField , BalanceDueField , changeField  , itemNameField , itemPriceField;

    private JComboBox searchDbTable;
    private JLabel cardLabel , cashLabel , totAmountLabel , BalaceDueLabel , changeLabel , itemName , itemPrice  ;

    private JPanel panel1 , panel2 , panel3;

    private String sName , sPrice , sBarcode  , stringQty , getItemName , searched , typeOfItem ,getTypeOfItem , sCostPrice;


    int dueAmount = 0 ;
    int change , qty;
    JButton searchbutton , payButton , removeButton , removeAllButton , backButton , payCardButton;

    public static   JTable table;

    Object getTable;

    DefaultTableModel tableModel;



 SellWindow(){


   panel2 = new JPanel(null);
   panel2.setBounds(0 , 0 , 550 ,70 );
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

   searchbutton = new JButton("Search");
   searchbutton.setBounds(0 , 25 , 75 , 20);
   searchbutton.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           searchButtonAction(e);
       }
   });
   panel2.add(searchbutton);


     itemName = new JLabel();
     itemName.setBounds(190 , 25 , 100 , 20 );
     itemName.setText("Item : ");
     itemName.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
     panel2.add(itemName);


     itemNameField = new JTextField();
     itemNameField.setBounds(245 , 25 , 100 , 20);
     panel2.add(itemNameField);

     itemPrice = new JLabel();
     itemPrice.setBounds(350 , 25 , 100 , 20 );
     itemPrice.setText("Price: R");
     itemPrice.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
     panel2.add(itemPrice);

     itemPriceField = new JTextField();
     itemPriceField.setBounds(430 , 25 , 100 , 20);
     panel2.add(itemPriceField);






     cardLabel = new JLabel();
     cardLabel.setBounds(0 , 255 , 100 , 70 );
     cardLabel.setText("CARD: R");
     cardLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));

     cardField = new JTextField();
     cardField.setBounds(85, 270 , 100 , 40 );


     cashLabel = new JLabel();
     cashLabel.setBounds(0 , 320 , 100 , 70 );
     cashLabel.setText("CASH: R");
     cashLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));

     cashField = new JTextField();
     cashField.setBounds(85, 340 , 100 , 40 );

     JPanel panel1 = new JPanel();
     panel1.setBounds( 200 , 270 , 350 , 190);
     panel1.setBackground(Color.lightGray);
     panel1.setLayout(null);

     totAmountLabel = new JLabel();
     totAmountLabel.setBounds(0 , 70 , 100 , 70 );
     totAmountLabel.setText("PAID: R");
     totAmountLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
     panel1.add(totAmountLabel);

     totAmountField = new JTextField();
     totAmountField.setBounds(90, 85 , 130 , 35 );
     panel1.add(totAmountField);



     BalaceDueLabel = new JLabel();
     BalaceDueLabel.setBounds(0 , 0 , 100 , 70 );
     BalaceDueLabel.setText("DUE: R");
     BalaceDueLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
     panel1.add(BalaceDueLabel);

     BalanceDueField = new JTextField();
     BalanceDueField.setBounds(90, 15 , 130 , 35 );
     panel1.add(BalanceDueField);

     changeLabel = new JLabel();
     changeLabel.setBounds(0 , 140 , 120 , 70 );
     changeLabel.setText("CHANGE: R");
     changeLabel.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
     panel1.add(changeLabel);

     changeField = new JTextField();
     changeField.setBounds(115, 155 , 130 , 35 );
     panel1.add(changeField);







     String[] columns = new String[] {"Item Name","Cost Price" , "Item Price" , "Item Barcode" , "Quantity" , "Type"};

     tableModel = new DefaultTableModel(null , columns);
     table = new JTable(tableModel);

     JScrollPane pane = new JScrollPane(table);
     pane.setBounds(0 , 73 , 450 , 195);

     backButton = new JButton("back");
     backButton.setBounds(450 , 220 , 75 , 20);
     backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             backButtonListener(e);
         }
     });



     payButton = new JButton("Pay");
     payButton.setBounds(0 , 400 , 90 , 40);
     payButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             payButtonListener(e);
         }
     });


     payCardButton = new JButton("Card Pay");
     payCardButton.setBounds(105 , 400 , 90 , 40);
     payCardButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             payCardButtonListener(e);
         }
     });


     removeAllButton = new JButton("Remove All");
     removeAllButton.setBounds(450 , 160 , 100 , 20);
     removeAllButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             removeAllBUtton(e);
         }
     });


     removeButton = new JButton("Remove");
     removeButton.setBounds(450 , 110 , 85 , 20);
     removeButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             removeButtonAction(e);
         }
     });



     this.setSize(565 , 500);
     this.setResizable(false);
     this.setLocation(300 , 100 );
     this.setLayout(null);
     this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
     this.setVisible(true);
     this.add(cardLabel);
     this.add(cardField);
     this.add(cashField);
     this.add(cashLabel);
     this.add(panel1);
     this.add(panel2);
     this.add(pane);
     this.add(payButton);
     this.add(removeAllButton);
     this.add(removeButton);
     this.add(backButton);
     this.add(payCardButton);



 }





     private void searchButtonAction(ActionEvent e){

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
                            ("insert into sales(item_name, item_price_cost, item_price , item_barcode,item_type,item_date) values('"+ getName + "','"+getCost+"' ,'"+getPrice+"' , '"+getBarcode +"' , '"+typeOfItem+"' , '"+sqlDate +"' )") ;

                    int count = st1.executeUpdate();

                    st1 = con2.prepareStatement("Update "+ typeOfItem.toLowerCase() +" set item_quantity = '"+getStringValue2+"' where item_name = '"+getName +"'");
                    System.out.print(typeOfItem);
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
                         System.out.println(getName);
                         String getCost = tableModel.getValueAt(i ,1).toString();
                         String getBarcode = tableModel.getValueAt(i , 3).toString();
                         String getPrice = tableModel.getValueAt(i , 2).toString();
                         typeOfItem = tableModel.getValueAt(i , 5).toString();



                         qty = Integer.parseInt(getStringValue);
                         System.out.println(qty);
                         qty -= 1;
                         System.out.println(qty);
                          String getStringValue2 = Integer.toString(qty);
                          System.out.println(getStringValue2);



                            try {
                                
                                PreparedStatement st1 = con2.prepareStatement
                                        ("insert into sales(item_name, item_price_cost, item_price , item_barcode,item_type,item_date) values('"+ getName + "','"+getCost+"' ,'"+getPrice+"' , '"+getBarcode +"' , '"+typeOfItem+"' , '"+sqlDate +"' )") ;

                                int count = st1.executeUpdate();

                                   PreparedStatement st2 = con2.prepareStatement("Update "+ typeOfItem.toLowerCase() +" set item_quantity = '"+getStringValue2 +"' where item_name = '"+getName +"'");

                                 count  = st2.executeUpdate();


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



    public static void main(String[] args){new SellWindow();}
}
