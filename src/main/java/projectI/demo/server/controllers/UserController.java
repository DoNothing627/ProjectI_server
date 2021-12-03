package projectI.demo.server.controllers;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projectI.demo.server.config.MyConstants;
import projectI.demo.server.models.AuthenticationRequest;
import projectI.demo.server.models.AuthenticationResponse;
import projectI.demo.server.models.Info;
import projectI.demo.server.models.User;
import projectI.demo.server.repositories.UserRepository;
import projectI.demo.server.mapping.UserMapping;
import projectI.demo.server.services.MyUserDetailsService;
import projectI.demo.server.util.JwtUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @PostMapping(UserMapping.USER_GET_MAPPING)
    public Info postUser(@RequestBody User user){
        List<User> fullList= userRepository.findAll();
        for(int i= 0; i< fullList.size(); i++) {
            if (fullList.get(i).getUserName().equals(user.getUserName())){
                return new Info(fullList.get(i).getUserName(), fullList.get(i).getPhoneNumber(), fullList.get(i).getAddress());
            }
        }
        return new  Info("an", "dep", "zai");
    }

    @GetMapping("/user/get/{userName}")
    public Info getAllUser(@PathVariable("userName") String userName){
        System.out.println(userName);
        List<User> fullList= userRepository.findAll();
        for(int i= 0; i< fullList.size(); i++) {
            if (fullList.get(i).getUserName().equals(userName)){
                return new Info(fullList.get(i).getUserName(), fullList.get(i).getPhoneNumber(), fullList.get(i).getAddress());
            }
        }
        return new  Info("vu", "xinh", "gai");
    }

    @PostMapping(UserMapping.USER_REGISTER_MAPPING)
    public boolean addUser(@RequestBody User user){
        List<User> fullList= userRepository.findAll();
        for(int i= 0; i< fullList.size(); i++) {
            if (fullList.get(i).getUserName().equals(user.getUserName())){
                return false;
            }
        }
        userRepository.save(user);
        return true;
    }

    @PostMapping(UserMapping.USER_LOGIN_MAPPING)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails= userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt= jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @ResponseBody
    @RequestMapping("/user/checkout/{listProduct}")
    public String sendSimpleEmail(@PathVariable("listProduct") String listProduct) {

        Object obj = JSONValue.parse(listProduct);
        JSONObject jsonObject = (JSONObject) obj;

        String email = (String) jsonObject.get("email");
        String order= (String) jsonObject.get("order");

        int res= -1;
        List<User> fullList= userRepository.findAll();
        for(int i= 0; i< fullList.size(); i++) {
            if (fullList.get(i).getUserName().equals(email)){
                res= i;
                break;
            }
        }
        if(res== -1) return "Fail";

        System.out.println("1"+ order);

        order= order.replace('_', '\n');

        System.out.println("2"+ order);
        String content= order+ "\n To: "+ fullList.get(res).getUserName()+
                "\n Address: "+ fullList.get(res).getAddress()+
                "\n Phone number: "+ fullList.get(res).getPhoneNumber();

        // Create a Simple MailMessage
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("New order");
        message.setText(content);

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}
