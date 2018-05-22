package by.bsuir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "thing", schema = "mydb", catalog = "")
public class ThingEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id = null;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer owner;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "contractId")
    private Integer contractId;

    @Basic
    @Column(name = "cost_per_month")
    private String costPerMonth;

    @Transient
    public boolean iscontractInThingServices(ContractEntity contractEntity)
    {
        for(ContractEntity contract : contracts){
            if(contract.getId().equals(contractEntity.getId())){
                return true;
            }
        }
        return false;
    }

    @Transient
    public boolean isValid() {

        return (
                title.length() > 0 &&
                        costPerMonth.length() > 0 &&
                        contractId != null
        );
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name="thing_contracts",
            joinColumns=@JoinColumn(name="thing_id"),
            inverseJoinColumns=@JoinColumn(name="contract_id"))
    private Set<ContractEntity> contracts = new HashSet<ContractEntity>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer costPerMonth) {
        this.contractId = costPerMonth;
    }

    public String getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(String description) {
        this.costPerMonth = description;
    }

    public Employer getOwner() {
        return owner;
    }

    public void setOwner(Employer owner) {
        this.owner = owner;
    }

    public Set<ContractEntity> getcontracts() {
        return contracts;
    }

    public void setcontracts(Set<ContractEntity> contracts) {
        this.contracts = contracts;
    }
}
