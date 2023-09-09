import React from 'react';
import { ReactComponent as Right } from 'assets/icons/navigation/right.svg';
import { ISettlement } from 'types/deposit';
import { formatMoney } from 'utils/common/formatMoney';
import { SettlementListItemContainer } from './style';

interface ISettlementListItemProps {
	settlement: ISettlement;
}

function SettlementListItem({ settlement }: ISettlementListItemProps) {
	const formattedMoneyUnit = formatMoney(settlement.moneyUnit);
	const formattedTotalMoney = formatMoney(settlement.cntBeforeReturn * settlement.moneyUnit);

	return (
		<SettlementListItemContainer>
			<div className="left">
				<div>
					<span className="title">{settlement.title}</span> 건
				</div>
				<div className="money-unit-row">
					입금단위 <span>{formattedMoneyUnit}원</span>
				</div>
				<div className="before-return-total-row">
					반환 전 합계 <span className="return-datetime">{formattedTotalMoney}원</span>
				</div>
			</div>
			<div className="right">
				{settlement.cntBeforeReturn ? (
					<span className="before-return">반환 전 {settlement.cntBeforeReturn}건</span>
				) : (
					<span className="after-return">반환완료</span>
				)}

				<Right />
			</div>
		</SettlementListItemContainer>
	);
}

export default SettlementListItem;
