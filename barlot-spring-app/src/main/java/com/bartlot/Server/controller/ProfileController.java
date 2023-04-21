// package com.bartlot.Server.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.http.ResponseEntity;

// import com.bartlot.Server.entity.ProfilesEntity;
// // import com.bartlot.Server.service.ProfileService;

// @RestController
// @RequestMapping("/profiles")
// @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

// public class ProfileController {

// @Autowired
// private ProfileService profileService;

// @GetMapping("/{id}")
// public ResponseEntity<ProfilesEntity> getProfile(@PathVariable Integer id) {
// ProfilesEntity profile = profileService.getProfileById(id);
// return ResponseEntity.ok(profile);
// }

// @PostMapping("/add")
// public ResponseEntity<ProfilesEntity> addProfile(@RequestParam String code,
// @RequestParam String name,
// @RequestParam String description, @RequestParam String category) {
// ProfilesEntity profile = profileService.addProfile(code, name, description,
// category);
// return ResponseEntity.ok(profile);
// }

// @PutMapping("/modify/{id}")
// public ResponseEntity<ProfilesEntity> updateProfile(@PathVariable Integer id,
// @RequestBody ProfilesEntity profile) {
// ProfilesEntity updatedProfile = profileService.updateProfile(id, profile);
// return ResponseEntity.ok(updatedProfile);
// }

// @DeleteMapping("/delete/{id}")
// public ResponseEntity<Void> deleteProfile(@PathVariable Integer id) {
// profileService.deleteProfile(id);
// return ResponseEntity.noContent().build();
// }

// }