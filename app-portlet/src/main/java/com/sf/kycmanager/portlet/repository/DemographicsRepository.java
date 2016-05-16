package com.sf.kycmanager.portlet.repository;

import com.sf.biocapture.entity.Demographic;
import com.sf.biocapture.entity.WsqImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Cyprian Omenuko on 4/12/2016.
 */
@Repository
public interface DemographicsRepository extends JpaRepository<Demographic, Long> {

    //    #{#entityName}
    @Query("select d from Demographic d WHERE d.phoneNumber like %:searchParam% " +
            " or LOWER(d.uniqueId) like LOWER(%:searchParam%) " +
            " or LOWER(d.msisdnSerial) like LOWER(%:searchParam%) " +
            "or LOWER(d.surname) like LOWER(%:searchParam%) " +
            "or LOWER(d.firstName) like LOWER(%:searchParam%) " +
            " or LOWER(d.otherName) like LOWER(%:searchParam%)")
    Page<Demographic> search(@Param("searchParam") String searchParam, Pageable pageable);

    @Query("select p.passportData from PassportData p where p.basicData.id =:id")
    byte[] imageData(@Param("id") Long id);

    @Query("select f from WsqImage f where f.basicData.id =:id")
    List<WsqImage> fingerPrintsData(@Param("id") Long id);

}
