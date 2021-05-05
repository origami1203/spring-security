package com.origami.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.origami.springsecurity.domain.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author origami1203
 * @date 2021-5-2 22:18
 * @description TODO
 */
@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@TableName
public class SysPermission extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -5667080586015782190L;
    
    private Long roleId;
    
    private Long parentId;
    
    private String name;
    
    private String url;
    
    private Byte type;
    
    private String permission;
}
