package controllers.interfaces;

public interface IUserController {
    boolean createUser(String name, String surname, String gender, String store);
    String readUser();
    String readUsersByStore(String store);
    String readUser(int id);
    boolean updateUser(int id, String name, String surname, String gender, String store);
    boolean deleteUser();
    boolean deleteUser(int id);

    boolean createCheque(String date, int uid, int price);
    String readCheque();
    String readLastCheque();
    String readCheque(int id);
    String readLastCheque(int id);
    boolean updateCheque(int id, String date, int uid, int price);
    boolean deleteCheque();
    boolean deleteCheque(int id);
}
