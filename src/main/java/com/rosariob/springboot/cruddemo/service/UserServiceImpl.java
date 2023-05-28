package com.rosariob.springboot.cruddemo.service;

import com.rosariob.springboot.cruddemo.dao.RoleDao;
import com.rosariob.springboot.cruddemo.dao.UserDao;
import com.rosariob.springboot.cruddemo.dto.UserDto;
import com.rosariob.springboot.cruddemo.entity.Role;
import com.rosariob.springboot.cruddemo.entity.User;
import com.rosariob.springboot.cruddemo.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public User registerNewUserAccount(final UserDto accountDto) {
		if (userExists(accountDto)) {
			throw new UserAlreadyExistException("There is already an account with user name: " + accountDto.getUserName());
		}
		final User user = new User();
		user.setUserName(accountDto.getUserName());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEnabled(true);
		user.setRoles(Arrays.asList(roleDao.findRoleByName(accountDto.getRole().name())));
		return userDao.save(user);
	}

	public boolean userExists(UserDto accountDto){
		return findByUserName(accountDto.getUserName()) != null;
	}
}
