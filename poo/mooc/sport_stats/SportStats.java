import java.nio.file.Paths;
import java.util.*;

public class SportStats {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Game> games = new ArrayList<>();
        System.out.println("What's the file name?");
        String file = reader.nextLine();
        try (Scanner scanner = new Scanner(Paths.get(file))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] stats = line.split(",");
                String team1 = stats[0];
                String team2 = stats[1];
                int points1 = Integer.parseInt(stats[2]);
                int points2 = Integer.parseInt(stats[3]);
                Game currGame = new Game(team1, team2, points1, points2);
                games.add(currGame);
            }
        } catch (Exception error) {
            System.out.println("Error " + error.getMessage());
        }
        System.out.println("What team you want to see the stats of?");
        String userTeam = reader.nextLine();
        for (Game g : games) {
            if (g.getTeam1().equals(userTeam) || g.getTeam2().equals(userTeam)) {
                System.out.println(g);
            }
        }
        int count = 0;
        int wins = 0;
        int losses = 0;
        System.out.println("Which team do you want to see how many games it played?");
        userTeam = reader.nextLine();
        for (Game g : games) {
            if (g.getTeam1().equals(userTeam) || g.getTeam2().equals(userTeam)) {
                count++;
                if (g.getTeam1().equals(userTeam)) {
                    if (g.getPointsTeam1() > g.getPointsTeam2()) {
                        wins++;
                    } else {
                        losses++;
                    }
                } else {
                    if (g.getPointsTeam2() > g.getPointsTeam1()) {
                        wins++;
                    } else {
                        losses++;
                    }
                }
            }
        }
        System.out.println(count + " games played");
        System.out.println("Wins:" + wins);
        System.out.println("Losses: " + losses);
    }
}
