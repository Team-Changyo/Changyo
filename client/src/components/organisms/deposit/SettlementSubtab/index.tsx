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

	// API 나오면 통합
	const settlements: ISettlement[] = [
		{ key: 0, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '홍진식' },
		{ key: 1, isReturned: true, dateTime: '2023-03-08 11:53', depositorName: '김진식' },
		{ key: 2, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '홍진식' },
		{ key: 3, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최진식' },
		{ key: 4, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최진식' },
		{ key: 5, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최진식' },
		{ key: 6, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최진식' },
		{ key: 7, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최진식' },
		{ key: 8, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최진식' },
		{ key: 9, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '전인혁' },
		{ key: 10, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '전인혁' },
		{ key: 11, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '전인혁' },
		{ key: 12, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '전인혁' },
		{ key: 13, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '전인혁' },
		{ key: 14, isReturned: false, dateTime: '2023-03-08 11:23', depositorName: '최익현' },
	];

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
						settlements={settlements.filter((el) => !el.isReturned)}
						isReturned={false}
						settlementGroup={settlementGroup}
					/>
				</TabPanel>

				{/* 반환 완료 탭 */}
				<TabPanel value={value} index={1}>
					<SettlementList
						settlements={settlements.filter((el) => el.isReturned)}
						isReturned
						settlementGroup={settlementGroup}
					/>
				</TabPanel>
			</div>
		</SettlementSubtabContainer>
	);
}

export default SettlementSubtab;
