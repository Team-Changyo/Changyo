import React, { useEffect, useState } from 'react';
import GuideText from 'components/atoms/common/GuideText';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import OptionTitleText from 'components/atoms/common/OptionTitleText';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import RemittancePageLayout from 'layouts/page/remit/RemittancePageLayout';
import Button from 'components/organisms/common/Button';
import RemittanceInfo from 'components/organisms/remittance/RemittanceInfo';
import ToAccountInfo from 'components/organisms/remittance/ToAccountInfo';
import FromAccountInfo from 'components/organisms/remittance/FromAccountInfo';
import { useLocation, useNavigate } from 'react-router-dom';
import { findRemitInfoApi, remitDepositApi } from 'utils/apis/trade';
import { IAccount, IStoreAccount } from 'types/account';
import queryString from 'query-string';
import { useRecoilState } from 'recoil';
import { memberInfoState } from 'store/member';
import RemittingPage from './RemittingPage';

function RemittanceDepositPage() {
	const [memberInfo] = useRecoilState(memberInfoState);
	const [remitting, setRemitting] = useState(false);
	const { search } = useLocation();
	const { qrCodeId } = queryString.parse(search);

	const navigate = useNavigate();
	const [storeAccount, setStoreAccount] = useState<IStoreAccount>();
	const [clientAccount, setClientAccount] = useState<IAccount>();

	const fetchData = async () => {
		try {
			if (qrCodeId) {
				const response = await findRemitInfoApi(qrCodeId as string);
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

	// 송금하기 클릭시
	const confirmRemit = async () => {
		// 로딩 컴포넌트 렌더
		setRemitting(true);

		try {
			const body = {
				accountId: clientAccount?.accountId as number,
				withdrawalAccountNumber: clientAccount?.accountNumber as string,
				qrCodeId: storeAccount?.qrCodeId as number,
				qrCodeTitle: storeAccount?.qrCodeTitle as string,
				depositAccountNumber: storeAccount?.accountNumber as string,
				amount: storeAccount?.amount as number,
				content: memberInfo?.name as string,
			};

			const response = await remitDepositApi(body);
			console.log(response);
			if (response.status === 200) {
				setTimeout(() => {
					navigate('/success', {
						state: {
							navTitle: '송금 완료',
							mainMessage: '송금 완료!',
							message: `${storeAccount?.memberName}님에게 ${storeAccount?.amount}원을 보냈어요`,
							buttonText: '확인',
						},
					});
				}, 5000);
			}
		} catch (error) {
			console.error(error);
			setTimeout(() => {
				navigate('/fail', {
					state: {
						navTitle: '송금 실패',
						mainMessage: '송금 실패!',
						message: `송금에 실패했습니다.`,
						buttonText: '돌아가기',
					},
				});
			}, 5000);
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
				Navbar={<SubTabNavbar text="보증금 송금" closePath="/" type="close" />}
				RemittanceInfo={<RemittanceInfo isDepositRequest depositTitle={storeAccount?.qrCodeTitle} />}
				ToAccountInfoTitle={<OptionTitleText text="입금할 계좌" />}
				ToAccountInfo={<ToAccountInfo />}
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

export default RemittanceDepositPage;
