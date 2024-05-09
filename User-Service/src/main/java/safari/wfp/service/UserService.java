package safari.wfp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import safari.wfp.exception.CustomException;
import safari.wfp.model.UserEntity;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CredentialRepresentation passwordMapper;
    private final ModelMapper modelMapper;
    private final UsersResource usersResource;

    public List<UserEntity> getUsers() {
        List<UserRepresentation> userRepresentations = usersResource.list();
        List<UserEntity> userList = new ArrayList<>();
        for (UserRepresentation userRep : userRepresentations) {
            UserEntity user = new UserEntity();
            user.setId(userRep.getId());
            user.setFirstName(userRep.getFirstName());
            user.setLastName(userRep.getLastName());
            user.setUserName(userRep.getUsername());
            user.setEmail(userRep.getEmail());
            user.setRoleNames(getRealmRolesFromUser(user.getId()));
            userList.add(user);
        }
        return userList;
    }

    private List<String> getRealmRolesFromUser(String userId) {
        List<RoleRepresentation> roleRepresentations = usersResource.get(userId).roles().realmLevel().listAvailable();
        return roleRepresentations.stream().map(RoleRepresentation::getName).toList();
    }


    public ResponseEntity<String> createUser(UserEntity user) {
        UserRepresentation newUser = modelMapper.map(user, UserRepresentation.class);
        passwordMapper.setValue(user.getPassword());
        newUser.setCredentials(List.of(passwordMapper));
        newUser.setEnabled(true);
        try {
            Response response = usersResource.create(newUser);
            if (response.getStatus() == 201) {
                log.info("Response |  Status: {} | Status Info: {}", response.getStatus(), response.getStatusInfo());
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                mapRealmRolesToUser(userId, user.getRoleNames());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            throw new CustomException("Failed to create user");
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    private void mapRealmRolesToUser(String userId, List<String> roleNames) {
        List<RoleRepresentation> roles = usersResource.get(userId).roles().realmLevel().listAvailable();

        for (String roleName : roleNames) {
            RoleRepresentation role = roles.stream()
                    .filter(r -> r.getName().equals(roleName))
                    .findFirst()
                    .orElse(null);
            if (role != null) {
                usersResource.get(userId).roles().realmLevel().add(List.of(role));
            } else {
                System.err.println("Role not found: " + roleName);
            }
        }
    }

    public List<UserRepresentation> getUser(String userName) {
        return usersResource.search(userName, true);
    }

    public void updateUser(String userId, UserEntity userDTO) {
        try {
            UserRepresentation user = modelMapper.map(userDTO, UserRepresentation.class);
            usersResource.get(userId).update(user);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public void deleteUser(String userId) {
        try {
            usersResource.get(userId)
                    .remove();
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public void sendVerificationLink(String userId) {
        try {
            usersResource.get(userId)
                    .sendVerifyEmail();
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public void sendResetPassword(String userId) {
        try {
            usersResource.get(userId)
                    .executeActionsEmail(List.of("UPDATE_PASSWORD"));
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

}
