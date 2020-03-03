package com.labservice.demo.service.impl;

import com.labservice.demo.entity.Label;
import com.labservice.demo.mapper.LabelMapper;
import com.labservice.demo.service.ILabelService;
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
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

}
