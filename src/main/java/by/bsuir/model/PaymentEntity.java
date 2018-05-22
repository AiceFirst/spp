package by.bsuir.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "payment", schema = "mydb", catalog = "")
public class PaymentEntity implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id = null;

    @Basic
    @Column(name = "employer_id")
    private Integer employer_id;

    @Basic
    @Column(name = "user_id")
    private Integer user_id;

    @Basic
    @Column(name = "amount")
    private Integer amount;

    @Basic
    @Column(name = "date")
    private Timestamp date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getemployer_id() {
        return employer_id;
    }

    public void setemployer_id(Integer employer_id) {
        this.employer_id = employer_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
