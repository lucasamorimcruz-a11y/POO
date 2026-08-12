class Carro:
    def __init__(self, marca:str)-> None:
        self.__marca = marca

    def __str__(self) -> str:
        return "O carro {}".format(self.__marca)
    