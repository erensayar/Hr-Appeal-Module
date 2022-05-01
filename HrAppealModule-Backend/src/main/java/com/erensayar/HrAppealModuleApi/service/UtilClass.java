package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.error.exception.ConflictException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UtilClass {

  private final Environment environment;

  public Boolean activeProfileCheck(String profile) {
    String[] activeProfiles = environment.getActiveProfiles();
    for (String ap : activeProfiles) {
      if (ap.equals(profile)) {
        return true;
      }
    }
    return false;
  }

  public <T> T optEmptyControl(Optional<T> opt) {
    if (opt.isEmpty()) {
      log.error("No Content whit this id. ");
      throw new NoContentException();
    }
    return opt.get();
  }

  public <T> T optPresentControl(Optional<T> opt) {
    if (opt.isPresent()) {
      log.error("Already exist. ");
      throw new ConflictException();
    }
    return opt.get();
  }

  public int generateRandomNumberAdjustedRange(int max, int min) {
    return new Random().nextInt((max - min) + 1) + min;
  }

}
