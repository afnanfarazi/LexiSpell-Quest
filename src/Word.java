public class Word {
    private String correctSpelling;
    private String pronunciation;
    private String definition;

    public Word(String correctSpelling, String pronunciation, String definition) {
        this.correctSpelling = correctSpelling;
        this.pronunciation = pronunciation;
        this.definition = definition;
    }

    public String getCorrectSpelling() {
        return correctSpelling;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getDefinition() {
        return definition;
    }
}
