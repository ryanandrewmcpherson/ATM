# ATM 2.0

## User Inputs
     16 digit debit card number (4234895478653427,4234854741235476)
     4 digit pin (1111,2222)
     Deposit Amount
     Withdrawal Amount 
     Customer Number (12345,54321)
     New Pin
     Confirm New Pin
     Account to Transfer From
     Account to Transfer To

## Functions
     1.AtmLauncher initializes all the instance variables needed for the class, including a list to hold customer objects, customer objects, lists to hold the account objects inside the customer objects, and the accounts themselves. In this version there are two customers, each with a savings and checking account, both of type account, that currently exhibit the same behavior. 
     2.AtmLauncher initializes a new ATM instance variable. 
     3.AtmLauncherlaunches the operatingSystem method in the Atm class with the new Atm that was created...feeding in the list of customers as an argument
     4. The operatingSystem prints the initial greeting using the printInitGreeting method
     5. The operatingSystem calls scanDebitCard method which prompts the user to input their 16 digit debit card and attempts to match the user entry to one of the customers on the list given to it as an argument.
     6. The operating system declares three local variables:Cust cust, in which it restores the customer object returned by the scanDebitCard method, boolean anotherTransaction, which is the condition for the game loop to repeat (set to true by default) and int numTransactions which keeps track of the number of transactions in the session for the sake of telling the user to enter vs re-enter their pin. 
     7. The game loop launches and the user is asked to enter or re-enter their pin
     8. verifyPin launches as an argument for mainMenu. verifyPin scans the user input and if it is of pin format, it takes the cust object previously saved by the OS and compares  the pin entered to the pin stored in that object. If the pin is verified, verifyPin then passes the cust object to the main menu as an argument.
     9. mainMenu launches and greets the customer by name, then it prints the menu options, then validates the input to make sure its an option.
         1. Balance
           -acctMenu (takes in a cust, returns an acct) is called as an argument to the balance method call. It prints the accounts stored inside the list of accounts in a customer object and prompts the user to select one. It then feeds the account selected by the user into the balance method.
           -balance the balance method takes the account that is given to it by acctMenu and prints the balance
         2. Deposit
           -acctMenu does its magic here also, feeding the account the user selects on to the depositPrompt
           -depositPrompt prints prompting the user to enter a deposit amount and then sends the account info along to the deposit method
           -the deposit method validates the deposit amount and then completes the deposit and prints a success message if the amount is valid
         3. Withdrawal
           -acctMenu does its magic here once again, feeding the account the user selects on to the withdrawalPrompt
           -withdrawalPrompt prints prompting the user to enter a withdrawal amount and then sends the account info along to the withdrawal method
           -the withdraw method validates the withdrawal amount and then complets the withdrawal and prints a success message if the amount is valid 
         4. Transfer
           -calls the transferFrom method as an argument to the transfer method with the argument acctMenu so that the account that is being transferred from can be collected from the user and sent to the transfer method
           -calls the transferTo method as second argument to the transfer method with the argument acctMenu so that the account that is being transferred to can be collected from the user and sent to the transfer method
           -prints the transferAmtPrompt and then validates the amount being transfered to make sure it is less than the balance in the transfer from account
           -if the transfer amount is valid completes the transfer and prints a success message
         5. Reset Pin 
           -prompts the user to verify their customer number and checks if the entry is valid
           -prompts the user to enter their current pin number and checks if the entry is valid
           -prompts the user to enter a new pin number as an argument for a comparison to the confirm new pin number
           -prompts the user to confirm their new pin number and compares it to the new pin number they entered the first time,
            loop completes if the pin numbers match, otherwise it prompts the user to enter a new pin and confirm again
           -if the new pin and confirm new pin match it changes the pin to the new pin and prints a success message
     10. The prompt for another transaction repeats... if y, another transaction is left at true, if anything besides y is entered, another transaction is set to false.
     11. numTransactions is incremented
     12. mainMenu launches again or it doesn't



# ATM 1.0

## User Inputs
	4 Digit Debit Card (4123)
	4 Digit Pin (1111)
	Deposit Amount
	Withdrawal Amount
	Customer Number (12345)
	New Pin 
	Confirm New Pin

## Functions
### 1. Debit Card Validation
### 2. Pin Code Validation
### 3. Menu
      1. Balance
      2. Deposit
      3. Withdrawal
      4. Reset Pin

## Special Features
	1. Input Validation at every Step
	2. Exit available at every step
	3. Session Terminates after 3 invalid attempts at every step
