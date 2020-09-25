import questions.*;
import voter.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Driver {
    public static void main(String[] args){
        Question question1 = new SingleChoiceQ("Question 1", 'a');
        Question question2 = new MultipleChoiceQ("Question 2", 'a', 'b', 'c');
        Question question3 = new MultipleChoiceQ("Question 3", 'b', 'c', 'd');
        Question question4 = new SingleChoiceQ("Question 4", 'd');
        Question question5 = new SingleChoiceQ("Question 5", 'c');
        Question question6 = new SentenceQ("Question 6", "correct answer");
        Question question7 = new TFQuestion("Question 7", 't');

        Question[] questions = new Question[]{question1, question2, question3, question4, question5, question6, question7};

        iVoteService(questions);
    }

    public static void iVoteService(Question[] questions){
        // Create Students
        List<Student> studentList = new ArrayList<Student>();
        for(int i = 0; i < 15; i++){
            studentList.add(new Student(UUID.randomUUID().toString()));
        }

        //Check Student Answers
        for(int i = 0; i < questions.length; i++){
            for(int j = 0; j < studentList.size(); j++) {
                iVoteServiceHelper(questions[i], studentList.get(j));
            }
        }

        //Display Statistics
        System.out.println("Class statistics (15 Students):\n");
        for(int i = 0; i < questions.length; i++){
            displayStatistics(questions[i], studentList.size());
        }

        //Re-attempt demonstration
        System.out.println("!Random student re-attempts question 6 (Easier to see change)!\n");
        Random r = new Random(System.currentTimeMillis());
        int reattemptq = r.nextInt(questions.length);
        int reattempts = r.nextInt(studentList.size());

        //Display Statistics after reattempt
        System.out.println("Statistics after re-attempt:\n!! there is a 50% chance that the random student will select the same\nanswer as before, might need to run a few times to see a change.\n");
        iVoteServiceHelper(questions[5], studentList.get(reattempts));
        displayStatistics(questions[5], studentList.size());
    }

    public static void iVoteServiceHelper(Question q, Student s){
        if (q.getClass() == MultipleChoiceQ.class)
            s.generateMCAnswer("abcdefghi");
        else if (q.getClass() == SingleChoiceQ.class)
            s.generateSingleChoiceAnswer("abcdefghi");
        else if(q.getClass() == SentenceQ.class)
            s.generateSentenceAnswer();
        else if(q.getClass() == TFQuestion.class)
            s.generateSingleChoiceAnswer("tf");

        q.checkStudentAnswer(s);
    }

    public static void displayStatistics(Question q, int s){
        System.out.println("Question: " + q.getQuestion() + "\nCorrect Answer: " + q.getCorrectAnswer());
        System.out.println("#Correct: " + q.getAnsweredCorrectly());
        System.out.println("#Incorrect: " + (s - q.getAnsweredCorrectly()));
        System.out.println("Stats: " + q.getStatistics().toString());
        System.out.println("\n------------------\n");
    }
}
