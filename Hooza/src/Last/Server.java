package Last;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Serializable, Runnable{
    private volatile boolean exit = false;
    private ServerView gui;
    private Quiz quiz;
    private ArrayList<Problem> problems = new ArrayList<>();
    private ArrayList<Clienthanler> list = new ArrayList<>();
    private JTextArea ta;
    private ServerSocket ss;
    private Thread tr;
    private Clienthanler client;
    private QuestionAnalysis analysis;

    public void setList(JTextArea ta){
        this.ta = ta;
    }

    public void setQuiz(Quiz quiz){
        this.quiz = quiz;
    }

    public void pressStart(){
        for (Clienthanler i : list) {
            System.out.println("send start 2 client(s)");
            i.write("start");
            i.sendQuiz(quiz);
        }

    }
    public void pressNext(){
        for (Clienthanler i : list) {
            System.out.println("Next Ques");
            i.write("Next");

        }

    }
    public void pressStop(){
        for (Clienthanler i : list) {
            System.out.println("Waiting");
            i.write("Stop");

        }

    }


    public QuestionAnalysis getQuestAna(){
        return  analysis;
    }

    public void clrQuestAna(){
        analysis.clear();
    }

    public void run(){

        try {
            ss = new ServerSocket(5555);
            System.out.println("server running..");
            analysis = new QuestionAnalysis();
            while (!exit){
                client = new Clienthanler(ss.accept());
                client.setQuestionAna(analysis);
                tr = new Thread(client);  //tr stand for Thread
                tr.start();
                list.add(client);
                try{
                    Thread.sleep(10);
                    ta.append(client.getID() + "\n");
                }catch (Exception ex){
                    System.out.println("Thread sleep error :" + ex);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeserver(){
        try {
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stop(){

        exit = true;
        //client.stop();
    }
}