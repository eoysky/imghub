package cn.lmsite.imghub.common.result;

import cn.lmsite.imghub.common.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 基本页面的结果
 *
 * @author Jonny.Chang  ( https://jonnyhub.com )  @jonny6015
 */
@Getter
@Setter
public class PageResult<T> extends BaseResult<T> {

    /**
     * 当前页码
     */
    private int pageStart;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 总条目数
     */
    private int totalCount;

    /**
     * Constructor.
     */
    public PageResult() {
    }

    /**
     * Constructor.
     *
     * @param success    the success
     * @param resultCode the result code
     * @param data       the data
     * @param resultMsg  the msg
     * @param pageStart  the current page
     * @param pageSize   the page size
     * @param totalCount the total
     */
    public PageResult(boolean success, String resultCode, T data, String resultMsg, int pageStart, int pageSize, int totalCount) {
        super(success, resultCode, data, resultMsg);
        this.pageStart = pageStart;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    /**
     * Constructor.
     *
     * @param resultCode the result code
     */
    public PageResult(ResultCodeEnum resultCode) {
        super(resultCode);
    }
}
