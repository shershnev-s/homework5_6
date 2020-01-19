package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.ConnectionRepository;
import by.tut.shershnev_s.repository.UserRepository;
import by.tut.shershnev_s.repository.impl.ConnectionRepositoryImpl;
import by.tut.shershnev_s.repository.impl.UserRepositoryImpl;
import by.tut.shershnev_s.repository.model.User;
import by.tut.shershnev_s.service.UserService;
import by.tut.shershnev_s.service.model.AddUserDTO;
import by.tut.shershnev_s.service.model.DeleteUserDTO;
import by.tut.shershnev_s.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.tut.shershnev_s.repository.constant.TaskConstant.LIMIT_AGE;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public void deleteTable() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                userRepository.deleteTable(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't delete table user");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
    }

    @Override
    public void createTable() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                userRepository.createTable(connection);
                connection.commit();
                logger.info("table user created");
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't create table user");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            List<UserDTO> userDTOS = new ArrayList<>();
            try {
                List<User> people = userRepository.findAll(connection);
                connection.commit();
                UserDTO userDTO;
                for (int i = 0; i < people.size(); i++) {
                    userDTO = convertUserToDTO(people.get(i));
                    userDTOS.add(userDTO);
                }
                return userDTOS;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't find user");
            }
        } catch (
                SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
        return null;
    }

    @Override
    public UserDTO add(AddUserDTO addUserDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                User user = convertDTOToUser(addUserDTO);
                user = userRepository.add(connection, user);
                connection.commit();
                return convertUserToDTO(user);
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't add user");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
        return null;
    }

    private UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setIsActive(user.getisActive());
        userDTO.setGroupNumber(user.getUserGroupID());
        userDTO.setAge(user.getAge());
        return userDTO;
    }

    private User convertDTOToUser(AddUserDTO addUserDTO) {
        User user = new User();
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        user.setIsActive(addUserDTO.getIsActive());
        user.setUserGroupID(addUserDTO.getGroupNumber());
        user.setAge(addUserDTO.getAge());
        return user;
    }

    @Override
    public DeleteUserDTO delete() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int deletedRows = userRepository.delete(connection, LIMIT_AGE);
                connection.commit();
                DeleteUserDTO deleteUserDTO = new DeleteUserDTO();
                deleteUserDTO.setDeletedRows(deletedRows);
                return deleteUserDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't delete user");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
        return null;
    }
}
