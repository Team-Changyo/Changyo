import React, { useState } from 'react';
import { ReactComponent as QR } from 'assets/icons/qr/qr-default-icon.svg';
import CreateQRPageLayout from 'layouts/page/qr/CreateQRPageLayout';
import PageLayout from 'layouts/common/PageLayout';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import OptionTitleText from 'components/atoms/common/OptionTitleText';
import SelectTabTypeList from 'components/organisms/common/SelectTabTypeList';
import Button from 'components/organisms/common/Button';
import UnderLineInput from 'components/atoms/common/UnderLineInput';
import AccountSelector from 'components/organisms/account/AccountSelector';
import { IAccount } from 'types/account';
import useAccountList from 'hooks/useAccountList';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import { createDepositQRApi, createNormalQRApi } from 'utils/apis/qr';
import { getExpiredTime } from 'utils/common/getExpiredTime';

function CreateQRPage() {
	const accountList = useAccountList();
	const [remittanceType, setRemittanceType] = useState(0); // 송금 타입 (단순 송금/보증금 송금)
	const [moneyUnit, setMoneyUnit] = useState(0); // 보낼 금액
	const [alias, setAlias] = useState(''); // 표시명
	const [selectedAccount, setSelectedAccount] = useState<IAccount>(accountList[0]); // 현재 선택된 계좌
	const navigate = useNavigate();

	const tabTypes = [
		{ idx: 0, title: '단순 송금', handleClick: () => setRemittanceType(0), selected: remittanceType },
		{ idx: 1, title: '보증금 송금', handleClick: () => setRemittanceType(1), selected: remittanceType },
	];

	// 간편송금 요청
	const createNormalQRRequest = async () => {
		try {
			const body = {
				bankCode: accountList[0].bankCode,
				accountNumber: accountList[0].accountNumber,
				amount: moneyUnit,
			};
			const response = await createNormalQRApi(body);
			console.log(response);
			if (response.status === 200) {
				toast.success(' 간편 송금 QR 생성이 완료되었습니다.');
				sessionStorage.setItem('normalQRInfo', JSON.stringify(response.data.data));
				sessionStorage.setItem('qrExpiredTime', getExpiredTime(3).toString());
				navigate(`/qr/normal`);
			}
		} catch (error) {
			console.error(error);
		}
	};

	// 보증금 송금 요청
	const createDepositQRRequest = async () => {
		try {
			const body = {
				accountId: accountList[0].accountId,
				amount: moneyUnit,
				title: alias,
			};
			const response = await createDepositQRApi(body);
			console.log(response);
			if (response.status === 201) {
				toast.success('보증금 송금 QR 생성이 완료되었습니다.');
				navigate(`/qr/deposit/${response.data.data.qrCodeId}`);
			}
		} catch (error) {
			console.error(error);
		}
	};

	return (
		<PageLayout>
			<CreateQRPageLayout
				Navbar={<SubTabNavbar text="송금 QR 만들기" type="back" />}
				RemittanceTypeTitle={<OptionTitleText text="송금 유형을 선택하세요" />}
				SelectRemittanceType={<SelectTabTypeList tabTypes={tabTypes} />}
				AccountInfoTitle={<OptionTitleText text="입금받을 계좌" />}
				SelectAccount={
					<AccountSelector accounts={accountList} selected={selectedAccount} setSelected={setSelectedAccount} />
				}
				MoneyUnitTitle={<OptionTitleText text="얼마를 받을까요?" />}
				InputMoneyUnit={
					<UnderLineInput
						placeholder="금액"
						unitText="원"
						width="150px"
						value={moneyUnit}
						setValue={setMoneyUnit}
						type="number"
					/>
				}
				CreateQRBtn={
					<Button
						handleClick={remittanceType ? createDepositQRRequest : createNormalQRRequest}
						text="송금 QR 만들기"
						type="Primary"
						icon={<QR />}
					/>
				}
				DisplayNameTitle={remittanceType ? <OptionTitleText text="요청 표시명을 입력하세요" /> : <div />}
				InputDisplayName={
					remittanceType ? (
						<UnderLineInput placeholder="표시명" width="240px" value={alias} setValue={setAlias} type="text" />
					) : (
						<div />
					)
				}
			/>
		</PageLayout>
	);
}

export default CreateQRPage;
