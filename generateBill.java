import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class generateBill extends JFrame implements ActionListener {


    JTextArea area;
    Choice cmonth;
    String meter;
    JButton bill;
    generateBill(String meter){

        this.meter = meter;

        setSize(500,800);
        setLocation(550,50);

        //panal

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        //label
        JLabel heading = new JLabel("generateBill");
        // get from the previous pages meter
        JLabel meterNumber = new JLabel(meter);

        cmonth = new Choice();

//        cmonth.setBounds(470,20,150,20);

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


         area = new JTextArea(50,15);
        area.setText("\n\n\n\t    -----click on the-----\n\tgenerate bill button to get \n\t bill of the selected month");
        area.setFont(new Font("Senserif",Font.ITALIC,18));



        JScrollPane Jpane = new JScrollPane(area);

         bill = new JButton("generateBill");
         bill.addActionListener(this);

        panel.add(heading);
        panel.add(meterNumber);
        panel.add(cmonth);

        add(Jpane,"Center");
        add(bill,"South");


        add(panel,"North");


        setVisible(true);


    }


    public void actionPerformed(ActionEvent ae){
        try{


            Conn c = new Conn();
            String month = cmonth.getSelectedItem();

            //writting work with concatenation
            /*
            for the date purpose we replace the information on the page with other information
            but in next case we have to print information for the given selected month
            so in that case instade of replace we append the information

             */
            area.setText("\treliance power limited\t\nelectricity bill generated for the month of " +month+" 2023\n\n\n");
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no ='"+meter+"' ");
            if(rs.next()){

                area.append("\n    customerName :    "+rs.getString("name"));
                area.append("\n    meterNumber :    "+rs.getString("meter_no"));
                area.append("\n    address :    "+rs.getString("address"));
                area.append("\n    city :   "+rs.getString("city"));
                area.append("\n    state :  "+rs.getString("state"));
                area.append("\n    email :  "+rs.getString("email"));
                area.append("\n    phoneNumber :    "+rs.getString("phone"));
                area.append("\n------------------------------------------------------------------------");
                area.append("\n");

            }

            // meter information remaining only ....
             rs = c.s.executeQuery("select * from meter_info where meter_no ='"+meter+"' ");
            if(rs.next()){

                area.append("\n    meterLocation :    "+rs.getString("meter_loc"));
                area.append("\n    meterType :    "+rs.getString("meter_type"));
                area.append("\n    meterNumber :    "+rs.getString("meter_no"));
                area.append("\n    phaseCode :    "+rs.getString("phasecode"));
                area.append("\n    bill_type :   "+rs.getString("bill_type"));
                area.append("\n    days :  "+rs.getString("days"));
                area.append("\n------------------------------------------------------------------------");
                area.append("\n");

            }
            rs = c.s.executeQuery("select * from tax ");
            if(rs.next()){

                area.append("\n    cost_per_unit :    "+rs.getString("cost_per_unit"));
                area.append("\n    meter_rent :    "+rs.getString("meter_rent"));
                area.append("\n    service_charge :    "+rs.getString("service_charge"));
                area.append("\n    service_tax :    "+rs.getString("service_tax"));
                area.append("\n    swacch_bharat_cess :   "+rs.getString("swacch_bharat_cess"));
                area.append("\n    fixed_tax :   "+rs.getString("fixed_tax"));

                area.append("\n------------------------------------------------------------------------");
                area.append("\n");

            }

            rs = c.s.executeQuery("select * from bill where meter_no='"+meter+"' AND month='"+cmonth.getSelectedItem()+"' ");
            if(rs.next()){

                area.append("\n    meterNumber :    "+rs.getString("meter_no"));
                area.append("\n    Month :    "+rs.getString("month"));
                area.append("\n    untis :    "+rs.getString("units"));
                area.append("\n    status :    "+rs.getString("status"));
                area.append("\n------------------------------------------------------------------------");

                area.append("\n    totalBill :   "+rs.getString("totalBill"));
//                area.append("\n    fixed_tax :   "+rs.getString("fixed_tax"));

                area.append("\n------------------------------------------------------------------------");
                area.append("\n");

            }

        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new generateBill("");
    }
}
