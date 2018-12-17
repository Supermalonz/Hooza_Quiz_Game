package Last;

public class User {

    public String getFname() {

        return fname;
    }

    public void setFname(String fname) {

        this.fname = fname;
    }



    public int getScore() {

        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }
    private String fname;
    private int id;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
    private int score;
    public User(int id, String fname, int score){
        this.fname = fname;
        this.id = id;
        this.score = score;
    }
}
