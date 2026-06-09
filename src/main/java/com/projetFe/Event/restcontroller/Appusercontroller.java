package com.projetFe.Event.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetFe.Event.Appuser;
import com.projetFe.Event.Image;
import com.projetFe.Event.Role;
import com.projetFe.Event.dto.UserOrgdto;
import com.projetFe.Event.dto.UserReserveurdto;
import com.projetFe.Event.security.AppuserDetails;
import com.projetFe.Event.security.AutentificationResponse;
import com.projetFe.Event.security.AuthentificationRequest;
import com.projetFe.Event.service.CustomUseerdetailService;
import com.projetFe.Event.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.projetFe.Event.repository.Appuser_repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Appusercontroller {
    @Autowired
    private Appuser_repository appuserRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    AuthenticationManager authenticationManager ;
    @Autowired
    JwtService jwtService ;
    @Autowired
    UserDetailsService userDetailsService ;


    @PostMapping("/post/reserver")
    public ResponseEntity<AutentificationResponse> retourneUser (@RequestBody UserReserveurdto user){
        /*
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Appuser us=  appuserRepository.save(user) ;
        return ResponseEntity.ok(us).getBody();
         */
        // 1. encoder le password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Appuser userplace = new Appuser();
        userplace.setNom(user.getNom());
        userplace.setPrenom(user.getPrenom());
        userplace.setEmail(user.getEmail());
        userplace.setAge(user.getAge());
        userplace.setVille_residence(user.getVille_residence());
        userplace.setRole(Role.RESERVEUR);
        userplace.setPassword(user.getPassword());
        // 2. sauvegarder user
        Appuser savedUser = appuserRepository.save(userplace);

        // 3. générer token JWT (ici tu dois convertir savedUser en UserDetails)
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                savedUser.getEmail(),
                savedUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + savedUser.getRole().name()))
        );// crée un UserDetails à partir de savedUser (ou utilise un UserDetailsService)

        String jwtToken = jwtService.generateToken(userDetails);

        // 4. construire et renvoyer la réponse avec le token
        AutentificationResponse response = new AutentificationResponse(jwtToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping(value = "/post/org", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AutentificationResponse> retourneUser (  @RequestPart("user") UserOrgdto user
                                             , @RequestPart("imageCarte") MultipartFile imageCarte



                                                                   /* @RequestPart("photo") MultipartFile photoFile */) throws IOException {


        System.out.println("user reçu: " + user);
        System.out.println("Nom du fichier reçu: " + imageCarte.getOriginalFilename());

        System.out.println("user reçu: " + user);
        System.out.println("Nom du fichier reçu: " + imageCarte.getOriginalFilename());

        /*
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Appuser us=  appuserRepository.save(user) ;
        return ResponseEntity.ok(us).getBody();

         */
        // 1. encoder le password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Image image = new Image(imageCarte.getBytes());
        // Image photo = new Image(photoFile.getBytes());

        Appuser userplace = new Appuser();
        userplace.setNom(user.getNom());
        userplace.setPrenom(user.getPrenom());
        userplace.setEmail(user.getEmail());
        userplace.setVille_residence(user.getVille_residence());
        userplace.setAge(user.getAge());
        userplace.setRole(Role.ORGANISATEUR);
        userplace.setPassword(user.getPassword());
        userplace.setCne(user.getCne());
        userplace.setImagecarte(image);
        // userplace.setPhoto(photo);
        // 2. sauvegarder user
        Appuser savedUser = appuserRepository.save(userplace);

        // 3. générer token JWT (ici tu dois convertir savedUser en UserDetails)
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                savedUser.getEmail(),
                savedUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + savedUser.getRole().name()))
        );// crée un UserDetails à partir de savedUser (ou utilise un UserDetailsService)

        String jwtToken = jwtService.generateToken(userDetails);

        // 4. construire et renvoyer la réponse avec le token
        AutentificationResponse response = new AutentificationResponse(jwtToken);

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

    @PostMapping("/login")
    public ResponseEntity<AutentificationResponse> retourne (@RequestBody AuthentificationRequest request){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken (request.getEmail() , request.getCode())) ;

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AutentificationResponse(jwt));

    }
    @GetMapping
    public List<Appuser> affichrUser(){
        return ResponseEntity.ok(appuserRepository.findAll()).getBody() ;
    }

    @PutMapping ("/modifier/{id}")
    public Appuser modifierUser (@PathVariable ("id") Long n , @RequestBody Appuser user ){
         appuserRepository.findById(n).orElseThrow(null);
         user.setId(n);
         return appuserRepository.save(user);
    }
    @GetMapping("/{id}")
    public Appuser  getAppuserbyId(@PathVariable ("id") long n) {
         Appuser user =  appuserRepository.findById(n).orElseThrow(null);
         return ResponseEntity.ok(user).getBody() ;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppuserById (@PathVariable ("id") Long n){
        Appuser user = appuserRepository.findById(n).orElseThrow(null) ;
         appuserRepository.delete(user);

    }





    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<String> handleMissingPart(MissingServletRequestPartException ex) {
        return ResponseEntity.badRequest().body(" Partie manquante : " + ex.getRequestPartName());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserOrgdto.class, new JsonEditor <>(UserOrgdto.class));
    }
    @InitBinder("user")
    public void initUserOrgBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserOrgdto.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    UserOrgdto user = objectMapper.readValue(text, UserOrgdto.class);
                    setValue(user);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Invalid JSON for UserOrgdto", e);
                }
            }
        });
    }
}
