package cn.lmsite.imghub.controller;

import java.util.List;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.lmsite.imghub.common.result.BaseResult;
import cn.lmsite.imghub.common.result.ServiceResult;
import cn.lmsite.imghub.common.result.enums.CommonResultEnum;
import cn.lmsite.imghub.service.UsersService;
import cn.lmsite.imghub.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = "/user")
public class UserApi extends BaseApi {

    final UsersService usersService;

    public UserApi(UsersService usersService) {this.usersService = usersService;}

    /**
     * 用户注册
     *
     * @return {@link BaseResult <UserVO>}
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<Boolean> userRegister(UserVO user) {
        ServiceResult<Boolean> result = usersService.register(user);
        return buildingBaseResult(result);
    }

    /**
     * 用户登录
     *
     * @return {@link BaseResult<Boolean>}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public BaseResult<Boolean> userLogin(UserVO user) {
        ServiceResult<Boolean> login = usersService.login(user);
        return buildingBaseResult(login);
    }

    /**
     * 更新用户信息
     *
     * @return {@link BaseResult <UserVO>}
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<UserVO> userInfoUpdate(UserVO user) {
        ServiceResult<UserVO> update = usersService.updateUser(user);
        return buildingBaseResult(update);
    }

    /**
     * 根据 id/uid/userName/phoneNum/email 之一获取用户详情
     *
     * @return {@link BaseResult <UserVO>}
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<UserVO> getUser(UserVO user) {
        ServiceResult<UserVO> detail = usersService.selectUser(user.getId(), user.getUid(), user.getUserName(), user.getPhoneNum(),
                user.getEmail());
        return buildingBaseResult(detail);
    }

    /**
     * 获取所有用户列表
     *
     * @return {@link BaseResult <List<UserVO>>}
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public BaseResult<List<UserVO>> getAllList() {
        ServiceResult<List<UserVO>> detail = usersService.selectAllUserList();
        return buildingBaseResult(detail);
    }

    /**
     * 根据 任意属性进行查询 用户
     *
     * @return {@link BaseResult <List<UserVO>>}
     */
    @RequestMapping(value = "/queryByCondition", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<List<UserVO>> getAllList(UserVO user) {
        ServiceResult<List<UserVO>> listServiceResult = usersService.selectByCondition(user);
        return buildingBaseResult(listServiceResult);
    }

    /**
     * 根据 用户名和密码生成签名
     *
     * @return {@link BaseResult <byte[]>}
     */
    @RequestMapping(value = "/genUserSign", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<byte[]> genUserSign(UserVO user) {
        if (StringUtils.isAnyBlank(user.getUserName(), user.getPasswd())) {
            return new BaseResult<>(CommonResultEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        byte[] content = (user.getUserName() + user.getPasswd()).getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        //签名
        byte[] signed = sign.sign(content);
        return new BaseResult<>(CommonResultEnum.SUCCESS, signed);
    }

    /**
     * 根据 用户名和密码生成签名
     *
     * @return {@link BaseResult <Boolean>}
     */
    @RequestMapping(value = "/{id}/verifySign", method = RequestMethod.GET)
    public BaseResult<Boolean> genUserSign(@Param("signature") String signature, @PathVariable("id") Long id) {
        if (StringUtils.isBlank(signature)) {
            return new BaseResult<>(CommonResultEnum.REQUIRED_PARAMETERS_ARE_EMPTY);
        }
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        UserVO userVO = usersService.selectById(id).getData();
        String content = userVO.getUserName() + userVO.getPasswd();
        // //验证签名
        boolean verify = sign.verify(content.getBytes(), signature.getBytes());
        return new BaseResult<>(CommonResultEnum.SUCCESS, verify);
    }
}
