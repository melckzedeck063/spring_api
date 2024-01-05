package com.example.srms.userService;

import com.example.srms.Model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailImpl implements UserDetails {

    @Getter
    private final Long id;
    private final String username;
    @Getter
    private final String phone;
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
    private final String gender;

    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String username, String phone, String firstName, String lastName, String gender, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender  = gender;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserDetailImpl build(Student  member){
        Collection<GrantedAuthority> authorities1 = getAuthorities(member);
        return new UserDetailImpl(
                member.getId(),
                member.getEmail(),
                member.getPhone(),
                member.getFirstName(),
                member.getLastName(),
                member.getPassword(),
                member.getGender(),
                authorities1
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    private static  Collection<GrantedAuthority> getAuthorities(Student student){


    return   new HashSet<>();
    }
}
