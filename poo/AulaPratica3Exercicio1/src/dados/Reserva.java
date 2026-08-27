package dados;

public class Reserva {

    private int numReserva;
    private String dataVoo;
    private String horaVoo;
    private float preco;
    private String classeVoo;
    private boolean idaEVolta;
    private int poltrona;
    private Cidade origem;
    private Cidade destino;
    private Reserva volta;

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

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public Cidade getOrigem() {
        return origem;
    }

    public void setOrigem(Cidade origem) {
        this.origem = origem;
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
        this.idaEVolta = true;
    }

    public void excluirVolta() {
        this.volta = null;
        this.idaEVolta = false;
    }

    @Override
    public String toString() {
        return  "Reserva #" + numReserva +
                " | Data: " + dataVoo +
                " | Hora: " + horaVoo +
                " | Poltrona: " + poltrona +
                " | Classe: " + classeVoo +
                " | Preço: R$ " + preco +
                " | Ida e Volta: " + (idaEVolta ? "Sim" : "Não");
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Reserva)) {
            return false;
        }
        Reserva outra = (Reserva) obj;
        return this.numReserva == outra.numReserva
                && this.dataVoo != null
                && this.dataVoo.equals(outra.dataVoo)
                && this.horaVoo != null
                && this.horaVoo.equals(outra.horaVoo)
                && Float.compare(this.preco, outra.preco) == 0
                && this.classeVoo != null
                && this.classeVoo.equals(outra.classeVoo)
                && this.idaEVolta == outra.idaEVolta
                && this.poltrona == outra.poltrona
                && java.util.Objects.equals(this.origem, outra.origem)
                && java.util.Objects.equals(this.destino, outra.destino);
    }
}