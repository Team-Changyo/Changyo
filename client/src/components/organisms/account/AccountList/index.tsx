import React, { useEffect, useState } from 'react';
import { IAccount } from 'types/account';
import AccountListItem from '../AccountListItem';
import { AccountListWrapper } from './style';

interface AccountListProps {
	accountList: IAccount[];
	selectedCode: string;
}
/**
 * 계좌 목록 (썸네일, 계좌별명, 잔액, 주계좌 여부 등)
 */
function AccountList({ accountList, selectedCode }: AccountListProps) {
	const [accounts, setAccounts] = useState<IAccount[]>(accountList);

	useEffect(() => {
		if (selectedCode === '000') {
			setAccounts(accountList);
		} else {
			setAccounts(accountList.filter((el) => el.bankCode === selectedCode));
		}
	}, [accountList, selectedCode]);

	// TODO api 나오면 데이터 교체
	return (
		<AccountListWrapper>
			{accounts.length ? (
				accounts.map((el) => {
					return <AccountListItem key={el.accountId} isMainAccount={el.mainAccount} />;
				})
			) : (
				<div>현재 등록된 계좌가 없습니다.</div>
			)}
		</AccountListWrapper>
	);
}

export default AccountList;
