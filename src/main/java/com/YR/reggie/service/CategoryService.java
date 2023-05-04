package com.YR.reggie.service;

import com.YR.reggie.entity.Category;
import com.YR.reggie.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ClassName: CategoryService
 * Description:
 * date: 2023/4/20 0020 13:42
 *
 * @author YR
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
