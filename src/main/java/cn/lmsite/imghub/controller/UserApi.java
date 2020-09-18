package cn.lmsite.imghub.controller;

import java.util.List;

import cn.lmsite.imghub.common.result.BaseResult;
import cn.lmsite.imghub.common.result.ServiceResult;
import cn.lmsite.imghub.service.UserService;
import cn.lmsite.imghub.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/user")
public class UserApi extends ApiResultEnhanced {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @return {@link BaseResult <UserVO>}
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<Boolean> userRegister(@RequestBody UserVO user) {
        ServiceResult<Boolean> result = userService.register(user);
        return buildResultByServiceRes(result);
    }

    /**
     * 用户登录
     *
     * @return {@link BaseResult<Boolean>}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<UserVO> userLogin(@RequestBody UserVO userVO) {
        ServiceResult<UserVO> login = userService.login(userVO);
        return buildResultByServiceRes(login);
    }

    /**
     * 更新用户信息
     *
     * @return {@link BaseResult <UserVO>}
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<UserVO> userInfoUpdate(@RequestBody UserVO userVO) {
        ServiceResult<UserVO> update = userService.updateUser(userVO);
        return buildResultByServiceRes(update);
    }

    /**
     * 根据 id/uid/userName/phoneNum/email 之一获取用户详情
     *
     * @return {@link BaseResult <UserVO>}
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<UserVO> getUser(@RequestBody UserVO userVO) {
        ServiceResult<UserVO> detail = userService.selectUser(userVO.getId(), userVO.getUid(), userVO.getUserName(), userVO.getPhoneNum(),
                userVO.getEmail());
        return buildResultByServiceRes(detail);
    }

    /**
     * 获取所有用户列表；管理员有权获取
     *
     * @return {@link BaseResult <List<UserVO>>}
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public BaseResult<List<UserVO>> getAllList() {
        ServiceResult<List<UserVO>> detail = userService.selectAllUserList();
        return buildResultByServiceRes(detail);
    }

    /**
     * 根据任意属性进行查询用户<br/>
     * 管理员有权查询，返回用户列表
     *
     * @return {@link BaseResult <List<UserVO>>}
     */
    @RequestMapping(value = "/queryByCondition", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult<List<UserVO>> getAllList(@RequestBody UserVO userVO) {
        ServiceResult<List<UserVO>> listServiceResult = userService.selectByCondition(userVO);
        return buildResultByServiceRes(listServiceResult);
    }

}
