import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class updateInformation extends JFrame implements ActionListener {


JButton update,cancel;
    JTextField tfaddress ,tfcity ,tfstate ,tfemail ,tfphone;
    String meter;

    JLabel name;
    updateInformation( String meter){

        this.meter = meter;

        setBounds(300,300,1000,450);
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);


        JLabel heading = new JLabel("updateCustomerInfotmation");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font(null,0,20));
        add(heading);

        JLabel lblname = new JLabel("name");
        lblname.setBounds(30,70,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblname);


        //name is in global field for the database usage
        name = new JLabel("");
        name.setBounds(230,70,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(name);


        JLabel lblmeterNumber = new JLabel("meterNumber");
        lblmeterNumber.setBounds(30,110,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblmeterNumber);

        JLabel Mnumber = new JLabel("");
        Mnumber.setBounds(230,110,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(Mnumber);

        JLabel lbladdress = new JLabel("address");
        lbladdress.setBounds(30,150,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lbladdress);

        tfaddress = new JTextField("");
        tfaddress.setBounds(230,150,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(tfaddress);

        JLabel lblcity = new JLabel("city");
        lblcity.setBounds(30,190,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblcity);

         tfcity = new JTextField("");
        tfcity.setBounds(230,190,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(tfcity);

        JLabel lblstate = new JLabel("state");
        lblstate.setBounds(30,230,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblstate);

        tfstate = new JTextField("");
        tfstate.setBounds(230,230,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(tfstate);

        JLabel lblemail = new JLabel("email");
        lblemail.setBounds(30,270,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblemail);

        tfemail = new JTextField("");
        tfemail.setBounds(230,270,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(tfemail);


        JLabel lblPhone = new JLabel("phone");
        lblPhone.setBounds(30,310,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblPhone);

         tfphone = new JTextField("");
        tfphone.setBounds(230,310,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(tfphone);





        //extracting information on the basics of meter number which we pass through the
        // class from the database

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"' ");
            while (rs.next()){
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                Mnumber.setText(rs.getString("meter_no"));



            }

        }catch (Exception e){
            e.printStackTrace();
        }


        update = new JButton("update");
        update.setBounds(70,350,100,20);
        add(update);
        update.addActionListener(this);

        cancel = new JButton("cancel");
        cancel.setBounds(230,350,100,20);
        add(cancel);
        cancel.addActionListener(this);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,600,300);
        add(image);

        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == update){
            // update query remaining .... only
            //15.12 min

//            String lblname = name.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address='"+address+"',city='"+city+"',state='"+state+"',email='"+email+"',phone='"+phone+"' where meter_no='"+meter+"' ");
                JOptionPane.showMessageDialog(null,"information Is Updated");
                setVisible(false);

            }catch (Exception e ){
                e.printStackTrace();
            }

        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new updateInformation("");
    }


}
