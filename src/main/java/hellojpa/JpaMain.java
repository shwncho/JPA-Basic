package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   //이걸로 DB커넥션 받았다고 생각하면 쉬움

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            Member findMember = em.find(Member.class, 1L);
            List<Member> result=em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)  // 페이징 처리시 몇페이지 부터 가져올지 OFFSET
                    .setMaxResults(8)   // 페이징 처리시 몇 개 가져올지 LIMIT
                    .getResultList();
            for(Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
