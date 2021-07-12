package com.origami.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.origami.springsecurity.domain.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * @author origami1203
 * @date 2021-5-3 18:45
 * @description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@TableName
public class Token extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 9034825791011383809L;
    
    private String authentication;
    
    // 存储uuid
    private String value;
    
    /**
     * 登录时的时间戳
     */
    private Long loginTime;
    
    @Value("${jwt.expireTime}")
    private Long expireTime;
}
