package spring.services;

import spring.models.User;

import java.util.List;

public interface UserInterface {
    public List<User> index();

    public User show(int id);

    public void save(User user);

    public void update(int id, User user);

    public void delete(int id);

}
