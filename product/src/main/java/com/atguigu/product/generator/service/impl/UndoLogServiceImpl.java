package com.atguigu.product.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.UndoLog;
import com.atguigu.product.generator.service.UndoLogService;
import com.atguigu.product.generator.mapper.UndoLogMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【undo_log】的数据库操作Service实现
* @createDate 2025-12-08 00:02:15
*/
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog>
    implements UndoLogService{

}




