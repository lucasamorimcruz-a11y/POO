public class Assinaturas {
    private double value;
    private int subscriptionPeriod;
    private String typeOfSubscription;

    public double getValue() {
        return value;
    }

    public int getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public String getTypeOfSubscription() {
        return typeOfSubscription;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSubscriptionPeriod(int subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public void setTypeOfSubscription(String typeOfSubscription) {
        this.typeOfSubscription = typeOfSubscription;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public boolean equals(Assinaturas assinatura) {
        return this.value == assinatura.getValue() && this.subscriptionPeriod == assinatura.getSubscriptionPeriod() && this.typeOfSubscription.equals(assinatura.getTypeOfSubscription());
    }
    Assinaturas (double value, int subscriptionPeriod){
        this.value = value;
        this.subscriptionPeriod = subscriptionPeriod;
    }
    Assinaturas (double value, String typeOfSubscription){
        this.value = value;
        this.typeOfSubscription = typeOfSubscription;
    }
}
