import org.controlsfx.control.RangeSlider;

import com.jfoenix.controls.*;
import eu.hansolo.applefx.*;
import javafx.application.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.embed.swing.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import javanoise.noise.*;
import javanoise.noise.fractal.*;

public class App extends Application {
    private static AnchorPane background = new AnchorPane();
    private static ImageView image = new ImageView(SwingFXUtils.toFXImage(NoiseMapGenerator.generate(new Simplex(1337, 0.1, new FBM(4, 2.00, 0.5, 0.0f)), 128, 128), null));

    private static MacosLabel titleLabel = new MacosLabel("Noisemap Generator");
    private static JFXButton previousButton = new JFXButton();
    private static JFXButton nextButton = new JFXButton();

    private static MacosLabel noiseFunctionLabel = new MacosLabel("Noise Function");
    private static MacosComboBox<String> noiseFunctionComboBox = new MacosComboBox<>(FXCollections.observableArrayList("Gaussian", "Value", "Cellular", "Perlin", "Simplex"));
    
    private static MacosLabel fractalModifierLabel = new MacosLabel("Fractal Modifier");
    private static MacosComboBox<String> fractalModifierComboBox = new MacosComboBox<>(FXCollections.observableArrayList("None", "FBM", "Billow", "RMF", "Ping Pong"));

    private static MacosLabel seedLabel = new MacosLabel("Seed");
    private static MacosTextField seedTextField = new MacosTextField();
    private static MacosButton seedButton = new MacosButton("Set Seed");

    private static MacosLabel frequencyLabel = new MacosLabel("Frequency: 0.1");
    private static MacosSlider frequencySlider = new MacosSlider(0.0, 0.25, 0.1);

    private static MacosLabel rngTypeLabel = new MacosLabel("RNG Type");
    private static MacosComboBox<String> rngTypeComboBox = new MacosComboBox<>(FXCollections.observableArrayList("LCG", "XORShift", "CBSquares"));

    private static MacosLabel valueInterpolationTypeLabel = new MacosLabel("Interpolation Type");
    private static MacosComboBox<String> valueInterpolationTypeComboBox = new MacosComboBox<>(FXCollections.observableArrayList("Linear", "Hermite", "Trigonometric", "Logistic"));

    private static MacosLabel distanceFunctionLabel = new MacosLabel("Distance Function");
    private static MacosComboBox<String> distanceFunctionComboBox = new MacosComboBox<>(FXCollections.observableArrayList("Euclidean", "Manhattan", "Hybrid"));

    private static MacosLabel jitterModifierLabel = new MacosLabel("Jitter Modifier: 0.025");
    private static MacosSlider jitterModifierSlider = new MacosSlider(0.0, 1.0, 0.5);

    private static MacosLabel returnTypeLabel = new MacosLabel("Return Type");
    private static MacosComboBox<String> returnTypeComboBox = new MacosComboBox<>(FXCollections.observableArrayList("Cell Value", "Distance", "Alternate Distance", "Distance Sum", "Distance Difference", "Distance Product", "Distance Quotient", "Distance Squared"));

    private static MacosLabel perlinInterpolationTypeLabel = new MacosLabel("Interpolation Type");
    private static MacosComboBox<String> perlinInterpolationTypeComboBox = new MacosComboBox<>(FXCollections.observableArrayList("Linear", "Hermite", "Trigonometric", "Logistic"));

    private static MacosLabel octavesLabel = new MacosLabel("Octaves: 4");
    private static MacosSlider octavesSlider = new MacosSlider(0.0, 3.0, 3.0);

    private static MacosLabel lacunarityLabel = new MacosLabel("Lacunarity: 2.00");
    private static MacosSlider lacunaritySlider = new MacosSlider(0.0, 3.0, 2.0);

    private static MacosLabel persistenceLabel = new MacosLabel("Persistence: 0.5");
    private static MacosSlider persistenceSlider = new MacosSlider(0.0, 1.0, 0.5);

    private static MacosLabel fractalStrengthLabel = new MacosLabel("Fractal Strength: 0.0");
    private static MacosSlider fractalStrengthSlider = new MacosSlider(0.0, 7.5, 2.5);

    private static MacosLabel pingPongStrengthLabel = new MacosLabel("Ping Pong Strength: 0.5");
    private static MacosSlider pingPongStrengthSlider = new MacosSlider(0.0, 2.0, 0.0);

    private static MacosLabel colorfulModeLabel = new MacosLabel("Colorful Mode: Off");
    private static MacosSwitch colorfulModeToggle = new MacosSwitch();

    private static MacosLabel toleranceLabel = new MacosLabel("Tolerance: -1.0 to 1.0");
    private static RangeSlider toleranceSlider = new RangeSlider(-1.0, 1.0, -1.0, 1.0);

    private static MacosLabel levelsLabel = new MacosLabel("Levels: 1");
    private static MacosSlider levelsSlider = new MacosSlider(0.0, 7.0, 0.0);

    private static int mode = 0;

    @Override
    public void start(Stage stage) throws Exception {
        // create root node
        AnchorPane root = new AnchorPane();

        // set element properties
        root.setPrefHeight(1002);
        root.setPrefWidth(527);

        background.setLayoutX(25);
        background.setLayoutY(25);
        background.setPrefHeight(952);
        background.setPrefWidth(477);
        background.setStyle("-fx-background-color: #D8D8D8; -fx-background-radius: 25; -fx-border-radius: 25;");
        
        image.setLayoutX(25);
        image.setLayoutY(500);
        image.setFitHeight(427);
        image.setFitWidth(427);

        titleLabel.setLayoutX(90.2);
        titleLabel.setLayoutY(38.7);
        titleLabel.setPrefHeight(22.6);
        titleLabel.setPrefWidth(296.6);
        titleLabel.alignmentProperty().set(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 18px;");

        previousButton.setLayoutX(25);
        previousButton.setLayoutY(25);
        previousButton.setPrefHeight(50);
        previousButton.setPrefWidth(50);
        previousButton.setStyle("-fx-shape: \"M33.721,31.01L33.721,44.602L5.14,23.691L33.721,2.779L33.721,16.372L62.303,16.372L62.303,31.01L33.721,31.01Z\"; -fx-background-color: #037aff;");

        previousButton.setOnAction(e -> {
            mode = mode == 0 ? 2 : mode - 1;
            switch (mode) {
                case 0:
                    titleLabel.setText("Noise Map Generator");
                    colorfulModeLabel.setVisible(true);
                    colorfulModeToggle.setVisible(true);
                    toleranceLabel.setVisible(false);
                    toleranceSlider.setVisible(false);
                    levelsLabel.setVisible(false);
                    levelsSlider.setVisible(false);
                    break;
                case 1:
                    titleLabel.setText("Tolerance Map Generator");
                    colorfulModeLabel.setVisible(false);
                    colorfulModeToggle.setVisible(false);
                    toleranceLabel.setVisible(true);
                    toleranceSlider.setVisible(true);
                    levelsLabel.setVisible(false);
                    levelsSlider.setVisible(false);
                    break;
                case 2:
                    titleLabel.setText("Level Map Generator");
                    colorfulModeLabel.setVisible(true);
                    colorfulModeToggle.setVisible(true);
                    toleranceLabel.setVisible(false);
                    toleranceSlider.setVisible(false);
                    levelsLabel.setVisible(true);
                    levelsSlider.setVisible(true);
                    break;
            }
            regenerate();
        });

        nextButton.setLayoutX(402);
        nextButton.setLayoutY(25);
        nextButton.setPrefHeight(50);
        nextButton.setPrefWidth(50);
        nextButton.setStyle("-fx-shape: \"M1009.3,255.523L1009.3,238.24L1068.48,238.24L1068.48,222.192L1127.67,246.881L1068.48,271.571L1068.48,255.523L1009.3,255.523Z\"; -fx-background-color: #037aff;");
        nextButton.setOnAction(e -> {
            mode = mode == 2 ? 0 : mode + 1;
            switch (mode) {
                case 0:
                    titleLabel.setText("Noise Map Generator");
                    colorfulModeLabel.setVisible(true);
                    colorfulModeToggle.setVisible(true);
                    toleranceLabel.setVisible(false);
                    toleranceSlider.setVisible(false);
                    levelsLabel.setVisible(false);
                    levelsSlider.setVisible(false);
                    break;
                case 1:
                    titleLabel.setText("Tolerance Map Generator");
                    colorfulModeLabel.setVisible(false);
                    colorfulModeToggle.setVisible(false);
                    toleranceLabel.setVisible(true);
                    toleranceSlider.setVisible(true);
                    levelsLabel.setVisible(false);
                    levelsSlider.setVisible(false);
                    break;
                case 2:
                    titleLabel.setText("Level Map Generator");
                    colorfulModeLabel.setVisible(true);
                    colorfulModeToggle.setVisible(true);
                    toleranceLabel.setVisible(false);
                    toleranceSlider.setVisible(false);
                    levelsLabel.setVisible(true);
                    levelsSlider.setVisible(true);
                    break;
            }
            regenerate();
        });

        noiseFunctionLabel.setLayoutX(84.5);
        noiseFunctionLabel.setLayoutY(100);
        noiseFunctionLabel.setPrefHeight(9.2);
        noiseFunctionLabel.setPrefWidth(82.7);
        noiseFunctionLabel.setStyle("-fx-font-size: 12px;");

        noiseFunctionComboBox.setLayoutX(79.5);
        noiseFunctionComboBox.setLayoutY(116);
        noiseFunctionComboBox.setPrefHeight(20);
        noiseFunctionComboBox.setPrefWidth(125);
        noiseFunctionComboBox.getSelectionModel().select(4);
        noiseFunctionComboBox.setDark(false);
        noiseFunctionComboBox.setOnAction(e -> {
            switch (noiseFunctionComboBox.getSelectionModel().getSelectedIndex()) {
                case 0:
                    rngTypeLabel.setVisible(true);
                    rngTypeComboBox.setVisible(true);
                    valueInterpolationTypeLabel.setVisible(false);
                    valueInterpolationTypeComboBox.setVisible(false);
                    distanceFunctionLabel.setVisible(false);
                    distanceFunctionComboBox.setVisible(false);
                    jitterModifierLabel.setVisible(false);
                    jitterModifierSlider.setVisible(false);
                    returnTypeLabel.setVisible(false);
                    returnTypeComboBox.setVisible(false);
                    perlinInterpolationTypeLabel.setVisible(false);
                    perlinInterpolationTypeComboBox.setVisible(false);
                    break;
                case 1:
                    rngTypeLabel.setVisible(false);
                    rngTypeComboBox.setVisible(false);
                    valueInterpolationTypeLabel.setVisible(true);
                    valueInterpolationTypeComboBox.setVisible(true);
                    distanceFunctionLabel.setVisible(false);
                    distanceFunctionComboBox.setVisible(false);
                    jitterModifierLabel.setVisible(false);
                    jitterModifierSlider.setVisible(false);
                    returnTypeLabel.setVisible(false);
                    returnTypeComboBox.setVisible(false);
                    perlinInterpolationTypeLabel.setVisible(false);
                    perlinInterpolationTypeComboBox.setVisible(false);
                    break;
                case 2:
                    rngTypeLabel.setVisible(false);
                    rngTypeComboBox.setVisible(false);
                    valueInterpolationTypeLabel.setVisible(false);
                    valueInterpolationTypeComboBox.setVisible(false);
                    distanceFunctionLabel.setVisible(true);
                    distanceFunctionComboBox.setVisible(true);
                    jitterModifierLabel.setVisible(true);
                    jitterModifierSlider.setVisible(true);
                    returnTypeLabel.setVisible(true);
                    returnTypeComboBox.setVisible(true);
                    perlinInterpolationTypeLabel.setVisible(false);
                    perlinInterpolationTypeComboBox.setVisible(false);
                    break;
                case 3:
                    rngTypeLabel.setVisible(false);
                    rngTypeComboBox.setVisible(false);
                    valueInterpolationTypeLabel.setVisible(false);
                    valueInterpolationTypeComboBox.setVisible(false);
                    distanceFunctionLabel.setVisible(false);
                    distanceFunctionComboBox.setVisible(false);
                    jitterModifierLabel.setVisible(false);
                    jitterModifierSlider.setVisible(false);
                    returnTypeLabel.setVisible(false);
                    returnTypeComboBox.setVisible(false);
                    perlinInterpolationTypeLabel.setVisible(true);
                    perlinInterpolationTypeComboBox.setVisible(true);
                    break;
                case 4:
                    rngTypeLabel.setVisible(false);
                    rngTypeComboBox.setVisible(false);
                    valueInterpolationTypeLabel.setVisible(false);
                    valueInterpolationTypeComboBox.setVisible(false);
                    distanceFunctionLabel.setVisible(false);
                    distanceFunctionComboBox.setVisible(false);
                    jitterModifierLabel.setVisible(false);
                    jitterModifierSlider.setVisible(false);
                    returnTypeLabel.setVisible(false);
                    returnTypeComboBox.setVisible(false);
                    perlinInterpolationTypeLabel.setVisible(false);
                    perlinInterpolationTypeComboBox.setVisible(false);
                    break;
            }
            regenerate();
        });

        fractalModifierLabel.setLayoutX(262.4);
        fractalModifierLabel.setLayoutY(100);
        fractalModifierLabel.setPrefHeight(9.2);
        fractalModifierLabel.setPrefWidth(88.8);
        fractalModifierLabel.setStyle("-fx-font-size: 12px;");

        fractalModifierComboBox.setLayoutX(257.4);
        fractalModifierComboBox.setLayoutY(116);
        fractalModifierComboBox.setPrefHeight(20);
        fractalModifierComboBox.setPrefWidth(125);
        fractalModifierComboBox.getSelectionModel().select(1);
        fractalModifierComboBox.setDark(false);
        fractalModifierComboBox.setOnAction(e -> {
            switch (fractalModifierComboBox.getSelectionModel().getSelectedIndex()) {
                case 0:
                    octavesLabel.setVisible(false);
                    octavesSlider.setVisible(false);
                    lacunarityLabel.setVisible(false);
                    lacunaritySlider.setVisible(false);
                    persistenceLabel.setVisible(false);
                    persistenceSlider.setVisible(false);
                    fractalStrengthLabel.setVisible(false);
                    fractalStrengthSlider.setVisible(false);
                    pingPongStrengthLabel.setVisible(false);
                    pingPongStrengthSlider.setVisible(false);
                    break;
                case 1:
                case 2:
                case 3:
                    octavesLabel.setVisible(true);
                    octavesSlider.setVisible(true);
                    lacunarityLabel.setVisible(true);
                    lacunaritySlider.setVisible(true);
                    persistenceLabel.setVisible(true);
                    persistenceSlider.setVisible(true);
                    fractalStrengthLabel.setVisible(true);
                    fractalStrengthSlider.setVisible(true);
                    pingPongStrengthLabel.setVisible(false);
                    pingPongStrengthSlider.setVisible(false);
                    break;
                case 4:
                    octavesLabel.setVisible(true);
                    octavesSlider.setVisible(true);
                    lacunarityLabel.setVisible(true);
                    lacunaritySlider.setVisible(true);
                    persistenceLabel.setVisible(true);
                    persistenceSlider.setVisible(true);
                    fractalStrengthLabel.setVisible(true);
                    fractalStrengthSlider.setVisible(true);
                    pingPongStrengthLabel.setVisible(true);
                    pingPongStrengthSlider.setVisible(true);
                    break;
            }
            regenerate();
        });

        seedLabel.setLayoutX(27.2);
        seedLabel.setLayoutY(160);
        seedLabel.setPrefHeight(9.2);
        seedLabel.setPrefWidth(29.4);
        seedLabel.setStyle("-fx-font-size: 12px;");

        seedTextField.setLayoutX(25);
        seedTextField.setLayoutY(175.2);
        seedTextField.setPrefHeight(20);
        seedTextField.setPrefWidth(166);
        seedTextField.setDark(false);
        seedTextField.setText("1337");

        seedButton.setLayoutX(194.4);
        seedButton.setLayoutY(178.2);
        seedButton.setPrefHeight(20);
        seedButton.setPrefWidth(30);
        seedButton.setDark(false);
        seedButton.setText("Set");
        seedButton.setStyle("-fx-font-size: 7px;");
        seedButton.setOnAction(e -> {
            // check if the seed is a valid integer
            try {
                Integer.parseInt(seedTextField.getText());
            } catch (NumberFormatException ex) {
                // set the seed to a random integer
                long sum = (long) Integer.MAX_VALUE - Integer.MIN_VALUE;
                long randomNumber = (long) (Math.random() * sum) + Integer.MIN_VALUE;
                seedTextField.setText("" + (int) randomNumber);
                return;
            }
            regenerate();
        });

        frequencyLabel.setLayoutX(251);
        frequencyLabel.setLayoutY(163.7);
        frequencyLabel.setPrefHeight(9.2);
        frequencyLabel.setPrefWidth(96.7);
        frequencyLabel.setStyle("-fx-font-size: 12px;");

        frequencySlider.setLayoutX(251.3);
        frequencySlider.setLayoutY(179.7);
        frequencySlider.setPrefHeight(11.9);
        frequencySlider.setPrefWidth(200.7);
        frequencySlider.setDark(false);
        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 40) / 40.0; // round to the nearest 0.025
            frequencySlider.setValue(newVal.doubleValue());
            frequencyLabel.setText("Frequency: " + newVal.doubleValue());
        });
        frequencySlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        rngTypeLabel.setLayoutX(179.4);
        rngTypeLabel.setLayoutY(220);
        rngTypeLabel.setPrefHeight(10.9);
        rngTypeLabel.setPrefWidth(55.6);
        rngTypeLabel.setStyle("-fx-font-size: 12px;");
        rngTypeLabel.setVisible(false);

        rngTypeComboBox.setLayoutX(176);
        rngTypeComboBox.setLayoutY(236);
        rngTypeComboBox.setPrefHeight(20);
        rngTypeComboBox.setPrefWidth(125);
        rngTypeComboBox.getSelectionModel().select(0);
        rngTypeComboBox.setDark(false);
        rngTypeComboBox.setVisible(false);
        rngTypeComboBox.setOnAction(e -> {
            regenerate();
        });

        valueInterpolationTypeLabel.setLayoutX(179.3);
        valueInterpolationTypeLabel.setLayoutY(220);
        valueInterpolationTypeLabel.setPrefHeight(10.9);
        valueInterpolationTypeLabel.setPrefWidth(103);
        valueInterpolationTypeLabel.setStyle("-fx-font-size: 9px;");
        valueInterpolationTypeLabel.setVisible(false);

        valueInterpolationTypeComboBox.setLayoutX(176);
        valueInterpolationTypeComboBox.setLayoutY(236);
        valueInterpolationTypeComboBox.setPrefHeight(20);
        valueInterpolationTypeComboBox.setPrefWidth(125);
        valueInterpolationTypeComboBox.getSelectionModel().select(0);
        valueInterpolationTypeComboBox.setDark(false);
        valueInterpolationTypeComboBox.setVisible(false);
        valueInterpolationTypeComboBox.setOnAction(e -> {
            regenerate();
        });

        distanceFunctionLabel.setLayoutX(29.1);
        distanceFunctionLabel.setLayoutY(221);
        distanceFunctionLabel.setPrefHeight(9.2);
        distanceFunctionLabel.setPrefWidth(108.3);
        distanceFunctionLabel.setStyle("-fx-font-size: 12px;");
        distanceFunctionLabel.setVisible(false);

        distanceFunctionComboBox.setLayoutX(25);
        distanceFunctionComboBox.setLayoutY(236);
        distanceFunctionComboBox.setPrefHeight(20);
        distanceFunctionComboBox.setPrefWidth(125);
        distanceFunctionComboBox.getSelectionModel().select(0);
        distanceFunctionComboBox.setDark(false);
        distanceFunctionComboBox.setVisible(false);
        distanceFunctionComboBox.setOnAction(e -> {
            regenerate();
        });

        jitterModifierLabel.setLayoutX(176.5);
        jitterModifierLabel.setLayoutY(221);
        jitterModifierLabel.setPrefHeight(9.2);
        jitterModifierLabel.setPrefWidth(119.6);
        jitterModifierLabel.setStyle("-fx-font-size: 12px;");
        jitterModifierLabel.setVisible(false);

        jitterModifierSlider.setLayoutX(176);
        jitterModifierSlider.setLayoutY(241);
        jitterModifierSlider.setPrefHeight(8);
        jitterModifierSlider.setPrefWidth(125);
        jitterModifierSlider.setDark(false);
        jitterModifierSlider.setVisible(false);
        jitterModifierSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 40) / 40.0; // round to the nearest 0.025
            jitterModifierSlider.setValue(newVal.doubleValue());
            jitterModifierLabel.setText("Jitter Modifier: " + newVal.doubleValue());
        });
        jitterModifierSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        returnTypeLabel.setLayoutX(332.2);
        returnTypeLabel.setLayoutY(221);
        returnTypeLabel.setPrefHeight(11.3);
        returnTypeLabel.setPrefWidth(66.4);
        returnTypeLabel.setStyle("-fx-font-size: 12px;");
        returnTypeLabel.setVisible(false);

        returnTypeComboBox.setLayoutX(327);
        returnTypeComboBox.setLayoutY(236);
        returnTypeComboBox.setPrefHeight(20);
        returnTypeComboBox.setPrefWidth(125);
        returnTypeComboBox.getSelectionModel().select(0);
        returnTypeComboBox.setDark(false);
        returnTypeComboBox.setVisible(false);
        returnTypeComboBox.setOnAction(e -> {
            regenerate();
        });

        perlinInterpolationTypeLabel.setLayoutX(179.3);
        perlinInterpolationTypeLabel.setLayoutY(221);
        perlinInterpolationTypeLabel.setPrefHeight(10.9);
        perlinInterpolationTypeLabel.setPrefWidth(103);
        perlinInterpolationTypeLabel.setStyle("-fx-font-size: 9px;");
        perlinInterpolationTypeLabel.setVisible(false);

        perlinInterpolationTypeComboBox.setLayoutX(176);
        perlinInterpolationTypeComboBox.setLayoutY(236);
        perlinInterpolationTypeComboBox.setPrefHeight(20);
        perlinInterpolationTypeComboBox.setPrefWidth(125);
        perlinInterpolationTypeComboBox.getSelectionModel().select(0);
        perlinInterpolationTypeComboBox.setDark(false);
        perlinInterpolationTypeComboBox.setVisible(false);
        perlinInterpolationTypeComboBox.setOnAction(e -> {
            regenerate();
        });

        octavesLabel.setLayoutX(25);
        octavesLabel.setLayoutY(280);
        octavesLabel.setPrefHeight(9.2);
        octavesLabel.setPrefWidth(61.3);
        octavesLabel.setStyle("-fx-font-size: 12px;");

        octavesSlider.setLayoutX(25.7);
        octavesSlider.setLayoutY(296);
        octavesSlider.setPrefHeight(11.9);
        octavesSlider.setPrefWidth(200.7);
        octavesSlider.setDark(false);
        octavesSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue()); // round to the nearest integer
            octavesSlider.setValue(newVal.doubleValue());
            octavesLabel.setText("Octaves: " + (newVal.intValue() + 1));
        });
        octavesSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        lacunarityLabel.setLayoutX(251);
        lacunarityLabel.setLayoutY(280);
        lacunarityLabel.setPrefHeight(9.2);
        lacunarityLabel.setPrefWidth(95.5);
        lacunarityLabel.setStyle("-fx-font-size: 12px;");

        lacunaritySlider.setLayoutX(251.3);
        lacunaritySlider.setLayoutY(296);
        lacunaritySlider.setPrefHeight(11.9);
        lacunaritySlider.setPrefWidth(200.7);
        lacunaritySlider.setDark(false);
        lacunaritySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 4) / 4.0; // round to the nearest 0.25
            lacunaritySlider.setValue(newVal.doubleValue());
            lacunarityLabel.setText("Lacunarity: " + newVal.doubleValue());
        });
        lacunaritySlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        persistenceLabel.setLayoutX(25);
        persistenceLabel.setLayoutY(340);
        persistenceLabel.setPrefHeight(9.2);
        persistenceLabel.setPrefWidth(99.7);
        persistenceLabel.setStyle("-fx-font-size: 12px;");

        persistenceSlider.setLayoutX(25.3);
        persistenceSlider.setLayoutY(356);
        persistenceSlider.setPrefHeight(11.9);
        persistenceSlider.setPrefWidth(200.7);
        persistenceSlider.setDark(false);
        persistenceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 10) / 10.0; // round to the nearest 0.1
            persistenceSlider.setValue(newVal.doubleValue());
            persistenceLabel.setText("Persistence: " + newVal.doubleValue());
        });
        persistenceSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        fractalStrengthLabel.setLayoutX(251);
        fractalStrengthLabel.setLayoutY(340);
        fractalStrengthLabel.setPrefHeight(9.2);
        fractalStrengthLabel.setPrefWidth(143.2);
        fractalStrengthLabel.setStyle("-fx-font-size: 12px;");

        fractalStrengthSlider.setLayoutX(251.8);
        fractalStrengthSlider.setLayoutY(356);
        fractalStrengthSlider.setPrefHeight(11.9);
        fractalStrengthSlider.setPrefWidth(200.7);
        fractalStrengthSlider.setDark(false);
        fractalStrengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 2) / 2.0; // round to the nearest 0.5
            fractalStrengthSlider.setValue(newVal.doubleValue());
            fractalStrengthLabel.setText("Fractal Strength: " + (newVal.doubleValue() - 2.5));
        });
        fractalStrengthSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        pingPongStrengthLabel.setLayoutX(138.5);
        pingPongStrengthLabel.setLayoutY(400);
        pingPongStrengthLabel.setPrefHeight(9.2);
        pingPongStrengthLabel.setPrefWidth(143.7);
        pingPongStrengthLabel.setStyle("-fx-font-size: 12px;");
        pingPongStrengthLabel.setVisible(false);

        pingPongStrengthSlider.setLayoutX(138.8);
        pingPongStrengthSlider.setLayoutY(415);
        pingPongStrengthSlider.setPrefHeight(11.9);
        pingPongStrengthSlider.setPrefWidth(200.7);
        pingPongStrengthSlider.setDark(false);
        pingPongStrengthSlider.setVisible(false);
        pingPongStrengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 10) / 10.0; // round to the nearest 0.1
            pingPongStrengthSlider.setValue(newVal.doubleValue());
            pingPongStrengthLabel.setText("Ping Pong Strength: " + (newVal.doubleValue() + 0.5));
        });
        pingPongStrengthSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        colorfulModeLabel.setLayoutX(377.5);
        colorfulModeLabel.setLayoutY(445);
        colorfulModeLabel.setPrefHeight(9.2);
        colorfulModeLabel.setPrefWidth(106);
        colorfulModeLabel.setStyle("-fx-font-size: 9px;");

        colorfulModeToggle.setLayoutX(398.5);
        colorfulModeToggle.setLayoutY(465);
        colorfulModeToggle.setPrefHeight(22);
        colorfulModeToggle.setPrefWidth(38);
        colorfulModeToggle.setDark(false);
        colorfulModeToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            colorfulModeLabel.setText(newVal ? "Colorful Mode: On" : "Colorful Mode: Off");
            regenerate();
        });

        toleranceLabel.setLayoutX(138.5);
        toleranceLabel.setLayoutY(445);
        toleranceLabel.setPrefHeight(9.2);
        toleranceLabel.setPrefWidth(116.9);
        toleranceLabel.setStyle("-fx-font-size: 12px;");
        toleranceLabel.setVisible(false);

        toleranceSlider.setLayoutX(138.8);
        toleranceSlider.setLayoutY(470);
        toleranceSlider.setPrefHeight(11.9);
        toleranceSlider.setPrefWidth(200.7);
        toleranceSlider.setVisible(false);
        toleranceSlider.lowValueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 10) / 10.0; // round to the nearest 0.1
            if (newVal.doubleValue() > toleranceSlider.getHighValue() - 0.1) {
                newVal = toleranceSlider.getHighValue() - 0.1; // don't let the low value be higher than the high value
            }
            toleranceSlider.setLowValue(newVal.doubleValue());
            toleranceLabel.setText("Tolerance: " + newVal.doubleValue() + " to " + toleranceSlider.getHighValue());
        });
        toleranceSlider.highValueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue() * 10) / 10.0; // round to the nearest 0.1
            if (newVal.doubleValue() < toleranceSlider.getLowValue() + 0.1) {
                newVal = toleranceSlider.getLowValue() + 0.1; // don't let the high value be lower than the low value
            }
            toleranceSlider.setHighValue(newVal.doubleValue());
            toleranceLabel.setText("Tolerance: " + toleranceSlider.getLowValue() + " to " + newVal.doubleValue());
        });
        toleranceSlider.lowValueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });
        toleranceSlider.highValueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        levelsLabel.setLayoutX(138.5);
        levelsLabel.setLayoutY(445);
        levelsLabel.setPrefHeight(9.2);
        levelsLabel.setPrefWidth(47);
        levelsLabel.setStyle("-fx-font-size: 12px;");
        levelsLabel.setVisible(false);

        levelsSlider.setLayoutX(138.8);
        levelsSlider.setLayoutY(465);
        levelsSlider.setPrefHeight(11.9);
        levelsSlider.setPrefWidth(200.7);
        levelsSlider.setDark(false);
        levelsSlider.setVisible(false);
        levelsSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            newVal = Math.round(newVal.doubleValue()); // round to the nearest integer
            levelsSlider.setValue(newVal.doubleValue());
            levelsLabel.setText("Levels: " + (newVal.intValue() + 1));
        });
        levelsSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean changing) {
                if (!changing) {
                    regenerate();
                }
            }
        });

        // add elements to the root
        root.getChildren().add(background);
        background.getChildren().add(image);
        background.getChildren().add(previousButton);
        background.getChildren().add(titleLabel);
        background.getChildren().add(nextButton);
        background.getChildren().add(noiseFunctionLabel);
        background.getChildren().add(noiseFunctionComboBox);
        background.getChildren().add(fractalModifierLabel);
        background.getChildren().add(fractalModifierComboBox);
        background.getChildren().add(seedLabel);
        background.getChildren().add(seedTextField);
        background.getChildren().add(seedButton);
        background.getChildren().add(frequencyLabel);
        background.getChildren().add(frequencySlider);
        background.getChildren().add(rngTypeLabel);
        background.getChildren().add(rngTypeComboBox);
        background.getChildren().add(valueInterpolationTypeLabel);
        background.getChildren().add(valueInterpolationTypeComboBox);
        background.getChildren().add(distanceFunctionLabel);
        background.getChildren().add(distanceFunctionComboBox);
        background.getChildren().add(jitterModifierLabel);
        background.getChildren().add(jitterModifierSlider);
        background.getChildren().add(returnTypeLabel);
        background.getChildren().add(returnTypeComboBox);
        background.getChildren().add(perlinInterpolationTypeLabel);
        background.getChildren().add(perlinInterpolationTypeComboBox);
        background.getChildren().add(octavesLabel);
        background.getChildren().add(octavesSlider);
        background.getChildren().add(lacunarityLabel);
        background.getChildren().add(lacunaritySlider);
        background.getChildren().add(persistenceLabel);
        background.getChildren().add(persistenceSlider);
        background.getChildren().add(fractalStrengthLabel);
        background.getChildren().add(fractalStrengthSlider);
        background.getChildren().add(pingPongStrengthLabel);
        background.getChildren().add(pingPongStrengthSlider);
        background.getChildren().add(colorfulModeLabel);
        background.getChildren().add(colorfulModeToggle);
        background.getChildren().add(toleranceLabel);
        background.getChildren().add(toleranceSlider);
        background.getChildren().add(levelsLabel);
        background.getChildren().add(levelsSlider);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED); // remove window decorations
        stage.show();

        // if ESC is pressed, terminate the program
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });

        // if the GUI is closed, terminate the program
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });
    }
    
    private static void regenerate() {
        Fractal fractalBase;
        switch (fractalModifierComboBox.getSelectionModel().getSelectedIndex()) {
            default:
            case 0:
                fractalBase = null;
                break;
            case 1:
                fractalBase = new FBM((int) octavesSlider.getValue() + 1, lacunaritySlider.getValue(), persistenceSlider.getValue(), (float) (fractalStrengthSlider.getValue() - 2.5f));
                break;
            case 2:
                fractalBase = new Billow((int) octavesSlider.getValue() + 1, lacunaritySlider.getValue(), persistenceSlider.getValue(), (float) (fractalStrengthSlider.getValue() - 2.5f));
                break;
            case 3:
                fractalBase = new RigidMulti((int) octavesSlider.getValue() + 1, lacunaritySlider.getValue(), persistenceSlider.getValue(), (float) (fractalStrengthSlider.getValue() - 2.5f));
                break;
            case 4:
                fractalBase = new PingPong((int) octavesSlider.getValue() + 1, lacunaritySlider.getValue(), persistenceSlider.getValue(), pingPongStrengthSlider.getValue() + 0.5, (float) (fractalStrengthSlider.getValue() - 2.5f));
                break;
        }

        Noise noise;
        switch (noiseFunctionComboBox.getSelectionModel().getSelectedIndex()) {
            default:
            case 4:
                noise = new Simplex(Integer.parseInt(seedTextField.getText()), frequencySlider.getValue(), fractalBase);
                break;
            case 0:
                noise = new Gaussian(Integer.parseInt(seedTextField.getText()), frequencySlider.getValue(), RNGType.valueOf(rngTypeComboBox.getSelectionModel().getSelectedItem().toUpperCase()), fractalBase);
                break;
            case 1:
                noise = new Value(Integer.parseInt(seedTextField.getText()), frequencySlider.getValue(), InterpolationType.valueOf(valueInterpolationTypeComboBox.getSelectionModel().getSelectedItem().toUpperCase()), fractalBase);
                break;
            case 2:
                noise = new Cellular(Integer.parseInt(seedTextField.getText()), frequencySlider.getValue(), (float) jitterModifierSlider.getValue(), CellularDistanceFunction.valueOf(distanceFunctionComboBox.getSelectionModel().getSelectedItem().toUpperCase()), CellularReturnType.valueOf(returnTypeKey(returnTypeComboBox.getSelectionModel().getSelectedItem().toUpperCase())), fractalBase);
                break;
            case 3:
                noise = new Perlin(Integer.parseInt(seedTextField.getText()), frequencySlider.getValue(), InterpolationType.valueOf(perlinInterpolationTypeComboBox.getSelectionModel().getSelectedItem().toUpperCase()), fractalBase);
                break;
        }

        switch (mode) {
            case 0:
                if (colorfulModeToggle.isSelected()) {
                    image.setImage(SwingFXUtils.toFXImage(NoiseMapGenerator.generateColorful(noise, 128, 128), null));
                } else {
                    image.setImage(SwingFXUtils.toFXImage(NoiseMapGenerator.generate(noise, 128, 128), null));
                }
                break;
            case 1:
                image.setImage(SwingFXUtils.toFXImage(NoiseMapGenerator.generate(noise, toleranceSlider.getLowValue(), toleranceSlider.getHighValue(), 128, 128), null));
                break;
            case 2:
                if (colorfulModeToggle.isSelected()) {
                    image.setImage(SwingFXUtils.toFXImage(NoiseMapGenerator.generateColorful(noise, (int) levelsSlider.getValue() + 1, 128, 128), null));
                } else {
                    image.setImage(SwingFXUtils.toFXImage(NoiseMapGenerator.generate(noise, (int) levelsSlider.getValue() + 1, 128, 128), null));
                }
                break;
        }
        
    }

    private static String returnTypeKey(String returnType) {
        switch (returnType) {
            default:
            case "CELL VALUE":
                return "CELL_VALUE";
            case "DISTANCE":
                return "DISTANCE";
            case "ALTERNATE DISTANCE":
                return "DISTANCE_ALT";
            case "DISTANCE SUM":
                return "DISTANCE_ADD";
            case "DISTANCE DIFFERENCE":
                return "DISTANCE_SUB";
            case "DISTANCE PRODUCT":
                return "DISTANCE_MULT";
            case "DISTANCE QUOTIENT":
                return "DISTANCE_DIV";
            case "DISTANCE SQUARED":
                return "DISTANCE_SQUARED";
        }
    }
}