package com.labservice.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author deporation
 * @since 2019-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class Peopleinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("pid")
    private Integer pid;

    @TableField("num")
    private String num;

    @TableField("account")
    private String account;

    @TableField("scname")
    private String scname;

    @TableField("pname")
    private String pname;

}
