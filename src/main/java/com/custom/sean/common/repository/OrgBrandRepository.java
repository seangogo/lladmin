package com.custom.sean.common.repository;

import com.custom.sean.common.domain.Org;
import com.custom.sean.common.domain.OrgBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrgBrandRepository extends JpaRepository<OrgBrand, String>, JpaSpecificationExecutor<OrgBrand> {
    void deleteByOrg(Org org);
}
