package com.cutm.marketconnect.Repository;

import com.cutm.marketconnect.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}
