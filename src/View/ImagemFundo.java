package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

// Classe personalizada para exibir um GIF animado em um JLabel
public class ImagemFundo extends JLabel {
    public ImagemFundo(ImageIcon imageIcon) {
        super(imageIcon);
        ((ImageIcon) getIcon()).setImageObserver(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
    }
}

