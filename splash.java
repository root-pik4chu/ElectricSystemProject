
import javax.swing.*;
import java.awt.*;
import java.rmi.UnexpectedException;

public class splash extends JFrame implements Runnable{
    //reason
    private JPanel panel1;

    Thread t; // thread new object

    splash(){
//image loading
        ImageIcon i1 = new ImageIcon(
                ClassLoader.getSystemResource
                        ("icon/elect.jpg")
        );

        Image i2 = i1.getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);



        JLabel img = new JLabel(i3);
        add(img);
        setVisible(true);

        // image adj
        int x = 1;
        for(int i = 1 ; i < 600; i+=4 , x += 1){
            setSize(i+x,i);
            setLocation(900 - ( (i+x)/2),550  - (i/2));

            try{
                Thread.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        // this = current class referance
        t = new Thread(this);
        t.start(); // internally call run method without calling run

        setVisible(true);
    }


    public void run(){
        //method overriding
        try{
            Thread.sleep(4000);
            setVisible(false);
            // login frame --- connect to the loginpage
            // after calling constructor our object is called automatically
            new LoginPage();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new splash();

    }
}
