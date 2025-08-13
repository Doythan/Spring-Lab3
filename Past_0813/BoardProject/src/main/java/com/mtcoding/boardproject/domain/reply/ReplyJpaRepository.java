package com.mtcoding.boardproject.domain.reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyJpaRepository {
    private final EntityManager em;

    // 추가: 권한체크 & 리다이렉트용
    public Reply findByIdJoinUserAndBoard(int id) {
        try {
            return em.createQuery("""
                    select r from Reply r
                    join fetch r.user u
                    join fetch r.board b
                    join fetch b.user bu
                    where r.id = :id
                    """, Reply.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }

    public void deleteById(int id) {
        Query query = em.createQuery("delete from Reply r where r.id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
