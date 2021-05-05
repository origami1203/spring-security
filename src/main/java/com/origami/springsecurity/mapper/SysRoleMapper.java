package com.origami.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.origami.springsecurity.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author origami1203
 * @date 2021-5-2 19:32
 * @description TODO
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    
    List<SysRole> selectAllRoleNamesByPermissionId(@Param("permissionId") Long permissionId);
    
    List<SysRole> selectAllRoleNamesByUserId(@Param("userId") Long userId);
    
    List<SysRole> selectAllRoleNamesByUrl(@Param("url") String url);
}
