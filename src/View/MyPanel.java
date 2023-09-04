package View;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private ImageIcon icon;

    public MyPanel() {
    }

    public MyPanel(ImageIcon icon) {
        this.icon = icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        repaint(); // Certifique-se de que o componente seja redesenhado após definir a nova imagem.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Certifique-se de chamar o método pai.

        if (icon != null) {
            // Desenhe a imagem se ela estiver definida.
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
