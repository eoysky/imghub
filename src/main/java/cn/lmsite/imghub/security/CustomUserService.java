package cn.lmsite.imghub.security;

import cn.lmsite.imghub.entity.ImghubRole;
import cn.lmsite.imghub.entity.User;
import cn.lmsite.imghub.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 加密，解密，认证
 */
@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户
        User user = userMapper.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (ImghubRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPasswd(), authorities);
    }
}
