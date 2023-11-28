package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {

   private JTextField user_id;
   private JPasswordField user_pin;

   private JButton userLogin;

   private JButton userSignUp;

   private JLabel posName;

   private JLabel label;

    private JLabel user_id_label;

   private JLabel getUser_id_pin;





public  Login(){



    userSignUp = new JButton();
    userSignUp.addActionListener(this);
    userSignUp.setBounds( 200, 200 , 120 , 30);
    userSignUp.setText("new user? ");
    userSignUp.setHorizontalAlignment(JLabel.CENTER);
    userSignUp.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
    userSignUp.setBackground(Color.white);
    userSignUp.setBorderPainted(false);
    userSignUp.setOpaque(true);
    userSignUp.setFocusable(false);


    userLogin = new JButton();
    userLogin.addActionListener(this);
    userLogin.setBounds( 200, 150 , 120 , 30);
    userLogin.setText("Login ");
    userLogin.setHorizontalAlignment(JLabel.CENTER);
    userLogin.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
    userLogin.setOpaque(true);
    userLogin.setFocusable(false);

    user_id = new JTextField();
    user_id.setBounds(200 ,50 ,100 ,35 );
    user_id.setCaretColor(Color.black);



    user_id_label = new JLabel();
    user_id_label.setBounds( 100, 50 , 100 , 40);
    user_id_label.setText("User ID: ");
    user_id_label.setHorizontalAlignment(JLabel.CENTER);
    user_id_label.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
    user_id_label.setOpaque(true);




    user_pin = new JPasswordField();
    user_pin.setBounds(200 ,100 ,100 ,35 );
    user_pin.setCaretColor(Color.black);



    getUser_id_pin = new JLabel();
    getUser_id_pin.setBounds( 100, 100 , 100 , 40);
    getUser_id_pin.setText("User PIN: ");
    getUser_id_pin.setHorizontalAlignment(JLabel.CENTER);
    getUser_id_pin.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
    getUser_id_pin.setOpaque(true);

    label = new JLabel();
    label.setBounds(0 , 0 , 500 , 40);
    label.setText("Welcome to Sell_IT");
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
    label.setOpaque(true);





    this.setTitle("ATM");
    this.setSize(500 , 300);
    this.setLocation(500 , 250);
    this.setResizable(false);
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    this.setBackground(Color.white);
    this.setLayout(null);
    this.add(user_id);
    this.add(getUser_id_pin);
    this.add(user_pin);
    this.add(label);
    this.add(user_id_label);
    this.add(userLogin);
    this.add(userSignUp);
    this.setVisible(true);



}

public static void main(String[] args){new Login();}


    @Override
    public void actionPerformed(ActionEvent e) {
    String userText = user_id.getText();
    String getPassword = String.valueOf(user_pin.getPassword());
    if(e.getSource() == userSignUp)
        {
            new SignUp();
            this.dispose();

        }
    else if(userText.isEmpty() || getPassword.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Input all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    else {

        int convertedUserId = Integer.parseInt(userText);
        Connection con = ImplementedDbConnections.getConnection();
        //Connection conn = ImplementedDbConnections.conn;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users where user_id = '"+convertedUserId+"' and user_pin = '"+ getPassword + "'");
            if (rs.next()){
                this.dispose();
                new TransactionsPage();

            }
            else
                JOptionPane.showMessageDialog(this , "Password or username not found" , "Error" , JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Couldnt query database to find user \n contract IT support");
        }
    }


    }


    }


