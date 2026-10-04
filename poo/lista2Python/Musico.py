class Musico:

    def __init__(self, nome, idade, instrumentos=None):
        self.nome = nome
        self.idade = idade
        self.instrumentos = instrumentos if instrumentos is not None else []

    def adicionar_instrumento(self, instrumento):
        if len(self.instrumentos) < 2:
            self.instrumentos.append(instrumento)
            return True
        return False

    def set_nome(self, nome):
        self.nome = nome

    def get_nome(self):
        return self.nome

    def set_idade(self, idade):
        self.idade = idade

    def get_idade(self):
        return self.idade

    def set_instrumentos(self, instrumentos):
        self.instrumentos = instrumentos

    def get_instrumentos(self):
        return self.instrumentos

    def __str__(self):
        return f"Nome: {self.nome}, Idade: {self.idade}, Instrumentos: {self.instrumentos}"