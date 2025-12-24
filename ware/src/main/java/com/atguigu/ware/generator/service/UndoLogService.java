package com.atguigu.ware.generator.service;

import com.atguigu.ware.generator.domain.UndoLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhenglee
* @description 针对表【undo_log】的数据库操作Service
* @createDate 2025-12-13 21:59:22
*/
public interface UndoLogService extends IService<UndoLog> {
    PageUtils queryPage(Map<String, Object> params);
}
