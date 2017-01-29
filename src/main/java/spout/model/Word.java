package spout.model;

/**
 * Created by archit on 29/1/17.
 */
public class Word {
    private  String word;
    private  boolean status;
    private  int index;
    private  int sentenceId;

    public Word(int sentenceId, int index, String word,boolean status){
        this.sentenceId=sentenceId;
        this.index=index;
        this.word=word;
        this.status=status;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }
}
