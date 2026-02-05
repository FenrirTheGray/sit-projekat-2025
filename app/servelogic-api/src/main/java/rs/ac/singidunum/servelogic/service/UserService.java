package rs.ac.singidunum.servelogic.service;

import com.arangodb.ArangoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.RegisterRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.UserUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.IUserMapper;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository repo;
    @Autowired
    private IUserMapper mapper;


    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(10);

    public Optional<UserResponseDTO> register(RegisterRequestDTO registerDTO){

        registerDTO.setPassword(encoder.encode(registerDTO.getPassword()));

        User user = mapper.registerToEntity(registerDTO);
        user.setRole("USER");

        UserResponseDTO userResponse = mapper.toResponse(repo.save(user));

        return Optional.of(userResponse);
    }

    public Optional<UserResponseDTO> create(UserCreateRequestDTO userDTO){
        return create(mapper.createToEntity(userDTO));
    }
    public Optional<UserResponseDTO> create(User user){
        user.setPassword(encoder.encode(user.getPassword()));

        UserResponseDTO userResponse = mapper.toResponse(repo.save(user));

        return Optional.of(userResponse);
    }

    public List<UserResponseDTO> findAll() {
        List<UserResponseDTO> allUsers = new ArrayList<>();

        repo.findAll().forEach(user -> allUsers.add(mapper.toResponse(user)));

        return allUsers;
    }
    public List<User> findAllInit(){
        List<User> allUsers = new ArrayList<>();

        repo.findAll().forEach(allUsers::add);

        return allUsers;
    }

    public Optional<UserResponseDTO> findByKey(String key) {
        Optional<User> opUser = findUserByKey(key);

        return opUser.map(user -> mapper.toResponse(user));
    }

    public Optional<UserResponseDTO> update(UserUpdateRequestDTO userDTO) {

        Optional<User> opUser = findUserByKey(userDTO.getKey());
        if(opUser.isEmpty()) return Optional.empty();

        User user = opUser.get();

        if(userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if(userDTO.getRole() != null) user.setRole(userDTO.getRole());
        if(userDTO.getPassword() != null) user.setPassword(encoder.encode(userDTO.getPassword()));

        repo.save(user);

        return Optional.of(mapper.toResponse(user));
    }

    public boolean deleteByKey(String key){
        Optional<User> opUser = findUserByKey(key);
        if(opUser.isEmpty()) return false;

        repo.delete(opUser.get());

        return true;
    }

    private Optional<User> findUserByKey(String key){
        ArangoCursor<User> cursor = repo.findByKey(key);

        if(!cursor.hasNext()) return Optional.empty();

        return Optional.of(cursor.next());
    }
}
