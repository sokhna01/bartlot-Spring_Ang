// package com.bartlot.Server.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.bartlot.Server.entity.ProfilesEntity;
// import com.bartlot.Server.repository.ProfilesRepository;

// import com.bartlot.Server.exception.ResourceNotFoundException;

// @Service
// public class ProfileService {
// @Autowired
// ProfilesRepository profilesRepository;

// public ProfilesEntity addProfile(String code, String name, String
// description, String category) {
// ProfilesEntity profile = new ProfilesEntity();
// profile.setPfCode(code);
// profile.setPfName(name);
// profile.setPfDescription(description);
// profile.setCategory(category);
// return profilesRepository.save(profile);
// }

// public ProfilesEntity updateProfile(Integer id, ProfilesEntity
// updatedProfile) {
// ProfilesEntity existingProfile = profilesRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id "
// + id));

// existingProfile.setPfCode(updatedProfile.getPfCode());
// existingProfile.setPfName(updatedProfile.getPfName());
// existingProfile.setPfDescription(updatedProfile.getPfDescription());
// existingProfile.setCategory(updatedProfile.getCategory());

// return profilesRepository.save(existingProfile);
// }

// public void deleteProfile(Integer id) {
// ProfilesEntity existingProfile = profilesRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id "
// + id));

// profilesRepository.delete(existingProfile);
// }

// public ProfilesEntity getProfileById(Integer id) {
// return profilesRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id "
// + id));
// }
// }
