package controllers;

import models.LastCheque;
import models.User;
import models.Cheque;
import controllers.interfaces.IUserController;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public boolean createUser(String name, String surname, String gender, String store) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, surname, male, store);

        return repo.createUser(user);
    }

    public String readUser() {
        List<User> users = repo.readUser();

        StringBuilder response = new StringBuilder();
        users.forEach(user -> response.append(user.toString()).append("\n"));

        return response.toString();
    }

    public String readUsersByStore(String store) {
        List<User> users = repo.readUsersByStore(store);

        StringBuilder response = new StringBuilder();
        users.forEach(user -> response.append(user.toString()).append("\n"));

        return response.toString();
    }

    public String readUser(int id) {
        User user = repo.readUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public boolean updateUser(int id, String name, String surname, String gender, String store) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(id, name, surname, male, store);

        return repo.updateUser(user);
    }

    public boolean deleteUser() {
        return repo.deleteUser();
    }

    public boolean deleteUser(int id) {
        return repo.deleteUser(id);
    }

    public boolean createCheque(String date, int uid, int price) {
        Cheque cheque = new Cheque(date, uid, price);

        return repo.createCheque(cheque);
    }

    public String readCheque() {
        List<Cheque> cheques = repo.readCheque();

        StringBuilder response = new StringBuilder();
        cheques.forEach(cheque -> response.append(cheque.toString()).append("\n"));

        return response.toString();
    }

    public String readLastCheque() {
        List<LastCheque> cheques = repo.readLastCheque();

        StringBuilder response = new StringBuilder();
        cheques.forEach(cheque -> response.append(cheque.toString()).append("\n"));

        return response.toString();
    }

    public String readCheque(int id) {
        Cheque cheque = repo.readCheque(id);

        return (cheque == null ? "Transaction was not found!" : cheque.toString());
    }

    public String readLastCheque(int id) {
        LastCheque cheque = repo.readLastCheque(id);

        return (cheque == null ? "User has no transactions!" : cheque.toString());
    }

    public boolean updateCheque(int id, String date, int uid, int price) {
        Cheque cheque = new Cheque(id, date, uid, price);

        return repo.updateCheque(cheque);
    }

    public boolean deleteCheque() {
        return repo.deleteCheque();
    }

    public boolean deleteCheque(int id) {
        return repo.deleteCheque(id);
    }
}
