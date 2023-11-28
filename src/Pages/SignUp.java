package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SignUp extends JFrame implements ActionListener {


    private JTextField userName;
    private JTextField userSurname;

    private JPasswordField newUserPin;

    private JButton signUp;

    private JLabel posName;

    private JLabel label;

    private JLabel user_id_label;

    private JLabel getUser_id_pin;

    private JLabel user_Pin_label;

    private JButton backButton;






    SignUp(){




        backButton = new JButton();
        backButton.addActionListener(this);
        backButton.setBounds( 200, 250 , 120 , 30);
        backButton.setText("Back ");
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
        backButton.setBackground(Color.lightGray);
        backButton.setOpaque(true);
        backButton.setFocusable(false);

        signUp = new JButton();
        signUp.addActionListener(this);
        signUp.setBounds( 200, 200 , 120 , 30);
        signUp.setText("Login ");
        signUp.setHorizontalAlignment(JLabel.CENTER);
        signUp.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
        signUp.setBackground(Color.lightGray);
        signUp.setOpaque(true);
        signUp.setFocusable(false);


        user_Pin_label = new JLabel();
        user_Pin_label.setBounds( 100, 140 , 100 , 40);
        user_Pin_label.setText("PIN: ");
        user_Pin_label.setHorizontalAlignment(JLabel.CENTER);
        user_Pin_label.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
        user_Pin_label.setOpaque(true);


        newUserPin = new JPasswordField();
        newUserPin.setBounds( 200, 150 , 120 , 30);
        newUserPin.setCaretColor(Color.black);



        userName = new JTextField();
        userName.setBounds(200 ,50 ,100 ,35 );
        userName.setCaretColor(Color.black);



        user_id_label = new JLabel();
        user_id_label.setBounds( 100, 50 , 100 , 40);
        user_id_label.setText("User Name: ");
        user_id_label.setHorizontalAlignment(JLabel.CENTER);
        user_id_label.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
        user_id_label.setOpaque(true);




        userSurname = new JTextField();
        userSurname.setBounds(220 ,100 ,100 ,35 );
        userSurname.setCaretColor(Color.black);



        getUser_id_pin = new JLabel();
        getUser_id_pin.setBounds( 90, 100 , 140 , 40);
        getUser_id_pin.setText("User Surname: ");
        getUser_id_pin.setHorizontalAlignment(JLabel.CENTER);
        getUser_id_pin.setFont(new Font("Ariel Black",Font.BOLD, 15 ));
        getUser_id_pin.setOpaque(true);

        label = new JLabel();
        label.setBounds(0 , 0 , 500 , 40);
        label.setText("New User Login");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Ariel Black",Font.BOLD, 20 ));
        label.setOpaque(true);





        this.setTitle("SignUP");
        this.setSize(500 , 350);
        this.setLocation(500 , 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setLayout(null);
        this.add(userName);
        this.add(getUser_id_pin);
        this.add(userSurname);
        this.add(label);
        this.add(user_Pin_label);
        this.add(user_id_label);
        this.add(newUserPin);
        this.add(signUp);
        this.add(backButton);
        this.setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent ev) {
        String getName = userName.getText();
        String getSurname = userSurname.getText();
        String getUserId = String.valueOf(newUserPin.getPassword());


        if (ev.getSource() == backButton) {
            this.dispose();
            new Login();

        } else if (getName.isEmpty() || getSurname.isEmpty() || getUserId.isEmpty()){
            JOptionPane.showMessageDialog(this ,"Please enter all fields" , "Error" , JOptionPane.ERROR_MESSAGE);


        }
        else {
            int finalUserId = Integer.parseInt(getUserId);
            Connection con = ImplementedDbConnections.getConnection();
            try {
                PreparedStatement st = con.prepareStatement("insert into users(user_pin , user_name , user_surname) values" +
                        "('"+ finalUserId +"','"+ getName+"' , '"+getSurname +"' )");
                int count = st.executeUpdate();
                new TransactionsPage();
                this.dispose();
                st.close();
                con.close();



            }catch (Exception sql){
                JOptionPane.showMessageDialog(this , "Cant connect to database \n Contract IT support" , "Error" , JOptionPane.ERROR_MESSAGE);


            }
            finally {
                new TransactionsPage();
            }
        }

    }




    public static void main(String[] args){new SignUp();}
}
