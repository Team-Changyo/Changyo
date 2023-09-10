package com.shinhan.changyo.domain.report;

import com.shinhan.changyo.domain.TimeBaseEntity;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.trade.Trade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 변동 사유 엔티티
 *
 * @author 최영환
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String reason;
    @Column(length = 100)
    private String description;
    @Column(nullable = false)
    private int fee;
    @Column(nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_trade_id")
    private Trade trade;

    @Builder
    private Report(String reason, String description, int fee, int amount, Member member, Trade trade) {
        this.reason = reason;
        this.description = description;
        this.fee = fee;
        this.amount = amount;
        this.member = member;
        this.trade = trade;
    }
}
