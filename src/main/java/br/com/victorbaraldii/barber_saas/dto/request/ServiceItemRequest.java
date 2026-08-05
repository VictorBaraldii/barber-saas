package br.com.victorbaraldii.barber_saas.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ServiceItemRequest {

    @NotBlank(message = "Nome obrigatório.")
    @Size(max = 120)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Preço obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Duração obrigatória.")
    @Min(1)
    private Integer duration;

    @NotNull(message = "Empresa obrigatória.")
    private Long companyId;

    public ServiceItemRequest() {

    }

    public ServiceItemRequest(String name, String description, BigDecimal price, Integer duration, Long companyId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
