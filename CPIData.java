public class CPIData {
    private String country;
    private int score;
    private int rank;

    public CPIData(String country, int score, int rank) {
        this.country = country;
        this.score = score;
        this.rank = rank;
    }

    // Getter methods
    public String getCountry() {
        return country;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.format("%-25s | Score: %-3d | Rank: %d", country, score, rank);
    }
}