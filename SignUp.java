import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {

    JButton create,back;

    //choice he function le use honar ahe cause aplya le db sobt connection banwlya wr
    // choice lagel ...for data connection
    Choice accountType;

    JTextField meter,name ,pass ,userName;
    SignUp(){
//        setSize(700 , 500);
//        setLocation(600,400);

        // creating the page
        /*
        why we use here the setBounds
         */
        setBounds(600,400,700,500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //panel - create for seperation of the page which we created
        JPanel panal = new JPanel();
        panal.setBounds(30,30,650,400);
        panal.setBorder(new TitledBorder(new LineBorder(Color.black),"create Account",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
        panal.setLayout(null);
        panal.setForeground(Color.black);
        add(panal);

        // same for writing and creating the text , textBoxes on the panal
        JLabel heading = new JLabel("create Account for");
        heading.setBounds(80,50,140,30);
        heading.setForeground(Color.darkGray);

        // inHeading we can give the font family new font takes some arguments
        /*
        arguments inn the sence it take font design ... roman , calibi ,,,etc
        second argument is style - (bold,italic) or if you  want null you can put 0
        third argument will be the size ...
         */
        heading.setFont(new Font(null,0,16));
        panal.add(heading);

        // choice for the admin and customer
        accountType = new Choice();
        accountType.add("admin");
        accountType.add("customer");
        accountType.setBounds(220,50,140,30);
        panal.add(accountType);
        //----------------------------------sql wali cheeze--------
//        accountType.addFocusListener();


//-------------------------------------------------------------
        // another label for the panal
        JLabel JmeterNo = new JLabel("meterNumber");
        JmeterNo.setBounds(80,90,140,30);
        JmeterNo.setForeground(Color.darkGray);
        JmeterNo.setFont(new Font(null,0,16));
        JmeterNo.setVisible(false);
        panal.add(JmeterNo);

        // blanck field for the meterNumber
        meter = new JTextField();
        meter.setBounds(220,90,140,30);
        meter.setVisible(false);
        panal.add(meter);

        JLabel JuserName = new JLabel("userName");
        JuserName.setBounds(80,130,140,30);
        JuserName.setForeground(Color.darkGray);
        JuserName.setFont(new Font(null,0,16));
        panal.add(JuserName);

        // blanck field for the userName
         userName = new JTextField();
        userName.setBounds(220,130,140,30);
        panal.add(userName);


        JLabel JName = new JLabel("name");
        JName.setBounds(80,170,140,30);
        JName.setForeground(Color.darkGray);
        JName.setFont(new Font(null,0,16));
        panal.add(JName);

        // blanck field for the Name
         name = new JTextField();
        name.setBounds(220,170,140,30);
        panal.add(name);

        // ---------------------------------sql wali cheeze------------

        meter.addFocusListener(new FocusListener() {
            @Override
            //click on the button
            public void focusGained(FocusEvent fe) {

            }

            @Override
            public void focusLost(FocusEvent fe) {
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"' ");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                    }
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });

        //--------------------------------------------------


        JLabel Jpassword = new JLabel("passWord");
        Jpassword.setBounds(80,210,140,30);
        Jpassword.setForeground(Color.darkGray);
        Jpassword.setFont(new Font(null,0,16));
        panal.add(Jpassword);

        // blanck field for the password
         pass = new JTextField();
        pass.setBounds(220,210,140,30);
        panal.add(pass);


        //------visible text field or nnot
        /*
        text field if visible then the customer is selected
        if their admin selected then (meter number should not be
        visible to all)
         */

        //---------------------------------------------------

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                 String user = accountType.getSelectedItem();
                 if(user.equals("customer")){
                     JmeterNo.setVisible(true);
                     meter.setVisible(true);

                     // if true then name should not be editable
                     name.setEditable(false);

                 }else {
                     JmeterNo.setVisible(false);
                     meter.setVisible(false);
                     // if false then name should not be editable
                     name.setEditable(true);
                 }
            }
        });


        //-----------------------------------------------------

        //jButton for create
        create = new JButton("create");
        create.setBackground(Color.gray);
        create.setForeground(Color.white);
        create.setBounds(80 , 260,120,30);
        //this is all after declaring new function called as actionllistener
        create.addActionListener(this);
        panal.add(create);

        //jButton for create
        back = new JButton("back");
        back.setBackground(Color.gray);
        back.setForeground(Color.white);
        back.setBounds(220 , 260,120,30);
        back.addActionListener(this);
        panal.add(back);

        //image creation
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400,30,250,250);
        panal.add(img);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent Ae){
        if(Ae.getSource() == create){


            String aType = accountType.getSelectedItem();
            String sUserName = userName.getText();
            String sName = name.getText();
            String sPass = pass.getText();
            String sMeter = meter.getText();

            try{

                Conn c = new Conn();
                //dml command .... ikde data modify hotay manun aapn function
                //execute update use kelaa
                String query = null;
                if(aType.equals("Admin")){
                    query = "insert into login values('"+sMeter+"','"+sUserName+"','"+sName+"','"+sPass+"','"+aType+"')";
                }
                else{
                    query = "update login set userName ='"+sUserName+"' ,password= '"+sPass+"',user='"+aType+"' where meter_no ='"+sMeter+"' ";
                }
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created Sucessfully");

                setVisible(false);
                new LoginPage();


            }catch (Exception e){
                e.printStackTrace();
            }


        }else if(Ae.getSource() == back){
            setVisible(false);
            new LoginPage();
        }
    }
    public static void main(String[] args) {
        new SignUp();
    }
}
