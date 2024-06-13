package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.UserProfile;
import de.sep.calocalendar.mapper.UserProfileMapper;
import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private UserProfileMapper userProfileMapper;

    @InjectMocks
    private UserProfileService userProfileService;

    private UserProfile userProfile;
    private UserProfileModel userProfileModel;

    @BeforeEach
    void setUp() {
        openMocks(this);
        userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setUserName("jannBeier");
        userProfile.setGender(1);
        userProfile.setAge(30);
        userProfile.setWeight(Double.parseDouble(75.0));
        userProfile.setLevelOfPhysicalActivity(3);

        userProfileModel = new UserProfileModel();
        userProfileModel.setId(1L);
        userProfileModel.setUserName("jannBeier");
        userProfileModel.setGender(1);
        userProfileModel.setAge(30);
        userProfileModel.setWeight(Double.parseDouble(75.0));
        userProfileModel.setLevelOfPhysicalActivity(3);
    }

    @Test
    void testGetAllUserProfiles() {
        when(userProfileRepository.findAll()).thenReturn(Arrays.asList(userProfile));
        when(userProfileMapper.toModel(any(UserProfile.class))).thenReturn(userProfileModel);

        Optional<List<UserProfileModel>> userProfileModels = userProfileService.getAllUserProfiles();

        assertThat(userProfileModels).isPresent();
        assertThat(userProfileModels.get()).hasSize(1);
        verify(userProfileRepository, times(1)).findAll();
    }

    @Test
    void testGetUserProfileById() {
        when(userProfileRepository.findById(1L)).thenReturn(Optional.of(userProfile));
        when(userProfileMapper.toModel(any(UserProfile.class))).thenReturn(userProfileModel);

        Optional<UserProfileModel> fetchedUserProfileModel = userProfileService.getUserProfileById(1L);

        assertThat(fetchedUserProfileModel).isPresent();
        assertThat(fetchedUserProfileModel.get().getUserName()).isEqualTo("jannBeier");
        verify(userProfileRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUserProfile() {
        userProfileModel.setId(null);
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);
        when(userProfileMapper.toEntity(any(UserProfileModel.class))).thenReturn(userProfile);

        Optional<Long> userProfileId = userProfileService.createUserProfile(userProfileModel);

        assertThat(userProfileId).isPresent();
        assertThat(userProfileId.get()).isEqualTo(userProfile.getId());
        verify(userProfileRepository, times(1)).save(any(UserProfile.class));
    }



    @Test
    void testUpdateUserProfile() {
        when(userProfileRepository.findById(1L)).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);
        when(userProfileMapper.toEntity(any(UserProfileModel.class))).thenReturn(userProfile);
        when(userProfileMapper.toModel(any(UserProfile.class))).thenReturn(userProfileModel);

        Optional<UserProfileModel> updatedUserProfileModel = userProfileService.updateUserProfile(1L, userProfileModel);

        assertThat(updatedUserProfileModel).isPresent();
        assertThat(updatedUserProfileModel.get().getUserName()).isEqualTo("jannBeier");
        verify(userProfileRepository, times(1)).findById(1L);
        verify(userProfileRepository, times(1)).save(any(UserProfile.class));
    }

    @Test
    void testDeleteUserProfile() {
        when(userProfileRepository.existsById(1L)).thenReturn(true);

        userProfileService.deleteUserProfile(1L);

        verify(userProfileRepository, times(1)).deleteById(1L);
    }
}

