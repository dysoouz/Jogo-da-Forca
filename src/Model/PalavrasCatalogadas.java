package Model;

import java.io.*;

public class PalavrasCatalogadas {
    private String palavraSelecionada = null;

    public PalavrasCatalogadas() {}

    public PalavrasCatalogadas(int numPalavra, int tipo) {
        try {
            String resourceName = "/Resource/";
            switch (tipo) {
                case 0:
                    resourceName += "Animais.txt";
                    break;
                case 1:
                    resourceName += "Profissao.txt";
                    break;
                case 2:
                    resourceName += "Corpo_Humano.txt";
                    break;
                default:
                    break;
            }

            InputStream inputStream = getClass().getResourceAsStream(resourceName);

            if (inputStream != null) {
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                int numeroLinha = 0;

                while ((palavraSelecionada = bufferedReader.readLine()) != null) {
                    numeroLinha++;
                    if (numeroLinha == numPalavra) {
                        break;
                    }
                }

                bufferedReader.close();
            } else {
                System.err.println("Recurso não encontrado: " + resourceName);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public String getPalavraSelecionada() {
        return palavraSelecionada;
    }

    @Override
    public String toString() {
        return palavraSelecionada;
    }
}
