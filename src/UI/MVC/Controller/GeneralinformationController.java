package UI.MVC.Controller;

import BE.GeneralInfo;
import UI.MVC.Model.GeneralinformationModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GeneralinformationController implements Initializable {
    public Label contextMenu;
    public TextArea contextTextArea;
    public Label lblOBS;
    @FXML
    private Button ressourcerBtn;
    @FXML
    private Button mestringBtn;
    @FXML
    private Button motivationBtn;
    @FXML
    private Button rollerBtn;
    @FXML
    private Button vanerBtn;
    @FXML
    private Button uddannelseBtn;
    @FXML
    private Button livshistorieBtn;
    @FXML
    private Button netværkBtn;
    @FXML
    private Button helbredBtn;
    @FXML
    private TextArea textTA;
    @FXML
    private Button saveBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Label lblCatName;
    private SceneCreator sceneCreator;
    private GeneralinformationModel generalinformationModel;
    private  ParseModel parseModel = ParseModel.getInstance();
    private String Ressourcer;
    private String Mestring;
    private String Motivation;
    private String Roller;
    private String Vaner;
    private String Uddannelse;
    private String Livshistorie;
    private String Netværk;
    private String Helbred;
    private int id = 0;
    private GeneralInfo generalInfo;
    private Alert alert;



    public GeneralinformationController() throws IOException {
        ressourcerBtn = new Button();
        mestringBtn = new Button();
        motivationBtn = new Button();
        rollerBtn = new Button();
        vanerBtn = new Button();
        uddannelseBtn = new Button();
        livshistorieBtn = new Button();
        netværkBtn = new Button();
        helbredBtn = new Button();
        sceneCreator = new SceneCreator();
        generalinformationModel = new GeneralinformationModel();
        lblCatName = new Label();
        textTA = new TextArea();
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        generalInfo = new GeneralInfo(generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getId(),"","","","","","","","","","","");
        contextTextArea = new TextArea();
        saveBtn = new Button();
        contextMenu = new Label();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ressourcerBtn.setOnMouseEntered(event -> ressourcerBtn.getStyleClass().add("gen-buttons-hover"));
        vanerBtn.setOnMouseEntered(event -> vanerBtn.getStyleClass().add("gen-buttons-hover"));
        mestringBtn.setOnMouseEntered(event -> mestringBtn.getStyleClass().add("gen-buttons-hover"));
        motivationBtn.setOnMouseEntered(event -> motivationBtn.getStyleClass().add("gen-buttons-hover"));
        rollerBtn.setOnMouseEntered(event -> rollerBtn.getStyleClass().add("gen-buttons-hover"));
        uddannelseBtn.setOnMouseEntered(event -> uddannelseBtn.getStyleClass().add("gen-buttons-hover"));
        livshistorieBtn.setOnMouseEntered(event -> livshistorieBtn.getStyleClass().add("gen-buttons-hover"));
        netværkBtn.setOnMouseEntered(event -> netværkBtn.getStyleClass().add("gen-buttons-hover"));
        helbredBtn.setOnMouseEntered(event -> helbredBtn.getStyleClass().add("gen-buttons-hover"));

        ressourcerBtn.setOnMouseExited(event -> ressourcerBtn.getStyleClass().remove("gen-buttons-hover"));
        vanerBtn.setOnMouseExited(event -> vanerBtn.getStyleClass().remove("gen-buttons-hover"));
        mestringBtn.setOnMouseExited(event -> mestringBtn.getStyleClass().remove("gen-buttons-hover"));
        motivationBtn.setOnMouseExited(event -> motivationBtn.getStyleClass().remove("gen-buttons-hover"));
        rollerBtn.setOnMouseExited(event -> rollerBtn.getStyleClass().remove("gen-buttons-hover"));
        uddannelseBtn.setOnMouseExited(event -> uddannelseBtn.getStyleClass().remove("gen-buttons-hover"));
        livshistorieBtn.setOnMouseExited(event -> livshistorieBtn.getStyleClass().remove("gen-buttons-hover"));
        netværkBtn.setOnMouseExited(event -> netværkBtn.getStyleClass().remove("gen-buttons-hover"));
        helbredBtn.setOnMouseExited(event -> helbredBtn.getStyleClass().remove("gen-buttons-hover"));

        setItemsInvisible(0,true);

        contextTextArea.setOpacity(0);
        contextTextArea.setDisable(true);
    }

    public void setItemsInvisible(double opacity, boolean isDisabled){
        textTA.setOpacity(opacity);
        textTA.setDisable(isDisabled);
        lblCatName.setOpacity(opacity);
        lblCatName.setDisable(isDisabled);
        saveBtn.setOpacity(opacity);
        saveBtn.setDisable(isDisabled);
        contextMenu.setOpacity(opacity);
        contextMenu.setDisable(isDisabled);
    }

    /**
     * closes the current fxml and opens the CitizenView
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css",this);
        stage.setScene(scene);
    }

    /**
     * Saves the information put in by the user
     * @param actionEvent
     * @throws SQLException
     */
    public void handleSave(ActionEvent actionEvent) throws SQLException {
        switch (id) {
            case 1:
                generalInfo.setRessourcer(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 2:
                generalInfo.setVaner(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 3:
                generalInfo.setMestring(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 4:
                generalInfo.setMotivation(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 5:
                generalInfo.setRoller(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 6:
                generalInfo.setUddannelseJob(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 7:
                generalInfo.setLivshistorie(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 8:
                generalInfo.setNetværk(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
            case 9:
                generalInfo.setHelbredsoplysninger(textTA.getText());
                generalinformationModel.updateInfo(generalInfo);
                break;
        }
        alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Info was saved", ButtonType.OK);
        alert.showAndWait();
    }

    public void handleRessourcer (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String ressourcer = "Definition: De fysiske eller mentale kræfter, som borgerenen i et vist omfang har til rådighed og kan udnytte. Fysiske kræfter kan fx være i form af fysisk sundhed og styrke. Mentale kræfter kan fx være i form af psykisk sundhed og styrke, herunder tanker og måder at forholde sig til situationer og andre mennesker på.\n" + "Dokumentationspraksis: Her dokumenteres de ressourcer, borgeren har i forhold til at løse dagligdagens opgaver. Det kan være både fysiske og mentale funktioner\n" + "Hjælpespørgsmål: Hvordan klarer du opgaver i dagligdagen? Hvilke hverdagsopgaver/ udfordringer er lette at løse for dig? Hvorfor? Hvilke hverdagsopgaver/ udfordringer er svære at løse for dig? Hvorfor?\n" + "Eksempler: \"N.N. kan godt lide udfordringer og er god til at lære nyt\". \"N.N. har levet et aktivt liv og er derfor i udmærket fysisk form\".\n";
        contextTextArea.setText(ressourcer);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Ressourcer");
        Ressourcer =  generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getRessourcer();
        ressourcerBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().add("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Ressourcer);
        id = 1;
    }

    public void handleVaner (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String vaner = "Definition: Regelmæssig adfærd som borgeren har tillært gennem stadig gentagelse og udførelse helt eller delvist ubevidst. Vaner er fx døgnrytmen, måden at blive tiltalt på, kontakt med medmennesker og relationer, måde at anskue verden på.\n" + "Dokumentationspraksis: Her dokumenteres borgerens vaner, som er en naturlig del af hverdagen, og som borgeren plejer at gøre. Det kan både være vaner, som borgeren gør af fysiske og psykiske årsager.\n" + "Hjælpespørgsmål: Har du nogle vaner, som det er vigtigt, vi kender? Hvad er dine rutiner, når du står op? Hvad husker dig på dine vaner?\n" + "Eksempler: ”N.N. vil gerne have smykker på hver morgen”. ”N.N vil gerne ryge efter at have spist”.\n";
        contextTextArea.setText(vaner);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Vaner");
        Vaner = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getVaner();
        vanerBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().add("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Vaner);
        id = 2;
    }

    public void handleMestring (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String mestring = "Definition: Borgerens bevidste eller ubevidste håndtering af livet/sygdommen – både udfordringer og muligheder.\n" + "Dokumentationspraksis: Her dokumenteres, hvordan borgeren positivt eller negativt mestrer den modgang vedkommende møder.\n" + "Hjælpespørgsmål: Hvordan klarer du med- og modgang? Hvordan reagerer du, når der sker noget, der er svært eller uventet? Hvordan kunne du godt tænke dig, at vi samarbejder om udfordringer?\n" + "Eksempler: \"N.N. beskriver sig selv som en person, der altid klarer udfordringer og godt kan lide at lære nyt og engagere sig.\" \"N.N. er nogle dage negativ over sin situation. Med opmuntring genfinder N.N. gejsten til at kæmpe videre.\"\n";
        contextTextArea.setText(mestring);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Mestring");
        Mestring = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getMestring();
        mestringBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().add("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Mestring);
        id = 3;
    }

    public void handleMotivation (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String motivation = "Definition: Drivkraften bag at borgeren handler på en bestemt måde eller går i gang med/opretholder en opgave/indsats.\n" + "Dokumentationspraksis: Her dokumenteres borgerens ønsker for sit liv (overordnet mål), og hvad der motiverer borgeren.\n" + "Hjælpespørgsmål: Hvad gør dig glad? Er der udfordringer, der er spændende at løse?\n" + "Eksempler: \"N.N. har det bedst, når der er rent og pænt i lejligheden: \"Hvis hjemmehjælpen tager det grove, så kan jeg sagtens selv klare resten.\"\" \"N.N. spiser mere, når han sidder foran sit tv med et par stykker smørrebrød.\"\n";
        contextTextArea.setText(motivation);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Motivation");
        Motivation = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getMotivation();
        motivationBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().add("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Motivation);
        id = 4;
    }

    public void handleRoller (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String roller = "Definition: De roller som er særligt vigtige for borgeren i forhold til familie, arbejde og samfund.\n" + "Dokumentationspraksis: Her dokumenteres de roller, borgeren angiver at have. Det kan fx være rolle som ægtefælle, bedsteforælder eller aktiv i beboerforening.\n" + "Hjælpespørgsmål: Hvilke roller har du, eller har du haft, som har haft betydning for dig? Har du været aktiv i lokalsamfund, bestyrelser/ foreninger eller lign.?\n" + "Eksempler: \"N.N. har tidligere været formand i idrætsforeningen\". \"N.N. var indtil fornyelig frivillig leder af den lokale genbrugsbutik\".\n";
        contextTextArea.setText(roller);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Roller");
        Roller = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getRoller();
        rollerBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().add("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Roller);
        id = 5;
    }

    public void handleUddannelse (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String uddannelse = "Definition: En beskrivelse af borgerens oplevelse af væsentlige begivenheder, interesser og gøremål igennem livet\n" + "Dokumentationspraksis: Her dokumenteres borgerens fortælling om sit liv. Her dokumenteres borgerens ønsker for den sidste tid.\n" + "Hjælpespørgsmål: Hvor er du født? Hvor er du opvokset? Hvilke interesser og begivenheder har været væsentlige for dig?\n" + "Eksempler: \"N.N. fortæller at han blev gift i 1960 og har to børn\". ”N.N. ønsker at være hjemme i den sidste tid. Han ønsker ikke indlæggelse og har aftalt alt det praktiske med børnene\".\n";
        contextTextArea.setText(uddannelse);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Uddannelse");
        Uddannelse = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getUddannelseJob();
        uddannelseBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().add("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Uddannelse);
        id = 6;
    }

    public void handleLivshistorie (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String livshistorie = "Definition: En beskrivelse af borgerens oplevelse af væsentlige begivenheder, interesser og gøremål igennem livet\n" + "Dokumentationspraksis: Her dokumenteres borgerens fortælling om sit liv. Her dokumenteres borgerens ønsker for den sidste tid.\n" + "Hjælpespørgsmål: Hvor er du født? Hvor er du opvokset? Hvilke interesser og begivenheder har været væsentlige for dig?\n" + "Eksempler: \"N.N. fortæller at han blev gift i 1960 og har to børn\". ”N.N. ønsker at være hjemme i den sidste tid. Han ønsker ikke indlæggelse og har aftalt alt det praktiske med børnene\".\n";
        contextTextArea.setText(livshistorie);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Livshistorie");
        Livshistorie = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getLivshistorie();
        livshistorieBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().add("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Livshistorie);
        id = 7;
    }

    public void handleNetværk (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String netværk = "Definition: Personer som er tæt på borgeren, og som giver praktisk og/eller følelsesmæssigt støtte og omsorg overfor borgeren. Netværk kan være offentligt eller privat. Et offentligt netværk består af personlige hjælpere, sundhedspersonale og andre professionelle primært omsorgsgivere. Privat netværk er familie, slægtning, venner og bekendtskaber\n" + "Dokumentationspraksis: Her dokumenteres borgerens netværk i bredere forstand.\n" + "Hjælpespørgsmål: Hvad betyder dit netværk for dig? Er der nogen, som har større betydning end andre? Hvordan er din kontakt til dine børn?\n" + "Eksempler: \"N.N. har en barndomsven fra Fyn, som han ringer sammen med 1 gang om ugen”. \"N.N. fortæller, at hans nærmeste venner er fra hans skakklub – der kommer han hver tirsdag, og de ringer altid efter ham, hvis han ikke dukker op”. \"N.N. fortæller, at han får besøg af Mia fra besøgstjenesten hver onsdag\"\n";
        contextTextArea.setText(netværk);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("Netværk");
        Netværk = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getNetværk();
        netværkBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().add("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Netværk);
        id = 8;
    }

    public void handleHelbred (ActionEvent actionEvent){
        setItemsInvisible(100,false);

        String helbred = "Definition: Helbredsoplysninger: Aktuelle eller tidligere sygdomme og handicap der har betydning for borgerens situation. Sundhedsfaglige kontakter: Medarbejder eller enheder indenfor sundhedsvæsenet borgeren er tilknyttet, fx øjenlæge, tandlæge, fodterapeut eller afdeling/ambulatorium.\n" + "Dokumentationspraksis: Her kan borgerens sygdomme og handicap dokumenteres for at give et samlet overblik. Hvis oplysningen kommer fra borgeren eller pårørende, skal dette fremgå. Her kan behandlingsansvarlig læge/ambulatorie ift. en konkret sygdom dokumenteres, hvis dette ikke er angivet andre steder.\n" + "Hjælpespørgsmål: Lad evt. medicinlisten danne baggrund for spørgsmål om borgerens helbred. Jeg kan se, at du får smertestillende medicin. Hvad skyldes smerterne? Fejler du noget, der har betydning for din hverdag/måde at leve dit liv på? Har du kontakt til andre læger end din praktiserende læge? Går du til fodterapeut? Hvem er din tandlæge?\n" + "Eksempler: Sygdomme: KOL Diabetes type 2 Forhøjet blodtryk Grøn stær Leddegigt. Handicap: Crusamputeret højre side. \"N.N. fortæller, at han blev opereret for prostatacancer for 3 år siden\". \"N.N. fortæller, at han ofte får blærebetændelse\"\n";
        contextTextArea.setText(helbred);

        lblOBS.setOpacity(0);
        lblOBS.setDisable(true);
        lblCatName.setText("HelbredOplysninger");
        Helbred = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getHelbredsoplysninger();
        helbredBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().add("gen-buttons-clicked");
        });
        textTA.setText(Helbred);
        id = 9;
    }

    public void contextMenuEntered(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(100);
        contextTextArea.setDisable(false);
    }

    public void contextMenuExited(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(0);
        contextTextArea.setDisable(true);
    }

    public void contextTextAreaEntered(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(100);
        contextTextArea.setDisable(false);
    }

    public void contextTextAreaExited(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(0);
        contextTextArea.setDisable(true);
    }
}