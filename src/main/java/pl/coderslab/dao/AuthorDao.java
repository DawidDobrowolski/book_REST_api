package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;


    public Author saveAuthor(Author entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Author findById(long id) {
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    public List<Author> findAll() {
        return entityManager.createQuery(
                "SELECT a FROM Author a").getResultList();
    }

    public Author update(Author entity) {
        entityManager.merge(entity);
        return entity;
    }


    public void delete(Author entity) {
        if (entity != null) {
            entityManager.remove(entityManager.contains(entity) ?
                    entity : entityManager.merge(entity));
        }
    }

}
