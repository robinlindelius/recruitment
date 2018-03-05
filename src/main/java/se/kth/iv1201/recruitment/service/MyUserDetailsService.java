package se.kth.iv1201.recruitment.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.entity.Person;
import se.kth.iv1201.recruitment.entity.User;
import se.kth.iv1201.recruitment.entity.UserRole;
import se.kth.iv1201.recruitment.exception.UsernameAlreadyExistsException;
import se.kth.iv1201.recruitment.repository.UserRepository;

/**
 * Service using Spring Security for handling users.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by username.
     * @param username the username who wants to load.
     */
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if(user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);
    }

    /**
     * Method to register a new User.
     * @param user The User to save.
     * @throws UsernameAlreadyExistsException is thrown if a user with the same username already exists.
     */
    @Transactional(rollbackFor = UsernameAlreadyExistsException.class)
    public void registerUser(User user) throws UsernameAlreadyExistsException {
        if(userRepository.findOne(user.getUsername())!= null) {
            throw new UsernameAlreadyExistsException("The username '" + user.getUsername() + "' already exists.");
        }

        user.setEnabled(true);
        user.addRole(UserRole.APPLICANT);

        userRepository.save(user);
    }

    /**
     * Retrieves the person connected to the user that is currently logged in.
     * @return person object of user.
     * @throws Exception if no user can be found.
     */
    public Person getLoggedInPerson() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof org.springframework.security.core.userdetails.User) {
            return userRepository.findOne(((org.springframework.security.core.userdetails.User) principal).getUsername()).getPerson();
        }
        throw new Exception("NO LOGGED IN");
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                                                                          List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}