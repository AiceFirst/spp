package by.bsuir.contract;


import by.bsuir.model.Employer;
import by.bsuir.model.ThingEntity;

import java.util.List;

public interface ThingService {

    public ThingEntity save(ThingEntity thingEntity);

    public ThingEntity getById(Integer id);

    public boolean delete(Integer id);

    public List<ThingEntity> getList();


    public List<ThingEntity> getemployerthings(Employer employer);

}