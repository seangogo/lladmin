package com.custom.sean.common.utils.vo;

import lombok.Getter;

/**
 * Created by sean on 2017/9/11.
 */
@Getter
public enum ResultEnum {
    SUCCESS("0", "Success"),
    SUCCESS_0("0", "Success"),
    SERVICE_RESPONSE_TIMEOUT("407","Time out"),
    SERVICE_RESPONSE_SERVICE_DISABLED("404","The service has been disabled"),
    SERVICE_RESPONSE_ERROR_CODE("500","Inner server errors"),
    FEIGN_CLIENT_ERROR("20010","目标服务停止工作"),
    SERVICE_RESPONSE_INVALID("401","Invalid Parameters"),

    OBJ_NOT_EXIST("10", "对象不存在"),
    PARAM_ERROR("100", "参数不正确"),
    DATA_NOT_EXIST("402","数据不存在"),
    CODE_ERROR("1001", "验证码不正确"),
    EMAIL_EXIST("1002","帐号|Email已经存在！"),
    USER_NOT_EXIST("1003","用户不存在"),
    PARENT_NOT_EXIST("1004","父节点不存在"),
    PROJECTIDID_NOT_EXIST("1005","无操作此项目权限"),
    RESOURCE_NOT_EXIST("1006","菜单不存在"),
    ROLE_NOT_EXIST("1007","角色不存在"),
    ACCOUNT_NOT_EXIST("1008","账户不存在"),
    PASSWORD_NOT_MATCHING("1009","原始密码不匹配"),
    DATA_BIND_NOT_DELETE("1010","此角色关联其他账户无法删除"),
    ACCOUNT_DISABLED("1010","账户不可用"),
    RESOURCE_DELETE_DISABLED("1012","此资源关联其他角色无法删除"),
    PROJECT_CODE_DISABLED("1013","项目code重复"),
    RESOURCE_HAS_CHILDREN("1014","此资源关联子节点，请不能删除"),
    DATA_EXIST("1014","编码已存在"),
    USER_BIND_EXIST("1015","请先删除该组织下的所有用户"),
    ORG_HAS_CHILDREN("1016","请先删除子组织"),
    DICT_HAS_CHILDREN("10160","请先删除子节点"),
    ORG_NOT_EXIST("1017","无此组织操作权限"),
    DEALER_NOT_EXIST("1018","此经销商不存在"),
    ACCOUNT_OR_PHONE_ERROR("1019","账户名或手机号码错误"),
    PROJECT_NOT_EXIST("1020","用户未关联任何项目"),
    TOKEN_EXPIRE("1021","登陆已过期"),
    CODE_EXPIRE("1022","编码已存在"),
    PWD_STRATEGY_EXIST("1023","密码策略已存在"),
    DATA_DICTIONARY_EXIST("1024","数据字典已存在"),
    SUPER_CANNOT_DELETE("1025","超级管理员不可删除"),
    SUPER_CANNOT_UPDATE("1026","超级管理员不可修改"),
    USERNAME_EXIST("1027","用户名已存在"),
    ROLES_BIND_NOT_DELETE("1028","关联用户无法删除"),
    ACTIVE_FAILED("1029","激活失败"),
    SERIES_NOT_EXISI("1030","车系不存在"),
    DEVICE_ID_EXIST("1031","设备ID已存在"),
    ROOT_ORG_DISABLED("1032","顶级组织禁用"),
    ORG_PROJECTCODES_DISABLED("1033","此账号无对应项目"),
    SERVICE_DISABLED("1034","服务不可用"),
    ORG_TYPE_DISABLED("1035","项目组不提供此功能"),
    USER_EDIT_DISABLED("1036","管理员只可编辑自己创建的用户"),
    VIN_EXIST("1037","VIN号已存在"),
    TSPUSER_ORG_EXIST("1038","账号所在组织不存在"),
    BRAND_DELETE_ERROR("1039","品牌删除失败"),
    BRAND_SAVE_ERROR("1040","品牌新增失败"),
    KYLIN_FETCH_ERROR("1041","大数据平台服务异常"),
    ORG_BIND_PROJECT("1042","组织绑定此项目，无法删除"),
    ORG_BIND_BRAND("1043","组织绑定此品牌，无法删除"),
    ORG_ONESELF_PARENT("1044","不可指定当前组织为上级组织"),
    CAR_ONESELF_PARENT("1045","未查找到车主信息或未给出拒绝原因"),
    CAR_ONESELF_PARENTVIN("1046","拒绝审核操作不成功!"),
    CAR_ONESELF_PARENTERROR("1047","车辆状态更新失败！"),
    CAR_ONESELF_PARENTSUSS("1048","用户审核成功"),
    CAR_ONESELF_PARENTTEXT("1049","激活类型或业务类型状态异常"),
    CAR_ONESELF_PARENTIMSI("1050","该车辆实名认证提交申请认证中，请勿重复操作！"),
    CAR_ONESELF_PARENTIMSISIM("1051","sim卡信息不存在!"),
    CAR_ONESELF_PARENTIMSISIME("1052","实名认证操作出现异常错误"),
    CAR_ONESELF_PARENTSUSSERROR("1053","联通实名认证失败"),
    CAR_ONESELF_PARENTNO("1054","用户账户不存在"),
    CAR_ONESELF_PARENTNOE("1055","解除车机激活出错"),
    SIM_ONESELF_PARENTERROR("1056","选择导出的数据等于0条或者大于50条，请精确查询条件"),
    CAR_ONESELF_PARENTOPSUSS("1048","用户审核失败"),
    CODE_MAX("1049","编码达到最大数"),
    ;

    private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
