package com.labservice.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Ppl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pplid", type = IdType.AUTO)
    private Integer pplid;

    @TableField("pid")
    private Integer pid;

    @TableField("pnum")
    private String pnum;

    @TableField("lid")
    private Integer lid;

    @TableField("stat")
    private Boolean stat;

}
