package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class ControllerGraph{

    //Nesta controller Graph temos apenas os métodos que plotam os gráficos de cada algoritmo
    @FXML
    private LineChart<String,Number> idGrafico;

    //código para gerar gráfico do MUR
    public void popularGraficoMRU(int frame_inicial, int frame_final, ArrayList<Integer> MRU){
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), MRU.get(qtdFramesi- frame_inicial)));

        }
        idGrafico.getData().add(series);

    }

    //código para gerar gráfico do FIFO
    public void popularGraficoFIFO(int frame_inicial, int frame_final, ArrayList<Integer> FIFO){
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), FIFO.get(qtdFramesi- frame_inicial)));
        }
        idGrafico.getData().add(series);

    }

    //código para gerar gráfico do ÓTIMO
    public void popularGraficoOTIMO(int frame_inicial, int frame_final, ArrayList<Integer> FIFO){
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), FIFO.get(qtdFramesi- frame_inicial)));
        }
        idGrafico.getData().add(series);

    }

    //código para gerar gráfico do SC
    public void popularGraficoSC(int frame_inicial, int frame_final, ArrayList<Integer> SC){
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), SC.get(qtdFramesi- frame_inicial)));
        }
        idGrafico.getData().add(series);

    }

    public void popularGraficoNUR(int frame_inicial, int frame_final, ArrayList<Integer> NUR) {
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), NUR.get(qtdFramesi- frame_inicial)));
        }
        idGrafico.getData().add(series);
    }
}
