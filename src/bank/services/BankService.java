package bank.services;

import bank.accounts.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankService {
    private List<Account> accounts = new ArrayList<>();
    private Account loggedInAccount = null;

    public void createAccount(Scanner scanner) {
        System.out.print("Podaj imię i nazwisko: ");
        String ownerName = scanner.next();
        System.out.print("Podaj adres: ");
        String address = scanner.next();
        System.out.print("Podaj datę urodzenia (YYYY-MM-DD): ");
        String dateOfBirth = scanner.next();
        System.out.print("Podaj PESEL: ");
        String pesel = scanner.next();
        System.out.print("Podaj login: ");
        String login = scanner.next();
        System.out.print("Podaj hasło: ");
        String password = scanner.next();
        System.out.print("Podaj email: ");
        String email = scanner.next();
        System.out.print("Podaj początkowe saldo: ");
        double balance = scanner.nextDouble();

        Account account = new Account(ownerName, address, dateOfBirth, pesel, login, password, email, balance);
        accounts.add(account);

        System.out.println("Konto zostało utworzone pomyślnie! Numer konta: " + account.getAccountNumber());
        System.out.println("Numer karty: " + account.getCardNumber() + ", PIN: " + account.getPin());
    }

    public void login(Scanner scanner) {
        System.out.print("Podaj login: ");
        String login = scanner.next();
        System.out.print("Podaj hasło: ");
        String password = scanner.next();

        for (Account account : accounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                loggedInAccount = account;
                System.out.println("Zalogowano pomyślnie. Witaj, " + account.getOwnerName() + "!");
                return;
            }
        }
        System.out.println("Nieprawidłowy login lub hasło.");
    }

    public void logout() {
        if (loggedInAccount == null) {
            System.out.println("Nie jesteś zalogowany.");
        } else {
            System.out.println("Wylogowano użytkownika: " + loggedInAccount.getOwnerName());
            loggedInAccount = null;
        }
    }

    public void transferMoney(Scanner scanner) {
        if (loggedInAccount == null) {
            System.out.println("Musisz być zalogowany, aby wykonać przelew.");
            return;
        }

        System.out.print("Podaj numer konta odbiorcy: ");
        String recipientAccountNumber = scanner.next();

        Account recipient = findAccount(recipientAccountNumber);
        if (recipient == null) {
            System.out.println("Nie znaleziono konta odbiorcy.");
            return;
        }

        System.out.print("Podaj kwotę do przelania: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Kwota musi być większa od zera.");
            return;
        }

        if (loggedInAccount.getBalance() < amount) {
            System.out.println("Niewystarczające środki na koncie.");
            return;
        }

        loggedInAccount.setBalance(loggedInAccount.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        System.out.println("Przelew zakończony sukcesem! Nowe saldo: " + loggedInAccount.getBalance());
    }

    private Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    public double getLoggedInAccountBalance() {
        if (loggedInAccount != null) {
            return loggedInAccount.getBalance();
        }
        return 0.0;
    }
}