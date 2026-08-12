import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner reader = new Scanner(Paths.get("file.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
        printingSpecifiedFile();

    }

    public static void printingSpecifiedFile() {
        String input;
        System.out.println("Which file should have its contents printed?");
        Scanner reader = new Scanner(System.in);
        input = reader.nextLine();
        try (Scanner newReader = new Scanner(Paths.get(input))) {
            while (newReader.hasNextLine()) {
                String line = newReader.nextLine();
                System.out.println(line);
            }
        } catch (Exception a) {

        }
    }
}
