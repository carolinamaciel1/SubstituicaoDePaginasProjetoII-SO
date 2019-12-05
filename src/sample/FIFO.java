package sample;
import sample.AlgoritmoDeSubstituicao;

import java.util.LinkedList;

class FIFO extends AlgoritmoDeSubstituicao {
    private static int INSERCAO = 0;

    public FIFO(int frames) {
        super(frames);
        this.quadros = new LinkedList();
    }

    @Override
    public void inserir(String stringArray) {
        String[] arrayAux = stringArray.split("W");
        String[] arrayAux2 = arrayAux[0].split("R");
        String pageNumber = arrayAux2[0];

        // antes de inserir, checar se a pagina ja esta na lista
        if (!quadros.contains(pageNumber)) {
            erros++;
            // se a quantidade de paginas na memoria for menor que o numero de quadros ou seja, ainda ha espaco
            if (quadros.size() < numeroDeQuadros) {
                quadros.add(pageNumber);
            } else {
                quadros.remove(INSERCAO);
                quadros.add(INSERCAO, pageNumber);
                INSERCAO++;
                if (INSERCAO == numeroDeQuadros) {
                    INSERCAO = 0;
                }
            }
        }
        else {
            acertos++;
        }
    }
}
