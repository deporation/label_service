package com.labservice.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author deporation
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Pplinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("pname")
    private String pname;

    @TableField("num")
    private String num;

    @TableField("proname")
    private String proname;

    @TableField("pnum")
    private String pnum;

    @TableField("lname")
    private String lname;

    @TableField("stat")
    private Boolean stat;

    @TableField("pplid")
    private Integer pplid;


}
