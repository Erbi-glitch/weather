package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {


    @FXML
    private AnchorPane main;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button;

    @FXML
    private TextField VvodText;

    @FXML
    private Text temp;

    @FXML
    private Text osusaetsa;

    @FXML

    private Text max;

    @FXML
    private Text min;

    @FXML
    private Text dav;

    @FXML
    void initialize() {
        Button.setOnAction( event -> {
            String getUserCity = VvodText.getText().trim();
            if(!getUserCity.equals("")){
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q="+ getUserCity +"&appid=5d98c4d8f29fc19ec18313b219fdb489&units=metric");

                if(!output.isEmpty())
                {
                    JSONObject obj = new JSONObject(output);
                    temp.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    osusaetsa.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                    max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                    dav.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });
    }
    private static String getUrlContent(String urlAdress)
    {
        StringBuffer content = new StringBuffer();
        try
        {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e)
        {
            System.out.println("Такой город не найден!");
        }
        return content.toString();
    }
}
