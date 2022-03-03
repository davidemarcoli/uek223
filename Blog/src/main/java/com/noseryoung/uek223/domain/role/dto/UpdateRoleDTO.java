package com.noseryoung.uek223.domain.role.dto;

import com.noseryoung.uek223.domain.authority.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDTO {

    private String name;

    private List<Authority> authorities;

}
