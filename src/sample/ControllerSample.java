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
    @FXML private TextField idLabelQ3;
    List<String> latFile;
    @FXML private Button btnCarregarArquivo;
    String[] inputs = new String[0];
    List<Integer> MRU = new ArrayList<Integer>();
    List<Integer> FIFO = new ArrayList<Integer>();
    List<Integer> NUR = new ArrayList<Integer>();
    List<Integer> SEGUNDA_CHANCE = new ArrayList<Integer>();
    List<Integer> OTIMO = new ArrayList<Integer>();


    //Botão carregar arquivo aciona o método de fazer upload do arquivo
    @FXML
    void CarregarArquivo(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Txt Files", latFile));
        File f = fc.showOpenDialog(null);
        List<String> temp = new ArrayList<String>();

        if (f != null) {
            //lê o arquivo
            Files.lines(f.toPath()).forEach((temp::add));
            //adiciona o conteúdo do arquivo a uma string "inputs" e retiramos os "-" usando o split
            inputs = temp.get(0).split("-");
            btnCarregarArquivo.setText(f.getAbsolutePath());
        }
    }

    //código para carregar somente os arquivos TXT e txt no modo leitura
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        latFile = new ArrayList<>();
        latFile.add("*.TXT");
        latFile.add("*.txt");
    }


    //Este método é disparado quando clicamos no botão gerar resultado
    public void ExecutarAlgoritmo(ActionEvent actionEvent) {


        int qtdFramesf = Integer.parseInt(labelQ2.getText());
        int zeresima = Integer.parseInt(idLabelQ3.getText());

        for (int qtdFramesi = Integer.parseInt(labelQ1.getText()); qtdFramesi <= qtdFramesf; qtdFramesi++) {
            //Instancia de cada algoritmo
            MRU mru = new MRU(qtdFramesi);
            FIFO fifo = new FIFO(qtdFramesi);
            Otimo otimo = new Otimo(qtdFramesi);
            SC segunda_chance = new SC(qtdFramesi,zeresima);
            NUR nur = new NUR(qtdFramesi,zeresima);

            //Passando o array de string para ser tratado no método de cada algoritmo
            for (int i = 0; i < (inputs.length); i++) {
                mru.MRU(inputs[i]);
                fifo.fifo(inputs[i]);
            }
            //aqui passamos a string inteira e a tratamos dentro de cada método
            otimo.otimo(inputs);
            segunda_chance.secondChance(inputs);
            nur.NUR(inputs);

            MRU.add(mru.getAcertos());
            FIFO.add(fifo.getAcertos());
            OTIMO.add(otimo.getAcertos());
            SEGUNDA_CHANCE.add(segunda_chance.getAcertos());
            NUR.add(nur.getAcertos());

        }
        System.out.println("LARANJA - MRU"+MRU);
        System.out.println("AMARELO - FIFO"+FIFO);
        System.out.println("VERDE - OTIMO"+OTIMO);
        System.out.println("AZUL AQUA - SEGUNDA CHANCE"+SEGUNDA_CHANCE);
        System.out.println("AZUL MARINHO - NUR:"+NUR);
    }

    //quando disparado, esse método chama a página graph.fxml passando através do método graphController.popularGraficoXXX()
    // as informações necessárias para gerar os gráficos
    public void GerarGrafico(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graph.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ControllerGraph graphController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1,700, 500));
        stage.show();

    //método para popular e gerar os gráficos
        graphController.popularGraficoFIFO(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) FIFO);
        graphController.popularGraficoMRU(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) MRU);
        graphController.popularGraficoOTIMO(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) OTIMO);
        graphController.popularGraficoSC(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) SEGUNDA_CHANCE);
        graphController.popularGraficoNUR(Integer.parseInt(labelQ1.getText()),Integer.parseInt(labelQ2.getText()), (ArrayList<Integer>) NUR);
        labelQ1.clear();
        labelQ2.clear();
        idLabelQ3.clear();
        MRU.clear();
        FIFO.clear();
        SEGUNDA_CHANCE.clear();
        OTIMO.clear();
        NUR.clear();
    }
}
