package sample;

import java.util.LinkedList;

public class MRU {


    private LinkedList<String> frames;
    private int acertos, faltas, qtd_frames;

    //construtor da classe
    public MRU(int frames) {
        this.acertos = 0;
        this.faltas = 0;
        this.frames = new LinkedList();
        this.qtd_frames = frames;
    }

    //para pegar os acertos
    public int getAcertos() { return acertos; }

    //para pegar as faltas
    public int getFaltas() { return faltas;  }

    public void MRU(String arrayEntrada) {

        //o controler nós retiramos os "-" aqui nós retiramos os "W" e "R" para fazer as comparações
        String[] arrayAux = arrayEntrada.split("W"); //retira os W
        String[] arrayAux2 = arrayAux[0].split("R"); //retira os R
        String pageNumber = arrayAux2[0]; //pega somente as primeiros posições

        //indexOf pega a posição do elemento no frame
        int tmp = frames.indexOf(pageNumber);
        //para os casos de ERRO
        if (tmp == -1) {
            //verifica se o frame está cheio
            if (frames.size() < qtd_frames) {
                //Se tiver espaço adiciona no final do vetor
                frames.add(pageNumber);
            } else {
                //caso esteja cheio remove o primeiro e adiciona o valor corrente no final
                frames.remove(0);
                frames.add(pageNumber);
            }
            faltas++;
        } else {
            //para os casos de ACERTO
            //caso ele já esteja no frame ele remove o valor na posição e adiciona no final do vetor
            frames.remove(tmp);
            frames.add(pageNumber);
            acertos++;
        }
    }
}