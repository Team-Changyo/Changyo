import React, { Dispatch, SetStateAction } from 'react';
import FilterListItem from 'components/atoms/common/FilterListItem';
import { AccountFilterListWrapper } from './style';

interface AccountFilterListProps {
	bankCodes: string[];
	selectedCode: string;
	setSelectedCode: Dispatch<SetStateAction<string>>;
}
/**
 * 계좌 필터(전체, 신한은행 등등)
 * @param bankCodes 은행 코드목록 ex) ['088',031' ...]
 * @param setSelectedCode 필터 클릭 시 선택된 코드(selectedCode) 변경
 */
function AccountFilterList({ bankCodes, selectedCode, setSelectedCode }: AccountFilterListProps) {
	console.log(bankCodes);

	// TODO : api 나오면 교체
	const tmp = [
		{ code: '088', content: '신한' },
		{ code: '055', content: '국민' },
	];

	return (
		<AccountFilterListWrapper>
			{/* 전체 */}
			<FilterListItem
				key="000"
				value="000"
				$isActive={selectedCode === '000'}
				text="전체"
				setSelected={setSelectedCode}
			/>

			{/* 은행필터 */}
			{tmp.map((el) => (
				<FilterListItem
					key={el.code}
					value={el.code}
					$isActive={el.code === selectedCode}
					text={el.content}
					setSelected={setSelectedCode}
				/>
			))}
		</AccountFilterListWrapper>
	);
}

export default AccountFilterList;
