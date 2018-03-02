package se.kth.iv1201.recruitment.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * An enum containing all available authorities.
 */
public enum UserRole implements GrantedAuthority {

    APPLICANT,
    RECRUITER;

    /**
     * If a GrantedAuthority can be represented as a String with sufficient precision, this method will return that String.
     * @return a representation of the granted authority.
     */
    @Override
    public String getAuthority() {
        return name();
    }
}