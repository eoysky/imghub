package cn.lmsite.imghub.mapper;

import java.util.List;

import cn.lmsite.imghub.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * insert record to table
     *
     * @param record 记录
     * @return int
     */
    int insert(User record);

    /**
     * select by primary key
     *
     * @param id       用户 id
     * @param uid      用户 uid
     * @param userName 用户名
     * @param phoneNum 电话号码
     * @param email    电子邮件
     * @return {@link User}
     */
    User selectByPrimaryKey(@Param("id") Long id, @Param("uid") String uid, @Param("userName") String userName,
            @Param("phoneNum") Long phoneNum, @Param("email") String email);

    /**
     * select all
     *
     * @return {@link List<User>}
     */
    List<User> selectAllList();

    /**
     * 查询
     *
     * @param user 用户实体
     * @return {@link List<User>}
     */
    List<User> selectByCondition(User user);

    /**
     * 更新
     *
     * @param user 用户实体
     * @return int
     */
    int updateByCondition(User user);

    /**
     * 批处理更新
     *
     * @param list 列表
     * @return int
     */
    int updateBatch(List<User> list);

    /**
     * 批量插入
     *
     * @param list 列表
     * @return int
     */
    int batchInsert(@Param("list") List<User> list);
}