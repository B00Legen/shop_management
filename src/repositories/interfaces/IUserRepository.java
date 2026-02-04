package repositories.interfaces;

import models.User;
import models.Cheque;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    boolean deleteUser(int id);

    boolean createCheque(Cheque cheque);
    Cheque getCheque(int id);
    List<Cheque> getAllCheques();
    boolean deleteCheque(int id);
}
