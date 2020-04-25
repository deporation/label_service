package com.labservice.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author deporation
 * @since 2019-06-10
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Recordtime")

public class Recordtime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("tid")
    private Integer tid;

    @TableField("sid")
    private Integer sid;

    @TableField("pid")
    private Integer pid;

    @TableField("pname")
    private String pname;

    @TableField("proname")
    private String proname;

    @TableField("lname")
    private String lname;

    @TableField("retime")
    private LocalDateTime retime;

}
