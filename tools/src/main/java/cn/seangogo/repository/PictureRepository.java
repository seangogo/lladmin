package cn.seangogo.repository;

import cn.seangogo.domain.Picture;
import cn.seangogo.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jie
 * @date 2018-12-27
 */
public interface PictureRepository extends JpaRepository<Picture,Long>, JpaSpecificationExecutor {
}
