package com.labservice.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author deporation
 * @since 2019-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("pnum")
    private String pnum;

    @TableField("proname")
    private String proname;

    @TableField("sid")
    private Integer sid;

    @TableField("tid")
    private Integer tid;

    @TableField("status")
    private Boolean status;

    @TableField("lid")
    private Integer lid;

}
