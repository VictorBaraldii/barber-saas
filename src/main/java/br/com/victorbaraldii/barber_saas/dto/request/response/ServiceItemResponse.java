package br.com.victorbaraldii.barber_saas.dto.request.response;

import java.math.BigDecimal;

public class ServiceItemResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private Long companyId;
    private String companyName;

    public ServiceItemResponse() {

    }

    public ServiceItemResponse(Long id, String name, String description, BigDecimal price, Integer duration, Long companyId, String companyName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getDuration() {
        return duration;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}
