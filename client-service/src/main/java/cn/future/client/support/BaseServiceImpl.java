package cn.future.client.support;

import cn.future.client.util.IdGenerateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author： ygl
 * @date： 2018/3/26
 * @Description：
 */
public class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public int insert(T t) {
        if (StringUtils.isEmpty(t.getId())) {
            t.setId(IdGenerateHelper.snowflakeId());
        }
        t.setCreatedBy("system");
        t.setCreatedDate(new Date());
        t.setUpdatedBy("system");
        t.setUpdatedDate(new Date());
        return baseMapper.insertSelective(t);
    }

    @Override
    public int insertList(List<T> list) {
        for (T t : list) {
            if (StringUtils.isEmpty(t.getId())) {
                t.setId(IdGenerateHelper.snowflakeId());
            }
            t.setCreatedBy("system");
            t.setCreatedDate(new Date());
            t.setUpdatedBy("system");
            t.setUpdatedDate(new Date());
        }
        return baseMapper.insertList(list);
    }

    @Override
    public int delete(T t) {
        return baseMapper.delete(t);
    }

    @Override
    public int deleteById(String id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(T t) {
        t.setUpdatedBy("system");
        t.setUpdatedDate(new Date());
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateList(List<T> list) {
        int rows = 0;
        for (T t : list) {
            t.setUpdatedBy("system");
            t.setUpdatedDate(new Date());
            rows += update(t);
        }
        return rows;
    }

    @Override
    public T findById(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<T> findByIds(String ids) {
        return baseMapper.selectByIds(ids);
    }
}
