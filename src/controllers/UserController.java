package controllers;

import models.ChequeFull;
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

    public boolean createUser(String name, String surname, String gender) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, surname, male);

        return repo.createUser(user);
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        users.forEach(user -> response.append(user.toString()).append("\n"));

        return response.toString();
    }

    public boolean deleteUser(int id) {
        return repo.deleteUser(id);
    }

    public boolean createCheque(String date, int uid, int price) {
        Cheque cheque = new Cheque(date, uid, price);

        return repo.createCheque(cheque);
    }

    public String getCheque(int id) {
        Cheque cheque = repo.getCheque(id);

        return (cheque == null ? "Transaction was not found!" : cheque.toString());
    }

    public String getAllCheques() {
        List<Cheque> cheques = repo.getAllCheques();

        StringBuilder response = new StringBuilder();
        cheques.forEach(cheque -> response.append(cheque.toString()).append("\n"));

        return response.toString();
    }

    public boolean deleteCheque(int id) {
        return repo.deleteCheque(id);
    }

    public String getLastCheque(int id) {
        ChequeFull cheque = repo.getLastCheque(id);

        return (cheque == null ? "User has no transactions!" : cheque.toString());
    }
}
