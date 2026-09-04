class Livro:
    def __init__(self, ISBN, nome, quantidade_de_vendas, preco):
        self.ISBN = ISBN
        self.nome = nome
        self.quantidade_de_vendas = quantidade_de_vendas
        self.preco = preco

    def __str__(self):
        return f"ISBN: {self.ISBN}, Nome: {self.nome}, Quantidade de vendas: {self.quantidade_de_vendas}, Preço: R$ {self.preco:.2f}"

    def __eq__(self, obj: object) -> bool:
        if not isinstance(obj, Livro):
            return NotImplemented
        return self.ISBN == obj.ISBN