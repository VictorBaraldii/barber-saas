package br.com.victorbaraldii.barber_saas.mapper;

import br.com.victorbaraldii.barber_saas.dto.request.UserRequest;
import br.com.victorbaraldii.barber_saas.dto.request.response.UserResponse;
import br.com.victorbaraldii.barber_saas.entity.Company;
import br.com.victorbaraldii.barber_saas.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequest request, Company company) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCompany(company);

        return user;
    }

    public static UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getCompany().getId(),
                user.getCompany().getName()
        );
    }
}
