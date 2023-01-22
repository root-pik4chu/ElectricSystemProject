




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;
public class customerDetails extends JFrame  implements ActionListener {


    Choice meterNumber,cmonth ;

    // table
    JTable table;

    JButton search , print;
    customerDetails(){

        super("Customer Details");
        setSize(1200,650);
        setLocation(200,150);



        table = new JTable();

        try {

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));


        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);

        add(jp);


        //buttons

        print = new JButton("print");
        print.addActionListener(this);

        // it will take the border layout and the put the button in south direction by default

        add(print , "South");


        setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
            try{
                table.print();

            }catch (Exception E ){
                E.printStackTrace();
            }
    }

    public static void main(String[] args) {
        new customerDetails();
    }
}


