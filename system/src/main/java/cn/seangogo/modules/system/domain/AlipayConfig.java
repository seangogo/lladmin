package cn.seangogo.modules.system.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-23
*/
@Entity
@Data
@Table(name="alipay_config")
public class AlipayConfig implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 应用ID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 编码
     */
    @Column(name = "charset")
    private String charset;

    /**
     * 类型 固定格式json
     */
    @Column(name = "format")
    private String format;

    /**
     * 网关地址
     */
    @Column(name = "gateway_url")
    private String gatewayUrl;

    /**
     * 异步回调
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    /**
     * 私钥
     */
    @Column(name = "private_key")
    private String privateKey;

    /**
     * 公钥
     */
    @Column(name = "public_key")
    private String publicKey;

    /**
     * 回调地址
     */
    @Column(name = "return_url")
    private String returnUrl;

    /**
     * 签名方式
     */
    @Column(name = "sign_type")
    private String signType;

    /**
     * 商户号
     */
    @Column(name = "sys_service_provider_id")
    private String sysServiceProviderId;
}