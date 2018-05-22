package by.bsuir.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


public class ThingServiceEntityPK implements Serializable {
    private int thingId;
    private int contractId;

    @Column(name = "thing_id")
    @Id
    public int getthingId() {
        return thingId;
    }

    public void setthingId(int thingId) {
        this.thingId = thingId;
    }

    @Column(name = "contract_id")
    @Id
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

        ThingServiceEntityPK that = (ThingServiceEntityPK) o;

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
