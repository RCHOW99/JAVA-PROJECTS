package expenses;


import javafx.application.Application;
import javafx.stage.Stage;
import payments.*;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyExpenses extends Application {

    int numberExpenses;
    int numberPayments;
    Expense[] myExpenses;
    Payment[] myPayments;


    public String ReadAndInit(String filename) throws FileNotFoundException {
        String s = "";
        try {
            if (filename.contains("Payments") || filename.contains("payments")|| filename.contains("Payment")|| filename.contains("payment")) {
                Scanner scn2 = new Scanner(new FileReader(filename));
                numberPayments = scn2.nextInt();
                myPayments = new Payment[numberPayments];
                String line2 = scn2.nextLine();
                while (scn2.hasNext()) {
                    line2 = line2.concat(scn2.nextLine()) + ',';
                }
                String[] PAY = line2.split(",");


                double[] DPAY = new double[numberPayments];
                int r = 7;
                for (int i = 0; i < DPAY.length; i++) {
                    DPAY[i] = Double.parseDouble(PAY[r]);
                    r = r + 8;
                }

                int j = 0;
                for (int i = 0; i < numberPayments; i++) {
                    if (PAY[j + 1].contains("credit")||PAY[j + 1].contains("Credit")) {
                        myPayments[i] = new CreditPayment(PAY[j], PAY[j + 2], PAY[j + 3], PAY[j + 4], PAY[j + 5], PAY[j + 6], DPAY[i], myExpenses[i]);
                    } else if (PAY[j + 1].contains("debit")||PAY[j + 1].contains("Debit")) {
                        myPayments[i] = new DebitPayment(PAY[j], PAY[j + 2], PAY[j + 3], PAY[j + 4], PAY[j + 5], PAY[j + 6], DPAY[i], myExpenses[i]);
                    }
                    j = j + 8;
                }
                s = "Payments data read";
            }
            else {
                try {
                    Scanner scn = new Scanner(new FileReader(filename));
                    numberExpenses = scn.nextInt();
                    myExpenses = new Expense[numberExpenses];
                    String line = scn.next();
                    while (scn.hasNextLine()) {
                        line = line.concat(scn.nextLine()) + ',';
                    }
                    String[] EX = line.split(",");

                    double[] DEX = new double[numberExpenses];
                    int o = 3;
                    for (int i = 0; i < DEX.length; i++) {
                        DEX[i] = Double.parseDouble(EX[o]);
                        o = o + 5;
                    }

                    int k = 0;
                    for (int i = 0; i < numberExpenses; i++) {
                        myExpenses[i] = new Expense(EX[k], EX[k + 1], EX[k + 2], DEX[i],EX[k + 4]);
                        k = k + 5;
                    }
                    s = "Expenses data read";
                } catch (FileNotFoundException e) {
                    s = "Could not find that file";
                }
                catch (InputMismatchException e){
                    s= "File is not formatted correctly";
                }
            }
        } catch (NullPointerException e) {
            s = "Always enter the expenses file path first";
        } catch (FileNotFoundException e) {
            s = "Could not find that file";
        }
        catch (InputMismatchException e){
            s= "File is not formatted correctly";
        }
        return s;
    }


    @Override
    public void start(Stage stage) throws Exception {

    }

    public String PrintTotalExpenses() {
        double TotalExpenses = 0;
        for (int i = 0; i < numberExpenses; i++) {
            TotalExpenses = TotalExpenses + myExpenses[i].getBillAmount();
        }
        return "Total Expense: " + TotalExpenses;
    }

    public String PrintTotalBalance() {
       String r;
        double TotalBalance = 0;
        try {
            for (int i = 0; i < numberExpenses; i++) {
                TotalBalance = TotalBalance + myPayments[i].getExpense().getBalanceAmount();
            }
        }
        catch (NullPointerException e){
            r="The payments file is not read in or is not formatted correctly";
        }
        r = "Total Balance " + TotalBalance;
        return r;
    }


    public String interact(String EXPENSEREFNUMBER) {
        String t = "";
        String r = "";
        try {
            for (int i = 0; i < numberExpenses; i++) {
                if (myExpenses[i].getExpenseRefNumber().equals(EXPENSEREFNUMBER)) {
                    EXPENSEREFNUMBER = t;
                    r = myExpenses[i].toString() + "\n" + "\n" + myPayments[i].toString();
                }
            }
            if (numberExpenses == 0) {
                r = "Read in the files first";
            }
            if (!EXPENSEREFNUMBER.equals(t) && numberExpenses != 0) {
                r = "That is not a ERF number";
            }
        }
        catch (NullPointerException e){
            r="The payments file is not read in or is not formatted correctly";
        }
        return r;
    }

    public String Print() {
        String s = "";
        try {
            String name = " for Raad Chowdhury" + "\n";
            if (!(numberExpenses == 0)) {
                s = "Expenses" + name + "\n";
                for (int i = 0; i < numberExpenses; i++) {
                    s = s + myExpenses[i].toString() +"\n" + "\n";
                }
                s = s + "Payments" + name +"\n";
                for (int i = 0; i < numberPayments-1; i++) {
                    s = s + myPayments[i].toString()+"\n" + "\n";
                }
                s = s + myPayments[numberPayments-1].toString();
            }
            else {
                s ="Read in the files first";
            }
        }
        catch (NullPointerException e){
            s="The payments file is not read in or is not formatted correctly";
        }
        return s;
    }
}
