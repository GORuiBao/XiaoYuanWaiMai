package com.YR.reggie.service;

import com.YR.reggie.dto.DishDto;
import com.YR.reggie.entity.Dish;
import com.YR.reggie.mapper.DishMapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ClassName: DishService
 * Description:
 * date: 2023/4/20 0020 17:10
 *
 * @author YR
 */
public interface DishService extends IService<Dish> {

    // 新增菜品，同时插入菜品对应的口味数据，需要操作俩张表：dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    // 根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
