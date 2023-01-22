import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener{


    String aType , meter;
    Project( String aType , String meter){

        this.aType = aType;
        this.meter = meter;

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1920, 1080,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        add(i4);

        //menu bar
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb  );

        //menu item
        JMenu master = new JMenu("Master");
//        master.setForeground(Color.CYAN);



        JMenuItem newCustomer = new JMenuItem("newCustomer");
        newCustomer.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        newCustomer.setMnemonic('D');
        newCustomer.addActionListener(this);
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D ,ActionEvent.CTRL_MASK));

        master.add(newCustomer);


        //second menu
        JMenuItem customerDetails = new JMenuItem("customerDetails");
        customerDetails.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        customerDetails.setMnemonic('M');
        customerDetails.addActionListener(this);
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M ,ActionEvent.CTRL_MASK));
        master.add(customerDetails);

        JMenuItem depositeDetails = new JMenuItem("depositeDetails");
        depositeDetails.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        depositeDetails.setMnemonic('N');
        depositeDetails.addActionListener(this);
        depositeDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N ,ActionEvent.CTRL_MASK));
        master.add(depositeDetails);


        JMenuItem calcuateBill = new JMenuItem("calcuateBill");
        calcuateBill.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        calcuateBill.setMnemonic('B');
        calcuateBill.addActionListener(this);
        calcuateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B ,ActionEvent.CTRL_MASK));
        master.add(calcuateBill);

        //---------------------------------------------
        JMenu info = new JMenu("Information");
//        info.setForeground(Color.RED);


        JMenuItem updateInfo = new JMenuItem("updateInformation");
        updateInfo.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        updateInfo.setMnemonic('U');
        updateInfo.addActionListener(this);
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U ,ActionEvent.CTRL_MASK));
        info.add(updateInfo);

        JMenuItem vievInfo = new JMenuItem("vievInfo");
        vievInfo.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        vievInfo.setMnemonic('V');
        vievInfo.addActionListener(this);
        vievInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V ,ActionEvent.CTRL_MASK));
        info.add(vievInfo);

        //------------------------------------------------
        JMenu user = new JMenu("User");
//        user.setForeground(Color.blue);


        JMenuItem payBill = new JMenuItem("payBill");
        payBill.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        payBill.setMnemonic('P');
        payBill.addActionListener(this);
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P ,ActionEvent.CTRL_MASK));
        user.add(payBill);

        JMenuItem billDetails = new JMenuItem("billDetails");
        billDetails.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        billDetails.setMnemonic('O');
        billDetails.addActionListener(this);
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O ,ActionEvent.CTRL_MASK));
        user.add(billDetails);
        //-----------------------------------------------

        JMenu report = new JMenu("report");
//        report.setForeground(Color.blue);


        JMenuItem generateBill = new JMenuItem("generateBill");
        generateBill.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        generateBill.setMnemonic('G');
        generateBill.addActionListener(this);

        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G ,ActionEvent.CTRL_MASK));
        report.add(generateBill);

        //--------------------------------------

        JMenu utility = new JMenu("utility");
//        report.setForeground(Color.blue);


        JMenuItem notePad = new JMenuItem("notePad");
        notePad.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        notePad.setMnemonic('F');
        notePad.addActionListener(this);
        notePad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F ,ActionEvent.CTRL_MASK));
        utility.add(notePad);

        JMenuItem calculator = new JMenuItem("calculator");
        calculator.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        calculator.setMnemonic('C');
        calculator.addActionListener(this);
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C ,ActionEvent.CTRL_MASK));
        utility.add(calculator);



        //---------------------------------------
        JMenu exit = new JMenu("exit");
//        report.setForeground(Color.blue);

        JMenuItem Exit = new JMenuItem("exit");
        Exit.setFont(new Font(null,Font.PLAIN,14));
        //set mnemonic item - ctrl + d wali cheeze ...
        Exit.setMnemonic('W');
        Exit.addActionListener(this);
        Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W ,ActionEvent.CTRL_MASK));



        //working the tabs with condition
        //who can see the information and who cannot see the information
        // on the dash board


        if(aType.equals("Admin")){
            mb.add(master);
        }else {
            mb.add(info);
            mb.add(report);
            mb.add(user);
        }

        mb.add(utility);
        mb.add(exit);

        exit.add(Exit);

        setLayout(new FlowLayout());

        setVisible(true);


    }

    public void actionPerformed(ActionEvent e){
        //if anything clicks on the slide then the msg contains that value
        // for eg - if click on customerDetails then value is customerDetails

            String msg = e.getActionCommand();
            if(msg.equals("newCustomer")){
                    new newCustomer();
            }else if(msg.equals("customerDetails")){
                    new customerDetails();
            }else if(msg.equals("depositeDetails")){
                    new depositeDetails();
            }else if(msg.equals("calcuateBill")){
                    new calculateBill();
            }else if(msg.equals("vievInfo")){
                    new viewInformationC(meter);
            }else if(msg.equals("updateInformation")){
                    new updateInformation(meter);
            }else if(msg.equals("billDetails")){
                new billDetails(meter);


                //this is for the opening notpad and calculator with clicks

            }else if(msg.equals("notePad")){
                try{
                    Runtime.getRuntime().exec("notepad.exe");
                }catch (Exception E){
                    E.printStackTrace();

                }
            }else if(msg.equals("calculator")){
                try{
                    Runtime.getRuntime().exec("calc.exe");
                }catch (Exception E){
                    E.printStackTrace();

                }
            }
            else if(msg.equals("exit")){
                setVisible(false);
                new LoginPage();
            }

            else if(msg.equals("payBill")){
                new payBill(meter);
            }
            else if(msg.equals("generateBill")){
                new generateBill(meter);
            }

    }

    public static void main(String[] args) {
        new Project(" ","");
    }
}
/*
When it comes to knowing the real you,
i feel like i bearly scratched the surface .
but if you let me ,
i would like to learn everything, little by little.

 */


