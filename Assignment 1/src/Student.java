import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student implements Voter {
    private String id;
    private Object studentAnswer;

    public Student(String id){
        this.id = id;
    }

    @Override
    public void generateSingleChoiceAnswer(){
        studentAnswer = new ArrayList<Character>();
        String options = "abcdefghi";
        try {
            Thread.sleep(5);
        }
        catch(Exception e){
        }
        Random r = new Random(System.currentTimeMillis());
        ((List<Character>)studentAnswer).add(options.charAt(r.nextInt(options.length())));
    }

    @Override
    public void generateMCAnswer() {
        studentAnswer = new ArrayList<Character>();
        try {
            Thread.sleep(5);
        }
        catch(Exception e){
        }
        Random r = new Random(System.currentTimeMillis());
        String options = "abcdefghi";
        int numChoices = r.nextInt(options.length());
        for(int i = 0; i<numChoices; i++){
            int now = r.nextInt(options.length());
            if(((List<Character>)studentAnswer).contains(options.charAt(now))){
                i--;
                continue;
            }
            ((List<Character>)studentAnswer).add(options.charAt(now));
        }
    }

    @Override
    public void generateSentenceAnswer(){
        try {
            Thread.sleep(5);
        }
        catch(Exception e){
        }
        Random r = new Random(System.currentTimeMillis());
        studentAnswer = r.nextInt(10) > 5 ? "correct answer" : "incorrect answer";
    }

    public List<Character> getStudentChoices() {
        return (List<Character>)studentAnswer;
    }

    public String getStudentAnswer(){
        return (String)studentAnswer;
    }

    public String getID(){
        return id;
    }
}