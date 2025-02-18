public class Player {
    private String name;
    private int score;
    private int position;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getPosition() {
        return position;
    }

    public void incrementScore() {
        score++;
    }

    public void incrementPosition() {
        position++;
    }
}
