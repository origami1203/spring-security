package com.origami.springsecurity.domain.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author origami1203
 * @date 2021-5-2 17:08
 * @description domain的基类,
 */


@Data
@Accessors(chain = true)
@MappedSuperclass
public abstract class BaseDomain {
    @Id
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    protected Long id;
    
    @TableField(value = "create_date")
    protected Date createDate;
    
    @TableField(value = "update_date")
    protected Date updateDate;
    
    @TableField(value = "deleted")
    @TableLogic
    protected Byte deleted;
    
    @Version
    @TableField(value = "version")
    protected String version;
}
