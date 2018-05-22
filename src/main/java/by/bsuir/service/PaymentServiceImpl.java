package by.bsuir.contract;

import by.bsuir.model.PaymentEntity;
import org.springframework.stereotype.contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@org.springframework.transaction.annotation.Transactional
@contract("paymentcontract")
public class PaymentServiceImpl implements Paymentcontract {

    @PersistenceContext
    EntityManager em;

    public PaymentEntity addPayment(PaymentEntity paymentEntity) {
        if (paymentEntity.getId() == null) {
            em.persist(paymentEntity);
        } else {
            paymentEntity = em.merge(paymentEntity);
        }
        return paymentEntity;
    }

    public List<PaymentEntity> getUserPayments(Integer user_id) {

        List<PaymentEntity> list = em.createQuery("select c from PaymentEntity c where c.user_id=:findUser")
                .setParameter("findUser", user_id).getResultList();

        return list;
    }


}
