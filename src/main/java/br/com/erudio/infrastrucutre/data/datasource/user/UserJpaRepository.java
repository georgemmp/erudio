package br.com.erudio.infrastrucutre.data.datasource.user;

import br.com.erudio.infrastrucutre.data.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserModel, Long> {

    UserModel findUserByUserName(String userName);
}
