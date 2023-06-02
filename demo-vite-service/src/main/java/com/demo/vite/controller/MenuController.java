package com.demo.vite.controller;

import com.demo.vite.model.Menu;
import com.demo.vite.service.IMenuService;
import demo.common.model.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author df
 * @since 2023-01-14
 */
@RestController
@RequestMapping("/demo-vite-service/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private IMenuService menuService;

    @GetMapping("/getMenuList")
    public ResultVo<List<Menu>>  getMenuList(){
        List<Menu> data = menuService.list();
        return ResultVo.success(data);
    }
}
