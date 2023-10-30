/**The SoilMoistureController class controls the soil moisture in a greenhouse by turning on or off the sprinkler based on the current moisture level.
 It extends the Thread class and runs in a separate thread.
 */

public class SoilMoistureController extends Thread {


private final GreenHouseGUI gui;
private boolean sprinklerOn;
private boolean isRunning = true;

    /** Constructs a SoilMoistureController object with the specified GreenHouseGUI object.
     @param gui the GreenHouseGUI object that is used to control the sprinkler and read the current moisture level, environment soil moisture level, soil moisture effect, desired moisture level, and moisture rate
     */

public SoilMoistureController(GreenHouseGUI gui){
    this.gui = gui;
}
    /**The run method runs the SoilMoistureController thread. It reads the current moisture level,
     environment soil moisture level, soil moisture effect, desired moisture level, and moisture
     rate from the GreenHouseGUI object and controls the sprinkler accordingly. It also updates
     the current moisture level and the sprinkler status in the GreenHouseGUI object.
     It continuously runs as long as the isRunning flag is set to true. It calculates the current
     moisture level, the environment soil moisture, the soil moisture effect, the desired moisture level,
     and the comfort range. Based on these values, it decides whether to turn on or off the sprinkler.
     If the sprinkler is on, it calculates the moisture rate, moisture difference, and moisture change
     required to bring the moisture level to the desired level. It then updates the current moisture level
     and sprinkler status in the GUI. If the sprinkler is off, it updates the sprinkler status in the GUI and
     sleeps for as the user entered.
     */
public void run() {
    while (isRunning) {
        double currentMoisture = Double.parseDouble(gui.getCurrentMoistureTextField().getText());
        double environmentSoilMoisture = Double.parseDouble(gui.getEnvironmentSoilMoistureTextField().getText());
        double soilMoistureEffect = Double.parseDouble(String.valueOf(gui.getSoilMoistureEffect().getValue()));
        double desiredMoisture = Double.parseDouble(String.valueOf(gui.getDesiredMoistureSlider().getValue()));
        double comfortRange = 5.0;
        double desiredMinMaxRange = (comfortRange / 2);


        if (currentMoisture > desiredMoisture - desiredMinMaxRange) {
            sprinklerOn = false;
        } else if (currentMoisture < desiredMoisture + desiredMinMaxRange) {
            sprinklerOn = true;
        }

        if (sprinklerOn) {
            double moistureRate = Double.parseDouble(String.valueOf(gui.getMoistureRateTextField().getText()));
            double moistureDifference = desiredMoisture - currentMoisture;
            double moistureChange = moistureRate * moistureDifference + soilMoistureEffect*(environmentSoilMoisture-currentMoisture);
            double newMoisture = currentMoisture + moistureChange;
            //update current moisture
            gui.getCurrentMoistureTextField().setText(String.format("%.1f", newMoisture));
            gui.getMoistureStatus().setText("Sprinkler On");
        } else {
            gui.getMoistureStatus().setText("Sprinkler Off");
            int moistureRefresh = Integer.parseInt(gui.getMoistureRefreshTextField().getText());
            try {
                Thread.sleep(moistureRefresh * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
    /**Stops the SoilMoistureController thread.*/
    public void stopThread(){
        isRunning = false;
}}