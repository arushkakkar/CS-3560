package questions;

import voter.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultipleChoiceQ extends Question{
    private List<Character> answer;
    private HashMap<Character, Integer> tally;

    public MultipleChoiceQ(String q, char ... answers){
        super(q);
        tally = new HashMap<Character, Integer>();
        String options = "abcdefghi";
        for(int i = 0; i < options.length(); i++)
            tally.put(options.charAt(i), 0);
        this.answer = new ArrayList<Character>();
        for(char a: answers) {
            this.answer.add(a);
        }
    }

    @Override
    public boolean checkStudentAnswer(Student s){
        List<Character> answer = (ArrayList<Character>)s.getStudentChoices();
        if(checkAttempted(s)) {
            List<Character> prev = (ArrayList<Character>)getPreviousAnswer(s);
            if (prev.containsAll(this.answer) && prev.size() == this.answer.size())
                decrementAnsweredCorrectly();
            for(int i = 0; i < prev.size(); i ++)
                tally.replace(prev.get(i), tally.get(prev.get(i)) - 1);
            updateAttempted(s, answer);
        }
        else {
            addAttempted(s, s.getStudentChoices());
        }

        for (int i = 0; i < answer.size(); i++) {
            tally.replace(answer.get(i), tally.get(answer.get(i)) + 1);
        }

        if (!answer.containsAll(this.answer) || answer.size() != this.answer.size())
            return false;
        incrementAnsweredCorrectly();
        return true;
    }

    @Override
    public HashMap getStatistics(){
        return tally;
    }

    @Override
    public String getCorrectAnswer() {
        return answer.toString();
    }
}