package com.sf.kycmanager.portlet.service.biodata;

import com.sf.biocapture.entity.Demographic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Created by Cyprian Omenuko on 4/12/2016.
 */
public interface DemographicsService {

    Page<Demographic> search(String searchParam, Pageable pageable);

    Page<Demographic> getAllDemographics(Pageable pageable);

    Optional<Demographic> getDemographicById(Long id);

    Optional<Demographic> addDemographic(Demographic demographic);

    Optional<String> getDemographicImage(Long id);

    List<Map<String, String>> getDemographicPrints(Long id);


}
