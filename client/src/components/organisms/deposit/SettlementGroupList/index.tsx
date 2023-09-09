import React from 'react';
import { ISettlementGroup } from 'types/deposit';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { SettlementGroupListContainer } from './style';
import SettlementGroupListItem from '../SettlementGroupListItem';

function SettlementGroupList({ settlementGroups }: { settlementGroups: ISettlementGroup[] }) {
	return (
		<SettlementGroupListContainer>
			<ListTotalText text="관리 중" totalCnt={settlementGroups.length} />
			{settlementGroups.map((el) => (
				<SettlementGroupListItem key={el.key} settlementGroup={el} />
			))}
		</SettlementGroupListContainer>
	);
}

export default SettlementGroupList;
