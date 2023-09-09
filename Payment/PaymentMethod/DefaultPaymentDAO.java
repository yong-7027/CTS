package PaymentMethod;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DefaultPaymentDAO {
    ArrayList<DefaultPaymentMethod> getAll() throws SQLException;

    int insert(DefaultPaymentMethod defaultPaymentMethod) throws SQLException;

    int update(DefaultPaymentMethod defaultPaymentMethod) throws SQLException;

    int delete(DefaultPaymentMethod defaultPaymentMethod);
}
