package rs.ac.singidunum.servelogic.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.Order;

public interface IOrderRepository extends ArangoRepository<Order, String> {

}
