package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionsPage  extends JFrame implements ActionListener {


    JButton sellButton;
    JButton checkSalesButton;
    JButton updateButton;

    JButton backButton;




    TransactionsPage(){

        sellButton = new JButton();
        sellButton.addActionListener(this);
        sellButton.setFocusable(false);
        sellButton.setBounds( 0 , 0 , 200, 200);
        sellButton.setBackground(Color.BLUE);
        sellButton.setText("SELL");
        sellButton.setFont(new Font("MV Boli",Font.PLAIN , 20));
        sellButton.setForeground(Color.WHITE);

        checkSalesButton = new JButton();
        checkSalesButton.addActionListener(this);
        checkSalesButton.setFocusable(false);
        checkSalesButton.setBounds( 0 , 270 , 200, 200);
        checkSalesButton.setBackground(Color.GREEN);
        checkSalesButton.setText("CHECK SALES");
        checkSalesButton.setFont(new Font("MV Boli",Font.PLAIN , 20));
        checkSalesButton.setForeground(Color.WHITE);

        updateButton = new JButton();
        updateButton.addActionListener(this);
        updateButton.setFocusable(false);
        updateButton.setBounds( 390 , 0 , 200, 200);
        updateButton.setBackground(Color.RED);
        updateButton.setText("UPDATE");
        updateButton.setFont(new Font("MV Boli",Font.PLAIN , 20));
        updateButton.setForeground(Color.WHITE);

        backButton = new JButton();
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setBounds( 390 , 270 , 200, 200);
        backButton.setBackground(Color.ORANGE);
        backButton.setText("BACK");
        backButton.setFont(new Font("MV Boli",Font.PLAIN , 20));
        backButton.setForeground(Color.WHITE);






        this.setSize(600 , 500);
        this.setResizable(false);
        this.setLocation(300 , 100 );
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(sellButton);
        this.add(checkSalesButton);
        this.add(backButton);
        this.add(updateButton);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sellButton ) {
            this.dispose();
            new SellWindow();
        }
        else if (e.getSource() == checkSalesButton)
        {
            this.dispose();
            new CheckSales();
        }
        else if(e.getSource() == updateButton){

            this.dispose();
            new UpdatePage();
        }
        else if(e.getSource() == backButton){
            this.dispose();
            new Login();

        }


    }


    public static void main(String[] args){new TransactionsPage();}
}
