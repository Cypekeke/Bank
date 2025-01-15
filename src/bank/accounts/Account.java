package bank.accounts;

import java.util.Random;

public class Account {
    private String accountNumber;
    private String ownerName;
    private String address;
    private String dateOfBirth;
    private String pesel;
    private String login;
    private String password;
    private String email;
    private String cardNumber;
    private String pin;
    private double balance;

    public Account(String ownerName, String address, String dateOfBirth, String pesel, String login, String password, String email, double balance) {
        this.accountNumber = generateNumber(26);
        this.ownerName = ownerName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.pesel = pesel;
        this.login = login;
        this.password = password;
        this.email = email;
        this.cardNumber = generateNumber(6);
        this.pin = generateNumber(4);
        this.balance = balance;
    }

    private String generateNumber(int length) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
