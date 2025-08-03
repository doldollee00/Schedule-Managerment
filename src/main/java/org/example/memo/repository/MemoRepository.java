package org.example.memo.repository;

import org.example.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByName(String name);
    List<Memo> findAllByOrderByUpdateAtDesc();
    List<Memo> findByNameOrderByUpdateAtDesc(String name);

}
