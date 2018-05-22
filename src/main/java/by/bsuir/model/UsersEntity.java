package by.bsuir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users", schema = "mydb", catalog = "")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType= DiscriminatorType.STRING)
public class UsersEntity implements Serializable {


    public static String DEF_ROLE = "ROLE_ADMIN";

    @PrePersist
    public void prePersist(){
        if(roles.isEmpty())
            roles.add(DEF_ROLE);
        setDate(Timestamp.valueOf(LocalDateTime.now()));
    }


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    protected String username;

    protected String password;

    @Column(name = "type", insertable=false,  updatable=false)
    protected String type;

    protected String email;

    protected Byte enabled = 1;

    protected Timestamp date;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="authorities", joinColumns = {@JoinColumn(name="username",  referencedColumnName = "username")})
    @Column(name="authority", nullable=false)
    protected Set<String> roles = new HashSet<String>();

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "enabled")
    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Transient
    public boolean isValid() {

        return (
                username.length() > 0 &&
                        password.length() > 0 &&
                        email.length() > 0
                );
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", date=" + date +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
