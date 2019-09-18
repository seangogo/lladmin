package com.seangogo.common.enums;

import lombok.Getter;

/**
 * Created by sean on 2017/9/11.
 */
@Getter
public enum ResultEnum {
    /**
     * 微服务错误码
     */
    SUCCESS("0", "成功"),
    CANNOT_ACCESS("1", "无权访问"),
    SIGNATURE_ERROR("1000", "签名错误"),
    PARAMETER_ERROR("1001", "参数错误"),
    SYSTEM_ERROR("1002", "系统错误"),
    ACCOUNT_NOT_EXIST("17001001", "账号不存在"),
    VERIFICATION_CODE_HAS_EXPIRED("17001002", "验证码已过期"),
    VERIFICATION_CODE_ERROR("17001003", "验证码错误"),
    PASSWORD_MISTAKE("17001004", "密码错误"),
    MENU_ROOT_NODE_NOT_EXIST("17001005", "菜单根节点不存在"),
    //role
    SUPER_CANNOT_UPDATE("1026","超级管理员不可修改"),

    FEIGN_CLIENT_ERROR("16001002", "目标服务停止工作"),
    SERVICE_RESPONSE_TIMEOUT("16001001", "请求超时"),
    DATA_NOT_EXIST("16001003", "数据不存在"),
    CODE_ERROR("16001004", "验证码不正确"),
    EMAIL_EXIST("16001005", "帐号|Email已经存在！"),
    RESOURCE_NOT_EXIST("16001006", "菜单不存在"),
    ROLE_NOT_EXIST("16001007", "角色不存在"),
    PASSWORD_NOT_MATCHING("16001009", "原始密码不匹配"),
    DATA_BIND_NOT_DELETE("16001010", "此角色关联其他账户无法删除"),
    ACCOUNT_DISABLED("16001011", "账户不可用"),
    RESOURCE_DELETE_DISABLED("16001012", "此资源关联其他角色无法删除"),
    PROJECT_CODE_DISABLED("16001013", "项目code重复"),
    RESOURCE_HAS_CHILDREN("16001014", "此资源关联子节点，请不能删除"),
    DATA_EXIST("16001015", "编码已存在"),
    USER_BIND_EXIST("16001016", "请先删除该组织下的所有用户"),
    ORG_HAS_CHILDREN("16001017", "请先删除子组织"),
    DICT_HAS_CHILDREN("16001018", "请先删除子节点"),
    ORG_NOT_EXIST("16001019", "无此组织操作权限"),
    DEALER_NOT_EXIST("16001020", "此经销商不存在"),
    ACCOUNT_OR_PHONE_ERROR("16001021", "账户名或手机号码错误"),
    PROJECT_NOT_EXIST("16001022", "用户未关联任何项目"),
    TOKEN_EXPIRE("16001023", "登陆已过期"),
    SERIES_NOT_EXISI("16001024", "车系不存在"),
    DEVICE_ID_EXIST("16001025", "设备ID已存在"),
    SOFTWARE_TYPE_REPETITION("16001026", "应用类型重复"),
    SOFTWARE_CONFIG_EMPTY("16001027", "设备软件配置明细数据为空"),
    SOFTWARE_VERSION_PUBLISHED("16001028", "软件包对应的软件版本已在升级计划中发布,不允许删除"),
    SOFTWARE_PKG_FILE_ISNULL("16001028", "升级包文件不能为空"),
    PROJECT_IS_NOT_EXIST("16001029", "项目ID不存在"),
    VERSION_IS_EXIST_CANNOT_DELETE("16001023", "该应用下已有发布的软件版本,删除失败"),
    FILE_MERGE_FAILURE("16001024", "该应用下已有发布的软件版本,删除失败"),
    RETRY_CAMPAIGN_TASK_FAILURE("16001025", "重试一个campagin任务失败"),
    RETRY_CAMPAIGN_TASK_ITEM_FAILURE("16001026", "重试一个campagin任务明细失败"),
    PAUSE_CAMPAIGN_TASK_FAILURE("16001027", "暂停一个campagin任务失败"),
    PAUSE_CAMPAIGN_TASK_ITEM_FAILURE("16001028", "暂停一个campagin任务明细失败"),
    EDIT_SOFTWARE_VERSION_ERROR("16001029", "修改软件版本失败"),
    ENVIRONMENT_NOT_ALLOW_GENERATION("16001029", "此环境不允许生成代码！"),;

    private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
