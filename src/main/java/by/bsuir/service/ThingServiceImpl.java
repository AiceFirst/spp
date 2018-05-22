package by.bsuir.contract;

import by.bsuir.model.Employer;
import by.bsuir.model.ThingEntity;
import org.springframework.stereotype.contract;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@contract("ThingService")
@Transactional
public class ThingServiceImpl implements ThingService {

    @PersistenceContext
    EntityManager em;

    public ThingEntity save(ThingEntity te) {
        if (te.getId() == null)
            em.persist(te);
        else
            te = em.merge(te);
        return te;
    }

    public ThingEntity getById(Integer id) {
        ThingEntity te = em.find(ThingEntity.class, id);
        return te;
    }

    public boolean delete(Integer id) {
        if (id != null) {

            int res = em.createNativeQuery("delete from thing where id =:tid").setParameter("tid", id).executeUpdate();
            return res > 0;

        }
        return false;
    }


    public List<ThingEntity> getemployerthings(Employer employer) {

        List<ThingEntity> things = em.createQuery("select c from ThingEntity c where c.owner.id=:findemployer").setParameter("findemployer", employer.getId()).getResultList();
        return things;
    }

    public List<ThingEntity> getList() {
        List<ThingEntity> things = em.createQuery("select c from ThingEntity c ").getResultList();
        return things;
    }


}
