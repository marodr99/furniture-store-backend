package pk.furniturestorebackend.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.furniturestorebackend.database.orders.OrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReportService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public ReportService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<ReportDetailsResponse> getReport(ReportRequest reportRequest) {
        List<ReportDetailsResponse> reports = new ArrayList<>();
        reports.add(validateReportResponse(ordersRepository.getTheMostSoldFurniture(reportRequest.getStartDate(),
                reportRequest.getEndDate()), "The most sold furniture"));
        reports.add(validateReportResponse(ordersRepository.getTheMostPopularChair(reportRequest.getStartDate(),
                reportRequest.getEndDate()), "The most popular chair"));
        reports.add(validateReportResponse(ordersRepository.getTheMostPopularWardrobe(reportRequest.getStartDate(),
                reportRequest.getEndDate()), "The most popular wardrobe"));
        reports.add(validateReportResponse(ordersRepository.getAllSoldChairs(reportRequest.getStartDate(),
                reportRequest.getEndDate()), "All sold chairs"));
        reports.add(validateReportResponse(ordersRepository.getAllSoldWardrobes(reportRequest.getStartDate(),
                reportRequest.getEndDate()), "All sold wardrobes"));
        return reports;
    }

    private ReportDetailsResponse validateReportResponse(ReportDetailsResponse reportDetailsResponse, String defaultDescription) {
        return Objects.requireNonNullElseGet(reportDetailsResponse,
                () -> new ReportDetailsResponseImpl(0, defaultDescription, "", "", 0, 0d));
    }
}
