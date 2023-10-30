
/**The TemperatureController class extends the Thread class and controls the temperature of a greenhouse by adjusting the furnace and AC rates based on the current and desired temperature, environment temperature, and temperature effect.
 */

public class TemperatureController extends Thread {
    /** The GUI of the greenhouse application. */
    private final GreenHouseGUI gui;
    private double currentTemp;
    boolean isRunning = true;
    /** Constructor for the TemperatureController class that initializes the GUI.
     @param gui the GUI of the greenhouse application.
     */
    public TemperatureController(GreenHouseGUI gui) {
        this.gui = gui;
    }
    /**The run method of the TemperatureController thread that runs until the isRunning flag is set to false.
     It adjusts the furnace and AC rates based on the current and desired temperature, environment temperature, and temperature effect.
     */
    public void run(){
        while(isRunning) {
            // Get the current temperature, desired temperature, environment temperature, and temperature effect
            double currentTemp = Double.parseDouble(gui.getCurrentTemperatureTextField().getText());
            double desiredTemp = Double.parseDouble(String.valueOf(gui.getDesiredTemperatureSlider().getValue()));
            double environmentTemp = Double.parseDouble(gui.getEnvironmentTempTextField().getText());
            double tempEffect = Double.parseDouble(String.valueOf(gui.getTempEffectSlider().getValue()));
            // Define the comfort range and desired minimum and maximum range
            double comfortRange = 3.0;
            double desiredMinMaxRange = (comfortRange /2);
            // Initialize the furnace and AC status
            boolean furnaceOn;
            boolean acOn;
            if(currentTemp < desiredTemp - desiredMinMaxRange){
                furnaceOn = true;
                acOn = false;
                //Furnace Rate from the TextField
                double furnaceRate = Double.parseDouble(String.valueOf(gui.getFurnaceRateTextField().getText()));
                gui.getFurnaceRateTextField().setText(String.valueOf(furnaceRate));


                //Adjust Temperature based on furnace rate
                double tempDifference = desiredTemp - currentTemp;
                double tempChange = furnaceRate * tempDifference + tempEffect * (environmentTemp - currentTemp);
                double newTemp = currentTemp + tempChange;

                //update current temperature text field
                gui.getCurrentTemperatureTextField().setText(String.valueOf(newTemp));

            // If the current temperature is higher than the desired temperature minus
                // the desired minimum and maximum range, turn on the AC and update the AC rate

            } else if (currentTemp > desiredTemp - desiredMinMaxRange) {
                acOn = true;
                furnaceOn = false;

                double acRate = Double.parseDouble(String.valueOf(gui.getAcRateTextField().getText()));
                gui.getAcRateTextField().setText(String.format("%.1f", acRate));

                double tempDifference = currentTemp - desiredTemp;
                double tempChange = acRate*tempDifference + tempEffect * (environmentTemp - currentTemp);
                double newTemp = currentTemp - tempChange;
                gui.getCurrentTemperatureTextField().setText(String.format("%.1f", newTemp));
            } else {
                furnaceOn = false;
                acOn = false;}

            // Update the furnace and AC status based on their on/off state
            if (furnaceOn){
                gui.getFurnaceStatus().setText("Furnace On");

            } else{
                gui.getFurnaceStatus().setText(" Furnace Off");}
            if (acOn){
                gui.getAirConditionerStatus().setText("AC On");
            } else {
                gui.getAirConditionerStatus().setText("AC Off");
            }
            //Get the current refresh rate
            int refreshRate = Integer.parseInt(String.valueOf(gui.getTemperatureRefreshTextField().getText()));


            // how the long it will take for the thread to refresh
            try {
                Thread.sleep(refreshRate * 1000);

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }  public void stopThread(){
        isRunning = false;
    }

    public void incrementTemperature(){


    }

}
