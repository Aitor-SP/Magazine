package Main;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Entities.Article;
import Entities.Autor;

public class JPAManager {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("MagazineJPA");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        JPAManager MA = new JPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
//        fa.readAutorsFile("src/main/java/Datos/autors.txt");.
//        System.out.println("Autors llegits des del fitxer");
//        for (int i = 0; i < fa.llistaAutors.size(); i++) {
//            System.out.println(fa.llistaAutors.get(i).toString());
//            MA.addAutor(fa.llistaAutors.get(i));
//        }
//        System.out.println("Autors llegits de la base de dades");
//        MA.listAutors();
//        MA.deleteAutor(5);
//        MA.updateAutor(12, false);
//        System.out.println("Autors llegits de la base de dades després de des actualitzacions");
//        MA.listAutors();

        fa.readArticlesFile("src/main/java/Datos/articles.txt");
        System.out.println("Articles llegits des del fitxer");
        for (int i = 0; i < fa.llistaRevistes.size(); i++) {
            System.out.println(fa.llistaRevistes.get(i).getArticle(i).toString());
            MA.addArticle(fa.llistaRevistes.get(i).getArticle(i));
        }
        System.out.println("Articles llegits de la base de dades");
        MA.listArticles();
        MA.deleteArticle(5);
        MA.updateAutor(12,false);
        System.out.println("Articles llegits de la base de dades després de les actualitzacions");
        MA.listArticles();
    }



    /* Method to CREATE an Autor in the database */
    public void addAutor(Autor autor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Autors */
    public void listAutors() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Autor> result = em.createQuery("from Autor", Autor.class)
                .getResultList();
        for (Autor autor : result) {
            System.out.println(autor.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an autor */
    public void updateAutor(Integer AutorID, boolean actiu) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor = (Autor) em.find(Autor.class, AutorID);
        autor.setActiu(actiu);
        em.merge(autor);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an employee from the records */
    public void deleteAutor(Integer AutorID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor = (Autor) em.find(Autor.class, AutorID);
        em.remove(autor);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to CREATE an Article in the database */
    public void addArticle(Article article) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Article */
    public void listArticles() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Article> result = em.createQuery("from Article", Article.class)
                .getResultList();
        for (Article article : result) {
            System.out.println(article.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an autor */
    public void updateArticle(Integer AutorID, boolean actiu) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Article article = (Article) em.find(Article.class, AutorID);
        article.setPublicable(actiu);
        em.merge(article);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an employee from the records */
    public void deleteArticle(Integer AutorID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Article article = (Article) em.find(Article.class, AutorID);
        em.remove(article);
        em.getTransaction().commit();
        em.close();
    }
}