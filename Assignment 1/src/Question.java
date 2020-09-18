import java.util.HashMap;

abstract class Question {

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
    public void incrementAnsweredCorrectly(){
        answeredCorrectly++;
    }
    public void decrementAnsweredCorrectly(){
        answeredCorrectly--;
    }

    public boolean checkAttempted(Student s){
        return attempted.containsKey(s);
    }
    public void addAttempted(Student s, Object o){
        attempted.put(s, o);
    }
    public void updateAttempted(Student s, Object o){
        attempted.replace(s, o);
    }
    public Object getPreviousAnswer(Student s){
        return attempted.get(s);
    }

    abstract boolean checkStudentAnswer(Student s);

    abstract HashMap getStatistics();
    abstract String getCorrectAnswer();
}
