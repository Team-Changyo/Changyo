import React, { useState } from 'react';
import AccountListItem from '../AccountListItem';
import { AccountListWrapper } from './style';

interface AccountListProps {
	accountList: [{ code: string }];
	selectedCode: string;
}
/**
 * 계좌 목록 (썸네일, 계좌별명, 잔액, 주계좌 여부 등)
 */
function AccountList({ accountList, selectedCode }: AccountListProps) {
	const [accounts, setAccounts] = useState([{ code: '000' }, { code: '088' }]);
	// const [accounts, setAccounts] = useState(accountList);

	useState(() => {
		if (selectedCode === '000') {
			// setAccounts(accountList);
		} else {
			// TODO : API 나오면 .filter로 거를 것. (아래콘솔은 에러 방지)
			// setAccounts(accountList);
			console.log(accountList);
			console.log(setAccounts);
		}
	});

	// TODO api 나오면 데이터 교체
	return (
		<AccountListWrapper>
			{accounts.map((el) => {
				return <AccountListItem key={el.code} isMainAccount={el.code === '0'} />;
			})}
		</AccountListWrapper>
	);
}

export default AccountList;
