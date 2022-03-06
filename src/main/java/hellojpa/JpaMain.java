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
            //영속
            Member member = em.find(Member.class, 1L);
            member.setName("AAAAA");

            em.clear();

            Member member2 = em.find(Member.class, 1L);

            System.out.println("==================");
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
