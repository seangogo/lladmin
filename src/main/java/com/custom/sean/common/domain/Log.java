package com.custom.sean.common.domain;

import com.custom.sean.common.utils.basics.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author sean
 * @date 2018/2/1
 */
@Entity
@Table(name = "auth_log")
@Getter
@Setter
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日志主键
     */
    @Id
    @GenericGenerator(name = "id", strategy = "uuid2")
    @GeneratedValue(generator = "id")
    @Column(name = "sid", length = 36)
    protected String id;
    private Boolean type;			//日志类型
    private String username;		//用户账户
    private String title;			//日志标题
    private String remoteAddr;		//请求地址
    private String requestUri;		//URI
    private String method;			//请求方式
    @Temporal(TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateDate;		//开始时间
    private long timeout;			//耗时
    private String params;			//提交参数
    private String exception;		//异常

    /**
     * 设置请求参数
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        if (paramMap == null){
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()){
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        this.params = params.toString();
    }
}
