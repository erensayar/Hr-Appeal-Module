package com.erensayar.HrAppealModuleApi.model.dto.request_dto.admin;

import com.erensayar.HrAppealModuleApi.security.UserRole;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Received object if save or update to db as directly, this situation creates a security
 * vulnerability. This class created for made to avoid security vulnerabilities.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCreateOrUpdateDto {

  private Integer id;
  private String name;
  private String username;
  private String surname;
  private String personalMail;
  private String workMail;
  private String password;
  List<UserRole> userRoles;

}
