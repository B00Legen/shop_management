import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController controller;

    public MyApplication(IUserController controller) {
        this.controller = controller;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to shop management system");
        System.out.println("Select option:");
        System.out.println("1. Get all employees");
        System.out.println("2. Get employee by id");
        System.out.println("3. Create employee");
        System.out.println("4. Delete employee");
        System.out.println("5. Get all transactions");
        System.out.println("6. Get transaction by id");
        System.out.println("7. Create transaction");
        System.out.println("8. Delete transaction");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-4): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                // All employees are referred as users
                int option = scanner.nextInt();
                switch (option) {
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserByIdMenu(); break;
                    case 3: createUserMenu(); break;
                    case 4: deleteUserMenu(); break;
                    case 5: getAllChequesMenu(); break;
                    case 6: getChequeByIdMenu(); break;
                    case 7: createChequeMenu(); break;
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
    }

    public void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    public void getUserByIdMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String response = controller.getUser(id);
        System.out.println(response);
    }

    public void createUserMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female)");
        String gender = scanner.next();

        boolean response = controller.createUser(name, surname, gender);
        System.out.println(response ? "User was created!" : "User creation was failed!");
    }

    public void deleteUserMenu() {
        while(true) {
            System.out.println("Please enter id");
            int id = scanner.nextInt();

            String response = controller.getUser(id);
            System.out.println("Is the following user correct? (y/n): " + response);
            String answer = scanner.next();

            if (answer.equals("y")) {
                if (controller.deleteUser(id)) {
                    System.out.println("User successfully deleted!");
                    break;
                } else {
                    System.out.println("Deleting user failed!");
                }
            }

            System.out.println("Try again? (y/n): ");
            answer = scanner.next();

            if (!answer.equals("y")) {
                break;
            }
        }
    }

    public void getAllChequesMenu() {
        String response = controller.getAllCheques();
        System.out.println(response);
    }

    public void getChequeByIdMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();

        String response = controller.getCheque(id);
        System.out.println(response);
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

    public void deleteChequeMenu() {
        while(true) {
            System.out.println("Please enter id");
            int id = scanner.nextInt();

            String response = controller.getCheque(id);
            System.out.println("Is the following transaction correct? (y/n): " + response);
            String answer = scanner.next();

            if (answer.equals("y")) {
                if (controller.deleteCheque(id)) {
                    System.out.println("Cheque successfully deleted!");
                    break;
                } else {
                    System.out.println("Deleting cheque failed!");
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
