package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.singidunum.servelogic.repository.IOrderRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

public class OrderService {

    @Autowired
    private IOrderRepository repo;
    @Autowired
    private ArangoFusekiReferenceService populator;
    @Autowired
    private OrderMapper mapper;
}
