package by.bsuir.model;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "user_contracts", schema = "mydb", catalog = "")
@IdClass(UserServiceEntityPK.class)
public class UserServiceEntity {
    private int sUserId;
    private int scontractId;
    private Timestamp date;

    @Id
    @Column(name = "s_user_id")
    public int getsUserId() {
        return sUserId;
    }

    public void setsUserId(int sUserId) {
        this.sUserId = sUserId;
    }

    @Id
    @Column(name = "s_contract_id")
    public int getscontractId() {
        return scontractId;
    }

    public void setscontractId(int scontractId) {
        this.scontractId = scontractId;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserServiceEntity that = (UserServiceEntity) o;

        if (sUserId != that.sUserId) return false;
        if (scontractId != that.scontractId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sUserId;
        result = 31 * result + scontractId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
