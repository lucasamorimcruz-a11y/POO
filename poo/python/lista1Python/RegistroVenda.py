class RegistroVenda:
    def __init__(self, cliente, funcionario, livros_vendidos):
        self.cliente = cliente
        self.funcionario = funcionario
        self.livros_vendidos = livros_vendidos

    def valor_total(self):
        total = 0

        for livro in self.livros_vendidos:
            total += livro.preco

        return total
    def __str__(self):
        livros = ""
        for livro in self.livros_vendidos:
            livros += f"\n    {livro}"
        return f"Cliente: {self.cliente}\nFuncionario: {self.funcionario}\nLivros vendidos:{livros}\nValor total: R$ {self.valor_total():.2f}"