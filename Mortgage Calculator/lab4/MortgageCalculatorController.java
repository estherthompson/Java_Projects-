/**
 * lab4 package containing classes for implementation of Mortgage calculator using the MVC pattern
 * */
package lab4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The MortgageCalculatorController class is the controller component of the MVC pattern for the mortgage calculator  application
 * receives user input from the view and interacts with the model to perform and update the results
 * */
public class MortgageCalculatorController {
    /**
     * the view and model components of the MVC pattern
     * */
    private final MortgageCalculatorView view;
    private final MortgageCalculatorModel model;

    /** main method of the controller class
     * it creates an instance of the view, model and controller and sets the view to be visible
     * @param args An array of command-line argument for the main method
     */

    public static void main(String[] args) {
        MortgageCalculatorView view = new MortgageCalculatorView();
        MortgageCalculatorModel model = new MortgageCalculatorModel(0, 0, 0, 0);
        MortgageCalculatorController controller = new MortgageCalculatorController(view, model);
        controller.view.setVisible(true);
    }
    /**
     * constructs a new instance of the controller class and with specified view and model
     * adds an action listener to the view's calculate mortgage button, which triggers the calculation and updating of the view
     * @param view The view component of the MVC Pattern for the mortgage calculator application
     * @param model the model component of the MVC Pattern for the mortgage calculator application
     * */

    public MortgageCalculatorController(MortgageCalculatorView view, MortgageCalculatorModel model) {
        this.view = view;
        this.model = model;
        this.view.calculateMortgage.addActionListener(new ActionListener() {

            /**
             * the actionPerformed method is invoked when the user clicks the calculateMortgage button in the view.
             * it retrieves the user input from the view, sets the input as the model's properties,
             * triggers the calculation, updates the view with the result.
             * if the input is invalid, it displays an error message in the view.
             * @param e The ActionEvent object that triggered the actionPerformed method.
             * */

            public void actionPerformed(ActionEvent e) {
                try {
                    double paymentMonthly = Double.parseDouble(view.getMonthlyPayments());
                    double principal = Double.parseDouble(view.getPrincipal());
                    double annualInterestRate = Double.parseDouble(view.getInterestRate());
                    int yearsOfLoans = Integer.parseInt(view.getYears());
                    MortgageCalculatorModel model = new MortgageCalculatorModel(principal, paymentMonthly,
                            annualInterestRate, yearsOfLoans);
                    model.calculate();
                    view.setMonthlyBlendedAmount(("Monthly Blended Amount: " +
                            model.computeMonthlyBlendedAmount()));
                    view.setTotalMortgageInterest(("Total Mortgage Interest: " + model.computeTotalMortgageInterest()));
                    view.setTotalInterestPrincipal(("Total Interest Principal: " + model.computeTotalInterestPrincipal()));
                    view.setTotalRatio(("The Total Ratio: " + model.computeRatioInterestPrincipal()));
                    view.setAverageAnnualInterest("Average Annual Interest: " + model.computeAverageAnnualInterest());
                    view.setAverageMonthlyInterest("Average Monthly Interest: " + model.computeAverageMonthlyInterest());
                    view.setAmortizationYearly("Yearly Amortization: " + model.computeAmortizationYearly());
                } catch (NumberFormatException ex) {
                    view.displayErrorMessage("Invalid input. Please Try Again!");
                }
            }
        });
    }
}
