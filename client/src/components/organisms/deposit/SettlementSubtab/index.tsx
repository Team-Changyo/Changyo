import React from 'react';
import { Tab, Tabs } from '@mui/material';
import { ISettlement, ISettlementGroup } from 'types/deposit';
import { SettlementSubtabContainer } from './style';
import SettlementList from '../SettlementList';

interface TabPanelProps {
	children?: React.ReactNode;
	index: number;
	value: number;
}

function TabPanel({ children, value, index, ...other }: TabPanelProps) {
	return (
		<div hidden={value !== index} {...other}>
			{children}
		</div>
	);
}

function SettlementSubtab({ settlementGroup }: { settlementGroup: ISettlementGroup }) {
	const [value, setValue] = React.useState(0);

	// TODO : API 나오면 통합 (보증금 정산관리 상세 조회)
	const settlements: ISettlement[] = [{ tradeId: 1, memberName: '전인혁', status: 'WAIT', tradeDate: '2023' }];

	return (
		<SettlementSubtabContainer>
			<div className="tab-selector">
				<Tabs value={value} onChange={(e, nv) => setValue(nv)}>
					<Tab label="반환 전" />
					<Tab label="반환 완료" />
				</Tabs>
			</div>

			<div className="tab-panels">
				{/* 반환 전 탭 */}
				<TabPanel value={value} index={0}>
					<SettlementList
						settlements={settlements.filter((el) => !el.status)}
						isReturned={false}
						settlementGroup={settlementGroup}
					/>
				</TabPanel>

				{/* 반환 완료 탭 */}
				<TabPanel value={value} index={1}>
					<SettlementList
						settlements={settlements.filter((el) => el.status)}
						isReturned
						settlementGroup={settlementGroup}
					/>
				</TabPanel>
			</div>
		</SettlementSubtabContainer>
	);
}

export default SettlementSubtab;
