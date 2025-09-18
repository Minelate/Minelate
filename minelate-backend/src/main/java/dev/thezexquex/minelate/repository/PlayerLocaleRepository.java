package dev.thezexquex.minelate.repository;

import dev.thezexquex.minelate.model.PlayerLocale;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface PlayerLocaleRepository extends CrudRepository<PlayerLocale, Long> {

    Optional<PlayerLocale> findByPlayerUuid(UUID playerUuid);

    void deleteByPlayerUuid(UUID playerUuid);
}
