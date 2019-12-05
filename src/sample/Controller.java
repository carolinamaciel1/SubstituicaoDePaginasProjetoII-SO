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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.w3c.dom.Text;

public class Controller implements Initializable{

    @FXML public TextField labelQ2;
    public TextField labelQ1;
    List<String> latFile;
    @FXML private Button btnCarregarArquivo;
    String[] inputs = new String[0];


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

            /*System.out.println("\nMRU com " + qtdFramesi + " frames:");
            System.out.println("Page Faults: " + mru.getPageFaultCount());
            System.out.println("Page Acertos: " + mru.getPageFoundCount());*/

            System.out.println("\nFIFO com " + qtdFramesi + " frames:");
            System.out.println("Page Faults: " + mru.getPageFaultCount());
            System.out.println("Page Acertos: " + mru.getPageFoundCount());
        }
    }
}
