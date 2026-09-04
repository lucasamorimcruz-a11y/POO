class Cliente:
    def __init__(self, nome, quantidade_de_cupons):
        self.nome = nome
        self.quantidade_de_cupons = quantidade_de_cupons

    def __str__(self):
        return f"Nome: {self.nome}, Quantidade de cupons: {self.quantidade_de_cupons}"