package sample;

import java.util.LinkedList;

public class OTIMO {
    protected int indice;
    private static LinkedList quadros;
    protected int acertos;
    protected int faltas;
    protected int numeroDeQuadros;
    protected int aux;
    protected int j;
    protected int k;
    protected boolean interruption;

    public OTIMO(int frames) {
        if (numeroDeQuadros < 0)
            throw new IllegalArgumentException();
        this.indice = 0;
        this.acertos = 0;
        this.faltas = 0;
        this.numeroDeQuadros = frames;
        this.quadros = new LinkedList();

    }

    public int getPageFoundCount() {
        return acertos;
    }

    public int getPageFaultCount() {
        return faltas;
    }

    public void calcular(String[] stringArray) {
        int i;

        String[] arrayAux = new String[stringArray.length];

        for (i = 0; i < stringArray.length; i++) {
            arrayAux[i] = stringArray[i].substring (0, stringArray[i].length() - 1);
        }

        for (i = 0; i < arrayAux.length; i++) {
            if (!quadros.contains(arrayAux[i])) {
                faltas++;
                if (quadros.size() < numeroDeQuadros) {
                    quadros.add(arrayAux[i]);
                }
                else {
                    aux = 0;
                    j = 0;
                    k = i+1;
                    interruption = false;

                    while(j < numeroDeQuadros){
                        while(k < arrayAux.length){
                            if(quadros.get(j) == arrayAux[k]){
                                if(k > aux){
                                    aux = k;
                                    indice = j;
                                    interruption = true;
                                }
                            }
                            k++;
                        }

                        if(!interruption){
                            quadros.remove(j);
                            quadros.add(j,arrayAux[i]);
                            break;
                        }
                        j++;
                    }

                    if(interruption){
                        quadros.remove(indice);
                        quadros.add(indice,arrayAux[i]);
                    }

                }
            }

            else {
                acertos++;
            }
        }
    }
}


