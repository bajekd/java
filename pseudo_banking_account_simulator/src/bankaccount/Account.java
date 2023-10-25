
package bankaccount;

public abstract class Account implements IBaseRate {
    //common properites for savings and checking  accounts
    private String name, sSN;
    private double balance;
    private static int index = 10000;
    protected String accountNumber; 
    protected double rate;
   
    
    //constructor
    public Account(String name, String sSN, double initDeposit) {
        this.name = name;
        this.sSN = sSN;
        balance = initDeposit;
        
        //set accountNumber
        index++;
        this.accountNumber = setAccountNumber();
        setRate();
    }
    
    public abstract void setRate();
    
    private String setAccountNumber() {
        String lastTwoOfSSN = sSN.substring(sSN.length() - 2, sSN.length());
        int uniqueID = index;
        int randomNumber = (int)(Math.random() * Math.pow(10,3));
        return lastTwoOfSSN + uniqueID + randomNumber;
    }
    
    public void compound() {
        double accruedInterest = balance * (rate/100);
        balance = balance + accruedInterest;
        System.out.println("Accrued interest: " + accruedInterest + " PLN");
        printBalance();
    }

    //list common  methods - transactions
    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Deposit PLN" + amount );
        printBalance();
    }
    
    public void withdraw(double amount) {
         balance = balance - amount;
         System.out.println("Withdrawing " + amount + " PLN");
         printBalance();
    }
    
    public void transfer(String toWhere, double amount) {
        balance = balance - amount; 
        System.out.println("Transfering " + amount + " PLN" + "  "  + toWhere);
        printBalance();
    }
    
    public void printBalance(){
        System.out.println("Your balance is now: " + balance + " PLN");
    }
    
     public void showInfo() {
        System.out.println("Name: " + name + "\nAccount number: " + accountNumber +
                           "\nBalance: " + balance + " PLN" +
                           "\nRate: " + rate + "%");
    }
   
 
}
