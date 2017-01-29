package logic.model;

/**
 * Created by archit on 29/1/17.
 */
public class Sentence {
    private int id;
    private String sentence;

    public Sentence(int id, String sentence){
        this.id=id;
        this.sentence=sentence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
