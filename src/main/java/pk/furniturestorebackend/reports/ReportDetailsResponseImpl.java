package pk.furniturestorebackend.reports;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportDetailsResponseImpl implements ReportDetailsResponse {
    private Integer furnitureId;
    private String description;
    private String furnitureType;
    private String name;
    private Integer totalSold;
    private Double profit;

    @Override
    public Integer getFurnitureId() {
        return furnitureId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getFurnitureType() {
        return furnitureType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getTotalSold() {
        return totalSold;
    }

    @Override
    public Double getProfit() {
        return profit;
    }
}
