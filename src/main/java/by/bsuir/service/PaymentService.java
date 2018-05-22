package by.bsuir.contract;


import by.bsuir.model.PaymentEntity;

import java.util.List;

/**
 * Created by Tom on 02.04.2018.
 */
public interface PaymentService {

    public PaymentEntity addPayment(PaymentEntity paymentEntity);

    public List<PaymentEntity> getUserPayments(Integer user_id) ;

}
