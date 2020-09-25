package questions;

import voter.Student;
import java.util.HashMap;

public abstract class Question {

    private String question;
    private int answeredCorrectly;
    private HashMap<Student, Object> attempted;

    public Question(){
        question = "";
        attempted = new HashMap<Student, Object>();
    }
    public Question(String q){
        this.question = q;
        attempted = new HashMap<Student, Object>();
    }

    public String getQuestion() {
        return question;
    }
    public boolean setQuestion(String q){
        try{
            this.question = q;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public int getAnsweredCorrectly(){
        return answeredCorrectly;
    }
    protected void incrementAnsweredCorrectly(){
        answeredCorrectly++;
    }
    protected void decrementAnsweredCorrectly(){
        answeredCorrectly--;
    }

    protected boolean checkAttempted(Student s){
        return attempted.containsKey(s);
    }
    protected void addAttempted(Student s, Object o){
        attempted.put(s, o);
    }
    protected void updateAttempted(Student s, Object o){
        attempted.replace(s, o);
    }
    protected Object getPreviousAnswer(Student s){
        return attempted.get(s);
    }

    public abstract boolean checkStudentAnswer(Student s);

    public abstract HashMap getStatistics();
    public abstract String getCorrectAnswer();
}
