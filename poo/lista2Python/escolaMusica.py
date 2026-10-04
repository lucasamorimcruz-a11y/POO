from Banda import Banda


class EscolaMusica:

    def __init__(self, bandas=None, alunos=None):
        self.bandas = bandas if bandas is not None else []
        self.alunos = alunos if alunos is not None else []

    def cadastrar_aluno(self, musico):
        self.alunos.append(musico)

    def formar_bandas(self):

        self.bandas = [
            Banda("Banda 1"),
            Banda("Banda 2"),
            Banda("Banda 3")
        ]

        for musico in self.alunos:

            bandas_sem_instrumento = []

            for banda in self.bandas:

                instrumentos_banda = set()

                for integrante in banda.get_musicos():
                    instrumentos_banda.update(integrante.get_instrumentos())

                possui_instrumento = False

                for instrumento in musico.get_instrumentos():
                    if instrumento in instrumentos_banda:
                        possui_instrumento = True

                if not possui_instrumento:
                    bandas_sem_instrumento.append(banda)

            if len(bandas_sem_instrumento) > 0:

                banda_escolhida = min(
                    bandas_sem_instrumento,
                    key=lambda banda: len(banda.get_musicos())
                )

            else:

                banda_escolhida = None
                menor_comum = 999
                menor_integrantes = 999

                for banda in self.bandas:

                    instrumentos_banda = set()

                    for integrante in banda.get_musicos():
                        instrumentos_banda.update(integrante.get_instrumentos())

                    quantidade_comum = 0

                    for instrumento in musico.get_instrumentos():
                        if instrumento in instrumentos_banda:
                            quantidade_comum += 1

                    quantidade_integrantes = len(banda.get_musicos())

                    if (quantidade_comum < menor_comum or
                        (quantidade_comum == menor_comum and
                         quantidade_integrantes < menor_integrantes)):

                        banda_escolhida = banda
                        menor_comum = quantidade_comum
                        menor_integrantes = quantidade_integrantes

            banda_escolhida.adicionar_musico(musico)