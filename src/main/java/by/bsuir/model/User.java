package by.bsuir.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("user")
@SecondaryTable(schema = "mydb",name="user_info", pkJoinColumns={@PrimaryKeyJoinColumn})
public class User extends UsersEntity {

    public static String DEF_ROLE = "ROLE_USER";

    @PrePersist
    public void prePersist(){
        if(roles.isEmpty())
            roles.add(DEF_ROLE);
        setDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_contracts",
            joinColumns=@JoinColumn(name="s_user_id"),
            inverseJoinColumns=@JoinColumn(name="s_contract_id"))
    private Set<ContractEntity> contracts = new HashSet<ContractEntity>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_employers",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="employer_id"))
    private Set<Employer> employers = new HashSet<Employer>();

    public Set<ContractEntity> getcontracts() {
        return contracts;
    }

    public void setcontracts(Set<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    public Set<Employer> getemployers() {
        return employers;
    }

    public void setemployers(Set<Employer> employers) {
        this.employers = employers;
    }

    @Column(name = "fio", table="user_info")
    private String fio;

    @Column(name = "city", table="user_info")
    private String city;

    @Column(name = "other", table="user_info")
    private String other;

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Transient
    public boolean isemployerInUser(Employer employer)
    {
        for(Employer op : employers){
            if(op.getId().equals(employer.getId())){
                return true;
            }
        }
        return false;
    }

    @Transient
    public boolean iscontractInUser(ContractEntity contractEntity)
    {
        for(ContractEntity contract : contracts){
            if(contract.getId().equals(contractEntity.getId())){
                return true;
            }
        }
        return false;
    }

}
