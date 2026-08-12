
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/*
    SPEED RUN DE POO
*/
public class Main {
    public static void main(String[] main) {
        sumOfUserInput();
    }

    public static void lessonOne() {
        Scanner reader = new Scanner(System.in);
        int first = Integer.valueOf(reader.nextLine());
        int second = Integer.valueOf(reader.nextLine());
        int media = (first + second) / 2;
        System.out.println("A média é :" + media);

        while (true) {
            System.out.println("Shall we carry on?");
            String input = String.valueOf(reader.nextLine());
            input.toLowerCase();
            if (input.equals("no")) {
                break;
            }
        }
        System.out.println("Great!");
        int numberOfTimes = Integer.valueOf(reader.nextLine());
        for (int i = 0; i < numberOfTimes; i++) {
            printText();
        }

    }

    public static void printText() {
        System.out.println("In a hole in the ground there lives a method");
    }

    public static void tryList() {
        ArrayList<String> newList = new ArrayList<>();
        newList.add("Lucas Amorim");
        for (String s : newList) {
            System.out.println(s);
        }
        List<String> newList1 = new ArrayList<>();
        ArrayList<Integer> newList2 = new ArrayList<>();
        Integer x = 5;
        newList1.add("Hi");
        newList1.get(0);

    }

    public static void thirdElement() {
        ArrayList<String> listOfElements = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        String current;
        do {
            current = reader.nextLine();
            listOfElements.add(current);
        } while (!(current.equals("")));
        if (listOfElements.size() < 3) {
            System.out.println("It doesn't have 3 elements in the list!");
        } else {

            System.out.println(listOfElements.get(2));
        }
    }

    public static void secondPlusThird() {
        Scanner reader = new Scanner(System.in);
        ArrayList<Integer> listOfInts = new ArrayList<>();
        int input;
        do {
            input = reader.nextInt();
            listOfInts.add(input);
        } while (input != 0);
        if (listOfInts.size() < 3) {
            System.out.println("The size of the list aint big enough for the operation!");
        } else {
            System.out.println(listOfInts.get(1) + listOfInts.get(2));
        }
    }

    public static void listSize() {
        Scanner reader = new Scanner(System.in);
        ArrayList<String> newList = new ArrayList<>();
        String input;
        do {
            input = reader.nextLine();
            newList.add(input);
        } while (input != "");
        System.out.println(newList.size());
    }

    public static void onlyTheseNumbers() {
        Scanner reader = new Scanner(System.in);
        ArrayList<Integer> newList = new ArrayList<>();
        int input;
        do {
            input = reader.nextInt();
            newList.add(input);
        } while (input != -1);
        System.out.println("From where?");
        int start = reader.nextInt();
        System.out.println("To where");
        int end = reader.nextInt();
        for (int i = start; i <= end; i++) {
            System.out.println(newList.get(i));
        }
    }

    public static void smallestNumber() {
        Scanner reader = new Scanner(System.in);
        ArrayList<Integer> newList = new ArrayList<>();
        int input;
        do {
            input = reader.nextInt();
            newList.add(input);
        } while (input != 9999);
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i) < min) {
                min = newList.get(i);
                index = i;
            }
        }
        System.out.println("The min is " + min);
        System.out.println("Found at " + index);
    }

    public static void sumOfAList() {
        Scanner reader = new Scanner(System.in);
        ArrayList<Integer> newList = new ArrayList<>();
        int input = 0;
        do {
            input = reader.nextInt();
            newList.add(input);
        } while (input != -1);
        int sum = 0;
        for (int x : newList) {
            sum += x;
        }
        System.out.println(sum);
    }

    public static void testingRemove() {
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<Integer> integerList = new ArrayList<>();
        stringList.add("HI there");
        stringList.add("Hi");
        stringList.add("There");
        stringList.remove("HI there");
        integerList.add(10);
        integerList.add(13);
        integerList.add(14);
        integerList.remove(1);
        integerList.remove(Integer.valueOf(14));

        for (String s : stringList) {
            System.out.println(s);
        }
        for (Integer i : integerList) {
            System.out.println(i);
        }
    }

    public static void onTheList() {
        Scanner reader = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        String input;
        do {
            input = reader.nextLine();
            names.add(input);
        } while (!(input.equals("")));
        System.out.println("Search for? ");
        String name = reader.nextLine();
        if (names.contains(name)) {
            System.out.println(name + " was found!");
        } else {
            System.out.println("Name wasnt found");
        }
    }

    public static void removeLast(ArrayList<String> strings) {
        if (strings.isEmpty()) {
            return;
        }
        strings.remove(strings.size() - 1); // como é passado por refêrencia, não é criado uma cópia da lista no próprio
                                            // metódo, mas sim na lista passado como parametro original.
    }

    public static void array() {
        int[] array = new int[2];
    }

    public static void swapIndex() {
        Scanner reader = new Scanner(System.in);
        int[] array = new int[5];
        for (int i = 0; i < 5; i++) {
            array[i] = reader.nextInt();
        }
        for (int x : array) {
            System.out.println(x);
        }
        System.out.println("Give two indices to swap:");
        int index1 = reader.nextInt();
        int index2 = reader.nextInt();
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        for (int x : array) {
            System.out.println(x);
        }
        System.out.println(array.length);
        // array é passado como referencia também
    }

    public static void printInStars(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i]; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void readingStrings() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        System.out.println(input);
    }

    public static void printThrice() {
        Scanner reader = new Scanner(System.in);
        String string = reader.nextLine();
        System.out.println(string + string + string);
    }

    public static void isItTrue() {
        Scanner reader = new Scanner(System.in);
        String string = reader.nextLine();
        if (string.equals("true")) {
            System.out.println("You got it right");
        } else {
            System.out.println("Try again!");
        }
    }

    public static void login() {
        String firstUser = "alex";
        String secondUser = "emma";
        Scanner reader = new Scanner(System.in);
        String login = reader.nextLine();
        String password = reader.nextLine();
        if (((login.equals(secondUser)) && password.equals("Haskell"))
                || (login.equals(firstUser)) && (password.equals("sunshine"))) {
            System.out.println("You have successfully logged in ");
        } else {
            System.out.println("Incorrect username or password");
        }

    }

    public static void lineByLine() {
        Scanner reader = new Scanner(System.in);
        String phrase = reader.nextLine();
        String[] words = phrase.split(" ");
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static void avClub() {
        Scanner reader = new Scanner(System.in);
        String phrase = reader.nextLine();
        String[] words = phrase.split(" ");
        for (String word : words) {
            if (word.contains("av")) {
                System.out.println(word);
            } else {

            }
        }
    }

    public static void firstWords() {
        Scanner reader = new Scanner(System.in);
        String phrase = reader.nextLine();
        String[] words = phrase.split(" ");
        System.out.println(words[0]);
    }

    public static void lastWords() {
        Scanner reader = new Scanner(System.in);
        String phrase = reader.nextLine();
        String[] words = phrase.split(" ");
        System.out.println(words[words.length - 1]);
    }

    public static void ageOfTheOldest() {
        Scanner reader = new Scanner(System.in);
        String input = "asdas";
        int max = Integer.MIN_VALUE;
        String final_name = "";
        while (true) {
            input = reader.nextLine();
            if (input.equals(""))
                break;
            String[] words = input.split(",");
            int x = Integer.parseInt(words[1]);
            if (max < x) {
                max = x;
                final_name = words[0];
            }
        }
        System.out.println("Name of the oldest is : " + final_name);
    }

    public static void tryPerson() {
        Person jefferson = new Person("Jefferson", 10, 0, 0);
        int i = 0;
        while (i < 30) {
            jefferson.growOlder();
        }
        System.out.println(jefferson);
    }

    public static void tryMultiplier() {
        Multiplier multiplyByThree = new Multiplier(3);

        System.out.println("multiplyByThree.multiply(2): " + multiplyByThree.multiply(2));

        Multiplier multiplyByFour = new Multiplier(4);

        System.out.println("multiplyByFour.multiply(2): " + multiplyByFour.multiply(2));
        System.out.println("multiplyByThree.multiply(1): " + multiplyByThree.multiply(1));
        System.out.println("multiplyByFour.multiply(1): " + multiplyByFour.multiply(1));
    }

    public static void sumOfUserInput() {
        Statistics test = new Statistics();
        Scanner reader = new Scanner(System.in);
        int input;
        do {
            input = reader.nextInt();
            test.addNumber(input);
        } while (input != -1);
        System.out.println("Sum :" + test.sum());
        System.out.println("Sum: " + test.sumOfEven());
        System.out.println("Sum: " + test.sumOfOdd());
    }

    public static void numberOfStrings() {
        Scanner reader = new Scanner(System.in);
        String input;
        int i = 0;
        while (true) {
            input = reader.nextLine();
            if (input.equals("end")) {
                break;
            }
            i++;
        }
        System.out.println(i);
    }

    public static void listOfObjs() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Lucas");
        names.add("Matheus");
        names.add("Vinicius");
        names.add("Gabriel");
        names.add("Fernando");
        for (String name : names) {
            System.out.println(name);
        }
    }

    public void createANewArray() {
        ArrayList<Person> newPerson = new ArrayList<>();
        Person john = new Person("Lucas");
        newPerson.add(john);
        newPerson.add(new Person("john"));
        for (Person person : newPerson) {
            System.out.println(person.toString());
        }
    }

    public void userInputObjToList() {
        Scanner reader = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();

        while (true) {
            System.out.println("Enter a name, if empty it will end the program");
            String name = reader.nextLine();
            if (name.isEmpty()) {
                break;
            }
            persons.add(new Person(name));
        }
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }

}