import java.io.*;
import java.util.LinkedList;

public class Pilha<X>{
	private LinkedList<X> conteudo;

	public Pilha() {
		conteudo = new LinkedList<>();
	}

	public void empilha(X x) {
		conteudo.addFirst(x);
	}

	public X desempilha() {
		if(this.estaVazia()) {
			throw new Error("Pilha vazia");
		}else {
			return conteudo.removeFirst();
		}
	}

	public boolean estaVazia() {
		return conteudo.isEmpty();
	}

	public X topo() {
		if(this.estaVazia()) {
			throw new Error("Pilha vazia");
		}else {
			return conteudo.getFirst();
		}
	}

	public String toString() {
		return conteudo.toString();

	}

	public void reinicialize() {
		conteudo.clear();
	}
	
	public String toStringInverse() {
		Pilha<X> temp = new Pilha<>();
		//COMPLETAR
		//percorrer conteudo e ir empilhando em temp;
		//depois vai desempilhando e imprimindo
		return null;
		
	}
}

class CalcRPN {
	// variaveis da instancia :
	// uma pilha para os calculos
	Pilha<Double> aPilha;
	Pilha<Operacao> hist;
	// construtor
	CalcRPN () {
		aPilha = new Pilha<>();
		hist = new Pilha<>();
	}
	// Adic~ao de dois elementos do topo da pilha
	void mais() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b+a;
		aPilha.empilha(res);
		Operacao code = new Operacao('+',a,b);
		hist.empilha(code);
	}
	// Subtrac~ao de dois elementos do topo da pilha
	void menos() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b-a;
		aPilha.empilha(res);

		Operacao code = new Operacao('-',a,b);
		hist.empilha(code);
	}
	// Multiplicac~ao de dois elementos do topo da pilha
	void vezes() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b*a;
		aPilha.empilha(res);

		Operacao code = new Operacao('*',a,b);
		hist.empilha(code);
	}
	// Divis~ao de dois elementos no topo da pilha
	void dividido() {
		Double a = aPilha.desempilha();
		Double b = aPilha.desempilha();
		Double res = b/a;
		aPilha.empilha(res);

		Operacao code = new Operacao('/',a,b);
		hist.empilha(code);
	}
	// retorna o conteudo do topo da pilha
	Double resultado() {
		return aPilha.topo();
	}
	// interpretador de comandos
	void exec(String cmd) {
		if(cmd == "+") {
			this.mais();
		}else
			if(cmd == "-") {
				this.menos();
			}else
				if(cmd == "*") {
					this.vezes();
				}else
					if(cmd == "/") {
						this.dividido();
					}else{
						double cmdDouble = Double.parseDouble(cmd);
						aPilha.empilha(cmdDouble);
						Operacao code = new Operacao(cmdDouble);
						hist.empilha(code);		
					}if(cmd == "clear") {
						aPilha.reinicialize();
						hist.reinicialize();
					}
					if(cmd == "hist") {
						hist.toStringInverse();
					}
	}
	
	void cancela() {
		//precisa ajeitar
        if(hist.topo() == 'e'){
            aPilha.desempilha();
            hist.desempilha();
        }else{
            aPilha.desempilha();
            aPilha.empilha(hist.topo().b);
            aPilha.empilha(hist.topo().a);
            hist.desempilha();
}
		
	}

	static void interfaceUsuario() throws IOException {
		CalcRPN calc = new CalcRPN() ;
		String line;
		BufferedReader reader = new BufferedReader
				(new InputStreamReader (System.in));
		while((line = reader.readLine()) != null) {
			if (line.isEmpty())
				continue;
			for (String s : line.split(" "))
				calc.exec(s);
			System.out.println("Pilha = " + calc.aPilha);
		}
		System.out.println("Ate logo");
	}
}

class Operacao{
	private char code;
	private double a,b;

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
		if(code == 'e') {
			String str = Double.toString(a);
			return str;
		}else {
			String cstr = Character.toString(code);
			return cstr;
			
		}
		
	}
}
