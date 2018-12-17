package Last;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class QuestionAnalysis extends JPanel {
        private Problem p;

    public void setP(Problem p) {
        this.p = p;
    }
	private int answerCount[] = {0, 0, 0, 0};

	public void SetA(){
		answerCount[0]++;
	}

	public void SetB(){
		answerCount[1]++;
	}
	public void SetC(){
		answerCount[2]++;
	}
	public void SetD(){
		answerCount[3]++;
	}

	public void clear(){
		for(int i=0; i<4; i ++){
			answerCount[i] = 0;
		}
	}

	public void paintComponent(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());


		g.setColor(Color.RED);
		g.drawString("A : ", 90, 120);
		g.fillRect(120, 108, 300 * answerCount[0]/5, 15);
		
		g.setColor(Color.BLUE);
                g.drawString("B : ", 90, 140);
		g.fillRect(120, 128, 300 * answerCount[1]/5, 15);
		
		g.setColor(Color.GREEN);
                g.drawString("C : ", 90, 160);
		g.fillRect(120, 148, 300 * answerCount[2]/5, 15);
		
		g.setColor(Color.ORANGE);
                g.drawString("D : ", 90, 180);
		g.fillRect(120, 168, 300 * answerCount[3]/5, 15);
		
		g.setColor(Color.BLACK);
		g.drawString("Answer : "+p.getCorrect(), 40, 100);
	}
}
