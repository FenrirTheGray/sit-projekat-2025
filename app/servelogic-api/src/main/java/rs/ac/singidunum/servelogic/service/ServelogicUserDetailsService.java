package rs.ac.singidunum.servelogic.service;

import com.arangodb.ArangoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.model.UserPrincipal;
import rs.ac.singidunum.servelogic.repository.IUserRepository;

@Service
public class ServelogicUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArangoCursor<User> userCursor = userRepo.findByEmail(username);

        if(!userCursor.hasNext()){
            System.out.printf("User %s does not exist!", username);
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }

        User user = userRepo.findByEmail(username).next();

        return new UserPrincipal(user);
    }
}
