package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.ConnectionRepository;
import by.tut.shershnev_s.repository.UserGroupRepository;
import by.tut.shershnev_s.repository.impl.ConnectionRepositoryImpl;
import by.tut.shershnev_s.repository.impl.UserGroupRepositoryImpl;
import by.tut.shershnev_s.repository.model.UserGroup;
import by.tut.shershnev_s.service.UserGroupService;
import by.tut.shershnev_s.service.model.AddUserGroupDTO;
import by.tut.shershnev_s.service.model.UserGroupDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

public class UserGroupServiceImpl implements UserGroupService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private UserGroupRepository userGroupRepository = UserGroupRepositoryImpl.getInstance();

    @Override
    public void deleteTable() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                userGroupRepository.deleteTable(connection);
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
                userGroupRepository.createTable(connection);
                connection.commit();
                logger.info("table user_group created");
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
    public UserGroupDTO add(AddUserGroupDTO addUserGroupDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                UserGroup userGroup = convertDTOToUserGroup(addUserGroupDTO);
                userGroup = userGroupRepository.add(connection, userGroup);
                connection.commit();
                return convertUserGroupToDTO(userGroup);
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't add user groups");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
        return null;
    }

    private UserGroupDTO convertUserGroupToDTO(UserGroup userGroup) {
        UserGroupDTO userGroupDTO = new UserGroupDTO();
        userGroupDTO.setName(userGroup.getName());
        return userGroupDTO;
    }

    private UserGroup convertDTOToUserGroup(AddUserGroupDTO addUserGroupDTO) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(addUserGroupDTO.getName());
        return userGroup;
    }
}
