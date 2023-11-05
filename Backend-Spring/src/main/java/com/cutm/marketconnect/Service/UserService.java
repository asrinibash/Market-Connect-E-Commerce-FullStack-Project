package com.cutm.marketconnect.Service;

import com.cutm.marketconnect.Model.User;
import com.cutm.marketconnect.Exception.UserException;

public interface UserService {
    public User findUserById(Long userId)throws UserException;
    public User findUserByJwt(String jwt)throws UserException;
}
