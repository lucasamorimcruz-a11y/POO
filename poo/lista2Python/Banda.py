class Banda:

    def __init__(self, nome, musicos=None):
        self.nome = nome
        self.musicos = musicos if musicos is not None else []

    def adicionar_musico(self, musico):
        self.musicos.append(musico)

    def get_nome(self):
        return self.nome

    def set_nome(self, nome):
        self.nome = nome

    def get_musicos(self):
        return self.musicos

    def set_musicos(self, musicos):
        self.musicos = musicos