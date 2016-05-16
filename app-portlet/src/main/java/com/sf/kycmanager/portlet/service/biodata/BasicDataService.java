package com.sf.kycmanager.portlet.service.biodata;


import com.sf.biocapture.entity.BasicData;

import java.util.List;
import java.util.Optional;

/**
 * Created by Cyprian Omenuko on 4/8/2016.
 */
public interface BasicDataService {

    Optional<BasicData> findById(Long id);

    Optional<String> findBDPassport(Long bid);

    Optional<Integer> findBDFingerCount(Long bid);

    Optional<String> findBDSpecialDataCaptured(Long bid);

    List<BasicData> getDemographicsData(Long bid);

}
