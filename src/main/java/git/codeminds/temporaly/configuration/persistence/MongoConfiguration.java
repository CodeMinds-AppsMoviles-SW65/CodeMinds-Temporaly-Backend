package git.codeminds.temporaly.configuration.persistence;

import git.codeminds.temporaly.entity.Role;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:58
 */
@Configuration
@AllArgsConstructor
public class MongoConfiguration {

    private final MongoTemplate mongoTemplate;
    private final MongoMappingContext mongoMappingContext;

    @PostConstruct
    public void initIndexes() {
        IndexOperations indexOps = mongoTemplate.indexOps(Role.class);
        IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
        resolver.resolveIndexFor(Role.class).forEach(indexOps::ensureIndex);
        indexOps.ensureIndex(new Index().on("name", Sort.Direction.ASC).unique());
    }
}