/**
 * The MortgageCalculatorModel class represents the data and calculations for the mortgage calculator
 * calculates the monthly payment, blended monthly payment, total mortgage interest total interest to principal ratio,
 * average annual interest, average monthly interest and yearly amortization based on principal amount, monthly payment,
 * annual interest rate and years of the loan entered by the user
 * */
package lab4;
public class MortgageCalculatorModel {
    private final double principal, annualInterestRate, monthlyInterestRate;
    private double monthlyPayment;
    private final int paymentsMade;
/**
 * Creates a MortgageCalculatorModel object with given parameters.
 * @param principal the amount of the loan
 * @param annualInterestRate the annual interest rate of the loan
 * @param years the number of years for the loan
 * */
    public MortgageCalculatorModel(double principal, double monthlyPayment,
                                   double annualInterestRate, int years) {
        this.principal = principal;
        this.monthlyPayment = monthlyPayment;
        this.annualInterestRate = annualInterestRate;

        //months that it will take to pay the loan at monthly interest rate and principal
        this.paymentsMade = years*12;
        this.monthlyInterestRate = annualInterestRate/1200.0;
    }
/**
 * returns the principal amount
 * @return  the principal amount
 * */
    public double getPrincipal() {
        return principal;
    }
    /**
     * returns the monthly payment amount
     * @return the monthly payment amount
     * */

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
    /**
     * returns the annual interest rate  amount
     * @return the annual interest rate  amount
     * */

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * returns the monthly interest rate  amount
     * @return the monthly interest rate  amount
     * */
    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }
    /**
     * returns the number of payments made
     * @return the number of payments made
     * */
    public double getPaymentsMade() {
        return paymentsMade;
    }
    /**
     * Computes and returns the monthly payment based on the principal,
     * monthly interest rate and number of payments made.
     *  @return the monthly payment
     * */

    public double computeMonthlyPayment() {
    double variableForPayment = Math.pow(1 +monthlyInterestRate, paymentsMade);
    return ((monthlyInterestRate*principal*variableForPayment)/(variableForPayment-1));
    }
    /**
     * Calculates the monthly payment and sets it as the object's monthly payment value.
     * */
    public void calculate() {
        monthlyPayment = computeMonthlyPayment();
    }
    /**
     * Computes and returns the blended monthly payment based on the principal and monthly interest rate.
     * @return the blended monthly payment
     * */
    public double computeMonthlyBlendedAmount() {
        double variableForBlended = 1 - Math.pow(1+monthlyInterestRate, -paymentsMade);

        return ((monthlyInterestRate*principal)/variableForBlended);
    }
    /**
     * Computes and returns the total mortgage interest based on the blended monthly payment and
     * number of payments made.
     * @return the total mortgage interest
     * */
    public double computeTotalMortgageInterest(){
        return computeMonthlyBlendedAmount() * (paymentsMade - principal);
    }
    /**
     * Computes the total interest and principal paid on the mortgage.
     * This method calculates the total interest and principal by multiplying the monthly blended amount by payments made.
     * @return the total amount of interest and principal paid
     * */

    public double computeTotalInterestPrincipal(){
        return computeMonthlyBlendedAmount() * paymentsMade;
    }
    /**
     * Computes the ratio of interest to principal paid on the mortgage.
     * This method calculates the ratio of interest to principal by dividing the total mortgage interest by the principal.
     * @return the ratio of interest to principal paid
     * */
    public double computeRatioInterestPrincipal(){ return computeTotalMortgageInterest()/principal; }
/**
 * Computes the average annual interest paid on the mortgage.
 * This method calculates the average annual interest by dividing the total mortgage interest by the number of payments made in a year.
 *  @return the average annual interest paid
 */
public double computeAverageAnnualInterest(){
        return computeTotalMortgageInterest()/(paymentsMade*12);
    }
    /**
     * Computes the average monthly interest paid on the mortgage.
     * This method calculates the average monthly interest by dividing the average annual interest by the number of payments made.
     *
     * @return the average monthly interest paid
     * */
    public double computeAverageMonthlyInterest(){
        return computeAverageAnnualInterest()/paymentsMade;
    }
    /**
     *  Computes the yearly amortization of the mortgage.
     *  This method calculates the yearly amortization by dividing the total interest and principal by the number of years in the mortgage.
     *  @return the yearly amortization of the mortgage
     * */

    public double computeAmortizationYearly(){
        return computeTotalInterestPrincipal()/(paymentsMade*12);
    }

    }















