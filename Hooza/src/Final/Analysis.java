package Final;


import javax.swing.*;
import java.awt.*;

public class Analysis{
    private JFrame jFrame = new JFrame();
    private QuestionAnalysis q;
    private boolean t = true;
    public void setQuesAna(QuestionAnalysis q){
        this.q = q;
        q.setBackground(new Color(188,143,143));
    }

    public void init(){
        
        
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