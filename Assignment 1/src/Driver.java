import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Driver {
    public static void main(String[] args){
        Question question1 = new SingleChoiceQ("Question 1", 'a');
        Question question2 = new MultipleChoiceQ("Question 2", 'a', 'b', 'c');
        Question question3 = new MultipleChoiceQ("Question 3", 'b', 'c', 'd');
        Question question4 = new SingleChoiceQ("Question 4", 'd');
        Question question5 = new SingleChoiceQ("Question 5", 'c');
        Question question6 = new SentenceQ("Question 6", "correct answer");

        Question[] questions = new Question[]{question1, question2, question3, question4, question5, question6};

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
                if (questions[i].getClass() == MultipleChoiceQ.class) {
                    studentList.get(j).generateMCAnswer();
                    ((MultipleChoiceQ) questions[i]).checkStudentAnswer(studentList.get(j));
                }
                else if (questions[i].getClass() == SingleChoiceQ.class) {
                    studentList.get(j).generateSingleChoiceAnswer();
                    ((SingleChoiceQ) questions[i]).checkStudentAnswer(studentList.get(j));
                }
                else if(questions[i].getClass() == SentenceQ.class){
                    studentList.get(j).generateSentenceAnswer();
                    ((SentenceQ) questions[i]).checkStudentAnswer(studentList.get(j));
                }
            }
        }

        //studentList.get(2).generateSingleChoiceAnswer();
        //((SingleChoiceQ)questions[0]).checkStudentAnswer(studentList.get(2));

        //Display Statistics
        System.out.println("Class statistics (15 Students):\n");
        for(int i = 0; i < questions.length; i++){
            System.out.println("Question: " + questions[i].getQuestion() + "\nCorrect Answer: " + questions[i].getCorrectAnswer());
            System.out.println("#Correct: " + questions[i].getAnsweredCorrectly());
            System.out.println("#Incorrect: " + (studentList.size() - questions[i].getAnsweredCorrectly()));
            System.out.println("Stats: " + questions[i].getStatistics().toString());
            System.out.println("\n------------------\n");
        }
    }
}
