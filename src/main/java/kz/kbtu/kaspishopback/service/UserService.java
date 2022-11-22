package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.KsRole;
import kz.kbtu.kaspishopback.domain.KsUser;

import java.util.List;

public interface UserService {
    KsUser saveUser(KsUser user);
    KsRole saveRole(KsRole role);
    void addRoleToUser(String username, String roleName);
    KsUser getUser(String username);
    List<KsUser> getUsers();
    KsUser getCurrentUser();

}
