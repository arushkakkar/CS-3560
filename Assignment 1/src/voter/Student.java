package voter;

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
    public void generateSingleChoiceAnswer(String options){
        studentAnswer = new ArrayList<Character>();
        try {
            Thread.sleep(5);
        }
        catch(Exception e){
        }
        Random r = new Random(System.currentTimeMillis());
        studentAnswer = options.charAt(r.nextInt(options.length()));
        if(options.equals("tf"))
            studentAnswer = r.nextInt(10) > 5 ? 't' : 'f';
    }

    @Override
    public void generateMCAnswer(String options) {
        studentAnswer = new ArrayList<Character>();
        try {
            Thread.sleep(5);
        }
        catch(Exception e){
        }
        Random r = new Random(System.currentTimeMillis());
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

    public Object getStudentChoices() {
        return studentAnswer;
    }

    public String getStudentAnswer(){
        return (String)studentAnswer;
    }

    public String getID(){
        return id;
    }
}