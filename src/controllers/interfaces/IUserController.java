package controllers.interfaces;

public interface IUserController {
    boolean createUser(String name, String surname, String gender);
    String getUser(int id);
    String getAllUsers();
    boolean deleteUser(int id);
}
