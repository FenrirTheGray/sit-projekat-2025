package rs.ac.singidunum.servelogic.service;

import com.arangodb.ArangoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.OrderCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.OrderResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.OrderUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.OrderMapper;
import rs.ac.singidunum.servelogic.model.Choice;
import rs.ac.singidunum.servelogic.model.Order;
import rs.ac.singidunum.servelogic.model.OrderStatus;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.repository.IOrderRepository;
import rs.ac.singidunum.servelogic.repository.IUserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository repo;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private OrderMapper mapper;

    public List<OrderResponseDTO> findAll(){
        List<OrderResponseDTO> allOrders = new ArrayList<>();

        repo.findAll().forEach(order -> allOrders.add(mapper.toResponse(order)));

        return allOrders;
    }

    public List<OrderResponseDTO> findAllUser(String email) {

        List<OrderResponseDTO> userOrders = new ArrayList<>();
        ArangoCursor<User> userCursor = userRepo.findByEmail(email);

        if(!userCursor.hasNext()) return userOrders;
        User user = userCursor.next();

        repo.findByUser(user.getId()).forEach(order -> userOrders.add(mapper.toResponse(order)));

        return userOrders;
    }

    public Optional<OrderResponseDTO> findByKey(String key) {
        Optional<Order> item = repo.findById(key);
        if(item.isPresent()){
            return Optional.of(mapper.toResponse(item.get()));
        }

        return Optional.empty();
    }

    public Optional<OrderResponseDTO> create(OrderCreateRequestDTO item) {

        ArangoCursor<User> userCursor = userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!userCursor.hasNext()) return Optional.empty();
        User user = userCursor.next();

        Order order = mapper.createToEntity(item);

        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(new Date());
        order.setUser(user);

        System.out.println(order);

        repo.save(order);

        return Optional.of(mapper.toResponse(order));
    }

    public Optional<OrderResponseDTO> update(OrderUpdateRequestDTO orderDTO) {
        Optional<Order> opOrder = findOrderByKey(orderDTO.getKey());
        if(opOrder.isEmpty()) return Optional.empty();

        Order order = opOrder.get();

        if(orderDTO.getStatus() != null) order.setStatus(orderDTO.getStatus());
        if(orderDTO.getCreatedAt() != null) order.setCreatedAt(orderDTO.getCreatedAt());
        if(orderDTO.getChoices() != null){
            List<Choice> newChoices = new ArrayList<>();

            orderDTO.getChoices().forEach(choice -> newChoices.add(mapper.mapToChoice(choice)));

            order.setChoices(newChoices);
        }

        repo.save(order);
        return Optional.of(mapper.toResponse(order));
    }


    public boolean deleteByKey(String key){
        Optional<Order> opOrder = findOrderByKey(key);
        if(opOrder.isEmpty()) return false;

        repo.delete(opOrder.get());

        return true;
    }
    private Optional<Order> findOrderByKey(String key){
        ArangoCursor<Order> cursor = repo.findByKey(key);

        if(!cursor.hasNext()) return Optional.empty();

        return Optional.of(cursor.next());
    }

}
