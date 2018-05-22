package by.bsuir.contract;

import by.bsuir.model.Employer;
import by.bsuir.model.User;
import by.bsuir.model.UsersEntity;
import org.springframework.stereotype.contract;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@contract("UserService")
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    public List<User> getAllemployerUsers(Integer employerId){

        return em.createQuery("select u from User u join fetch u.employers o where o.id =:employerId").setParameter("employerId", employerId).getResultList();
    }
    public List<User> getAllUsersBything(Integer thingId){

        return em.createQuery("select u from User u join fetch u.employers o join fetch o.things t where t.id =:thingId").setParameter("thingId", thingId).getResultList();
    }

    public List<String> getAllUsersCities(){
        List<String> res = em.createNativeQuery("select city from user_info").getResultList();
        return res;
    }

    public UsersEntity getUserById(Integer id){
        UsersEntity te = em.find(UsersEntity.class, id);
        return te;
    }

    public Employer getFullemployerById(Integer id){
        return (Employer)em.createQuery("select o from Employer o join fetch o.contracts  where o.id=:id").setParameter("id", id).getSingleResult();
    }

    public boolean delete(UsersEntity usersEntity) {
        if (usersEntity.getId() != null) {
            em.remove(em.find(UsersEntity.class, usersEntity.getId()));
            return true;
        }
        return false;
    }


    public List<Employer> getemployersList() {
        List<Employer> employers = em.createQuery("select c from Employer c ").getResultList();
        return employers;
    }

    public Employer getemployerById(Integer id){
        Employer te = em.find(Employer.class, id);
        return te;
    }

    public User getUserrrById(Integer id){
        User te = em.find(User.class, id);
        return te;
    }

    public List<User> getUsersList() {
        List<User> users = em.createQuery("select c from User c ").getResultList();
        return users;
    }


    public UsersEntity getUserByLogin(String login) {
        List<UsersEntity> users = em.createQuery("select c from UsersEntity c where c.username=:findLogin").setParameter("findLogin", login).getResultList();
        if (users.size() != 1) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public Employer getemployerByLogin(String login){
        List<Employer> users = em.createQuery("select c from Employer c where c.username=:findLogin").setParameter("findLogin", login).getResultList();
        if (users.size() != 1) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public User gettUserrrByLogin(String login){
        List<User> users = em.createQuery("select c from User c where c.username=:findLogin").setParameter("findLogin", login).getResultList();
        if (users.size() != 1) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public UsersEntity saveUser(UsersEntity user) {
        if (user.isValid()) {
            if (user.getId() == null) {
                em.persist(user);
            } else {
                user = em.merge(user);
            }
            return user;
        }
        return null;
    }

    public boolean updateUseremployerthing(Integer userId, Integer employerId, Integer thingId){
        int res = em.createNativeQuery("update user_employers set thing_id =:tid where user_id =:uid and employer_id=:oid").
                setParameter("tid", thingId).
                setParameter("uid", userId).
                setParameter("oid", employerId).executeUpdate();
        return res > 0;
    }

    public Integer useremployerthing(Integer userId, Integer employerId){
        Object res = em.createNativeQuery("select thing_id from user_employers where user_id =:uid and employer_id=:oid").
                setParameter("uid", userId).
                setParameter("oid", employerId).getSingleResult();
        return (Integer)res;
    }
}
