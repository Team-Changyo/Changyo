import React, { useState } from 'react';
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
import { useNavigate } from 'react-router-dom';
import RemittingPage from './RemittingPage';

function RemittanceNormalPage() {
	const [remitting, setRemitting] = useState(false);
	const navigate = useNavigate();

	const confirmRemit = () => {
		// TODO : API 나오면 연결하기
		setRemitting(true);

		setTimeout(() => {
			navigate('/success', {
				state: {
					navTitle: '송금 완료',
					mainMessage: '송금 완료!',
					message: '전인혁님에게 20,000원을 보냈어요',
					buttonText: '확인',
				},
			});
		}, 5000);
	};

	if (remitting) {
		return <RemittingPage />;
	}

	return (
		<PageLayout>
			<RemittancePageLayout
				Navbar={<SubTabNavbar text="간편 송금" closePath="/" type="close" />}
				RemittanceInfo={<RemittanceInfo isDepositRequest={false} />}
				ToAccountInfoTitle={<OptionTitleText text="입금할 계좌" />}
				ToAccountInfo={<ToAccountInfo />}
				FromAccountInfoTitle={<OptionTitleText text="출금할 내 계좌" />}
				FromAccountInfo={<FromAccountInfo />}
				MoneyUnitTitle={<OptionTitleText text="보낼 금액" />}
				MoneyUnit={<LargeMoneyText money={20000} />}
				RemittanceGuideText={<GuideText text="예금주와 금액을 한번 더 확인하세요" />}
				RemitButton={<Button text="송금하기" type="Primary" handleClick={confirmRemit} />}
			/>
		</PageLayout>
	);
}

export default RemittanceNormalPage;
