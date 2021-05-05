package com.origami.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author origami1203
 * @date 2021-5-2 22:39
 * @description TODO
 */
@Data
@Entity
@Table
@TableName
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 3429002380326944470L;
    @Id
    private Long id;
    private Long roleId;
    private Long permissionId;
    
}
