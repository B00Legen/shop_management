package repositories.interfaces;

import models.User;
import models.Cheque;
import models.LastCheque;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    List<User> readUser();
    List<User> readUsersByStore(String store);
    User readUser(int id);
    boolean updateUser(User user);
    boolean deleteUser();
    boolean deleteUser(int id);

    boolean createCheque(Cheque cheque);
    List<Cheque> readCheque();
    List<LastCheque> readLastCheque();
    Cheque readCheque(int id);
    LastCheque readLastCheque(int id);
    boolean updateCheque(Cheque cheque);
    boolean deleteCheque();
    boolean deleteCheque(int id);
}
