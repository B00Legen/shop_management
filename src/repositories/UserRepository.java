package repositories;

import data.interfaces.IDB;
import models.User;
import models.Cheque;
import models.LastCheque;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;  // Dependency Injection

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(name,surname,gender,store) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());
            st.setString(4, user.getStore());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<User> readUser() {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,store FROM users ORDER BY id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("store"));

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> readUsersByStore(String store) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,store FROM users WHERE store=? ORDER BY id";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, store);

            ResultSet rs = st.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("store"));

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public User readUser(int id) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,store FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("store"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateUser(User user) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "UPDATE users SET name=?,surname=?,gender=?,store=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());
            st.setString(4, user.getStore());
            st.setInt(5, user.getId());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser() {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "TRUNCATE TABLE users RESTART IDENTITY";
            PreparedStatement st = con.prepareStatement(sql);

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean createCheque(Cheque cheque) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO cheques(date,uid,price) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, cheque.getDate());
            st.setInt(2, cheque.getUid());
            st.setInt(3, cheque.getPrice());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Cheque> readCheque() {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT id,date,uid,price FROM cheques ORDER BY id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Cheque> cheques = new ArrayList<>();
            while (rs.next()) {
                Cheque cheque = new Cheque(rs.getInt("id"),
                        rs.getString("date"),
                        rs.getInt("uid"),
                        rs.getInt("price"));

                cheques.add(cheque);
            }

            return cheques;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<LastCheque> readLastCheque() {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT u.id AS user_id, u.name || ' ' || u.surname AS full_name, c.id AS transaction_id, c.price, c.date FROM users u LEFT JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY uid ORDER BY date DESC) AS rn FROM cheques) c ON u.id = c.uid AND c.rn = 1 ORDER BY u.id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<LastCheque> cheques = new ArrayList<>();
            while (rs.next()) {
                LastCheque cheque = new LastCheque(rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getInt("transaction_id"),
                        rs.getInt("price"),
                        rs.getString("date"));

                cheques.add(cheque);
            }

            return cheques;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Cheque readCheque(int id) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT id,date,uid,price FROM cheques WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Cheque(rs.getInt("id"),
                        rs.getString("date"),
                        rs.getInt("uid"),
                        rs.getInt("price"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public LastCheque readLastCheque(int id) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "SELECT u.id AS user_id, u.name || ' ' || u.surname AS full_name, c.id AS transaction_id, c.price, c.date FROM users u LEFT JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY uid ORDER BY date DESC) AS rn FROM cheques) c ON u.id = c.uid AND c.rn = 1 WHERE uid = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new LastCheque(rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getInt("transaction_id"),
                        rs.getInt("price"),
                        rs.getString("date"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateCheque(Cheque cheque) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "UPDATE cheques SET date=?,uid=?,price=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, cheque.getDate());
            st.setInt(2, cheque.getUid());
            st.setInt(3, cheque.getPrice());
            st.setInt(4, cheque.getId());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCheque() {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "TRUNCATE TABLE users RESTART IDENTITY";
            PreparedStatement st = con.prepareStatement(sql);

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCheque(int id) {
        Connection con;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM cheques WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }
}
