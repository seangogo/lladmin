package com.seangogo.base.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author seangogo
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseRepository<T, ID> getBaseDao();

    @Override
    public T find(ID id) {
        T t = getBaseDao().getOne(id);
        return t;
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
