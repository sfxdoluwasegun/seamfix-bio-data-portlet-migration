package com.sf.kycmanager.delegate.controller;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/names")
class DelegateRestController {

    private static final Logger LOG = LoggerFactory.getLogger(DelegateRestController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<String> geNames() throws SystemException, PortalException {
        LOG.info("Got request for RestContactController getContacts");
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        User user = PortalUtil.getUser(req);
        if (user != null) {
            return Arrays.asList(user.getScreenName(), user.getFullName());
        }
        return Arrays.asList("name1", "name2");
    }
}
