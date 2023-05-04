package com.YR.reggie.dto;

import com.YR.reggie.entity.Setmeal;
import com.YR.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
