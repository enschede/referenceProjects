package nl.marcenschede.springtest.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marc on 21/03/15.
 */
@Repository
public interface LogItemRepository extends JpaRepository<LogItem, Long> {
}
