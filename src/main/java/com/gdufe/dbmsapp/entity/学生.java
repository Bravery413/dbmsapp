package com.gdufe.dbmsapp.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
public class 学生 extends Model<学生> {

    private static final long serialVersionUID = 1L;

    private String 学号;

    private String 姓名;

    private String 性别;

    private LocalDateTime 出生日期;

    private String 籍贯;

    private String 家庭住址;

    private String 班级;


    @Override
    protected Serializable pkVal() {
        return this.学号;
    }

}
