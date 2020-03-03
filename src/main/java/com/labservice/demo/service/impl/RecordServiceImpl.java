package com.labservice.demo.service.impl;

import com.labservice.demo.entity.Record;
import com.labservice.demo.mapper.RecordMapper;
import com.labservice.demo.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author deporation
 * @since 2019-06-08
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

}
