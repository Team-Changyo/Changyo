import React from 'react';
import { ReactComponent as Right } from 'assets/icons/navigation/right.svg';
import { ISettlementGroup } from 'types/deposit';
import { formatMoney } from 'utils/common/formatMoney';
import { useNavigate } from 'react-router-dom';
import { SettlementGroupListItemContainer } from './style';

interface ISettlementGroupListItemProps {
	settlementGroup: ISettlementGroup;
}

function SettlementGroupListItem({ settlementGroup }: ISettlementGroupListItemProps) {
	const navigate = useNavigate();
	const formattedMoneyUnit = formatMoney(settlementGroup.amount);
	const formattedTotalMoney = formatMoney(settlementGroup.remainTotal);

	return (
		<SettlementGroupListItemContainer onClick={() => navigate(`settlement/${settlementGroup.qrCodeId}`)}>
			<div className="left">
				<div>
					<span className="title">{settlementGroup.qrCodeTitle}</span> 건
				</div>
				<div className="money-unit-row">
					입금단위 <span>{formattedMoneyUnit}원</span>
				</div>
				<div className="before-return-total-row">
					반환 전 합계 <span className="return-datetime">{formattedTotalMoney}원</span>
				</div>
			</div>
			<div className="right">
				{settlementGroup.remainCount ? (
					<span className="before-return">반환 전 {settlementGroup.remainCount}건</span>
				) : (
					<span className="after-return">반환완료</span>
				)}

				<Right />
			</div>
		</SettlementGroupListItemContainer>
	);
}

export default SettlementGroupListItem;
