/**
 * Created by Mac on 2017/8/5.
 */

public class Word {
    public String term; //词
    public WordType type; //词频

    public Word(String w, WordType f) {
        term = w;
        type = f;
    }

    public String toString() {
        return term + ":" + type;
    }
}
