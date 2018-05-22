package by.bsuir.contract;

import by.bsuir.model.ContractEntity;
import by.bsuir.model.Employer;
import org.springframework.stereotype.contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@org.springframework.transaction.annotation.Transactional
@contract("contractscontract")
public class ContractServiceImpl implements ContractService {

    @PersistenceContext
    EntityManager em;


    public List<ContractEntity> getemployercontracts(Employer employer){

        List<ContractEntity> contracts = em.createQuery("select c from ContractEntity c where c.owner.id=:findemployer").setParameter("findemployer", employer.getId()).getResultList();
        return contracts;
    }

    public ContractEntity save(ContractEntity contractEntity) {

        if (contractEntity.getId() == null) {
            em.persist(contractEntity);
        } else {
            contractEntity = em.merge(contractEntity);
        }
        return contractEntity;

    }

    public ContractEntity getById(Integer id){
        ContractEntity te = em.find(ContractEntity.class, id);
        return te;
    }


    public boolean delete(ContractEntity contractEntity) {
        if (contractEntity.getId() != null) {

            int res = em.createNativeQuery("delete from contract where id =:tid").setParameter("tid", contractEntity.getId()).executeUpdate();
            return res > 0;

//            em.remove(em.find(ContractEntity.class, contractEntity.getId()));
//            return true;
        }
        return false;
    }


}
