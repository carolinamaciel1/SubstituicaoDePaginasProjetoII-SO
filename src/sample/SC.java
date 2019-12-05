package sample;

import java.util.LinkedList;

public class SC {
    private LinkedList<String> frames;
    private LinkedList<Integer> bitR;
    private int acertos, faltas, qtd_frames, zeresima;


    public SC(int frames, int zeresima){
        this.acertos = 0;
        this.faltas = 0;
        this.qtd_frames = frames;
        this.frames = new LinkedList<>();
        this.bitR = new LinkedList<>();
        this.zeresima = zeresima;
    }

    public int getAcertos() { return acertos; }

    public int getFaltas() { return faltas;  }

    public void zerarBitR(){
        bitR.clear();
        for(int i = 0; i<frames.size(); i++){
            bitR.add(0);
        }
    }

    public void secondChance(String [] arrayEntrada){
        int time = zeresima;
        String[] arrayAux = new String[arrayEntrada.length];
        int i;

        for (i = 0; i < arrayEntrada.length; i++) {
            arrayAux[i] = arrayEntrada[i].substring (0, arrayEntrada[i].length() - 1);
        }

        for (i = 0; i<arrayAux.length; i++){
            time--;
            if(!frames.contains(arrayAux[i])){
                this.faltas++;
                if(frames.size() < qtd_frames){
                    frames.add(arrayAux[i]);
                    bitR.add(1);
                }

                else{
                    int j = 0;
                    while(j<bitR.size()){
                        if(bitR.get(j)==1){
                            String aux = frames.get(j);
                            frames.remove(j);
                            bitR.remove(j);
                            frames.add(aux);
                            bitR.add(0);
                        }
                        else {
                            frames.remove(j);
                            bitR.remove(j);
                            frames.add(arrayAux[i]);
                            bitR.add(1);
                            break;
                        }
                    }
                }
            }

            else {
                bitR.remove(frames.indexOf(arrayAux[i]));
                bitR.add(frames.indexOf(arrayAux[i]),1);
                this.acertos++;
            }

            if(time == 0){
                zerarBitR();
                time = zeresima;
            }
        }
    }
}