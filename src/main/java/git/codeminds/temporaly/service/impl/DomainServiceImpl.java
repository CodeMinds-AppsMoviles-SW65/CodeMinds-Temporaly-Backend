package git.codeminds.temporaly.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import git.codeminds.temporaly.pojo.AllowedDomainList;
import git.codeminds.temporaly.service.DomainService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 21/10/24 @ 11:47
 */
@Service
public class DomainServiceImpl implements DomainService {

    private List<String> allowedDomains;

    public DomainServiceImpl() throws IOException {
        loadAllowedDomains();
    }

    void loadAllowedDomains() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("domains.json");
        AllowedDomainList allowedDomainList = mapper.readValue(resource.getFile(), AllowedDomainList.class);
        allowedDomains = allowedDomainList.getSecureDomains();
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