package by.bsuir.model;

import javax.persistence.*;


@Entity
@Table(name = "user_employers", schema = "mydb", catalog = "")
@IdClass(UserEmployerEntityPK.class)
public class UserEmployerEntity {
    private int userId;
    private int employerId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "employer_id")
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

        UserEmployerEntity that = (UserEmployerEntity) o;

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
