package projectI.demo.server.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projectI.demo.server.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService { ;
    private final UserRepository userRepository;

    ArrayList<User> list= new ArrayList<User>();

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        for(int i= 0; i< userRepository.findAll().size(); i++){
            list.add(new User(userRepository.findAll().get(i).getUserName()
                    , userRepository.findAll().get(i).getPassWord()
                    , new ArrayList<>()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println(list);
        for(int i= 0; i< list.size(); i++) {
            if(list.get(i).getUsername().equals(userName)) {
                System.out.println(list.get(i));
                return new User(list.get(i).getUsername(), list.get(i).getPassword(), new ArrayList<>());
            }
        }
        return null;
    }

}
