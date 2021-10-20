package expenses;

import payments.*;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyExpenses {


    int numberExpenses;
    int numberPayments;
    Expense[] myExpenses = new Expense[numberExpenses];
    Payment[] myPayments = new Payment[numberPayments];

    public static void main(String[] args) throws IOException {
        MyExpenses main = new MyExpenses();
        main.init();
        main.Print();
        main.interact();
    }

    public void read() throws FileNotFoundException {
        try {
            Scanner scn = new Scanner(new FileReader("expenses.txt"));
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
                myExpenses[i] = new Expense(EX[k], EX[k + 1], EX[k + 2],DEX[i],EX[k + 4]);
                k = k + 5;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find expenses file");
        } catch (InputMismatchException e) {
            System.out.println("File is not formatted correctly : scanner string could not be split into string array, expenses in read()");
        } catch (NumberFormatException e) {
            System.out.println("File is not formatted correctly: Could not parse string to double, expenses in read()");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File is not formatted correctly: Array Index out of bounds, expenses in read()");
        }
        catch (NullPointerException e){
            System.out.println("A part of the array is null: expenses in read() ");
        }

        try {
            Scanner scn2 = new Scanner(new FileReader("payments.txt"));
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
        } catch (FileNotFoundException e) {
            System.out.println("Could not find payments file");
        } catch (InputMismatchException e) {
            System.out.println("File is not formatted correctly : Scanner string could not be split into string array, payments in read()");
        } catch (NumberFormatException e) {
            System.out.println("File is not formatted correctly: Could not parse string to double, payments in read()");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File is not formatted correctly: Array Index out of bounds, payments in read()");
        }
        catch (NullPointerException e){
            System.out.println("A part of the array is null: payments in read() ");
        }
    }


    public void init() throws FileNotFoundException {
        read();
    }

    public void interact() {
        String s = "";
        if ((numberExpenses>0)&&(numberPayments>0)&&this.myExpenses[0]!=null) {
            Scanner scn = new Scanner(System.in);
            try {
                do {
                    System.out.println("Enter A Expense Reference Number To Load Specific Info");
                    System.out.println("Otherwise Enter \"exit\" To End The Program");
                    s = scn.nextLine();

                    for (int i = 0; i < numberExpenses; i++) {
                        if (myExpenses[i].getExpenseRefNumber().equals(s)) {
                            myExpenses[i].PrintExpensesOrPayment();
                            myPayments[i].PrintWithCreditOrDebit();
                        }
                        if (!s.equals("exit") && Integer.parseInt(s) > numberExpenses) {
                            System.out.println("The Expenses Only Go Up To " + numberExpenses + " \n" );
                            break;
                        }
                        if (!s.equals("exit") && Integer.parseInt(s) < Integer.parseInt(myExpenses[0].getExpenseRefNumber())) {
                            System.out.println("The Minimum Expense Reference Number is " + myExpenses[0].getExpenseRefNumber() + " \n" );
                            break;
                        }
                    }
                }
                while (!s.equals("exit"));
                System.out.println("system exit");
            } catch (NullPointerException e) {
                System.out.println("Expenses and or payments file is either formatted wrong or not found: Interact method");
            }
        }
        else{
            System.out.println("Expenses and or payments file is either formatted wrong or not found: interact() method");
        }
    }


    public void Print() {
        String name = " for Raad Chowdhury" + "\n";


        if((numberExpenses>0)&&(numberPayments>0)&&this.myExpenses[0]!=null) {
            System.out.println("Expenses" + name);
        }

        try {
           if ((numberExpenses>0)&&(numberPayments>0)) {
                for (int i = 0; i < numberExpenses; i++) {
                    myExpenses[i].PrintExpensesOrPayment();
                }
            }
        }catch (NullPointerException e) {
            System.out.println("Expenses file is read but not the payments file, check the payments file format or name");
        }

        if((numberExpenses>0)&&(numberPayments>0)&&this.myExpenses[0]!=null) {
            System.out.println("Payments" + name);
        }


        try {
                for (int i = 0; i < numberPayments; i++) {
                    myPayments[i].PrintWithCreditOrDebit();
                }
        } catch (NullPointerException e) {
            System.out.println("Payments file is read but not the expenses file, check expenses file format or name");
        }


        PrintTotalExpenses();
        PrintTotalBalance();
    }

    public String PrintTotalExpenses() {
        double TotalExpenses = 0;
        try {
            for (int i = 0; i < numberExpenses; i++) {
                TotalExpenses = TotalExpenses + myExpenses[i].getBillAmount();
            }
        }
        catch (NullPointerException e){
            System.out.println("Payments and or expenses file is not formatted correctly or not found: Null Exception for PrintTotalExpenses()");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Payments and or expenses file is not formatted correctly or not found: Array index Exception for PrintTotalExpenses()");
        }
        return "Total Expenses: " + TotalExpenses;
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
            System.out.println("Payments and or expenses file is not formatted correctly or not found: Null Exception for PrintTotalBalance()");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Payments and or expenses file is not formatted correctly or not found: Array Index Exception for PrintTotalBalance()");
        }
        r = "Total Balance " + TotalBalance;
        return r;
    }
}
