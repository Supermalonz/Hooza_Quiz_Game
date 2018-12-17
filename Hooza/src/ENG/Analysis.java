package ENG;
import javax.swing.*;

public class Analysis{
    private JFrame jFrame;
    private QuestionAnalysis q;

    public void setQuesAna(QuestionAnalysis q){

        this.q = q;
    }

    public void init(){
        jFrame = new JFrame();
        jFrame.add(q);
        jFrame.setSize(980, 760);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}