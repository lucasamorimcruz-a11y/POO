#teste

pessoa = 'Nome {} - Idade {}'
pessoa_formatada = pessoa.format('Vinicius', 20)
print(pessoa_formatada)

nome = "vinicius takeo friedrich kuwaki"
nome_quebrado = nome.split(" ");
print (nome_quebrado)

list = [1,2,3,4,5]
for number in list:
    print(number)

def soma_0 (x, y):
    return x + y

def soma_1 (x:int, y:int):
    return x + y

def soma_2 (x:int = 0, y:int = 0) -> int:
    return x +_y

file = open("file.txt", "w")
file.write("Hi there, how are you? \n")