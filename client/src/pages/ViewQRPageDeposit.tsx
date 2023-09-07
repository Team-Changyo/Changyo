import React from 'react';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import SubTextButton from 'components/atoms/common/SubTextButton';
import Button from 'components/organisms/common/Button';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import QRGuideText from 'components/organisms/qr/QRGuideText';
import RemittanceRequestInfo from 'components/organisms/qr/RemittanceRequestInfo';
import PageLayout from 'layouts/common/PageLayout';
import ViewQRPageLayout from 'layouts/page/ViewQRPageLayout';

function ViewQRPageDeposit() {
	return (
		<PageLayout>
			<ViewQRPageLayout
				Navbar={<SubTabNavbar text="송금 QR 상세보기" type="close" closePath="/qr" />}
				RemittanceRequestInfo={
					<RemittanceRequestInfo
						accountInfo="신한 15168542165 (전인혁)"
						isDepositRequest
						depositTitle="럭셔리 글램핑 객실이용"
					/>
				}
				MoneyUnit={<LargeMoneyText money={30000} />}
				QRImage={
					<img src="https://img1.bizhows.com/bhfile01/__CM_FILE_DATA/201708/07/17/536998_1502092980542.png" alt="" />
				}
				GuideText={<QRGuideText isDepositRequest />}
				QRShareBtn={<Button type="Primary" handleClick={() => {}} text="QR 공유하기" />}
				LinkShareBtn={<SubTextButton text="QR 대신 링크로 공유" handleClick={() => {}} />}
			/>{' '}
		</PageLayout>
	);
}

export default ViewQRPageDeposit;
