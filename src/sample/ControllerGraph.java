package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class ControllerGraph{
    @FXML
    private LineChart<String,Number> idGrafico;

    public void popularGraficoMRU(int frame_inicial, int frame_final, ArrayList<Integer> MRU){
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), MRU.get(qtdFramesi- frame_inicial)));

        }
        idGrafico.getData().add(series);
        idGrafico.legendSideProperty();

    }

    public void popularGraficoFIFO(int frame_inicial, int frame_final, ArrayList<Integer> FIFO){
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        for (int qtdFramesi = frame_inicial; qtdFramesi <= frame_final; qtdFramesi++) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(qtdFramesi), FIFO.get(qtdFramesi- frame_inicial)));
        }
        idGrafico.getData().add(series);

    }


}
