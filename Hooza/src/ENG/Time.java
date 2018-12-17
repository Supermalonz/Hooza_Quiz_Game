package ENG;

import javax.swing.*;

public class Time extends JLabel implements Runnable{

    private int sec = 20;

    public int getSec() {
        return sec;
    }


    public Time(){
        setText(String.format("%02d", sec));
    }

    @Override
    public void run() {
        Time time = new Time();
        boolean play = true;
        while(true){
            if(time.getSec() <= 0){
                play = false;
            }
            sec --;
            setText(String.format("%02d", sec));
            try{
                Thread.sleep(1000);
            }
            catch (Exception i){
                System.out.println(i);
            }
        }
    }
}