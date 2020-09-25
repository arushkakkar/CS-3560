package questions;

import voter.Student;
import java.util.HashMap;

public class SentenceQ extends Question{

    private String answer;
    private HashMap<String, Integer> tally;

    public SentenceQ(String q, String a){
        super(q);
        tally = new HashMap<>();
        answer = a;
    }

    @Override
    public boolean checkStudentAnswer(Student s){
        if(checkAttempted(s)) {
            String prev = (String)getPreviousAnswer(s);
            if(prev.equals(this.answer))
                decrementAnsweredCorrectly();
            tally.replace(prev, tally.get(prev) - 1);
            updateAttempted(s, s.getStudentAnswer());
        }
        else {
            addAttempted(s, s.getStudentAnswer());
        }

        try {
            tally.replace(s.getStudentAnswer(), tally.get(s.getStudentAnswer()) + 1);
        }
        catch(NullPointerException e){
            tally.put(s.getStudentAnswer(), 1);
        }

        if(!s.getStudentAnswer().equals(this.answer))
            return false;
        incrementAnsweredCorrectly();
        return false;
    }

    @Override
    public HashMap getStatistics() {
        return tally;
    }

    @Override
    public String getCorrectAnswer(){
        return answer;
    }
}
