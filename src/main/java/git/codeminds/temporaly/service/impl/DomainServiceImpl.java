package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.configuration.properties.SecureDomainsProperties;
import git.codeminds.temporaly.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 21/10/24 @ 11:47
 */
@Service
public class DomainServiceImpl implements DomainService {

    private List<String> allowedDomains;

    private final Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);

    @Autowired
    public DomainServiceImpl(SecureDomainsProperties secureDomainsProperties) {
        this.allowedDomains = secureDomainsProperties.getDomains();
        for (String domain : allowedDomains) {
            logger.info("Allowed domain: {}", domain);
        }
    }

    @Override
    public boolean isSafeEmail(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        for (String safeDomain : allowedDomains) {
            String regex = safeDomain.replace(".", "\\.").replace("*", ".*");
            if (domain.matches(regex)) {
                return true;
            }
        }
        return false;
    }
}