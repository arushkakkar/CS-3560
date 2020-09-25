package voter;

public interface Voter {
    public void generateSingleChoiceAnswer(String options);
    public void generateMCAnswer(String options);
    public void generateSentenceAnswer();
}
