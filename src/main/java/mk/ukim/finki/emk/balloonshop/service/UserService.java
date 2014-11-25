package mk.ukim.finki.emk.balloonshop.service;

import java.util.List;

import mk.ukim.finki.emk.balloonshop.model.User;

public interface UserService {

	public void addUser(User u);

	public void deleteUser(int id);

	public void updateUser(User u);

	public User getUser(int id);

	public List<User> getAllUsers();
}
