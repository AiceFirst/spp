package by.bsuir.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


public class UserEmployerEntityPK implements Serializable {
    private int userId;
    private int employerId;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "employer_id")
    @Id
    public int getemployerId() {
        return employerId;
    }

    public void setemployerId(int employerId) {
        this.employerId = employerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEmployerEntityPK that = (UserEmployerEntityPK) o;

        if (userId != that.userId) return false;
        if (employerId != that.employerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + employerId;
        return result;
    }
}
