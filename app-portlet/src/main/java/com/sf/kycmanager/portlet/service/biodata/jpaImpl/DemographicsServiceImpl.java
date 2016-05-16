package com.sf.kycmanager.portlet.service.biodata.jpaImpl;

import com.liferay.portal.kernel.util.Base64;
import com.sf.biocapture.entity.Demographic;
import com.sf.biocapture.entity.WsqImage;
import com.sf.kycmanager.portlet.repository.DemographicsRepository;
import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
import org.jnbis.api.Jnbis;
import org.jnbis.api.handler.FileHandler;
import org.jnbis.api.handler.WsqHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Cyprian Omenuko on 4/12/2016.
 */
@Service("demographicsService")
@Transactional
public class DemographicsServiceImpl implements DemographicsService {

    private DemographicsRepository demographicsRepository;

    @Autowired
    public DemographicsServiceImpl(DemographicsRepository demographicsRepository) {
        this.demographicsRepository = demographicsRepository;
    }

    /**
     * The textual information for a registered Kyc User
     *
     * @param searchParam The Item search, mostly my name, phoneNumber or UniqueId
     * @param pageable
     * @return @Page of @BasicData
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Demographic> search(String searchParam, Pageable pageable) {
        return demographicsRepository.search(searchParam, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Demographic> getAllDemographics(Pageable pageable) {
        return demographicsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demographic> getDemographicById(Long id) {
        return Optional.ofNullable(demographicsRepository.findOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demographic> addDemographic(Demographic demographic) {
        return Optional.ofNullable(demographicsRepository.save(demographic));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<String> getDemographicImage(Long id) {
        return Optional.ofNullable(Base64.encode(demographicsRepository.imageData(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, String>> getDemographicPrints(Long id) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<WsqImage> wsqImages = demographicsRepository.fingerPrintsData(id);
        WsqHandler wsq = Jnbis.wsq();
        for (WsqImage w : wsqImages) {
            Map<String, String> mapPrints = new HashMap<String, String>();
            try {
                FileHandler fileHandler = wsq.decode(w.getWsqData()).toJpg();
                mapPrints.put("data", Base64.encode(fileHandler.asByteArray()));
                mapPrints.put("type", w.getFinger());
                list.add(mapPrints);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
