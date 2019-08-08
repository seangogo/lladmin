package com.seangogo.base.jpa;


import com.seangogo.base.exception.CheckedException;
import com.seangogo.common.enums.ResultEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author seangogo
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 获取基础数据仓库类
     *
     * @return 基础数据仓库类
     */
    public abstract BaseRepository<T, ID> getBaseDao();

    @Override
    public T find(ID id) {
        Optional<T> t = getBaseDao().findById(id);
        if (t.isPresent()) {
            return t.get();
        } else {
            throw new CheckedException(ResultEnum.DATA_NOT_EXIST);
        }
    }

    @Override
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseDao().findAll(pageable);
    }
}
