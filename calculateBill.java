import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Random;

public class calculateBill extends JFrame implements ActionListener {


    JLabel lblMeter,lblName,labelAddress;
    //tf - text field
    JTextField tfName,tfAddress,tfState,tfCity,tfEmail,tfPhone,tfunit;


    JButton Jnext,Jcancel;

    Choice meterNumber , cmonth;
    calculateBill(){

        setSize(700,400);
        setLocation(490,250);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(181, 234, 225));
        add(p);
//----------------------------------------------------------
        JLabel heading = new JLabel("CalculateBill");
        heading.setBounds(200,10,200,25);
        heading.setFont(new Font(null,0,20));
        p.add(heading);
//-----------------------------------------------------------
        JLabel lblNameNo = new JLabel("meterNumber");
        lblNameNo.setBounds(100,60,140,20);
        lblNameNo.setFont(new Font(null,0,17));
        p.add(lblNameNo);
//-------------------------------------------------------------
        //data Fetch from the database.
        meterNumber = new Choice();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()){
                meterNumber.add(rs.getString("meter_no"));
            }

        }catch (Exception e ){
            e.printStackTrace();
        }

        meterNumber.setBounds(240,60,200,20);
//        meterNumber.setFont(new Font(null,0,17));
        p.add(meterNumber);

        //------------------------------------------------------

        JLabel lblMeterNo = new JLabel("name");
        lblMeterNo.setBounds(100,100,200,20);
        lblMeterNo.setFont(new Font(null,0,17));
        p.add(lblMeterNo);

        //generating the meter number form random function
        lblName = new JLabel("");
        lblName.setBounds(280,100,200,20);
        lblName.setFont(new Font(null,0,17));
        p.add(lblName);


        //--------------------------------------------

        JLabel lblAddress = new JLabel("address");
        lblAddress.setBounds(100,140,200,20);
        lblAddress.setFont(new Font(null,0,17));
        p.add(lblAddress);

        labelAddress = new JLabel();
        labelAddress.setBounds(240,140,200,20);
        p.add(labelAddress);

        //fetch the values
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
            while (rs.next()){
                lblName.setText(rs.getString("name"));
                labelAddress.setText(rs.getString("address"));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

        //event hit for the meter number changing
        // if meter number changes then the values should be change with given meter number

        meterNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
                    while (rs.next()){
                        lblName.setText(rs.getString("name"));
                        labelAddress.setText(rs.getString("address"));
                    }
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
        //-----------------------------------------------

        JLabel lblCity = new JLabel("unitConsume");
        lblCity.setBounds(100,180,200,20);
        lblCity.setFont(new Font(null,0,17));
        p.add(lblCity);

        tfunit = new JTextField();
        tfunit.setBounds(240,180,200,20);
        p.add(tfunit);

        //-----------------------------------------------------

        JLabel lblState = new JLabel("month");
        lblState.setBounds(100,220,140,20);
        lblState.setFont(new Font(null,0,17));
        p.add(lblState);

        cmonth = new Choice();
        cmonth.setBounds(240,220,200,20);
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

        p.add(cmonth);
        //-------------------------------------------------------



        Jnext = new JButton("submit");
        Jnext.setBounds(240,300,80,25);
        //action performed by this button
        Jnext.addActionListener(this);
        p.add(Jnext);

        Jcancel = new JButton("cancel");
        Jcancel.setBounds(360,300,80,25);
        //action performed by this button
        Jcancel.addActionListener(this);
        p.add(Jcancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
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
//
            //this is meter number which is generated form the random function
//            String meter = lblMeter.getText();

            String meter = meterNumber.getSelectedItem();
            String unit = tfunit.getText();
            String month = cmonth.getSelectedItem();

            int totalBill = 0;

            int unit_consume = Integer.parseInt(unit);


            String query = "select * from tax";


            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                while (rs.next()){
                    totalBill += unit_consume * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                }

            }

            catch (Exception e ){
                e.printStackTrace();
            }

            String query2 = "insert into bill values('"+meter+"','"+month+"','"+unit+"','"+totalBill+"','Not Paid')";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"customerBillUpdated");
                setVisible(false);

            }catch (Exception e ){
                e.printStackTrace();
            }

        }
        else if (ae.getSource() == Jcancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {

        new calculateBill();
    }
}
