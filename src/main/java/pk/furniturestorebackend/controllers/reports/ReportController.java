package pk.furniturestorebackend.controllers.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.furniturestorebackend.reports.ReportDetailsResponse;
import pk.furniturestorebackend.reports.ReportRequest;
import pk.furniturestorebackend.reports.ReportService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<List<ReportDetailsResponse>> getReport(@RequestBody ReportRequest reportRequest) {
        List<ReportDetailsResponse> reportDetailsResponse = reportService.getReport(reportRequest);
        return ResponseEntity.ok(reportDetailsResponse);
    }
}
