package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.mapper.OrderMapper;
import rs.ac.singidunum.servelogic.repository.IOrderRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository repo;
    @Autowired
    private ArangoFusekiReferenceService populator;
    @Autowired
    private OrderMapper mapper;
}
