package cn.seangogo.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-23
*/
@Data
public class AlipayConfigDTO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 编码
     */
    private String charset;

    /**
     * 类型 固定格式json
     */
    private String format;

    /**
     * 网关地址
     */
    private String gatewayUrl;

    /**
     * 异步回调
     */
    private String notifyUrl;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 回调地址
     */
    private String returnUrl;

    /**
     * 签名方式
     */
    private String signType;

    /**
     * 商户号
     */
    private String sysServiceProviderId;
}