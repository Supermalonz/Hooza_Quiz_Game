package Last;


import javax.swing.*;
import java.awt.*;

public class Analysis{
    private JFrame jFrame;
    private QuestionAnalysis q;

    public void setQuesAna(QuestionAnalysis q){
        this.q = q;
    }

    public void init(){
        jFrame = new JFrame();
        jFrame.add(q);
        
       
        
        jFrame.setSize(500, 400);
       jFrame.setResizable(false);
        jFrame.setLocation(600, 200);
        jFrame.setVisible(true);
    }
    public void hide(){
        jFrame.setVisible(false);
    }

//    public static void main(String[] args) {
//        Analysis a = new Analysis();
//        a.init();
//    }
}