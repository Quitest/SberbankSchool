package ru.sbt.solid_hw.reportGenerator;

import java.time.LocalDate;

public interface ReportGenerator {
    String generateReport(String departmentId, LocalDate dateFrom, LocalDate dateTo);
}
