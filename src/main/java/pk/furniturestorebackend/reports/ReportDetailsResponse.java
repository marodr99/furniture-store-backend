package pk.furniturestorebackend.reports;

public interface ReportDetailsResponse {
    Integer getFurnitureId();

    String getDescription();

    String getFurnitureType();

    String getName();

    Integer getTotalSold();

    Double getProfit();
}
