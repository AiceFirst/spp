package by.bsuir.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


public class UserServiceEntityPK implements Serializable {
    private int sUserId;
    private int scontractId;

    @Column(name = "s_user_id")
    @Id
    public int getsUserId() {
        return sUserId;
    }

    public void setsUserId(int sUserId) {
        this.sUserId = sUserId;
    }

    @Column(name = "s_contract_id")
    @Id
    public int getscontractId() {
        return scontractId;
    }

    public void setscontractId(int scontractId) {
        this.scontractId = scontractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserServiceEntityPK that = (UserServiceEntityPK) o;

        if (sUserId != that.sUserId) return false;
        if (scontractId != that.scontractId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sUserId;
        result = 31 * result + scontractId;
        return result;
    }
}
