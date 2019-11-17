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
public class 注册登录 extends Model<注册登录> {

    private static final long serialVersionUID = 1L;

    private String 学号;

    private String 密码;


    @Override
    protected Serializable pkVal() {
        return this.学号;
    }

}
