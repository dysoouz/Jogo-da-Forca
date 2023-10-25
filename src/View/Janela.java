package View;

import Model.PNteclado;
import Model.PalavrasCatalogadas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Random;

public class Janela extends JFrame implements ActionListener, KeyListener {
    private final int rows = 40, cols = 20, size = 400;

    private int i = 5;
    private JPanel pnOrganizador,pn1, pn2, pn3, pnLetrasUsadas;
    private PNteclado pNteclado;
    private JTextField tfchutarLetra;
    private JLabel lbTentativas, lbletras, tfnomeEscondido;
    private int indicaFundo;
    private PalavrasCatalogadas palavrasCatalogadas;
    private Forca forca;
    private JButton btAnimais, btProfissao, btpdc, btVerificar;
    private ImageIcon background;
    private Font font;
    private String stringEscondida;
    public Janela(){
        this.setLayout(new GridLayout(4,1));

        font = new Font("Arial", Font.BOLD, 24);

        tfnomeEscondido = new JLabel( "-^-^-^- ESCOLHA O TIPO FORCA -^-^-^-");
        tfnomeEscondido.setForeground(Color.WHITE);
        tfnomeEscondido.setFont(font);
        tfnomeEscondido.setOpaque(true);
        tfnomeEscondido.setBackground(Color.DARK_GRAY);
        tfnomeEscondido.setHorizontalAlignment(SwingConstants.CENTER);
        tfchutarLetra = new JTextField(1);
        tfchutarLetra.setForeground(Color.WHITE);
        tfchutarLetra.setFont(font);
        tfchutarLetra.setOpaque(true);
        tfchutarLetra.setBackground(Color.DARK_GRAY);

        btAnimais = new JButton("Animais");
        btProfissao = new JButton("Profissões");
        btpdc = new JButton("Partes do Corpo");
        btVerificar = new JButton("Verificar");

        lbTentativas = new JLabel("Letra usadas:");
        lbletras = new JLabel();


        forca = new Forca();

        pn1 = new JPanel();
        pn1.setLayout(new FlowLayout());
        pn1.setPreferredSize(new Dimension(600,100));
        pn1.add(btAnimais);
        pn1.add(btProfissao);
        pn1.add(btpdc);

        pn2 = new JPanel();
        pn2.setLayout(new FlowLayout());
        pn2.setPreferredSize(new Dimension(600,100));
        pn2.add(tfnomeEscondido);

        pn3 = new JPanel();
        pn3.setLayout(new FlowLayout());
        pn3.setPreferredSize(new Dimension(600,100));
        pn3.add(tfchutarLetra);
        pn3.add(btVerificar);


        pnOrganizador = new JPanel();
        pnOrganizador.add(pn1);
        pnOrganizador.add(pn2);
        pnOrganizador.add(pn3);
        pnOrganizador.setLayout(new GridLayout(3,1));

        pnLetrasUsadas = new JPanel();
        pnLetrasUsadas.add(lbTentativas);
        pnLetrasUsadas.add(lbletras);

        pNteclado =new PNteclado(this);

        this.add(forca);
        this.add(pnOrganizador);
        this.add(pNteclado);
        this.add(pnLetrasUsadas);

        btAnimais.addActionListener(this);
        btProfissao.addActionListener(this);
        btpdc.addActionListener(this);
        btVerificar.addActionListener(this);


        this.setTitle("[ JOGO DA FORCA ]");
        this.setExtendedState(2);
        this.setVisible(true);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static String removerAcentos(String string) {
        String semAcentos = Normalizer.normalize(string, Normalizer.Form.NFD);
        semAcentos = semAcentos.replaceAll("[^\\p{ASCII}]", "");
        return semAcentos;
    }


    //Esconde o conteudo da string
    public String esconderNome(String string){
        char[] caracter = string.toCharArray();
        for (int i = 0; i < caracter.length; i++) {
            if(caracter[i] != ' ') {
                caracter[i] = '?';
            }
        }

        return new String(caracter);
    }

    //Verifica se a letra escolhida faz parte da Forca escolhida
    public String Verificar(String string, String string2) {
        // Use a função removerAcentos para tratar a palavra da forca
        String palavraForca = removerAcentos(palavrasCatalogadas.getPalavraSelecionada());
        char[] charForcaComAcentos = palavraForca.toCharArray();
        char[] charEscondido = string.toCharArray();
        char[] charLetra = string2.toCharArray();

        // Tratamento de erro: Converta a letra inserida para minúscula
        charLetra[0] = Character.toLowerCase(charLetra[0]);

        boolean letraCorreta = false;

        // Verifique se a letra faz parte da palavra da forca (com ou sem acentos)
        for (int i = 0; i < charForcaComAcentos.length; i++) {
            if (charForcaComAcentos[i] == charLetra[0]) {
                charEscondido[i] = palavraForca.charAt(i);
                letraCorreta = true;
            }
        }

        // Se a letra estiver incorreta, atualize a imagem da forca
        if (!letraCorreta) {
            indicaFundo++;
            if (indicaFundo < 7) {
                forca.atualizarImagem(indicaFundo);
            }
        }
        return new String(charEscondido);
    }

    public void teclaClicada(String tecla) {
        // Este método é chamado quando uma tecla é clicada no teclado virtual
        tfchutarLetra.setText(tecla);
    }
    public void finalizar(String string){
        String vencedor = "Parabéns, você venceu!\n\n Resposta correta -> [ "+palavrasCatalogadas.getPalavraSelecionada()+" ]";
        String perdedor = "Perdeu otário, muito burro kkkkkkkkkkkkkk \n\n Resposta correta -> [ "+palavrasCatalogadas.getPalavraSelecionada()+" ]";

        if (indicaFundo == 6){
            JOptionPane.showMessageDialog(null,perdedor);
            tfnomeEscondido.setText("");
            tfchutarLetra.setText("");
            indicaFundo = 0;
            forca.atualizarImagem(indicaFundo);
        }
        boolean venceu = true;
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) == '?'){
                venceu = false;
            }
        }
        if (venceu == true){
            JOptionPane.showMessageDialog(null,vencedor);
            tfnomeEscondido.setText("");
            tfchutarLetra.setText("");
            indicaFundo = 0;
            forca.atualizarImagem(indicaFundo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        if(e.getSource() == btAnimais){
            tfnomeEscondido.setText("");
            int i = random.nextInt(51);
            palavrasCatalogadas = new PalavrasCatalogadas(i,0);

            tfnomeEscondido.setText(esconderNome(palavrasCatalogadas.getPalavraSelecionada()));
            lbletras.setText("");
        } else if (e.getSource() == btProfissao) {
            tfnomeEscondido.setText("");
            int i = random.nextInt(21);
            palavrasCatalogadas = new PalavrasCatalogadas(i,1);
            tfnomeEscondido.setText(esconderNome(palavrasCatalogadas.getPalavraSelecionada()));
            lbletras.setText("");
        } else if (e.getSource() == btpdc) {
            tfnomeEscondido.setText("");
            int i = random.nextInt(28);
            palavrasCatalogadas = new PalavrasCatalogadas(i,2);
            tfnomeEscondido.setText(esconderNome(palavrasCatalogadas.getPalavraSelecionada()));
            lbletras.setText("");
        }else if (e.getSource() == btVerificar) {
            tfnomeEscondido.setText(Verificar(tfnomeEscondido.getText(),tfchutarLetra.getText()));
            lbletras.setText(lbletras.getText()+tfchutarLetra.getText()+" | ");
            tfchutarLetra.setText("");
            finalizar(tfnomeEscondido.getText());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
