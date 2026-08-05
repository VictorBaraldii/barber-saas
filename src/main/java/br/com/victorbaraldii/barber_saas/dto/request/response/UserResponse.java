package br.com.victorbaraldii.barber_saas.dto.request.response;

public class UserResponse {
    private Long id;

    private String fullName;

    private String email;

    private Long companyId;

    private String companyName;

    public UserResponse() {
    }

    public UserResponse(Long id,
                        String fullName,
                        String email,
                        Long companyId,
                        String companyName) {

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}
