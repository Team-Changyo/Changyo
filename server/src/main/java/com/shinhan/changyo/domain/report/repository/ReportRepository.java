package com.shinhan.changyo.domain.report.repository;

import com.shinhan.changyo.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 변동사유 레포지토리
 *
 * @author 최영환
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
