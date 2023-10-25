
package bankaccount;

import java.util.LinkedList;
import java.util.List;



public class Bankaccount {

    public static void main(String[] args) {
        List<Account> accounts = new LinkedList<Account>();

        // Read a CSV file and create accounts based on that data
        String file = "C:\\Users\\Bajek\\Downloads\\NewBankAccounts.csv";
        List<String[]> newAccountHolders = utilities.CSV.read(file);
        for (String[] accountHolder: newAccountHolders) {
            String name = accountHolder[0];
            String sSN = accountHolder[1];
            String accountType = accountHolder[2];
            double initDeposit = Double.parseDouble(accountHolder[3]);
            
            if (accountType.equals("Savings") ) {
                accounts.add(new Savings(name, sSN, initDeposit) );
            }
            
            else if (accountType.equals("Checking") ) {
                 accounts.add(new Checking(name, sSN, initDeposit) );
            }
            
            else {
                System.out.println("ERROR READING ACCOUNT TYPE");
            }
            
        } 
        

        for (Account acc : accounts) {
            System.out.println("------------------------------------------");
            acc.showInfo() ;
        }
    }
    
}
