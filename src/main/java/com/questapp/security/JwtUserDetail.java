package com.questapp.security;

import com.questapp.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetail implements UserDetails { //Bu sınıfın amacı, JWT tabanlı kimlik doğrulama kullanıldığında, JWT'nin içindeki kullanıcı bilgilerinin çözülmesi ve
                                                    // UserDetails arayüzünü uygulayan bir sınıfa dönüştürülmesidir.

    public long Id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetail(long id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
        Id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetail createUser(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("user"));  // simple class, kullanıcı rolünü belirleyerek getAuthority() methodu ile yetkilendirme yapar.
        return new JwtUserDetail(user.getId(), user.getUserName(), user.getPassword(), authorityList);
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
