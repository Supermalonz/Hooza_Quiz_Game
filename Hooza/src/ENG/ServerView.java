package ENG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerView implements ActionListener, Serializable {

    private Server server;
    private Quiz quiz;
    private ArrayList<Problem> problems = new ArrayList<>();
    static ArrayList<Clienthanler> list = new ArrayList<>();
    private JFrame jf = new JFrame();
    private static JTextArea ta = new JTextArea(10, 10);
    private JButton bn = new JButton("Start");
    private JButton bnstop = new JButton("Stop");
    private JButton bn2 = new JButton("Open");
    private JButton bn3 = new JButton("Close");
    private JLabel stat = new JLabel("Status : Please Open Quiz");
    private int count = 0;
    private JTextField tf = new JTextField("Question :");
    private Thread serverThread;
    private InetAddress ip ;
    public ServerView(Server server){
        this.server = server;
    }

    public void init() {
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerView.class.getName()).log(Level.SEVERE, null, ex);
        }
        jf.setTitle("Host");
        stat.setHorizontalAlignment(0);
        jf.setSize(350, 600);
        jf.setResizable(true);
        ta.setText("IP: "+ip+"\n"+"Student List \n");
        JPanel tfp = new JPanel(new BorderLayout());
        JPanel tap = new JPanel(new BorderLayout());
        JPanel jp = new JPanel(new GridLayout(0, 1));
        tf.setEditable(false);
        bn.addActionListener(this);
        bn2.addActionListener(this);
        bn3.addActionListener(this);
        bnstop.addActionListener(this);
        tfp.add(stat);
        jp.add(tfp);
        jp.add(bn);
        jp.add(bnstop);
        jp.add(bn2);
        jp.add(bn3);
        jp.add(tf, BorderLayout.SOUTH);
        jf.add(jp);
        tap.add(ta);
        jf.add(tfp, BorderLayout.NORTH);
        jf.add(tap, BorderLayout.SOUTH);
        ta.setEditable(false);
        serverThread = new Thread(server);
        serverThread.start();
        jf.setVisible(true);
        jf.setLocation(850, 300);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public JTextArea getList(){
        return ta;
    }

    public void updateList(String id){
        ta.append(id + "\n");
    }

    public void actionPerformed(ActionEvent ev) {

        String cmd = ev.getActionCommand();
        if (cmd.equals("Start")) {

            server.pressStart();
            bn.setText("Next");
        } else if (cmd.equals("Open")) {
            JFileChooser fc = new JFileChooser();

            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    quiz = Quiz.read(fc.getSelectedFile().getAbsolutePath());
                    server.setQuiz(quiz);
                    stat.setText("Status:Loaded Quiz");

                } catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Failed to load quiz");
                }
            }

        }
        else if(cmd.equals("Stop")){
            Analysis ana = new Analysis();
            ana.setQuesAna(server.getQuestAna());
            ana.init();
        }
        else if (cmd.equals("Close")){
            server.stop();
            server.closeserver();
            System.out.println("Server Closed");

            jf.dispose();
        }
        else if (cmd.equals("Next")){
            server.pressNext();
            server.clrQuestAna();
        }
    }

}

