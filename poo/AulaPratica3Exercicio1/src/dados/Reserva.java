package dados;

public class Reserva {
    private int numReserva;
    private String dataVoo;
    private String horaVoo;
    private float preco;
    private String classeVoo;
    private boolean idaEVolta;
    private int poltrona;
    private Reserva volta;
    private Cidade destino;

    public boolean getIdaEVolta(){
        return this.idaEVolta;
    }

    public int getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    public String getDataVoo() {
        return dataVoo;
    }

    public void setDataVoo(String dataVoo) {
        this.dataVoo = dataVoo;
    }

    public String getHoraVoo() {
        return horaVoo;
    }

    public void setHoraVoo(String horaVoo) {
        this.horaVoo = horaVoo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getClasseVoo() {
        return classeVoo;
    }

    public void setClasseVoo(String classeVoo) {
        this.classeVoo = classeVoo;
    }

    public boolean isIdaEVolta() {
        return idaEVolta;
    }

    public void setIdaEVolta(boolean idaEVolta) {
        this.idaEVolta = idaEVolta;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public Reserva getReserva() {
        return volta;
    }

    public void setReserva(Reserva reserva) {
        this.idaEVolta = true;
        this.volta = reserva;
    }

    public Cidade getDestino() {
        return destino;
    }

    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    public Reserva getVolta() {
        return volta;
    }

    public void setVolta(Reserva volta) {
        this.volta = volta;
    }

    @Override
    public String toString() {
        String info = "Reserva nº: " + numReserva +
                "\nData do Voo: " + dataVoo +
                "\nHora do Voo: " + horaVoo +
                "\nPreço: R$ " + preco +
                "\nClasse: " + classeVoo +
                "\nPoltrona: " + poltrona +
                "\nIda e Volta: " + (idaEVolta ? "Sim" : "Não");

        if (destino != null) {
            info += "\nDestino: " + destino.toString();
        }

        if (volta != null) {
            info += "\n--- Voo de Volta ---\n" + volta.toString();
        }
        return info;
    }
    @Override
    public boolean equals (Object obj){
        if (obj instanceof Reserva){
            return this.numReserva == ((Reserva) obj).getNumReserva() && this.dataVoo == ((Reserva) obj).getDataVoo() && this.horaVoo == ((Reserva) obj).horaVoo && this.preco == ((Reserva) obj).getPreco() && this.classeVoo == ((Reserva) obj).getClasseVoo() && this.idaEVolta == ((Reserva) obj).getIdaEVolta() && this.poltrona == ((Reserva) obj).getPoltrona();
        }
        return false;
    }

}
