package ENG;

import java.net.Socket;
import java.io.*;

public class Clienthanler implements Runnable{
    private String ID;
    private int point = 0;
    private boolean running = true;
    private Socket sk;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectOutputStream objOut;
    private QuestionAnalysis analysis;

    public Clienthanler(Socket sk){
        try {
            this.sk = sk;
            in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            out = new PrintWriter(sk.getOutputStream(), true);
            objOut = new ObjectOutputStream(sk.getOutputStream());
            out.println("welcome");
        }
        catch (IOException i){
            System.out.println(i);
        }
    }
    // sent message from ser to client
    public void write(String mss){

        out.println(mss);
    }

    public void sendQuiz(Quiz q){
        try{

            objOut.reset();
            objOut.writeObject(q);
            objOut.flush();

        }catch (Exception ex){}

    }

    public void sendQuestion(Problem p){
        try{

            //write("p");
            objOut.writeObject(p);


        }catch (Exception ex){}

    }




    public String getID(){

        return ID;
    }

    public void setQuestionAna(QuestionAnalysis analysis){
        this.analysis = analysis;
    }
    //Thread waiting for client
    @Override
    public void run() {

        String run = "";
        //read input only
        try {
            while(running){
                run = in.readLine();
                System.out.println(run);
                String[] data = run.split(" ");
                if(data[0].equals("id")){
                    ID = data[1];
                }
                else if(data[0].equals("ch")){

                    if(data[1].equals("A")){
                        analysis.SetA();
                    }
                    else if(data[1].equals("B")){
                        analysis.SetB();
                    }
                    else if(data[1].equals("C")){
                        analysis.SetC();
                    }
                    else if(data[1].equals("D")){
                        analysis.SetD();
                    }

                }
            }

        }
        catch (IOException i){
            System.out.println(i);
        }finally {
            try{
                sk.close();
                running = false;
            }catch (IOException i){}
        }
    }
    public void stop(){

        running = false;
    }
}
