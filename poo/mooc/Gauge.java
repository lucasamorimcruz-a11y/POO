package udesc.poo.mooc;

public class Gauge {
    private int value;

    public Gauge() {
        this.value = 0;
    }

    public void increase() {
        this.value++;
    }

    public void decrase() {
        this.value--;
    }

    public int value() {
        return this.value;
    }

    public boolean full() {
        return (this.value == 5);
    }

}
