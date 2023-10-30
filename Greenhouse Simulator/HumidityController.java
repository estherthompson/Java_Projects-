
/**The HumidityController class is a Thread that controls the humidity in the greenhouse based on the current and desired humidity levels.
 */
public class HumidityController extends Thread {

    private final GreenHouseGUI gui;
    private boolean humidifierOn;
    private boolean isRunning = true;

    /**Constructs a new HumidityController object with the specified GreenHouseGUI.
     @param gui the GreenHouseGUI to use for controlling humidity
     */
    public HumidityController( GreenHouseGUI gui) {
        this.gui = gui;
    }

    /**The run method of the HumidityController class is responsible for controlling the humidity in the greenhouse based on the current and desired humidity levels.
     It runs in a while loop until the thread is stopped, which can be done by calling the stopThread method.
     */

    public void run() {
        while (isRunning) {
            // Get the current humidity, environment humidity, and other necessary values from the GUI.
            double currentHumidity = Double.parseDouble(gui.getCurrentHumidityTextField().getText());
            double environmentHumidity = Double.parseDouble(gui.getEnvironmentHumidityTextField().getText());
            double humidityEffect = Double.parseDouble(String.valueOf(gui.getHumidityEffectSlider().getValue()));
            double desiredHumidity = Double.parseDouble(String.valueOf(gui.getDesiredHumiditySlider().getValue()));
            double comfortRange = 5.0;
            double desiredMinMaxRange = (comfortRange / 2);
            // Determine if the humidifier should be on or off based on the current humidity and desired humidity.
            if (currentHumidity < desiredHumidity - desiredMinMaxRange) {
                humidifierOn = true;
            } else if (currentHumidity > desiredHumidity + desiredMinMaxRange) {
                humidifierOn = false;
            }
            // If the humidifier is on, calculate and update the new humidity value.
            if (humidifierOn) {
                double humidifierRate = Double.parseDouble(String.valueOf(gui.getHumidityRateTextField().getText()));
                gui.getHumidityStatus().setText("Humidity On");
                double humidityDifference = desiredHumidity - currentHumidity;
                double humidityChange = humidifierRate * humidityDifference + humidityEffect * (environmentHumidity - currentHumidity);
                double newHumidity = currentHumidity + humidityChange;
                gui.getCurrentHumidityTextField().setText(String.format("%.1f", newHumidity));
            // If the humidifier is off, update the GUI and wait for the specified refresh rate.
            } else {
                gui.getHumidityStatus().setText("Humidity Off");

                int humidityRate = Integer.parseInt(String.valueOf(gui.getHumidityRefreshRateTextField().getText()));

                try {
                    Thread.sleep(humidityRate * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

} /**The stopThread method is used to stop the thread from running.*/
public void stopThread(){
        isRunning = false;

    }
}