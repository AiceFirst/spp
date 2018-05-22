package by.bsuir.contract;


import by.bsuir.model.ContractEntity;
import by.bsuir.model.Employer;

import java.util.List;

public interface ContractService {

//    public List<ContractEntity> getUseremployercontracts(User user, Employer employer);
//
//    public void deletecontractFromUseremployersList(User user, Employer employer);
//
//    public List<ContractEntity> getemployercontracts(Employer employer);
//
//    public List<ContractEntity> getAllUserServices(User user);

    public ContractEntity getById(Integer id);

    public ContractEntity save(ContractEntity contractEntity);

    public boolean delete(ContractEntity contractEntity);

    public List<ContractEntity> getemployercontracts(Employer employer);
}
