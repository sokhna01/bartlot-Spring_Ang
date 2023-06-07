package com.bartlot.Server.service;

import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.bartlot.Server.AuthenticationException;
import com.bartlot.Server.entity.ActionEntity;
import com.bartlot.Server.entity.UsersEntity;
import com.bartlot.Server.entity.ProfilesEntity;
import com.bartlot.Server.repository.ProfilesRepository;
import com.bartlot.Server.repository.CompanyUsersRepository;
import com.bartlot.Server.repository.ProfileActionRepository;

@Service
public class LoginWeb {
    @Autowired
    private CompanyUsersRepository companyUsersRepository;

    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProfileActionRepository ProfilActionRepository;

    UsersEntity user = null;
    int idCompany = 0;
    int idUser = 0;

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> response = new HashMap<>();

        // Vérifier les informations d'authentification
        UsersEntity user = companyUsersRepository.findByUsernameAndPassword(username, password);

        System.out.println("User: " + user);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (user == null || !user.getPassword().equals(password)) {
            throw new AuthenticationException("Authentication failed. Invalid username or password.");
        }

        List<Object[]> users = companyUsersRepository.findCompanyUsers(username, password);

        if (!users.isEmpty()) {

            Object[] userFields = users.get(0);

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", userFields[0]);
            userMap.put("firstname", userFields[1]);
            userMap.put("lastname", userFields[2]);
            userMap.put("address", userFields[3]);
            userMap.put("city", userFields[4]);
            userMap.put("phone", userFields[5]);
            userMap.put("reset_password", userFields[6]);
            userMap.put("created_date", userFields[7]);
            userMap.put("language", userFields[8]);
            userMap.put("useautocompletion", userFields[9]);

            // Générer un token d'authentification
            String token = tokenService.generateToken((Integer) userFields[0]);
            if (token != null) {
                userMap.put("token", token);
            }

            // Récupérer la liste des profils et des actions pour l'utilisateur
            List<ProfilesEntity> profiles = getListProfiles((Integer) userFields[0]);
            List<ActionEntity> actions = getActionsForUser((Integer) userFields[0]);

            userMap.put("profiles", profiles);
            userMap.put("actions", actions);

            response.putAll(userMap);
            response.put("error", null);
        }
        return response;
    }

    // Liste des profiles en fonction de l'id de la compagnie
    public List<ProfilesEntity> getListProfiles(Integer idUsers) {
        List<Object[]> profiles = profilesRepository.getProfiles(idUsers);
        List<ProfilesEntity> list = new ArrayList<>();
        for (Object[] profile : profiles) {
            ProfilesEntity p = new ProfilesEntity();
            p.setPfCode((String) profile[0]);
            p.setPfName((String) profile[1]);
            list.add(p);
        }
        return list;
    }

    // Recupere les actions asscociées à un profil spécifique
    public List<ActionEntity> getListActionsForProfile(String pfCode) {
        List<Object[]> actions = ProfilActionRepository.getListActions(pfCode);
        List<ActionEntity> list = new ArrayList<>();
        for (Object[] action : actions) {
            ActionEntity act = new ActionEntity();
            act.setActCode((String) action[0]);
            act.setUrlCode((String) action[1]);
            act.setActName((String) action[2]);
            act.setCategory((String) action[3]);
            act.setActionLabel((String) action[4]);
            list.add(act);
        }

        return list;
    }

    // Recupere toutes les actions associées a tous les profiles de l'utilisateur
    public List<ActionEntity> getActionsForUser(int userId) {
        List<ProfilesEntity> profiles = getListProfiles(userId);
        List<ActionEntity> actions = new ArrayList<>();
        for (ProfilesEntity profile : profiles) {
            actions.addAll(getListActionsForProfile(profile.getPfCode()));
        }
        return actions;
    }

    public UsersEntity getUserByUsername(String username) {
        Optional<UsersEntity> userOptional = companyUsersRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UsersEntity user = userOptional.get();
        } else {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
        return user;
    }

}
