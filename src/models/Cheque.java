package models;

public class Cheque {
    private int id;
    private String date;
    private int uid;
    private int price;

    public Cheque() {

    }

    public Cheque(String date, int uid, int price) {
        setDate(date);
        setUid(uid);
        setPrice(price);
    }

    public Cheque(int id, String date, int uid, int price) {
        this(date, uid, price);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", uid='" + uid + '\'' +
                ", price=" + price +
                '}';
    }
}
