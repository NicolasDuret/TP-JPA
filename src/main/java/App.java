import domain.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-test-jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //ajout d'un livre
        Livre newLivre = new Livre();
        newLivre.setTitre("Ratatouille");
        newLivre.setAuteur("Auguste Gusteau");
        em.persist(newLivre);

        //modification d'un livre
        Livre changelivre = em.find(Livre.class, 5);
        if(changelivre != null){
            changelivre.setTitre("Du plisir dans la cuisine");
        }

        //suppression d'un livre
        Livre deleteLivre = em.find(Livre.class, 7);
        if(deleteLivre != null){
            em.remove(deleteLivre);
        }

        //Faire une requête JPQL pour extraire de la base un livre en fonction de son titre
        TypedQuery<Livre> selectQuery = em.createQuery("select l from Livre l where l.titre ='Vingt mille lieues sous les mers'", Livre.class);
        Livre selectLivre = selectQuery.getResultList().get(0);
        System.out.println(selectLivre);

        //Faire une requête JPQL pour extraire de la base un livre en fonction de son auteur
        TypedQuery<Livre> newSelectQuery = em.createQuery("select l from Livre l where l.auteur ='Léon Tolstoï'", Livre.class);
        Livre newSelectLivre = newSelectQuery.getResultList().get(0);
        System.out.println(newSelectLivre);

        //Afficher la liste de tous les livres présents en base de données (titre et auteur).
        TypedQuery<Livre> selectAllQuery = em.createQuery("select l from Livre l", Livre.class);
        int selectAllLivre = selectAllQuery.getResultList().size();
        Livre l;
        for(int i = 0; i<=selectAllLivre; i++) {
            System.out.println(l=selectAllQuery.getResultList().get(i));
        }


        //recherche d'un livre par son id
        Livre findLivre = em.find(Livre.class, 5);
        System.out.println(findLivre);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
