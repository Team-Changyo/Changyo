import React from 'react';
import { ISettlement } from 'types/deposit';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { SettlementListContainer } from './style';
import SettlementListItem from '../SettlementListItem';

function SettlementList({ settlements }: { settlements: ISettlement[] }) {
	return (
		<SettlementListContainer>
			<ListTotalText text="관리 중" totalCnt={settlements.length} />
			{settlements.map((el) => (
				<SettlementListItem key={el.key} settlement={el} />
			))}
		</SettlementListContainer>
	);
}

export default SettlementList;
