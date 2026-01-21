package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.mapper.IUserMapper;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.repository.IUserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository repo;
    @Autowired
    private IUserMapper mapper;


    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(10);

    public Optional<UserResponseDTO> register(UserCreateRequestDTO userDTO){

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        User user = mapper.createToEntity(userDTO);
        UserResponseDTO userResponse = mapper.toResponse(repo.save(user));

        return Optional.of(userResponse);
    }

    public Optional<UserResponseDTO> register(User user){
        return register(mapper.toCreateResponse(user));
    }

}
