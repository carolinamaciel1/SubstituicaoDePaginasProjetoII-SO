package sample;

import java.util.Arrays;
import java.util.LinkedList;

class Otimo {
    private LinkedList<String> frames;
    private LinkedList<Integer> delta;
    private int acertos;
    private int faltas;
    private int qtd_frames;

    public Otimo(int frames) {
        this.acertos = 0;
        this.faltas = 0;
        this.qtd_frames = frames;
        this.frames = new LinkedList<>();
        this.delta = new LinkedList<>();

    }

    public int getAcertos() {
        return acertos;
    }

    public int getFaltas() {
        return faltas;
    }

    public int calcularDelta (String[] array, int ini, int index){
        int delta = 0;
        for(int i = ini; i<array.length; i++){
            if(frames.get(index).equals(array[i])){
                return delta;
            }
            delta++;
        }
        return delta+1;
    }


    public void otimo(String[] stringEntrada) {
        int i, j, aux;

        String[] arrayAux = new String[stringEntrada.length];

        for (i = 0; i < stringEntrada.length; i++) {
            arrayAux[i] = stringEntrada[i].substring (0, stringEntrada[i].length() - 1);
        }

        for (i = 0; i<arrayAux.length; i++){
            if(!frames.contains(arrayAux[i])){
                this.faltas++;

                if(frames.size() < qtd_frames){
                    frames.add(arrayAux[i]);
                }

                else{
                    if(i+1 == arrayAux.length){
                        frames.remove(0);
                        frames.add(0,arrayAux[i]);
                    }

                    else{
                        delta.clear();
                        for(j=0;j<frames.size();j++){
                            delta.add(calcularDelta(arrayAux,i+1,j));
                        }

                        aux = 0;
                        for(j = 0; j<delta.size();j++){

                            if(delta.get(j) > aux){
                                aux = delta.get(j);
                            }
                        }

                        frames.remove(delta.indexOf(aux));
                        frames.add(delta.indexOf(aux),arrayAux[i]);
                    }
                }
            }

            else{
                this.acertos++;
            }
        }
    }
}