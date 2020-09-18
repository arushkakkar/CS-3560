import java.util.HashMap;
import java.util.List;

public class SingleChoiceQ extends Question{

    private char answer;
    private HashMap<Character, Integer> tally;

    public SingleChoiceQ(String q, char a){
        super(q);
        tally = new HashMap<Character, Integer>();
        String options = "abcdefghi";
        for(int i = 0; i < options.length(); i++)
            tally.put(options.charAt(i), 0);
        answer = a;
    }

    @Override
    public boolean checkStudentAnswer(Student s){
        if(checkAttempted(s)) {
            if(((List<Character>)getPreviousAnswer(s)).contains(this.answer))
                decrementAnsweredCorrectly();
            tally.replace(((List<Character>)getPreviousAnswer(s)).get(0), tally.get(((List<Character>)getPreviousAnswer(s)).get(0)) - 1);
            updateAttempted(s, s.getStudentChoices());
        }
        else {
            addAttempted(s, s.getStudentChoices());
        }

        tally.replace(s.getStudentChoices().get(0), tally.get(s.getStudentChoices().get(0)) + 1);

        if (!s.getStudentChoices().contains(this.answer))
            return false;
        incrementAnsweredCorrectly();
        return true;
    }

    @Override
    public String getCorrectAnswer(){
        return Character.toString(answer);
    }

    @Override
    HashMap getStatistics() {
        return tally;
    }

}