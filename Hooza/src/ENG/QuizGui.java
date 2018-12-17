package ENG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizGui extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1125527090979758382L;
    private Quiz quiz;
    private ArrayList<Problem> problems = new ArrayList<>();
    private JFileChooser jfc = new JFileChooser();
    JFrame frame = new JFrame("");
    JTextField nameQ = new JTextField("");
    JTextField ques = new JTextField("");
    JTextField ch1 = new JTextField("");
    JTextField ch2 = new JTextField("");
    JTextField ch3 = new JTextField("");
    JTextField ch4 = new JTextField("");
    JTextField ch5 = new JTextField("choice correct A-D");
    JLabel name = new JLabel("Quiz Name :");
    JLabel lquestion = new JLabel("Question :");
    JLabel lA = new JLabel("A :");
    JLabel lB = new JLabel("B :");
    JLabel lC = new JLabel("C :");
    JLabel lD = new JLabel("D :");
    JLabel lcorrect = new JLabel("Correct :");
    JTextArea tes = new JTextArea(20,20);

    JButton addPro = new JButton("Add Problem");
    JButton saveQ = new JButton("Save Quiz");
    JButton Load = new JButton("Load Quiz");
    JPanel  mainPN = new JPanel(new BorderLayout());
    JPanel tmpPN = new JPanel(new GridLayout(8,2));
    JPanel btnPN = new JPanel(new GridLayout(1, 1));

    public QuizGui(){
        initGUI();
    }

    public void initGUI(){

        tes.setEditable(false);
        addPro.addActionListener(this);
        saveQ.addActionListener(this);
        Load.addActionListener(this);
        name.setHorizontalAlignment(0);
        lquestion.setHorizontalAlignment(0);
        lA.setHorizontalAlignment(0);
        lB.setHorizontalAlignment(0);
        lC.setHorizontalAlignment(0);
        lD.setHorizontalAlignment(0);
        lcorrect.setHorizontalAlignment(0);
        name.setFont(new Font("MS Gothic", 0, 14));
        lquestion.setFont(new Font("MS Gothic", 0, 14));
        lA.setFont(new Font("MS Gothic", 0, 14));
        lB.setFont(new Font("MS Gothic", 0, 14));
        lC.setFont(new Font("MS Gothic", 0, 14));
        lD.setFont(new Font("MS Gothic", 0, 14));
        lcorrect.setFont(new Font("MS Gothic", 0, 14));
        addPro.setFont(new Font("MS Gothic", 0, 14));
        saveQ.setFont(new Font("MS Gothic", 0, 14));
        Load.setFont(new Font("MS Gothic", 0, 14));
        tmpPN.add(name);
        tmpPN.add(nameQ);
        tmpPN.add(lquestion);
        tmpPN.add(ques);
        tmpPN.add(lA);
        tmpPN.add(ch1);
        tmpPN.add(lB);
        tmpPN.add(ch2);
        tmpPN.add(lC);
        tmpPN.add(ch3);
        tmpPN.add(lD);
        tmpPN.add(ch4);
        tmpPN.add(lcorrect);
        tmpPN.add(ch5);
        btnPN.add(addPro);
        btnPN.add(saveQ);
        btnPN.add(Load);
        tmpPN.setBackground(new Color(51, 153, 255));
        tmpPN.setFont(new Font("MS Gothic", 0, 14));
        frame.setFont(new Font("MS Gothic", 0, 14));
        JScrollPane sp=new JScrollPane(tes,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainPN.add(tmpPN, BorderLayout.NORTH);
        mainPN.add(btnPN, BorderLayout.CENTER);
        mainPN.add(sp, BorderLayout.SOUTH);
        mainPN.setBackground(new Color(187, 157, 177));
        mainPN.setFont(new Font("MS Gothic", 0, 14));
        frame.setBackground(new Color(204, 255, 255));
        frame.add(mainPN);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        sp.setFont(new Font("MS Gothic", 0, 14));
    }

    public void actionPerformed(ActionEvent ev){
        String cmd = ev.getActionCommand();
        if(cmd.equals("Add Problem")){

            Problem p = new Problem(ques.getText(), ch1.getText(), ch2.getText(), ch3.getText(), ch4.getText(),ch5.getText());
            problems.add(p);
            ques.setText("");
            ch1.setText("");
            ch2.setText("");
            ch3.setText("");
            ch4.setText("");
            ch5.setText("");
            tes.append(p.toString() + "\n");
            System.out.println(p.toString());
        }
        else if(cmd.equals("Load Quiz")){
            if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    quiz = Quiz.read(jfc.getSelectedFile().getAbsolutePath());
                    nameQ.setText(quiz.getQuizName());
                    tes.setText("");
                    for (Problem o : quiz.getProblems()){
                        problems.add(o);
                        tes.append(o.toString() + "\n");
                    }



                } catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Failed to load quiz");
                }
            }

        }



        else{
            if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

                quiz = new Quiz(nameQ.getText(), problems);


                try {
                    quiz.save(jfc.getSelectedFile().getAbsolutePath());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Failed to save quiz");
                }
            }
        }
    }


}