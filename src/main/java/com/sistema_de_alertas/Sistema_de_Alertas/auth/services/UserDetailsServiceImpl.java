package com.sistema_de_alertas.Sistema_de_Alertas.auth.services;

import com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto.AuthLoginRequest;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto.AuthRegisterRequest;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.controller.dto.AuthResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.entity.RoleEnum;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.entity.UserEntity;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.repository.UserRepository;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private UserRepository userRepository;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, JwtUtils jwtUtils, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.findByMail(mail).orElseThrow(
                ()-> new UsernameNotFoundException("El mail:" + mail + " no está registrado")
        );

        List<SimpleGrantedAuthority> authority = new ArrayList<>();

        authority.add(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRol().name())));

        return new User(
                userEntity.getMail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authority
        );
    }

    public AuthResponse loginUser(AuthLoginRequest userRequest){
        String mail = userRequest.getMail();
        String password = userRequest.getPassword();

        Authentication authentication = this.authenticate(mail,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = this.jwtUtils.createToken(authentication);

        UserEntity user = this.userRepository.findByMail(mail).orElseThrow(
                ()-> new UsernameNotFoundException("El mail:" + mail + " no está registrado")
        );

        return new AuthResponse(user.getUsername(),"User Loged Succesfull",accessToken,true);
    }

    public Authentication authenticate(String mail, String password){
        UserDetails userDetails = this.loadUserByUsername(mail);
        if(userDetails == null){
            //TODO Controlar error
            throw new BadCredentialsException("Invalid username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            //TODO Controlar error
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(mail,userDetails.getPassword(),userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthRegisterRequest authRegisterRequest){
        String username = authRegisterRequest.getUsername();
        String name = authRegisterRequest.getName();
        String lastname = authRegisterRequest.getLastname();
        String mail = authRegisterRequest.getMail();
        String password = authRegisterRequest.getPassword();

        //Por defecto
        RoleEnum rol = RoleEnum.CUSTOMER;
        String stringPasswordEncoder = passwordEncoder.encode(password);
        System.out.println("stringPasswordEncoder = " + stringPasswordEncoder);
        UserEntity userEntity = UserEntity.builder()
                .lastname(lastname)
                .username(username)
                .name(name)
                .mail(mail)
                .password(passwordEncoder.encode(password))
                .rol(rol)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();
        UserEntity userCreated = userRepository.save(userEntity);

        List<SimpleGrantedAuthority> authority = new ArrayList<>();

        authority.add(new SimpleGrantedAuthority("ROLE_".concat(userCreated.getRol().name())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getMail(),userCreated.getPassword(),authority);

        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponse(userCreated.getUsername(),"User Created Successfully",accessToken,true);
    }
}
