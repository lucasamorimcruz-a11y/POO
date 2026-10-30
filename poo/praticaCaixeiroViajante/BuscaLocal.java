/** Uma busca local: recebe uma rota e devolve uma rota igual ou melhor. */
public interface BuscaLocal {
    Rota melhorar(Rota rota);
}
