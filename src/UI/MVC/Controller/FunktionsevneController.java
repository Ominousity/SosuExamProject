package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FunktionsevneController implements Initializable {
    public Button saveBtn;
    public Label contextMenu;
    public TextArea contextTextArea;
    public Label contextMenuFunc;
    public TextArea contextTextAreaFunc;
    @FXML
    private ImageView imgArrow;
    @FXML
    private Label lblOBS;
    @FXML
    private ImageView ivImage;
    @FXML
    private Label lblNurværende;
    @FXML
    private Label lblBorgerVudering;
    @FXML
    private Label lblMål;
    @FXML
    private Label lblVudering1;
    @FXML
    private Label lblVudering2;
    @FXML
    private Label lblVudering3;
    @FXML
    private Label lblVælg;
    @FXML
    private GridPane gridPaneFunc;
    @FXML
    private Button backBtn;
    @FXML
    private ComboBox cbNuværendeTilstand;
    @FXML
    private ComboBox cbBorgerVudering;
    @FXML
    private ComboBox cbGoal;
    @FXML
    private ComboBox<SubCategory> cbSubCat;
    @FXML
    private Label lblCat;
    private SceneCreator sceneCreator;
    private ButtonCreator buttonCreator;
    private int btnid = 0;
    private int Colorid = 1;
    private int x = 0;
    private int y = 0;
    private ArrayList<ComboBox> comboxs;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private List<Category> categoryList;
    private ObservableList<Category> funcCategory;
    private List<SubCategory> funcsubcatlist;
    private ObservableList<SubCategory> subCategoryList;
    private ObservableList<Integer> vudering;
    private ParseModel parseModel = ParseModel.getInstance();
    private ArrayList<Button> buttons;


    public FunktionsevneController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        funcCategory = FXCollections.observableArrayList();
        subCategoryList = FXCollections.observableArrayList();
        vudering = FXCollections.observableArrayList();
        vudering.add(0);
        vudering.add(1);
        vudering.add(2);
        vudering.add(3);
        vudering.add(4);
        vudering.add(9);
        buttons = new ArrayList<>();
        lblCat = new Label();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFuncCategories();
        for (Category category : funcCategory){
            addFuncButtons(category.getCatName());
        }
        System.out.println(funcCategory);
        setItemsInvisible(0, true);
        cbNuværendeTilstand.setItems(vudering);
        cbGoal.setItems(vudering);
        cbBorgerVudering.setItems(vudering);

        contextTextArea.setOpacity(0);
        contextTextArea.setDisable(true);
        contextTextAreaFunc.setOpacity(0);
        contextTextAreaFunc.setDisable(true);
    }

    /**
     * closes the current fxml and opens the CitizenView
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void handleSave(ActionEvent actionEvent) {

    }

    public void getFuncCategories(){
        categoryList = categoryModel.getAllCategories(ParseModel.citizen.getID());
        for (Category category:categoryList) {
            if(!category.isFuncHealth){
                funcCategory.add(category);
            }
        }
    }


    public void setItemsInvisible(int opacity, boolean isDisabled){
        ivImage.setOpacity(opacity);
        ivImage.setDisable(isDisabled);
        lblBorgerVudering.setOpacity(opacity);
        lblBorgerVudering.setDisable(isDisabled);
        lblMål.setOpacity(opacity);
        lblMål.setDisable(isDisabled);
        lblNurværende.setOpacity(opacity);
        lblNurværende.setDisable(isDisabled);
        lblVudering1.setOpacity(opacity);
        lblVudering1.setDisable(isDisabled);
        lblVudering2.setOpacity(opacity);
        lblVudering2.setDisable(isDisabled);
        lblVudering3.setOpacity(opacity);
        lblVudering3.setDisable(isDisabled);
        cbBorgerVudering.setOpacity(opacity);
        cbBorgerVudering.setDisable(isDisabled);
        cbGoal.setOpacity(opacity);
        cbGoal.setDisable(isDisabled);
        cbNuværendeTilstand.setOpacity(opacity);
        cbNuværendeTilstand.setDisable(isDisabled);
        lblVælg.setOpacity(opacity);
        lblVælg.setDisable(isDisabled);
        cbSubCat.setOpacity(opacity);
        cbSubCat.setDisable(isDisabled);
        contextMenu.setOpacity(opacity);
        contextMenu.setDisable(isDisabled);
        saveBtn.setOpacity(opacity);
        saveBtn.setDisable(isDisabled);
        contextMenuFunc.setOpacity(opacity);
        contextMenuFunc.setDisable(isDisabled);
    }

    public void setItemsToCombobox() throws SQLException {
        cbSubCat.setItems(subCategoryList);
    }

    public void addFuncButtons(String text){
        Button button = buttonCreator.createButtons(false, 100, 325, 0, 0, 0, 0, Pos.CENTER, "buttons-pressed", ""+btnid, text);
        String color = "-fx-background-color: " + categoryList.get(Colorid).getCatColor();
        button.setStyle(color);
        gridPaneFunc.add(button, x, y);
        button.setFont(Font.font(24));
        Paint paint = new Color(1,1,1, 1);
        button.setTextFill(paint);
        if (button != null){
            buttons.add(button);
        }
        button.setOnAction(e -> {
            try {
                lblCat.setText(button.getText());
                parseId(button.getId());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #415d8a");
        });
        button.setOnMouseExited(event -> {
            button.setStyle(color);
        });
        Colorid++;
        btnid++;
        y++;
    }

    public void parseId(String i) throws SQLException {
        Category category = categoryModel.getAllCategories(ParseModel.citizen.getID()).get(Integer.parseInt(i));
        subCategoryList = subCategoryModel.getObservableSubCategories(category.getID());
        cbSubCat.setItems(subCategoryList);
        setItemsInvisible(100, false);
        lblOBS.setDisable(true);
        lblOBS.setOpacity(0);
    }

    public void handleContextMenuEntered(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(100);
        contextTextArea.setDisable(false);
        //EgenOmsorg:
        String vaskeSig = "Vaske og tørre sig på kroppen og kropsdele med anvendelse af vand og passende rensemidler f.eks. tage bad, brusebad, vaske hænder og fødder, ansigt og hår og tørre sig med håndklæde. Inkl.: vaske kropsdele, vaske hele kroppen og tørre sig. Ekskl.: kropspleje; gå på toilet.\n" + "0: Kan klare sig selv, evt. ved brug af fx badebænk, greb ved bruser, tåvasker, tåtørrer, rygbadebørste\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx gøre klar til badet, minde om vask under rask arm.\n" + "2: Behov for nogen hjælp, fx hjælp til at vaske og/eller tørre sig, nedre hygiejne.\n" + "3: Behov for en hel del hjælp, men kan fx hjælpe med til at vaske/tørre hænder, ansigt, overkrop, evt. under guidning.\n" + "4: Behov for hjælp til alt i forbindelse med at vaske sig. \n";
        String gåPåToilet = "Planlægge og udføre toiletbesøg til udskillelse af affaldsprodukter (menstruation, urin og afføring) og efterfølgende rengøring. Inkl.: styre vandladning, afføring og menstruation Ekskl.: vaske sig; kropspleje.\n" + "\n" + "0: Kan klare sig selv, evt. med brug af fx kolbe, toiletstol, vasketørretoilet, toiletbørste med langt skaft\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx påmindelse om toiletbesøg, tømning af bækken/kolbe, hjælp til ble om natten.\n" + "2: Behov for nogen hjælp, fx skifte ble, placere ble/kateter korrekt, hygiejne, trække benklæder op og ned ved toiletbesøg.\n" + "3: Behov for en hel del hjælp, kan deltage i dele, fx tage initiativ til toiletbesøg, tørre sig selv ved vandladning, selv vaske hænder.\n" + "4: Behov for hjælp til alt i forbindelse med toiletbesøg.\n";
        String kropspleje = "Pleje af de dele af kroppen, som behøver anden pleje end vask og tørring f.eks. hud, ansigt, tænder, hår, negle og kønsdele. Inkl.: hudpleje, tandpleje, hårpleje, neglepleje. Ekskl.: vaske sig; gå på toilet.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx cremepåsmører, lang kam eller protesetandbørste med sugekopper.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx gøre en tandbørste klar, finde barbermaskine eller skraber frem, rense negle.\n" + "2:  Behov for nogen hjælp, fx barbering af en del af ansigtet, almindelig neglepleje.\n" + "3: Behov for en hel del hjælp, men kan fx børste tænder, rede hår, evt. under guidning.\n" + "4:  Behov for hjælp til al kropspleje.\n";
        String afOgPåklædning = "Udføre sammensatte handlinger i forbindelse med på- og afklædning, at tage fodbeklædning på og af i rækkefølge og i overensstemmelse med den sociale sammenhæng og de klimatiske forhold som f.eks. iføre sig, rette på og afføre sig skjorter, bluser, bukser, undertøj, […], handsker, frakker, sko, støvler, sandaler og hjemmesko. Inkl.: tage tøj på og af inkl. sko og strømper og vælge passende påklædning\n" + "\n" + "0: Kan klare sig selv, evt. med brug af strømpepåtager/ aftager, langt skohorn, knappekrog\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til lukning af knapper, lynlåse, bh, sko; vejledning ved valg af tøj, påmindelse om skift af tøj.\n" + "2: Behov for nogen hjælp, fx til al nedre påklædning, kropsbårne hjælpemidler\n" + "3: Behov for en hel del hjælp, men kan måske vælge fx ønsket beklædning, tage tøj på rask arm/ben under detaljeret guidning\n" + "4: Behov for hjælp til alt ved af- og påklædning\n";
        String spise = "Udføre sammensatte handlinger i forbindelse med indtagelse af føde, som er serveret for én, få maden op til munden og spise på en kulturelt accepteret måde, skære eller bryde maden i stykker, åbne flasker og dåser, anvende spiseredskaber, deltage i måltider og i festligheder. Ekskl.: drikke.\n" + "0: Kan klare sig selv, fx ved brug af gigtvenligt bestik, tallerken med skridsikker bund, spiserobot\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx skære maden i stykker, tage låg af, øse op fra fade\n" + "2: Behov for nogen hjælp, fx til at spise dele af maden som suppe, sikre hensigtsmæssig spisestilling, guides til at starte.\n" + "3:  Behov for en hel del hjælp, kan evt. bryde mad i stykker, bruge et spiseredskab.\n" + "4: Behov for hjælp til al spisning, fx at blive madet.\n";
        String drikke = "Holde fast om en drik, tage drikken op til munden og drikke på en kulturelt accepteret måde, blande, omrøre og skænke drikke op, åbne flasker og dåser, bruge sugerør eller drikke af rindende vand fra en hane eller en kilde; amning. Ekskl.: spise\n" + "0: Kan klare sig selv, evt. ved brug af tunge kopper, sugerør, skruelågsåbner, elektrisk dåseåbner.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til at blande saft, åbne mælkekarton, guides til at drikke\n" + "2: Behov for nogen hjælp, fx skænke op i et glas, mælk i kaffen, fortykningsmiddel i væsker, sikre hensigtsmæssig drikkestilling\n" + "3: Behov for en hel del hjælp, skal måske have hjælp til at tage fat om koppen, men kan fx løfte tudekop det sidste stykke til munden, evt. under guidning.\n" + "4: Behov for hjælp til alt for at kunne drikke.\n";
        String varetageEgenSundhed = "Sikre sit velvære, helbred og fysiske og psykiske velbefindende ved f.eks. at indtage varieret kost, have passende niveau af fysisk aktivitet, holde sig varm eller afkølet, undgå skader på helbredet, dyrke sikker sex inkl. anvendelse af kondomer, lade sig vaccinere og følge regelmæssige helbredsundersøgelser. Inkl.: sikre sit fysiske velvære, have hensigtsmæssige kost- og motionsvaner; tage vare på egen sundhed.\n" + "\n" + "0: Kan klare sig selv, fx ved brug af kalender til at huske influenzavaccination.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx information om mulige sundhedsog aktivitetstilbud, vejledning om vaccination, mindes om at drikke og spise\n" + "2: Behov for nogen hjælp, fx vejledning om varieret kost, regulering af rusmidler, kontakte egen læge eller tandlæge, følge regelmæssige helbredsundersøg elser.\n" + "3: Behov for en hel del hjælp, fx for at holde sig passende varm eller afkølet, detaljeret guidning for at huske overtøj når det er koldt, målrettet forebygge forværring af sygdom eller funktionsevne, men kan evt. deltage i fx motion under guidning; nødes til at spise og drikke.\n" + "4: Kan ikke selv tage vare på egen sundhed, herunder forebygge forværring af sygdom eller tab af funktionsevne.\n";
        String fødeindtagelse = "Indtagelse og bearbejdning af fødemidler og væsker gennem munden. Inkl.: at suge, tygge, bide og behandle maden i mundhulen, spytflåd, at synke, gylpe, spytte og kaste op; tilstande som dysfagi, aspiration af føde, […], utilstrækkelig spytproduktion. Ekskl.: fornemmelser relaterede til fordøjelsessystemet\n" + "\n" + "0: Kan klare sig selv, evt. med brug af fx fortykningsmiddel.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx påmindelse eller vejledning om at anvende fortykningsmiddel.\n" + "2:  Behov for nogen hjælp, fx vejledning til at fravælge vanskeligt spiselige madvarer (sejt, hårdt, slimprovokerende) og spise i passende tempo og mundrette bidder.\n" + "3: Behov for en hel del hjælp, kan evt. indtage føde efter fx mundstimulation og tungeøvelser inden måltidet, eller indtage mad og væske under detaljeret guidning eller opsyn.\n" + "4: Behov for hjælp til alt i forbindelse med fødeindtagelse.\n";
        //Praktiske Opgaver:
        String udføreDagligeRutiner = "Udføre simple, komplekse og sammensatte handlinger til planlægning, styring og gennemførelse af dagligt tilbagevendende rutiner eller pligter som f.eks. at overholde tider og lægge planer for særlige aktiviteter i løbet af dagen. Inkl.: styre og gennemføre daglige rutiner; styre ens eget aktivitetsniveau. Ekskl.: påtage sig flere opgave.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx kalender, påmindelse via ur\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx blive mindet om at udføre/deltage i ikkerutineprægede aktiviteter og anvende/ ajourføre kalender.\n" + "2: Behov for nogen hjælp, fx blive mindet om daglige og hyppigt tilbagevendende gøremål og aftaler.\n" + "3: Behov for en hel del hjælp, for at kunne skabe sammenhæng og struktur i hverdagens aktiviteter, men kan fx deltage i enkelte af hverdagens aktiviteter, hvis borgeren bliver sat i gang.\n" + "4: Har behov for hjælp til alt for at kunne udføre daglige rutiner.\n";
        String skaffeSigVarerOgTjenesteydelser = "Vælge, tilvejebringe og transportere varer, som er nødvendige i dagliglivet som f.eks. at vælge, købe, transportere og opbevare mad, drikke, tøj, rengøringsmidler, brændsel, husholdningsgenstande og værktøj; tilvejebringe brugsgenstande og service. Inkl.: indkøbe og anskaffe daglige fornødenheder. Ekskl.: skaffe sig bolig.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx indkøbstrolley.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx transport og håndtering af tunge varer.\n" + "2: Behov for nogen hjælp, fx til at bestille varer og tjenesteydelser, skrive indkøbssedlen, sætte tunge varer på plads\n" + "3: Behov for en hel del hjælp, men kan fx oplyse om nogle ønskede varer og tjenesteydelser, samt sætte enkelte varer på plads\n" + "4: Har behov for hjælp til alt for at kunne skaffe sig varer og tjenesteydelser.\n";
        String laveMad = "Planlægge, tilberede og servere enkle eller sammensatte måltider til sig selv og andre som f.eks. at sammensætte et måltid, udvælge appetitlig mad og drikke, fremskaffe ingredienser til tilberedning af måltider; forberede mad og drikke til tilberedning, lave varm og kold mad og drikke, servere maden. Inkl.: tilberede enkle og sammensatte måltider. Ekskl.: spise; drikke; skaffe sig varer og tjenesteydelser […].\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx silåg til gryde, gigtvenlige redskaber, forhøjet skærebræt, Y-kartoffelskræller, elektrisk dåseåbner.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx at åbne/lukke emballage, stille ingredienser frem, skære brød, tage mad ud af fryseren.\n" + "2: Behov for nogen hjælp til fx at tilberede og anrette morgenmad og aftensmad og /eller opvarmning af middagsmad. Kan selv finde anrettet mad frem.\n" + "3: Behov for en hel del hjælp til at tilberede, anrette og servere alle måltider. Men kan fx deltage ved at smøre brød, når alt er sat frem, lave kaffe, når kaffemaskinen er gjort klar, eller skrælle frugt og grønt.\n" + "4: Har behov for hjælp til alt i forbindelse med at lave mad.\n";
        String laveHusligtArbejde = "Holde hus ved at gøre rent, vaske tøj, bruge husholdningsmaskiner, opbevare mad og smide affald ud, f.eks. ved at feje, moppe, tørre borde, […] holde orden i værelser og stuer, i skabe og skuffer; samle, vaske, tørre og stryge tøj; gøre fodtøj rent; bruge kost, børster og støvsuger; bruge vaskemaskine, tørretumbler og strygejern. Inkl.: tøjvask og tørring af tøj; rengøre kogested og køkkenredskaber; rengøring af opholdsrum; […] og bortskaffe affald. Ekskl.: skaffe sig bolig; skaffe sig varer og tjenesteydelser; lave mad; passe ejendele; hjælpe andre\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx moppesystem, vasketøjskurv på hjul, støvekost på langt skaft.\n" + "1:  Har behov for minimal hjælp til enkelte konkrete dele, fx en af følgende: støvsugning og/eller gulvvask, badeværelse, skift af sengelinned, håndtering af stort vasketøj, fjerne affald.\n" + "2: Har fx behov for nogen hjælp til støvsugning, og/eller gulvvask, badeværelse, tørre støv af, skifte og vaske sengelinned, hænge vasketøj op/tage vasketøj ned.\n" + "3:  Har behov for en hel del hjælp til de fleste rengøringsopgaver fx alt omkring tøjvask, opvask. Kan deltage i enkelte dele af det huslige arbejde, fx tørre støv af på tilgængelige flader, tørre opvask af, lægge tøj sammen.\n" + "4: Har behov for hjælp til alle delfunktioner i forbindelse med husligt arbejde.\n";
        //Mobilitet:
        String ændreKropsstilling = "Skifte kropsstilling og bevæge sig fra et sted til et andet som f.eks. at flytte sig fra en stol til liggende stilling og skifte til og fra knælende eller hugsiddende stilling. Inkl.: ændring af stilling fra liggende til stående, knælende til stående, stående til siddende, hugsiddende eller knælende, bøje sig og ændre kroppens vægtfordeling. Ekskl.: forflytte sig.\n" + "\n" + "0: Kan klare sig selv, fx ved brug af klodser under stoleben, rollator, elektrisk hovedgærde, toiletforhøjer, sengegalge.\n" + "1:  Har behov for minimal hjælp til enkelte konkrete dele, fx til at samle ting op fra gulvet, let støtte til at komme fra siddende til stående. Opsyn af sikkerhedsmæssige grunde fx ved skift mellem stol/seng.\n" + "2: Har behov for nogen hjælp, fx for at komme fra siddende til liggende og omvendt.\n" + "3: Har behov for en hel del hjælp. Kan fx deltage når der skal ændres kropsstilling, fx fra stående til siddende; og kan samarbejde/ undgå at modarbejde ændring i kropsstilling\n" + "4: Kan ikke bidrage til at ændre kropsstilling.\n";
        String forflytteSig = "Flytte sig fra en overflade til en anden som f.eks. at glide hen ad en bænk eller bevæge sig fra seng til stol uden at ændre kroppens stilling. Inkl.: flytte sig mens man sidder eller ligger.\n" + "\n" + "0: Kan klare sig selv, fx ved brug af elektrisk hovedgærde, sengegalge, glidebræt.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele ved forflytning i vandret plan, fx fra stol til toilet, evt. med hjælp fra glidebræt, placering af hjælpemidler eller mundtlig guidning\n" + "2: Behov for nogen hjælp, fx til komplicerede og/eller belastende forflytninger, både fysisk og mundtlig guidning. Kan fx selv dreje sig fra sideliggende til rygliggende, men ikke fra rygliggende til sideliggende.\n" + "3: Behov for en hel del hjælp, fx til at vende sig i seng og lejring. Kan fx samarbejde eller undgå at modarbejde forflytning og lejring\n" + "4: Kan ikke forflytte sig på egen hånd.\n";
        String løfteOgBære = "Løfte en genstand op og flytte noget fra et sted til et andet som f.eks. at løfte en kop eller bære et barn fra et rum til et andet. Inkl.: løfte, bære i hænderne eller på armene, på skuldrene, på hovedet, på ryggen og på hoften; sætte ting fra sig.\n" + "\n" + "0: Kan klare sig selv, fx ved brug af rullebord, rollator.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx vejledning om hensigtsmæssige måder at bære på, eller hjælp til løft af tunge genstande som vasketøjskurv, bakke med service til flere persone\n" + "2: Behov for nogen hjælp, fx til at løfte/flytte tungere eller uhåndterbare genstande i samme rum, som en bakke med enkelte glas/kopper.\n" + "3: Behov for en hel del hjælp, men kan fx selv flytte et glas fra skab til bord i samme rum.\n" + "4: Kan ikke selv løfte og bære.\n";
        String gå = "Bevæge sig til fods skridt for skridt på et underlag, således at den ene fod hele tiden hviler på underlaget, som når man slentrer, går forlæns, baglæns eller sidelæns. Inkl.: gå over korte og lange afstande; gå på forskelligt underlag; gå uden om forhindringer. Ekskl.: forflytte sig; bevæge sig omkring.\n" + "\n" + "0: Kan klare sig selv både inden- og udendørs, evt. ved brug af fx rollator eller stok.\n" + "1:  Behov for minimal hjælp til enkelte konkrete dele, for at kunne gå, fx tilrettelægge en passende gåtur og pauser, eller personledsagelse på længere ture pga. usikkerhed ift. balance.\n" + "2: Behov for nogen hjælp, fx til at gå udenom møbler og sikker brug af hjælpemiddel.\n" + "3: Behov for en hel del hjælp, men kan fx gå korte stræk ved fysisk eller mundtlig guidning og evt. hjælpemiddel.\n" + "4: Er ikke selv i stand til at gå.\n";
        String bevægeSigOmkring = "Bevæge sig fra et sted til et andet på andre måder end ved at gå […]. Inkl.: kravle, klatre, løbe, jogge, hoppe, springe og svømme, gå på trin/trappe/stige. Ekskl.: forflytte sig; gå.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af gelænder, stok, trappelift.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx en støttende hånd ved trappegang\n" + "2: Behov for nogen hjælp, fx guides til at placere fødderne korrekt ved trappegang.\n" + "3: Behov for en hel del hjælp, men kan fx anvende trappelift, hvis der er ledsageperson.\n" + "4: Har behov for hjælp til alt for at bevæge sig omkring.\n";
        String færdenIForskelligeOmgivelser = "Gang og færden i forskellige omgivelser som f.eks. at gå mellem rum i huset, inden for en bygning eller ned ad gaden. Inkl.: færdes i hjemmet, gå, kravle eller klatre i hjemmet, gå eller færdes i andre bygninger end hjemmet og uden for hjemmet og andre bygninger.\n" + "\n" + "0: Kan klare sig selv både indendørs og udendørs, fx ved brug af en stok, rollator eller kørestol.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, har måske brug for små pauser undervejs, eller vejledning ved færden indenog udenfor i ukendte omgivelser.\n" + "2: Behov for nogen hjælp, fx til at færdes udendørs på kendt terræn, komme til og fra et rum i boligen til et andet (fx til og fra badeværelset), gå over dørtrin.\n" + "3: Behov for en hel del hjælp, men kan fx færdes i et enkelt rum i boligen.\n" + "4: Kan ikke færdes på egen hånd i forskellige omgivelser, hverken ude eller inde.\n";
        String brugeTransportmidler = "Bruge transportmidler som passager til at færdes omkring som f.eks. at blive kørt i en bil eller køre med […] taxi, bus, tog, sporvogn, undergrundsbane, skib eller fly. Inkl.: bruge transportmiddel, der trækkes af en person; anvendelse af private motoriserede eller offentlige transportmidler. Ekskl.: færden med brug af udstyr; køre.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx stol eller rollator.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til at komme ind og ud af en bil, købe billet, få rollator op i bussen, blive mindet om at være klar til afhentning.\n" + "2: Behov for nogen hjælp, fx til at anvende servicebusser, komme ind og ud af kollektiv transport.\n" + "3: Behov for en hel del hjælp, men kan fx hjælpe med til at planlægge og skaffe sig hjælp\n" + "4: Har behov for hjælp til alt i forbindelse med brug af transportmidler\n";
        String udholdenhed = "Funktioner bestemmende for respiratorisk og kardiovaskulær kapacitet, som er nødvendig ved fysisk anstrengelse. Inkl.: fysisk udholdenhed, aerob kapacitet, udtrætning. Ekskl.: kardiovaskulære funktioner; hæmatologiske funktioner; respiration; respirationsmuskulaturens funktioner; andre respiratoriske funktioner.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af fx ilt.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til at disponere kræfterne ved at strukturere de daglige aktiviteter over døgnet/ugen.\n" + "2: Behov for nogen hjælp til specifikke aktiviteter, fx bad, støvsugning, bære og flytte tunge genstande\n" + "3: Behov for en hel del hjælp, men kan fx vaske/tørre dele af kroppen, deltage i stillesiddende aktiviteter eller lette praktiske opgaver, som sidde ned og tørre lette ting af.\n" + "4: Magter ikke fysiske anstrengelser.\n";
        String muskelstyrke = "Kraften som opstår ved kontraktion af en muskel eller en muskelgruppe. Inkl.: styrken i specifikke muskler og muskelgrupper, muskler i én ekstremitet, i den ene halvdel af kroppen, i underkrop, i alle ekstremiteter, i trunkus og i alle muskler i kroppen; tilstande som nedsat kraft i små muskler i fødder og hænder, parese, paralyse, monoplegi, hemiplegi, paraplegi, tetraplegi, akinetisk mutisme. Ekskl.: muskeltonus; muskulær udholdenhed; funktioner af strukturer i og omkring øjet.\n" + "\n" + "0: Kan klare sig selv, evt. ved brug af håndleds- eller benskinne.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele fx let fysisk støtte eller guidning til at løfte og bevæge fx en hånd eller en fod, i forbindelse med en enkelt, specifik aktivitet.\n" + "2: Behov for nogen hjælp til fx at løfte, holde eller bevæge en arm eller et ben i forbindelse med en eller flere aktiviteter.\n" + "3: Behov for en hel del hjælp fx til at aktivere den ene halvdel af kroppen\n" + "4: Har ingen muskelkraft i hele eller dele af kroppen\n";
        //Mentale Funktioner:
        String tilegneSigFærdigheder = "Udvikle basale og komplekse kompetencer i sammensatte handlinger eller opgaver med det formål at påbegynde og gennemføre erhvervelsen af en færdighed, som f.eks. håndtering af værktøj eller spil som skak. Inkl.: tilegnelse af basale og komplekse færdigheder\n" + "0: Kan selv tilegne sig nye færdigheder.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx oplæring eller vejledning i at anvende nye hjælpemidler som vaske-tørretoilet, GPS, skærmbesøg, telemedicin eller synshjælpemidler.\n" + "2: Behov for nogen hjælp, fx oplæring i basale færdigheder i dagligdagen. Fx i rækkefølgen ved brug af redskaber og maskiner som støvsuger, mikroovn, ny vaskemaskine, ny strømpepåtager.\n" + "3: Behov for en hel del hjælp, men kan fx efter fysisk eller mundtlig guidning tilegne sig basale færdigheder i få konkrete og velkendte situationer som at bruge kam, tandbørste eller gaffel hensigtsmæssigt, eller tage tøj på i korrekt rækkefølge.\n" + "4: Formår ikke at tilegne sig basale færdigheder.\n";
        String problemløsning = "Løsning af spørgsmål eller situationer ved at identificere og analysere emner, udvikle muligheder og løsninger, evaluere mulige virkninger af løsninger og gennemføre en valgt løsning som f.eks. ved løsning af en uoverensstemmelse mellem to personer. Inkl.: løse enkle og komplekse problemer. Ekskl.: tænke; tage beslutninger.\n" + "0: Intet behov for hjælp til at løse evt. problemer.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til at justere kendt medicin ift. ny ordination, eller ændring i navn på kendt medicin. Eller at overskue og forholde sig til situationer, hvor borgeren ikke kan trække på tidligere erfaringer, fx håndtere at en dement nabo aflægger uønskede besøg.\n" + "2: Behov for nogen hjælp, fx til at overskue nye udfordringer i hverdagen, vurdere muligheder og konsekvenser. Fx forholde sig til en forestående flytning\n" + "3: Behov for en hel del hjælp, men kan med guidning løse enkle spørgsmål og situationer i hverdagen, fx vælge pålæg i køleskabet, smøre maden og sætte pålæg tilbage på køl; vælge påklædning, der passer til vejret\n" + "4: Kan ikke løse hverdagens kendte problemer og situationer.\n";
        String anvendeKommunikationsudstyrOgTeknikker = "Anvende udstyr, teknikker og andre midler med kommunikationsformål som f.eks. at ringe til en ven. Inkl.: brug af telekommunikationsudstyr, skriveudstyr og kommunikationsteknologi.\n" + "0: Kan klare sig selv, fx ved hjælp af pegeplade, PC med tale, tegn til tale.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til at opsætte/ klargøre telefon, pegeplade, PC, tablet.\n" + "2: Behov for nogen hjælp, fx til betjening af telefon, pegeplade, PC, tablet.\n" + "3: Behov for en hel del hjælp, men kan fx benytte telefon med indkodede numre, betjene nødkald, guides ved brug af pegeplade.\n" + "4: Kan ikke selv bruge kommunikationsudstyr og - teknikker, og fx ikke tilkalde hjælp.\n";
        String orienteringsevne = "Overordnede mentale funktioner bestemmende for kendskab til og konstatering af relationerne til en selv, til andre, til tid, sted og andre omgivelser. Inkl.: orientering i tid, sted og egne data, orientering om sig selv og andre, desorientering i tid, sted og egne data. Ekskl.: bevidsthedstilstand, opmærksomhed, hukommelse.\n" + "0: Intet behov for hjælp til at håndtere evt. nedsat nedsat orienteringsevne.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx lettere vejledning i tid, finde rundt i ukendte omgivelser.\n" + "2: Behov for nogen hjælp, fx blive mindet om tidspunktet for en aktivitet, blive fulgt på vej eller mindet om relationer.\n" + "3: Behov for en hel del hjælp, men kan fx bekræfte oplysninger om egne data og steder, evt. efter guidning.\n" + "4: Er ikke i stand til at orientere sig hverken i forhold til tid, sted eller sig selv.\n";
        String energiOgHandlekraft = "Overordnede mentale funktioner af fysiologisk og psykologisk art, som får personen til at opnå tilfredsstillelse af specifikke behov og overordnede mål på en vedholdende måde. Inkl.: energiniveau, motivation, appetit, trang (inkl. til stoffer, som kan misbruges) og impulskontrol. Ekskl.: bevidsthedstilstand, temperament og personlighed, søvn, psykomotoriske funktioner; følelsesfunktioner.\n" + "0: Intet behov for hjælp til at håndtere evt. nedsat energi og handlekraft.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx blive mindet om behov, ønsker og beslutninger om at udføre opgaver, som man fysisk magter, fx træne efter vejledning. Fastholde strategi for stop af misbrug.\n" + "2: Behov for nogen hjælp, fx opmuntring i hverdagen for at udføre de fysiske opgaver, det er muligt. Vejledning i strategier for at kontrollere impulser til fx misbrug.\n" + "3: Behov for en hel del hjælp, men kan fx. under guidning fastholde konkrete og afgrænsede aktiviteter som klæde sig på, spise færdigt, eller kontrollere misbrug.\n" + "4: Formår ikke at handle hensigtsmæssigt for at få opfyldt sine behov, eller kontrollere misbrug.\n";
        String hukommelse = "Specifikke mentale funktioner bestemmende for registrering, lagring genkaldelse af information efter behov. Inkl.: korttids- og langtidshukommelse, umiddelbar erindring, […]; amnesi. Ekskl.: bevidsthedstilstand, orientering, intellektuelle funktioner, opmærksomhed; opfattelse; tænkning, overordnede kognitive funktioner, særlige sprogfunktioner; regnefunktioner.\n" + "0: Ingen problemer med at huske, bruger evt. kalender som støtte.\n" + "1: Kan stort set huske det væsentlige, har behov for minimal hjælp til enkelte konkrete dele, fx til at huske særlige og ikketilbagevendende aftaler.\n" + "2: Behov for nogen hjælp, fx mundtlig guidning til dagens opgaver og aftaler, mindes om medicin.\n" + "3: Behov for en hel del hjælp, men kan fx. huske daglige rutiner og nære personer, evt. vha. fysisk og mundtlig guidning.\n" + "4: Formår ikke at huske, men kan evt. genkalde automatiserede bevægelser og sange via stimuli.\n";
        String følelsesfunktioner = "Specifikke mentale funktioner forbundet med følelser og affektive komponenter i sindet. Inkl.: funktioner bestemmende for fyldestgørende følelser, regulering og spændvidde af følelserne; affekt; tristhed, lykkefølelse, kærlighed, frygt, vrede, had, anspændthed, angst, glæde, sorg; emotionel labilitet; følelsesaffladning. Ekskl.: temperament og personlighed, energi og handlekraft.\n" + "\n" + "0: Kan selv håndtere sine følelser.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx til at mindes, reflektere over og håndtere en konkret følelse, fx ved ensomhed, nærtståendes sygdom eller nylige død. Støtte til at genoptage samvær, der giver fællesskab og glæde, fx frivillige klubber efter tab af ægtefælle.\n" + "2: Behov for nogen hjælp, fx til at handle på behov for hjælp ved længerevarende følelser af angst, tristhed. Regulere og stabilisere følelser i kontakt med andre mennesker, fx for at undgå vredesudbrud.\n" + "3: Behov for en hel del hjælp, men kan fx give udtryk for at være følelsesmæssigt overbelastet, og under mundtlig guidning stabilisere følelser og fornemme andre mennesker og situationer\n" + "4: Kan ikke regulere følelser i forskellige situationer.\n";
        String overordnedeKognitiveFunktioner = "Specifikke mentale funktioner først og fremmest knyttet til hjernens pandelapper omfattende kompleks målrettet adfærd som beslutningstagning, abstrakt tænkning, planlægning og gennemførelse af planer, mental fleksibilitet og tilpasning af adfærden efter omstændighederne, såkaldte eksekutive funktioner. Inkl.: abstraktion og organisering af ideer; administration af tid, indsigt og dømmekraft, begrebsdannelse, kategorisering, kognitiv fleksibilitet. Ekskl.: hukommelse, tænkning, sprogfunktioner, regnefunktioner.\n" + "0: Kan klare sig selv.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele. Fx få vejledning i forhold til egen adfærd, planlægning eller dømmekraft ved frustration over en misset aftale; støtte til at omstille sig til nyt, fx ny kontaktperson.\n" + "2: Behov for nogen hjælp, fx støttes i at undgå forvirring og sættes i gang, hvis der pludselig opstår en ny situation, der ikke er en del af den planlagte og strukturerede hverdag, fx i tilfælde af tab af ægtefælle eller nyopstået sygdom.\n" + "3: Behov for en hel del hjælp, men kan fx via mundtlig guidning deltage i kendte aktiviteter, og agere og træffe ukomplicerede valg og beslutninger.\n" + "4: Kan ikke forstå egen og andres adfærd og træffe hensigtsmæssige beslutninger, har evt. behov for skærmning eller afledning.\n";
        //Samfundsliv:
        String haveLønnetBeskæftigelse = "Deltage i alle aspekter af et arbejde, erhverv eller anden form for beskæftigelse […]. Inkl.: deltids- og fuldtidsbeskæftigelse.\n" + "0: Kan klare sig selv.\n" + "1: Behov for minimal hjælp til enkelte konkrete dele, fx for at møde til tiden på arbejde.\n" + "2: Behov for nogen hjælp, fx til at udarbejde/ følge en struktur på arbejdet ift. arbejdsopgaverne.\n" + "3: Behov for en hel del hjælp, men kan fx selv helt eller delvist udføre konkrete, kendte og afgrænsede opgaver.\n" + "4: Er ikke i stand til at have lønnet arbejde.\n";

        if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Vaske sig")){
            contextTextArea.setText(vaskeSig);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Gå på toilet")){
            contextTextArea.setText(gåPåToilet);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Kropspleje")){
            contextTextArea.setText(kropspleje);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Af- og påklædning")){
            contextTextArea.setText(afOgPåklædning);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Spise")){
            contextTextArea.setText(spise);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Drikke")){
            contextTextArea.setText(drikke);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Varetage egen sundhed")){
            contextTextArea.setText(varetageEgenSundhed);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Fødeindtagelse")){
            contextTextArea.setText(fødeindtagelse);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Udføre daglige rutiner")){
            contextTextArea.setText(udføreDagligeRutiner);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Skaffe sig varer og tjenesteydelser")){
            contextTextArea.setText(skaffeSigVarerOgTjenesteydelser);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Lave mad")){
            contextTextArea.setText(laveMad);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Lave husligt arbejde")){
            contextTextArea.setText(laveHusligtArbejde);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Ændre kropstilling")){
            contextTextArea.setText(ændreKropsstilling);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Forflytte sig")){
            contextTextArea.setText(forflytteSig);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Løfte og bære")){
            contextTextArea.setText(løfteOgBære);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Gå")){
            contextTextArea.setText(gå);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Bevæge sig omkring")){
            contextTextArea.setText(bevægeSigOmkring);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Færden is forskellige omgivelser")){
            contextTextArea.setText(færdenIForskelligeOmgivelser);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Bruge transportmidler")){
            contextTextArea.setText(brugeTransportmidler);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Udholdenhed")){
            contextTextArea.setText(udholdenhed);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Muskelstyrke")){
            contextTextArea.setText(muskelstyrke);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Tilegne sig færdigheder")){
            contextTextArea.setText(tilegneSigFærdigheder);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Problemløsning")){
            contextTextArea.setText(problemløsning);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Anvende kommunikationsudstyr og-teknikker")){
            contextTextArea.setText(anvendeKommunikationsudstyrOgTeknikker);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Orienteringsevne")){
            contextTextArea.setText(orienteringsevne);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Energi og handlekraft")){
            contextTextArea.setText(energiOgHandlekraft);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Hukommelse")){
            contextTextArea.setText(hukommelse);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Følelsesfunktioner")){
            contextTextArea.setText(følelsesfunktioner);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Overordnede kognitive funktioner")){
            contextTextArea.setText(overordnedeKognitiveFunktioner);

        }else if (cbSubCat.getSelectionModel().getSelectedItem().getSubCatName().contains("Have lønnet beskæftigelse")){
            contextTextArea.setText(haveLønnetBeskæftigelse);
        }

    }

    public void handleContextMenuExited(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(0);
        contextTextArea.setDisable(true);
    }

    public void handleContextTextAreaEntered(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(100);
        contextTextArea.setDisable(false);
    }

    public void handleContextTextAreaExited(MouseEvent mouseEvent) {
        contextTextArea.setOpacity(0);
        contextTextArea.setDisable(true);
    }

    public void handleContextMenuEnteredFunc(MouseEvent mouseEvent) {
        contextTextAreaFunc.setOpacity(100);
        contextTextAreaFunc.setDisable(false);

        String func = "0: Inden eller ubetydelige begrænsninger: Borgeren er selvstændig og har ikke behov for personassistance til at udfører aktiviteten.\n" + "1: lette begrænsninger: Borgeren er den aktive part og kan med let personassistance udføre aktiviteten.\n" + "2: Moderate begrænsninger: Borgeren er den aktive part og kan under forudsætning af moderat personassistance udfører aktiviteten.\n" + "3: Svære begrænsninger: Borgeren deltager og kan under forudsætning af omfattende personassistance udføre aktiviteten.\n" + "4: Totale begrænsninger: Borgeren er ude af stand til at udføre aktiviteten og har brug for fuldstændig personassistance for at udføre aktiviteten. \n" + "9: Ikke relevant: 9-tallet anvendes der, hvor man vurderer, at det ikke er relevant at registrere en vurdering for borgerens funktionsevne. Det viser andre kollegaer og samarbejdspartnere, at der er taget aktivt stilling til området, og at den manglende vurdering ikke skyldes en forglemmelse.\n";
        contextTextAreaFunc.setText(func);
    }

    public void handleContextMenuExitedFunc(MouseEvent mouseEvent) {
        contextTextAreaFunc.setOpacity(0);
        contextTextAreaFunc.setDisable(true);
    }

    public void handleContextTextAreaEnteredFunc(MouseEvent mouseEvent) {
        contextTextAreaFunc.setOpacity(100);
        contextTextAreaFunc.setDisable(false);
    }

    public void handleContextTextAreaExitedFunc(MouseEvent mouseEvent) {
        contextTextAreaFunc.setOpacity(0);
        contextTextAreaFunc.setDisable(true);
    }
}
