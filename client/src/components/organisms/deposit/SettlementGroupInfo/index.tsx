import React from 'react';
import { formatMoney } from 'utils/common/formatMoney';
import { SettlementGroupInfoContainer } from './style';

function SettlementGroupInfo(props: { title: string; moneyUnit: number; totalMoney: number }) {
	const { title, moneyUnit, totalMoney } = props;
	const formattedMoneyUnit = formatMoney(moneyUnit);
	const formattedTotalMoney = formatMoney(totalMoney);

	return (
		<SettlementGroupInfoContainer>
			<div className="title">
				<span>{title}</span> 건
			</div>
			<div className="money-unit">
				입금단위 <span>{formattedMoneyUnit}원</span>
			</div>
			<div className="before-return-total">
				반환 전 합계 <span>{formattedTotalMoney}원</span>
			</div>
		</SettlementGroupInfoContainer>
	);
}

export default SettlementGroupInfo;
