public class Atores {
    private int age;
    private String name;
    private int amountOfMoviesMade;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfMoviesMade() {
        return amountOfMoviesMade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmountOfMoviesMade(int amountOfMoviesMade) {
        this.amountOfMoviesMade = amountOfMoviesMade;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Atores ator) {
        return this.age == ator.age && this.name.equals(ator.getName())
                && this.amountOfMoviesMade == ator.getAmountOfMoviesMade();
    }

    public Atores(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Atores(String name, int amountOfMoviesMade) {
        this.name = name;
        this.amountOfMoviesMade = amountOfMoviesMade;
    }

}
