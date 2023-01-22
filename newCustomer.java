import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {


    JLabel lblMeter;
    //tf - text field
    JTextField tfName,tfAddress,tfState,tfCity,tfEmail,tfPhone;


    JButton Jnext,Jcancel;
    newCustomer(){

        setSize(700,450);
        setLocation(490,250);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(181, 234, 225));
        add(p);
//----------------------------------------------------------
        JLabel heading = new JLabel("newCusotmer");
        heading.setBounds(200,10,200,25);
        heading.setFont(new Font(null,0,20));
        p.add(heading);
//-----------------------------------------------------------
        JLabel lblName = new JLabel("newCusotmer");
        lblName.setBounds(100,60,200,20);
        lblName.setFont(new Font(null,0,17));
        p.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(240,60,200,20);
        p.add(tfName);

        //------------------------------------------------------

        JLabel lblMeterNo = new JLabel("meterNumber");
        lblMeterNo.setBounds(100,100,200,20);
        lblMeterNo.setFont(new Font(null,0,17));
        p.add(lblMeterNo);

        //generating the meter number form random function
        lblMeter = new JLabel("");
        lblMeter.setBounds(280,100,200,20);
        lblMeter.setFont(new Font(null,0,17));
        p.add(lblMeter);

        // random number logic
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        lblMeter.setText( "" + Math.abs(number));

        //--------------------------------------------

        JLabel lblAddress = new JLabel("address");
        lblAddress.setBounds(100,140,200,20);
        lblAddress.setFont(new Font(null,0,17));
        p.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(240,140,200,20);
        p.add(tfAddress);

        //-----------------------------------------------

        JLabel lblCity = new JLabel("city");
        lblCity.setBounds(100,180,200,20);
        lblCity.setFont(new Font(null,0,17));
        p.add(lblCity);

        tfCity = new JTextField();
        tfCity.setBounds(240,180,200,20);
        p.add(tfCity);
        //-----------------------------------------------------

        JLabel lblState = new JLabel("state");
        lblState.setBounds(100,220,200,20);
        lblState.setFont(new Font(null,0,17));
        p.add(lblState);

        tfState = new JTextField();
        tfState.setBounds(240,220,200,20);
        p.add(tfState);
        //-------------------------------------------------------

        JLabel lblEmail = new JLabel("email");
        lblEmail.setBounds(100,260,200,20);
        lblEmail.setFont(new Font(null,0,17));
        p.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(240,260,200,20);
        p.add(tfEmail);
        //----------------------------------------------------------

        JLabel lblPhone = new JLabel("phoneNumber");
        lblPhone.setBounds(100,300,200,20);
        lblPhone.setFont(new Font(null,0,17));
        p.add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(240,300,200,20);
        p.add(tfPhone);


        Jnext = new JButton("next");
        Jnext.setBounds(240,340,80,25);
        //action performed by this button
        Jnext.addActionListener(this);
        p.add(Jnext);

        Jcancel = new JButton("cancel");
        Jcancel.setBounds(360,340,80,25);
        //action performed by this button
        Jcancel.addActionListener(this);
        p.add(Jcancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img,"West");

        getContentPane().setBackground(Color.white);


        setVisible(true);



    }
    public void actionPerformed(ActionEvent ae){

        // now will use this fiiled for fetching the value
        if(ae.getSource() == Jnext){
//            tfName,tfAddress,tfState,tfCity,tfEmail,tfPhone;
            String name = tfName.getText();

            //this is meter number which is generated form the random function
            String meter = lblMeter.getText();

            String add = tfAddress.getText();
            String state = tfState.getText();
            String city = tfCity.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();



            String query = "insert into customer values('"+name+"','"+meter+"','"+add+"','"+city+"','"+state+"','"+email+"','"+phone+"' )";
            String query2 = "insert into login values('"+meter+"' ,' ', '"+name+"' ,' ' , ' ')";


            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"customerDetailsAdded");
                setVisible(false);
                //new frame

                new meterInfo(meter);
            }catch (Exception e ){
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == Jcancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new newCustomer();
    }
}
