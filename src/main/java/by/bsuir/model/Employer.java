package by.bsuir.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("employer")
@SecondaryTable(schema = "mydb", name = "employer_info", pkJoinColumns = {@PrimaryKeyJoinColumn})
public class Employer extends UsersEntity {

    public static String DEF_ROLE = "ROLE_employer";

    @PrePersist
    public void prePersist() {
        if (roles.isEmpty())
            roles.add(DEF_ROLE);
        setDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ThingEntity> things = new ArrayList<ThingEntity>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContractEntity> contracts = new ArrayList<ContractEntity>();

    @Column(name = "title", table = "employer_info")
    private String title;

    @Column(name = "description", table = "employer_info")
    private String description;

    public List<ThingEntity> getthings() {
        return things;
    }

    public void setthings(List<ThingEntity> things) {
        this.things = things;
    }

    public List<ContractEntity> getcontracts() {
        return contracts;
    }

    public void setcontracts(List<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
