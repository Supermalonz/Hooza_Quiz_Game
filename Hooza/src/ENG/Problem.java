package ENG;

import java.io.Serializable;


public class Problem  implements Serializable{
    private static final long serialVersionUID = 1L;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String correct;

    public Problem(String question, String ch1, String ch2, String ch3, String ch4,String correct){
        this.question = question;
        this.choice1 = ch1;
        this.choice2 = ch2;
        this.choice3 = ch3;
        this.choice4 = ch4;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Question: "+ question +
                " (A: " + choice1 +
                " B: " + choice2 +
                " C: " + choice3 +
                " D: " + choice4 +
                " ) Correct : " +correct;
    }
}