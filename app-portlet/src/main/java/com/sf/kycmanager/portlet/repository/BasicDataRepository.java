package com.sf.kycmanager.portlet.repository;

import com.sf.biocapture.entity.BasicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cyprian Omenuko on 4/9/2016.
 */
@Repository
public interface BasicDataRepository extends JpaRepository<BasicData, Long> {

}
