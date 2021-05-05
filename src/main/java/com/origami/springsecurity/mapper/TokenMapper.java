package com.origami.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.origami.springsecurity.domain.entity.Token;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author origami1203
 * @date 2021-5-3 22:56
 * @description TODO
 */
@Mapper
public interface TokenMapper extends BaseMapper<Token> {
}
