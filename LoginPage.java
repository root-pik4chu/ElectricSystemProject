
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LoginPage extends JFrame  implements ActionListener {

    //why these buttons are here -
    /*
    all the buttons are in constructor and the scope of constructor is
    not enought for thr calling them again hence we declare outsidee the
    constructor ,,,
     */

    /*
    Action listener - action is for the action performation in between the files

     */
    JButton login,signUp,cancel;

    JTextField username ,pass;

    Choice loginIn;


    LoginPage(){
        //page heading
        super("login Page");

        //page color
        getContentPane().setBackground(Color.white);

        //our layout is written after null function
        setLayout(null);


        //Jlable - to write anything in page
        //lable 1
        JLabel Jusername = new JLabel("UserName");
        Jusername.setBounds(400,30,100,30);
        add(Jusername);

        //input Box 1
         username = new JTextField();
        username.setBounds(500,30,150,30);
        add(username);

        //lable 2
        JLabel  Jpass = new JLabel("PassWord");
        Jpass.setBounds(400,70,100,30);
        add(Jpass);

        //input Box 1
         pass = new JTextField();
        pass.setBounds(500,70,150,30);
        add(pass);

        //lable 3
        JLabel  JloginAs = new JLabel("Login As");
        JloginAs.setBounds(400,110,100,30);
        add(JloginAs);

        //drop down menu
         loginIn = new Choice();
        loginIn.add("Admin");
        loginIn.add("Customer");
        loginIn.setBounds(500,110,100,30);
        add(loginIn);

        //Image Icon input
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);

        //buttons
        //after taking all buttons globally we remove the explicite declaration
        //Jbutton from here ...
        login = new JButton("Login",new ImageIcon(i2));
        login.setBounds(400,150,90,25);
        //button Action
        login.addActionListener(this);
        add(login);


        //image icon Cancle
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);


        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(500,150,100,26);
        //button Action
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);

        signUp = new JButton("SignUp",new ImageIcon(i6));
        signUp.setBounds(400,200,100,25);
        //button Action
        signUp.addActionListener(this);
        add(signUp);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel img = new JLabel(i9);
        img.setBounds(50,0,250,250);
        add(img);

        // page size and all stuff
        setSize(700 , 350);
        setLocation(600,400);
        setVisible(true);
    }

    /*
    why actionPerformed class - because all the action are performed in this
    class only ...we have to override it form the action listener class
     */
    public void actionPerformed(ActionEvent e){
        // all action is performed in button clicks
         if(e.getSource() == login){

             String sUserName = username.getText();
             String sPass = pass.getText();
             String user = loginIn.getSelectedItem();

             try{

                 //connection establish
                 Conn c = new Conn();
                 String query = "select * from login where userName = '"+sUserName+"' and password = '"+ sPass+"' and user = '"+user+"'  ";

                 //execute query -- ddl command
                 ResultSet res = c.s.executeQuery(query);


                 //he kam jumping sathi and text field blank kara sathi..
                 if(res.next()){


                    String meter = res.getString("meter_no");
                     setVisible(false);
                     //6th day sending user to the project for
                     // opening the file if its user or admin
                     // open diffrent interface for users ...
                     new Project(user , meter);
                 }else {
                     JOptionPane.showMessageDialog(null,"Invalid login");
                    username.setText("");
                    pass.setText("");
                 }

             }catch (Exception E){
                 E.printStackTrace();
             }


         }else if(e.getSource() == cancel){
            setVisible(false);
         }else if (e.getSource() == signUp){
             setVisible(false);
             new SignUp();
         }
    }
    public static void main(String[] args) {

        new LoginPage();
    }
}
