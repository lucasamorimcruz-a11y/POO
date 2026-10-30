import java.io.IOException;
import java.io.PrintWriter;

/** Salva uma rota como imagem SVG (abra o arquivo no navegador). */
public class Svg {

    public static void salvar(Rota rota, String arquivo) {
        Instancia inst = rota.getInstancia();
        int tam = 600;
        double esc = tam / 1000.0;
        double raio = inst.n() > 200 ? 1.5 : 4;
        try (PrintWriter out = new PrintWriter(arquivo)) {
            out.printf("<svg xmlns='http://www.w3.org/2000/svg' width='%d' height='%d' viewBox='-10 -30 %d %d'>%n",
                    tam + 20, tam + 40, tam + 20, tam + 40);
            out.println("<rect x='-10' y='-30' width='100%' height='100%' fill='white'/>");
            out.printf("<text x='0' y='-10' font-family='sans-serif' font-size='14'>n = %d   custo = %.1f</text>%n",
                    inst.n(), rota.custo());
            StringBuilder pts = new StringBuilder();
            for (int c : rota.getCidades()) {
                pts.append(String.format(java.util.Locale.US, "%.1f,%.1f ", inst.x(c) * esc, inst.y(c) * esc));
            }
            out.printf("<polygon points='%s' fill='none' stroke='#1F4E79' stroke-width='1.5'/>%n", pts);
            for (int i = 0; i < inst.n(); i++) {
                String cor = i == 0 ? "#D9622B" : "#1F4E79";
                out.printf(java.util.Locale.US, "<circle cx='%.1f' cy='%.1f' r='%.1f' fill='%s'/>%n",
                        inst.x(i) * esc, inst.y(i) * esc, i == 0 ? raio * 1.8 : raio, cor);
            }
            out.println("</svg>");
        } catch (IOException e) {
            System.err.println("Erro ao salvar " + arquivo + ": " + e.getMessage());
        }
    }
}
