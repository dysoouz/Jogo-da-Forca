package View;


import javax.swing.*;
import java.awt.*;


public class Forca extends JPanel {
    private String fundo = "/Resource/fundo.png";
    private String fundo1 = "/Resource/fundo1.png";
    private String fundo2 = "/Resource/fundo2.png";
    private String fundo3 = "/Resource/fundo3.png";
    private String fundo4 = "/Resource/fundo4.png";
    private String fundo5 = "/Resource/fundo5.png";
    private String fundo6 = "/Resource/fundo6.png";

    private MyPanel myPanel;
    private  ImageIcon icon, icon1, icon2, icon3, icon4, icon5, icon6;

    public Forca(){
        this.setLayout(new FlowLayout());

        icon = new ImageIcon(getClass().getResource(fundo));
        icon1 = new ImageIcon(getClass().getResource(fundo1));
        icon2 = new ImageIcon(getClass().getResource(fundo2));
        icon3 = new ImageIcon(getClass().getResource(fundo3));
        icon4 = new ImageIcon(getClass().getResource(fundo4));
        icon5 = new ImageIcon(getClass().getResource(fundo5));
        icon6 = new ImageIcon(getClass().getResource(fundo6));

        myPanel = new MyPanel(icon);
        myPanel.setPreferredSize(new Dimension(200, 140));

        this.add(myPanel);

        this.setVisible(true);
        this.setSize(800,300);
    }
    public void atualizarImagem(int tipo) {
        ImageIcon novaImagem = getIcon(tipo);
        myPanel.setIcon(novaImagem);
    }
    public ImageIcon getIcon(int tipo){
        switch (tipo){
            case 1:
                return icon1;
            case 2:
                return icon2;
            case 3:
                return icon3;
            case 4:
                return icon4;
            case 5:
                return icon5;
            case 6:
                return icon6;
            default:
                return icon;
        }
    }
}
