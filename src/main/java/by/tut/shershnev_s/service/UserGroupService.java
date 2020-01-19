package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.AddUserGroupDTO;
import by.tut.shershnev_s.service.model.UserGroupDTO;

public interface UserGroupService {
    void deleteTable();

    void createTable();

    UserGroupDTO add(AddUserGroupDTO UserDTO);
}
