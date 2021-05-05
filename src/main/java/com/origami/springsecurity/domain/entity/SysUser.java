package com.origami.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.origami.springsecurity.domain.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author origami1203
 * @date 2021-5-2 17:07
 * @description 系统用户
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table
@TableName(value = "sys_user")
public class SysUser extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -8644668841132620346L;
    
    @TableField
    private String username;
    @TableField
    private String password;
    @TableField
    private String email;
    @TableField
    private Short gender;
    
    private Byte enabled;
    
}
