package com.demo.vite.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author df
 * @since 2023-01-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 栏目名称
     */
    @TableField("name")
    private String name;

    /**
     * 栏目标题
     */
    @TableField("title")
    private String title;

    /**
     * 父级栏目id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 路径
     */
    @TableField("path")
    private String path;

    /**
     * 0 显示   1不显示
     */
    @TableField("is_show")
    private Integer isShow;

    /**
     * 状态 0 正常  1停用
     */
    @TableField("status")
    private Integer status;

    /**
     * 栏目绑定的组件
     */
    @TableField("component")
    private String component;

    /**
     * 自定义属性
     */
    @TableField("meta")
    private String meta;
}
