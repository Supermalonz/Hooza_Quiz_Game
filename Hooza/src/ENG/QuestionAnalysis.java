package ENG;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class QuestionAnalysis extends JPanel {

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
		g.drawString("A : ", 160, 130);
		g.fillRect(200, 120, 300 * answerCount[0]/5, 15);
		
		g.setColor(Color.BLUE);
		g.drawString("B : ", 160, 150);
		g.fillRect(200, 140, 300 * answerCount[1]/5, 15);
		
		g.setColor(Color.GREEN);
		g.drawString("C : ", 160, 170);
		g.fillRect(200, 160, 300 * answerCount[2]/5, 15);
		
		g.setColor(Color.ORANGE);
		g.drawString("D : ", 160, 190);
		g.fillRect(200, 180, 300 * answerCount[3]/5, 15);
		
		g.setColor(Color.BLACK);
		g.drawString("Answer : "+"A", 40, 100);
	}
}
