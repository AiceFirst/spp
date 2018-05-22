package by.bsuir.contract;

import by.bsuir.model.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface ReportGenerationService {
    void generateUserPaymentsReport(List<PaymentEntity> payments) throws IOException;

    void generateUserServicesReport(User user) throws IOException;

    void generateUserService(OutputStream stream, User user, ThingEntity thing, Date date) throws Exception;

    void generatePaymentQuote(User user, PaymentEntity payment, Employer employer, Date date) throws Exception;

    void generateListOfAllemployercontracts(Employer employer) throws IOException;
}
