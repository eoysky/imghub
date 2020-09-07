package cn.lmsite.imghub.service;

import java.util.List;

import cn.lmsite.imghub.common.result.ServiceResult;
import cn.lmsite.imghub.vo.UserVO;

public interface UsersService {

    ServiceResult<Boolean> login(UserVO userVO);

    ServiceResult<Boolean> register(UserVO userVO);

    ServiceResult<UserVO> updateUser(UserVO userVO);

    ServiceResult<UserVO> selectUser(Long id, String uid, String userName, Long phoneNum, String email);

    ServiceResult<UserVO> selectById(Long id);

    ServiceResult<UserVO> selectByUid(String uid);

    ServiceResult<UserVO> selectByUserName(String userName);

    ServiceResult<UserVO> selectByPhoneNum(Long phoneNum);

    ServiceResult<UserVO> selectByEmail(String email);

    ServiceResult<List<UserVO>> selectAllUserList();

    ServiceResult<List<UserVO>> selectByCondition(UserVO user);
}
