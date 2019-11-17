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
public class 课程 extends Model<课程> {

    private static final long serialVersionUID = 1L;

    private String 课程编号;

    private String 课程名称;

    private Integer 课时;

    private Integer 学分;

    private String 课程类别;


    @Override
    protected Serializable pkVal() {
        return this.课程编号;
    }

}
