package payments;

import expenses.Expense;
import expenses.MyExpenses;

import javax.print.DocFlavor;

public abstract class Payment implements print {
    protected String Name;
    protected String Number;
    protected String paymentDate;
    protected double paymentAmount;
    protected String paymentRefNumber;
    private String expenseRefNumber;
    private Expense expense;

    public Payment(String ern2, String nme,String num, String prn, String pd, double amnt, Expense exp) {
        setExpenseRefNumber(ern2);
        setExpense(exp);
        setName(nme);
        setNumber(num);
        setPaymentDate(pd);
        setPaymentAmount(amnt);
        setPaymentRefNumber(prn);
        expense.setPayment(Payment.this);
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getNumber() {
        return Number;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentRefNumber(String paymentRefNumber) {
        this.paymentRefNumber = paymentRefNumber;
    }

    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }

    public void setExpenseRefNumber(String expenseRefNumber) {
        this.expenseRefNumber = expenseRefNumber;
    }

    public String getExpenseRefNumber() {
        return expenseRefNumber;
    }

    public abstract void PrintWithCreditOrDebit();

    @Override
    public void PrintExpensesOrPayment() {
        System.out.println("Expense Reference Number: " + getExpenseRefNumber());
        System.out.println("Payment Reference Number: " + getPaymentRefNumber());
        System.out.println("Card Name: " + getName());
        System.out.println("Card Number: " + getNumber());
        System.out.println("Payment Date: " + getPaymentDate());
        System.out.println("Payment Amount: " + getPaymentAmount());
        System.out.println('\n');
    }
}
