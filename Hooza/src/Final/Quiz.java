package Final;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ink25
 */
public class Quiz implements Serializable {
    private static final long serialVersionUID = -348125175644252486L;
    private String quizName;
    private ArrayList<Problem> problems = new ArrayList<>();
    public Quiz(String name, ArrayList<Problem> problems) {
        quizName = name;
        this.problems  = problems;
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public void addProblem(Problem p){
        this.problems.add(p);
    }

    public void setName(String name){
        this.quizName = name;
    }

    public static Quiz read(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Quiz q = (Quiz)ois.readObject();
        ois.close();
        fis.close();
        return q;
    }

    public String getQuizName() {
        return quizName;
    }
    public void save(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
        fos.close();
    }
}
