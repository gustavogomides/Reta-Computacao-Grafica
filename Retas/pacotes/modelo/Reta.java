package modelo;

import java.awt.*;
import java.util.ArrayList;

public class Reta {

	private Point pontoInicial;
	private Point pontoFinal;
	public ArrayList<Point> pontos = new ArrayList<Point>();

	// ****************************************************************************************
	public Reta(Point pInicial, Point pFinal) {
		pontoInicial = pInicial;
		pontoFinal = pFinal;
	}

	// ****************************************************************************************
	public Point getPontoInicial() {
		return (pontoInicial);
	}

	// ****************************************************************************************
	public Point getPontoFinal() {
		return (pontoFinal);
	}

	// ****************************************************************************************
	public void addPonto(Point P) {
		this.pontos.add(P);
	}

	// ****************************************************************************************
	public int procuraPonto(Point P) {
		for (Point ponto : this.pontos) {
			if (ponto.equals(P))
				return 1;

		}
		return 0;
	}

	// ****************************************************************************************
}
