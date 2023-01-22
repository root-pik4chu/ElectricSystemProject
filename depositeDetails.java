import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;
public class depositeDetails extends JFrame  implements ActionListener {


    Choice meterNumber,cmonth ;

    // table
    JTable table;

    JButton search , print;
    depositeDetails(){

        super("Deposite Details");
        setSize(700,700);
        setLocation(500,200);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblmeterNumber = new JLabel("search by meter number");
        lblmeterNumber.setBounds(20,20,140,20);
        add(lblmeterNumber);

        meterNumber = new Choice();
        meterNumber.setBounds(180,20,150,20);
        add(meterNumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");

            while (rs.next()){
                meterNumber.add(rs.getString("meter_no"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel lblmonth = new JLabel("search by month");
        lblmonth.setBounds(360,20,100,20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(470,20,150,20);

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



        table = new JTable();

        try {

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));


        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,700,600);
        add(jp);


        //buttons

        search = new JButton("search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);


        setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == search){
            String query = "select * from bill where meter_no ='"+meterNumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));

            }catch (Exception E){
                E.printStackTrace();
            }

        }
        else if (e.getSource() == print){

            try{
                table.print();

            }catch (Exception E ){
                E.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new depositeDetails();
    }
}

