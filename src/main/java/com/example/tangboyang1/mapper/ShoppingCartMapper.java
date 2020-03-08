package com.example.tangboyang1.mapper;

import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.pojo.ShoppingCartExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ShoppingCartMapper {
    long countByExample(ShoppingCartExample example);

    int deleteByExample(ShoppingCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    ShoppingCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    int updateByExample(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}