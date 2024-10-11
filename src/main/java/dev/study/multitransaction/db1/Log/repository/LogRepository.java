package dev.study.multitransaction.db1.Log.repository;

import dev.study.multitransaction.db1.Log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Integer> {

}
