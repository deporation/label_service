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
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Projectinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("status")
    private Boolean status;

    @TableField("tid")
    private Integer tid;

    @TableField("pnum")
    private String pnum;

    @TableField("proname")
    private String proname;

    @TableField("scid")
    private Integer scid;

    @TableField("pname")
    private String pname;

    @TableField("lname")
    private String lname;

    @TableField("num")
    private String num;


}
