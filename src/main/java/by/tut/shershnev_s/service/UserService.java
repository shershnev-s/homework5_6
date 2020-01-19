package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.AddUserDTO;
import by.tut.shershnev_s.service.model.DeleteUserDTO;
import by.tut.shershnev_s.service.model.UserDTO;

import java.util.List;

public interface UserService {
    void deleteTable();

    void createTable();

    UserDTO add(AddUserDTO addUserDTO);

    List<UserDTO> findAll();

    DeleteUserDTO delete();
}
