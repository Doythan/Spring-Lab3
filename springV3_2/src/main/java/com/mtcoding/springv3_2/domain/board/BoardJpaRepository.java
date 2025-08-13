package com.mtcoding.springv3_2.domain.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈 등록
public class BoardJpaRepository {

    @PersistenceContext // JPA가 EntityManager 주입
    private EntityManager em;

    /**
     * 게시글 전체 조회
     * - 최신순으로 정렬
     * - 작성자(User)는 아직 Lazy 로딩 상태
     */
    public List<Board> findAll() {
        return em.createQuery(
                        "select b from Board b order by b.id desc", Board.class)
                .getResultList();
    }

    /**
     * 게시글 상세 조회
     * - 작성자(User) 정보까지 즉시 가져오기 위해 fetch join 사용
     */
    public Board findByIdJoinUser(int id) {
        return em.createQuery(
                        "select b from Board b join fetch b.user where b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
