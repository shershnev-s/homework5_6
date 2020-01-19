package by.tut.shershnev_s.controller.impl;

import by.tut.shershnev_s.controller.HomeWorkController;
import by.tut.shershnev_s.service.UserGroupService;
import by.tut.shershnev_s.service.UserInformationService;
import by.tut.shershnev_s.service.UserService;
import by.tut.shershnev_s.service.impl.UserGroupServiceImpl;
import by.tut.shershnev_s.service.impl.UserInformationServiceImpl;
import by.tut.shershnev_s.service.impl.UserServiceImpl;
import by.tut.shershnev_s.service.model.AddUserDTO;
import by.tut.shershnev_s.service.model.AddUserGroupDTO;
import by.tut.shershnev_s.service.model.DeleteUserDTO;
import by.tut.shershnev_s.service.model.UserDTO;
import by.tut.shershnev_s.util.RandomUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import static by.tut.shershnev_s.repository.constant.TaskConstant.*;


public class HomeWorkControllerImpl implements HomeWorkController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void runHomeWork() {
        logger.info("-------------Run HomeWork-------------");
        UserGroupService userGroupService = new UserGroupServiceImpl();
        UserService userService = new UserServiceImpl();
        UserInformationService userInformationService = new UserInformationServiceImpl();
        userInformationService.deleteTable();
        userService.deleteTable();
        userGroupService.deleteTable();
        userGroupService.createTable();
        userService.createTable();
        userInformationService.createTable();
        List<AddUserGroupDTO> userGroups = generateUserGroup(USER_GROUP_NUMBER, userGroupService);
        List<AddUserDTO> addUserDTOS = generateUser(USER_NUMBER, userService);
        List<UserDTO> userDTOS = userService.findAll();
        for (UserDTO element : userDTOS) {
            logger.info(element.getUsername() + " " + element.getPassword() + " " + element.getGroupNumber() +
                    " " + element.getAge());
        }
        DeleteUserDTO deleteUserDTO = userService.delete();
        logger.info("Deleted users older then 27: " + deleteUserDTO.getDeletedRows());
    }

    private List<AddUserGroupDTO> generateUserGroup(int userGroupNumber, UserGroupService userGroupService) {
        List<AddUserGroupDTO> addUserGroupDTOS = new ArrayList<>();
        for (int i = 0; i < userGroupNumber; i++) {
            AddUserGroupDTO addUserGroupDTO = new AddUserGroupDTO();
            addUserGroupDTO.setName("group " + i);
            addUserGroupDTOS.add(addUserGroupDTO);
            userGroupService.add(addUserGroupDTOS.get(i));
        }
        return addUserGroupDTOS;
    }

    private List<AddUserDTO> generateUser(int userNumber, UserService userService) {
        List<AddUserDTO> addUserDTOs = new ArrayList<>();
        for (int i = 0; i < userNumber; i++) {
            int randomGroupNumber = RandomUtil.getRandom(MIN_GROUP_NUMBER, MAX_GROUP_NUMBER);
            int randomUserAge = RandomUtil.getRandom(MIN_USER_AGE, MAX_USER_AGE);
            int randomIsActive = RandomUtil.getRandom(MIN_RANDOM_BOOLEAN, MAX_RANDOM_BOOLEAN);
            AddUserDTO addUserDTO = new AddUserDTO();
            addUserDTO.setUsername("user " + i);
            addUserDTO.setPassword("pas" + i);
            if (randomIsActive == 1) {
                addUserDTO.setActive(true);
            } else {
                addUserDTO.setActive(false);
            }
            addUserDTO.setGroupNumber(randomGroupNumber);
            addUserDTO.setAge(randomUserAge);
            addUserDTOs.add(addUserDTO);
            userService.add(addUserDTOs.get(i));
        }
        return addUserDTOs;
    }
}
