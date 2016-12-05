package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controle.ControlarAplicativo;
import modelo.Reta;

public class MontarPainelInicial implements MouseListener, MouseMotionListener {

	private JFrame baseFrame;
	private JPanel basePanel;
	private JPanel outputPanel;

	private JButton btPontos;
	private JButton btEnd;
	private JButton btHorizontal;
	private JButton btVertical;
	private JButton btCruzamentos;
	private JButton btPerpendiculares;
	private JButton btDiagonais;
	private JButton btInclinadas;
	private JButton btLimpar;
	private JButton btOcultar;
	public JButton btMetodoClassico;
	public JButton btMetodoDDASimples;
	public JButton btMetodoDDAInteiro;
	private JButton btMostrarRetas;

	public JLabel labelVisor;

	public JToggleButton tbSelecionar;

	private Graphics desenho;

	private ControlarAplicativo controlePrograma;

	public ArrayList<Reta> retas = new ArrayList<>();
	public ArrayList<Reta> arrayX = new ArrayList<>();

	public boolean selectReta = false;
	public boolean desenharClassico = false;
	public boolean desenharDDASimples = false;
	public boolean desenharDDAInteiro = false;
	public boolean abrir = false;

	private int cliques;
	private Point pontoPrimeiroClique;

	// ******************************************************************************************************************
	public MontarPainelInicial(ControlarAplicativo controlePrograma) {
		this.controlePrograma = controlePrograma;
		JPanel buttonPanel;
		JPanel titlePanel;

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE
															// ACTUAL MONITOR
															// SIZE
		baseFrame.setUndecorated(true); // TURN OFF ALL THE PANEL BORDERS

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(0, 50));
		titlePanel.setBackground(Color.BLUE);

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 70));
		buttonPanel.setBackground(Color.BLUE);

		// ADICIONAR BOTÕES
		// Método Clássico
		btMetodoClassico = addAButton("Desenhar Retas Método Clássico", "metodoClassico", buttonPanel);
		btMetodoClassico.addActionListener(controlePrograma);
		btMetodoClassico.setBackground(Color.GRAY);
		btMetodoClassico.setForeground(Color.WHITE);

		// Método DDA Simples
		btMetodoDDASimples = addAButton("Desenhar Retas Método DDA Simples", "metodoDDASimples", buttonPanel);
		btMetodoDDASimples.addActionListener(controlePrograma);
		btMetodoDDASimples.setBackground(Color.GRAY);
		btMetodoDDASimples.setForeground(Color.WHITE);

		// Método DDA Inteiro
		btMetodoDDAInteiro = addAButton("Desenhar Retas Método DDA Inteiro", "metodoDDAInteiro", buttonPanel);
		btMetodoDDAInteiro.addActionListener(controlePrograma);
		btMetodoDDAInteiro.setBackground(Color.GRAY);
		btMetodoDDAInteiro.setForeground(Color.WHITE);

		// Mostrar Pontos
		btPontos = addAButton("Mostrar Pontos", "mostrarPontos", buttonPanel);
		btPontos.addActionListener(controlePrograma);
		btPontos.setBackground(Color.GRAY);
		btPontos.setForeground(Color.WHITE);

		// Mostrar Retas
		btMostrarRetas = addAButton("Mostrar Retas", "mostrarRetas", buttonPanel);
		btMostrarRetas.addActionListener(controlePrograma);
		btMostrarRetas.setBackground(Color.GRAY);
		btMostrarRetas.setForeground(Color.WHITE);

		// Retas Horizontais
		btHorizontal = addAButton("Retas Horizontais", "botaoHorizontal", buttonPanel);
		btHorizontal.addActionListener(controlePrograma);
		btHorizontal.setBackground(Color.GRAY);
		btHorizontal.setForeground(Color.WHITE);

		// Retas Verticais
		btVertical = addAButton("Retas Verticais", "botaoVertical", buttonPanel);
		btVertical.addActionListener(controlePrograma);
		btVertical.setBackground(Color.GRAY);
		btVertical.setForeground(Color.WHITE);

		// Retas Diagonais
		btDiagonais = addAButton("Retas Diagonais", "botaoDiagonal", buttonPanel);
		btDiagonais.addActionListener(controlePrograma);
		btDiagonais.setBackground(Color.GRAY);
		btDiagonais.setForeground(Color.WHITE);

		// Retas Inclinadas
		btInclinadas = addAButton("Retas Inclinadas", "botaoInclinada", buttonPanel);
		btInclinadas.addActionListener(controlePrograma);
		btInclinadas.setBackground(Color.GRAY);
		btInclinadas.setForeground(Color.WHITE);

		// Cruzamento
		btCruzamentos = addAButton("Cruzamentos", "botaoCruzamento", buttonPanel);
		btCruzamentos.addActionListener(controlePrograma);
		btCruzamentos.setBackground(Color.GRAY);
		btCruzamentos.setForeground(Color.WHITE);

		// Perpendicular
		btPerpendiculares = addAButton("Perpendiculares", "botaoPerpendicular", buttonPanel);
		btPerpendiculares.addActionListener(controlePrograma);
		btPerpendiculares.setBackground(Color.GRAY);
		btPerpendiculares.setForeground(Color.WHITE);

		// Limpar Tela
		btLimpar = addAButton("Limpar Tela", "botaoLimpar", buttonPanel);
		btLimpar.addActionListener(controlePrograma);
		btLimpar.setBackground(Color.GRAY);
		btLimpar.setForeground(Color.WHITE);

		// Ocultar Desenho
		btOcultar = addAButton("Ocultar Desenho", "botaoOcultar", buttonPanel);
		btOcultar.addActionListener(controlePrograma);
		btOcultar.setBackground(Color.GRAY);
		btOcultar.setForeground(Color.WHITE);

		// Sair do Programa
		btEnd = addAButton("Sair", "botaoFim", buttonPanel);
		btEnd.addActionListener(controlePrograma);
		btEnd.setBackground(Color.GRAY);
		btEnd.setForeground(Color.WHITE);

		// OUVINTES DO MOUSE
		outputPanel.addMouseListener(this);
		outputPanel.addMouseMotionListener(this);

		// VISIBLE PANELS
		baseFrame.add(basePanel);
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		// LABEL VISOR
		labelVisor = new JLabel("");
		labelVisor.setHorizontalAlignment(SwingConstants.LEFT);
		outputPanel.add(labelVisor, BorderLayout.SOUTH);
		labelVisor.setBackground(Color.WHITE);
		labelVisor.setForeground(Color.BLACK);
		labelVisor.setBorder(new EmptyBorder(5, 5, 5, 5));

		baseFrame.setVisible(true);
	}

	// ******************************************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA
	// return JButton -> retorna o botão
	private JButton addAButton(String textoBotao, String textoControle, Container container) {
		JButton botao;

		botao = new JButton(textoBotao);
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(botao);

		botao.setActionCommand(textoControle);

		return (botao);
	}

	// ******************************************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO

	public void showPanel() {
		basePanel.setVisible(true);
	}

	// ******************************************************************************************************************
	public void mouseClicked(MouseEvent evento) {
		controlePrograma.cruzamentos = false;
		controlePrograma.perpendiculares = false;
		int X = evento.getX();
		int Y = evento.getY();
		Point P = new Point(X, Y);

		if (abrir || desenharClassico || desenharDDASimples || desenharDDAInteiro) {
			controlePrograma.cruzamentos = false;
			controlePrograma.perpendiculares = false;

			// CONTROLE DE CLIQUE
			if (this.cliques == 0) { // Primeiro clique
				this.cliques = 1;
				this.pontoPrimeiroClique = P;
				if (desenharClassico) {
					controlePrograma.desenharX(P, Color.RED, desenho, 1);
				} else if (desenharDDASimples) {
					controlePrograma.desenharX(P, Color.RED, desenho, 2);
				} else {
					controlePrograma.desenharX(P, Color.RED, desenho, 3);
				}

			} else { // Segundo clique
				controlePrograma.cruzamentos = false;
				controlePrograma.perpendiculares = false;
				Reta reta = new Reta(this.pontoPrimeiroClique, P);
				if (desenharClassico) {
					controlePrograma.desenharX(P, Color.RED, desenho, 1);
				} else if (desenharDDASimples) {
					controlePrograma.desenharX(P, Color.RED, desenho, 2);
				} else {
					controlePrograma.desenharX(P, Color.RED, desenho, 3);
				}
				this.cliques = 0;

				retas.add(reta);

				if (desenharClassico) {
					controlePrograma.mostrarRetas(reta, Color.black, desenho, 1);
				} else if (desenharDDASimples) {
					controlePrograma.mostrarRetas(reta, Color.black, desenho, 2);
				} else {
					controlePrograma.mostrarRetas(reta, Color.black, desenho, 3);
				}
			}
		}
	}

	// ******************************************************************************************************************
	public void mouseEntered(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseMoved(MouseEvent e) {
		Point P = new Point(e.getX(), e.getY());

		this.labelVisor.setText("Ponto: ( " + (int) P.getX() + " ; " + (int) P.getY() + " )");
		Point temReta = e.getPoint();

		// SE CLICOU NO BOTÃO 'Cruzamento'
		if (controlePrograma.cruzamentos) {
			controlePrograma.localizaCruzamentos(temReta, desenho, controlePrograma.escolhaCruzamento);
		}

		// PINTAR OS 'X' DAS RETAS DO CRUZAMENTO COM CINZA
		if (!controlePrograma.cruzamentos) {
			if (controlePrograma.arrayXCruzamentos.size() > 0) {
				Color cinza = new Color(238, 238, 238);
				for (Reta r : controlePrograma.arrayXCruzamentos) {
					controlePrograma.desenharX(r.getPontoInicial(), cinza, desenho, 3);
					controlePrograma.desenharX(r.getPontoInicial(), cinza, desenho, 3);
				}
				controlePrograma.arrayXCruzamentos.clear();
			}
		}

		// SE CLICO NO BOTÃO 'Perpendicular'
		if (controlePrograma.perpendiculares) {
			controlePrograma.localizaPerpendiculares(temReta, desenho, controlePrograma.escolhaPerpendicular);
		}
	}

	// ******************************************************************************************************************
	public void mouseExited(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mousePressed(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseReleased(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseDragged(MouseEvent e) {
	}

	// ******************************************************************************************************************
	public Graphics iniciarGraphics() {
		desenho = outputPanel.getGraphics();
		return (desenho);
	}

	// ******************************************************************************************************************
	// OCULTAR O DESENHO
	public void ocultarDesenho() {
		Color cinza = new Color(238, 238, 238);
		desenho.clearRect(0, 0, outputPanel.getWidth(), outputPanel.getHeight());
		desenho.setColor(cinza);
	}

	// ******************************************************************************************************************
	// MENSAGEM INICIAL
	public void mensagemInicial() {
		bloquearBotoes(false);
		JOptionPane.showMessageDialog(null,
				"Você pode desenhar suas próprias retas utilizando o método clássico e/ou DDA Simples e/ou DDA Inteiro!",
				"Bem-vindo!", JOptionPane.INFORMATION_MESSAGE);
	}

	// ******************************************************************************************************************
	// BLOQUEAR OS BOTÕES
	public void bloquearBotoes(boolean estado) {
		btMostrarRetas.setEnabled(estado);
		btPontos.setEnabled(estado);
		btHorizontal.setEnabled(estado);
		btVertical.setEnabled(estado);
		btCruzamentos.setEnabled(estado);
		btPerpendiculares.setEnabled(estado);
		btDiagonais.setEnabled(estado);
		btInclinadas.setEnabled(estado);
		btLimpar.setEnabled(estado);
		btOcultar.setEnabled(estado);
	}
}
