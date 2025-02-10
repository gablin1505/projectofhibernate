package spring.daos;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDao implements UserDaoInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> index() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User p = entityManager.find(User.class, id);
        if (p != null) {
            p.setName(user.getName());
            p.setAge(user.getAge());
            entityManager.merge(p);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        User p = entityManager.find(User.class, id);
        if (p != null) {
            entityManager.remove(p);
        }
    }
}
