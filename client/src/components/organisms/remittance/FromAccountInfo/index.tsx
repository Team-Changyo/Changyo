import React from 'react';
import AccountSelectListItem from 'components/organisms/account/AccountSelectListItem';
import { IAccount } from 'types/account';
import { formatMoney } from 'utils/common/formatMoney';
import { FromAccountInfoContainer } from './style';

function FromAccountInfo() {
	const account: IAccount = {
		key: 1,
		alias: '항상 가난한 내 신한',
		accountNumber: '1234567895',
		bankCode: '088',
		accountHolder: '전인혁',
	};
	return (
		<FromAccountInfoContainer>
			<AccountSelectListItem account={account} />
			{/* TODO : 추후 IMyAccount IAccount 분리할 것. === 잔액 때문에 */}
			<p className="balance">잔액 {formatMoney(30000)}원</p>
		</FromAccountInfoContainer>
	);
}

export default FromAccountInfo;
