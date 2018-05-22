package by.bsuir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "contract", schema = "mydb", catalog = "")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "daysNumber")
    private String daysNumber;

    @Basic
    @Column(name = "cost_per_month")
    private Integer costPerMonth;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer owner;

    @Transient
    public boolean isValid() {

        return (
                title.length() > 0 &&
                        description.length() > 0 &&
                        daysNumber.length() > 0 &&
                        costPerMonth != null
        );
    }

    //
//    @ManyToMany
//    private Set<ThingEntity> things = new HashSet<ThingEntity>();

//    public Set<ThingEntity> getthings() {
//        return things;
//    }
//
//    public void setthings(Set<ThingEntity> things) {
//        this.things = things;
//    }

    public Employer getOwner() {
        return owner;
    }

    public void setOwner(Employer owner) {
        this.owner = owner;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getdaysNumber() {
        return daysNumber;
    }

    public void setdaysNumber(String daysNumber) {
        this.daysNumber = daysNumber;
    }

    public Integer getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(Integer costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

}
