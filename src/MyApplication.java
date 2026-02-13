import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController controller;

    public MyApplication(IUserController controller) {
        this.controller = controller;
    }

    private void mainMenu(int num) {
        System.out.println();
        System.out.println("Welcome to Shop Management System");
        System.out.println("Select option:");
        if (num == 0) {
            System.out.println("1. Manage employees");
            System.out.println("2. Manage transactions");
        } else if (num == 1) {
            System.out.println("1. Create employee");
            System.out.println("2. Read all employees");
            System.out.println("3. Read employees by store");
            System.out.println("4. Read employee by id");
            System.out.println("5. Update employee by id");
            System.out.println("6. Delete all employees");
            System.out.println("7. Delete employee by id");
        } else if (num == 2) {
            System.out.println("1. Create transaction");
            System.out.println("2. Read all transactions");
            System.out.println("4. Read all last transactions");
            System.out.println("3. Read transaction by id");
            System.out.println("5. Read last transaction by id");
            System.out.println("6. Update transaction by id");
            System.out.println("7. Delete all transactions");
            System.out.println("8. Delete transaction by id");
        }
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option: ");
    }

    public void start() {
        while (true) {
            mainMenu(0);
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1: userMenu(); break;
                    case 2: chequeMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("*************************");
        }
    }

    public void userMenu() {
        mainMenu(1);
        try {
            int option = scanner.nextInt();
            switch (option) {
                case 1: createUserMenu(); break;
                case 2: readAllUsersMenu(); break;
                case 3: readUsersByStoreMenu(); break;
                case 4: readUserMenu(); break;
                case 5: updateUserMenu(); break;
                case 6: deleteAllUsersMenu();break;
                case 7: deleteUserMenu(); break;
                default: return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be integer: " + e);
            scanner.nextLine(); // to ignore incorrect input
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*************************");
    }

    public void chequeMenu() {
        mainMenu(2);
        try {
            int option = scanner.nextInt();
            switch (option) {
                case 1: createChequeMenu(); break;
                case 2: readAllChequesMenu(); break;
                case 3: readAllLastChequesMenu(); break;
                case 4: readChequeMenu(); break;
                case 5: readLastChequeMenu(); break;
                case 6: updateChequeMenu(); break;
                case 7: deleteAllChequesMenu(); break;
                case 8: deleteChequeMenu(); break;
                default: return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be integer: " + e);
            scanner.nextLine(); // to ignore incorrect input
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*************************");
    }

    public void createUserMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female)");
        String gender = scanner.next();
        System.out.println("Please enter store");
        String store = scanner.next();

        boolean response = controller.createUser(name, surname, gender, store);
        System.out.println(response ? "Employee was created!" : "Employee creation was failed!");
    }

    public void readAllUsersMenu() {
        String response = controller.readUser();
        System.out.println(response);
    }

    public void readUsersByStoreMenu() {
        System.out.println("Please enter store");
        String store = scanner.next();

        String response = controller.readUsersByStore(store);
        System.out.println(response);
    }

    public void readUserMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String response = controller.readUser(id);
        System.out.println(response);
    }

    public void updateUserMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String user = controller.readUser(id);
        System.out.println("Is the following user correct? (y/n): " + user);
        String answer = scanner.next();

        if (!answer.equals("y")) {
            return;
        }

        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female)");
        String gender = scanner.next();
        System.out.println("Please enter store");
        String store = scanner.next();

        boolean response = controller.updateUser(id, name, surname, gender, store);
        System.out.println(response ? "User was updated!" : "User update was failed!");
    }

    public void deleteAllUsersMenu() {
        System.out.println("Are you sure? (y/n): ");
        String answer = scanner.next();

        if (answer.equals("y")) {
            System.out.println((controller.deleteUser() ? "All employees successfully deleted!" : "Deleting all employees failed!"));
        }
    }

    public void deleteUserMenu() {
        while(true) {
            System.out.println("Please enter id");
            int id = scanner.nextInt();

            String response = controller.readUser(id);
            System.out.println("Is the following employee correct? (y/n): " + response);
            String answer = scanner.next();

            if (answer.equals("y")) {
                if (controller.deleteUser(id)) {
                    System.out.println("Employee successfully deleted!");
                    break;
                } else {
                    System.out.println("Deleting employee failed!");
                }
            }

            System.out.println("Try again? (y/n): ");
            answer = scanner.next();

            if (!answer.equals("y")) {
                break;
            }
        }
    }

    public void createChequeMenu() {
        System.out.println("Please enter date (yyyy.mm.dd/hh:mm)");
        String date = scanner.next();
        System.out.println("Please enter employee id");
        int uid = scanner.nextInt();
        System.out.println("Please enter price");
        int price = scanner.nextInt();

        boolean response = controller.createCheque(date, uid, price);
        System.out.println(response ? "Transaction was created!" : "Transaction creation was failed!");
    }

    public void readAllChequesMenu() {
        String response = controller.readCheque();
        System.out.println(response);
    }

    public void readAllLastChequesMenu() {
        String response = controller.readLastCheque();
        System.out.println(response);
    }

    public void readChequeMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String response = controller.readCheque(id);
        System.out.println(response);
    }

    public void readLastChequeMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String response = controller.readLastCheque(id);
        System.out.println(response);
    }

    public void updateChequeMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String cheque = controller.readCheque(id);
        System.out.println("Is the following transaction correct? (y/n): " + cheque);
        String answer = scanner.next();

        if (!answer.equals("y")) {
            return;
        }

        System.out.println("Please enter date (yyyy.mm.dd/hh:mm)");
        String date = scanner.next();
        System.out.println("Please enter employee id");
        int uid = scanner.nextInt();
        System.out.println("Please enter price");
        int price = scanner.nextInt();

        boolean response = controller.updateCheque(id, date, uid, price);
        System.out.println(response ? "Transaction was created!" : "Transaction creation was failed!");
    }

    public void deleteAllChequesMenu() {
        System.out.println("Are you sure? (y/n): ");
        String answer = scanner.next();

        if (answer.equals("y")) {
            System.out.println((controller.deleteCheque() ? "All transactions successfully deleted!" : "Deleting all transactions failed!"));
        }
    }

    public void deleteChequeMenu() {
        while(true) {
            System.out.println("Please enter id");
            int id = scanner.nextInt();

            String response = controller.readCheque(id);
            System.out.println("Is the following transaction correct? (y/n): " + response);
            String answer = scanner.next();

            if (answer.equals("y")) {
                if (controller.deleteCheque(id)) {
                    System.out.println("Transaction successfully deleted!");
                    break;
                } else {
                    System.out.println("Deleting transaction failed!");
                }
            }

            System.out.println("Try again? (y/n): ");
            answer = scanner.next();

            if (!answer.equals("y")) {
                break;
            }
        }
    }
}
