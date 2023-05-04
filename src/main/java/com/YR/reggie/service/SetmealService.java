package com.YR.reggie.service;

import com.YR.reggie.dto.SetmealDto;
import com.YR.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * ClassName: SetmealService
 * Description:
 * date: 2023/4/20 0020 17:11
 *
 * @author YR
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时删除套餐以及菜品的关联
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
