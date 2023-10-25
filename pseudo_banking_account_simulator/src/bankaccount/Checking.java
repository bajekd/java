
package bankaccount;

public class Checking extends Account{
    //prosperities specific for chcecking account
     private int debitCardNumber, debitCardPIN;
    //constructor
    public Checking(String name, String sSN, double initDeposit) {
        super(name, sSN, initDeposit);
        accountNumber = "2" + accountNumber;
        setDebitCard();
    }
    
    //list methods specific for savings class:
      public void showInfo() {
          super.showInfo();
           System.out.println("Your checking account features: " +
                            "\n Debit Card Number : " + debitCardNumber +
                            "\n Debit Card PIN: " + debitCardPIN);
      }
      
      private void setDebitCard() {
          debitCardNumber = (int) (Math.random() * Math.pow(10,12));
          debitCardPIN = (int) (Math.random() * Math.pow(10,4));
      }
      
      @Override
      public void setRate() {
          rate = getBaseRate() * .15;
}
      
}

