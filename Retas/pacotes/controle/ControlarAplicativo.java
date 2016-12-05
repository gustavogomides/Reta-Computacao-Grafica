package controle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import modelo.Reta;
import view.MontarPainelInicial;

public class ControlarAplicativo implements ActionListener {

	private MontarPainelInicial pnCenario;
	private Graphics desenho;
	private ArrayList<Reta> listaRetasHorizontais = new ArrayList<>();
	private ArrayList<Reta> listaRetasVerticais = new ArrayList<>();
	private ArrayList<Reta> listaRetasDiagonais = new ArrayList<>();
	private ArrayList<Reta> listaRetasInclinadas = new ArrayList<>();
	private ArrayList<Reta> auxiliarPerpendicular = new ArrayList<>();
	public ArrayList<Reta> retas;
	public ArrayList<Reta> arrayX;
	public ArrayList<Reta> arrayXCruzamentos = new ArrayList<>();

	public boolean selectReta = false;
	public boolean cruzamentos = false;
	public boolean perpendiculares = false;

	private ControlarReta controleReta;

	public int escolhaCruzamento;
	public int escolhaPerpendicular;

	// ****************************************************************************
	// CONSTRUTOR DA CLASSE ControlarAplicativo
	public ControlarAplicativo() {
		pnCenario = new MontarPainelInicial(this);
		pnCenario.showPanel();
		pnCenario.mensagemInicial();
		desenho = pnCenario.iniciarGraphics();
		retas = pnCenario.retas;
		arrayX = pnCenario.arrayX;
		controleReta = new ControlarReta();
	}

	// ****************************************************************************
	// ACTION PERFORMED - CAPTURAR E TRATAR CLIQUE DOS BOTÕES
	public void actionPerformed(ActionEvent e) {
		String comando;
		comando = e.getActionCommand();

		// MÉTODO CLÁSSICO
		if (comando.equals("metodoClassico")) {
			pnCenario.desenharClassico = true;
			pnCenario.desenharDDASimples = false;
			pnCenario.desenharDDAInteiro = false;
			pnCenario.bloquearBotoes(true);
			auxiliarPerpendicular.clear();
			limparRetas();
		}

		// MÉTODO DDA SIMPLES
		if (comando.equals("metodoDDASimples")) {
			pnCenario.desenharDDASimples = true;
			pnCenario.desenharDDAInteiro = false;
			pnCenario.desenharClassico = false;
			pnCenario.bloquearBotoes(true);
			auxiliarPerpendicular.clear();
			limparRetas();
		}

		// MÉTODO DDA INTEIRO
		if (comando.equals("metodoDDAInteiro")) {
			pnCenario.desenharDDAInteiro = true;
			pnCenario.desenharClassico = false;
			pnCenario.desenharDDASimples = false;
			pnCenario.bloquearBotoes(true);
			auxiliarPerpendicular.clear();
			limparRetas();
		}

		// MOSTRAR OS PONTOS
		if (comando.equals("mostrarPontos")) {
			imprimirRetasPontos(false, desenho, 0);
			auxiliarPerpendicular.clear();
			limparRetas();
		}

		// MOSTRAR AS RETAS
		if (comando.equals("mostrarRetas")) {
			cruzamentos = false;
			perpendiculares = false;
			int escolha;
			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolha = Integer.parseInt(escolhaInformada);
			} while ((escolha < 1) || (escolha > 3));

			if (!escolhaInformada.equals("")) {
				pnCenario.ocultarDesenho();
				imprimirRetasPontos(true, desenho, escolha);
			}
			auxiliarPerpendicular.clear();
		}

		// RETAS HORIZONTAIS
		if (comando.equals("botaoHorizontal")) {
			cruzamentos = false;
			perpendiculares = false;
			desenho = pnCenario.iniciarGraphics();
			new ControlarReta();
			int escolha;
			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolha = Integer.parseInt(escolhaInformada);
			} while ((escolha < 1) || (escolha > 3));

			if (!escolhaInformada.equals("")) {
				pnCenario.ocultarDesenho();
				retasHorizontaisVerticaisDiagonaisInclinadas(1, desenho, escolha);
			}
			auxiliarPerpendicular.clear();
		}

		// RETAS VERTICAIS
		if (comando.equals("botaoVertical")) {
			cruzamentos = false;
			perpendiculares = false;
			desenho = pnCenario.iniciarGraphics();
			new ControlarReta();
			int escolha;
			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolha = Integer.parseInt(escolhaInformada);
			} while ((escolha < 1) || (escolha > 3));

			if (!escolhaInformada.equals("")) {
				pnCenario.ocultarDesenho();
				retasHorizontaisVerticaisDiagonaisInclinadas(2, desenho, escolha);
			}
		}

		// RETAS DIAGONAIS
		if (comando.equals("botaoDiagonal")) {
			cruzamentos = false;
			perpendiculares = false;
			int escolha;
			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolha = Integer.parseInt(escolhaInformada);
			} while ((escolha < 1) || (escolha > 3));

			if (!escolhaInformada.equals("")) {
				pnCenario.ocultarDesenho();
				retasHorizontaisVerticaisDiagonaisInclinadas(3, desenho, escolha);
			}
			auxiliarPerpendicular.clear();
		}

		// RETAS INCLINADAS
		if (comando.equals("botaoInclinada")) {
			cruzamentos = false;
			perpendiculares = false;
			int escolha;
			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolha = Integer.parseInt(escolhaInformada);
			} while ((escolha < 1) || (escolha > 3));

			if (!escolhaInformada.equals("")) {
				pnCenario.ocultarDesenho();
				retasHorizontaisVerticaisDiagonaisInclinadas(4, desenho, escolha);
			}
			auxiliarPerpendicular.clear();
		}

		// RETAS CRUZAMENTOS
		if (comando.equals("botaoCruzamento")) {
			cruzamentos = true;
			perpendiculares = false;
			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolhaCruzamento = Integer.parseInt(escolhaInformada);
			} while ((escolhaCruzamento < 1) || (escolhaCruzamento > 3));

			auxiliarPerpendicular.clear();
		}

		// RETAS PERPENDICULARES
		if (comando.equals("botaoPerpendicular")) {
			perpendiculares = true;
			cruzamentos = false;

			String escolhaInformada;
			do {
				escolhaInformada = JOptionPane.showInputDialog("Mostrar reta utilizando o método:\n"
						+ "[1] Método Clássico\n" + "[2] Método DDA Simples\n" + "[3] Método DDA Inteiro");
				escolhaPerpendicular = Integer.parseInt(escolhaInformada);
			} while ((escolhaPerpendicular < 1) || (escolhaPerpendicular > 3));

			if (!escolhaInformada.equals("")) {
				escolhaPerpendicular = Integer.parseInt(escolhaInformada);
				pnCenario.ocultarDesenho();

				for (int i = 0; i < retas.size(); i++) {
					auxiliarPerpendicular.add(retas.get(i));
					desenharX(retas.get(i).getPontoInicial(), Color.RED, desenho, escolhaPerpendicular);
					desenharX(retas.get(i).getPontoFinal(), Color.RED, desenho, escolhaPerpendicular);
				}
				for (Reta reta : auxiliarPerpendicular) {
					mostrarRetas(reta, Color.BLACK, desenho, escolhaPerpendicular);
				}
			}
		}

		// LIMPAR TELA
		if (comando.equals("botaoLimpar")) {
			cruzamentos = false;
			perpendiculares = false;
			int option;
			option = JOptionPane.showConfirmDialog(null, "Deseja limpar a tela e exluir o desenho?", "Limpar",
					JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
				limparLista();
			}
			auxiliarPerpendicular.clear();
		}

		// OCULTAR DESENHO
		if (comando.equals("botaoOcultar")) {
			pnCenario.ocultarDesenho();
			auxiliarPerpendicular.clear();
		}

		// FIM DO PROGRAMA
		if (comando.equals("botaoFim")) {
			cruzamentos = false;
			perpendiculares = false;
			int option;

			option = JOptionPane.showConfirmDialog(null, "Deseja sair da aplicação?", "Sair",
					JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			auxiliarPerpendicular.clear();
		}
	}

	// ****************************************************************************
	// EXCLUIR RETAS DESENHADAS E LIMPAR TELA
	private void limparLista() {
		if (retas.size() > 0) {
			retas.clear();
			pnCenario.ocultarDesenho();
		}
	}

	// ****************************************************************************
	// IMPRIMIR RETAS OU PONTOS
	// opcao = true -> imprimir retas
	// opcao = false -> imprimir pontos
	public void imprimirRetasPontos(boolean opcao, Graphics g, int escolha) {
		int k;
		Reta reta;
		Point pa, pb;
		int xa, xb, ya, yb;
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		if (arrayXCruzamentos.size() > 0) {
			Color cinza = new Color(238, 238, 238);
			for (Reta r : arrayXCruzamentos) {
				desenharX(r.getPontoInicial(), cinza, g, escolha);
				desenharX(r.getPontoFinal(), cinza, g, escolha);
			}
			arrayXCruzamentos.clear();
		}

		for (k = 0; k < retas.size(); k++) {
			reta = retas.get(k);

			pa = reta.getPontoInicial();
			pb = reta.getPontoFinal();
			xa = pa.x;
			ya = pa.y;

			xb = pb.x;
			yb = pb.y;

			if (opcao == false) {
				listModel.addElement("( " + xa + ", " + ya + " ) - ( " + xb + ", " + yb + " )\n");
			} else {
				mostrarRetas(reta, Color.BLACK, g, escolha);
				desenharX(reta.getPontoInicial(), Color.RED, g, escolha);
				desenharX(reta.getPontoFinal(), Color.RED, g, escolha);
			}
		}

		if (retas.size() == 0) {
			if (opcao == false) {
				JOptionPane.showMessageDialog(null, "Não existem pontos!");
			} else {
				JOptionPane.showMessageDialog(null, "Não existem retas!");
			}
		} else {
			if (opcao == false) {
				JList<String> list = new JList<String>(listModel);
				JScrollPane scrollPane = new JScrollPane(list);

				scrollPane.setPreferredSize(new Dimension(200, 200));
				JOptionPane.showMessageDialog(null, scrollPane);
			}
		}
	}

	// ****************************************************************************
	// DESENHAR RETAS
	public void mostrarRetas(Reta reta, Color cor, Graphics g, int escolha) {
		// new ControlarReta();

		controleReta.desenharReta(reta, cor, g, escolha);
		/*
		 * if (tipo == 1) { controleReta.desenharRetaMetodoClassico(reta, cor,
		 * g); } else if (tipo == 2) { controleReta.desenharRetaDDASimples(reta,
		 * cor, g); } else { controleReta.desenharRetaDDAInteiro(reta, cor, g);
		 * }
		 */

	}

	// ****************************************************************************
	// DETERMINAR SE A RETA É HORIZONTAL, VERTICAL, DIAGONAL OU INCLINADA
	// opcao = 1 -> reta horizontal
	// opcao = 2 -> reta vertical
	// opcao = 3 -> reta diagonal
	// opcao = 4 -> reta inclinada
	private void retasHorizontaisVerticaisDiagonaisInclinadas(int opcao, Graphics g, int escolha) {
		int k;
		Reta reta;
		Point pa, pb;
		int xa, ya, xb, yb, contadorH = 0, contadorV = 0, contadorD = 0, contadorI = 0;

		for (k = 0; k < retas.size(); k++) {
			reta = retas.get(k);
			pa = reta.getPontoInicial();
			pb = reta.getPontoFinal();
			xa = pa.x;
			xb = pb.x;
			ya = pa.y;
			yb = pb.y;
			if (opcao == 1) {
				if (ya == yb) {
					listaRetasHorizontais.add(reta);
					contadorH++;
				}
			} else if (opcao == 2) {
				if (xa == xb) {
					listaRetasVerticais.add(reta);
					contadorV++;
				}
			} else if (opcao == 3) {
				if (Math.abs(xa - xb) == Math.abs(ya - yb)) {
					listaRetasDiagonais.add(reta);
					contadorD++;
				}
			} else {
				if ((ya != yb) && (xa != xb) && (Math.abs(xa - xb) != Math.abs(ya - yb))) {
					listaRetasInclinadas.add(reta);
					contadorI++;
				}
			}
		}

		if (opcao == 1) {
			if (contadorH == 0) {
				imprimirRetasPontos(true, desenho, escolha);
				JOptionPane.showMessageDialog(null, "Não existem retas horizontais!");
			} else {
				pnCenario.ocultarDesenho();
				for (int i = 0; i < listaRetasHorizontais.size(); i++) {
					desenharX(listaRetasHorizontais.get(i).getPontoInicial(), Color.RED, g, escolha);
					desenharX(listaRetasHorizontais.get(i).getPontoFinal(), Color.RED, g, escolha);
					controleReta.desenharReta(listaRetasHorizontais.get(i), Color.RED, g, escolha);
				}
			}
		} else if (opcao == 2) {
			if (contadorV == 0) {
				imprimirRetasPontos(true, desenho, escolha);
				JOptionPane.showMessageDialog(null, "Não existem retas verticais!");
			} else {
				pnCenario.ocultarDesenho();
				for (int i = 0; i < listaRetasVerticais.size(); i++) {
					desenharX(listaRetasVerticais.get(i).getPontoInicial(), Color.RED, g, escolha);
					desenharX(listaRetasVerticais.get(i).getPontoFinal(), Color.RED, g, escolha);
					controleReta.desenharReta(listaRetasVerticais.get(i), Color.RED, g, escolha);
				}
			}
		} else if (opcao == 3) {
			if (contadorD == 0) {
				imprimirRetasPontos(true, desenho, escolha);
				JOptionPane.showMessageDialog(null, "Não existem retas diagonais!");
			} else {
				pnCenario.ocultarDesenho();
				for (int i = 0; i < listaRetasDiagonais.size(); i++) {
					desenharX(listaRetasDiagonais.get(i).getPontoInicial(), Color.RED, g, escolha);
					desenharX(listaRetasDiagonais.get(i).getPontoFinal(), Color.RED, g, escolha);
					controleReta.desenharReta(listaRetasDiagonais.get(i), Color.RED, g, escolha);
				}
			}
		} else {
			if (contadorI == 0) {
				imprimirRetasPontos(true, desenho, escolha);
				JOptionPane.showMessageDialog(null, "Não existem retas inclinadas!");
			} else {
				pnCenario.ocultarDesenho();
				for (int i = 0; i < listaRetasInclinadas.size(); i++) {
					desenharX(listaRetasInclinadas.get(i).getPontoInicial(), Color.RED, g, escolha);
					desenharX(listaRetasInclinadas.get(i).getPontoFinal(), Color.RED, g, escolha);
					controleReta.desenharReta(listaRetasInclinadas.get(i), Color.RED, g, escolha);
				}
			}
		}
	}

	// ******************************************************************************************************************
	// DESENHAR 'X' NO PONTO INICIAL E FINAL DA RETA
	public void desenharX(Point point, Color color, Graphics g, int escolha) {
		g.setColor(color);

		arrayX.add(new Reta(new Point(point.x, point.y), new Point(point.x + 5, point.y - 5)));
		arrayX.add(new Reta(new Point(point.x, point.y), new Point(point.x - 5, point.y + 5)));
		arrayX.add(new Reta(new Point(point.x - 5, point.y - 5), new Point(point.x, point.y)));
		arrayX.add(new Reta(new Point(point.x + 5, point.y + 5), new Point(point.x, point.y)));

		ControlarReta controlarReta = new ControlarReta();
		controlarReta.desenharX(point, color, g, escolha);
	}

	// ******************************************************************************************************************
	// DETERMINAR SE AS RETAS SE CRUZAM E IMPRIMIR 'X' NO LOCAL DO CRUZAMENTO
	public void retasCruzamentos(Reta reta1, Reta reta2, Color color, Graphics g, int escolha) {

		double x1, x2, x3, x4;
		double y1, y2, y3, y4;
		double d, xi, yi;
		Point pontoColisao;

		// Coordenadas da reta 1.
		x1 = reta1.getPontoInicial().getX();
		x2 = reta1.getPontoFinal().getX();
		y1 = reta1.getPontoInicial().getY();
		y2 = reta1.getPontoFinal().getY();

		// Coordenadas da reta 2.
		x3 = reta2.getPontoInicial().getX();
		x4 = reta2.getPontoFinal().getX();
		y3 = reta2.getPontoInicial().getY();
		y4 = reta2.getPontoFinal().getY();

		// CÃ¡lculo do Determinante.

		// Cálculo do Determinante.
		d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

		if (d == 0)
			return;

		// Pontos onde as retas se cruzam.
		xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
		yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
		pontoColisao = new Point((int) xi, (int) yi);
		if (pontoNaReta(reta2, pontoColisao, false, 3)) {
			desenharX(pontoColisao, color, g, escolha);
		}

	}

	// ******************************************************************************************************************
	// DETERMINAR SE OS PONTOS SE CRUZAM
	// return true -> os pontos se cruzam
	// return false -> os pontos não se cruzam
	public boolean pontosCruzam(Point a1, Point b1, Point a2, Point b2) {
		// LOCALIZAR AS ORIENTAÇÕES
		int o1 = orientacaoPontos(a1, b1, a2);
		int o2 = orientacaoPontos(a1, b1, b2);
		int o3 = orientacaoPontos(a2, b2, a1);
		int o4 = orientacaoPontos(a2, b2, b1);

		// Caso geral
		if (o1 != o2 && o3 != o4)
			return true;

		// Casos especiais

		// a1, b1 e a2 são colineares e a2 está na reta a1b1
		if (o1 == 0 && encontraNaReta(a1, a2, b1))
			return true;

		// a1, b1 e a2 são colineares e b2 está na reta a1b1
		if (o2 == 0 && encontraNaReta(a1, b2, b1))
			return true;

		// a2, b2 e a1 são colineares e a1 está na reta a2b2
		if (o3 == 0 && encontraNaReta(a2, a1, b2))
			return true;

		// a2, b2 e b1 são colineares e b1 está na reta a2q2
		if (o4 == 0 && encontraNaReta(a2, b1, b2))
			return true;

		return false;
	}

	// ******************************************************************************************************************
	// ENCONTRAR ORIENTAÇÃO DOS PONTOS p1, p2 3 p3
	// return 0 -> p1, p2 e p3 são colineares
	// return 1 -> sentido horário
	// return 2 -> sentido anti-horário
	private int orientacaoPontos(Point p1, Point p2, Point p3) {
		int val = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);

		// Colineares.
		if (val == 0)
			return 0;

		// Sentido horÃ¡rio ou sentido anti-horÃ¡rio.
		return (val > 0) ? 1 : 2;
	}

	// ******************************************************************************************************************
	// VERIFICAR SE O PONTO p2 ESTÁ NO SEGMENTO p1p3
	// return true -> está no segmento
	// return false -> não está no segmento
	private boolean encontraNaReta(Point p1, Point p2, Point p3) {
		if (p2.x <= Math.max(p1.x, p3.x) && p2.x >= Math.min(p1.x, p3.x) && p2.y <= Math.max(p1.y, p3.y)
				&& p2.y >= Math.min(p1.y, p3.y))
			return true;

		return false;
	}

	// ******************************************************************************************************************
	// VERIFICAR SE O PONTO ESTÁ NA RETA
	// return true -> o ponto está na reta
	// return false -> o ponto não está na reta
	public boolean pontoNaReta(Reta reta, Point ponto, boolean opcao, int escolha) {
		int x1, x2, y1, y2;
		int x, y;
		double m, b;
		double epsilon;

		epsilon = 5;

		x = ponto.x;
		y = ponto.y;

		x1 = reta.getPontoInicial().x;
		y1 = reta.getPontoInicial().y;
		x2 = reta.getPontoFinal().x;
		y2 = reta.getPontoFinal().y;

		double dy = y2 - y1;
		double dx = x2 - x1;

		if (dx == 0) {
			dx = 1;
		}
		m = dy / dx;
		b = y1 - m * x1;

		if (opcao) {
			if (Math.abs(y - (m * x + b)) < epsilon) {
				mostrarRetas(reta, Color.red, desenho, escolha);
				pnCenario.labelVisor.setText("Ponto: ( " + (int) x + " ; " + (int) y + " ) - RETA");
				return true;
			}
		} else {
			if (Math.abs(y - (m * x + b)) < epsilon) {
				pnCenario.labelVisor.setText("Ponto: ( " + (int) x + " ; " + (int) y + " ) - RETA");
				return true;
			}
		}
		return false;
	}

	// ******************************************************************************************************************
	// VERIFICAR SE RETAS SÃO PERPENDICULARES
	// return true -> as retas são perpendiculares
	// return false -> as retas não são perpendiculares
	public boolean retasPerpendiculares(Reta reta1, Reta reta2) {
		int x1, x2, x3, x4;
		int y1, y2, y3, y4;
		double m1, m2;

		// Coordenadas da reta 1
		x1 = reta1.getPontoInicial().x;
		x2 = reta1.getPontoFinal().x;
		y1 = reta1.getPontoInicial().y;
		y2 = reta1.getPontoFinal().y;

		// Coordenadas da reta 2
		x3 = reta2.getPontoInicial().x;
		x4 = reta2.getPontoFinal().x;
		y3 = reta2.getPontoInicial().y;
		y4 = reta2.getPontoFinal().y;

		// Verifica se é divisão por 0, se for verifica se as retas se cruzam
		if (x1 - x2 == 0 || x3 - x4 == 0) {
			if (y1 == y2 || y3 == y4) {
				if (pontosCruzam(reta1.getPontoInicial(), reta1.getPontoFinal(), reta2.getPontoInicial(),
						reta2.getPontoFinal())) {
					return true;
				}
			}
			return false;
		}

		// Coeficientes angulares das retas
		m1 = (y2 - y1) / (x2 - x1);
		m2 = (y4 - y3) / (x4 - x3);

		// Verifica a perpendicularidade
		if (m1 * m2 == -1) {
			return true;
		}

		return false;
	}

	// ******************************************************************************************************************
	// LOCALIZAR AS RETAS PERPENDICULARES
	public void localizaPerpendiculares(Point ponto, Graphics g, int escolha) {
		int i = 0;
		for (Reta reta : retas) {
			mostrarRetas(reta, Color.BLACK, g, escolha);
			if (pontoNaReta(reta, ponto, false, escolha)) {
				for (i = 0; i <= retas.size() - 2; i++) {
					boolean resultado = retasPerpendiculares(reta, retas.get(i));
					if (resultado) {
						mostrarRetas(reta, Color.BLUE, g, escolha);
						mostrarRetas(retas.get(i), Color.GREEN, g, escolha);
					}
				}
			}
		}
	}

	// ******************************************************************************************************************
	// LOCALIZAR OS CRUZAMENTOS DA RETA
	public void localizaCruzamentos(Point ponto, Graphics g, int escolha) {
		int i = 0;
		for (Reta reta : retas) {
			if (pontoNaReta(reta, ponto, true, escolha)) {
				mostrarRetas(reta, Color.orange, g, escolha);
				for (i = 0; i <= retas.size() - 1; i++) {
					retasCruzamentos(reta, retas.get(i), Color.BLUE, g, escolha);
				}
			}
		}
	}

	// ******************************************************************************************************************
	private void limparRetas() {
		cruzamentos = false;
		perpendiculares = false;
		if (!retas.isEmpty()) {
			for (Reta reta : retas) {
				mostrarRetas(reta, Color.BLACK, desenho, 3);
			}
			Color cinza = new Color(238, 238, 238);
			for (Reta reta : arrayXCruzamentos) {
				desenharX(reta.getPontoInicial(), cinza, desenho, 3);
				desenharX(reta.getPontoInicial(), cinza, desenho, 3);
			}
			arrayXCruzamentos.clear();
		}
	}
}
