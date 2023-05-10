package com.homubee.jwebcrawler.dto.response;

import com.homubee.jwebcrawler.domain.Gender;
import com.homubee.jwebcrawler.domain.RoleType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberResponseDTO {
    private Long id;
    private List<RoleType> roleList;
    private String email;
    private String nickname;
    private Gender gender;
    private String purpose;
}
