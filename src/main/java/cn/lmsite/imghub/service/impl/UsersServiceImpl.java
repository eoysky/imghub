package cn.lmsite.imghub.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.lang.UUID;
import cn.lmsite.imghub.common.enums.ResultCodeEnum;
import cn.lmsite.imghub.common.result.ServiceResult;
import cn.lmsite.imghub.common.constants.BaseConfig;
import cn.lmsite.imghub.entity.User;
import cn.lmsite.imghub.mapper.UserMapper;
import cn.lmsite.imghub.service.RedisService;
import cn.lmsite.imghub.service.UserService;
import cn.lmsite.imghub.utils.BeanConvertor;
import cn.lmsite.imghub.utils.JsonUtils;
import cn.lmsite.imghub.utils.user.CryptUtils;
import cn.lmsite.imghub.vo.UserVO;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public ServiceResult<UserVO> login(UserVO userVO) {
        // 参数校验
        if (userVO == null || StringUtils.isBlank(userVO.getPasswd())) {
            return new ServiceResult<>(ResultCodeEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        if (StringUtils.isNoneBlank(userVO.getUid(), userVO.getUserName(), userVO.getEmail()) && userVO.getPhoneNum() == null) {
            return new ServiceResult<>(ResultCodeEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        UserVO selectUserVO = null;
        if (StringUtils.isNotBlank(userVO.getUid())) {
            selectUserVO = selectByUid(userVO.getUid()).getData();
        }
        if (selectUserVO == null && StringUtils.isNotBlank(userVO.getUserName())) {
            selectUserVO = selectByUserName(userVO.getUserName()).getData();
        }
        if (selectUserVO == null && StringUtils.isNotBlank(userVO.getEmail())) {
            selectUserVO = selectByEmail(userVO.getEmail()).getData();
        }
        if (selectUserVO == null && userVO.getPhoneNum() != null) {
            selectUserVO = selectByPhoneNum(userVO.getPhoneNum()).getData();
        }
        if (selectUserVO == null) {
            return new ServiceResult<>(ResultCodeEnum.COLUMN_DOES_NOT_EXIST);
        }
        boolean loginCheck = true;
        if (userVO.getPhoneNum() != null) {
            loginCheck = userVO.getPhoneNum().equals(selectUserVO.getPhoneNum());
        }
        if (StringUtils.isNotBlank(userVO.getUserName())) {
            loginCheck = userVO.getUserName().equals(selectUserVO.getUserName()) && loginCheck;
        }
        if (StringUtils.isNotBlank(userVO.getEmail())) {
            loginCheck = userVO.getEmail().equals(selectUserVO.getEmail()) && loginCheck;
        }
        if (StringUtils.isNotBlank(userVO.getUid())) {
            loginCheck = userVO.getUid().equals(selectUserVO.getUid()) && loginCheck;
        }
        // 更新最后登录时间
        selectUserVO.setLastLogin(new Date());
        selectUserVO.setPasswd(null);
        updateUser(selectUserVO);
        selectUserVO.setToken(getUserToken(selectUserVO).getData());
        return loginCheck ? new ServiceResult<>(selectUserVO) : new ServiceResult<>(ResultCodeEnum.SYSTEM_EXECUTION_ERROR);
    }

/*    @Override
    public ServiceResult<Boolean> register(UserVO userVO) {
        if (userVO == null || StringUtils.isAnyBlank(userVO.getUid(), userVO.getUserName(), userVO.getPasswd(), userVO.getEmail(),
                userVO.getRegUa(), userVO.getRegIp()) || userVO.getPhoneNum() == null || userVO.getPermission() == null) {
            return new ServiceResult<>(ResultCodeEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        userVO.setGender(0);
        userVO.setStatus(0);
        userVO.setPermission(0);
        userVO.setLastLogin(new Date());
        userVO.setUploadLimit(20);
        userVO.setUploadTotal(0L);
        userVO.setGmtCreate(new Date());
        userVO.setPasswd(CryptUtils.Encrypt(userVO.getPasswd()));
        int insert = userMapper.insert(BeanConvertor.convertBean(userVO, User.class));
        return insert > 0 ? new ServiceResult<>(true, ResultCodeEnum.SUCCESS) : new ServiceResult<>(ResultCodeEnum.DATABASE_SERVICE_ERROR);
    }*/


    @Override
    public ServiceResult<Boolean> register(UserVO userVO) {
        // 获取明文
        String password = userVO.getPasswd();
        //对明文密码进行加密 -- 加盐加密\
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        //把加密数据设置到用户中
        userVO.setPasswd(encode);
        int insert = userMapper.insert(BeanConvertor.convertBean(userVO, User.class));
        return insert > 0 ? new ServiceResult<>(true, ResultCodeEnum.SUCCESS) : new ServiceResult<>(ResultCodeEnum.DATABASE_SERVICE_ERROR);
    }

    @Override
    public ServiceResult<UserVO> updateUser(UserVO userVO) {
        if (userVO == null || userVO.getId() == null) {
            return new ServiceResult<>(ResultCodeEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        if (StringUtils.isNotBlank(userVO.getPasswd())) {
            userVO.setPasswd(CryptUtils.Encrypt(userVO.getPasswd()));
        }
        int updateByCondition = userMapper.updateByCondition(BeanConvertor.convertBean(userVO, User.class));
        return updateByCondition > 0 ? selectById(userVO.getId()) : new ServiceResult<>(ResultCodeEnum.DATABASE_SERVICE_ERROR);
    }

    @Override
    public ServiceResult<UserVO> selectUser(Long id, String uid, String userName, Long phoneNum, String email) {
        if (id == null && phoneNum == null && StringUtils.isAnyBlank(uid, userName, email)) {
            return new ServiceResult<>(ResultCodeEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        User user = userMapper.selectByPrimaryKey(id, uid, userName, phoneNum, email);
        if (user == null) {
            return new ServiceResult<>(ResultCodeEnum.COLUMN_DOES_NOT_EXIST);
        }
        return new ServiceResult<>(BeanConvertor.convertBean(user, UserVO.class), ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<UserVO> selectById(Long id) {
        User user = userMapper.selectByPrimaryKey(id, null, null, null, null);
        UserVO userVO = BeanConvertor.convertBean(user, UserVO.class);
        return new ServiceResult<>(userVO, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<UserVO> selectByUid(String uid) {
        User user = userMapper.selectByPrimaryKey(null, uid, null, null, null);
        UserVO userVO = BeanConvertor.convertBean(user, UserVO.class);
        return new ServiceResult<>(userVO, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<UserVO> selectByUserName(String userName) {
        User user = userMapper.selectByPrimaryKey(null, null, userName, null, null);
        UserVO userVO = BeanConvertor.convertBean(user, UserVO.class);
        return new ServiceResult<>(userVO, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<UserVO> selectByPhoneNum(Long phoneNum) {
        User user = userMapper.selectByPrimaryKey(null, null, null, phoneNum, null);
        UserVO userVO = BeanConvertor.convertBean(user, UserVO.class);
        return new ServiceResult<>(userVO, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<UserVO> selectByEmail(String email) {
        User user = userMapper.selectByPrimaryKey(null, null, null, null, email);
        UserVO userVO = BeanConvertor.convertBean(user, UserVO.class);
        return new ServiceResult<>(userVO, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<List<UserVO>> selectAllUserList() {
        List<UserVO> userVOList = convertUserVOList(userMapper.selectAllList());
        return new ServiceResult<>(userVOList, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<List<UserVO>> selectByCondition(UserVO userVO) {
        List<UserVO> userVOList = convertUserVOList(userMapper.selectByCondition(BeanConvertor.convertBean(userVO, User.class)));
        return new ServiceResult<>(userVOList, ResultCodeEnum.SUCCESS);
    }

    @Override
    public ServiceResult<String> getUserToken(UserVO userVO) {
        JSONObject userJson = JsonUtils.beanToJsonObject(userVO);
        String token = UUID.randomUUID().toString().replace("-", "");
        redisService.set(token, userJson, BaseConfig.TOKEN_EXPIRATION_TIME);
        return new ServiceResult<>(token);
    }

    @Override
    public ServiceResult<Boolean> verifyUserToken(String token) {
        Object object = redisService.get(token);
        if (object instanceof User) {
            User user = (User) object;
        }
        return new ServiceResult<>(true);
    }

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    //\\\\\                     ****************                            \\\\\
    //\\\\\                     ***  私有方法 ***                             \\\\\
    //\\\\\                     ****************                            \\\\\
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private List<UserVO> convertUserVOList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            new ArrayList<>();
        }
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : userList) {
            userVOList.add(BeanConvertor.convertBean(user, UserVO.class));
        }
        return userVOList;
    }

}
