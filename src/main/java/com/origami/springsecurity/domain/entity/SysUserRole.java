package com.origami.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author origami1203
 * @date 2021-5-2 22:37
 * @description TODO
 */
@Entity
@Table
@Data
@TableName
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 8835650228212633616L;
    @Id
    private Long id;
    private Long userId;
    private Long roleId;
}
