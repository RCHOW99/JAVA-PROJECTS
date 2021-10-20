package payments;

import expenses.Expense;

public class DebitPayment extends Payment {

    private String bank;


    public DebitPayment(String ern2,String bnk,String nme,String num,String prn, String pd,double amnt,Expense exp){
        super(ern2,nme,num,prn,pd,amnt,exp);
        setBank(bnk);
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

    public void PrintWithCreditOrDebit(){
        System.out.println("Expense Reference Number: " + getExpenseRefNumber());
        System.out.println("Payment Reference Number: " + getPaymentRefNumber());
        System.out.println("BankName: "+getBank());
        System.out.println("Card Name: " + getName());
        System.out.println("Card Number: " + getNumber());
        System.out.println("Payment Date: " + getPaymentDate());
        System.out.println("Payment Amount: " + getPaymentAmount());
        System.out.println();
    }
}
