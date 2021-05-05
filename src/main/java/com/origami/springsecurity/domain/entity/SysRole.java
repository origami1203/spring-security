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
 * @date 2021-5-2 17:58
 * @description 角色
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_role")
@Entity
@Table
public class SysRole extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 999228032102466293L;
    @TableField
    private String roleName;
    
    private String description;
    
}
