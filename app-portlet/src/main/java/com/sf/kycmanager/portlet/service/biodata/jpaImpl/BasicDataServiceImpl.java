package com.sf.kycmanager.portlet.service.biodata.jpaImpl;


import com.sf.biocapture.entity.BasicData;
import com.sf.kycmanager.portlet.service.biodata.BasicDataService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Cyprian Omenuko on 4/11/2016.
 * <p/>
 * Implemented the @BasicDataServiceImpl
 */
@Service("bioDataService")
@Repository
@Transactional
public class BasicDataServiceImpl implements BasicDataService {

    /**
     * The textual information for a registered Kyc User
     *
     * @param id of the @BasicData to be returned
     * @return @BasicData
     */
    @Override
    @Transactional
    public Optional<BasicData> findById(Long id) {
        return null;
    }

    /**
     * The PassportDetails for a Registered KYC User
     *
     * @param bid @BasicData @ID
     * @return @Optional String
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<String> findBDPassport(Long bid) {
        return null;
    }

    /**
     * The @WsqImages for a registered Customer in KYC
     *
     * @param bid @BasicData @ID
     * @return @Optional @Integer
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Integer> findBDFingerCount(Long bid) {
        return null;
    }

    /**
     * The SpecialData for a registered Customer in KYC
     *
     * @param bid @BasicData @ID
     * @return @Optional String
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<String> findBDSpecialDataCaptured(Long bid) {
        return null;
    }

    /**
     * The demograhic information for a customer
     *
     * @param bid the @BasicData @ID
     * @return @List of @BasicData
     */
    @Override
    @Transactional(readOnly = true)
    public List<BasicData> getDemographicsData(Long bid) {
        return null;
    }
}
