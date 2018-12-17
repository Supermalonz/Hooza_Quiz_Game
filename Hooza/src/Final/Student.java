package Final;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student implements ActionListener, Serializable {

    private MySql sql = new MySql();
    private static BufferedReader in;
    private static PrintWriter out;
    private static ObjectInputStream objIn;
    private Problem pro;
    private String name;
    private int score;
    private JFrame fr = new JFrame();
    private JPanel loginPanel, gamePanel;
    private static Quiz quiz;
    private static ArrayList<Problem> problems;
    private static Problem prob;
    private JButton ansA, ansB, ansC, ansD;
    private static String dataIn;
    private User user;
    JFrame login = new JFrame();
    private JFrame game = new JFrame();
    private static JLabel lquestion = new JLabel("Question :", SwingConstants.CENTER);
    private static JLabel lA = new JLabel("A :", SwingConstants.CENTER);
    private static JLabel lB = new JLabel("B :", SwingConstants.CENTER);
    private static JLabel lC = new JLabel("C :", SwingConstants.CENTER);
    private static JLabel lD = new JLabel("D :", SwingConstants.CENTER);
    private int score1 = 0;
    private static int count = 0;
    private boolean timeRunning = true;
    private JLabel lblTime;
    private boolean running = true;
    private JPanel tmpPN;
    private Thread t1;
    private Time1 time = new Time1();
    private JPanel waiting = new JPanel();
    private JLabel wait = new JLabel("Waiting For Next Question....");
    private JFrame waitt = new JFrame();
    private int size;
    private Socket s;
    public Student(User u) {
        this.user = u;
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new GridLayout(0, 1));
        loginPanel.setSize(800, 600);
        JTextField ipField = new JTextField();
        JTextField usernameField = new JTextField();
        JButton connectButton = new JButton("Connect");
        JButton exitButton = new JButton("Cancel");
        loginPanel.setFont(new Font("MS Gothic", 0, 14));
        JLabel ipname = new JLabel("IP Address");
        ipname.setFont(new Font("MS Gothic", 0, 14));
        
        JLabel usernamel = new JLabel("Username");
        usernamel.setFont(new Font("MS Gothic", 0, 14));
        loginPanel.add(ipname);
        loginPanel.add(ipField);
        loginPanel.add(usernamel);
        usernameField.setText(user.getId() + "");
        usernameField.setEditable(false);
        loginPanel.add(usernameField);
        loginPanel.add(connectButton);
        loginPanel.add(exitButton);
        loginPanel.setBackground(new Color(204, 255, 255));
        login.add(loginPanel);
        login.setResizable(false);
        login.setSize(200, 200);
        login.setLocation(600, 300);
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        JPanel tmpPN = new JPanel(new GridLayout(5, 1));
        JPanel btnPN = new JPanel(new GridLayout(2, 2));
        tmpPN.add(lquestion);
        tmpPN.add(lA);
        tmpPN.add(lB);
        tmpPN.add(lC);
        tmpPN.add(lD);
        gamePanel.add(tmpPN);
        ansA = new JButton("A");

        ansB = new JButton("B");

        ansC = new JButton("C");

        ansD = new JButton("D");
        btnPN.add(ansA);
        btnPN.add(ansB);
        btnPN.add(ansC);
        btnPN.add(ansD);
        gamePanel.add(btnPN, BorderLayout.SOUTH);
        game.add(gamePanel);
        game.setSize(500, 400);
        game.setResizable(false);
        game.setLocation(600, 200);
        connectButton.addActionListener(this);
        ansA.addActionListener(this);
        ansB.addActionListener(this);
        ansC.addActionListener(this);
        ansD.addActionListener(this);
        login.setDefaultCloseOperation(login.HIDE_ON_CLOSE);
        game.setDefaultCloseOperation(game.HIDE_ON_CLOSE);
        
        
        
        //wait
        wait.setFont(new Font("MS Gothic", 0, 14));
        waiting.add(wait);
        waitt.add(waiting);
        waiting.setBackground(new Color(55, 127, 234));
         waitt.setSize(500, 400);
       waitt.setResizable(false);
        waitt.setLocation(600, 200);
        

    }

    public int gameplay(Quiz q) {
        time.setFont(new Font("MS Gothic", 0, 14));
        Thread tim = new Thread(time);
        tim.start();
        //lblTime = new JLabel("60");
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        tmpPN = new JPanel(new GridLayout(6, 1));
        JPanel btnPN = new JPanel(new GridLayout(2, 2));
        tmpPN.add(time);
        tmpPN.add(lquestion);
        tmpPN.add(lA);
        tmpPN.add(lB);
        tmpPN.add(lC);
        tmpPN.add(lD);
        //188	143	143
        tmpPN.setBackground(new Color(55, 127, 234));
        gamePanel.add(tmpPN);
        ansA = new JButton("A");

        ansB = new JButton("B");

        ansC = new JButton("C");

        ansD = new JButton("D");
        btnPN.add(ansA);
        btnPN.add(ansB);
        btnPN.add(ansC);
        btnPN.add(ansD);

        gamePanel.add(btnPN, BorderLayout.SOUTH);
        game.add(gamePanel);
        game.setSize(500, 400);
        game.setResizable(false);
        game.setLocation(600, 200);
        ansA.addActionListener(this);
        ansB.addActionListener(this);
        ansC.addActionListener(this);
        ansD.addActionListener(this);
        game.setVisible(true);
        boolean playing = true;
        count=0;
        while (playing) {
            size = q.getProblems().size();
            prob = q.getProblems().get(count);
            lquestion.setText("\t\t" + "Question:" + prob.getQuestion());
            lA.setText("\t\t\tA :" + prob.getChoice1());
            lB.setText("\t\t\tB :" + prob.getChoice2());
            lC.setText("\t\t\tC :" + prob.getChoice3());
            lD.setText("\t\t\tD :" + prob.getChoice4());
            try {
                dataIn = in.readLine();
                if (dataIn.equals("Next")) {
                    game.setVisible(true);
                    waitt.setVisible(false);
                    count++;
                    time.stop();
                    
                }
                else if(dataIn.equals("Stop")){
                    game.setVisible(false);
                    waitt.setVisible(true);
                }
                else if(dataIn.equals("close")){
                    playing = false;
                    game.setVisible(false);
                    waitt.setVisible(false);
                    in.close();
                    out.close();
                    objIn.close();
                    s.close();
                    time.close();
                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (count == q.getProblems().size()){
                playing = false;
                 game.setVisible(true);
                    waitt.setVisible(false);
                System.out.println("end");
            }


        }             
        time.stop();
        time.close();
        time.setText("");
        lquestion.setText("End Your score is : " + score);
        lA.setText("");
        lB.setText("");
        lC.setText("");
        lD.setText("");
        ansA.setEnabled(false);
        ansB.setEnabled(false);
        ansC.setEnabled(false);
        ansD.setEnabled(false);
        sql.updateScore(user.getId(), user.getScore() + score);
        int total = user.getScore() + score;
        lA.setText("Your total score is " + total);
        game.setDefaultCloseOperation(game.DISPOSE_ON_CLOSE);
        return score;

    }

    class Time1 extends JLabel implements Runnable {

        private boolean run = true;
        private boolean play = true;

        public Time1() {

            setText(String.format("%02d", 20));
        }

        @Override
        public void run() {
            while (run) {

                for (int i = 20; i >= 0; i--) {

                    try {
                        if (i == 0 && count != size) {
                            new Time1();
                            
                            game.setVisible(false);
                            waitt.setVisible(true);

                        }
                        if (!timeRunning && play) {
                            System.out.println("in");
                            i = 20;
                            timeRunning = true;

                        }
                        if (timeRunning) {
                            setText(String.format("Time Remaining : %02d", i));
                            Thread.sleep(800);

                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.print(i + " ");
                }
            }

        }

        public void stop() {
            timeRunning = false;

        }

        public void close() {
            run = false;
            play = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Connect")) {
            login.setVisible(false);

            try {
                 s = new Socket("127.0.0.1", 5555);
                
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(s.getOutputStream(), true);
                objIn = new ObjectInputStream(s.getInputStream());
                out.println("id " + user.getId());

                new Thread(new Runnable() {
                    //read thread
                    @Override
                    public void run() {
                        while (true) {

                            try {
                                dataIn = in.readLine();
                                if (dataIn.equals("start")) {

                                    System.out.println("game start");
                                    try {
                                       
                                        System.out.println("ink");
                                        quiz = (Quiz) objIn.readObject();
                                        
                                        System.out.println("recv obj");
                                        System.out.println(quiz.toString());
                                        gameplay(quiz);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                }
                            } catch (IOException ex) {
                                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                }).start();

            } catch (IOException e1) {
                e1.printStackTrace();
                game.setVisible(false);
            }
        }
        if (cmd.equals("A")) {
            out.println("ch A");
            if (prob.getCorrect().equals("A")) {
                score++;
                game.setVisible(false);
                waitt.setVisible(true);
            } else {
                game.setVisible(false);
                waitt.setVisible(true);
            }

        }
        if (cmd.equals("B")) {
            out.println("ch B");
            if (prob.getCorrect().equals("B")) {
                score++;
                game.setVisible(false);
                waitt.setVisible(true);
            } else {
                game.setVisible(false);
                waitt.setVisible(true);
            }

        }
        if (cmd.equals("C")) {
            out.println("ch C");
            if (prob.getCorrect().equals("C")) {
                score++;
                game.setVisible(false);
                waitt.setVisible(true);
            } else {
                game.setVisible(false);
                waitt.setVisible(true);

            }

        }
        if (cmd.equals("D")) {
            out.println("ch D");
            if (prob.getCorrect().equals("D")) {
                score++;
                game.setVisible(false);
                waitt.setVisible(true);
            } else {
                game.setVisible(false);
                waitt.setVisible(true);
            }

        }
    }
}
