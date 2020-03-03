package com.labservice.demo.service.impl;

import com.labservice.demo.entity.People;
import com.labservice.demo.mapper.PeopleMapper;
import com.labservice.demo.service.IPeopleService;
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
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements IPeopleService {

}
