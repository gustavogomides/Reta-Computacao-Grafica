package controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import modelo.Reta;

public class ControlarReta {

	// *************************************************************************************
	// MÉTODO QUE CHAMA DETERMINANDO MÉTODO DE DESENHO DE ACORDO COM A ESCOLHA
	// DO USUÁRIO
	public void desenharReta(Reta reta, Color cor, Graphics g, int escolha) {
		if (escolha == 1) {
			desenharRetaMetodoClassico(reta, cor, g);
		} else if (escolha == 2) {
			desenharRetaDDASimples(reta, cor, g);
		} else {
			desenharRetaDDAInteiro(reta, cor, g);
		}
	}

	// *************************************************************************************
	// DESENHAR RETA UTILIZANDO O METODO CLASSICO
	public void desenharRetaMetodoClassico(Reta reta, Color cor, Graphics g) {
		Point pontoA = reta.getPontoInicial();
		Point pontoB = reta.getPontoFinal();

		double m, b;
		int selecao = -1; // 0 - vertical, 1 - horizontal, 2- inclinada

		// DETECTA QUAL PONTO É O INÍCIO
		// Reta Vertical
		if ((pontoA.x == pontoB.x) && (pontoA.y > pontoB.y)) {
			Point aux;
			aux = new Point(pontoA.x, pontoA.y);
			pontoA.setLocation(pontoB.x, pontoB.y);
			pontoB.setLocation(aux.x, aux.y);

			selecao = 0;
		} else {
			selecao = 0;
		}

		// Reta Horizontal
		if ((pontoA.x > pontoB.x) && (pontoA.y == pontoB.y)) {
			Point aux;
			aux = new Point(pontoA.x, pontoA.y);
			pontoA.setLocation(pontoB.x, pontoB.y);
			pontoB.setLocation(aux.x, aux.y);

			selecao = 1;
		}

		// Retas Inclinadas
		if ((pontoA.x > pontoB.x)) {
			if (pontoA.y < pontoB.y) {
				Point aux;
				aux = new Point(pontoA.x, pontoA.y);
				pontoA.setLocation(pontoB.x, pontoB.y);
				pontoB.setLocation(aux.x, aux.y);

				selecao = 2;
			} else {
				Point aux;
				aux = new Point(pontoA.x, pontoA.y);
				pontoA.setLocation(pontoB.x, pontoB.y);
				pontoB.setLocation(aux.x, aux.y);

				selecao = 2;

			}
		}
		if ((pontoA.x < pontoB.x) && ((pontoA.y > pontoB.y) || (pontoA.y < pontoB.y))) {
			selecao = 2;
		}

		// EQUAÇÃO GERAL DA RETA
		m = (pontoB.getY() - pontoA.getY()) / (pontoB.getX() - pontoA.getX());
		b = pontoA.getY() - m * pontoA.getX();

		// RETA VERTICAL
		if (selecao == 0) {
			for (int i = pontoA.y; i < pontoB.y; i++) {
				desenharPonto(pontoA.x, (double) i, cor, g);
			}
		}

		// RETA HORIZONTAL
		else if (selecao == 1) {
			for (int i = pontoA.x; i < pontoB.x; i++) {
				desenharPonto((double) i, pontoA.y, cor, g);
			}
		}

		// RETA INCLINADA
		else if (selecao == 2) {
			for (int i = pontoA.x; i < pontoB.x; i++) {
				desenharPonto((double) i, m * i + b, cor, g);
			}
		}
	}

	// *************************************************************************************
	// DESENHAR RETA UTILIZANDO O MÉTODO DDA SIMPLES
	public void desenharRetaDDASimples(Reta reta, Color cor, Graphics g) {
		double xinicio = reta.getPontoInicial().getX();
		double yinicio = reta.getPontoInicial().getY();
		double xfim = reta.getPontoFinal().getX();
		double yfim = reta.getPontoFinal().getY();
		double dx, dy, m, xt, yt;

		dx = xfim - xinicio;
		dy = yfim - yinicio;

		m = dy / dx;

		// PLOTANDO PRIMEIRO PIXEL
		if ((Math.abs(m) < 1 && (xinicio > xfim)) || ((Math.abs(m) > 1) && (yinicio > yfim))) {
			troca(xinicio, yinicio, xfim, yfim);
		}

		desenharPonto(xinicio, yinicio, cor, g);

		// PLOTANDO OS PIXELS INTERMEDIÁRIOS
		if (Math.abs(m) < 1) {
			yt = yinicio;
			for (int x = (int) (xinicio + 1); x < (int) (xfim - 1); x++) {
				yt += m;
				desenharPonto((double) x, yt, cor, g);
			}
		} else {
			double m_inverso = 1 / m;
			xt = xinicio;
			for (int y = (int) (yinicio + 1); y < (int) (yfim - 1); y++) {
				xt += m_inverso;
				desenharPonto(xt, (double) y, cor, g);
			}
		}

		// PLOTANDO ULTIMO PIXEL
		desenharPonto(xfim, yfim, cor, g);

	}

	// *************************************************************************************
	// TROCAR OS PONTOS CASO 'dy < 0' NO MÉTODO DDA SIMPLES
	private void troca(double x1, double y1, double x2, double y2) {
		double t = x2;
		x2 = x1;
		x1 = t;

		t = y2;
		y2 = y1;
		y1 = t;
	}

	// *************************************************************************************
	// DESENHAR RETA UTILIZANDO O MÉTODO DDA INTEIRO
	public void desenharRetaDDAInteiro(Reta reta, Color cor, Graphics g) {
		double xinicio = reta.getPontoInicial().getX();
		double yinicio = reta.getPontoInicial().getY();
		double xfim = reta.getPontoFinal().getX();
		double yfim = reta.getPontoFinal().getY();
		double dx, dy, x, y;

		dx = xfim - xinicio;
		dy = yfim - yinicio;
		x = xinicio;
		y = yinicio;

		if ((Math.abs(dy) >= Math.abs(dx) && yinicio > yfim) || (Math.abs(dy) < Math.abs(dx) && dy < 0)) {
			x = xfim;
			y = yfim;
			dx = xinicio - xfim;
			dy = yinicio - yfim;
			troca(xinicio, yinicio, xfim, yfim);
		}

		desenharPonto(xinicio, yinicio, cor, g);

		if (dx >= 0) {
			if (Math.abs(dx) >= Math.abs(dy)) {
				caso1(dx, dy, x, y, cor, g);
			} else {
				caso2(dx, dy, x, y, cor, g);
			}
		} else {
			if (Math.abs(dx) >= Math.abs(dy)) {
				caso3(dx, dy, x, y, cor, g);
			} else {
				caso4(dx, dy, x, y, cor, g);
			}
		}

		desenharPonto(xfim, yfim, cor, g);

	}

	// *************************************************************************************
	// CASO 1 DO MÉTODO DDA INTEIRO
	// 0 <= m <= 1
	// dx > 0
	// dy > 0
	private void caso1(double dx, double dy, double x, double y, Color cor, Graphics g) {
		double erro = 0;
		for (int i = 0; i < Math.abs(dx - 1); i++) {
			if (erro <= 0) {
				x++;
				desenharPonto(x, y, cor, g);
				erro += dy;
			} else {
				x++;
				y++;
				desenharPonto(x, y, cor, g);
				erro += dy - dx;
			}
		}
	}

	// *************************************************************************************
	// CASO 2 DO MÉTODO DDA INTEIRO
	// m > 1
	// dx > 0
	// dy > 0
	private void caso2(double dx, double dy, double x, double y, Color cor, Graphics g) {
		double erro = 0;
		for (int i = 0; i < Math.abs(dy - 1); i++) {
			if (erro <= 0) {
				x++;
				y++;
				desenharPonto(x, y, cor, g);
				erro += dy - dx;
			} else {
				y++;
				desenharPonto(x, y, cor, g);
				erro -= dx;
			}
		}
	}

	// *************************************************************************************
	// CASO 3 DO MÉTODO DDA INTEIRO
	// -1 <= m <= 0
	// dx < 0
	// dy > 0
	private void caso3(double dx, double dy, double x, double y, Color cor, Graphics g) {
		double erro = 0;
		for (int i = 0; i < Math.abs(dx - 1); i++) {
			if (erro <= 0) {
				x--;
				desenharPonto(x, y, cor, g);
				erro += dy;
			} else {
				x--;
				y++;
				desenharPonto(x, y, cor, g);
				erro += dx + dy;
			}
		}
	}

	// *************************************************************************************
	// CASO 4 DO MÉTODO DDA INTEIRO
	// m < -1
	// dx < 0
	// dy > 0
	private void caso4(double dx, double dy, double x, double y, Color cor, Graphics g) {
		double erro = 0;
		for (int i = 0; i < Math.abs(dy - 1); i++) {
			if (erro <= 0) {
				x--;
				y++;
				desenharPonto(x, y, cor, g);
				erro += dx + dy;
			} else {
				y++;
				desenharPonto(x, y, cor, g);
				erro += dx;
			}
		}
	}

	// *************************************************************************************
	// DESENHAR PONTO
	private void desenharPonto(double x1, double y1, Color cor, Graphics g) {
		g.setColor(cor);
		g.drawLine((int) x1, (int) y1, (int) x1, (int) y1);
	}

	// *************************************************************************************
	// DESENHAR 'X' NO INÍCIO E FIM DA RETA
	// tipo = 1 -> método clássico
	// tipo = 2 -> método DDA Simples
	// tipo = 3 -> método DDA Inteiro
	public void desenharX(Point ponto, Color cor, Graphics g, int tipo) {
		g.setColor(cor);

		if (tipo == 1) {
			desenharRetaMetodoClassico(new Reta(new Point(ponto.x, ponto.y), new Point(ponto.x + 5, ponto.y - 5)), cor,
					g);
			desenharRetaMetodoClassico(new Reta(new Point(ponto.x, ponto.y), new Point(ponto.x - 5, ponto.y + 5)), cor,
					g);
			desenharRetaMetodoClassico(new Reta(new Point(ponto.x - 5, ponto.y - 5), new Point(ponto.x, ponto.y)), cor,
					g);
			desenharRetaMetodoClassico(new Reta(new Point(ponto.x + 5, ponto.y + 5), new Point(ponto.x, ponto.y)), cor,
					g);
		} else if (tipo == 2) {
			desenharRetaDDASimples(new Reta(new Point(ponto.x, ponto.y), new Point(ponto.x + 5, ponto.y - 5)), cor, g);
			desenharRetaDDASimples(new Reta(new Point(ponto.x, ponto.y), new Point(ponto.x - 5, ponto.y + 5)), cor, g);
			desenharRetaDDASimples(new Reta(new Point(ponto.x - 5, ponto.y - 5), new Point(ponto.x, ponto.y)), cor, g);
			desenharRetaDDASimples(new Reta(new Point(ponto.x + 5, ponto.y + 5), new Point(ponto.x, ponto.y)), cor, g);
		} else {
			desenharRetaDDAInteiro(new Reta(new Point(ponto.x, ponto.y), new Point(ponto.x + 5, ponto.y - 5)), cor, g);
			desenharRetaDDAInteiro(new Reta(new Point(ponto.x, ponto.y), new Point(ponto.x - 5, ponto.y + 5)), cor, g);
			desenharRetaDDAInteiro(new Reta(new Point(ponto.x - 5, ponto.y - 5), new Point(ponto.x, ponto.y)), cor, g);
			desenharRetaDDAInteiro(new Reta(new Point(ponto.x + 5, ponto.y + 5), new Point(ponto.x, ponto.y)), cor, g);
		}
	}
	// *************************************************************************************
}
