package Model;

import View.Janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PNteclado extends JPanel implements ActionListener {
    private String[] teclas = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M"
    };
    private JButton[] botoes = new JButton[teclas.length];
    private JPanel pn1, pn2, pn3;
    private Janela janela;  // Referência à janela principal

    public PNteclado(Janela janela) {
        this.janela = janela;
        this.setLayout(new GridLayout(4, 1));

        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();


        // Crie botões para cada letra do teclado
        for (int i = 0; i <= 9; i++) {
            botoes[i] = new JButton(teclas[i]);
            botoes[i].addActionListener(this);
            pn1.add(botoes[i]);
        }
        for (int i = 10; i <= 18; i++) {
            botoes[i] = new JButton(teclas[i]);
            botoes[i].addActionListener(this);
            pn2.add(botoes[i]);
        }
        for (int i = 19; i <= 25; i++) {
            botoes[i] = new JButton(teclas[i]);
            botoes[i].addActionListener(this);
            pn3.add(botoes[i]);
        }

        this.add(pn1);
        this.add(pn2);
        this.add(pn3);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < teclas.length; i++) {
            if (e.getSource() == botoes[i]) {
                // Notifica a janela principal com a tecla clicada
                janela.teclaClicada(teclas[i]);
                break;
            }
        }
    }
}
