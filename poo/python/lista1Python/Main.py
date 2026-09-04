from Cliente import Cliente
from Funcionario import Funcionario
from Livro import Livro
from RegistroVenda import RegistroVenda


cliente1 = Cliente("Lucas", 5)

funcionario1 = Funcionario("Juca", 2500.50)

livro1 = Livro("123456", "Dom Casmurro", 10, 35.90)
livro2 = Livro("654321", "O Hobbit", 20, 45.90)

livros_vendidos = [livro1, livro2]

registro1 = RegistroVenda(cliente1, funcionario1, livros_vendidos)


print(cliente1)
print(funcionario1)
print(livro1)
print(livro2)
print(registro1)