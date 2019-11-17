package com.gdufe.dbmsapp.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangshan
 * @since 2019-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class 毕业学分 extends Model<毕业学分> {

    private static final long serialVersionUID = 1L;

    private String 课程类别;

    private Integer 最低学分;


    @Override
    protected Serializable pkVal() {
        return this.课程类别;
    }

}
