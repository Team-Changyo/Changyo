import React, { useState, useEffect } from 'react';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import RemittancePageLayout from 'layouts/page/remit/RemittancePageLayout';
import OptionTitleText from 'components/atoms/common/OptionTitleText';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import GuideText from 'components/atoms/common/GuideText';
import Button from 'components/organisms/common/Button';
import ToAccountInfo from 'components/organisms/remittance/ToAccountInfo';
import FromAccountInfo from 'components/organisms/remittance/FromAccountInfo';
import RemittanceInfo from 'components/organisms/remittance/RemittanceInfo';
import { useLocation, useNavigate } from 'react-router-dom';
import { findNormalRemitInfoApi, remitApi } from 'utils/apis/trade';
import queryString from 'query-string';
import { IAccount, INormalStoreAccount } from 'types/account';
import RemittingPage from './RemittingPage';

function RemittanceNormalPage() {
	const [remitting, setRemitting] = useState(false);
	const navigate = useNavigate();
	const [storeAccount, setStoreAccount] = useState<INormalStoreAccount>();
	const [clientAccount, setClientAccount] = useState<IAccount>();
	const { search } = useLocation();
	const { simpleQrCodeId } = queryString.parse(search);

	const fetchData = async () => {
		try {
			if (simpleQrCodeId) {
				const response = await findNormalRemitInfoApi(simpleQrCodeId as string);
				console.log(response);
				if (response.status === 200) {
					const resObj = response.data.data;
					setStoreAccount(resObj.storeAccount);
					setClientAccount(resObj.clientAccount);
				}
			}
		} catch (error) {
			console.error(error);
		}
	};

	const confirmRemit = async () => {
		// TODO : API 나오면 연결하기
		setRemitting(true);

		try {
			const body = {
				accountId: clientAccount?.accountId as number,
				simpleQrCodeId: simpleQrCodeId as string,
			};

			const response = await remitApi(body);
			console.log(response);
			// TODO : 일반 qr송금 공유하기
			if (response.status === 200) {
				setTimeout(() => {
					navigate('/success', {
						state: {
							navTitle: '송금 완료',
							mainMessage: '송금 완료!',
							// message: `${storeAccount?.memberName}님에게 ${storeAccount?.amount}원을 보냈어요`,
							message: 'gd',
							buttonText: '확인',
						},
					});
				}, 5000);
			}
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchData();
	}, []);

	if (remitting) {
		return <RemittingPage />;
	}

	return (
		<PageLayout>
			<RemittancePageLayout
				Navbar={<SubTabNavbar text="간편 송금" closePath="/" type="close" />}
				RemittanceInfo={<RemittanceInfo isDepositRequest={false} />}
				ToAccountInfoTitle={<OptionTitleText text="입금할 계좌" />}
				ToAccountInfo={<ToAccountInfo storeAccount={storeAccount} />}
				FromAccountInfoTitle={<OptionTitleText text="출금할 내 계좌" />}
				FromAccountInfo={<FromAccountInfo account={clientAccount as IAccount} />}
				MoneyUnitTitle={<OptionTitleText text="보낼 금액" />}
				MoneyUnit={<LargeMoneyText money={storeAccount?.amount as number} />}
				RemittanceGuideText={<GuideText text="예금주와 금액을 한번 더 확인하세요" />}
				RemitButton={<Button text="송금하기" type="Primary" handleClick={confirmRemit} />}
			/>
		</PageLayout>
	);
}

export default RemittanceNormalPage;
