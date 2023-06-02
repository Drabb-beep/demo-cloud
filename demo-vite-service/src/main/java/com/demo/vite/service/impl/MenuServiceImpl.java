package com.demo.vite.service.impl;

import com.demo.vite.model.Menu;
import com.demo.vite.mapper.MenuMapper;
import com.demo.vite.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author df
 * @since 2023-01-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
