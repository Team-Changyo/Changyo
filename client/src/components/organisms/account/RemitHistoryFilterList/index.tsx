import React, { Dispatch, SetStateAction } from 'react';
import FilterListItem from 'components/atoms/common/FilterListItem';
import { RemitHistoryFilterListWrapper } from './style';

interface RemitHistoryFilterListProps {
	selectedMenu: string;
	setSelcetedMenu: Dispatch<SetStateAction<string>>;
}
/**
 * 계좌 필터(전체, 신한은행 등등)
 * @param setSelcetedMenu 필터 클릭 시 선택된 메뉴(selectedMenu) 변경
 */
function RemitHistoryFilterList({ selectedMenu, setSelcetedMenu }: RemitHistoryFilterListProps) {
	// TODO : api 나오면 교체
	const tmp = [
		{ key: '1', content: '입금' },
		{ key: '2', content: '출금' },
	];

	return (
		<RemitHistoryFilterListWrapper>
			{/* 전체 */}
			<FilterListItem key="0" value="0" $isActive={selectedMenu === '0'} text="전체" setSelected={setSelcetedMenu} />

			{/* 은행필터 */}
			{tmp.map((el) => (
				<FilterListItem
					key={el.key}
					value={el.key}
					$isActive={el.key === selectedMenu}
					text={el.content}
					setSelected={setSelcetedMenu}
				/>
			))}
		</RemitHistoryFilterListWrapper>
	);
}

export default RemitHistoryFilterList;
