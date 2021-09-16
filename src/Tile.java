public class Tile {

    private String letter;
    private int score;

    public Tile(String letter, int score){
        this.letter = letter;
        this.score = score;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getScore() {
        return score;
    }
}
