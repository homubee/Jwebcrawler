package com.homubee.jwebcrawler.dto.response;

import com.homubee.jwebcrawler.domain.Gender;
import com.homubee.jwebcrawler.domain.Role;
import com.homubee.jwebcrawler.domain.RoleType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemberResponseDTO {
    private Long id;
    private List<RoleType> roleList;
    private String email;
    private String nickname;
    private Gender gender;
    private String purpose;

    public void setRoleList(List<Role> roleEntities) {
        this.roleList = roleEntities.stream().map(role -> role.getRoleType()).collect(Collectors.toList());
    }
}
