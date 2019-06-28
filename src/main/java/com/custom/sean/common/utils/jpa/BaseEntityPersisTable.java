package com.custom.sean.common.utils.jpa;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseEntityPersisTable<U, PK extends Serializable> extends AbstractPersistable<PK> implements Auditable<U, PK, LocalDateTime> {
    private static final long serialVersionUID = 141481953116476081L;
    @ManyToOne
    @Nullable
    private U createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date createdDate;
    @ManyToOne
    @Nullable
    private U lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date lastModifiedDate;

    public BaseEntityPersisTable() {
    }

    @Override
    public Optional<U> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    @Override
    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return null == this.createdDate ? Optional.empty() : Optional.of(LocalDateTime.ofInstant(this.createdDate.toInstant(), ZoneId.systemDefault()));
    }

    @Override
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Optional<U> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return null == this.lastModifiedDate ? Optional.empty() : Optional.of(LocalDateTime.ofInstant(this.lastModifiedDate.toInstant(), ZoneId.systemDefault()));
    }

    @Override
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
    }
}