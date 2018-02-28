package se.kth.iv1201.recruitment.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    APPLICANT,
    RECRUITER;

    @Override
    public String getAuthority() {
        return name();
    }
}