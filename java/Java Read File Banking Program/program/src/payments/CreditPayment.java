package payments;

import expenses.Expense;

public class CreditPayment extends Payment{
private String card;


    public CreditPayment(String ern2,String c,String nme,String num,String prn, String pd,double amnt,Expense exp){
        super(ern2,nme,num,prn,pd,amnt,exp);
       setCard(c);
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    @Override
    public void PrintWithCreditOrDebit() {
        System.out.println("Expense Reference Number: " + getExpenseRefNumber());
        System.out.println("Payment Reference Number: " + getPaymentRefNumber());
        System.out.println("CreditCard: "+getCard());
        System.out.println("Card Name: " + getName());
        System.out.println("Card Number: " + getNumber());
        System.out.println("Payment Date: " + getPaymentDate());
        System.out.println("Payment Amount: " + getPaymentAmount());
        System.out.println();
    }
}

