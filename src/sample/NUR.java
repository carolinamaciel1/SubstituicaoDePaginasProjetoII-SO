package sample;

import java.util.LinkedList;


public class NUR {
    private LinkedList<String> frames;
    private LinkedList<Integer> bitR;
    private LinkedList<Integer> bitM;
    private LinkedList<Integer> classes;
    private int acertos, faltas, qtd_frames, zeresima;

    public NUR(int frames, int zeresima){
        this.acertos = 0;
        this.faltas = 0;
        this.frames = new LinkedList();
        this.bitR = new LinkedList();
        this.bitM = new LinkedList();
        this.classes = new LinkedList<>();
        this.qtd_frames = frames;
        this.zeresima = zeresima;
    }

    public int getAcertos() { return acertos; }

    public int getFaltas() { return faltas; }

    public void zerarBitR(){
        int tam = bitR.size();
        bitR.clear();
        for(int i = 0; i<tam; i++){
            bitR.add(0);
        }
    }

    public int testeClasse(int bitR, int bitM){
        if(bitR == 0){
            if(bitM == 0){
                return 0;
            }
            else{
                return 1;
            }
        }
        else {
            if(bitM == 0){
                return 2;
            }
            else{
                return 3;
            }
        }
    }

    public void NUR(String[] arrayEntrada){
        int i;
        int time = zeresima;

        String[] arrayAux = new String[arrayEntrada.length];
        String[] arrayAux2 = new String[arrayEntrada.length];

        for (i = 0; i < arrayEntrada.length; i++) {
            arrayAux[i] = arrayEntrada[i].substring (0, arrayEntrada[i].length() - 1);
        }

        for (i = 0; i < arrayEntrada.length; i++) {
            arrayAux2[i] = arrayEntrada[i].substring (arrayEntrada[i].length()-1);
        }

        for (i = 0; i<arrayAux.length; i++){
            time--;
            if(!frames.contains(arrayAux[i])){
                this.faltas++;
                if(frames.size() < qtd_frames){
                    frames.add(arrayAux[i]);
                    bitR.add(1);
                    if(arrayAux2[i].contains("W")){
                        bitM.add(1);
                    }
                    else {
                        bitM.add(0);
                    }
                }

                else {
                    int j;
                    classes.clear();
                    for(j=0; j < frames.size(); j++){
                        classes.add(testeClasse(bitR.get(j),bitM.get(j)));
                    }

                    int aux = 4;
                    for(j = 0; j < classes.size();j++){
                        if(classes.get(j) < aux){
                            aux = classes.get(j);
                        }
                    }

                    int remove_index = classes.indexOf(aux);

                    frames.remove(remove_index);
                    bitR.remove(remove_index);
                    bitM.remove(remove_index);

                    frames.add(remove_index,arrayAux[i]);
                    bitR.add(remove_index,1);

                    if(arrayAux2[i].contains("W")){
                        bitM.add(remove_index,1);
                    }
                    else {
                        bitM.add(remove_index,0);
                    }
                }
            }

            else {
                this.acertos++;
                bitR.remove(frames.indexOf(arrayAux[i]));
                bitR.add(frames.indexOf(arrayAux[i]),1);
                if(bitM.get(frames.indexOf(arrayAux[i])) == 0){
                    if(arrayAux2[i].contains("W")){
                        bitM.remove(frames.indexOf(arrayAux[i]));
                        bitM.add(frames.indexOf(arrayAux[i]), 1);
                    }
                }
            }

            if(time == 0){
                zerarBitR();
                time = zeresima;
            }
        }
    }
}