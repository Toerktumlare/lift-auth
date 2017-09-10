package se.andolf.lift.auth.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import se.andolf.lift.auth.api.AccountInfoEntity;

/**
 * @author Thomas on 2017-09-10.
 */
public interface AccountRepository extends Neo4jRepository<AccountInfoEntity, Long> {

    AccountInfoEntity findByUsername(String username);
}
