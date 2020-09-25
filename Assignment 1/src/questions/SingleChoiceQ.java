package questions;

import voter.Student;

import java.util.HashMap;
import java.util.List;

public class SingleChoiceQ extends Question{

    private char answer;
    private HashMap<Character, Integer> tally;
    private String options = "abcdefghi";

    public SingleChoiceQ(String q, char a){
        super(q);
        tally = new HashMap<Character, Integer>();
        for(int i = 0; i < options.length(); i++)
            tally.put(options.charAt(i), 0);
        answer = a;
    }

    protected SingleChoiceQ(String q, char a, String o){
        super(q);
        options = o;
        tally = new HashMap<Character, Integer>();
        for(int i = 0; i < options.length(); i++)
            tally.put(options.charAt(i), 0);
        answer = a;
    }

    @Override
    public boolean checkStudentAnswer(Student s){
        if(checkAttempted(s)) {
            if(((Character)getPreviousAnswer(s)) == (Character)this.answer)
                decrementAnsweredCorrectly();
            tally.replace(((Character)getPreviousAnswer(s)), tally.get(((Character)getPreviousAnswer(s))) - 1);
            updateAttempted(s, s.getStudentChoices());
        }
        else {
            addAttempted(s, s.getStudentChoices());
        }

        tally.replace((Character) s.getStudentChoices(), tally.get(s.getStudentChoices()) + 1);

        if ((Character) s.getStudentChoices() != (this.answer))
            return false;
        incrementAnsweredCorrectly();
        return true;
    }

    protected void setOptions(String o){
        options = o;
    }

    @Override
    public String getCorrectAnswer(){
        return Character.toString(answer);
    }

    @Override
    public HashMap getStatistics() {
        return tally;
    }

}