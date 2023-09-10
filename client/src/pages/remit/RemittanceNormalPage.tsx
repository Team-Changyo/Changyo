import React from 'react';
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

function RemittanceNormalPage() {
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
				RemitButton={<Button text="송금하기" type="Primary" handleClick={() => {}} />}
			/>
		</PageLayout>
	);
}

export default RemittanceNormalPage;
