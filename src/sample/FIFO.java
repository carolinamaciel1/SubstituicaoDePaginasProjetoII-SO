package sample;

import java.util.LinkedList;

public class FIFO {
    private int INSERCAO = 0;
    private LinkedList<String> frames;
    private int acertos, faltas, qtd_frames;

    //construtor
    public FIFO(int frames) {
        this.acertos = 0;
        this.faltas = 0;
        this.frames = new LinkedList();
        this.qtd_frames = frames;
    }

    public int getAcertos() { return acertos; }

    public int getFaltas() { return faltas;  }

    public void fifo(String arrayEntrada) {
        //retira os W e  R do  vetor para poder tratar só os valores
        String[] arrayAux = arrayEntrada.split("W"); //remove o W
        String[] arrayAux2 = arrayAux[0].split("R"); //remove o R
        String pageNumber = arrayAux2[0]; //pega os valores

        // antes de inserir, checar se a pagina ja esta na lista
        if (!frames.contains(pageNumber)) {
            faltas++;
            // se a quantidade de paginas na memoria for menor que o numero de quadros ou seja, ainda ha espaco
            if (frames.size() < qtd_frames) {
                frames.add(pageNumber);
            } else {
                //caso não seja ele remove da página e adiciona o valor no final
                frames.remove(INSERCAO);
                frames.add(INSERCAO, pageNumber);
                //ponteiro para o array de frames, serve para controlar onde será adicionado (index), ele deve ser menor que a quantidade de frames
                INSERCAO++;
                if (INSERCAO == qtd_frames) {
                    //quando chega ao final ele volta ao início para continuar percorrendo os frames
                    INSERCAO = 0;
                }
            }
        }
        else {
            acertos++;
        }

    }
}