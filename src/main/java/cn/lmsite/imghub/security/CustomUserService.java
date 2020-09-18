package cn.lmsite.imghub.security;

import cn.lmsite.imghub.entity.ImghubRole;
import cn.lmsite.imghub.entity.User;
import cn.lmsite.imghub.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 UserDetailsService 接口 </br>
 * 实现 加密，解密，认证
 */
@Service
public class CustomUserService implements UserDetailsService {

    UserMapper userMapper;

    /**
     * 重写 loadUserByUsername 方法获得 user details 类型用户
     *
     * @param username 用户名
     * @return {@link UserDetails}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
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
