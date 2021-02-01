package lab1;

import java.io.*;
import java.util.LinkedList;

public class Pilha<X> {

    private LinkedList<X> conteudo;

    public Pilha() {
        conteudo = new LinkedList<>();
    }

    public void empilha(X x) {
        conteudo.addFirst(x);
    }

    public X desempilha() {
        if (this.estaVazia()) {
            throw new Error("Pilha vazia");
        } else {
            return conteudo.removeFirst();
        }
    }

    public boolean estaVazia() {
        return conteudo.isEmpty();
    }

    public X topo() {
        if (this.estaVazia()) {
            throw new Error("Pilha vazia");
        } else {
            return conteudo.getFirst();
        }
    }

    @Override
    public String toString() {
        return conteudo.toString();

    }

    public void reinicialize() {
        conteudo.clear();
    }

    public String toStringInverse() {
        Pilha<X> temp = new Pilha<>();
        if (this.estaVazia()) {
            throw new Error("Pilha vazia");
        } else {
            while (!this.estaVazia()) {
                temp.empilha(this.desempilha());
            }
            return temp.toString();
        }
    }

    static void test1() {
        Pilha<Double> aPilha = new Pilha<Double>();
        aPilha.empilha(1.1);
        aPilha.empilha(2.1);
        aPilha.empilha(3.1);
        aPilha.empilha(4.1);
        aPilha.empilha(5.1);
        double valor = 0.0;
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
    }

    static void test2() {
        Pilha<Double> aPilha = new Pilha<Double>();
        System.out.println(aPilha);
        aPilha.empilha(1.1);
        System.out.println(aPilha);
        aPilha.empilha(2.1);
        System.out.println(aPilha);
        aPilha.empilha(3.1);
        System.out.println(aPilha);
        double valor = 0.0;
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
    }

    /*public static void main(String[] args) {
        test2();
    }*/
}

class CalcRPN {

    Pilha<Double> aPilha;
    Pilha<Operacao> hist;

    CalcRPN() {
        aPilha = new Pilha<>();
        hist = new Pilha<>();
    }

    void mais() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        Double res = b + a;
        aPilha.empilha(res);
        Operacao code = new Operacao('+', a, b);
        hist.empilha(code);
    }

    void menos() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        Double res = b - a;
        aPilha.empilha(res);

        Operacao code = new Operacao('-', a, b);
        hist.empilha(code);
    }

    void vezes() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        Double res = b * a;
        aPilha.empilha(res);

        Operacao code = new Operacao('*', a, b);
        hist.empilha(code);
    }

    void dividido() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        Double res = b / a;
        aPilha.empilha(res);

        Operacao code = new Operacao('/', a, b);
        hist.empilha(code);
    }

    Double resultado() {
        return aPilha.topo();
    }

    void exec(String cmd) {

        if (cmd.equals("+")) {
            this.mais();
        } else if (cmd.equals("-")) {
            this.menos();
        } else if (cmd.equals("*")) {
            this.vezes();
        } else if (cmd.equals("/")) {
            this.dividido();
        } else {
            double cmdDouble = Double.parseDouble(cmd);
            aPilha.empilha(cmdDouble);
            Operacao code = new Operacao(cmdDouble);
            hist.empilha(code);
            if ("clear".equals(cmd)) {
                aPilha.reinicialize();
                hist.reinicialize();
            }
            if ("hist".equals(cmd)) {
                hist.toStringInverse();
            }
        }
    }

    void cancela() {
        if (hist.topo().getCode() == 'e') {
            aPilha.desempilha();
            hist.desempilha();
        } else {
            aPilha.desempilha();
            aPilha.empilha(hist.topo().getB());
            aPilha.empilha(hist.topo().getA());
            hist.desempilha();
        }

    }

    static void interfaceUsuario() throws IOException {
        CalcRPN calc = new CalcRPN();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            for (String s : line.split(" ")) {
                calc.exec(s);
            }
            System.out.println("Pilha = " + calc.aPilha);
        }
        System.out.println("Ate logo");
    }

    static void test() {
        CalcRPN calc = new CalcRPN();
        System.out.print("3 2 + = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 - = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.menos();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 * = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.vezes();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 / = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.dividido();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("1 2 + 3 4 - / 10 3 - * = ");
        calc.aPilha.empilha(1.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(4.0);
        calc.menos();
        calc.dividido();
        calc.aPilha.empilha(10.0);
        calc.aPilha.empilha(3.0);
        calc.menos();
        calc.vezes();
        System.out.println(calc.resultado());
    }

    /*public static void main(String[] args) {
        CalcRPN.test();
    }*/

}

class Operacao {

    private char code;
    private double a, b;

    public Operacao(double a) {
        this.code = 'e';
        this.a = a;
    }

    public Operacao(char code, double a, double b) {
        this.code = code;
        this.a = a;
        this.b = b;
    }

    public String toString() {
        if (code == 'e') {
            String str = Double.toString(a);
            return str;
        } else {
            String cstr = Character.toString(code);
            return cstr;

        }

    }

    public char getCode() {
        return this.code;
    }

    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public static void main(String[] args) {
        Operacao[] op = new Operacao[9];
        op[0] = new Operacao(16.0);
        op[1] = new Operacao(8.0);
        op[2] = new Operacao(4.0);
        op[3] = new Operacao(2.0);
        op[4] = new Operacao(1.0);
        op[5] = new Operacao(
                '+', 2.0, 1.0);

        op[6] = new Operacao(
                '-', 4.0, 3.0);
        op[7] = new Operacao(
                '*', 8.0, 1.0);
        op[8] = new Operacao(
                '/', 16.0, 8.0);
        for (int i = 0; i < op.length; i++) {
            System.out.print(op[i] + " ");
        }
        System.out.println();
    }

}
