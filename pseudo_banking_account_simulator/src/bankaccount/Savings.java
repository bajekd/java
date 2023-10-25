
package bankaccount;


public class Savings extends Account{
    //prosperities specific for savings account:
    private int safetyDepositBoxKey, safetyDepositBoxID;
    
    //contructor
    public Savings(String name, String sSN, double initDeposit) {
        super(name, sSN, initDeposit);
        accountNumber = "1" + accountNumber;
        setSafetyDepositBox();        
    }
     
    //list methods specific for savings class:
      public void showInfo() {
          super.showInfo();
          System.out.println("Your savings account features: " +
                            "\n Safety Deposit Box ID: " + safetyDepositBoxID +
                            "\n Safety Deposit Box Key: " + safetyDepositBoxKey);
      }
      
      private void setSafetyDepositBox(){
          safetyDepositBoxID = (int) (Math.random() * Math.pow(10,3));
          safetyDepositBoxKey = (int) (Math.random() * Math.pow(10,4));
      }
      
      @Override
      public void setRate() {
          rate = getBaseRate() - .25;
       }
    
}
