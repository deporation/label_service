package com.labservice.demo.service.impl;

import com.labservice.demo.entity.School;
import com.labservice.demo.mapper.SchoolMapper;
import com.labservice.demo.service.ISchoolService;
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
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

}
