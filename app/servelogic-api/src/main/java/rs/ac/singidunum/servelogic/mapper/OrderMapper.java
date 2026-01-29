package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import rs.ac.singidunum.servelogic.dto.response.OrderResponseDTO;
import rs.ac.singidunum.servelogic.model.Order;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    //public abstract OrderResponseDTO toResponse(Order order);
}
