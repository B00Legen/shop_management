package controllers.interfaces;

public interface IUserController {
    boolean createUser(String name, String surname, String gender);
    String getUser(int id);
    String getAllUsers();
    boolean deleteUser(int id);

    boolean createCheque(String date, int uid, int price);
    String getCheque(int id);
    String getAllCheques();
    boolean deleteCheque(int id);
}
