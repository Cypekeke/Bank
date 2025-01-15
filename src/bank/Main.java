package bank;

import bank.accounts.Account;
import bank.services.BankService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();

        while (true) {
            System.out.println("----------------------------");
            System.out.println("Centralny system bankowości");
            System.out.println("----------------------------");
            System.out.println("1. Utwórz konto");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Przelew pieniędzy");
            System.out.println("4. Wyloguj się");
            System.out.println("5. Wyjdź");
            System.out.print("Wybierz operacje: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowy wybór. Proszę wybrać którąś z powyższych operacji.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    bankService.createAccount(scanner);
                    break;
                case 2:
                    bankService.login(scanner);
                    if (bankService.isLoggedIn()) {
                        System.out.println("Twoje saldo: " + bankService.getLoggedInAccountBalance() + " PLN");
                    }
                    break;
                case 3:
                    bankService.transferMoney(scanner);
                    break;
                case 4:
                    bankService.logout();
                    break;
                case 5:
                    System.out.println("Dziękujemy za skorzystanie z systemu bankowego. Do widzenia!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Proszę wybrać którąś z powyższych operacji.");
            }
        }
    }
}