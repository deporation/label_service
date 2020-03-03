package com.labservice.demo.service.impl;

import com.labservice.demo.entity.Project;
import com.labservice.demo.mapper.ProjectMapper;
import com.labservice.demo.service.IProjectService;
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
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
