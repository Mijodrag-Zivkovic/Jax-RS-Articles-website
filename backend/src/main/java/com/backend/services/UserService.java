package com.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.backend.entities.User;
import com.backend.repositories.user.UserRepository;
import com.backend.resources.ArticleResource;
import com.backend.resources.CategoryResource;
import com.backend.resources.UserResource;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> allUsers() {
        return this.userRepository.allUsers();
    }

    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }

    public User findUser(Integer id) {
        return this.userRepository.findUser(id);
    }

    public User findUser(String email) {
        return this.userRepository.findUser(email);
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteUser(id);
    }

    public User updateUser(Integer id, User user) {
        return this.userRepository.updateUser(id,user);
    }

    public String login(String email, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("userType", user.getUserType())
                .withClaim("status",user.getStatus())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token,List<Object> matchedResources){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        //String username = jwt.getSubject();
//        jwt.getClaim("role").asString();
        String userType = jwt.getClaim("userType").asString();
        String status = jwt.getClaim("status").asString();
       //User user = this.userRepository.findUser(username);

        if (userType.equals("admin") && status.equals("active")){
            System.out.println("admin");
            return true;
        }
        else if(userType.equals("regular") && status.equals("active"))
        {
            for(Object matchedResource : matchedResources)
            {
                if(!(matchedResource instanceof UserResource))
                {
                    System.out.println(matchedResource);
                    return true;
                }

            }
        }

        return false;
    }


}
