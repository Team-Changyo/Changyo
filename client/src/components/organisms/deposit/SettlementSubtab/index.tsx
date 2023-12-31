import React from 'react';
import { Tab, Tabs } from '@mui/material';
import { ISettlement } from 'types/deposit';
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

function SettlementSubtab(props: {
	waitSettlement: ISettlement[];
	doneSettlement: ISettlement[];
	title: string;
	moneyUnit: number;
	fetchSettlementGroup: () => void;
}) {
	const { waitSettlement, doneSettlement, title, moneyUnit, fetchSettlementGroup } = props;
	const [value, setValue] = React.useState(0);

	return (
		<SettlementSubtabContainer>
			<div className="tab-selector">
				<Tabs value={value} onChange={(_e, nv) => setValue(nv)}>
					<Tab label="반환 전" />
					<Tab label="반환 완료" />
				</Tabs>
			</div>

			<div className="tab-panels">
				{/* 반환 전 탭 */}
				<TabPanel value={value} index={0}>
					<SettlementList
						settlements={waitSettlement}
						isReturned={false}
						title={title}
						moneyUnit={moneyUnit}
						fetchSettlementGroup={fetchSettlementGroup}
					/>
				</TabPanel>

				{/* 반환 완료 탭 */}
				<TabPanel value={value} index={1}>
					<SettlementList
						settlements={doneSettlement}
						isReturned
						title={title}
						moneyUnit={moneyUnit}
						fetchSettlementGroup={fetchSettlementGroup}
					/>
				</TabPanel>
			</div>
		</SettlementSubtabContainer>
	);
}

export default SettlementSubtab;
