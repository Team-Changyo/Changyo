import React from 'react';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { QRListWrapper } from './style';
import QRListItem from '../QRListItem';

function QRList() {
	// TODO : API 연결 필요
	const list = [0, 1, 2, 3];

	return (
		<QRListWrapper>
			<ListTotalText text="관리 중" totalCnt={list.length} />
			{list.map((el) => (
				<QRListItem
					title="럭셔리 글램핑 객실이용"
					bankCode="088"
					accountNumber="11054142084"
					moneyUnit={20000}
					key={el}
				/>
			))}
		</QRListWrapper>
	);
}

export default QRList;
