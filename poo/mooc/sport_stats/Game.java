import java.util.Scanner;

public class Game {
    private String team1;
    private String team2;
    private int point1;
    private int point2;

    public Game(String team1, String team2, int point1, int point2) {
        this.team1 = team1;
        this.team2 = team2;
        this.point1 = point1;
        this.point2 = point2;
    }

    public String toString() {
        return this.team1 + ", " + this.team2 + ", " + this.point1 + ", " + this.point2;
    }

    public String getTeam1() {
        return this.team1;
    }

    public String getTeam2() {
        return this.team2;
    }

    public int getPointsTeam1() {
        return this.point1;
    }

    public int getPointsTeam2() {
        return this.point2;
    }
}
