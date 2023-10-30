/** GreenHouseGUI is a GUI class that contains three panels, temperaturePanel, soilMoisturePanel and humidityPanel.
 Each panel contains a set of labels, text fields, sliders and buttons that allow users to control and monitor the greenhouse simulation.
 The class extends JFrame and implements ActionListener interface to handle button events.
 The GUI class uses three other controller classes, TemperatureController, SoilMoistureController and HumidityController
 to perform actions based on user inputs.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GreenHouseGUI extends JFrame {
    JSlider desiredTemperatureSlider, desiredHumiditySlider, moistureRangeSlider, tempEffectSlider,
            humidityEffectSlider, moistureEffectSlider;
    JButton endSimulation, startSimulation, saveSimulation;

    private final JLabel furnaceStatus,airConditionerStatus,  humidityStatus,
            humidityRateLabel, moistureStatus, moistureRateLabel, humidityRefreshRate;
    private final JTextField currentTemperatureTextField, currentMoistureTextField, humidityRateTextField,
            moistureRefreshTextField, airCoolingRateTextField, furnaceHeatingRateTextField, moistureRateTextField,
            currentHumidityTextField, environmentTempTextField, environmentHumidityTextField, temperatureRefreshTextField,
            environmentSoilMoistureTextField, humidityRefreshRateTextField;
    private final TemperatureController temperatureController;
    private final SoilMoistureController soilMoistureController;
    private final HumidityController humidityController;

    /** Constructs the GreenHouseGUI object and initializes its components*/
    public GreenHouseGUI(){
        temperatureController = new TemperatureController(this);
        soilMoistureController = new SoilMoistureController(this);
        humidityController = new HumidityController(this);
        this.setTitle(" ");
        this.setSize(1000, 800 );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());

        //creating Temperature Panel
        JPanel temperaturePanel = new JPanel();
        temperaturePanel.setBackground(new Color(177, 183, 169));
        temperaturePanel.setBorder(BorderFactory.createTitledBorder("Temperature" ));
        temperaturePanel.setLayout(new GridLayout(12,15));

        JLabel currentTemperatureLabel = new JLabel("Current Temperature (°C)");
        currentTemperatureTextField = new JTextField();
        JLabel desiredTemperatureLabel = new JLabel("Desired Temperature (°C)");
        JLabel airCoolingRateLabel = new JLabel("Air Condition Cooling Rate (°C/Min)");
        airCoolingRateTextField = new JTextField();
        JLabel furnaceHeatingRateLabel = new JLabel("Furnace Heating Rate (°C/Min)");
        furnaceHeatingRateTextField = new JTextField();
        furnaceStatus = new JLabel("Furnace : OFF");
        airConditionerStatus = new JLabel("Air Conditioner: OFF");
        JLabel temperatureRefreshLabel = new JLabel("Refresh Rate (Seconds)");
        temperatureRefreshTextField = new JTextField();

        //making the slider for the desired temperature
        desiredTemperatureSlider = new JSlider(0, 100, 50);
        desiredTemperatureSlider.setPreferredSize(new Dimension(400, 200));
        desiredTemperatureSlider.setPaintTicks(true);
        desiredTemperatureSlider.setMinorTickSpacing(10);
        desiredTemperatureSlider.setPaintTrack(true);
        desiredTemperatureSlider.setMajorTickSpacing(25);
        desiredTemperatureSlider.setPaintLabels(true);
        desiredTemperatureSlider.setFont(new Font("MV Boli", Font.PLAIN,15));

        //adding the labels and fields to the panel

        temperaturePanel.add(currentTemperatureLabel);
        temperaturePanel.add(currentTemperatureTextField);
        temperaturePanel.add(desiredTemperatureLabel);
        temperaturePanel.add(desiredTemperatureSlider);
        temperaturePanel.add(airCoolingRateLabel);
        temperaturePanel.add(airCoolingRateTextField);
        temperaturePanel.add(furnaceHeatingRateLabel);
        temperaturePanel.add(furnaceHeatingRateTextField);
        temperaturePanel.add(temperatureRefreshLabel);
        temperaturePanel.add(temperatureRefreshTextField);
        temperaturePanel.add(furnaceStatus);
        temperaturePanel.add(airConditionerStatus);

        //creating the soil moisture panel
        JPanel soilMoisturePanel = new JPanel();
        soilMoisturePanel.setBackground(new Color(211, 215, 210));
        soilMoisturePanel.setBorder(BorderFactory.createTitledBorder("Soil Moisture"));
        soilMoisturePanel.setLayout(new GridLayout(9,15 ));

        JLabel currentMoistureLabel = new JLabel("Current Moisture (%)");
        currentMoistureTextField = new JTextField();

        JLabel moistureLabel = new JLabel("Desired Moisture (%)");
        moistureStatus = new JLabel("Sprinkler on : OFF");
        moistureRateLabel = new JLabel("Moisture Rate (%/Min)");
        JLabel moistureRefreshLabel = new JLabel("Refresh Rate (seconds)");
        moistureRefreshTextField = new JTextField();
        moistureRangeSlider = new JSlider(0,100,50);

        moistureRangeSlider.setPaintTicks(true);
        moistureRangeSlider.setMinorTickSpacing(10);
        moistureRangeSlider.setPaintTrack(true);
        moistureRangeSlider.setMajorTickSpacing(25);
        moistureRangeSlider.setPaintLabels(true);
        moistureRangeSlider.setFont(new Font("MV Boli", Font.PLAIN,15));

        JLabel moistureRateLabel = new JLabel("Moisture Rate (%/Min)");
        moistureRateTextField = new JTextField();

        soilMoisturePanel.add(currentMoistureLabel);
        soilMoisturePanel.add(currentMoistureTextField);
        soilMoisturePanel.add(moistureLabel);
        soilMoisturePanel.add(moistureRangeSlider);
        soilMoisturePanel.add(moistureRateLabel);
        soilMoisturePanel.add(moistureRateTextField);
        soilMoisturePanel.add(moistureRefreshLabel);
        soilMoisturePanel.add(moistureRefreshTextField);
        soilMoisturePanel.add(moistureStatus);

        //create the humidity panel
        JPanel humidityPanel = new JPanel();
        humidityPanel.setBackground(new Color(177, 183, 169));
        humidityPanel.setBorder(BorderFactory.createTitledBorder("Humidity"));
        humidityPanel.setLayout(new GridLayout(9,2));
        JLabel currentHumidityLabel = new JLabel("Current Humidity (%): ");
        humidityStatus = new JLabel("Humidity : OFF");
        humidityRateLabel = new JLabel("Humidity Rate (%/Min)");
        humidityRateTextField = new JTextField();
        currentHumidityTextField = new JTextField();
        JLabel desiredHumidityLabel = new JLabel("Desired Humidity (%): ");
        humidityRefreshRate = new JLabel("Refresh Rate (seconds): ");
        humidityRefreshRateTextField = new JTextField();
        //slider
        desiredHumiditySlider = new JSlider();
        desiredHumiditySlider.setPaintTicks(true);
        desiredHumiditySlider.setMinorTickSpacing(10);
        desiredHumiditySlider.setPaintTrack(true);
        desiredHumiditySlider.setMajorTickSpacing(25);
        desiredHumiditySlider.setPaintLabels(true);
        desiredHumiditySlider.setFont(new Font("MV Boli", Font.PLAIN,15));
        //adding to the panel
        humidityPanel.add(currentHumidityLabel);
        humidityPanel.add(currentHumidityTextField);
        humidityPanel.add(desiredHumidityLabel);
        humidityPanel.add(desiredHumiditySlider);
        humidityPanel.add(humidityRateLabel);
        humidityPanel.add(humidityRateTextField);
        humidityPanel.add(humidityRefreshRate);
        humidityPanel.add(humidityRefreshRateTextField);
        humidityPanel.add(humidityStatus);

        // creating environment panel
        JPanel environmentPanel = new JPanel();
        environmentPanel.setBackground(new Color(211, 215, 210));
        environmentPanel.setBorder(BorderFactory.createTitledBorder("Environment"));
        environmentPanel.setLayout(new GridLayout(12,15));
        JLabel environmentTempLabel = new JLabel(" Environment Temperature (°C)");
        environmentTempTextField = new JTextField();
        JLabel tempEffect = new JLabel("Temperature Effect (°C/Min)");
        tempEffectSlider = new JSlider();
        tempEffectSlider.setPaintTicks(true);
        tempEffectSlider.setMinorTickSpacing(10);
        tempEffectSlider.setPaintTrack(true);
        tempEffectSlider.setMajorTickSpacing(25);
        tempEffectSlider.setPaintLabels(true);
        tempEffectSlider.setFont(new Font("MV Boli", Font.PLAIN,15));
        JLabel environmentHumidityLabel = new JLabel("Environment Humidity (%)");
        environmentHumidityTextField = new JTextField();
        JLabel humidityEffect = new JLabel("Humidity Effect (%/Min) ");
        humidityEffectSlider = new JSlider();
        humidityEffectSlider.setPaintTicks(true);
        humidityEffectSlider.setMinorTickSpacing(10);
        humidityEffectSlider.setPaintTrack(true);
        humidityEffectSlider.setMajorTickSpacing(25);
        humidityEffectSlider.setPaintLabels(true);
        humidityEffectSlider.setFont(new Font("MV Boli", Font.PLAIN,15));

        JLabel environmentSoilMoistureLabel = new JLabel("Environment Soil Moisture (%)");
        environmentSoilMoistureTextField = new JTextField();
        JLabel moistureEffect = new JLabel("Moisture Effect (°C/Min)");
        JSlider moistureEffectSlider = new JSlider();
        moistureEffectSlider.setPaintTicks(true);
        moistureEffectSlider.setMinorTickSpacing(10);
        moistureEffectSlider.setPaintTrack(true);
        moistureEffectSlider.setMajorTickSpacing(25);
        moistureEffectSlider.setPaintLabels(true);
        moistureEffectSlider.setFont(new Font("MV Boli", Font.PLAIN,15));


        // add of the panel
        environmentPanel.add(environmentTempLabel);
        environmentPanel.add(environmentTempTextField);
        environmentPanel.add(tempEffect);
        environmentPanel.add(tempEffectSlider);
        environmentPanel.add(environmentHumidityLabel);
        environmentPanel.add(environmentHumidityTextField);
        environmentPanel.add(humidityEffect);
        environmentPanel.add(humidityEffectSlider);
        environmentPanel.add(environmentSoilMoistureLabel);
        environmentPanel.add(environmentSoilMoistureTextField);
        environmentPanel.add(moistureEffect);
        environmentPanel.add(moistureEffectSlider);


        //creating the buttons
        startSimulation = new JButton("Start");
        endSimulation = new JButton("Stop");
        saveSimulation = new JButton("Save");
        JLabel nameLabel = new JLabel("Green Thumb Greenhouses Simulation");
        nameLabel.setFont(new Font("Century Gothic", Font.PLAIN,15));
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(nameLabel);
        buttonPanel.add(startSimulation);
        buttonPanel.add(endSimulation);
        buttonPanel.add(saveSimulation);


        // Add the panels to the JFrame
        this.add(environmentPanel);
        this.add(temperaturePanel);
        this.add(soilMoisturePanel);
        this.add(humidityPanel);
        this.add(buttonPanel);

        //creating event listener for each button

        startSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });

        endSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSimulation();
            }
        });

        saveSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSimulation();
            }
        });

        // Set the visibility of the JFrame
        JFrame frame = new JFrame("Greenhouse Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the program when user clicks the X button
        frame.setSize(1000, 600); //set the size of the frame
        frame.setLocationRelativeTo(null); //center the frame on the screen
        frame.setVisible(true); //set the visibility of the frame to true
    }
    // implementing the threads to stop and start simulation
    public void startSimulation(){
        temperatureController.start();
        soilMoistureController.start();
        humidityController.start();

    }
    public void stopSimulation(){
        temperatureController.stopThread();
        soilMoistureController.stopThread();
        humidityController.stopThread();
    }
    //allowing user to save file data
    public void saveSimulation(){ JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Save the simulation data to the selected file
            try (PrintWriter writer = new PrintWriter(fileToSave)) {
                writer.println("Simulation data");
                writer.println("Temperature: " + getCurrentTemperatureTextField().getText());
                writer.println("Soil moisture: " + getCurrentMoistureTextField().getText());
                writer.println("Humidity: " + getCurrentHumidityTextField().getText());
                // Add any other data that you want to save here
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**Returns the JTextField that displays the current temperature.
     @return the JTextField for current temperature
     */
    public JTextField getCurrentTemperatureTextField(){
        return currentTemperatureTextField;
    }
    /**Returns the JTextField that displays the furnace heating rate.
     @return the JTextField for furnace heating rate
     */
    public JTextField getFurnaceRateTextField(){
        return furnaceHeatingRateTextField;
    }
    /**Returns the JTextField that displays the air cooling rate.
     @return the JTextField for air cooling rate
     */
    public JTextField getAcRateTextField(){return airCoolingRateTextField;}
    /**Returns the JTextField that displays the current moisture level.
     @return the JTextField for current moisture level
     */
    public JTextField getCurrentMoistureTextField(){
        return currentMoistureTextField;
    }
    /**Returns the JTextField that displays the moisture refresh rate.
     @return the JTextField for moisture refresh rate
     */
    public JTextField getMoistureRefreshTextField(){return moistureRefreshTextField;}
    /**Returns the JTextField that displays the current humidity level.
     @return the JTextField for current humidity level
     */
    public JTextField getCurrentHumidityTextField(){return currentHumidityTextField;}
    /**Returns the JTextField that displays the humidity rate.
     @return the JTextField for humidity rate
     */
    public JTextField getHumidityRateTextField(){return humidityRateTextField;}
    /** Returns the JTextField that displays the moisture rate.
     @return the JTextField for moisture rate
     */
    public JTextField getMoistureRateTextField(){return moistureRateTextField;}
    /**Returns the JTextField that displays the environmental temperature.
     @return the JTextField for environmental temperature
     */
    public JTextField getEnvironmentTempTextField(){return environmentTempTextField;}
    /**Returns the JTextField that displays the temperature refresh rate.
     @return the JTextField for temperature refresh rate
     */
    public JTextField getTemperatureRefreshTextField(){return temperatureRefreshTextField;}
    /**Returns the JTextField that displays the humidity refresh rate.
     @return the JTextField for humidity refresh rate
     */
    public JTextField getHumidityRefreshRateTextField() {return humidityRefreshRateTextField;}
    /**Returns the JTextField that displays the environmental soil moisture level.
     @return the JTextField for environmental soil moisture level
     */
    public JTextField getEnvironmentSoilMoistureTextField(){return environmentSoilMoistureTextField;}
    /**Returns the JTextField that displays the environmental humidity level.
     @return the JTextField for environmental humidity level
     */
    public JTextField getEnvironmentHumidityTextField(){return environmentHumidityTextField;}
    /**
     Returns the JSlider that controls the desired temperature.
     @return the JSlider for desired temperature
     */
    public JSlider getDesiredTemperatureSlider(){return desiredTemperatureSlider;}
    /**

     Returns the JSlider that controls the desired humidity level.
     @return the JSlider for desired humidity level
     */
    public JSlider getDesiredHumiditySlider(){return desiredHumiditySlider;}
    /** Returns the JSlider object representing the effect of soil moisture on the environment.
     @return the JSlider object representing the effect of soil moisture on the environment
     */
    public JSlider getSoilMoistureEffect(){return moistureEffectSlider;}
    /** Returns the JSlider object representing the effect of temperature on the environment.
     @return the JSlider object representing the effect of temperature on the environment
     */
    public JSlider getTempEffectSlider(){return tempEffectSlider;}
    /**Returns the JSlider object representing the effect of humidity on the environment.
     @return the JSlider object representing the effect of humidity on the environment
     */
    public JSlider getHumidityEffectSlider(){return humidityEffectSlider;}
    /** Returns the JSlider object representing the desired moisture range for the environment.
     @return the JSlider object representing the desired moisture range for the environment
     */
    public JSlider getDesiredMoistureSlider(){return moistureRangeSlider;}
    /** Returns the JLabel object representing the status of the furnace.
     @return the JLabel object representing the status of the furnace
     */
    public JLabel getFurnaceStatus(){return furnaceStatus;}
    /** Returns the JLabel object representing the status of the air conditioner.
     @return the JLabel object representing the status of the air conditioner
     */
    public JLabel getAirConditionerStatus(){return airConditionerStatus;}
    /** Returns the JLabel object representing the status of the sprinklers .
     @return the JLabel object representing the status of the sprinklers.
     */
    public JLabel getMoistureStatus(){return moistureStatus;}
    /**

     Returns the JLabel object representing the status of the humidity level in the environment.
     @return the JLabel object representing the status of the humidity level.
     */
    public JLabel getHumidityStatus(){return humidityStatus;}



}

