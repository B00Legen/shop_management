package models;

public class LastCheque {
    private int uid;
    private String name;
    private int cid;
    private int price;
    private String date;

    public LastCheque() {

    }

    public LastCheque(int uid, String name, int cid, int price, String date) {
        setUid(uid);
        setName(name);
        setCid(cid);
        setPrice(price);
        setDate(date);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LastCheque{" +
                "user_id=" + uid +
                ", full_name='" + name + '\'' +
                ", cheque_id='" + cid + '\'' +
                ", price=" + price + '\'' +
                ", date=" + date +
                '}';
    }
}
