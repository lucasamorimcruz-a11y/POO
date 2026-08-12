class Bicicleta:
    def __init__(self, marca:str, idade: int) -> None:
        self.__marca = marca
        self.__idade = idade
        

    def __str__(self) -> str:
        return "A bicicleta: {}, tem idade {}".format(self.__marca, self.__idade)