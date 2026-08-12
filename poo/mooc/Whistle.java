package udesc.poo.mooc;

public class Whistle {
    private String sound;

    public Whistle(String thisSound) {
        this.sound = thisSound;
    }

    public void sound() {
        System.out.println(this.sound);
    }
}
