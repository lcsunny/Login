package com.rb.login.service.impl;

import com.rb.login.exception.BusinessException;
import com.rb.login.mapper.ResourceMapper;
import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.User;
import com.rb.login.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Integer save(Resource resource){
        if(null == resource.getId()){
            //新建
            //验证名称是否重复
            if(resourceMapper.countByResourceName(resource.getName()) > 0){
                throw new BusinessException("资源名称重复");
            }
            resourceMapper.save(resource);
        }else{
            //修改
            Resource oldResource = resourceMapper.findById(resource.getId());
            if(null == oldResource){
                throw new BusinessException("资源信息不存在");
            }
            //当修改的名称和之前的名称不一样的时候，需要验证名称是否重复
            if(!oldResource.getName().equals(resource.getName())){
                if(resourceMapper.countByResourceName(resource.getName()) > 0){
                    throw new BusinessException("资源名称重复");
                }
            }
            resourceMapper.update(resource);
        }
        log.info("id:"+resource.getId());
        return resource.getId();
    }

    @Override
    public Resource findById(Integer id){
        return resourceMapper.findById(id);
    }

    @Override
    public List<Resource> findAll(){
        return resourceMapper.findAll();
    }

    @Override
    public void delete(Integer id){
        resourceMapper.delete(id);
    }
}
