import React, { useState } from 'react';
import { ReactComponent as QR } from 'assets/icons/qr/qr-default-icon.svg';
import CreateQRPageLayout from 'layouts/page/CreateQRPageLayout';
import PageLayout from 'layouts/common/PageLayout';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import OptionTitleText from 'components/atoms/common/OptionTitleText';
import SelectTabTypeList from 'components/organisms/common/SelectTabTypeList';
import Button from 'components/organisms/common/Button';
import UnderLineInput from 'components/atoms/common/UnderLineInput';
import AccountSelector from 'components/organisms/account/AccountSelector';
import { IAccount } from 'types/account';

function CreateQRPage() {
	const [remittanceType, setRemittanceType] = useState(0);

	const accounts: IAccount[] = [
		{
			key: 0,
			alias: '항상 가난한 내 신한',
			bankCode: '088',
			accountNumber: '12345615496',
		},
	];

	const [selectedAccount, setSelectedAccount] = useState<IAccount>(accounts[0]);

	const tabTypes = [
		{ idx: 0, title: '단순 송금', handleClick: () => setRemittanceType(0), selected: remittanceType },
		{ idx: 1, title: '보증금 송금', handleClick: () => setRemittanceType(1), selected: remittanceType },
	];

	const createQRRequest = () => {
		console.log(remittanceType);
	};

	return (
		<PageLayout>
			<CreateQRPageLayout
				Navbar={<SubTabNavbar text="송금 QR 만들기" type="back" />}
				RemittanceTypeTitle={<OptionTitleText text="송금 유형을 선택하세요" />}
				SelectRemittanceType={<SelectTabTypeList tabTypes={tabTypes} />}
				AccountInfoTitle={<OptionTitleText text="입금받을 계좌" />}
				SelectAccount={
					<AccountSelector accounts={accounts} selected={selectedAccount!} setSelected={setSelectedAccount} />
				}
				MoneyUnitTitle={<OptionTitleText text="얼마를 받을까요?" />}
				InputMoneyUnit={<UnderLineInput placeholder="금액" unitText="원" width="150px" />}
				CreateQRBtn={<Button handleClick={createQRRequest} text="송금 QR 만들기" type="Primary" icon={<QR />} />}
				DisplayNameTitle={remittanceType ? <OptionTitleText text="요청 표시명을 입력하세요" /> : <div />}
				InputDisplayName={remittanceType ? <UnderLineInput placeholder="표시명" width="240px" /> : <div />}
			/>
		</PageLayout>
	);
}

export default CreateQRPage;
