package dev.study.multitransaction.db2.board.repository;

import dev.study.multitransaction.db2.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer>,BoardRepositoryCustom {

}
