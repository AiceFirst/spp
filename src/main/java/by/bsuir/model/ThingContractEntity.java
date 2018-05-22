package by.bsuir.model;

import javax.persistence.*;

/**
 * Created by Tom on 15.05.2018.
 */
@Entity
@Table(name = "thing_contracts", schema = "mydb", catalog = "")
@IdClass(ThingServiceEntityPK.class)
public class ThingServiceEntity {
    private int thingId;
    private int contractId;

    @Id
    @Column(name = "thing_id")
    public int getthingId() {
        return thingId;
    }

    public void setthingId(int thingId) {
        this.thingId = thingId;
    }

    @Id
    @Column(name = "contract_id")
    public int getcontractId() {
        return contractId;
    }

    public void setcontractId(int contractId) {
        this.contractId = contractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThingServiceEntity that = (ThingServiceEntity) o;

        if (thingId != that.thingId) return false;
        if (contractId != that.contractId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = thingId;
        result = 31 * result + contractId;
        return result;
    }
}
