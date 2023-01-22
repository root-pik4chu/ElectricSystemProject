
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class meterInfo extends JFrame implements ActionListener {


    JLabel lblMeter;
    //tf - text field
    JTextField tfName,tfAddress,tfState,tfCity,tfEmail,tfPhone;
    Choice meterLocation,meterType,PhaseCode,billType;

    JButton Jnext,Jcancel,Jsubmit;

    String meterNumber;
    meterInfo(String meterNum){

        this.meterNumber = meterNum;

        setSize(700,450);
        setLocation(490,250);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(181, 234, 225));
        add(p);
//----------------------------------------------------------
        JLabel heading = new JLabel("meterInformation");
        heading.setBounds(200,10,200,25);
        heading.setFont(new Font(null,0,20));
        p.add(heading);
//-----------------------------------------------------------
        JLabel lblName = new JLabel("meterNo");
        lblName.setBounds(100,60,200,20);
        lblName.setFont(new Font(null,0,17));
        p.add(lblName);

        //random number
        JLabel lblmeterNO = new JLabel(meterNum);
        lblmeterNO.setBounds(240,60,200,20);
        lblmeterNO.setFont(new Font(null,0,17));
        p.add(lblmeterNO);


        //------------------------------------------------------

        JLabel lblmeterLocation = new JLabel("meterLocation");
        lblmeterLocation.setBounds(100,100,140,20);
        lblmeterLocation.setFont(new Font(null,0,17));
        p.add(lblmeterLocation);

        //generating the meter number form random function
//        lblMeter = new JLabel("");
//        lblMeter.setBounds(280,100,200,20);
//        lblMeter.setFont(new Font(null,0,17));
//        p.add(lblMeter);

        meterLocation = new Choice();
        meterLocation.add("inside");
        meterLocation.add("outside");
        meterLocation.setBounds(240,100,200,20);
        p.add(meterLocation);

//        // random number logic
//        Random ran = new Random();
//        long number = ran.nextLong() % 1000000;
//        lblMeter.setText( "" + Math.abs(number));

        //--------------------------------------------

        JLabel lblMeterType = new JLabel("meterType");
        lblMeterType.setBounds(100,140,140,20);
        lblMeterType.setFont(new Font(null,0,17));
        p.add(lblMeterType);

        meterType = new Choice();
        meterType.add("electric");
        meterType.add("solar");
        meterType.add("smart");

        meterType.setBounds(240,140,200,20);
        p.add(meterType);

        //-----------------------------------------------

        JLabel lblPhaseCode = new JLabel("phaseCode");
        lblPhaseCode.setBounds(100,180,140,20);
        lblPhaseCode.setFont(new Font(null,0,17));
        p.add(lblPhaseCode);

        PhaseCode = new Choice();
        PhaseCode.add("011");
        PhaseCode.add("022");
        PhaseCode.add("033");
        PhaseCode.add("044");
        PhaseCode.add("055");
        PhaseCode.add("066");
        PhaseCode.add("077");
        PhaseCode.add("088");
        PhaseCode.add("099");
        PhaseCode.add("000");

        PhaseCode.setBounds(240,180,200,20);
        p.add(PhaseCode);

        //-----------------------------------------------------

        JLabel lblState = new JLabel("billType");
        lblState.setBounds(100,220,140,20);
        lblState.setFont(new Font(null,0,17));
        p.add(lblState);

        billType = new Choice();
        billType.add("normal_Meter");
        billType.add("industrial_Meter");


        billType.setBounds(240,220,200,20);
        p.add(billType);
        //-------------------------------------------------------

        JLabel lbldays = new JLabel("Days");
        lbldays.setBounds(100,260,200,20);
        lbldays.setFont(new Font(null,0,17));
        p.add(lbldays);

        JLabel Jdays = new JLabel("30 Days");
        Jdays.setBounds(240,260,200,20);
        Jdays.setFont(new Font(null,0,17));
        p.add(Jdays);
        //----------------------------------------------------------

        JLabel lblPhone = new JLabel("note");
        lblPhone.setBounds(100,300,200,20);
        lblPhone.setFont(new Font(null,0,17));
        p.add(lblPhone);

        JLabel lblInfo = new JLabel("By default bill calculated for 30 days");
        lblInfo.setBounds(240,300,500,20);
        lblInfo.setFont(new Font(null,0,17));
        p.add(lblInfo);



        Jsubmit = new JButton("submit");
        Jsubmit.setBounds(240,340,80,25);
        //action performed by this button
        Jsubmit.addActionListener(this);
        p.add(Jsubmit);
//---------------------------------------------------------------

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
        if(ae.getSource() == Jsubmit){
//            tfName,tfAddress,tfState,tfCity,tfEmail,tfPhone;
            String meter = meterNumber;

            //this is meter number which is generated form the random function
            String location = meterLocation.getSelectedItem();

            String type = meterType.getSelectedItem();
            String code = PhaseCode.getSelectedItem();
            String typeBill = billType.getSelectedItem();
            String days = "30";



            String query = "insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typeBill+"','"+days+"' )";

            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
//                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"meterInfoSubmited");
                setVisible(false);
                //new frame


            }catch (Exception e ){
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == Jcancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new meterInfo("");
    }
}
