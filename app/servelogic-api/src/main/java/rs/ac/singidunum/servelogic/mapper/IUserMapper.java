package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.ac.singidunum.servelogic.dto.create.RegisterRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.UserUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    IUserMapper MAPPER = Mappers.getMapper(IUserMapper.class);

    User createToEntity(UserCreateRequestDTO dto);
    User registerToEntity(RegisterRequestDTO dto);
    User updateToEntity(UserUpdateRequestDTO dto);
    UserCreateRequestDTO toCreateResponse(User user);
    UserResponseDTO toResponse(User user);
}
