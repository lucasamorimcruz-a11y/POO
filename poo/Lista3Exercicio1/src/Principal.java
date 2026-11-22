public class Principal {
    static void main() {
        Estagiario e = new Estagiario("lucas", "13309466930", 1200, 0);
        Gerente g = new Gerente("jucas", "000000000", 10000, 12, 90);
        Diretor d = new Diretor("LucasProMax", "00000001", 24000, 10000);
        System.out.println(e.getNome() + " - " +  e.calcularSalario());
        System.out.println(g.getNome() + " - " + g.calcularSalario());
        System.out.println(d.getNome() + " - " + d.calcularSalario());
    }
}
