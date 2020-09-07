package cn.lmsite.imghub.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.lmsite.imghub.common.result.ServiceResult;
import cn.lmsite.imghub.vo.UserVO;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersServiceTest {

    @Autowired
    UsersService usersService;

    @Test
    void testRegister() {
        UserVO userVO = new UserVO();
        userVO.setUid(UUID.randomUUID().toString().replace("-", ""));
        userVO.setUserName("jonny6015");
        userVO.setNickName("管理员");
        userVO.setGender(2);
        userVO.setPasswd("rain8240");
        userVO.setPhoneNum(13221066206L);
        userVO.setEmail("jonny6015@icloud.com");
        userVO.setStatus(0);
        userVO.setPermission(2);
        userVO.setLastLogin(new Date());
        userVO.setUploadLimit(0);
        userVO.setUploadTotal(0L);
        userVO.setRegIp("47.99.172.232");
        userVO.setNickName("Jonny.Chang");
        userVO.setWelcomeMsg("欢迎主人来到您的私人空间！");
        userVO.setRegUa(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.2 Safari/605.1.15");
        userVO.setGmtCreate(new Date());
        System.out.println(userVO.toString());
        ServiceResult<Boolean> register = usersService.register(userVO);
        System.out.println("\n\n" + register.toString());
    }

    @Test
    void testLogin() {
        Long id = 1L;
        ServiceResult<UserVO> result = usersService.selectById(id);
        UserVO userVO = new UserVO();
        userVO.setUserName("jonny6015");
        userVO.setPasswd("rain8240");
        ServiceResult<Boolean> login = usersService.login(userVO);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void testUpdate() {
        UserVO userVO = usersService.selectById(1L).getData();
        userVO.setPasswd("rain8240");
        ServiceResult<UserVO> result = usersService.updateUser(userVO);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void testSelectAll() {
        List<UserVO> data = usersService.selectAllUserList().getData();
        System.out.println(JSON.toJSONString(data));
    }

    /**
     * Setter method for property <tt>usersService</tt>.
     *
     * @param usersService value to be assigned to property usersService
     */
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}