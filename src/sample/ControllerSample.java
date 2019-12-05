package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class ControllerSample implements Initializable{

    @FXML public TextField labelQ2;
    public TextField labelQ1;
    List<String> latFile;
    @FXML private Button btnCarregarArquivo;
    String[] inputs = new String[0];
    List<Integer> MRU = new ArrayList<Integer>();
    List<Integer> FIFO = new ArrayList<Integer>();
    List<Integer> NUR = new ArrayList<Integer>();
    List<Integer> SEGUNDA_CHANCE = new ArrayList<Integer>();
    List<Integer> OTIMO = new ArrayList<Integer>();

    @FXML
    void CarregarArquivo(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Txt Files", latFile));
        File f = fc.showOpenDialog(null);
        List<String> temp = new ArrayList<String>();

        if (f != null) {
            Files.lines(f.toPath()).forEach((temp::add));
            inputs = temp.get(0).split("-");
            btnCarregarArquivo.setText(f.getAbsolutePath());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        latFile = new ArrayList<>();
        latFile.add("*.TXT");
        latFile.add("*.txt");
    }


    public void ExecutarAlgoritmo(ActionEvent actionEvent) {
        int qtdFramesf = Integer.parseInt(labelQ2.getText());
        for (int qtdFramesi = Integer.parseInt(labelQ1.getText()); qtdFramesi <= qtdFramesf; qtdFramesi++) {
            AlgoritmoDeSubstituicao mru = new MRU(qtdFramesi);
            AlgoritmoDeSubstituicao fifo = new FIFO(qtdFramesi);

            for (int i = 0; i < (inputs.length); i++) {
                mru.inserir(inputs[i]);
                fifo.inserir(inputs[i]);

            }
            MRU.add(mru.getPageFoundCount());
            FIFO.add(fifo.getPageFoundCount());
            /*System.out.println("\nMRU com " + qtdFramesi + " frames:");
            System.out.println("Page Faults: " + mru.getPageFaultCount());
            System.out.println("Page Acertos: " + mru.getPageFoundCount());*/

            /*System.out.println("\nFIFO com " + qtdFramesi + " frames:");
            System.out.println("Page Faults: " + mru.getPageFaultCount());
            System.out.println("Page Acertos: " + mru.getPageFoundCount());*/
        }
        System.out.println(MRU);
        System.out.println(FIFO);
    }

    public void GerarGrafico(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graph.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ControllerGraph graphController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1,700, 500));
        stage.show();
        graphController.popularGraficoFIFO(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) FIFO);
        graphController.popularGraficoMRU(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) MRU);
    }
}
