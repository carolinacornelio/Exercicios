package livros;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class calculadoraLivros extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField espaco1,espaco2,espaco3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadoraLivros frame = new calculadoraLivros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public calculadoraLivros() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 580);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel bemVindo = new JLabel("Bem vindo!");
        bemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        bemVindo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
        contentPane.add(bemVindo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(216, 191, 216));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel pergunta1 = new JLabel("Quantos páginas o livro possui?");
        pergunta1.setHorizontalAlignment(SwingConstants.CENTER);
        pergunta1.setFont(new Font("Tahoma", Font.ITALIC, 20));
        pergunta1.setBounds(0, 36, 856, 65);
        panel.add(pergunta1);

        espaco1 = new JTextField();
        espaco1.setBounds(183, 90, 500, 40);
        panel.add(espaco1);
        espaco1.setColumns(10);

        JLabel pergunta2 = new JLabel("Em quantos dias você deseja ler?");
        pergunta2.setHorizontalAlignment(SwingConstants.CENTER);
        pergunta2.setFont(new Font("Tahoma", Font.ITALIC, 20));
        pergunta2.setBounds(0, 136, 856, 65);
        panel.add(pergunta2);

        espaco2 = new JTextField();
        espaco2.setBounds(183, 190, 500, 40);
        panel.add(espaco2);
        espaco2.setColumns(10);

        JLabel pergunta3 = new JLabel("Que dia você vai começar?");
        pergunta3.setHorizontalAlignment(SwingConstants.CENTER);
        pergunta3.setFont(new Font("Tahoma", Font.ITALIC, 20));
        pergunta3.setBounds(0, 236, 856, 65);
        panel.add(pergunta3);

        espaco3 = new JTextField();
        espaco3.setBounds(183, 290, 500, 40);
        panel.add(espaco3);
        espaco3.setColumns(10);
        
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		int pags = Integer.parseInt(espaco1.getText());
                int dias = Integer.parseInt(espaco2.getText());
                int comeco = Integer.parseInt(espaco3.getText());

                double pagsDia = pags / dias;
                int fim = comeco + dias;

                LocalDate dataAtual = LocalDate.now();
                LocalDate dataInicio = LocalDate.of(dataAtual.getYear(), dataAtual.getMonthValue(), comeco);
                LocalDate dataFim = dataInicio.plusDays(dias);

                int paginasPorDia = pags / dias;
                int paginaAtual = 0;

                StringBuilder tabela = new StringBuilder();
				tabela.append("\n-----TABELA DE LEITURA-----");
				tabela.append("\nData de início: " + dataInicio.getDayOfMonth() + "/" + dataInicio.getMonthValue() + "/" + dataInicio.getYear());
				tabela.append("\nData prevista para o final: " + dataFim.getDayOfMonth() + "/" + dataFim.getMonthValue() + "/" + dataFim.getYear());
				tabela.append("\nTempo de leitura: " + dias + " dias");
				tabela.append("\nQuantas páginas devem ser lidas por dia: " + pagsDia + " páginas");
				JOptionPane.showMessageDialog(null, tabela.toString());
				
				System.out.println("Até que página deve ser lido em cada dia? \n");
                for (LocalDate data = dataInicio; data.isBefore(dataFim.plusDays(1)); data = data.plusDays(1)) {
                    paginaAtual += paginasPorDia;
                    if (paginaAtual > pags) {
                        paginaAtual = pags;
                    }
                    System.out.printf("Dia %d/%d/%d: ler até a página %d\n", data.getDayOfMonth(), data.getMonthValue(), data.getYear(), paginaAtual);
                }
        	}
        });
        btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCalcular.setBounds(325, 356, 200, 50);
        panel.add(btnCalcular);
	}
}