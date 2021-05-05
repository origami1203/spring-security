package com.origami.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.origami.springsecurity.domain.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author origami1203
 * @date 2021-5-2 22:41
 * @description TODO
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    
    List<SysPermission> selectListByUsername(@Param("username") String username);
    
    List<SysPermission> selectListByUserId(@Param("userId") Long userId);
    
}
