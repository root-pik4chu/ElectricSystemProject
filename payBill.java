import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;

public class payBill extends JFrame implements ActionListener{


    Choice cmonth;
    JButton pay,back;

    String meter;
    payBill(String meter){
        this.meter = meter;


        setLayout(null);
        setBounds(300,250,900,600);

        JLabel heading = new JLabel("payBill");
        heading.setBounds(150,5,400,40);
        heading.setFont(new Font(null,0,20));
        add(heading);

//-------------------------------------------------------------------
        JLabel lblMeterNumber = new JLabel("meterNumber");
        lblMeterNumber.setBounds(35,80,200,20);
        add(lblMeterNumber);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(300,80,200,20);
        add(meterNumber);
//------------------------------------------------------------------

        JLabel lblName = new JLabel("name");
        lblName.setBounds(35,140,200,20);
        add(lblName);

        JLabel name = new JLabel("");
        name.setBounds(300,140,200,20);
        add(name);


        //-------------------------------------------------------------------

        JLabel lblmonth = new JLabel("month");
        lblmonth.setBounds(35,200,200,20);
        add(lblmonth);


        cmonth = new Choice();
        cmonth.setBounds(300,200,200,20);

        cmonth.add("jan");
        cmonth.add("feb");
        cmonth.add("march");
        cmonth.add("april");
        cmonth.add("may");
        cmonth.add("june");
        cmonth.add("july");
        cmonth.add("aug");
        cmonth.add("sept");
        cmonth.add("oct");
        cmonth.add("nov");
        cmonth.add("dec");
        add(cmonth);


        //-------------------------------------------------------------------
        JLabel lblunit = new JLabel("unit");
        lblunit.setBounds(35,260,200,20);
        add(lblunit);

        JLabel Labelunit = new JLabel("");
        Labelunit.setBounds(300,260,200,20);
        add(Labelunit);

        //-------------------------------------------------------------------

        JLabel lblbill = new JLabel("totalBill");
        lblbill.setBounds(35,320,200,20);
        add(lblbill);

        JLabel labelBill = new JLabel("");
        labelBill.setBounds(300,320,200,20);
        add(labelBill);

        //------------------------------------------------------------------

        JLabel lblstatus = new JLabel("status");
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);

        JLabel labelStatus = new JLabel("");
        labelStatus.setBounds(300,380,200,20);
        labelStatus.setForeground(Color.red);
        add(labelStatus);


        try{

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"' ");

            while(rs.next()){
                meterNumber.setText(meter);
                name.setText(rs.getString("name"));
            }


            // 'jan' - this is for the included values which are already in the database ...
           // single quote notation for these type of values...
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = 'jan' ");

            while(rs.next()){
                lblunit.setText(rs.getString("units"));
                lblbill.setText(rs.getString("totalBill"));
                lblstatus.setText(rs.getString("status"));

            }


        }catch (Exception e ){
            e.printStackTrace();
        }

        cmonth.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try{
                        Conn c = new Conn();
                        ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"' ");

                        while(rs.next()){
                            Labelunit.setText(rs.getString("units"));
                            labelBill.setText(rs.getString("totalBill"));
                            labelStatus.setText(rs.getString("status"));

                        }

                    }catch (Exception E ){
                        E.printStackTrace();
                    }
                }
        });

        // buttons

        pay = new JButton("pay");
//        pay.setBackground();
        pay.setBounds(100,400,100,25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("back");
//        pay.setBackground();
        back.setBounds(230,400,100,25);
        back.addActionListener(this);

        add(back);



        getContentPane().setBackground(Color.white);

        //Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400,120,600,300);
        add(img);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == pay){
            //update the information


            try{

                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'paid' where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"' ");


            }catch (Exception e ){
                e.printStackTrace();
            }

            setVisible(false);
            new paytm(meter);

        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {

        new payBill( " ");
    }
}
