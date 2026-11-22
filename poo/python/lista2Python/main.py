from escolaMusica import EscolaMusica
from Musico import Musico
from Instrumento import Instrumento


escola = EscolaMusica()

m1 = Musico("Joao", 20)
m1.adicionar_instrumento(Instrumento.GUITARRA)
m1.adicionar_instrumento(Instrumento.BAIXO)

m2 = Musico("Maria", 21)
m2.adicionar_instrumento(Instrumento.BATERIA)
m2.adicionar_instrumento(Instrumento.TECLADO)

m3 = Musico("Pedro", 22)
m3.adicionar_instrumento(Instrumento.VIOLAO)
m3.adicionar_instrumento(Instrumento.SAXOFONE)

m4 = Musico("Ana", 19)
m4.adicionar_instrumento(Instrumento.GUITARRA)
m4.adicionar_instrumento(Instrumento.VIOLAO)

m5 = Musico("Carlos", 23)
m5.adicionar_instrumento(Instrumento.BAIXO)
m5.adicionar_instrumento(Instrumento.SAXOFONE)

escola.cadastrar_aluno(m1)
escola.cadastrar_aluno(m2)
escola.cadastrar_aluno(m3)
escola.cadastrar_aluno(m4)
escola.cadastrar_aluno(m5)

escola.formar_bandas()

for banda in escola.bandas:

    print(banda.get_nome())

    for musico in banda.get_musicos():
        print(f"  {musico.get_nome()} - ", end="")

        for instrumento in musico.get_instrumentos():
            print(instrumento.value, end=" ")

        print()

    print()