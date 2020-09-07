package cn.lmsite.imghub.mapper;

import java.util.List;

import cn.lmsite.imghub.entity.OperationConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OperationConfigMapper {
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OperationConfig record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    OperationConfig selectByPrimaryKey(Long id);

    int updateBatch(List<OperationConfig> list);

    int batchInsert(@Param("list") List<OperationConfig> list);
}