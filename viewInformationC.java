import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class viewInformationC extends JFrame implements ActionListener {




    JButton cancel;

    viewInformationC(String meter){
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel heading = new JLabel("viewCustomerInfotmation");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font(null,0,20));
        add(heading);

        JLabel lblname = new JLabel("name");
        lblname.setBounds(70,80,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(250,80,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(name);


        JLabel lblmeterNumber = new JLabel("meterNumber");
        lblmeterNumber.setBounds(70,140,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblmeterNumber);

        JLabel Mnumber = new JLabel("");
        Mnumber.setBounds(250,140,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(Mnumber);

        JLabel lbladdress = new JLabel("address");
        lbladdress.setBounds(70,200,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250,200,200,20);
//        lblname.setFont(new Font(null,0,20));
        add(address);

        JLabel lblcity = new JLabel("city");
        lblcity.setBounds(70,260,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250,260,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(city);

        JLabel lblstate = new JLabel("state");
        lblstate.setBounds(500,80,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(650,80,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(state);

        JLabel lblemail = new JLabel("email");
        lblemail.setBounds(500,140,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblemail);

        JLabel email = new JLabel("");
        email.setBounds(650,140,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(email);


        JLabel lblPhone = new JLabel("phone");
        lblPhone.setBounds(500,200,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(lblPhone);

        JLabel phone = new JLabel("");
        phone.setBounds(650,200,100,20);
//        lblname.setFont(new Font(null,0,20));
        add(phone);


        //extracting information on the basics of meter number which we pass through the
        // class from the database

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"' ");
            while (rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                Mnumber.setText(rs.getString("meter_no"));



            }

        }catch (Exception e){
            e.printStackTrace();
        }



        cancel = new JButton("cancel");
        cancel.setBounds(350,350,100,20);
        add(cancel);
        cancel.addActionListener(this);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/customer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20,360,600,300);
        add(image);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        setVisible(false);
    }
    public static void main(String[] args) {
        new viewInformationC("");
    }
}
