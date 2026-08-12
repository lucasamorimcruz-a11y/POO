
public class Main {
    public static void main(String[] args) {
        PaymentCard paulsCard = new PaymentCard(20);
        PaymentCard mattsCard = new PaymentCard(30);
        paulsCard.eatHeartily();
        mattsCard.eatAffordably();
        System.out.println("pauls:" + paulsCard);
        System.out.println("matts: " + mattsCard);
        paulsCard.addMoney(20);
        mattsCard.eatHeartily();
        System.out.println("pauls:" + paulsCard);
        System.out.println("matts: " + mattsCard);
        paulsCard.eatAffordably();
        paulsCard.eatAffordably();
        mattsCard.addMoney(50);
        System.out.println("pauls:" + paulsCard);
        System.out.println("matts: " + mattsCard);
    }
}
