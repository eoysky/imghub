package cn.lmsite.imghub.mapper;

import java.util.List;

import cn.lmsite.imghub.entity.InvitationCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InvitationCodeMapper {
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(InvitationCode record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    InvitationCode selectByPrimaryKey(Long id);

    int updateBatch(List<InvitationCode> list);

    int batchInsert(@Param("list") List<InvitationCode> list);
}