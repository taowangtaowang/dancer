package com.wt.overflow.cache.impl;

import com.wt.overflow.bean.SysRole;
import com.wt.overflow.cache.BaseCacheService;
import com.wt.overflow.cache.CacheConstants;
import com.wt.overflow.dao.SysRoleMapper;
import com.wt.overflow.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.concurrent.*;

/**
 * basecache基类
 */
@Service
public class BaseCacheServiceImpl implements BaseCacheService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final ConcurrentMap<String, Future<SysRole>> BaseCacheMap_getRole_By_RoleId = new ConcurrentHashMap<String, Future<SysRole>>();

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole getRoleByRoleId(String roleId) {
        return getBaseCache(BaseCacheMap_getRole_By_RoleId, roleId,"getRoleByRoleId");
    }
    //利用future实现的缓存
    private <T> T getBaseCache(ConcurrentMap<String, Future<T>> cacheMap, String pamStr,String type) {
        while (true) {
            Future<T> f = cacheMap.get(pamStr);
            if (f == null) {
                Callable<T> eval = new Callable<T>() {
                    @Override
                    public T call() throws Exception {
                        // TODO 类型转换不够优雅，后续去优化
                        if (type.equals(CacheConstants.GET_Role_By_RoleId)) {
                            Example example = new Example(SysRole.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andEqualTo("id",pamStr);
                            List<SysRole> list = sysRoleMapper.selectByExample(example);
                            return (T)(list.isEmpty()?null:list.get(0));
                        } else {
                            return null;
                        }
                    }
                };
                FutureTask<T> ft = new FutureTask<T>(eval);
                f = cacheMap.putIfAbsent(pamStr, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (ExecutionException e) {
                //todo
                logger.info(this.getClass().getName(),"缓存版块->","加载缓存失败异常");
                throw new RRException("读取用户信息异常");
            } catch (Exception e) {throw new RRException("读取角色信息异常");
            }
        }
    }

}
