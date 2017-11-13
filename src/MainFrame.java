/**
 * Created by 20160117 on 2017/11/14.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private Random rnd=new Random(System.currentTimeMillis());
    private LoginFrame loginframe;
    private Container cp;
    private Container cp2;
    private int width=500,height=500;
    private JMenuBar jmb=new JMenuBar();
    private JMenu jmF=new JMenu("File");
    private JMenu jmS=new JMenu("Set");
    private JMenu jmG=new JMenu("Game");
    private JMenu jmA=new JMenu("About");
    private JMenuItem jmiexit=new JMenuItem("Exit");
    private JMenuItem jmiloto=new JMenuItem("Loto");
    private JMenuItem jmimath=new JMenuItem("Random");
    private JDesktopPane jdp=new JDesktopPane();
    private JButton jbtnclose=new JButton("Close");
    private JButton jbtnregen=new JButton("Generate");
    private JPanel jplloto=new JPanel(new GridLayout(1,6,3,3));
    private JPanel jplloto2=new JPanel(new GridLayout(1,2,3,3));
    private JPanel jplmath=new JPanel(new GridLayout(4,3,3,3));
    private JInternalFrame jifloto=new JInternalFrame();
    private JInternalFrame jifmath=new JInternalFrame();
    private JButton jbtnmath[]=new JButton[12];
    private JLabel jlb[]=new JLabel[6];
    private JTextField jtfmath=new JTextField();
    private int Screenw=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int Screenh=Toolkit.getDefaultToolkit().getScreenSize().height;

    public MainFrame(LoginFrame log){
        loginframe=log;
        initComp();
    }
    private void initComp(){
        this.setBounds(Screenw/2-width/2,Screenh/2-height/2,width,height);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginframe.reset();
                loginframe.setVisible(true);
            }
        });
        
        this.setJMenuBar(jmb);
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        jmF.add(jmiexit);
        jmG.add(jmiloto);
        jmG.add(jmimath);
        this.setContentPane(jdp);
        cp=jifloto.getContentPane();
        cp.setLayout(new BorderLayout(5,5));
        cp.add(jplloto,BorderLayout.CENTER);
        cp.add(jplloto2,BorderLayout.SOUTH);
        cp2=jifmath.getContentPane();
        cp2.setLayout(new BorderLayout(5,5));
        cp2.add(jplmath,BorderLayout.CENTER);
        cp2.add(jtfmath,BorderLayout.NORTH);
        jifloto.setBounds(10,10,300,150);
        jifmath.setBounds(10,10,300,300);
        jplloto2.add(jbtnclose);
        jplloto2.add(jbtnregen);
        jtfmath.setEditable(false);
        for(int i=0;i<6;i++){
            jlb[i]=new JLabel();
            jlb[i].setOpaque(true);
            jlb[i].setBackground(new Color(102, 189, 248));
            jplloto.add(jlb[i]);
        }
        loto();
        jmiexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiloto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmimath.setAccelerator(KeyStroke.getKeyStroke('K',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginframe.setVisible(true);
                loginframe.reset();
                dispose();

            }
        });

        jmiloto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jifmath.dispose();
                jdp.add(jifloto);
                jifloto.setVisible(true);
                loto();

            }
        });

        for(int i=0;i<10;i++){
            jbtnmath[i]=new JButton();
            jplmath.add(jbtnmath[i]);
            jbtnmath[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton jbtn= (JButton) e.getSource();
                    jtfmath.setText(jtfmath.getText()+jbtn.getText());
                }
            });
        }
        jbtnmath[10]=new JButton(".");
        jbtnmath[11]=new JButton("clear");
        jplmath.add(jbtnmath[10]);
        jplmath.add(jbtnmath[11]);
        wow();
        jmimath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jifloto.dispose();
                jdp.add(jifmath);
                jifmath.setVisible(true);
                wow();
            }
        });
        jbtnclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jifloto.dispose();
            }
        });
        jbtnregen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loto();
            }
        });
        jbtnmath[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfmath.setText(jtfmath.getText()+".");
            }
        });
        jbtnmath[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfmath.setText("");
            }
        });
    }
    private void loto(){
        int data[]=new int[6];
        Boolean flag=true;
        int i=0;
        while(i<6){
            data[i]=rnd.nextInt(42)+1;
            flag=true;
            int j=0;
            while(j<i&&flag){
                if(data[i]==data[j]){
                    flag=false;
                }
                j++;
            }
            if(flag){
                jlb[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
    private void wow(){
        int i=0;
        Boolean flag=true;
        int data[]=new int[10];
        while(i<10){
            data[i]=rnd.nextInt(10);
            flag=true;
            int j=0;
            while(j<i&&flag){
                if(data[i]==data[j]){
                    flag=false;
                }
                j++;
            }
            if(flag){
                jbtnmath[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}