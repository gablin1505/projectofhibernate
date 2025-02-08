package spring.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> index() {
        try {
            return entityManager.createQuery("FROM User ", User.class).getResultList();
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении запроса: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void update(int id, User user) {
        User p = entityManager.find(User.class, id);
        if (p != null) {
            p.setName(user.getName());
            p.setAge(user.getAge());
            entityManager.merge(p);
        }
    }

    @Transactional
    public void delete(int id) {
        User p = entityManager.find(User.class, id);
        if (p != null) {
            entityManager.remove(p);
        }
    }
}
