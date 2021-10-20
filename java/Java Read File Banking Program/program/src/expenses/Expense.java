package expenses;

import payments.CreditPayment;
import payments.Payment;
import payments.print;


public class Expense implements print {

    private String serviceType;
    private String billDate;
    private double billAmount;
    private String dueDate;
    private double balanceAmount;
    private String expenseRefNumber;
    private Payment payment;

    public Expense(String ern1, String st, String bd, double ba, String dd) {
        setExpenseRefNumber(ern1);
        setServiceType(st);
        setBillDate(bd);
        setBillAmount(ba);
        setDueDate(dd);
    }

    public void setExpenseRefNumber(String expenseRefNumber) {
        this.expenseRefNumber = expenseRefNumber;
    }

    public String getExpenseRefNumber() {
        return expenseRefNumber;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public double getBalanceAmount() {
        return getBillAmount()-payment.getPaymentAmount();
    }

    @Override
    public void PrintExpensesOrPayment() {
        System.out.println("Expense Reference Number: " + getExpenseRefNumber());
        System.out.println("ServiceType: " + getServiceType());
        System.out.println("BillDate: " + getBillDate());
        System.out.println("BillAmount: " + getBillAmount());
        System.out.println("DueDate: " + getDueDate());
        System.out.println("BalanceAmount: " + getBalanceAmount());
        System.out.println();
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }
}


