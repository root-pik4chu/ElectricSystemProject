import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paytm extends JFrame implements ActionListener {


    String meter;
    JButton back;
    paytm(String meter){
    this.meter = meter;

    //same as the text field but lil diffrent it gives area for text but in lil large amount

    JEditorPane j = new JEditorPane();
    j.setEditable(false);

    try{
        j.setPage("https://paytm.com/online-payment");

    }catch(Exception e ){
        j.setContentType("text/html");
        j.setText("<html>could not load<html>");
    }

    JScrollPane pane = new JScrollPane(j);
    add(pane);


    back = new JButton("back");
    back.setBounds(640,20,80,30);
    back.addActionListener(this);
    j.add(back);

    setSize(800,600);
    setLocation(400,150);
    setVisible(true);


    }
    public void actionPerformed(ActionEvent e){

        setVisible(false);
        new payBill(meter);

    }

    public static void main(String[] args) {
        new paytm("" );
    }
}


