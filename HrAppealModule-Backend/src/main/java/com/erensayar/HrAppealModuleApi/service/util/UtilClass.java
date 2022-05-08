package com.erensayar.HrAppealModuleApi.service.util;

import com.erensayar.HrAppealModuleApi.error.exception.ConflictException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
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

  public <T> void optPresentControl(Optional<T> opt) {
    if (opt.isPresent()) {
      log.error("Already exist.");
      throw new ConflictException();
    }
  }

  public int generateRandomNumberAdjustedRange(int max, int min) {
    return new Random().nextInt((max - min) + 1) + min;
  }

  public <T> void printClassFields(Class<T> c) {
    Field[] fields = c.getDeclaredFields();
    for (Field field : fields) {
      System.out.println(field.getName() + ": " + field.getType().getSimpleName());
    }
  }

  private <T> void checkClassHasTheseFields(Class<T> c, Set<String> inputFieldNames) {
    List<String> classFieldNames = Arrays.stream(c.getDeclaredFields()).map(Field::getName).toList();
    boolean isContainAll = classFieldNames.retainAll(inputFieldNames);
    if (!isContainAll) {
      log.error("No Field whit these fields.");
      throw new NoContentException();
    }
  }

  // TODO: Phone number parser
  //  set 10 length all valid format

}
